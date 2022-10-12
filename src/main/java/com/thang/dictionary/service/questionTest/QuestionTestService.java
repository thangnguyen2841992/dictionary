package com.thang.dictionary.service.questionTest;

import com.thang.dictionary.model.entity.QuestionTest;
import com.thang.dictionary.repository.IQuestionTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionTestService implements IQuestionTestService{
    @Autowired
    private IQuestionTestRepository questionTestRepository;
    @Override
    public Iterable<QuestionTest> findAll() {
        return this.questionTestRepository.findAll();
    }

    @Override
    public Optional<QuestionTest> findById(Long id) {
        return this.questionTestRepository.findById(id);
    }

    @Override
    public QuestionTest save(QuestionTest questionTest) {
        return this.questionTestRepository.save(questionTest);
    }

    @Override
    public void deleteById(Long id) {
        this.questionTestRepository.deleteById(id);
    }

    @Override
    public List<QuestionTest> findQuestionTestByTestId(Long testId) {
        return this.questionTestRepository.findQuestionTestByTestId(testId);
    }
}
