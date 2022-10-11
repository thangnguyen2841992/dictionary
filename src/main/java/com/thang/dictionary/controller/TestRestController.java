package com.thang.dictionary.controller;

import com.thang.dictionary.model.dto.ErrorMessage;
import com.thang.dictionary.model.dto.TestForm;
import com.thang.dictionary.model.entity.AnswerQuestion1;
import com.thang.dictionary.model.entity.QuestionTest1;
import com.thang.dictionary.model.entity.Test;
import com.thang.dictionary.repository.IQuestionTest1Repository;
import com.thang.dictionary.service.answerQuestion1.IAnswerQuestion1Service;
import com.thang.dictionary.service.questionTest1.IQuestionTest1Service;
import com.thang.dictionary.service.test.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/tests")
public class TestRestController {
    @Autowired
    private ITestService testService;

    @Autowired
    private IAnswerQuestion1Service answerQuestion1Service;

    @Autowired
    private IQuestionTest1Service questionTest1Service;

    @PostMapping
    public ResponseEntity<?> createNewTest(@RequestBody Test test) {
        return new ResponseEntity<>(this.testService.save(test), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getAllTest() {
        return  new ResponseEntity<>(this.testService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/test/{testId}")
    public ResponseEntity<?> findByID(@PathVariable Long testId) {
        Optional<Test> testOptional = this.testService.findById(testId);
        if (!testOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Bài test không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(testOptional.get(), HttpStatus.OK);
    }
    @PutMapping("edit/test/{testId}")
    public ResponseEntity<?> editTest(@PathVariable Long testId, @RequestBody Test test) {
        Optional<Test> testOptional = this.testService.findById(testId);
        if (!testOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Bài test không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        testOptional.get().setName(test.getName());
        return new ResponseEntity<>(this.testService.save(testOptional.get()), HttpStatus.OK);
    }
    @DeleteMapping("delete/test/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable Long testId) {
        Optional<Test> testOptional = this.testService.findById(testId);
        if (!testOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Bài test không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        this.testService.deleteById(testId);
        return new ResponseEntity<>(new ErrorMessage("Đã xoá thành công"), HttpStatus.OK);
    }
}
