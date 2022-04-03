package de.tzimom.javarobot.graphics.rendering.config;

import javax.swing.WindowConstants;
import java.awt.Dimension;

/**
 * Describes how the display for the robot should be initialized
 */
public final class DisplayConfig {
    private final Dimension size;
    private final String title;
    private final CloseOperation defaultCloseOperation;

    /**
     * @param size The initial size of the window
     * @param title The title of the window
     * @param defaultCloseOperation The default close operation of the window
     *
     * @see WindowConstants Magic constants for the close operation
     */
    public DisplayConfig(Dimension size, String title, CloseOperation defaultCloseOperation) {
        this.size = size;
        this.title = title;
        this.defaultCloseOperation = defaultCloseOperation;
    }

    public Dimension size() {
        return size;
    }

    public String title() {
        return title;
    }

    public CloseOperation defaultCloseOperation() {
        return defaultCloseOperation;
    }

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
