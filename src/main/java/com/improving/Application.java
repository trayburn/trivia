package com.improving;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Application {
    private final GameContext context;
    private final GameThread thread;

    public Application(GameContext context, GameThread thread) {
        this.context = context;
        this.thread = thread;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);
        var game = context.getBean(Application.class);
        game.run();
    }


    public void run() {
        thread.start();

        try (var server = new ServerSocket(1701, 0, Inet4Address.getLocalHost())) {
            var serverIP = server.getInetAddress().getHostAddress();
            System.out.println("Running at " + serverIP + " port 1701...");
            while (true) {
                var socket = server.accept();
                new Thread(new NewPlayerRunnable(context, socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
