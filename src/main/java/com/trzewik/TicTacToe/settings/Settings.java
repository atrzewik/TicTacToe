package com.trzewik.OX.settings;


import com.trzewik.OX.inputProvider.IllegalInterruptedException;
import com.trzewik.OX.inputProvider.Language;

/**
 * @author Agnieszka Trzewik
 */
public class Settings {


    private static Settings instance = null;
    private GameSettings gameSettings;
    private Language language;

    private Settings(GameSettings gameSettings, Language language) {
        this.gameSettings = gameSettings;
        this.language = language;
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings(new GameSettings(), Language.ENGLISH);
        }
        return Settings.instance;
    }

    public void updateSettings() throws IllegalInterruptedException {
        new SettingsUpdater().updateSettings();
    }

    public Language getLanguage() {
        return language;
    }

    public void changeLanguage() throws IllegalInterruptedException {
        language = Language.chooseLanguage();
    }

    public void setGameSettings(String parameter) throws IllegalInterruptedException {
        gameSettings.setSettings(parameter, language);
    }

    public int getGameSettings(String parameter) {
        return gameSettings.getSettings(parameter);
    }
}
