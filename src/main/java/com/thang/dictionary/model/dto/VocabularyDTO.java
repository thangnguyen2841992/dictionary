package com.thang.dictionary.model.dto;

import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Lesson;
import com.thang.dictionary.model.entity.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyDTO {

    private User user;
    private Book book;
    private Lesson lesson;
    private String vocabulary;
    private String japanese;
    private String kanji;
    private String vietnameseChinese;
    private List<String> translation;
}
