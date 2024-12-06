package com.java.quiz.javaquizapplication.repository;

import com.java.quiz.javaquizapplication.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
