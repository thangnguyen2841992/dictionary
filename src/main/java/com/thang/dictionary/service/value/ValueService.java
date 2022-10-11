package com.thang.dictionary.service.value;

import com.thang.dictionary.model.dto.VocabularyDTO;
import com.thang.dictionary.model.entity.Key;
import com.thang.dictionary.model.entity.Value;
import com.thang.dictionary.repository.IValueRepository;
import com.thang.dictionary.service.vocabulary.IVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ValueService implements IValueService{
    @Autowired
    private IValueRepository valueRepository;
    @Autowired
    private IVocabularyService vocabularyService;
    @Override
    public Iterable<Value> findAll() {
       return this.valueRepository.findAll();
    }

    @Override
    public Optional<Value> findById(Long id) {
        return this.valueRepository.findById(id);
    }

    @Override
    public Value save(Value value) {
        return this.valueRepository.save(value);
    }

    @Override
    public void deleteById(Long id) {
            this.valueRepository.deleteById(id);
    }

    @Override
    public List<String> findValuesByKey_Id(Long keyId) {
        return this.valueRepository.findValuesByKey_Id(keyId);
    }

    @Override
    public List<Value> findValuesByTranslationContaining(String translation) {
        return this.valueRepository.findValuesByTranslationContaining(translation);
    }

    @Override
    public List<VocabularyDTO> findVocabularyDTOByListValue(List<Value> values) {
        List<Key> keys = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            keys.add(this.vocabularyService.findById(values.get(i).getKey().getId()).get());
        }
        List<VocabularyDTO> vocabularyDTOS = this.vocabularyService.searchByKey(keys);
        return vocabularyDTOS;
    }
}
