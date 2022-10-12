package com.thang.dictionary.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "question_tests")
public class QuestionTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer note;

    private String caption;

    @ManyToOne
    private Test test;

    public QuestionTest(Integer note, String caption, Test test) {
        this.note = note;
        this.caption = caption;
        this.test = test;
    }
}
