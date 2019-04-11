package com.trzewik.TicTacToe;

import com.trzewik.TicTacToe.board.BoardManager;
import com.trzewik.TicTacToe.displayer.IllegalInterruptedException;
import com.trzewik.TicTacToe.displayer.Language;
import com.trzewik.TicTacToe.displayer.Logger;
import com.trzewik.TicTacToe.displayer.UserInputProvider;
import com.trzewik.TicTacToe.settings.Settings;

import java.util.Scanner;

/**
 * @author Agnieszka Trzewik
 */
public class Game {

    private Players players;
    private Settings settings;
    private Scanner scanner;
    private int numberOfRound;
    private int numberOfRounds = 3;

    Game(Scanner scanner, Settings settings) {
        this.scanner = scanner;
        this.settings = settings;
    }

    void play() {
        Logger logger = settings.getLogger();
        Language language = settings.getLanguage();
        UserInputProvider userInputProvider = UserInputProvider.userInputProvider(scanner, settings.getLogger());
        try {
            settings.changeSettingsIfUserWant(scanner);
            players = Players.buildPlayers(userInputProvider, language);
            BoardManager boardManager = new BoardManager(settings, userInputProvider);


            for (int i = 0; i < numberOfRounds; i++) {
                numberOfRound = i;
                logger.display(language, "round", Integer.toString(i + 1));


                new Round(players, logger, language, boardManager).play();
            }

        } catch (IllegalInterruptedException e) {
            countSurrender();
            logger.display(language, "current_results", players.getPlayer(0).toString(), Integer.toString(players.getPlayer(0).countScore()),
                    players.getPlayer(1).toString(), Integer.toString(players.getPlayer(1).countScore()));
            logger.display(language, "end");
        }
        logger.display(language, "end");
    }

    private void countSurrender() {
        int leftAmountOfPoints = (numberOfRounds + 1 - numberOfRound) * 3;
        players.changeCurrentPlayer();
        players.getCurrentPlayer().addPoints(leftAmountOfPoints);
    }
}