package com.quiz_app.QuizSpringApp.controller;

import com.quiz_app.QuizSpringApp.model.QuestionWrapper;
import com.quiz_app.QuizSpringApp.model.Response;
import com.quiz_app.QuizSpringApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numOfQue, @RequestParam String title) {
        return quizService.createQuiz(category, numOfQue, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable long id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }
}
