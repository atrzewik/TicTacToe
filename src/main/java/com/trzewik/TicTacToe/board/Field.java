package com.trzewik.TicTacToe.board;

import com.trzewik.TicTacToe.Sign;

/**
 * @author Agnieszka Trzewik
 */
class Field {

    private Sign sign;

    Field() {
        this.sign = Sign.EMPTY;
    }

    Sign signOfField() {
        return sign;
    }

    void setSignOfField(Sign sign){
        this.sign = sign;
    }
}