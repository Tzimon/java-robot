package de.tzimom.javarobot.graphics.rendering;

import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicGraphics;

public interface GraphicsComponent {
    void render(int width, int height, DynamicGraphics graphics);
}
