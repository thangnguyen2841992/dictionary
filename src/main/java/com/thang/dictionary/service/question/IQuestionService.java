package com.thang.dictionary.service.question;

import com.thang.dictionary.model.entity.Question;
import com.thang.dictionary.service.IGeneralService;

import java.util.List;

public interface IQuestionService extends IGeneralService<Question> {
    List<Question> saveAll(List<Question> questions);

    Iterable<Question> getQuestionsByExcercise_Id(Long excerciseId);

}
