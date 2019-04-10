package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Player;
import com.trzewik.TicTacToe.Sign;
import com.trzewik.TicTacToe.displayer.IllegalInterruptedException;
import com.trzewik.TicTacToe.displayer.Language;
import com.trzewik.TicTacToe.displayer.Logger;
import com.trzewik.TicTacToe.displayer.UserInputProvider;
import com.trzewik.TicTacToe.settings.Settings;


/**
 * @author Agnieszka Trzewik
 */
public class BoardCooperator {

    private int quantityOfMarkedFields;
    private boolean somePlayerWon;
    private UserInputProvider userInputProvider;
    private Settings settings = Settings.getInstance();
    private Logger logger = settings.getLogger();
    private Language language = settings.getLanguage();


    private Arbiter arbiter;
    private BoardPrinter boardPrinter;
    private Board board;

    private BoardCooperator() throws IllegalInterruptedException, IllegalSizeOfBoardException {
        arbiter = new Arbiter(settings.getGameSettings("winning_condition"));
        boardPrinter = new BoardPrinter(logger);
        board = new Board(new BoardCreator(settings.getGameSettings("number_of_columns"), settings.getGameSettings("number_of_rows")));
        board.addObserver(arbiter);
        board.addObserver(boardPrinter);
        board.notifyObservers();
    }

    public static BoardCooperator createBoardCooperatorForBoard(UserInputProvider userInputProvider) throws IllegalInterruptedException, IllegalSizeOfBoardException {
        BoardCooperator boardCooperator = new BoardCooperator();
        boardCooperator.userInputProvider = userInputProvider;
        boardCooperator.quantityOfMarkedFields = 0;
        boardCooperator.somePlayerWon = false;
        return boardCooperator;
    }

    public void resetBoardCooperator() {
        quantityOfMarkedFields = 0;
        somePlayerWon = false;
        board.clearBoard();
        board.notifyObservers();
    }

    public void doAMove(Player player) throws IllegalInterruptedException {
        printBoard();
        collectField(player);
        somePlayerWon = arbiter.isPlayerWinning(player);
    }

    public void printBoard(){
        boardPrinter.printBoard();
    }

    public boolean gameHasEnded() {
        return somePlayerWon || board.countCapacity() == quantityOfMarkedFields;
    }

    private void collectField(Player player) throws IllegalInterruptedException {
        Sign playerSign = player.playerSign();
        while (true) {
            try {
                int move = userInputProvider.collectIntegerInRangeMinMax(language, 0, board.countCapacity() - 1,
                        "input_for_field", playerSign.name());
                board.markField(move, playerSign);
                board.notifyObservers();
                player.changeLastMove(move);
                quantityOfMarkedFields++;
                return;
            } catch (FieldAlreadyOccupiedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
