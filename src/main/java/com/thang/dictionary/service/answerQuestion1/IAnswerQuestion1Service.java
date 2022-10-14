package com.thang.dictionary.service.answerQuestion1;

import com.thang.dictionary.model.entity.AnswerQuestion1;
import com.thang.dictionary.service.IGeneralService;

import java.util.List;

public interface IAnswerQuestion1Service extends IGeneralService<AnswerQuestion1> {
    List<AnswerQuestion1> findAnswerQuestion1ByQuestionTest1Id(Long questionTest1Id);

}
