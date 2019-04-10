package com.trzewik.TicTacToe;

import com.trzewik.TicTacToe.displayer.ConsoleLogger;
import com.trzewik.TicTacToe.settings.Settings;

import java.util.Scanner;

/**
 * @author Agnieszka Trzewik
 */
public class AutomatedMain {

    private static String width;
    private static String height;
    private static String winningCondition;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("error");
            return;
        }
        width = args[0];
        height = args[1];
        winningCondition = args[2];

        new Game(new Scanner(System.in), new Settings(new ConsoleLogger())).play();
    }


    boolean isInLeftUppperRange(int number) {
        return (number % Integer.parseInt(width) <= Integer.parseInt(width) - Integer.parseInt(winningCondition)) &&
                (number / Integer.parseInt(width) <= Integer.parseInt(height) - Integer.parseInt(winningCondition));
    }

    boolean isInLeftLowerRange(int number) {
        return ((number % Integer.parseInt(width)) >= (Integer.parseInt(winningCondition) - 1) &&
                ((number / Integer.parseInt(width)) <= (Integer.parseInt(height) - Integer.parseInt(winningCondition))));
    }

    void getAllPossiblities() {

    }

    //for(int i = 0; i < boardSize; i++){
    //if ( checkSzer(i) && checkWys(i) ){
    //genDoł();
    //genGóra();
    //genDółSkos();
    //else if( jestWDrugimPrzedziale ){
    // gen skosGóra()/dółLewo();
    //}
}
