package com.trzewik.TicTacToe;


import com.trzewik.OX.inputProvider.BundleProvider;
import com.trzewik.OX.inputProvider.IllegalInterruptedException;
import com.trzewik.OX.settings.Settings;

import java.util.List;

/**
 * @author Agnieszka Trzewik
 */
class Board {

    private List<Field> board;
    private int numberOfColumns;

    Board(BoardCreator boardCreator) {
        this.board = boardCreator.createBoard();
        this.numberOfColumns = boardCreator.countNumberOfColumns();
    }

    void markField(int fieldOnBoard, Sign sign) throws FieldAlreadyOccupiedException, IllegalInterruptedException {
        if (isFieldOfBoardEmpty(fieldOnBoard)) {
            gainField(fieldOnBoard).setSignOfField(sign);
        } else {
            throw new FieldAlreadyOccupiedException(BundleProvider.oxBundle(Settings.getInstance().getLanguage(), "field_is_taken"));
        }
    }

    void updatePrinter(BoardPrinter boardPrinter){
        boardPrinter.updateBoard(board);
    }

    void updateArbiter(Arbiter arbiter){arbiter.updateBoard(this);}

    int countCapacity(){
        return board.size();
    }

    int countColumns(){
        return numberOfColumns;
    }

    Sign gainSignOfField(int fieldOnBoard) {
        return gainField(fieldOnBoard).signOfField();
    }

    private Field gainField(int fieldOnBoard) {
        return board.get(fieldOnBoard);
    }

    private boolean isFieldOfBoardEmpty(int fieldOnBoard) {
        return gainSignOfField(fieldOnBoard).equals(Sign.EMPTY);
    }
}
