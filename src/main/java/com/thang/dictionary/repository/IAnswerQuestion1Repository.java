package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.AnswerQuestion1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerQuestion1Repository extends JpaRepository<AnswerQuestion1, Long> {
}
