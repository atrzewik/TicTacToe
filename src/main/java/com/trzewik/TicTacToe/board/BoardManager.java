package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Player;
import com.trzewik.TicTacToe.Players;
import com.trzewik.TicTacToe.Sign;
import com.trzewik.TicTacToe.displayer.IllegalInterruptedException;
import com.trzewik.TicTacToe.displayer.Language;
import com.trzewik.TicTacToe.displayer.UserInputProvider;
import com.trzewik.TicTacToe.settings.Settings;


/**
 * @author Agnieszka Trzewik
 */
public class BoardManager {

    private int quantityOfMarkedFields;
    private boolean somePlayerWon;
    private UserInputProvider userInputProvider;
    private Language language;
    private Arbiter arbiter;
    private BoardPrinter boardPrinter;
    private Board board;

    public BoardManager(Settings settings, UserInputProvider userInputProvider) {
        this.language = settings.getLanguage();
        this.userInputProvider = userInputProvider;
        arbiter = new Arbiter(settings.getGameSettings("winning_condition"));
        boardPrinter = new BoardPrinter(settings.getLogger(), settings.getLanguage(), settings.getGameSettings("number_of_columns"));
        createBoard(settings.getGameSettings("number_of_rows"), settings.getGameSettings("number_of_columns"));
        quantityOfMarkedFields = 0;
        somePlayerWon = false;
    }

    private void createBoard(int numberOfRows, int numberOfColumns) {
        board = new Board(new BoardCreator(numberOfRows, numberOfColumns), numberOfColumns, language);
        board.addObserver(arbiter);
        board.addObserver(boardPrinter);
        board.notifyObservers();
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
        addPointsIfPlayerWon(player);
    }

    public void printBoard() {
        boardPrinter.printBoard();
    }

    public boolean gameHasEnded() {
        return somePlayerWon || board.countCapacity() == quantityOfMarkedFields;
    }

    public void assignPointsIfDraw(Players players) {
        if (!somePlayerWon) {
            for (int i = 0; i < players.numbersOfPlayers(); i++) {
                players.getPlayer(i).addPoints(1);
            }
        }
    }

    private void addPointsIfPlayerWon(Player player) {
        if (somePlayerWon) {
            player.addPoints(3);
        }
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
