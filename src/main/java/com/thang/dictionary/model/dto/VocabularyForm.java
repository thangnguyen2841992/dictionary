package com.thang.dictionary.model.dto;

import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Lesson;
import com.thang.dictionary.model.entity.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyForm {
    private String hiragana;

    private String katakana;

    private String kanji;

    private String translation;

    private String spell;

    private String vietnameseChinese;

    private Long userId;

    private Long bookId;

    private Long lessonId;
}
