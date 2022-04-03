package de.tzimom.javarobot.graphics.rendering;

import de.tzimom.javarobot.graphics.rendering.config.DisplayConfig;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

public abstract class View {
    private final Display display;
    
    private final Collection<GraphicsComponent> components = new ArrayList<>();

    public View(Color backgroundColor, DisplayConfig displayConfig) {
        display = new Display(backgroundColor, displayConfig);
    }

    public void startRendering(int fps) {
        new Thread(() -> {
            long lastFrame = System.nanoTime();

            while (true) {
                long now = System.nanoTime();
                long deltaTime = now - lastFrame;

                if (deltaTime < 1000 / fps) continue;

                try {
                    update();
                    lastFrame = now;
                } catch (IllegalStateException ignored) {}
            }
        }).start();
    }

    protected void addComponent(GraphicsComponent component) {
        components.add(component);
    }

    public void update() {
        display.update(components);
    }
}
