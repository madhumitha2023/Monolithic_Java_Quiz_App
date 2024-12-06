package com.java.quiz.javaquizapplication.service;

import com.java.quiz.javaquizapplication.entity.Question;
import com.java.quiz.javaquizapplication.exception.InvalidDataException;
import com.java.quiz.javaquizapplication.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionService {

    QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public ResponseEntity<?> getQuestionsByCategory(String category){
        List<String> validCategories = List.of("Core Java", "Advanced Java");
        Map<String,String> response = new HashMap<>();
        response.put("Error","Invalid Category! Valid Categories are: "+ validCategories);

        if(!validCategories.contains(category)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(questionRepository.findByCategory(category));
    }

    public ResponseEntity<Map<String,String>> addQuestion(Question question) {
        questionRepository.save(question);
        Map<String,String> response = new HashMap<>();
        response.put("message", "Question added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Map<Integer, String>> updateQuestion(Integer id, Question question) {
        Optional<Question> optionalId = questionRepository.findById(id);
        if(optionalId.isPresent()){
            Question dbQuestion = optionalId.get();
            dbQuestion.setCategory(question.getCategory());
            dbQuestion.setDifficultyLevel(question.getDifficultyLevel());
            dbQuestion.setJavaQuestion(question.getJavaQuestion());
            dbQuestion.setOption1(question.getOption1());
            dbQuestion.setOption2(question.getOption2());
            dbQuestion.setOption3(question.getOption3());
            dbQuestion.setOption4(question.getOption4());
            dbQuestion.setRightAnswer(question.getRightAnswer());
            questionRepository.save(dbQuestion);
        }
        else{
            throw new InvalidDataException("Question not found");
        }
        Map<Integer,String> response = new HashMap<>();
        response.put(id,"Question updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Map<Integer, String>> deleteQuestion(Integer id) {
        Optional<Question> optionalId = questionRepository.findById(id);
        if(optionalId.isPresent()){
            questionRepository.deleteById(id);
        }
        else{
            throw new InvalidDataException("Id not found");
        }
        Map<Integer,String> response = new HashMap<>();
        response.put(id,"Question deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
