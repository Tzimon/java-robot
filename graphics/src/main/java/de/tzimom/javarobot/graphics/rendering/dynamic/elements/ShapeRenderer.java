package de.tzimom.javarobot.graphics.rendering.dynamic.elements;

import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicElement;
import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicShape;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;

import java.awt.Color;
import java.awt.Graphics2D;

public class ShapeRenderer implements DynamicElement {
    private final DynamicShape shape;
    private final Vector2f location;
    private final Color color;
    private final boolean fill;

    public ShapeRenderer(DynamicShape shape, Vector2f location, Color color, boolean fill) {
        this.shape = shape;
        this.location = location;
        this.color = color;
        this.fill = fill;
    }

    public void render(Graphics2D graphics, int scale) {
        var colorBefore = graphics.getColor();
        var translation = location.multiply(scale);

        graphics.setColor(color);
        graphics.translate(translation.x(), translation.y());

        var scaledShape = shape.scale(scale);

        if (fill) graphics.fill(scaledShape);
        else graphics.draw(scaledShape);

        graphics.setColor(colorBefore);
        graphics.translate(-translation.x(), -translation.y());
    }
}
