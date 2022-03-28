package de.tzimom.javarobot.graphics.config;

import javax.swing.*;
import java.awt.*;

/**
 * Describes how the display for the robot should be initialized
 *
 * @param size The initial size of the window
 * @param title The title of the window
 * @param defaultCloseOperation The default close operation of the window
 */
public record DisplayConfig(Dimension size, String title, CloseOperation defaultCloseOperation) {
    public enum CloseOperation {
        DO_NOTHING(WindowConstants.DO_NOTHING_ON_CLOSE),
        HIDE(WindowConstants.HIDE_ON_CLOSE),
        DISPOSE(WindowConstants.DISPOSE_ON_CLOSE),
        EXIT(WindowConstants.EXIT_ON_CLOSE);

        private final int value;

        CloseOperation(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
