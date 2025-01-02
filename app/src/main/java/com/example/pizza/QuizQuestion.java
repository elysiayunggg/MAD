package com.example.pizza;

import java.io.Serializable;
import java.util.List;

public class QuizQuestion implements Serializable {
    private String question;
    private List<String> options; // List of answer options
    private String correctAnswer; // The correct answer

    public QuizQuestion(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Getters and Setters...

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}