package com.thang.dictionary.service.book;

import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> findBooksByName(String name) {
        return this.bookRepository.findBooksByName(name);
    }
}
