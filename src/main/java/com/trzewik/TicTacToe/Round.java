package com.trzewik.TicTacToe;

import com.trzewik.TicTacToe.board.BoardManager;
import com.trzewik.TicTacToe.displayer.IllegalInterruptedException;
import com.trzewik.TicTacToe.displayer.Language;
import com.trzewik.TicTacToe.displayer.Logger;


/**
 * @author Agnieszka Trzewik
 */
public class Round {

    private Players players;
    private Language language;
    private Logger logger;
    private BoardManager boardCooperator;

    Round(Players players, Logger logger, Language language, BoardManager boardCooperator) {
        this.players = players;
        this.logger = logger;
        this.language = language;
        this.boardCooperator = boardCooperator;
    }

    void play() throws IllegalInterruptedException {

        while (!boardCooperator.gameHasEnded()) {
            boardCooperator.doAMove(players.getCurrentPlayer());
            players.changeCurrentPlayer();
        }

        boardCooperator.printBoard();
        boardCooperator.assignPointsIfDraw(players);

        logger.display(language, "current_results", players.getPlayer(0).toString(), Integer.toString(players.getPlayer(0).countScore()),
                players.getPlayer(1).toString(), Integer.toString(players.getPlayer(1).countScore()));

        players.changeStartingPlayer();
        boardCooperator.resetBoardCooperator();
    }
}
