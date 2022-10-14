package com.thang.dictionary.service.questionTest1;

import com.thang.dictionary.model.entity.QuestionTest1;
import com.thang.dictionary.repository.IQuestionTest1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionTest1Service implements IQuestionTest1Service{
    @Autowired
    private IQuestionTest1Repository questionTest1Repository;
    @Override
    public Iterable<QuestionTest1> findAll() {
        return this.questionTest1Repository.findAll();
    }

    @Override
    public Optional<QuestionTest1> findById(Long id) {
        return this.questionTest1Repository.findById(id);
    }

    @Override
    public QuestionTest1 save(QuestionTest1 questionTest1) {
        return this.questionTest1Repository.save(questionTest1);
    }

    @Override
    public void deleteById(Long id) {
        this.questionTest1Repository.deleteById(id);
    }

    @Override
    public List<QuestionTest1> findQuestionTest1sByTestId(Long testId) {
        return this.questionTest1Repository.findQuestionTest1sByTestId(testId);
    }
}
