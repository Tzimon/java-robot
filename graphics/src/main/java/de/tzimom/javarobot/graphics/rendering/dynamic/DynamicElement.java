package de.tzimom.javarobot.graphics.rendering.dynamic;

import java.awt.Graphics2D;

public interface DynamicElement {
    void render(Graphics2D graphics, int scale);
}
