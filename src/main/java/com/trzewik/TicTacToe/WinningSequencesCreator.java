package com.trzewik.TicTacToe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agnieszka Trzewik
 */
class WinningSequencesCreator {

    private int widthInt;
    private int heightInt;
    private int winningConditionInt;
    private int boardSize;
    private List<List<Integer>> listOfSequences = new ArrayList<>();

    WinningSequencesCreator(int widthInt, int heightInt, int winningConditionInt) {
        this.widthInt = widthInt;
        this.heightInt = heightInt;
        this.winningConditionInt = winningConditionInt;
        this.boardSize = widthInt * heightInt;
    }

    List<List<Integer>> createListOfSequences() {
        createAllSequences();
        return listOfSequences;
    }

    private void createAllSequences() {
        for (int i = 0; i < boardSize; i++) {
            createAllSequencesForField(i);
        }
    }

    private void createAllSequencesForField(int field) {
        if (isInLeftRange(field)) {
            listOfSequences.add(horizontalWinningSequence(field));
        }
        if (isInUpRange(field)){
            listOfSequences.add(verticalWinningSequence(field));
        }
        if(isInLeftRange(field)&&isInUpRange(field)){
            listOfSequences.add(diagonalDownRightWinningSequence(field));
        }
        if (isInRightRange(field)) {
            listOfSequences.add(diagonalDownLeftWinningSequence(field));
        }
    }

    private boolean isInLeftRange(int field) {
        return field % widthInt <= widthInt - winningConditionInt;
    }

    private boolean isInUpRange(int field){
        return field / widthInt <= heightInt - winningConditionInt;
    }

    private boolean isInRightRange(int field) {
        return (field % widthInt >= winningConditionInt - 1) &&
                ((field / widthInt <= heightInt - winningConditionInt));
    }

    private List<Integer> verticalWinningSequence(int field) {
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < winningConditionInt; i++) {
            sequence.add(field);
            field += widthInt;
        }
        return sequence;
    }

    private List<Integer> horizontalWinningSequence(int field) {
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < winningConditionInt; i++) {
            sequence.add(field);
            field += 1;
        }
        return sequence;
    }

    private List<Integer> diagonalDownRightWinningSequence(int field) {
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < winningConditionInt; i++) {
            sequence.add(field);
            field += (widthInt + 1);
        }
        return sequence;
    }

    private List<Integer> diagonalDownLeftWinningSequence(int field) {
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < winningConditionInt; i++) {
            sequence.add(field);
            field += (widthInt - 1);
        }
        return sequence;
    }
}
