package com.trzewik.OX.settings;

import com.trzewik.OX.inputProvider.IllegalInterruptedException;
import com.trzewik.OX.inputProvider.Language;
import com.trzewik.OX.inputProvider.MessagePrinter;
import com.trzewik.OX.inputProvider.UserInputProvider;

/**
 * @author Agnieszka Trzewik
 */
class SettingsUpdater {


    private Settings settings = Settings.getInstance();
    private Language language = settings.getLanguage();

    SettingsUpdater() throws IllegalInterruptedException {
    }

    void updateSettings() throws IllegalInterruptedException {
        MessagePrinter.printMessageInNewLine(language, "columns", Integer.toString(settings.getGameSettings("numberOfColumns")));
        MessagePrinter.printMessageInNewLine(language, "rows", Integer.toString(settings.getGameSettings("numberOfRows")));
        MessagePrinter.printMessageInNewLine(language, "winning", Integer.toString(settings.getGameSettings("winningCondition")));
        MessagePrinter.printMessageInNewLine(language, "language_to_choose", settings.getLanguage().toString().toLowerCase());
        MessagePrinter.printMessageInNewLine(language, "press_q");
        if (UserInputProvider.collectString(language, "change_settings").toLowerCase().equals("y")) {
            int parameter = UserInputProvider.collectIntegerInRangeMinMax(language, 1, 4, "chane_one_setting");
            switch (parameter) {
                case 1:
                    settings.setGameSettings("numberOfColumns");
                    updateSettings();
                    break;
                case 2:
                    settings.setGameSettings("numberOfRows");
                    updateSettings();
                    break;
                case 3:
                    settings.setGameSettings("winningCondition");
                    updateSettings();
                    break;
                case 4:
                    settings.changeLanguage();
                default:
                    updateSettings();
            }
        }
    }
}
