package com.thang.dictionary.model.dto;

import com.thang.dictionary.model.entity.AnswerQuestion1;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionTest1Form {
    private String caption;

    private AnswerQuestion1[] answerQuestion1s;

    private String answer;

    private Long questionTestId;
}
