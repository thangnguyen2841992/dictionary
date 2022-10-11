package com.thang.dictionary.service.test;

import com.thang.dictionary.model.entity.Test;
import com.thang.dictionary.repository.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService implements ITestService{
    @Autowired
    private ITestRepository testRepository;
    @Override
    public Iterable<Test> findAll() {
        return this.testRepository.findAll();
    }

    @Override
    public Optional<Test> findById(Long id) {
        return this.testRepository.findById(id);
    }

    @Override
    public Test save(Test test) {
        return this.testRepository.save(test);
    }

    @Override
    public void deleteById(Long id) {
        this.testRepository.deleteById(id);
    }
}
