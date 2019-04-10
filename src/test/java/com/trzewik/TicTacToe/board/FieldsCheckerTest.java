package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Sign;
import com.trzewik.TicTacToe.displayer.Language;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

/**
 * @author Agnieszka Trzewik
 */
public class FieldsCheckerTest {

    @Test
    public void checkIfHorizontalForwardIteratorWorkProperly() throws FieldAlreadyOccupiedException {
        Board board = new Board(new BoardCreator(3,9), 9, Language.POLISH);
        board.markField(13, Sign.O);
        FieldsChecker fieldsChecker = new FieldsChecker(board.getCurrentField(), 9, 27, DirectionToGo.HORFOR);
        for (Iterator i = fieldsChecker.iterator(); i.hasNext(); )
            System.out.println(i.next());
    }

    @Test
    public void checkIfHorizontalBackwardIteratorWorkProperly() throws FieldAlreadyOccupiedException {
        Board board = new Board(new BoardCreator(5, 5), 5, Language.POLISH);
        board.markField(7, Sign.O);
        FieldsChecker fieldsChecker = new FieldsChecker(board.getCurrentField(), 5, 25, DirectionToGo.HORBACK);
        for (int i = 0; i < 6; i++) {
            if (fieldsChecker.iterator().hasNext()) {
                System.out.println(fieldsChecker.iterator().next());
            }
        }
    }


    @Test
    public void checkIfVerticalForwardIteratorWorkProperly() throws FieldAlreadyOccupiedException {
        Board board = new Board(new BoardCreator(5,5), 5, Language.POLISH);
        board.markField(6, Sign.O);
        FieldsChecker fieldsChecker = new FieldsChecker(board.getCurrentField(), 5, 25, DirectionToGo.VERFOR);
        for (Iterator i = fieldsChecker.iterator(); i.hasNext(); )
            System.out.println(i.next());
    }

    @Test
    public void checkIfVerticalBackwardIteratorWorkProperly() throws FieldAlreadyOccupiedException {
        Board board = new Board(new BoardCreator(5,5), 5, Language.POLISH);
        board.markField(6, Sign.O);
        FieldsChecker fieldsChecker = new FieldsChecker(board.getCurrentField(), 5, 25, DirectionToGo.VERBACK);
        for (int i = 0; i < 6; i++) {
            if (fieldsChecker.iterator().hasNext()) {
                System.out.println(fieldsChecker.iterator().next());
            }
        }
    }
}