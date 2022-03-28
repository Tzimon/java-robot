package de.tzimom.javarobot.graphics;

import de.tzimom.javarobot.graphics.config.DisplayConfig;

import javax.swing.JFrame;
import java.awt.image.BufferStrategy;

public class Display {
    private final JFrame frame;
    private final BufferStrategy bufferStrategy;

    @SuppressWarnings("MagicConstant")
    public Display(DisplayConfig displayConfig) {
        frame = new JFrame(displayConfig.title());

        frame.setSize(displayConfig.size());
        frame.setDefaultCloseOperation(displayConfig.defaultCloseOperation().getValue());
        frame.setVisible(true);

        frame.createBufferStrategy(2);
        bufferStrategy = frame.getBufferStrategy();
    }
}
