package com.trzewik.TicTacToe.settings;


import com.trzewik.TicTacToe.displayer.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Agnieszka Trzewik
 */
class GameSettings {

    private int numberOfColumns;
    private int numberOfRows;
    private int winningCondition;
    private Language language;
    private Logger logger;
    private Map<String, String> settings;

    private GameSettings(Logger logger) {
        this.logger = logger;
        assignDefaultSettings();
    }

    static GameSettings gameSettings(Logger logger) {
        return new GameSettings(logger);
    }

    Map<String, String> getSettings() {
        return settings;
    }

    Language getLanguage() {
        return language;
    }

    int getSettings(String parameter) {
        switch (parameter) {
            case "number_of_columns":
                return numberOfColumns;
            case "number_of_rows":
                return numberOfRows;
            case "winning_condition":
                return winningCondition;
            default:
                throw new IllegalArgumentException("Wrong parameter");
        }
    }

    void setSettings(Scanner scanner) throws IllegalInterruptedException {
        UserInputProvider userInputProvider = UserInputProvider.userInputProvider(scanner, logger);

        numberOfColumns = userInputProvider.collectIntegerInRangeMinMax(language, 3, 100, "input_for_columns");
        settings.put("number_of_columns", Integer.toString(numberOfColumns));

        numberOfRows = userInputProvider.collectIntegerInRangeMinMax(language, 3, 100, "input_for_rows");
        settings.put("number_of_rows", Integer.toString(numberOfRows));

        int maximumOfWinningCondition = Math.min(numberOfColumns, numberOfRows);
        winningCondition = userInputProvider.collectIntegerInRangeMinMax(language, 3, maximumOfWinningCondition, "input_for_win_condition", Integer.toString(3), Integer.toString(maximumOfWinningCondition));
        settings.put("winning_condition", Integer.toString(winningCondition));

        language = userInputProvider.collectProperLanguage(language, "language_choice", Arrays.toString(Language.values()));
        settings.put("language", language.name());
    }

    private void assignDefaultSettings() {

        Properties properties = new Properties();

        try (InputStream file = new FileInputStream("./src/main/resources/game_settings.properties")) {

            properties.load(file);

            settings = new HashMap<>();
            for (Map.Entry<Object, Object> e : properties.entrySet()) {
                settings.put(e.getKey().toString(), e.getValue().toString());
            }

            numberOfColumns = Integer.parseInt(properties.getProperty("number_of_columns"));
            numberOfRows = Integer.parseInt(properties.getProperty("number_of_rows"));
            winningCondition = Integer.parseInt(properties.getProperty("winning_condition"));
            language = Language.languageMatcher(properties.getProperty("language"), Language.POLISH);

        } catch (IOException | IllegalLanguageException ex) {
            logger.displayError(language, "error");
        }
    }
}
