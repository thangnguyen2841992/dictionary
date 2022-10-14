package com.thang.dictionary.service.questionTest1;

import com.thang.dictionary.model.entity.QuestionTest1;
import com.thang.dictionary.service.IGeneralService;

import java.util.List;

public interface IQuestionTest1Service extends IGeneralService<QuestionTest1> {
    List<QuestionTest1>  findQuestionTest1sByTestId(Long testId);

}
