package com.thang.dictionary.model.dto;

import com.thang.dictionary.model.entity.Book;
import com.thang.dictionary.model.entity.Lesson;
import com.thang.dictionary.model.entity.Question;
import com.thang.dictionary.model.entity.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcerciseDTO {
    private Long id;

    private String name;

    private String caption;

    private String audioFile;

    private Iterable<Question> questions;


}
