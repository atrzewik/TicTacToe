package com.trzewik.TicTacToe.board;

/**
 * @author Agnieszka Trzewik
 */
class FieldAlreadyOccupiedException extends Throwable {
    FieldAlreadyOccupiedException(String message) {
        super(message);
    }
}
