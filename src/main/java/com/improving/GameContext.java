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
        list.add(new MultipleChoiceQuestion(
                "At what temperature does water boil?",
                "B",
                Map.of(
                        "A", "32 F/0 C",
                        "B", "212 F/100 C",
                        "C","491 F/255 C",
                        "D", "-42 F/-41.1 C")
        ));
        list.add(new MultipleChoiceQuestion(
                "How many colonies did the US start with?",
                "D",
                Map.of(
                        "A", "8",
                        "B", "11",
                        "C","33",
                        "D", "13")
        ));
        list.add(new MultipleChoiceQuestion(
                "Where is the smallest bone in the body?",
                "B",
                Map.of(
                        "A", "Finger",
                        "B", "Ear",
                        "C","Toe",
                        "D", "Nose")
        ));
        list.add(new MultipleChoiceQuestion(
                "What is the only mammal that can't jump?",
                "A",
                Map.of(
                        "A", "Elephant",
                        "B", "Bear",
                        "C","Rodents",
                        "D", "Horses")
        ));
        list.add(new MultipleChoiceQuestion(
                "What does the roman numeral C represent?",
                "A",
                Map.of(
                        "A", "100",
                        "B", "400",
                        "C","500",
                        "D", "900")
        ));
        list.add(new MultipleChoiceQuestion(
                "Who painted the Mona Lisa?",
                "C",
                Map.of(
                        "A", "Vincent Van Gogh",
                        "B", "Pablo Picasso",
                        "C","Leonardo Da Vinci",
                        "D", "Claude Monet")
        ));
        list.add(new MultipleChoiceQuestion(
                "How many dots are there on two dice?",
                "D",
                Map.of(
                        "A", "32",
                        "B", "40",
                        "C","54",
                        "D", "42")
        ));
        list.add(new MultipleChoiceQuestion(
                "Who created E = mc^2?",
                "B",
                Map.of(
                        "A", "Issac Newton",
                        "B", "Albert Einstein",
                        "C","David Hilbert",
                        "D", "Euclid")
        ));
        list.add(new MultipleChoiceQuestion(
                "Name of the famous detective who smoked a pipe & Played the violin",
                "A",
                Map.of(
                        "A", "Sherlock Holmes",
                        "B", "Philip Marlowe",
                        "C","Inspector Gadget",
                        "D", "Scooby Doo")
        ));
        list.add(new MultipleChoiceQuestion(
                "What language has the most words",
                "B",
                Map.of(
                        "A", "Chinese",
                        "B", "English",
                        "C","German",
                        "D", "Russian")
        ));
        list.add(new MultipleChoiceQuestion(
                "Where is Marco Polo's home town?",
                "B",
                Map.of(
                        "A", "Rome",
                        "B", "Venice",
                        "C","Florence",
                        "D", "Nobody knows")
        ));list.add(new MultipleChoiceQuestion(
                "Which of the following is Elvis Presley's most popular song?",
                "D",
                Map.of(
                        "A", "Crying In The Chapel",
                        "B", "Return To Sender",
                        "C","A Little Less Conversation",
                        "D", "Heartbreak Hotel")
        ));
        list.add(new MultipleChoiceQuestion(
                "How many events are in a decathlon?",
                "A",
                Map.of(
                        "A", "10",
                        "B", "5",
                        "C","3",
                        "D", "12")
        ));
        list.add(new MultipleChoiceQuestion(
                "How many squares are there on the chess board",
                "B",
                Map.of(
                        "A", "35",
                        "B", "64",
                        "C","50",
                        "D", "25")
        ));


        return list;
    }

    public Map<String, InputOutput> getPlayerConnections() {
        return connections;
    }
    public List<MultipleChoiceQuestion> getQuestions() { return questions; }
}
