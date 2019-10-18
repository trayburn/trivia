package com.improving;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInputOutput implements InputOutput{
    private Scanner scanner = new Scanner(System.in);

    public ConsoleInputOutput() {
    }

    public boolean hasInput() { return scanner.hasNextLine(); }

    @Override
    public String getName() {
        return "The Local Console";
    }

    @Override
    public String readInput() {
        return scanner.nextLine();
    }

    @Override
    public void displayText(String text) {
        System.out.println(text);
    }

    @Override
    public void displayPrompt(String text) {
        System.out.print(text);
    }
}
