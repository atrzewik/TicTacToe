package com.trzewik.OX;

import com.trzewik.OX.inputProvider.IllegalInterruptedException;
import com.trzewik.OX.inputProvider.UserInputProvider;
import org.testng.annotations.Test;

import java.util.Scanner;

/**
 * @author Agnieszka Trzewik
 */
public class OXGameTest {

    @Test(expectedExceptions = IllegalInterruptedException.class)
    private void should_quitGame_when_eneredQ() throws IllegalSizeOfBoardException, FieldAlreadyOccupiedException, IllegalInterruptedException {
        //arrange
        OXGame oxGame = new OXGame();
        UserInputProvider.userInput = new Scanner("first\nsecond\nq");
        //act
        oxGame.play();
        //assert
    }
}