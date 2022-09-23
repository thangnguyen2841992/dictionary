package com.thang.dictionary.service.lesson;

import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Lesson;
import com.thang.dictionary.repository.ILessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonService implements ILessonService{
    @Autowired
    private ILessonRepository lessonRepository;
    @Override
    public Iterable<Lesson> findAll() {
        return this.lessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> findById(Long id) {
        return this.lessonRepository.findById(id);
    }

    @Override
    public Lesson save(Lesson lesson) {
        return this.lessonRepository.save(lesson);
    }

    @Override
    public void deleteById(Long id) {
            this.lessonRepository.deleteById(id);
    }

    @Override
    public Iterable<Lesson> findLessonsByBook(Book book) {
        return this.lessonRepository.findLessonsByBook(book);
    }
}
