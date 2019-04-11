package com.trzewik.TicTacToe.displayer;

/**
 * @author Agnieszka Trzewik
 */
public interface Logger {

    void display(Language language, String message, String... formats);

    void displayError(Language language, String errorMessage, String... formats);
}