package com.thang.dictionary.service.lesson;

import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Lesson;
import com.thang.dictionary.service.IGeneralService;

public interface ILessonService extends IGeneralService<Lesson> {
    Iterable<Lesson> findLessonsByBook(Book book);

}
