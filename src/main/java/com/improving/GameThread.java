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
        for (var player : context.getPlayerConnections().keySet()) {
            var nio = context.getPlayerConnections().get(player);
            nio.displayText("Who wrote Julius Cesar?");
            nio.displayText("A. Shakespear");
            nio.displayText("B. Charles Dickens");
            nio.displayText("C. William Woodsworth");
            nio.displayText("D. J.K Rawling");
        }
        while (answers.size() < context.getPlayerConnections().size()) {
            for (var player : context.getPlayerConnections().keySet()) {
                var nio = context.getPlayerConnections().get(player);
                if (nio.hasInput()) {
                    String i = nio.readInput();

                    if (i.equalsIgnoreCase("A")) {
                        answers.put(player, i);
                    } else if (i.equalsIgnoreCase("B")) {
                        answers.put(player, i);
                    } else if (i.equalsIgnoreCase("C")) {
                        answers.put(player, i);
                    } else if (i.equalsIgnoreCase("D")) {
                        answers.put(player, i);
                    } else {
                        nio.displayText("Please choose A, B, C, or D...");
                    }
                }
                if (answers.containsValue("A")) {
                    nio.displayText("Correct ");
                    score.put(player, 3);
                    nio.displayText("You got " + score.get(player).toString()+ " points! ");
                } else {
                        nio.displayText("Incorrect ");
                        score.put(player, 0);
                        }
                }
            }
        }

        // score
        // report
        // ... loop to next question
    }

