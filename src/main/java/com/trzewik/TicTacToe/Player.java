package com.trzewik.TicTacToe;

/**
 * @author Agnieszka Trzewik
 */
public abstract class Player {

    private Sign playerSign;
    private String name;
    private int lastMove;
    private int score;


    Player(String name, Sign playerSign) {
        this.playerSign = playerSign;
        this.name = name;
        this.score = 0;
    }

    public Sign playerSign() {
        return playerSign;
    }

    public int getLastMove() {
        return lastMove;
    }

    public void changeLastMove(int move) {
        lastMove = move;
    }

    public void addPoints(int points) {
        score += points;
    }

    public int countScore() {
        return score;
    }

    @Override
    public String toString() {
        return name;
    }

}
