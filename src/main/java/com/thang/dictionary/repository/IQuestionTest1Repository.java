package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.QuestionTest1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionTest1Repository extends JpaRepository<QuestionTest1, Long> {
}
