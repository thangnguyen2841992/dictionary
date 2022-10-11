package com.thang.dictionary.model.dto;

import com.thang.dictionary.model.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcerciseForm {
    private String name;

    private String caption;

    private String audioFile;

    private Long userId;

    private Long bookId;

    private Long lessonId;

    private Question[] questions;
}
