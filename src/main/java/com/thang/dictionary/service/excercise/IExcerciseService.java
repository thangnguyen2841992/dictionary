package com.thang.dictionary.service.excercise;

import com.thang.dictionary.model.dto.ExcerciseDTO;
import com.thang.dictionary.model.entity.Excercise;
import com.thang.dictionary.model.entity.Question;
import com.thang.dictionary.service.IGeneralService;

public interface IExcerciseService extends IGeneralService<Excercise> {

    Iterable<Excercise> getExcercisesByBook_IdAndLesson_Id(Long bookId, Long lessonId);

    Iterable<ExcerciseDTO> getExcerciseDTO(Iterable<Excercise> excercises);
}
