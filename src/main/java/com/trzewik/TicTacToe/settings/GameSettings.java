package com.trzewik.TicTacToe;


import com.trzewik.TicTacToe.displayer.IllegalLanguageException;
import com.trzewik.TicTacToe.displayer.Language;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Agnieszka Trzewik
 */
public class GameSettings {

    private int numberOfColumns;
    private int numberOfRows;
    private int winningCondition;
    private Language language;
    private Map<String, String> settings;

    public static void main(String[] args) {
        GameSettings game = new GameSettings();
        game.assignChosenSettings();
        System.out.println(game.numberOfColumns);
        System.out.println(game.numberOfRows);
        System.out.println(game.winningCondition);
        System.out.println(game.language);
        System.out.println(game.settings.entrySet());
    }

    public void assignChosenSettings() {

        Properties properties = new Properties();


        try (InputStream file = new FileInputStream("./src/main/resources/game_settings.properties")){

            properties.load(file);

            settings = new HashMap<>();
            for(Map.Entry<Object, Object> e : properties.entrySet()) {
                settings.put(e.getKey().toString(), e.getValue().toString());
            }

            numberOfColumns = Integer.parseInt(properties.getProperty("number_of_columns"));
            numberOfRows = Integer.parseInt(properties.getProperty("number_of_rows"));
            winningCondition = Integer.parseInt(properties.getProperty("winning_condition"));
            language = Language.languageMatcher(properties.getProperty("language"));

        } catch (IOException | IllegalLanguageException ex) {
            ex.printStackTrace();
        }
    }
}
