package com.thang.dictionary.controller;

import com.thang.dictionary.model.dto.ErrorMessage;
import com.thang.dictionary.model.dto.QuestionTestForm;
import com.thang.dictionary.model.entity.QuestionTest;
import com.thang.dictionary.model.entity.Test;
import com.thang.dictionary.service.questionTest.IQuestionTestService;
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
}
