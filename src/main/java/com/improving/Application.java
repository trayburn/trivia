package com.improving;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);

        Game game =context.getBean(Game.class);
        game.run();
    }
}
