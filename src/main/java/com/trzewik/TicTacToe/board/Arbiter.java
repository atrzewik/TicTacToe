package com.trzewik.TicTacToe;


import com.trzewik.TicTacToe.board.Board;

/**
 * @author Agnieszka Trzewik
 */
public class Arbiter {

    private Board board;
    private int winningCondition;

    Arbiter( int winningCondition) {
        this.winningCondition = winningCondition;
    }

    void updateBoard(Board board){
        this.board = board;
    }

    boolean isPlayerWinning(Player player) {
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, winningCondition);
        return winningConditionsChecker.checkDiagonalCondition() || winningConditionsChecker.checkDiagonalUpCondition()
                || winningConditionsChecker.checkHorizontalCondition() || winningConditionsChecker.checkVerticalCondition();
    }
}
