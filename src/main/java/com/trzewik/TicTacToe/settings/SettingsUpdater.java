package com.trzewik.TicTacToe.settings;

import com.trzewik.TicTacToe.displayer.IllegalInterruptedException;
import com.trzewik.TicTacToe.displayer.Language;
import com.trzewik.TicTacToe.displayer.Logger;
import com.trzewik.TicTacToe.displayer.UserInputProvider;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Agnieszka Trzewik
 */
class SettingsUpdater {

    void changeSettingsIfUserWant(Scanner scanner, Logger logger, Language language, GameSettings gameSettings) throws IllegalInterruptedException {
        logger.display(language, "settings");
        for (Map.Entry<String, String> keys : gameSettings.getSettings().entrySet()) {
            logger.display(language, keys.getKey(), keys.getValue());
        }
        logger.display(language, "press_q");
        if (UserInputProvider.userInputProvider(scanner, logger).collectString(language, "change_settings").toLowerCase().equals("y")) {
            gameSettings.setSettings(scanner);
        }
    }
}
