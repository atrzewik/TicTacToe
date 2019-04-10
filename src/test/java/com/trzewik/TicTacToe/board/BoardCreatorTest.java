package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Sign;
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
    public void checkIfEmptyBoardIsCreated(int numberOfColumns, int numberOfRows) {
        BoardCreator boardCreator = new BoardCreator(numberOfRows, numberOfColumns);
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
}
