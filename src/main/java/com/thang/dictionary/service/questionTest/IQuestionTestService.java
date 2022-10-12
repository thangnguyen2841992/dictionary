package com.thang.dictionary.service.questionTest;

import com.thang.dictionary.model.entity.QuestionTest;
import com.thang.dictionary.service.IGeneralService;

import java.util.List;

public interface IQuestionTestService extends IGeneralService<QuestionTest> {
    List<QuestionTest> findQuestionTestByTestId(Long testId);

}
