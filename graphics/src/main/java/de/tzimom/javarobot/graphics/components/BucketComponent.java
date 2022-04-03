package de.tzimom.javarobot.graphics.components;

import de.tzimom.javarobot.entities.Bucket;
import de.tzimom.javarobot.graphics.RobotView;
import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicGraphics;
import de.tzimom.javarobot.graphics.rendering.GraphicsComponent;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.TextRenderer;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.shapes.DynamicPolygon;

import java.awt.Color;
import java.util.ArrayList;
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
        double relativeAngleRadians = bucket.getConfig().relativeAngle() * Math.PI / 180;
        double angleErrorMarginRadians = bucket.getConfig().angleErrorMargin() * Math.PI / 180;

        float centerX = (float) Math.sin(relativeAngleRadians) * RobotView.ARM_LENGTH;
        float leftX = (float) Math.sin(relativeAngleRadians - angleErrorMarginRadians) * RobotView.ARM_LENGTH;
        float rightX = (float) Math.sin(relativeAngleRadians + angleErrorMarginRadians) * RobotView.ARM_LENGTH;

        float bucketWidth = Math.abs(leftX - rightX);
        float bucketRadius = bucketWidth / 2;

        float relativeY = RobotView.ARM_LENGTH * (1 + ARM_DISTANCE_FACTOR);

        Vector2f location = RobotView.ANCHOR_POINT.add(new Vector2f(centerX, relativeY));

        List<Vector2f> points = new ArrayList<>();

        points.add(new Vector2f(-bucketRadius, 0f));
        points.add(new Vector2f(+bucketRadius, 0f));
        points.add(new Vector2f(+bucketRadius, HEIGHT));
        points.add(new Vector2f(-bucketRadius, HEIGHT));

        graphics.fill(new DynamicPolygon(points), location, bucket.getConfig().color().getValue());

        String numberText = bucket.getBallCount().toString();
        Vector2f numberLocation = location.add(new Vector2f(0f, HEIGHT * 0.5f));

        Color textColor = bucket.getConfig().color().isBright() ? Color.DARK_GRAY : Color.WHITE;

        graphics.drawText(new TextRenderer(numberText, numberLocation, bucketWidth * 0.5f, RobotView.FONT_INFO, textColor));
    }
}
