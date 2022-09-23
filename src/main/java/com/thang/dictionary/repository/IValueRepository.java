package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IValueRepository extends JpaRepository<Value, Long> {
    @Query(value = "select v.translation from _values as v where key_id = ?1", nativeQuery = true)
    List<String> findValuesByKey_Id(Long keyId);

    List<Value> findValuesByTranslationContaining(String translation);
}
