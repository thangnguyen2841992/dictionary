package com.thang.dictionary.controller;

import com.thang.dictionary.model.dto.ErrorMessage;
import com.thang.dictionary.model.dto.VocabularyAudioFileForm;
import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Lesson;
import com.thang.dictionary.repository.ILessonRepository;
import com.thang.dictionary.service.book.IBookService;
import com.thang.dictionary.service.lesson.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
@CrossOrigin("*")
public class LessonRestController {
    @Autowired
    private ILessonService lessonService;
    @Autowired
    private IBookService bookService;

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<?> findById(@PathVariable Long lessonId) {
        Optional<Lesson> lessonOptional = this.lessonService.findById(lessonId);
        if (!lessonOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Lesson không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lessonOptional.get(), HttpStatus.OK);
    }
    @GetMapping("book/{bookId}")
    public ResponseEntity<?> getAllLessonOfBook(@PathVariable Long bookId) {
        Optional<Book> bookOptional = this.bookService.findById(bookId);
        if (!bookOptional.isPresent()){
            return new ResponseEntity<>(new ErrorMessage("Sách không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Iterable<Lesson> lessons = this.lessonService.findLessonsByBook(bookOptional.get());
        if (!lessons.iterator().hasNext()) {
            return new ResponseEntity<>(new ErrorMessage("Sách này chưa có tập nào"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }
    @PostMapping("/book/{bookId}/create")
    public ResponseEntity<?> createNewLessonOfBook(@PathVariable Long bookId, @RequestBody Lesson lesson) {
        Optional<Book> bookOptional = this.bookService.findById(bookId);
        if (!bookOptional.isPresent()){
            return new ResponseEntity<>(new ErrorMessage("Sách không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Lesson newLesson = new Lesson();
        newLesson.setBook(bookOptional.get());
        newLesson.setName(lesson.getName());
        this.lessonService.save(newLesson);
        return new ResponseEntity<>(newLesson, HttpStatus.CREATED);
    }
    @PutMapping("/addVocabularyFileAudio/lesson/{lessonId}")
    public ResponseEntity<?> addVocabularyFileAudio(@PathVariable Long lessonId, @RequestBody VocabularyAudioFileForm vocabularyAudioFileForm) {
        Optional<Lesson> lessonOptional = this.lessonService.findById(lessonId);
        if (!lessonOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Bài học không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        lessonOptional.get().setFileVocabularyAudio(vocabularyAudioFileForm.getAudioFile());
        this.lessonService.save(lessonOptional.get());
        return new ResponseEntity<>(lessonOptional.get(), HttpStatus.OK);
    }
}
