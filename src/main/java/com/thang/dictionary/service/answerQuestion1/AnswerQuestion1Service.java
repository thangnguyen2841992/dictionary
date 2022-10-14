package com.thang.dictionary.service.answerQuestion1;

import com.thang.dictionary.model.entity.AnswerQuestion1;
import com.thang.dictionary.repository.IAnswerQuestion1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerQuestion1Service implements IAnswerQuestion1Service{
    @Autowired
    private IAnswerQuestion1Repository answerQuestion1Repository;
    @Override
    public Iterable<AnswerQuestion1> findAll() {
        return this.answerQuestion1Repository.findAll();
    }

    @Override
    public Optional<AnswerQuestion1> findById(Long id) {
        return this.answerQuestion1Repository.findById(id);
    }

    @Override
    public AnswerQuestion1 save(AnswerQuestion1 answerQuestion1) {
        return this.answerQuestion1Repository.save(answerQuestion1);
    }

    @Override
    public void deleteById(Long id) {
        this.answerQuestion1Repository.deleteById(id);
    }

    @Override
    public List<AnswerQuestion1> findAnswerQuestion1ByQuestionTest1Id(Long questionTest1Id) {
        return this.answerQuestion1Repository.findAnswerQuestion1ByQuestionTest1Id(questionTest1Id);
    }
}
