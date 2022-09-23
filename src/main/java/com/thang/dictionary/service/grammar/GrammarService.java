package com.thang.dictionary.service.grammar;

import com.thang.dictionary.model.entity.Grammar;
import com.thang.dictionary.repository.IGrammarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GrammarService implements IGrammarService{
    @Autowired
    private IGrammarRepository grammarRepository;

    @Override
    public Iterable<Grammar> findAll() {
        return this.grammarRepository.findAll();
    }

    @Override
    public Optional<Grammar> findById(Long id) {
        return this.grammarRepository.findById(id);
    }

    @Override
    public Grammar save(Grammar grammar) {
        return this.grammarRepository.save(grammar);
    }

    @Override
    public void deleteById(Long id) {
            this.grammarRepository.deleteById(id);
    }
}
