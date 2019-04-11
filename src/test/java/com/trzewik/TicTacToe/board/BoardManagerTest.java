package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Player;
import com.trzewik.TicTacToe.PlayerO;
import com.trzewik.TicTacToe.displayer.ConsoleLogger;
import com.trzewik.TicTacToe.displayer.IllegalInterruptedException;
import com.trzewik.TicTacToe.displayer.Logger;
import com.trzewik.TicTacToe.displayer.UserInputProvider;
import com.trzewik.TicTacToe.settings.Settings;
import org.testng.annotations.Test;

import java.util.Scanner;

/**
 * @author Agnieszka Trzewik
 */

@Test
public class BoardManagerTest {

    @Test
    public void checkIfPlayerWin() throws IllegalInterruptedException {
        Logger logger = new ConsoleLogger();
        BoardManager boardManager = new BoardManager(new Settings(logger), UserInputProvider.userInputProvider(new Scanner("0\n4\n8\n"), logger));
        Player player = new PlayerO("O");
        boardManager.doAMove(player);
        boardManager.doAMove(player);
        boardManager.doAMove(player);
        assert boardManager.gameHasEnded();
    }

    @Test
    public void checkIfPlayerNotWin() throws IllegalInterruptedException {
        Logger logger = new ConsoleLogger();
        BoardManager boardManager = new BoardManager(new Settings(logger), UserInputProvider.userInputProvider(new Scanner("0\n5\n8\n"), logger));
        Player player = new PlayerO("O");
        boardManager.doAMove(player);
        boardManager.doAMove(player);
        boardManager.doAMove(player);
        assert !boardManager.gameHasEnded();
    }

    @Test(expectedExceptions = IllegalInterruptedException.class)
    public void checkIfQThrowsException() throws IllegalInterruptedException {
        Logger logger = new ConsoleLogger();
        BoardManager boardManager = new BoardManager(new Settings(logger), UserInputProvider.userInputProvider(new Scanner("O\nq\n8\n"), logger));
        Player player = new PlayerO("O");
        boardManager.doAMove(player);
    }

}