package com.thang.dictionary.service.excercise;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.thang.dictionary.model.dto.ExcerciseDTO;
import com.thang.dictionary.model.entity.Excercise;
import com.thang.dictionary.model.entity.Question;
import com.thang.dictionary.repository.IExcerciseRepository;
import com.thang.dictionary.service.question.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExcerciseService implements IExcerciseService{
    @Autowired
    private IExcerciseRepository excerciseRepository;
    @Autowired
    private IQuestionService questionService;
    @Override
    public Iterable<Excercise> findAll() {
        return this.excerciseRepository.findAll();
    }

    @Override
    public Optional<Excercise> findById(Long id) {
        return this.excerciseRepository.findById(id);
    }

    @Override
    public Excercise save(Excercise excercise) {
        return this.excerciseRepository.save(excercise);
    }

    @Override
    public void deleteById(Long id) {
            this.excerciseRepository.deleteById(id);
    }


    @Override
    public Iterable<Excercise> getExcercisesByBook_IdAndLesson_Id(Long bookId, Long lessonId) {
        return this.excerciseRepository.getExcercisesByBook_IdAndLesson_IdOrderById(bookId, lessonId);
    }

    @Override
    public Iterable<ExcerciseDTO> getExcerciseDTO(Iterable<Excercise> excercises) {
        List<Excercise> excerciseList = (List<Excercise>) excercises;
        List<ExcerciseDTO> excerciseDTOList = new ArrayList<>();
        for (int i = 0; i < excerciseList.size(); i++) {
            excerciseDTOList.add( new ExcerciseDTO(excerciseList.get(i).getId(),
                                excerciseList.get(i).getName(),
                                excerciseList.get(i).getCaption(),
                                excerciseList.get(i).getAudioFile(),
                                this.questionService.getQuestionsByExcercise_Id(excerciseList.get(i).getId())));
        }
        return excerciseDTOList;
    }
}
