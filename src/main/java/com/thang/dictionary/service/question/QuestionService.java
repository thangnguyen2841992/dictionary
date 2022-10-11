package com.thang.dictionary.service.question;

import com.thang.dictionary.model.entity.Question;
import com.thang.dictionary.repository.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements IQuestionService{
    @Autowired
    private IQuestionRepository questionRepository;
    @Override
    public Iterable<Question> findAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(Long id) {
        return this.questionRepository.findById(id);
    }

    @Override
    public Question save(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public void deleteById(Long id) {
        this.questionRepository.deleteById(id);
    }

    @Override
    public List<Question> saveAll(List<Question> questions) {
        return this.questionRepository.saveAll(questions);
    }

    @Override
    public Iterable<Question> getQuestionsByExcercise_Id(Long excerciseId) {
        return this.questionRepository.getQuestionsByExcercise_Id(excerciseId);
    }
}
