package com.thang.dictionary.controller;

import com.thang.dictionary.model.dto.ErrorMessage;
import com.thang.dictionary.model.dto.GrammarForm;
import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Grammar;
import com.thang.dictionary.model.entity.Lesson;
import com.thang.dictionary.model.entity.auth.User;
import com.thang.dictionary.service.book.IBookService;
import com.thang.dictionary.service.grammar.IGrammarService;
import com.thang.dictionary.service.lesson.ILessonService;
import com.thang.dictionary.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/grammars")
@CrossOrigin("*")
public class GrammarRestController {
    @Autowired
    private IGrammarService grammarService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private ILessonService lessonService;

    @PostMapping("/create/user/{userId}/book/{bookId}/lesson/{lessonId}")
    public ResponseEntity<?> createNewGrammar(@PathVariable Long userId, @PathVariable Long bookId, @PathVariable Long lessonId, @RequestBody GrammarForm grammarForm) {
        Optional<User> userOptional = this.userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Người dùng không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<Book> bookOptional = this.bookService.findById(bookId);
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Sách không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<Lesson> lessonOptional = this.lessonService.findById(lessonId);
        if (!lessonOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Bài học không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Grammar newGrammar = new Grammar();
        newGrammar.setUser(userOptional.get());
        newGrammar.setBook(bookOptional.get());
        newGrammar.setLesson(lessonOptional.get());
        newGrammar.setSituatation(grammarForm.getSituatation());
        newGrammar.setExplain(grammarForm.getExplain());
        newGrammar.setExample(grammarForm.getExample());
        this.grammarService.save(newGrammar);
        return new ResponseEntity<>(newGrammar, HttpStatus.CREATED);

    }
}
