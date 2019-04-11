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
        String configuration = "y\n" + width + "\n" + height + "\n" + winningCondition + "\npolish\nO\nX\n";
        List<List<Integer>> seq = returnResources();

        int seqNumber = 0;

        while (seqNumber < seq.size()) {

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                if (seqNumber >= seq.size()) {
                    System.out.println("Koniec symulacji.");
                    return;
                }
                List<Integer> oneSeq = seq.get(seqNumber);
                seqNumber++;
                for (Integer integer : oneSeq) {
                    stringBuilder.append(integer + "\n");
                }
            }
            String stringForGame = configuration + stringBuilder.toString();

            new Game(new Scanner(stringForGame), new Settings(new ConsoleLogger())).play();

        }
    }

    private static List<List<Integer>> returnResources() {
        AllWinningSequencesCreator allWinningSequencesCreator = new AllWinningSequencesCreator(Integer.parseInt(width), Integer.parseInt(height), Integer.parseInt(winningCondition));
        List<List<Integer>> listOfSequences = allWinningSequencesCreator.createListOfSequences();
        AllSequencesCreator allSequences = new AllSequencesCreator(Integer.parseInt(width), Integer.parseInt(height), Integer.parseInt(winningCondition), listOfSequences);
        return allSequences.createAllSequencesForAutomate();
    }
}