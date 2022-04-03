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

    protected void addComponent(GraphicsComponent component) {
        components.add(component);
    }

    public void update() {
        display.update(components);
    }
}
