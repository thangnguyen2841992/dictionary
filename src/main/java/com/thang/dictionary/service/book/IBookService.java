package com.thang.dictionary.service.book;

import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.service.IGeneralService;

import java.util.Optional;

public interface IBookService extends IGeneralService<Book> {

    Optional<Book> findBooksByName(String name);

}
