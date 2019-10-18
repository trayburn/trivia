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

//                    if (i.isValid) {
//                        answers.put(player, i);
//                    } else {
                    nio.displayText("Please choose A, B, C, or D...");
//                    }
                }
            }
        }
    }
}
