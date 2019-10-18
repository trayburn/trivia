package com.improving;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class NetworkInputOutput implements InputOutput {
    private final Socket socket;
    private final Scanner scanner;

    public NetworkInputOutput(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
    }

    public String getName() {
        return socket.getInetAddress().toString();
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void displayText(String text) {
        try {
            socket.getOutputStream().write((text + "\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayPrompt(String text) {
        try {
            socket.getOutputStream().write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
