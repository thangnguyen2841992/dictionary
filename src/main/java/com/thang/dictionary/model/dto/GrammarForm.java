package com.thang.dictionary.model.dto;

import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Lesson;
import com.thang.dictionary.model.entity.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GrammarForm {
    private String situatation;

    private String explain;

    private String example;

    private Long userId;

    private Long bookId;

    private Long lessonId;
}
