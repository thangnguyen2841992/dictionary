package com.thang.dictionary.controller;

import com.thang.dictionary.model.dto.ErrorMessage;
import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/books")
public class BookRestController {
    @Autowired
    private IBookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        Iterable<Book> books = this.bookService.findAll();
        if (!books.iterator().hasNext()) {
            return new ResponseEntity<>(new ErrorMessage("Không có cuốn sách nào"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    @GetMapping("/book/{bookId}")
    public ResponseEntity<?> getBookByBookId(@PathVariable Long bookId) {
        Optional<Book> bookOptional = this.bookService.findById(bookId);
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Sách không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public  ResponseEntity<?> createNewBook(@RequestBody Book book) {
        Optional<Book> bookOptional = this.bookService.findBooksByName(book.getName());
        if (bookOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Sách đã tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.bookService.save(book), HttpStatus.CREATED);
    }
}
