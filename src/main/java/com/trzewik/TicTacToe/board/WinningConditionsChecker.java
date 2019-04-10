package com.trzewik.TicTacToe;

import com.trzewik.TicTacToe.board.Board;

/**
 * @author Agnieszka Trzewik
 */
class WinningConditionsChecker {

    private Board board;
    private int boardCapacity;
    private int frequency;
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
        frequency = 1;
        if (!(lastPlayerMove + 1 % numberOfColumns == 0)) {
            frequencyOfSign(true, true, 0, false, playerSign, numberOfColumns - 1);
        }
        if (!(lastPlayerMove % numberOfColumns == 0)) {
            frequencyOfSign(true, false, boardCapacity, true, playerSign, numberOfColumns - 1);
        }
        return frequency >= winningCondition;
    }

    boolean checkDiagonalCondition() {
        frequency = 1;
        if (!(lastPlayerMove % numberOfColumns == 0)) {
            frequencyOfSign(true, true, 0, false, playerSign, numberOfColumns + 1);
        }
        if (!(lastPlayerMove + 1 % numberOfColumns == 0)) {
            frequencyOfSign(true, false, boardCapacity, true, playerSign, numberOfColumns + 1);
        }
        return frequency >= winningCondition;
    }

    boolean checkVerticalCondition() {
        frequency = 1;
        int numberOfRows = boardCapacity / numberOfColumns;
        if (isWinningConditionLessThanNumberOfCoordinate(numberOfRows, winningCondition)) {
            frequencyOfSign(false, true, 0, false, playerSign, numberOfColumns);
            frequencyOfSign(false, false, boardCapacity, true, playerSign, numberOfColumns);
        }
        return frequency >= winningCondition;
    }

    boolean checkHorizontalCondition() {
        frequency = 1;
        int minimumNumber = (lastPlayerMove / numberOfColumns) * numberOfColumns;
        int maximumNumber = minimumNumber + numberOfColumns;
        frequencyOfSign(false, true, minimumNumber, false, playerSign, 1);
        frequencyOfSign(false, false, maximumNumber, true, playerSign, 1);
        return frequency >= winningCondition;

    }
    private void frequencyOfSign(boolean isDiagonal, boolean fieldIsBiggerOrEqualToNumber, int number, boolean shouldAddNumberToChange, Sign sign, int valueToChangeField) {

        int fieldToCheck = changeField(shouldAddNumberToChange, lastPlayerMove, valueToChangeField);

        while (fieldIsBiggerOrEqualToNumber == (fieldToCheck >= number)) {
            if (board.gainSignOfField(fieldToCheck).equals(sign)) {
                if (isDiagonal) {
                    if (fieldIsBiggerOrEqualToNumber) {
                        if (((fieldToCheck + 1) % numberOfColumns == 0)) {
                            frequency++;
                            break;
                        } else {
                            frequency++;
                            fieldToCheck = changeField(shouldAddNumberToChange, fieldToCheck, valueToChangeField);
                        }
                    }
                    if (!fieldIsBiggerOrEqualToNumber) {
                        if ((fieldToCheck % numberOfColumns == 0)) {
                            frequency++;
                            break;
                        } else {
                            frequency++;
                            fieldToCheck = changeField(shouldAddNumberToChange, fieldToCheck, valueToChangeField);
                        }
                    }
                } else {
                    frequency++;
                    fieldToCheck = changeField(shouldAddNumberToChange, fieldToCheck, valueToChangeField);
                }
            } else {
                break;
            }
        }
    }

    private int changeField(boolean shouldAddNumberToChange, int fieldToChange, int valueToChangeField) {
        return shouldAddNumberToChange ? fieldToChange + valueToChangeField : fieldToChange - valueToChangeField;
    }

    private boolean isWinningConditionLessThanNumberOfCoordinate(int numberOfCoordinates, int winningCondition) {
        return winningCondition <= numberOfCoordinates;
    }
}
