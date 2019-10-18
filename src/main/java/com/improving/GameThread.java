package com.improving;

import org.springframework.stereotype.Component;

import java.util.HashMap;

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
        var answers = new HashMap<String, String>();
        io.displayText("running the game thread...");
        while (context.getPlayerConnections().size() == 0) {
        }

        MultipleChoiceQuestion question = context.getQuestions().get(0);

        for (var player : context.getPlayerConnections().keySet()) {
            var nio = context.getPlayerConnections().get(player);
            nio.displayText(question.toString());
        }
        while (answers.size() < context.getPlayerConnections().size()) {
            for (var player : context.getPlayerConnections().keySet()) {
                var nio = context.getPlayerConnections().get(player);
                if (nio.hasInput()) {
                    String i = nio.readInput();

                    if (question.isValidAnswer(i)) {
                        answers.put(player, i);
                    } else {
                        nio.displayText("Please choose A, B, C, or D...");
                    }
                }
            }
        }
        for (var player : context.getPlayerConnections().keySet()) {
            var nio = context.getPlayerConnections().get(player);
            if (question.isCorrectAnswer(answers.get(player))) {
                nio.displayText("Correct ");
                score.put(player, 3);
            } else {
                nio.displayText("Incorrect ");
                score.put(player, 0);
            }
            nio.displayText("You have " + score.get(player).toString() + " points.");
        }
    }

    // score
    // report
    // ... loop to next question
}

