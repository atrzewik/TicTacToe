package com.trzewik.OX;

/**
 * @author Agnieszka Trzewik
 */
class Player {

    private Sign playerSign;
    private String name;
    private int lastMove;
    private int score;


    Player(String name, Sign playerSign) {
        this.playerSign = playerSign;
        this.name = name;
        this.score = 0;
    }

    Sign playerSign() {
        return playerSign;
    }

    void changeLastMove(int move){
        lastMove = move;
    }

    int getLastMove() {
        return lastMove;
    }

    void addPonits(int points){
        score += points;
    }

    int countScore(){
        return score;
    }

    @Override
    public String toString() {
        return name;
    }

}
