package com.trzewik.TicTacToe;

import com.trzewik.TicTacToe.displayer.ConsoleLogger;
import com.trzewik.TicTacToe.settings.Settings;

import java.util.List;
import java.util.Scanner;

/**
 * @author Agnieszka Trzewik
 */
public class Main {

    private static String width;
    private static String height;
    private static String winningCondition;

    public static void main(String[] args) {
        if (args.length == 0) {
            new Game(new Scanner(System.in), new Settings(new ConsoleLogger())).play();
            return;
        } else if (args.length < 3) {
            System.out.println("Error");
            return;
        }

        width = args[0];
        height = args[1];
        winningCondition = args[2];

        String configuration = createBaseStringConfiguration();
        List<List<Integer>> allSequencesForAutomatedGame = getMoveSequencesForAutomatedGame();
        int indexOfCurrentPlayedSequence = 0;

        while (indexOfCurrentPlayedSequence < allSequencesForAutomatedGame.size()) {

            SingleSequencesCreatorForAutomate SingleSequencesCreatorForAutomate = new SingleSequencesCreatorForAutomate(allSequencesForAutomatedGame, indexOfCurrentPlayedSequence).ifIsPossibleCreateSequencesForSingleGame();
            if (SingleSequencesCreatorForAutomate.isEndOfSequences()) return;
            indexOfCurrentPlayedSequence = SingleSequencesCreatorForAutomate.getIndexOfCurrentPlayedSequence();
            StringBuilder buildSequenceForOneGame = SingleSequencesCreatorForAutomate.getBuildSequenceForOneGame();
            String threeSequencesForGame = configuration + buildSequenceForOneGame.toString();

            new Game(new Scanner(threeSequencesForGame), new Settings(new ConsoleLogger())).play();

        }
    }


    private static String createBaseStringConfiguration(){
        return  "y\n" + width + "\n" + height + "\n" + winningCondition + "\npolish\nO\nX\n";
    }

    private static List<List<Integer>> getMoveSequencesForAutomatedGame() {
        WinningSequencesCreator winningSequencesCreator = new WinningSequencesCreator(Integer.parseInt(width), Integer.parseInt(height), Integer.parseInt(winningCondition));
        List<List<Integer>> listOfSequences = winningSequencesCreator.createListOfSequences();
        AllSequencesCreator allSequences = new AllSequencesCreator(Integer.parseInt(width), Integer.parseInt(height), Integer.parseInt(winningCondition), listOfSequences);
        return allSequences.createAllSequencesForAutomate();
    }
}