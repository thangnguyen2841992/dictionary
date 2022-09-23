package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ILessonRepository extends JpaRepository<Lesson, Long> {
    Iterable<Lesson> findLessonsByBook(Book book);
}
