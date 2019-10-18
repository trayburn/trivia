package com.improving;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameThread extends Thread {
    private final GameContext context;
    private final ConsoleInputOutput io;

    public GameThread(GameContext context, ConsoleInputOutput io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void run() {
        super.run();
        var score = new HashMap<String, Integer>();
        io.displayText("running the game thread...");

        waitForPlayers(2);

        for (int x = 0; x < context.getQuestions().size(); x++) {
            var answers = new HashMap<String, String>();
            MultipleChoiceQuestion question = context.getQuestions().get(x);

            displayQuestion(question);

            collectAnswersOrTimeout(answers, question);

            scoreCurrentQuestionAnswers(score, answers, question);

            displayLeaderboard(score);
        }

        sendToAllPlayers("Game Over!");
        var winners = score.keySet().stream()
                .sorted(Comparator.comparingInt(e -> score.get(e)).reversed())
                .filter(e -> score.get(e) == score.values().stream().mapToInt(x -> x).max().orElse(0))
                .collect(Collectors.toList());
        for (var winner : winners) {
            sendToAllPlayers(winner + " has won the game!");
        }
    }

    private void sendToAllPlayers(String s) {
        for (var player : context.getPlayerConnections().keySet()) {
            var nio = context.getPlayerConnections().get(player);
            nio.displayText(s);
        }
    }

    private void displayQuestion(MultipleChoiceQuestion q) {
        sendToAllPlayers(q.toString());
    }

    private void waitForPlayers(int numberOfPlayers) {
        while (context.getPlayerConnections().size() < numberOfPlayers) {
        }
    }

    private void collectAnswersOrTimeout(HashMap<String, String> answers, MultipleChoiceQuestion question) {
        var timeout = new Timeout(30).start();
        var lastTimeWarning = new Date();

        while (timeout.isExpired() == false &&
                answers.size() < context.getPlayerConnections().size()) {
            boolean warnTime = false;
            if (Duration.between(lastTimeWarning.toInstant(), new Date().toInstant()).toMillis() > 5000) {
                warnTime = true;
                lastTimeWarning = new Date();
            }
            for (var player : context.getPlayerConnections().keySet()) {
                var nio = context.getPlayerConnections().get(player);
                if (nio.hasInput()) {
                    String i = nio.readInput();

                    if (question.isValidAnswer(i)) {
                        answers.put(player, i);
                    } else {
                        nio.displayText("Please choose a valid option.");
                    }
                }
                if (warnTime) {
                    if (answers.containsKey(player)) {
                        nio.displayText("Waiting on other players, " + timeout.toString() + " remaining.");
                    } else {
                        nio.displayText(timeout.toString() + " remaining");
                    }
                }
            }
        }
    }

    private void scoreCurrentQuestionAnswers(HashMap<String, Integer> score, HashMap<String, String> answers, MultipleChoiceQuestion question) {
        for (var player : context.getPlayerConnections().keySet()) {
            if (score.containsKey(player) == false) score.put(player, 0);
            var nio = context.getPlayerConnections().get(player);
            if (question.isCorrectAnswer(answers.get(player))) {
                nio.displayText("\r\nCorrect\r\n");

                score.put(player, score.get(player) + 3);
            } else {
                nio.displayText("\r\nIncorrect \r\n");
            }
        }
    }

    private void displayLeaderboard(HashMap<String, Integer> score) {
        var sb = new StringBuilder();
        for (var player : context.getPlayerConnections().keySet()) {
            sb.append("" + player + "\t\t" + score.get(player) + "\r\n");
        }
        sendToAllPlayers(sb.toString());
    }

}

