package com.trzewik.OX;

import com.trzewik.OX.inputProvider.BundleProvider;
import com.trzewik.OX.inputProvider.IllegalInterruptedException;
import com.trzewik.OX.settings.Settings;

/**
 * @author Agnieszka Trzewik
 */
public enum Sign {
    EMPTY,
    O,
    X;

    public static Sign signMatcher(String userInput) throws IllegalSignException, IllegalInterruptedException {
        for (Sign sign : values()) {
            if (sign.name().equals(userInput.toUpperCase())) {
                return sign;
            }
        }
        throw new IllegalSignException(BundleProvider.oxBundle(Settings.getInstance().getLanguage(), "wrong_sign"));
    }
}
