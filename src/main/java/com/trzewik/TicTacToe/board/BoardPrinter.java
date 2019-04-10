package com.trzewik.TicTacToe.board;


import com.trzewik.TicTacToe.Sign;
import com.trzewik.TicTacToe.displayer.Language;
import com.trzewik.TicTacToe.displayer.Logger;


/**
 * @author Agnieszka Trzewik
 */
class BoardPrinter implements Observer {

    private Logger logger;
    private Language language;
    private int numberOfColumns;
    private Board board;

    BoardPrinter(Logger logger, Language language, int numberOfColumns) {
        this.logger = logger;
        this.numberOfColumns = numberOfColumns;
        this.language = language;
    }

    void printBoard() {
        for (int i = 0; i < board.countCapacity(); i++) {
            Sign signOfField = board.gainSignOfField(i);
            String signToPrint = signOfField.equals(Sign.EMPTY) ? Integer.toString(i) : signOfField.name();
            if (i % (numberOfColumns) == numberOfColumns - 1) {
                logger.display(language, "n%-10s", signToPrint);
            } else {
                logger.display(language, "%-10s", signToPrint);
            }
        }
    }

    @Override
    public void update(Board board) {
        this.board = board;
    }
}