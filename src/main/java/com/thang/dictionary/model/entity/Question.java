package com.thang.dictionary.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String answer;

    @ManyToOne
    private Excercise excercise;

    public Question(String question, String answer, Excercise excercise) {
        this.question = question;
        this.answer = answer;
        this.excercise = excercise;
    }
}
