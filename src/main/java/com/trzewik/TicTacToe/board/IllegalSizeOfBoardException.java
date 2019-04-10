package com.trzewik.TicTacToe;

/**
 * @author Agnieszka Trzewik
 */
public class IllegalSizeOfBoardException extends Throwable {
    public IllegalSizeOfBoardException(String message) {
        super(message);
    }
}
