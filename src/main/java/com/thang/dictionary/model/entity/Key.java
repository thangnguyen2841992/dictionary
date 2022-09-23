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
@Table(name = "_keys")
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String vocabulary;

    private String japanese;

    private String katakana;

    private String kanji;

    private String vietnameseChinese;

    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Lesson lesson;

}
