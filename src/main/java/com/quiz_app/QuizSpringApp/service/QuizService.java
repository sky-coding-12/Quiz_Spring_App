package com.quiz_app.QuizSpringApp.service;

import com.quiz_app.QuizSpringApp.model.Question;
import com.quiz_app.QuizSpringApp.model.QuestionWrapper;
import com.quiz_app.QuizSpringApp.model.Quiz;
import com.quiz_app.QuizSpringApp.model.Response;
import com.quiz_app.QuizSpringApp.repository.QuestionRepo;
import com.quiz_app.QuizSpringApp.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numOfQue, String title) {
        List<Question> questionList = questionRepo.findRandomQuestionsByCategory(category, numOfQue);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questionList);
        quizRepo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(long id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        if (quiz.isPresent()) {
            List<Question> questionListFromDB = quiz.get().getQuestionList();
            List<QuestionWrapper> questionListForUser = new ArrayList<>();
            for (Question q : questionListFromDB) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getDifficultyLevel());
                questionListForUser.add(qw);
            }
            try {
                return new ResponseEntity<>(questionListForUser, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        if (quiz.isPresent()) {
            List<Question> questionList = quiz.get().getQuestionList();
            int correct = 0, idx = 0;
            for (Response response : responses) {
                if (response.getResponse().equals(questionList.get(idx).getRightAnswer())) correct++;
                idx++;
            }
            try {
                return new ResponseEntity<>(correct, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return new ResponseEntity<>(-1, HttpStatus.OK);
        }
        return new ResponseEntity<>(-1, HttpStatus.OK);
    }
}
