package com.thang.dictionary.controller;

import com.thang.dictionary.model.QuestionTest1DTo;
import com.thang.dictionary.model.dto.ErrorMessage;
import com.thang.dictionary.model.dto.ListAnswer;
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
import java.util.Arrays;
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
        Optional<Test> testOptional = this.testService.findById(questionTest1Form.getTestId());
        if (!test.isPresent()) {
            return new ResponseEntity<>("Bài test không tồn tại!", HttpStatus.BAD_REQUEST);
        }
        QuestionTest1 newQuestionTest1 = new QuestionTest1();
        newQuestionTest1.setQuestion(questionTest1Form.getCaption());
        newQuestionTest1.setCorrectAnswer(questionTest1Form.getAnswer());
        newQuestionTest1.setQuestionTest(test.get());
        newQuestionTest1.setTest(testOptional.get());
        this.questionTest1Service.save(newQuestionTest1);
        AnswerQuestion1[] answerQuestion1s = questionTest1Form.getAnswerQuestion1s();
        for (int i = 0; i < answerQuestion1s.length; i++) {
            this.answerQuestion1Service.save(new AnswerQuestion1("" + (i + 1), answerQuestion1s[i].getAnswer(), newQuestionTest1));
        }
        return new ResponseEntity<>(newQuestionTest1, HttpStatus.OK);
    }

    @GetMapping("getAllQuestionTest1/test/{testId}")
    public ResponseEntity<?> getAllQuestionTest1OfQuestionTest(@PathVariable Long testId) {
        List<QuestionTest1DTo> questionTest1DToList = this.questionTestService.findAllQuestionTest1(testId);
        if (questionTest1DToList.size() == 0) {
            return new ResponseEntity<>(new ErrorMessage("Nội dung trống"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(questionTest1DToList, HttpStatus.OK);
    }

    @PostMapping("/getCountCorrectAnswer/test/{testId}")
    public ResponseEntity<?> getCountCorrectAnswer(@PathVariable Long testId, @RequestBody ListAnswer listAnswer) {
        List<String> answers = Arrays.asList(listAnswer.getListAnswer());
        int count = this.questionTest1Service.countCorrectAnswer(testId, answers);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/findQuestionTest1DTOByQuestionTest1Id/questionTest1/{questionTest1Id}")
    public ResponseEntity<?> findQuestionTest1DTOByQuestionTest1Id(@PathVariable Long questionTest1Id) {
        Optional<QuestionTest1> questionTest1Optional = this.questionTest1Service.findById(questionTest1Id);
        if (!questionTest1Optional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Câu hỏi không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        QuestionTest1DTo questionTest1DTo = this.questionTest1Service.findQuestionTest1DTOByQuestionTest1Id(questionTest1Id);
        return new ResponseEntity<>(questionTest1DTo, HttpStatus.OK);
    }
}
