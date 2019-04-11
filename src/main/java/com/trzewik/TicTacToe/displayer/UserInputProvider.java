package com.trzewik.TicTacToe.displayer;

import java.util.Scanner;

import static com.trzewik.TicTacToe.displayer.Language.oxBundle;

/**
 * @author Agnieszka Trzewik
 */
public class UserInputProvider {

    private Logger logger;
    private Scanner userInput;

    private UserInputProvider(Scanner userInput, Logger logger) {
        this.userInput = userInput;
        this.logger = logger;
    }

    public static UserInputProvider userInputProvider(Scanner userInput, Logger logger) {
        return new UserInputProvider(userInput, logger);
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
                logger.displayError(language, "input_must_be_int_in_range", minimum.toString(), maximum.toString());
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
                logger.displayError(language, "input_must_be_int");
            }
        }
    }


    public Language collectProperLanguage(Language language, String message, String... formats) throws IllegalInterruptedException {
        while (true) {
            try {
                return Language.languageMatcher(collectString(language, message, formats), Language.POLISH);
            } catch (IllegalLanguageException e) {
                logger.displayError(language, "language_not_exist");
            }
        }
    }

    public String collectString(Language language, String message, String... formats) throws IllegalInterruptedException {
        while (true) {
            try {
                Scanner userInput = getMessage(language, message, formats);
                String input = userInput.nextLine();
                if (!input.toUpperCase().equals("Q")) {
                    return input;
                } else throw new IllegalInterruptedException(oxBundle(language, "user_interrupted"));
            } catch (IllegalArgumentException e) {
                logger.displayError(language, "input_must_be_string");
            }
        }
    }

    private Scanner getMessage(Language language, String message, String... formats) {
        logger.display(language, message, formats);
        return userInput;
    }
}
