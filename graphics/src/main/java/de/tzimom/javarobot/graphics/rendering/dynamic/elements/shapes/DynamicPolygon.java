package de.tzimom.javarobot.graphics.rendering.dynamic.elements.shapes;

import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicShape;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicPolygon implements DynamicShape {
    private final List<Vector2f> points;

    public DynamicPolygon(List<Vector2f> points) {
        this.points = points;
    }

    public Shape scale(int scale) {
        var scaledPoints = points.stream().map(point -> point.multiply(scale)).collect(Collectors.toList());

        var path = new Path2D.Float();
        if (scaledPoints.isEmpty()) return path;

        var firstPoint = scaledPoints.stream().findFirst().orElse(null);
        scaledPoints.remove(firstPoint);

        path.moveTo(firstPoint.x(), firstPoint.y());
        scaledPoints.forEach(point -> path.lineTo(point.x(), point.y()));
        path.closePath();

        return path;
    }
}
