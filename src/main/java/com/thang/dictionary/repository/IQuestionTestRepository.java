package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.QuestionTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionTestRepository extends JpaRepository<QuestionTest, Long> {
    List<QuestionTest> findQuestionTestByTestId(Long testId);
}
