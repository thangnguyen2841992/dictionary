package com.thang.dictionary.controller;

import com.thang.dictionary.model.dto.ErrorMessage;
import com.thang.dictionary.model.dto.VocabularyDTO;
import com.thang.dictionary.model.dto.VocabularyForm;
import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Lesson;
import com.thang.dictionary.model.entity.Value;
import com.thang.dictionary.model.entity.Key;
import com.thang.dictionary.model.entity.auth.User;
import com.thang.dictionary.service.book.IBookService;
import com.thang.dictionary.service.lesson.ILessonService;
import com.thang.dictionary.service.user.IUserService;
import com.thang.dictionary.service.value.IValueService;
import com.thang.dictionary.service.vocabulary.IVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vocabularies")
@CrossOrigin("*")
public class VocabularyRestController {
    @Autowired
    private IVocabularyService vocabularyService;

    @Autowired
    private IValueService valueService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private ILessonService lessonService;

    @GetMapping("/book/{bookId}/lesson/{lessonId}")
    public ResponseEntity<?> getAllVocabularyOfBookNoPage(@PathVariable Long bookId, @PathVariable Long lessonId) {
        Iterable<Key> keys = this.vocabularyService.getAllVocabularyOfLessonOfBook(bookId, lessonId);
        Iterable<VocabularyDTO> vocabularyDTOS = this.vocabularyService.searchByKey((List<Key>) keys);
        return new ResponseEntity<>(vocabularyDTOS, HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}/lesson/{lessonId}/limit/{limit}/offset/{offset}")
    public ResponseEntity<?> getAllVocabularyOfLessonOfBook(@PathVariable Long bookId, @PathVariable Long lessonId, @PathVariable Integer limit, @PathVariable Integer offset) {
        Iterable<Key> keys = this.vocabularyService.getAllVocabularyOfLessonOfBook(bookId, lessonId,limit,offset);
        Iterable<VocabularyDTO> vocabularyDTOS = this.vocabularyService.searchByKey((List<Key>) keys);
        return new ResponseEntity<>(vocabularyDTOS, HttpStatus.OK);
    }

    @GetMapping("/searchByKey")
    public ResponseEntity<?> searchByName(@RequestParam(value = "key") String key) {
        List<Key> keys = this.vocabularyService.findByVocabularyContainingOrJapaneseContainingOrKanjiContaining(key,key,key);
        if (keys.isEmpty()) {
            return new ResponseEntity<>(new ErrorMessage("Không tìm thấy kết quả nào"), HttpStatus.NO_CONTENT);
        }
        List<VocabularyDTO> vocabularyDTOList = this.vocabularyService.searchByKey(keys);
        return new ResponseEntity<>(vocabularyDTOList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVocabularies() {
        return new ResponseEntity<>(this.vocabularyService.findAllKeyAndValue(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewVocabuary(@RequestBody VocabularyForm vocabularyForm) {
        Optional<User> userOptional = this.userService.findById(vocabularyForm.getUserId());
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Người dùng không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<Book> bookOptional = this.bookService.findById(vocabularyForm.getBookId());
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Sách không tồn tại"), HttpStatus.BAD_REQUEST);
        }
        Optional<Lesson> lessonOptional = this.lessonService.findById(vocabularyForm.getLessonId());
        if (!lessonOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Tập không tồn tại"), HttpStatus.BAD_REQUEST);
        }

        Key key = new Key();
        key.setUser(userOptional.get());
        key.setBook(bookOptional.get());
        key.setLesson(lessonOptional.get());
        key.setVocabulary(vocabularyForm.getSpell());
        key.setJapanese(vocabularyForm.getHiragana());
        key.setKanji(vocabularyForm.getKanji());
        key.setVietnameseChinese(vocabularyForm.getVietnameseChinese());
        key.setKatakana(vocabularyForm.getKatakana());
        this.vocabularyService.save(key);
        Value value = new Value();
        value.setKey(key);
        value.setTranslation(vocabularyForm.getTranslation());
        this.valueService.save(value);
        return new ResponseEntity<>(new ErrorMessage("Thêm từ vựng thành công!"), HttpStatus.CREATED);

    }
}
