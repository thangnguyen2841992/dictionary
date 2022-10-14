package com.thang.dictionary.service.questionTest;

import com.thang.dictionary.model.QuestionTest1DTo;
import com.thang.dictionary.model.entity.AnswerQuestion1;
import com.thang.dictionary.model.entity.QuestionTest;
import com.thang.dictionary.model.entity.QuestionTest1;
import com.thang.dictionary.repository.IQuestionTestRepository;
import com.thang.dictionary.service.answerQuestion1.IAnswerQuestion1Service;
import com.thang.dictionary.service.questionTest1.IQuestionTest1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionTestService implements IQuestionTestService{
    @Autowired
    private IQuestionTestRepository questionTestRepository;
    @Autowired
    private IQuestionTest1Service questionTest1Service;

    @Autowired
    private IAnswerQuestion1Service iAnswerQuestion1Service;
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
    private Long questionTest1Id;

    private String question;

    private String correctAnswer;

    private QuestionTest questionTest;

    private List<AnswerQuestion1> answerQuestion1List;
    @Override
    public List<QuestionTest1DTo> findAllQuestionTest1(Long testId) {
        List<QuestionTest1> questionTest1List = this.questionTest1Service.findQuestionTest1sByTestId(testId);
        List<QuestionTest1DTo> questionTest1DToList = new ArrayList<>();
        for (int i = 0; i < questionTest1List.size(); i++) {
            questionTest1DToList.add(new QuestionTest1DTo(questionTest1List.get(i).getId(),
                                                            questionTest1List.get(i).getQuestion(),
                                                            questionTest1List.get(i).getCorrectAnswer(),
                                                            questionTest1List.get(i).getQuestionTest(),
                                                            this.iAnswerQuestion1Service.findAnswerQuestion1ByQuestionTest1Id(questionTest1List.get(i).getId())));
        }
        return questionTest1DToList;
    }
}
