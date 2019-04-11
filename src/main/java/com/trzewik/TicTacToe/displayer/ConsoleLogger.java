package com.trzewik.TicTacToe.displayer;

import java.util.MissingFormatArgumentException;

import static com.trzewik.TicTacToe.displayer.Language.oxBundle;

/**
 * @author Agnieszka Trzewik
 */
public class ConsoleLogger implements Logger {

    @Override
    public void display(Language language, String message, String... formats) {
        try {
            System.out.print(String.format(oxBundle(language, message), formats));
        } catch (MissingFormatArgumentException ex) {
            throw new MissingFormatArgumentException("missing_arg_excp");
        }
    }

    @Override
    public void displayError(Language language, String errorMessage, String... formats) {
        try {
            System.err.println(String.format(oxBundle(language, errorMessage), formats));
        } catch (MissingFormatArgumentException ex) {
            throw new MissingFormatArgumentException(oxBundle(language, "missing_arg_excp"));
        }
    }
}