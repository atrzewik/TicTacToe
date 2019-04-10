package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Player;
import com.trzewik.TicTacToe.PlayerO;
import com.trzewik.TicTacToe.Sign;
import com.trzewik.TicTacToe.displayer.IllegalInterruptedException;
import com.trzewik.TicTacToe.displayer.Language;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * @author Agnieszka Trzewik
 */
@Test
public class WinningConditionsCheckerTest {

    @Test(dataProvider = "dataForWinningDiagonalUpCondition")
    public void diagonalUpWinningConditionIsMet(int numberOfColumns, int numberOfRows, int field, Sign sign, int winningCondition) throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException, IllegalInterruptedException {
        Player player = new PlayerO("Jaś");
        player.changeLastMove(field);
        Board board = createBoard(numberOfColumns, numberOfRows);
        int fieldInDiagonal = field;
        for (int i = 0; i < winningCondition; i++) {
            board.markField(fieldInDiagonal, sign);
            player.changeLastMove(fieldInDiagonal);
            fieldInDiagonal += numberOfColumns - 1;
        }
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, winningCondition);
        assert winningConditionsChecker.checkDiagonalUpCondition() : "Diagonal up winning condition should be true";
    }

    @DataProvider
    public static Object[][] dataForWinningDiagonalUpCondition() {
        return new Object[][]{
                {3, 3, 2, Sign.O, 3},
                {5, 6, 4, Sign.O, 3},
                {9, 9, 17, Sign.O, 6}
        };
    }

    @Test()
    public void checkDiagonalUpConditionIsWrong() throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException {
        Player player = new PlayerO("Jaś");
        player.changeLastMove(26);
        Board board = createBoard(7, 5);
        board.markField(20,Sign.O);
        board.markField(26,Sign.O);
        board.markField(14,Sign.O);
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, 3);
        assert !winningConditionsChecker.checkDiagonalUpCondition() : "Diagonal winning condition should be false";
    }

    @Test()
    public void diagonalWinningConditionIsMetIsCheckedProperly() throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException {
        Player player = new PlayerO("Jaś");
        player.changeLastMove(21);
        Board board = createBoard(7, 5);
        board.markField(5,Sign.O);
        board.markField(13,Sign.O);
        board.markField(21,Sign.O);
        board.markField(29, Sign.O);
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, 4);
        assert !winningConditionsChecker.checkDiagonalCondition() : "Diagonal winning condition should be false";
    }

    @Test()
    public void diagonalWinningConditionIsWrong() throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException {
        Player player = new PlayerO("Jaś");
        player.changeLastMove(13);
        Board board = createBoard(7, 5);
        board.markField(5,Sign.O);
        board.markField(13,Sign.O);
        board.markField(21,Sign.O);
        board.markField(29, Sign.O);
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, 4);
        winningConditionsChecker.checkDiagonalUpCondition();
        assert !winningConditionsChecker.checkDiagonalCondition() : "Diagonal winning condition should be false";
    }

    @Test(dataProvider = "dataForWinningDiagonalCondition")
    public void diagonalWinningConditionIsMet(int numberOfColumns, int numberOfRows, int field, Sign sign, int winningCondition) throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException {
        Player player = new PlayerO("Jaś");
        player.changeLastMove(field);
        Board board = createBoard(numberOfColumns, numberOfRows);
        int fieldInDiagonal = field;
        for (int i = 0; i < winningCondition; i++) {
            board.markField(fieldInDiagonal, sign);
            fieldInDiagonal += numberOfColumns + 1;
        }
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, winningCondition);
        assert winningConditionsChecker.checkDiagonalCondition() : "Diagonal winning condition should be true";
    }

    @DataProvider
    public static Object[][] dataForWinningDiagonalCondition() {
        return new Object[][]{
                {3, 3, 0, Sign.O, 3},
                {5, 6, 5, Sign.O, 3},
                {9, 9, 9, Sign.O, 5}
        };
    }

    @Test(dataProvider = "dataForWinningHorizontalCondition")
    public void horizontalWinningConditionIsMet(int numberOfColumns, int numberOfRows, int field, Sign sign, int winningCondition) throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException {
        Player player = new PlayerO("Jaś");
        player.changeLastMove(field);
        Board board = createBoard(numberOfColumns, numberOfRows);
        int fieldInRow = field;
        for (int i = 0; i < winningCondition; i++) {
            board.markField(fieldInRow, sign);
            fieldInRow++;
        }
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, winningCondition);
        assert winningConditionsChecker.checkHorizontalCondition() : "Horizontal winning condition should be true";
    }

    @DataProvider
    public static Object[][] dataForWinningHorizontalCondition() {
        return new Object[][]{
                {3, 3, 0, Sign.O, 3},
                {6, 5, 18, Sign.O, 5},
                {6, 5, 12, Sign.O, 5},
                {10, 4, 30, Sign.O, 4}
        };
    }

    @Test(dataProvider = "dataForWinningHorizontalConditionUpsideDown")
    public void horizontalWinningConditionIsMetUpsideDown(int numberOfColumns, int numberOfRows, int field, Sign sign, int winningCondition) throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException {
        Player player = new PlayerO("Jaś");
        player.changeLastMove(field);
        Board board = createBoard(numberOfColumns, numberOfRows);
        int fieldInRow = field;
        for (int i = 0; i < winningCondition; i++) {
            board.markField(fieldInRow, sign);
            fieldInRow--;
        }
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, winningCondition);
        assert winningConditionsChecker.checkHorizontalCondition() : "Horizontal winning condition should be true";
    }

    @DataProvider
    public static Object[][] dataForWinningHorizontalConditionUpsideDown() {
        return new Object[][]{
                {3, 3, 2, Sign.O, 3},
                {6, 5, 17, Sign.O, 5},
                {6, 5, 11, Sign.O, 6},
                {10, 4, 29, Sign.O, 10}
        };
    }

    @Test(dataProvider = "dataForWinningVerticalCondition")
    public void verticalWinningConditionIsMet(int numberOfColumns, int numberOfRows, int field, Sign sign, int winningCondition) throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException {
        Player player = new PlayerO("Jaś");
        player.changeLastMove(field);
        Board board = createBoard(numberOfColumns, numberOfRows);
        int fieldInColumn = field;
        for (int i = 0; i < winningCondition; i++) {
            board.markField(fieldInColumn, sign);
            fieldInColumn += numberOfColumns;
        }
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, winningCondition);
        assert winningConditionsChecker.checkVerticalCondition() : "Vertical winning condition should be true";
    }

    private Board createBoard(int numberOfColumns, int numberOfRows){
        return new Board(new BoardCreator(numberOfRows, numberOfColumns), numberOfColumns, Language.POLISH);
    }

    @DataProvider
    public static Object[][] dataForWinningVerticalCondition() {
        return new Object[][]{
                {3, 3, 0, Sign.O, 3},
                {5, 6, 1, Sign.O, 5},
                {5, 6, 3, Sign.O, 5},
                {4, 10, 2, Sign.O, 4}
        };
    }

    @Test(enabled = false, dataProvider = "dataForLoosingVerticalCondition")
    public void verticalWinningConditionNotExist(int numberOfColumns, int numberOfRows, int field, Sign sign, int winningCondition) throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException {
        Player player = new PlayerO("Jaś");
        player.changeLastMove(field);
        Board board = createBoard(numberOfColumns, numberOfRows);
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(winningCondition - 1); i++) {
            board.markField(field, sign);
        }
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, winningCondition);
        assert !winningConditionsChecker.checkVerticalCondition() : "Vertical winning condition should be false";
    }

    @DataProvider
    public static Object[][] dataForLoosingVerticalCondition() {
        return new Object[][]{
                {3, 3, 0, 0, Sign.O, 3},
                {5, 6, 1, 0, Sign.X, 5},
                {5, 6, 3, 0, Sign.X, 6},
                {4, 10, 2, 0, Sign.O, 10}
        };
    }


    @Test(enabled = false, dataProvider = "dataWhenVerticalWinningNotPossible")
    public void verticalWinningNotPossible(int numberOfColumns, int numberOfRows, int field, Sign sign, int winningCondition) throws FieldAlreadyOccupiedException {
        Player player = new PlayerO("Jaś");
        player.changeLastMove(field);
        Board board = createBoard(numberOfColumns, numberOfRows);
        for (int i = 0; i < numberOfRows; i++) {
            board.markField(field, sign);
        }
        WinningConditionsChecker winningConditionsChecker = new WinningConditionsChecker(board, player, winningCondition);
        assert !winningConditionsChecker.checkVerticalCondition() : "Vertical winning condition should be false";
    }

    @DataProvider
    public static Object[][] dataWhenVerticalWinningNotPossible() {
        return new Object[][]{
                {9, 3, 0, 0, Sign.O, 4},
                {70, 61, 1, 0, Sign.X, 65},
                {6, 5, 3, 0, Sign.X, 6},
                {10, 4, 2, 0, Sign.O, 10}
        };
    }
}