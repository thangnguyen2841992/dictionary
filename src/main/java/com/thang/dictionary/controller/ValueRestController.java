package com.thang.dictionary.controller;

import com.thang.dictionary.model.dto.VocabularyDTO;
import com.thang.dictionary.model.entity.Key;
import com.thang.dictionary.model.entity.Value;
import com.thang.dictionary.service.value.IValueService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/values")
public class ValueRestController {
    @Autowired
    private IValueService valueService;

    @GetMapping("/searchByKey")
    public ResponseEntity<?> searchByKey(@RequestParam(value = "key") String key) {
        List<Value> values = this.valueService.findValuesByTranslationContaining(key);
        List<VocabularyDTO> vocabularyDTOS = this.valueService.findVocabularyDTOByListValue(values);
        return new ResponseEntity<>(vocabularyDTOS, HttpStatus.OK);
    }

}
