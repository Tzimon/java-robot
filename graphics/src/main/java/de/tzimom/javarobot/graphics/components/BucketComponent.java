package de.tzimom.javarobot.graphics.components;

import de.tzimom.javarobot.entities.Bucket;
import de.tzimom.javarobot.graphics.RobotView;
import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicGraphics;
import de.tzimom.javarobot.graphics.rendering.GraphicsComponent;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.TextRenderer;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.shapes.DynamicPolygon;

import java.awt.Color;
import java.util.List;

public class BucketComponent implements GraphicsComponent {
    private static final float HEIGHT = 0.15f;
    private static final float ARM_DISTANCE_FACTOR = 0.5f;

    private static final Color COLOR = new Color(250, 220, 120);

    private final Bucket bucket;

    public BucketComponent(Bucket bucket) {
        this.bucket = bucket;
    }

    public void render(int width, int height, DynamicGraphics graphics) {
        var relativeAngleRadians = bucket.getConfig().relativeAngle() * Math.PI / 180;
        var angleErrorMarginRadians = bucket.getConfig().angleErrorMargin() * Math.PI / 180;

        var centerX = (float) Math.sin(relativeAngleRadians) * RobotView.ARM_LENGTH;
        var leftX = (float) Math.sin(relativeAngleRadians - angleErrorMarginRadians) * RobotView.ARM_LENGTH;
        var rightX = (float) Math.sin(relativeAngleRadians + angleErrorMarginRadians) * RobotView.ARM_LENGTH;

        var bucketWidth = Math.abs(leftX - rightX);
        var bucketRadius = bucketWidth / 2;

        var relativeY = RobotView.ARM_LENGTH * (1 + ARM_DISTANCE_FACTOR);

        var location = RobotView.ANCHOR_POINT.add(new Vector2f(centerX, relativeY));

        var points = List.of(
                new Vector2f(-bucketRadius, 0f),
                new Vector2f(+bucketRadius, 0f),
                new Vector2f(+bucketRadius, HEIGHT),
                new Vector2f(-bucketRadius, HEIGHT)
        );

        graphics.fill(new DynamicPolygon(points), location, bucket.getConfig().color().getValue());

        var numberText = bucket.getBallCount().toString();
        var numberLocation = location.add(new Vector2f(0f, HEIGHT * 0.5f));

        var textColor = bucket.getConfig().color().isBright() ? Color.DARK_GRAY : Color.WHITE;

        graphics.drawText(new TextRenderer(numberText, numberLocation, bucketWidth * 0.5f, RobotView.FONT_INFO, textColor));
    }
}
