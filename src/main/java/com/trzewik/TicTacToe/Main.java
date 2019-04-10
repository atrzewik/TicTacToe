package com.trzewik.TicTacToe;

import com.trzewik.TicTacToe.displayer.ConsoleLogger;
import com.trzewik.TicTacToe.settings.Settings;

import java.util.Scanner;

/**
 * @author Agnieszka Trzewik
 */
public class Main {

    public static void main(String[] args) {
        new Game(new Scanner(System.in), new Settings(new ConsoleLogger())).play();
    }
}