package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.Excercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExcerciseRepository extends JpaRepository<Excercise, Long> {
    Iterable<Excercise> getExcercisesByBook_IdAndLesson_IdOrderById(Long bookId, Long lessonId);
}
