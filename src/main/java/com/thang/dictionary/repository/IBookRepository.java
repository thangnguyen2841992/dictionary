package com.thang.dictionary.repository;

import com.sun.xml.internal.ws.wsdl.writer.document.soap12.Body;
import com.thang.dictionary.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBooksByName(String name);
}
