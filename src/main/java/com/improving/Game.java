package com.improving;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Game {


    public void run() {
        Scanner scan = new Scanner(System.in);

        while (true){
            System.out.print("> ");
            var input = scan.nextLine();
            System.out.println(input);
        }


    }
}
