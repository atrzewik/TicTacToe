package com.trzewik.TicTacToe;

import com.trzewik.TicTacToe.displayer.ConsoleLogger;
import com.trzewik.TicTacToe.settings.Settings;
import org.testng.annotations.Test;

import java.util.Scanner;



/**
 * @author Agnieszka Trzewik
 */
public class GameTest {

    @Test
    public void checkIfGameCanBePassedAndQuitWith(){
        new Game(new Scanner("y\n5\n4\n3\npolish\nO\nX\n0\n10\n6\n14\n12\nq"),new Settings(new ConsoleLogger())).play();
    }
}