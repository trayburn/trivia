package com.improving;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class GameThread extends Thread {
    private final GameContext context;
    private final ConsoleInputOutput io;

    public GameThread(GameContext context, ConsoleInputOutput io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void run() {
        super.run();
        var score = new HashMap<String, Integer>();
        io.displayText("running the game thread...");

        while (context.getPlayerConnections().size() == 0) {
        }
//------------------------------------------------------------------------------

        for (int x = 0; x < context.getQuestions().size(); x++) {
            var answers = new HashMap<String, String>();
            MultipleChoiceQuestion question = context.getQuestions().get(x);

            for (var player : context.getPlayerConnections().keySet()) {
                var nio = context.getPlayerConnections().get(player);
                nio.displayText(question.toString());
            }

            var timeout = new Timeout(30).start();
            var lastTimeWarning = new Date();

            while (timeout.isExpired() == false &&
                    answers.size() < context.getPlayerConnections().size()) {
                boolean warnTime = false;
                if (Duration.between(lastTimeWarning.toInstant(), new Date().toInstant()).toMillis() > 5000) {
                    warnTime = true;
                    lastTimeWarning = new Date();
                }

                while (answers.size() < context.getPlayerConnections().size()) {
                    for (var player : context.getPlayerConnections().keySet()) {
                        var nio = context.getPlayerConnections().get(player);
                        if (nio.hasInput()) {
                            String i = nio.readInput();

                            if (question.isValidAnswer(i)) {
                                answers.put(player, i);
                            } else {
                                nio.displayText("Please choose a valid option.");
                            }
                        }
                        if (warnTime) {
                            nio.displayText(timeout.toString());
                        }
                    }
                }
                for (var player : context.getPlayerConnections().keySet()) {
                    var nio = context.getPlayerConnections().get(player);
                    if (question.isCorrectAnswer(answers.get(player))) {
                        nio.displayText("Correct ");

                        score.put(player, 3);
                    } else {
                        nio.displayText("Incorrect ");
                        score.put(player, 0);
                    }
                    nio.displayText("You have " + score.get(player).toString() + " points. \r\n");
                }
            }


        }
    }

}

