package com.quiz_app.QuizSpringApp.controller;

import com.quiz_app.QuizSpringApp.model.Question;
import com.quiz_app.QuizSpringApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestionByService();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionByCategory(@PathVariable String category) {
        return questionService.getAllQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
}
