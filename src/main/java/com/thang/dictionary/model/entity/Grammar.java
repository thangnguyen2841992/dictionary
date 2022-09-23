package com.thang.dictionary.model.entity;

import com.thang.dictionary.model.entity.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "grammars")
public class Grammar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String situatation;

    private String explain;

    private String example;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Lesson lesson;


}
