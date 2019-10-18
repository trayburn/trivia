package com.improving;

import org.springframework.stereotype.Component;

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
        io.displayText("running the game thread...");
        while(true) {
            try {
                Thread.sleep(5000);
                io.displayText("still running...(local)");
                for (var player : context.getPlayerConnections().keySet()) {
                    var nio = context.getPlayerConnections().get(player);
                    nio.displayText("still running...(remote)");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
