package com.thang.dictionary.controller;

import com.thang.dictionary.model.dto.ErrorMessage;
import com.thang.dictionary.model.dto.QuestionTest1Form;
import com.thang.dictionary.model.dto.QuestionTestForm;
import com.thang.dictionary.model.entity.AnswerQuestion1;
import com.thang.dictionary.model.entity.QuestionTest;
import com.thang.dictionary.model.entity.QuestionTest1;
import com.thang.dictionary.model.entity.Test;
import com.thang.dictionary.service.answerQuestion1.IAnswerQuestion1Service;
import com.thang.dictionary.service.questionTest.IQuestionTestService;
import com.thang.dictionary.service.questionTest1.IQuestionTest1Service;
import com.thang.dictionary.service.test.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/questionTests")
public class QuestionTestRestController {
    @Autowired
    private IQuestionTestService questionTestService;

    @Autowired
    private ITestService testService;

    @Autowired
    private IQuestionTest1Service questionTest1Service;

    @Autowired
    private IAnswerQuestion1Service answerQuestion1Service;

    @PostMapping
    public ResponseEntity<?> createNewQuestionTest(@RequestBody QuestionTestForm questionTest) {
        List<QuestionTest> questionTestList = this.questionTestService.findQuestionTestByTestId(questionTest.getTestId());
        for (int i = 0; i < questionTestList.size(); i++) {
            if (questionTestList.get(i).getNote() == questionTest.getNote()) {
                return new ResponseEntity<>(new ErrorMessage("Bài tập đã tồn tại"), HttpStatus.BAD_REQUEST);
            }
        }
        Optional<Test> testOptional = this.testService.findById(questionTest.getTestId());
        QuestionTest newQuestionTest = new QuestionTest();
        newQuestionTest.setNote(questionTest.getNote());
        newQuestionTest.setCaption(questionTest.getCaption());
        newQuestionTest.setTest(testOptional.get());
        this.questionTestService.save(newQuestionTest);
        return new ResponseEntity<>(newQuestionTest, HttpStatus.CREATED);
    }
    @GetMapping("/test/{testId}")
    public ResponseEntity<?> getAllQuestionTestOfTest(@PathVariable Long testId) {
        List<QuestionTest> questionTestList = this.questionTestService.findQuestionTestByTestId(testId);
        if (questionTestList.size() == 0) {
            return new ResponseEntity<>(new ErrorMessage("Bài test chưa có câu hỏi nào!"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questionTestList, HttpStatus.OK);
    }
    @PostMapping("/createQuestionTest1")
    public ResponseEntity<?> createNewQuestionTest1(@RequestBody QuestionTest1Form questionTest1Form) {
        Optional<QuestionTest> test = this.questionTestService.findById(questionTest1Form.getQuestionTestId());
        if (!test.isPresent()){
            return new ResponseEntity<>("Bài test không tồn tại!", HttpStatus.BAD_REQUEST);
        }
        QuestionTest1 newQuestionTest1 = new QuestionTest1();
        newQuestionTest1.setQuestion(questionTest1Form.getCaption());
        newQuestionTest1.setCorrectAnswer(questionTest1Form.getAnswer());
        newQuestionTest1.setQuestionTest(test.get());
        this.questionTest1Service.save(newQuestionTest1);
        AnswerQuestion1[] answerQuestion1s = questionTest1Form.getAnswerQuestion1s();
        for (int i = 0; i < answerQuestion1s.length ; i++) {
            this.answerQuestion1Service.save(new AnswerQuestion1("" + (i+1), answerQuestion1s[i].getAnswer(), newQuestionTest1));
        }
        return new ResponseEntity<>(newQuestionTest1, HttpStatus.OK);
    }
}
