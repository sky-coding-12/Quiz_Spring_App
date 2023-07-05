package com.quiz_app.QuizSpringApp.repository;

import com.quiz_app.QuizSpringApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Long> {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RAND() LIMIT :numOfQue", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numOfQue);
}
