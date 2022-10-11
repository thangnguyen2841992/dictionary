package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Long> {
    Iterable<Question> getQuestionsByExcercise_Id(Long excerciseId);
}
