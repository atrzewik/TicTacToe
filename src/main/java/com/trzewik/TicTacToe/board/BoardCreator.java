package com.trzewik.TicTacToe;

import com.trzewik.OX.inputProvider.BundleProvider;
import com.trzewik.OX.inputProvider.IllegalInterruptedException;
import com.trzewik.OX.settings.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agnieszka Trzewik
 */
class BoardCreator {

    private final int numberOfRows;
    private final int numberOfColumns;

    BoardCreator(int numberOfColumns, int numberOfRows) throws IllegalSizeOfBoardException, IllegalInterruptedException {
        this.numberOfColumns = assignValueOfBoardSize(numberOfColumns);
        this.numberOfRows = assignValueOfBoardSize(numberOfRows);
    }

    List<Field> createBoard() {
        int capacity = countBoardCapacity();
        List<Field> board = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            board.add(new Field());
        }
        return board;
    }

    int countNumberOfColumns(){
        return numberOfColumns;
    }

    private int countBoardCapacity() {
        return numberOfRows * numberOfColumns;
    }

    private int assignValueOfBoardSize(int valueOfBoardSize) throws IllegalSizeOfBoardException, IllegalInterruptedException {
        if (valueOfBoardSize >= 3 && valueOfBoardSize <= 100) {
            return valueOfBoardSize;
        } else {
            throw new IllegalSizeOfBoardException(BundleProvider.oxBundle(Settings.getInstance().getLanguage(), "wrong_board_size"));
        }
    }
}
