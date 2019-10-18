package com.improving;

import java.io.IOException;
import java.net.Socket;

public class NewPlayerRunnable implements Runnable {
    public final GameContext context;
    public final Socket socket;

    public NewPlayerRunnable(GameContext context, Socket socket) {
        this.context = context;
        this.socket = socket;
    }

    @Override
    public void run() {
        var ipAddress = socket.getInetAddress().toString();
        System.out.println("Connection received from " + ipAddress);
        try {
            var networkIO = new NetworkInputOutput(socket);
            printBanner(networkIO);
            var name = printWelcome(networkIO);
            context.getPlayerConnections()
                    .put(name, networkIO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String printWelcome(InputOutput io) {
        io.displayText("");
        io.displayText("Welcome to the Improving Trivia Game!");
        io.displayText("You are about to join a game played by multiple players");
        io.displayText("that tests your knowledge of trivia.");
        io.displayText("");
        io.displayPrompt("What should we call you? ");
        var input = io.readInput();
        while (input.trim().length() == 0) {
            io.displayText("I'm sorry I didn't get that...");
            io.displayPrompt("What should we call you?");
            input = io.readInput();
        }
        io.displayText("");
        io.displayText("Got it!  Welcome to the game " + input);
        io.displayText("");
        return input;
    }

    public void printBanner(InputOutput io) {
        io.displayText("  _____                                _                ");
        io.displayText("  \\_   \\_ __ ___  _ __  _ __ _____   _(_)_ __   __ _    ");
        io.displayText("   / /\\/ '_ ` _ \\| '_ \\| '__/ _ \\ \\ / / | '_ \\ / _` |   ");
        io.displayText("/\\/ /_ | | | | | | |_) | | | (_) \\ V /| | | | | (_| |   ");
        io.displayText("\\____/ |_| |_| |_| .__/|_|  \\___/ \\_/ |_|_| |_|\\__, |   ");
        io.displayText("                 |_|                           |___/    ");
        io.displayText(" ____      _       _           ___                     ");
        io.displayText("/__  \\_ __(_)_   _(_) __ _    / _ \\__ _ _ __ ___   ___ ");
        io.displayText(" / /\\/ '__| \\ \\ / / |/ _` |  / /_\\/ _` | '_ ` _ \\ / _ \\");
        io.displayText("/ /  | |  | |\\ V /| | (_| | / /_\\\\ (_| | | | | | |  __/");
        io.displayText("\\/   |_|  |_| \\_/ |_|\\__,_| \\____/\\__,_|_| |_| |_|\\___|");
    }
}
