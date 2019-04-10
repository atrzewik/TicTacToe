package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Player;
import com.trzewik.TicTacToe.Sign;

/**
 * @author Agnieszka Trzewik
 */
class WinningConditionsChecker {

    private Board board;
    private int boardCapacity;
    private Sign playerSign;
    private int lastPlayerMove;
    private int numberOfColumns;
    private int winningCondition;

    WinningConditionsChecker(Board board, Player player, int winningCondition) {
        this.board = board;
        this.boardCapacity = board.countCapacity();
        this.playerSign = player.playerSign();
        this.lastPlayerMove = player.getLastMove();
        this.numberOfColumns = board.countColumns();
        this.winningCondition = winningCondition;
    }

    boolean checkDiagonalUpCondition() {
        int frequency = 1;
        if (isNotOnMostRightColumn()) {
            frequency = diagonalDownwards(frequency, 0, false, numberOfColumns - 1);
        }
        if (isNotOnMostLeftColumn()) {
            frequency = diagonalUpwards(frequency, boardCapacity, true, numberOfColumns - 1);
        }
        return didWeJustWin(frequency, winningCondition);
    }

    boolean checkDiagonalCondition() {
        int frequency = 1;
        if (isNotOnMostLeftColumn()) {
            frequency = diagonalDownwards(frequency, 0, false, numberOfColumns + 1);
        }
        if (isNotOnMostRightColumn()) {
            frequency = diagonalUpwards(frequency, boardCapacity, true, numberOfColumns + 1);
        }
        return didWeJustWin(frequency, winningCondition);
    }

    private boolean isNotOnMostRightColumn() {
        return lastPlayerMove + 1 % numberOfColumns != 0;
    }

    private boolean isNotOnMostLeftColumn() {
        return lastPlayerMove % numberOfColumns != 0;
    }

    boolean checkVerticalCondition() {
        int frequency = 1;
        int numberOfRows = boardCapacity / numberOfColumns;
        if (isWinningConditionLessThanNumberOfCoordinate(numberOfRows, winningCondition)) {
            frequency = nonDiagonalLengthCheck(frequency, true, 0, false, numberOfColumns);
            frequency = nonDiagonalLengthCheck(frequency, false, boardCapacity, true, numberOfColumns);
        }
        return didWeJustWin(frequency, winningCondition);
    }

    boolean checkHorizontalCondition() {
        int frequency = 1;
        int minimumNumber = (lastPlayerMove / numberOfColumns) * numberOfColumns;
        int maximumNumber = minimumNumber + numberOfColumns;
        frequency = nonDiagonalLengthCheck(frequency, true, minimumNumber, false, 1);
        frequency = nonDiagonalLengthCheck(frequency, false, maximumNumber, true, 1);
        return didWeJustWin(frequency, winningCondition);

    }

    private boolean didWeJustWin(int frequency, int winningCondition) {
        return frequency >= winningCondition;
    }

    private int nonDiagonalLengthCheck(int frequency, boolean goDown, int number, boolean goForward, int valueToChangeField) {
        int fieldToCheck = nextInSequence(goForward, lastPlayerMove, valueToChangeField);

        while (goDown == isSeqFieldBiggerOrEqualToNumber(number, fieldToCheck)) {
            if (!board.gainSignOfField(fieldToCheck).equals(playerSign)) break;
            frequency++;
            fieldToCheck = nextInSequence(goForward, fieldToCheck, valueToChangeField);
        }
        return frequency;
    }

    private int diagonalDownwards(int frequency, int number, boolean goForward, int valueToChangeField) {
        int fieldToCheck = nextInSequence(goForward, lastPlayerMove, valueToChangeField);

        while (isSeqFieldBiggerOrEqualToNumber(number, fieldToCheck)) {
            if (!board.gainSignOfField(fieldToCheck).equals(playerSign)) break;

            if (((fieldToCheck + 1) % numberOfColumns == 0)) {
                frequency++;
                break;
            } else {
                frequency++;
                fieldToCheck = nextInSequence(goForward, fieldToCheck, valueToChangeField);
            }
        }
        return frequency;
    }

    private int diagonalUpwards(int frequency, int number, boolean goForward, int nextInSeq) {
        int fieldToCheck = nextInSequence(goForward, lastPlayerMove, nextInSeq);

        while (!isSeqFieldBiggerOrEqualToNumber(number, fieldToCheck)) {
            if (!board.gainSignOfField(fieldToCheck).equals(playerSign)) break;
            if ((fieldToCheck % numberOfColumns == 0)) {
                frequency++;
                break;
            } else {
                frequency++;
                fieldToCheck = nextInSequence(goForward, fieldToCheck, nextInSeq);
            }

        }
        return frequency;
    }

    private boolean isSeqFieldBiggerOrEqualToNumber(int number, int fieldToCheck) {
        return fieldToCheck >= number;
    }

    private int nextInSequence(boolean goForward, int fieldToChange, int valueToChangeField) {
        return goForward ? fieldToChange + valueToChangeField : fieldToChange - valueToChangeField;
    }

    private boolean isWinningConditionLessThanNumberOfCoordinate(int numberOfCoordinates, int winningCondition) {
        return winningCondition <= numberOfCoordinates;
    }
}