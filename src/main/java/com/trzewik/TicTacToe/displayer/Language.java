package com.trzewik.TicTacToe;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Agnieszka Trzewik
 */
public enum Language {

    ENGLISH(ResourceBundle.getBundle("TicTacToe", new Locale("en", "EN"))),
    POLISH(ResourceBundle.getBundle("TicTacToe", new Locale("pl", "PL")));

    private final ResourceBundle resourceBundle;

    Language(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public static String oxBundle(Language language, String message) {
        return language.resourceBundle.getString(message);
    }

    public static String oxBundle(String message) {
        return oxBundle(Language.POLISH, message);
    }

    public static Language languageMatcher(String userInput) throws IllegalLanguageException {
        for (Language language : values()) {
            if (language.name().equals(userInput.toUpperCase())) {
                return language;
            }
        }
        throw new IllegalLanguageException(oxBundle(POLISH, "wrong_language"));
//        Settings.getInstance().getLanguage()
    }

}
