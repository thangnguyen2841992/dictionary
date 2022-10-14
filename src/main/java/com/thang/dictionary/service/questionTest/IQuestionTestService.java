package com.thang.dictionary.service.questionTest;

import com.thang.dictionary.model.QuestionTest1DTo;
import com.thang.dictionary.model.entity.QuestionTest;
import com.thang.dictionary.service.IGeneralService;

import java.util.List;

public interface IQuestionTestService extends IGeneralService<QuestionTest> {
    List<QuestionTest> findQuestionTestByTestId(Long testId);

    List<QuestionTest1DTo> findAllQuestionTest1(Long testId);
}
