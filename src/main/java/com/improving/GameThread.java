package com.improving;

public class GameThread extends Thread {
    private final GameContext context;

    public GameThread(GameContext context) {

        this.context = context;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("running the game thread...");
        while(true) {
            try {
                Thread.sleep(5000);
                System.out.println("still running...(local)");
                for (var player : context.getPlayerConnections().keySet()) {
                    var io = context.getPlayerConnections().get(player);
                    io.displayText("still running...(remote)");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
