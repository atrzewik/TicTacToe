package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Sign;
import org.testng.annotations.Test;

/**
 * @author Agnieszka Trzewik
 */
@Test
public class FieldTest {

    @Test
    public void fieldIsEmpty(){
        Field field = new Field();
        field.setSignOfField(Sign.EMPTY);
        assert field.signOfField().equals(Sign.EMPTY) : "Field should by sign by empty";
    }

    @Test
    public void fieldIsOccupiedByCross(){
        Field field = new Field();
        field.setSignOfField(Sign.X);
        assert field.signOfField().equals(Sign.X) : "Field should by sign by cross";
    }

    @Test
    public void fieldIsOccupiedByNaught(){
        Field field = new Field();
        field.setSignOfField(Sign.O);
        assert field.signOfField().equals(Sign.O) : "Field should by sign by naught";
    }

    @Test
    public void fieldIsEmptyByDefault(){
        Field field = new Field();
        assert field.signOfField().equals(Sign.EMPTY) : "Field should be sign by empty";
    }

    @Test
    public void twoDifferentFieldAreNotSame(){
        Field field = new Field();
        Field field2 = new Field();
        assert !field.equals(field2) : "Fields should not be the same";
    }
}
