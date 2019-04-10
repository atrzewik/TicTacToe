package com.trzewik.TicTacToe;

import com.trzewik.OX.inputProvider.IllegalInterruptedException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Agnieszka Trzewik
 */
@Test
public class BoardTest {

    @Test(dataProvider = "coordinatesAndSignsForBusyField", expectedExceptions = FieldAlreadyOccupiedException.class)
    public void fieldIsBusySoFieldCanNotBeMarked(int field, Sign sign1, Sign sign2) throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException {
        Board board = createBoard(5,5);
        board.markField(field, sign1);
        board.markField(field, sign2);
    }

    @DataProvider
    public static Object[][] coordinatesAndSignsForBusyField() {
        return new Object[][] {
            {0, Sign.O, Sign.X},
            {1, Sign.X, Sign.X},
            {2, Sign.X, Sign.O},
            {3, Sign.O, Sign.O},
        };
    }

    @Test(dataProvider = "getNumberOfColumnsRowsAndCapacity")
    public void capacityOfBoardIsRight(int numberOfColumns, int numberOfRows, int capacity) throws IllegalSizeOfBoardException, IllegalInterruptedException {
        Board board = createBoard(numberOfColumns, numberOfRows);
        assert board.countCapacity() == capacity : "Capacity of board should be right";
    }

    private Board createBoard(int numberOfColumns, int numberOfRows) throws IllegalSizeOfBoardException, IllegalInterruptedException {
        return new Board(new BoardCreator(numberOfColumns, numberOfRows));
    }

    @DataProvider
    public static Object[][] getNumberOfColumnsRowsAndCapacity() {
        return new Object[][] {
                {5,5,25},
                {6,9,54},
                {7,18,126},
                {100,10,1000},
                {3,100,300},
                {55,55,3025},
                {3,3,9},
                {100,100,10000}
        };
    }


    @Test(dataProvider = "getWrongNumberOfColumnsAndRows", expectedExceptions = IllegalSizeOfBoardException.class)
    public void capacityOfBoardWrong(int numberOfColumns, int numberOfRows) throws IllegalSizeOfBoardException, IllegalInterruptedException {
        createBoard(numberOfColumns, numberOfRows);
    }

    @DataProvider
    public static Object[][] getWrongNumberOfColumnsAndRows() {
        return new Object[][] {
                {5,101},
                {200,9},
                {2,18},
                {10,2},
                {1,1},
                {2,2}
        };
    }
}
