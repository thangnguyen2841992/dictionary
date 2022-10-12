package com.thang.dictionary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionTestForm {
    private Integer note;

    private String caption;

    private Long testId;
}
