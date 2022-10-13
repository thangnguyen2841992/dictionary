package com.thang.dictionary.model.dto;

import com.thang.dictionary.model.entity.QuestionTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestDTO {
    private List<QuestionTest> questionTests;


}
