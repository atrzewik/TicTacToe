package com.trzewik.TicTacToe.settings;

import com.trzewik.TicTacToe.displayer.IllegalInterruptedException;
import com.trzewik.TicTacToe.displayer.Language;
import com.trzewik.TicTacToe.displayer.Logger;

import java.util.Scanner;

/**
 * @author Agnieszka Trzewik
 */
public class Settings {

    private Logger logger;
    private GameSettings gameSettings;
    private Language language;

    public Settings(Logger logger) {
        this.logger = logger;
        this.gameSettings = GameSettings.gameSettings(logger);
        this.language = gameSettings.getLanguage();
    }

    public void changeSettingsIfUserWant(Scanner scanner) throws IllegalInterruptedException {
        new SettingsUpdater().changeSettingsIfUserWant(scanner, logger, language, gameSettings);
    }

    public int getGameSettings(String parameter) {
        return gameSettings.getSettings(parameter);
    }

    public Logger getLogger() {
        return logger;
    }

    public Language getLanguage() {
        return language;
    }
}
