package com.improving;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameContext {
    private final ConcurrentHashMap<String, InputOutput> connections = new ConcurrentHashMap<>();
    private final List<MultipleChoiceQuestion> questions = buildQuestions();

    private List<MultipleChoiceQuestion> buildQuestions() {
        var list = new ArrayList<MultipleChoiceQuestion>();
        list.add(new MultipleChoiceQuestion(
                "Who wrote Julius Cesar?",
                "A",
                Map.of(
                        "A", "Shakespeare",
                        "B", "Charles Dickens",
                        "C","William Wordsworth",
                        "D", "J.K Rowling")
        ));
        list.add(new MultipleChoiceQuestion(
                "Who created Star Trek?",
                "C",
                Map.of(
                        "A", "Isaac Asimov",
                        "B", "Robert Heinlein",
                        "C","Gene Rodenberry",
                        "D", "George Lucas")
        ));
        return list;
    }

    public Map<String, InputOutput> getPlayerConnections() {
        return connections;
    }
    public List<MultipleChoiceQuestion> getQuestions() { return questions; }
}
