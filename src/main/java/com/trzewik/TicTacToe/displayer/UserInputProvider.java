package com.trzewik.TicTacToe;

import java.util.Scanner;

import static com.trzewik.TicTacToe.Language.POLISH;
import static com.trzewik.TicTacToe.Language.oxBundle;

/**
 * @author Agnieszka Trzewik
 */
public class UserInputProvider {

    private Logger logger;
    private Scanner userInput;

    private UserInputProvider(Scanner userInput, Logger logger){
        this.userInput = userInput;
        this.logger = logger;
    }


    public UserInputProvider userInputProvider(Scanner userInput, Logger logger){
        return new UserInputProvider(userInput, logger);
    }

    public static void main(String[] args) throws IllegalInterruptedException {
        UserInputProvider userInputProvider = new UserInputProvider(new Scanner(System.in), new ConsoleLogger());
        userInputProvider.collectString(POLISH, "input_must_be_string");
    }

    public Integer collectIntegerInRangeMinMax(Language language, Integer minimum, Integer maximum, String message, String... formats) throws IllegalInterruptedException {
        while (true) {
            try {
                Integer userInput = collectInteger(language, message, formats);
                if (userInput >= minimum && userInput <= maximum) {
                    return userInput;
                }
                throw new ArithmeticException();
            } catch (ArithmeticException e) {
                logger.displayError(language, "\ninput_must_be_int_in_range", minimum.toString(), maximum.toString());
            }
        }
    }

    private Integer collectInteger(Language language, String message, String... formats) throws IllegalInterruptedException {
        while (true) {
            try {
                Scanner userInput = getMessage(language, message, formats);
                String input = userInput.nextLine();
                if (!input.toUpperCase().equals("Q")) {
                    return Integer.parseInt(input);
                } else throw new IllegalInterruptedException(oxBundle(language, "user_interrupted"));
            } catch (NumberFormatException e) {
                logger.displayError(language, "\ninput_must_be_int");
            }
        }
    }


    public Language collectProperLanguage(String message, String... formats) throws IllegalInterruptedException {
        while (true) {
            try {
                return Language.languageMatcher(collectString(Language.ENGLISH, message, formats));
            } catch (IllegalLanguageException e) {
                logger.displayError(Language.ENGLISH, "\nlanguage_not_exist");
            }
        }
    }

//    public Sign collectProperSign(Language language, String message, String... formats) throws IllegalInterruptedException {
//        while (true) {
//            try {
//                return Sign.signMatcher(collectString(language, message, formats));
//            } catch (IllegalSignException e) {
//                logger.displayError(language, "input_must_be_o_x");
//            }
//
//        }
//    }

    public String collectString(Language language, String message, String... formats) throws IllegalInterruptedException {
        while (true) {
            try {
                Scanner userInput = getMessage(language, message, formats);
                String input = userInput.nextLine();
                if (!input.toUpperCase().equals("Q")) {
                    return input;
                } else throw new IllegalInterruptedException(oxBundle(language, "user_interrupted"));
            } catch (IllegalArgumentException e) {
                logger.displayError(language, "\ninput_must_be_string");
            }
        }
    }

    private Scanner getMessage(Language language, String message, String... formats) {
        logger.display(language, message, formats);
        return userInput;
    }
}
