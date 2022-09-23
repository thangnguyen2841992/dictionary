package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.Grammar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGrammarRepository extends JpaRepository<Grammar, Long> {
}
