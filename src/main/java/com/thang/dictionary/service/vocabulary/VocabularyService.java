package com.thang.dictionary.service.vocabulary;

import com.thang.dictionary.model.dto.VocabularyDTO;
import com.thang.dictionary.model.entity.Key;
import com.thang.dictionary.repository.IValueRepository;
import com.thang.dictionary.repository.IVocabularyRepository;
import com.thang.dictionary.service.value.IValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VocabularyService implements IVocabularyService {
    @Autowired
    private IVocabularyRepository vocabularyRepository;
    @Autowired
    private IValueService valueService;

    @Override
    public Iterable<Key> findAll() {
        return this.vocabularyRepository.findAll();
    }

    @Override
    public Optional<Key> findById(Long id) {
        return this.vocabularyRepository.findById(id);
    }

    @Override
    public Key save(Key key) {
        return this.vocabularyRepository.save(key);
    }

    @Override
    public void deleteById(Long id) {
            this.vocabularyRepository.deleteById(id);
    }


    @Override
    public List<VocabularyDTO> findAllKeyAndValue() {
        List<Key> keys = this.vocabularyRepository.findAllKeyAndValue();
        List<VocabularyDTO> vocabularyDTOList = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
                vocabularyDTOList.add(new VocabularyDTO(keys.get(i).getUser(), keys.get(i).getBook(), keys.get(i).getLesson(), keys.get(i).getVocabulary(),
                        keys.get(i).getJapanese(), keys.get(i).getKanji(), keys.get(i).getVietnameseChinese(),
                        this.valueService.findValuesByKey_Id(keys.get(i).getId())));
        }
        return vocabularyDTOList;
    }

    @Override
    public List<Key> findByVocabularyContaining(String key) {
        return null;
    }


    @Override
    public List<VocabularyDTO> searchByKey(List<Key> keys) {
        List<VocabularyDTO> vocabularyDTOList = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            vocabularyDTOList.add(new VocabularyDTO(keys.get(i).getUser(), keys.get(i).getBook(), keys.get(i).getLesson(), keys.get(i).getVocabulary(),
                    keys.get(i).getJapanese(), keys.get(i).getKanji(), keys.get(i).getVietnameseChinese(),
                    this.valueService.findValuesByKey_Id(keys.get(i).getId())));
        }
        return vocabularyDTOList;
    }

    @Override
    public List<Key> findByVocabularyContainingOrJapaneseContainingOrKanjiContaining(String key1, String key2, String key3) {
        return this.vocabularyRepository.findByVocabularyContainingOrJapaneseContainingOrKanjiContaining(key1, key2, key3);
    }

    @Override
    public Iterable<Key> getAllVocabularyOfLessonOfBook(Long bookId, Long lessonId, Integer limit, Integer offset) {
        return this.vocabularyRepository.getAllVocabularyOfLessonOfBook(bookId, lessonId, limit, offset);
    }

    @Override
    public Iterable<Key> getAllVocabularyOfLessonOfBook(Long bookId, Long lessonId) {
        return this.vocabularyRepository.getAllVocabularyOfLessonOfBook(bookId, lessonId);
    }


}
