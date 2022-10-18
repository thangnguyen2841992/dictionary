package com.thang.dictionary.service.questionTest1;

import com.thang.dictionary.model.QuestionTest1DTo;
import com.thang.dictionary.model.entity.QuestionTest1;
import com.thang.dictionary.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IQuestionTest1Service extends IGeneralService<QuestionTest1> {
    List<QuestionTest1>  findQuestionTest1sByTestId(Long testId);

    Integer countCorrectAnswer(Long testId ,List<String> answers);

    QuestionTest1DTo findQuestionTest1DTOByQuestionTest1Id(Long questionTest1Id);

}
