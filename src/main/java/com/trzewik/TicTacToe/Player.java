package com.trzewik.TicTacToe;

import java.util.Objects;

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

    int countScore() {
        return score;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return lastMove == player.lastMove &&
                score == player.score &&
                playerSign == player.playerSign &&
                name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerSign, name, lastMove, score);
    }
}
