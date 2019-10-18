package com.improving;

import java.io.IOException;
import java.net.ServerSocket;

public class Application {
    private final GameContext context;
    private final GameThread thread;

    public Application(GameContext context, GameThread thread) {
        this.context = context;
        this.thread = thread;
    }

    public static void main(String[] args) {
        var context = new GameContext();
        var gThread = new GameThread(context);
        var g = new Application(context, gThread);
        g.run();
    }

    public void run() {
        thread.start();

        try (var server = new ServerSocket(1701)) {
            var serverIP = server.getInetAddress().toString();
            System.out.println("Running at " + serverIP + " port 1701...");
            while (true) {
                var socket = server.accept();
                var ipAddress = socket.getInetAddress().toString();
                System.out.println("Connection recevied from " + ipAddress);
                context.getPlayerConnections()
                        .put(ipAddress, new NetworkInputOutput(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
