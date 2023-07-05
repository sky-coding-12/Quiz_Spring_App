package com.quiz_app.QuizSpringApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany
    private List<Question> questionList;
}
