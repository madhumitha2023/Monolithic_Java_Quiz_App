package com.java.quiz.javaquizapplication.service;

import com.java.quiz.javaquizapplication.entity.Question;
import com.java.quiz.javaquizapplication.model.QuestionWrapper;
import com.java.quiz.javaquizapplication.entity.Quiz;
import com.java.quiz.javaquizapplication.exception.InvalidDataException;
import com.java.quiz.javaquizapplication.model.Response;
import com.java.quiz.javaquizapplication.repository.QuestionRepository;
import com.java.quiz.javaquizapplication.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    QuizRepository quizRepository;
    QuestionRepository questionRepository;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String quizTitle) {
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setQuizTitle(quizTitle);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return ResponseEntity.ok("Quiz created successfully with title: " + quizTitle);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> optionalId = quizRepository.findById(id);
        if (optionalId.isPresent()) {
            List<Question> questionsFromDb = optionalId.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();
            for(Question q : questionsFromDb){
                QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getJavaQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
                questionsForUser.add(qw);
            }
            return ResponseEntity.status(HttpStatus.OK).body(questionsForUser);
        }
        else{
            throw new InvalidDataException("Id Not Found");
        }
    }

    public ResponseEntity<String> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questionsFromDb = quiz.getQuestions();
        int i=0;
        int right=0;
        for(Response response : responses){
            if(response.getResponse().equalsIgnoreCase(questionsFromDb.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return ResponseEntity.status(HttpStatus.OK).body("Your Score: "+ right);
    }
}
