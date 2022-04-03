package de.tzimom.javarobot.graphics.rendering.dynamic;

import de.tzimom.javarobot.graphics.rendering.dynamic.elements.ShapeRenderer;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.TextRenderer;

import java.awt.Color;
import java.awt.Graphics2D;

public class DynamicGraphics {
    private final Graphics2D graphics;
    private final int scale;

    public DynamicGraphics(Graphics2D graphics, int scale) {
        this.graphics = graphics;
        this.scale = scale;
    }

    public void draw(DynamicShape shape, Vector2f location, Color color) {
        new ShapeRenderer(shape, location, color, false).render(graphics, scale);
    }

    public void fill(DynamicShape shape, Vector2f location, Color color) {
        new ShapeRenderer(shape, location, color, true).render(graphics, scale);
    }

    public void drawText(TextRenderer textRenderer) {
        textRenderer.render(graphics, scale);
    }
}
