package com.trzewik.TicTacToe;

import java.util.List;

/**
 * @author Agnieszka Trzewik
 */
class SingleSequencesCreatorForAutomate {
        private boolean endOfSequences;
        private List<List<Integer>> allSequencesForAutomatedGame;
        private int indexOfCurrentPlayedSequence;
        private StringBuilder buildSequenceForOneGame;

        SingleSequencesCreatorForAutomate(List<List<Integer>> allSequencesForAutomatedGame, int indexOfCurrentPlayedSequence) {
            this.allSequencesForAutomatedGame = allSequencesForAutomatedGame;
            this.indexOfCurrentPlayedSequence = indexOfCurrentPlayedSequence;
        }

        private static void threeSequencesBuilder(StringBuilder buildSequenceForOneGame, List<Integer> currentSequence) {
            for (Integer integer : currentSequence) {
                buildSequenceForOneGame.append(integer).append("\n");
            }
        }

        boolean isEndOfSequences() {
            return endOfSequences;
        }

        int getIndexOfCurrentPlayedSequence() {
            return indexOfCurrentPlayedSequence;
        }

        StringBuilder getBuildSequenceForOneGame() {
            return buildSequenceForOneGame;
        }

        SingleSequencesCreatorForAutomate ifIsPossibleCreateSequencesForSingleGame() {
            buildSequenceForOneGame = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                if (indexOfCurrentPlayedSequence >= allSequencesForAutomatedGame.size()) {
                    System.out.println("Koniec symulacji.");
                    endOfSequences = true;
                    return this;
                }
                List<Integer> currentSequence = allSequencesForAutomatedGame.get(indexOfCurrentPlayedSequence);
                indexOfCurrentPlayedSequence++;
                threeSequencesBuilder(buildSequenceForOneGame, currentSequence);
            }
            endOfSequences = false;
            return this;
        }
}
