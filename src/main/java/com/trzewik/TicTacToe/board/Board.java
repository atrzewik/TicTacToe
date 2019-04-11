package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Sign;
import com.trzewik.TicTacToe.displayer.Language;

import java.util.ArrayList;
import java.util.List;

import static com.trzewik.TicTacToe.displayer.Language.oxBundle;

/**
 * @author Agnieszka Trzewik
 */
class Board implements SubjectOfObservation {

    private List<Field> board;
    private int numberOfColumns;
    private Language language;
    private List<Observer> observerList = new ArrayList<>();
    private int currentField;

    Board(BoardCreator boardCreator, int numberOfColumns, Language language) {
        this.board = boardCreator.createBoard();
        this.numberOfColumns = numberOfColumns;
        this.language = language;
    }

    public int getCurrentField() {
        return currentField;
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update(this);
        }
    }

    void markField(int fieldOnBoard, Sign sign) throws FieldAlreadyOccupiedException {
        if (isFieldOfBoardEmpty(fieldOnBoard)) {
            gainField(fieldOnBoard).setSignOfField(sign);
            currentField = fieldOnBoard;
        } else {
            throw new FieldAlreadyOccupiedException(oxBundle(language, "field_is_taken"));
        }
    }

    void addObserver(Observer observer) {
        observerList.add(observer);
    }

    void clearBoard() {
        board.forEach(field -> field.setSignOfField(Sign.EMPTY));
    }

    int countCapacity() {
        return board.size();
    }

    int countColumns() {
        return numberOfColumns;
    }

    Sign gainSignOfField(int fieldOnBoard) {
        return gainField(fieldOnBoard).signOfField();
    }

    private boolean isFieldOfBoardEmpty(int fieldOnBoard) {
        return gainSignOfField(fieldOnBoard).equals(Sign.EMPTY);
    }

    private Field gainField(int fieldOnBoard) {
        return board.get(fieldOnBoard);
    }
}
