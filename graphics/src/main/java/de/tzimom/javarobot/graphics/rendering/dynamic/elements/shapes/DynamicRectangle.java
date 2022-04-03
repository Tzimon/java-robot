package de.tzimom.javarobot.graphics.rendering.dynamic.elements.shapes;

import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicShape;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class DynamicRectangle implements DynamicShape {
    private final Vector2f size;

    public DynamicRectangle(Vector2f size) {
        this.size = size;
    }

    public Shape scale(int scale) {
        Vector2f scaledSize = size.multiply(scale);
        return new Rectangle2D.Float(0f, 0f, scaledSize.x(), scaledSize.y());
    }
}
