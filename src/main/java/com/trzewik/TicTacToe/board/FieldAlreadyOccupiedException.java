package com.trzewik.TicTacToe;

/**
 * @author Agnieszka Trzewik
 */
class FieldAlreadyOccupiedException extends Exception {
    FieldAlreadyOccupiedException(String message) {
        super(message);
    }
}
