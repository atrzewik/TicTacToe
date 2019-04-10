package com.trzewik.TicTacToe;

import com.trzewik.OX.inputProvider.IllegalInterruptedException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

/**
 * @author Agnieszka Trzewik
 */
@Test
public class BoardCreatorTest {

    @Test(dataProvider = "getNumberOfColumnsAndRows")
    public void checkIfEmptyBoardIsCreated(int numberOfColumns, int numberOfRows) throws IllegalSizeOfBoardException, IllegalInterruptedException {
        BoardCreator boardCreator = new BoardCreator(numberOfColumns, numberOfRows);
        List<Field> board = boardCreator.createBoard();
        Random rand = new Random();
        Field field = board.get(rand.nextInt(board.size()));
        assert field.signOfField().equals(Sign.EMPTY);
    }

    @DataProvider
    public static Object[][] getNumberOfColumnsAndRows() {
        return new Object[][] {
            {5,5},
            {6,9},
            {7,18},
            {100,10},
            {3,100},
            {55,55},
            {3,3}
        };
    }

    @Test(dataProvider = "getWrongNumberOfColumnsAndRows", expectedExceptions = IllegalSizeOfBoardException.class)
    public void checkIfBoardCantBeCreatedLessThan3MoreThan100(int numberOfColumns, int numberOfRows) throws IllegalSizeOfBoardException, IllegalInterruptedException {
        new BoardCreator(numberOfColumns, numberOfRows);
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
