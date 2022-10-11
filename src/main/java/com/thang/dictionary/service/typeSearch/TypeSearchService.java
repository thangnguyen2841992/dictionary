package com.thang.dictionary.service.typeSearch;

import com.thang.dictionary.model.entity.TypeSearch;
import com.thang.dictionary.repository.ITypeSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeSearchService implements ITypeSearchService {
    @Autowired
    private ITypeSearchRepository ITypeSearchRepository;

    @Override
    public Iterable<TypeSearch> findAll() {
        return this.ITypeSearchRepository.findAll();
    }

    @Override
    public Optional<TypeSearch> findById(Long id) {
        return this.ITypeSearchRepository.findById(id);
    }

    @Override
    public TypeSearch save(TypeSearch typeSearch) {
        return this.ITypeSearchRepository.save(typeSearch);
    }

    @Override
    public void deleteById(Long id) {
        this.ITypeSearchRepository.deleteById(id);
    }
}
