package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.QuestionTest1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionTest1Repository extends JpaRepository<QuestionTest1, Long> {
    List<QuestionTest1>  findQuestionTest1sByTestId(Long testId);

}
