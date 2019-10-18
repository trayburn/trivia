package com.improving;

import java.util.HashMap;
import java.util.Map;

public class MultipleChoiceQuestion {
    private final String question;
    private final Map<String, String> answers;
    private final String correctAnswerKey;

    public MultipleChoiceQuestion(String question, String correctAnswerKey, Map<String, String> answers) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerKey = correctAnswerKey;
    }

    public boolean isValidAnswer(String input) {
        return answers.keySet().stream().anyMatch(input::equalsIgnoreCase);
    }

    public boolean isCorrectAnswer(String input) {
        return correctAnswerKey.equalsIgnoreCase(input);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append(question + "\r\n");
        answers.keySet().stream().sorted().forEach(key -> {
            sb.append(key + ") " + answers.get(key) + "\r\n");
        });
        return sb.toString();
    }
}
