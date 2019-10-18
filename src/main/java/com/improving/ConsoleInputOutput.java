package com.improving;

import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput{
    private Scanner scan = new Scanner(System.in);

    public ConsoleInputOutput(Scanner scan) {
        this.scan = scan;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String readInput() {
        return null;
    }

    @Override
    public void displayText(String text) {

    }

    @Override
    public void displayPrompt(String text) {

    }
}
