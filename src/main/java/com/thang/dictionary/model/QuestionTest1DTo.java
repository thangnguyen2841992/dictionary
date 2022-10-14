package com.thang.dictionary.model;

import com.thang.dictionary.model.entity.AnswerQuestion1;
import com.thang.dictionary.model.entity.QuestionTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionTest1DTo {
    private Long questionTest1Id;

    private String question;

    private String correctAnswer;

    private QuestionTest questionTest;

    private List<AnswerQuestion1> answerQuestion1List;
}
