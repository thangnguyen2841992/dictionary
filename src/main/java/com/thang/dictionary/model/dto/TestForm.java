package com.thang.dictionary.model.dto;

import com.thang.dictionary.model.entity.AnswerQuestion1;
import com.thang.dictionary.model.entity.QuestionTest1;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestForm {
    private String name;

    private QuestionTest1[] questionTest1List;


    private AnswerQuestion1[] answerQuestion1List;

}
