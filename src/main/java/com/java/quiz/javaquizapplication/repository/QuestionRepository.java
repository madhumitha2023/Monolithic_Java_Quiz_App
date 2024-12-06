package com.java.quiz.javaquizapplication.repository;

import com.java.quiz.javaquizapplication.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM quiz_questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
