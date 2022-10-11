package com.thang.dictionary.controller;

import com.thang.dictionary.model.dto.ErrorMessage;
import com.thang.dictionary.model.dto.ExcerciseDTO;
import com.thang.dictionary.model.dto.ExcerciseForm;
import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Excercise;
import com.thang.dictionary.model.entity.Lesson;
import com.thang.dictionary.model.entity.Question;
import com.thang.dictionary.model.entity.auth.User;
import com.thang.dictionary.service.book.IBookService;
import com.thang.dictionary.service.excercise.IExcerciseService;
import com.thang.dictionary.service.lesson.ILessonService;
import com.thang.dictionary.service.question.IQuestionService;
import com.thang.dictionary.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/exercises")
public class ExcerciseRestController {
    @Autowired
    private IExcerciseService excerciseService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private ILessonService lessonService;
    @Autowired
    private IQuestionService questionService;
    @Value("D:/thang/Image")
    private String uploadPath;

    @PostMapping("/create/user/{userId}/book/{bookId}/lesson/{lessonId}")
    public ResponseEntity<?> createNewErcercise(@RequestBody ExcerciseForm excerciseForm) {
        Optional<User> userOptional = this.userService.findById(excerciseForm.getUserId());
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Người dùng không tồn tại"), HttpStatus.BAD_REQUEST);
        }
        Optional<Book> bookOptional = this.bookService.findById(excerciseForm.getBookId());
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Sách không tồn tại"), HttpStatus.BAD_REQUEST);
        }
        Optional<Lesson> lessonOptional = this.lessonService.findById(excerciseForm.getLessonId());
        if (!lessonOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Bài học không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Excercise newExcercise = new Excercise();
//        MultipartFile audioFile = excerciseForm.getAudioFile();
//        if (audioFile != null && audioFile.getSize() != 0) {
//            String audioFileName = audioFile.getOriginalFilename();
//            Long currentTime = System.currentTimeMillis();
//            String fileName = currentTime + audioFileName;
//            newExcercise.setAudioFile(fileName);
//            try {
//                FileCopyUtils.copy(audioFile.getBytes(), new File(uploadPath + fileName));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
        newExcercise.setAudioFile(excerciseForm.getAudioFile());
        newExcercise.setUser(userOptional.get());
        newExcercise.setBook(bookOptional.get());
        newExcercise.setLesson(lessonOptional.get());
        newExcercise.setName(excerciseForm.getName());
        newExcercise.setCaption(excerciseForm.getCaption());
        this.excerciseService.save(newExcercise);
        Question[]  questions = excerciseForm.getQuestions();
//        this.questionService.saveAll(Arrays.asList(questions));
        for (int i = 0; i < questions.length; i++) {
            this.questionService.save(new Question(questions[i].getQuestion(), questions[i].getAnswer(), newExcercise));
        }
        return new ResponseEntity<>(new ErrorMessage("Thêm bài học thành công!"), HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}/lesson/{lessonId}")
    public ResponseEntity<?> getAllExcerciseOfBookOfLesson(@PathVariable Long bookId, @PathVariable Long lessonId) {
        Optional<Book> bookOptional = this.bookService.findById(bookId);
        if (!bookOptional.isPresent()) {
            return  new ResponseEntity<>(new ErrorMessage("Sach khong ton tai!"), HttpStatus.BAD_REQUEST);
        }
        Optional<Lesson> lessonOptional = this.lessonService.findById(lessonId);
        if (!lessonOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Bai tap khong ton tai!"), HttpStatus.BAD_REQUEST);
        }
        Iterable<Excercise> excercises = this.excerciseService.getExcercisesByBook_IdAndLesson_Id(bookId, lessonId);
        Iterable<ExcerciseDTO> excerciseDTOS = this.excerciseService.getExcerciseDTO(excercises);
        return new ResponseEntity<>(excerciseDTOS, HttpStatus.OK);
    }
}
