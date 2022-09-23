package com.thang.dictionary.service.vocabulary;


import com.thang.dictionary.model.dto.VocabularyDTO;
import com.thang.dictionary.model.entity.Key;
import com.thang.dictionary.service.IGeneralService;

import java.util.List;

public interface IVocabularyService extends IGeneralService<Key> {
    List<VocabularyDTO> findAllKeyAndValue();

    List<Key> findByVocabularyContaining(String key);

    List<VocabularyDTO> searchByKey(List<Key> keys);

    List<Key> findByVocabularyContainingOrJapaneseContainingOrKanjiContaining(String key1, String key2, String key3);

    Iterable<Key> getAllVocabularyOfLessonOfBook(Long bookId, Long lessonId, Integer limit, Integer offset);

    Iterable<Key> getAllVocabularyOfLessonOfBook(Long bookId, Long lessonId);



}
