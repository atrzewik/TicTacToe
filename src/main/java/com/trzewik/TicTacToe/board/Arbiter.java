package com.trzewik.TicTacToe.board;


import com.trzewik.TicTacToe.Player;

/**
 * @author Agnieszka Trzewik
 */
public class Arbiter implements Observer {

    private Board board;
    private int winningCondition;

    Arbiter(int winningCondition) {
        this.winningCondition = winningCondition;
    }

    boolean isPlayerWinning(Player player) {
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, winningCondition);
        return winningConditionsChecker.checkDiagonalCondition() || winningConditionsChecker.checkDiagonalUpCondition()
                || winningConditionsChecker.checkHorizontalCondition() || winningConditionsChecker.checkVerticalCondition();
    }

    @Override
    public void update(Board board) {
        this.board = board;
    }
}
