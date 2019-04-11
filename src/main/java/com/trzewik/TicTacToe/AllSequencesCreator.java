package com.trzewik.TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Agnieszka Trzewik
 */
public class AllSequencesCreator {
    private int winningConditionInt;
    private int boardSize;
    private List<List<Integer>> listOfSequences;
    private List<List<Integer>> allSequencesForAutomate = new ArrayList<>();

    AllSequencesCreator(int widthInt, int heightInt, int winningConditionInt, List<List<Integer>> listOfSequences) {
        this.winningConditionInt = winningConditionInt;
        this.listOfSequences = listOfSequences;
        this.boardSize = widthInt * heightInt;
    }

    List<List<Integer>> createAllSequencesForAutomate() {
        Random rand = new Random();
        for (List<Integer> winningSequence : listOfSequences) {
            List<Integer> loosingSequence = createLoosingSequence(winningSequence, rand);
            createAllSequencesForOneCase(winningSequence, loosingSequence);
        }
        return allSequencesForAutomate;
    }

    private void createAllSequencesForOneCase(List<Integer> winningSequence, List<Integer> loosingSequence) {
        List<Integer> sequenceForAutomate = new ArrayList<>();
        for (int i = 0; i < winningConditionInt - 1; i++) {
            sequenceForAutomate.add(winningSequence.get(i));
            sequenceForAutomate.add(loosingSequence.get(i));
        }
        sequenceForAutomate.add(winningSequence.get(winningConditionInt - 1));
        allSequencesForAutomate.add(sequenceForAutomate);
    }

    private List<Integer> createLoosingSequence(List<Integer> winningSequence, Random random) {
        List<Integer> loosingSequence = new ArrayList<>();
        while (loosingSequence.size() < winningConditionInt - 1) {
            int field = random.nextInt(boardSize);
            if (!winningSequence.contains(field) && !loosingSequence.contains(field)) {
                loosingSequence.add(field);
            }
        }
        return loosingSequence;
    }
}