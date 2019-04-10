package com.trzewik.TicTacToe;


import java.util.List;

/**
 * @author Agnieszka Trzewik
 */
class BoardPrinter {

    private List<Field> board;

    void updateBoard(List<Field> board){
        this.board = board;
    }

    void printBoard(int numberOfColumns) {
        for (int i = 0; i < board.size(); i++) {
            Sign signOfField = board.get(i).signOfField();
            String signToPrint = signOfField.equals(Sign.EMPTY) ? Integer.toString(i) : signOfField.name();
            if (i % (numberOfColumns) == numberOfColumns - 1) {
                MessagePrinter.printMessageInNewLine("%-10s", signToPrint);
            } else {
                MessagePrinter.printMessage("%-10s",signToPrint);
            }
        }
    }
}