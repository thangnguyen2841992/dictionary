package com.thang.dictionary.service.questionTest1;

import com.thang.dictionary.model.QuestionTest1DTo;
import com.thang.dictionary.model.entity.AnswerQuestion1;
import com.thang.dictionary.model.entity.QuestionTest1;
import com.thang.dictionary.repository.IQuestionTest1Repository;
import com.thang.dictionary.service.answerQuestion1.IAnswerQuestion1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionTest1Service implements IQuestionTest1Service{
    @Autowired
    private IQuestionTest1Repository questionTest1Repository;
    @Autowired
    private IAnswerQuestion1Service answerQuestion1Service;
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

    @Override
    public Integer countCorrectAnswer(Long testId, List<String> answers) {
        List<QuestionTest1> questionTest1List = this.questionTest1Repository.findQuestionTest1sByTestId(testId);
        List<String> listAnswerCorrects = new ArrayList<>();
        int count = 0;
        for (QuestionTest1 questionTest1 : questionTest1List) {
            listAnswerCorrects.add(questionTest1.getCorrectAnswer());
        }
        for (int i = 0; i < listAnswerCorrects.size(); i++) {
            if (listAnswerCorrects.get(i).equals(answers.get(i))) {
                count = count + 1;
            }
        }
        return count;
    }

    @Override
    public QuestionTest1DTo findQuestionTest1DTOByQuestionTest1Id(Long questionTest1Id) {
        List<AnswerQuestion1> answerQuestion1List = this.answerQuestion1Service.findAnswerQuestion1ByQuestionTest1Id(questionTest1Id);

        Optional<QuestionTest1> questionTest1 = findById(questionTest1Id);

        QuestionTest1DTo questionTest1DTo = new QuestionTest1DTo();

        questionTest1DTo.setQuestionTest1Id(questionTest1Id);
        questionTest1DTo.setQuestion(questionTest1.get().getQuestion());
        questionTest1DTo.setQuestionTest(questionTest1.get().getQuestionTest());
        questionTest1DTo.setCorrectAnswer(questionTest1.get().getCorrectAnswer());
        questionTest1DTo.setAnswerQuestion1List(answerQuestion1List);
        return questionTest1DTo;
    }

    @Override
    public List<QuestionTest1> findQuestionTest1sByQuestionTestId(Long questionTestId) {
        return this.questionTest1Repository.findQuestionTest1sByQuestionTestId(questionTestId);
    }
}
