package com.thang.dictionary.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "answer_question1s")
public class AnswerQuestion1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;

    private String answer;

    @ManyToOne
    private QuestionTest1 questionTest1;

    public AnswerQuestion1(String note, String answer, QuestionTest1 questionTest1) {
        this.note = note;
        this.answer = answer;
        this.questionTest1 = questionTest1;
    }
}
