package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Sign;
import com.trzewik.TicTacToe.displayer.Language;
import com.trzewik.TicTacToe.settings.Settings;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * @author Agnieszka Trzewik
 */
@Test
public class BoardTest {

    @Test
    public void checkIfHorizontalForwardIteratorWorkProperly() throws FieldAlreadyOccupiedException {
        Board board = createBoard(9,3);
        board.markField(13, Sign.O);
        Board.MoveHorizontalForward moveHorizontalForward = board.moveHorizontalForward;
        for (Iterator i = moveHorizontalForward.iterator(); i.hasNext(); )
            System.out.println(i.next());
        }

    @Test
    public void checkIfHorizontalBackwardIteratorWorkProperly() throws FieldAlreadyOccupiedException {
        Board board = createBoard(5,5);
        board.markField(7, Sign.O);
        Board.MoveHorizontalBackward moveHorizontalBackward = board.moveHorizontalBackward;
        for (int i = 0; i < 6; i++) {
            if (moveHorizontalBackward.iterator().hasNext()) {
                System.out.println(moveHorizontalBackward.iterator().next());
            }
        }
    }

    @Test(dataProvider = "coordinatesAndSignsForBusyField", expectedExceptions = FieldAlreadyOccupiedException.class)
    public void fieldIsBusySoFieldCanNotBeMarked(int field, Sign sign1, Sign sign2) throws FieldAlreadyOccupiedException {
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
    public void capacityOfBoardIsRight(int numberOfColumns, int numberOfRows, int capacity) {
        Board board = createBoard(numberOfColumns, numberOfRows);
        assert board.countCapacity() == capacity : "Capacity of board should be right";
    }

    private Board createBoard(int numberOfColumns, int numberOfRows) {
        return new Board(new BoardCreator(numberOfRows, numberOfColumns), numberOfColumns, Language.POLISH);
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

    private void setMockitoForTest(int numberOfColumns, int numberOfRows, int winningCondition){
        Settings settings = Mockito.mock(Settings.class);
        Mockito.when(settings.getGameSettings("number_of_columns")).thenReturn(numberOfColumns);
        Mockito.when(settings.getGameSettings("number_of_rows")).thenReturn(numberOfRows);
        Mockito.when(settings.getGameSettings("winning_condition")).thenReturn(winningCondition);
    }
}
