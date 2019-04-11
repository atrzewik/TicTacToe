package com.trzewik.TicTacToe;

import com.trzewik.TicTacToe.displayer.ConsoleLogger;
import com.trzewik.TicTacToe.displayer.IllegalInterruptedException;
import com.trzewik.TicTacToe.displayer.Language;
import com.trzewik.TicTacToe.displayer.UserInputProvider;
import org.testng.annotations.Test;

import java.util.Scanner;


/**
 * @author Agnieszka Trzewik
 */
public class PlayersTest {

    @Test
    public void checkIfStartingPlayerIsO() throws IllegalInterruptedException {
        Players players = Players.buildPlayers(UserInputProvider.userInputProvider(new Scanner("O\nX\n"), new ConsoleLogger()), Language.POLISH);
        assert players.getCurrentPlayer().equals(new PlayerO("O"));
    }

    @Test
    public void checkIfAfterChangeCurrentPlayerIsX() throws IllegalInterruptedException {
        Players players = Players.buildPlayers(UserInputProvider.userInputProvider(new Scanner("O\nX\n"), new ConsoleLogger()), Language.POLISH);
        players.changeCurrentPlayer();
        assert players.getCurrentPlayer().equals(new PlayerX("X"));
    }

    @Test(expectedExceptions = IllegalInterruptedException.class)
    public void checkIfAfterQIsThrowException() throws IllegalInterruptedException {
        Players.buildPlayers(UserInputProvider.userInputProvider(new Scanner("q\nX\n"), new ConsoleLogger()), Language.POLISH);
    }
}