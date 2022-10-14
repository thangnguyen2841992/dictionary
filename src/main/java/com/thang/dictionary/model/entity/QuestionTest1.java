package com.thang.dictionary.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "question1")
public class QuestionTest1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String correctAnswer;

    @ManyToOne
    private Test test;
    @ManyToOne
    private QuestionTest questionTest;

    public QuestionTest1(String question, String correctAnswer, QuestionTest questionTest) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.questionTest = questionTest;
    }
}
