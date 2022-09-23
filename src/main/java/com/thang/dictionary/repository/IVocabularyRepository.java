package com.thang.dictionary.repository;

import com.sun.xml.internal.org.jvnet.fastinfoset.Vocabulary;
import com.thang.dictionary.model.dto.VocabularyDTO;
import com.thang.dictionary.model.entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVocabularyRepository extends JpaRepository<Key, Long> {
    @Query(value = "select * from _keys as k order by k.vocabulary", nativeQuery = true)
    List<Key> findAllKeyAndValue();

    @Query(value = "select  * from  _keys where book_id = ?1 and lesson_id = ?2  LIMIT ?3 offset ?4", nativeQuery = true)
    Iterable<Key> getAllVocabularyOfLessonOfBook(Long bookId, Long lessonId, Integer limit, Integer offset);


    @Query(value = "select  * from  _keys where book_id = ?1 and lesson_id = ?2  ", nativeQuery = true)
    Iterable<Key> getAllVocabularyOfLessonOfBook(Long bookId, Long lessonId);

    List<Key> findByVocabularyContainingOrJapaneseContainingOrKanjiContaining(String key1, String key2, String key3);

}
