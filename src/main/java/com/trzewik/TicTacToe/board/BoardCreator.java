package com.trzewik.TicTacToe.board;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agnieszka Trzewik
 */
class BoardCreator {

    private int numberOfRows;
    private int numberOfColumns;

    BoardCreator(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }


    List<Field> createBoard() {
        int capacity = countBoardCapacity();
        List<Field> board = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            board.add(new Field());
        }
        return board;
    }

    private int countBoardCapacity() {
        return numberOfRows * numberOfColumns;
    }

}
