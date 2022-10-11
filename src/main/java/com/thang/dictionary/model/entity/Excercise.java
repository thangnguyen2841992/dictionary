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
@Table(name = "excercises")
public class Excercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String caption;

    private String audioFile;



    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Lesson lesson;
}
