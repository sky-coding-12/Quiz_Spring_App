package com.quiz_app.QuizSpringApp.repository;

import com.quiz_app.QuizSpringApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Long> {
}
