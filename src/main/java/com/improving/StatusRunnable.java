package com.improving;

import org.springframework.stereotype.Component;

@Component
public class StatusRunnable implements Runnable {
    private final GameContext context;
    private final ConsoleInputOutput io;

    public StatusRunnable(GameContext context, ConsoleInputOutput io) {
        this.context = context;
        this.io = io;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                io.displayText("" + context.getPlayerConnections().size() + " players currently playing.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
