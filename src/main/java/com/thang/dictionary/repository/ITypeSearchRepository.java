package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.TypeSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeSearchRepository extends JpaRepository<TypeSearch, Long> {
}
