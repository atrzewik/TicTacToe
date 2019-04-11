package com.trzewik.TicTacToe.board;

import java.util.Iterator;

/**
 * @author Agnieszka Trzewik
 */
public class FieldsChecker implements Iterable<Integer> {

    private int currentField;
    private int numberOfColumns;
    private DirectionToGo directionToGo;
    private int boardCapacity;
    private int winningCondition;

    FieldsChecker(int currentField, int numberOfColumns, int boardCapacity, int winningCondition, DirectionToGo directionToGo) {
        this.currentField = currentField;
        this.numberOfColumns = numberOfColumns;
        this.directionToGo = directionToGo;
        this.boardCapacity = boardCapacity;
        this.winningCondition = winningCondition;
    }

    public void updateCurrentFieldAndDirection(int currentFieldToSet, DirectionToGo directionToUpdate) {
        currentField = currentFieldToSet;
        directionToGo = directionToUpdate;
    }

    @Override
    public Iterator<Integer> iterator() {

        Iterator<Integer> horizontalForward = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                int minimumNumberOfFieldForHorizontalCheck = (currentField / numberOfColumns) * numberOfColumns;
                int maximumNumberOfFieldForHorizontalCheck = minimumNumberOfFieldForHorizontalCheck + numberOfColumns - 1;
                return currentField < maximumNumberOfFieldForHorizontalCheck;
            }

            @Override
            public Integer next() {
                currentField += 1;
                return currentField;
            }
        };

        Iterator<Integer> horizontalBackward = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                int minimumNumberOfFieldForHorizontalCheck = (currentField / numberOfColumns) * numberOfColumns;
                return currentField > minimumNumberOfFieldForHorizontalCheck;
            }

            @Override
            public Integer next() {
                currentField -= 1;
                return currentField;
            }
        };

        Iterator<Integer> verticalForward = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return currentField + numberOfColumns < boardCapacity;
            }

            @Override
            public Integer next() {
                currentField += numberOfColumns;
                return currentField;
            }
        };

        Iterator<Integer> verticalBackward = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return currentField - numberOfColumns > 0;
            }

            @Override
            public Integer next() {
                currentField -= numberOfColumns;
                return currentField;
            }
        };

        Iterator<Integer> diagonalForward = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                int fieldToCheck = currentField + numberOfColumns + 1;
                int height = boardCapacity / numberOfColumns;
                return (fieldToCheck % numberOfColumns <= numberOfColumns - winningCondition) && (fieldToCheck / numberOfColumns <= height - winningCondition);
            }

            @Override
            public Integer next() {
                currentField += (numberOfColumns + 1);
                return currentField;
            }
        };

        Iterator<Integer> diagonalBackward = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return currentField - numberOfColumns > 0;
            }

            @Override
            public Integer next() {
                currentField -= numberOfColumns;
                return currentField;
            }
        };

        switch (directionToGo) {
            case HORFOR:
                return horizontalForward;
            case HORBACK:
                return horizontalBackward;
            case VERFOR:
                return verticalForward;
            case VERBACK:
                return verticalBackward;
            default:
                return null;
        }
    }
}
