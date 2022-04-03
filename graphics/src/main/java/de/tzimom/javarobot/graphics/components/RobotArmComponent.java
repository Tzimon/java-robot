package de.tzimom.javarobot.graphics.components;

import de.tzimom.javarobot.entities.Robot;
import de.tzimom.javarobot.graphics.RobotView;
import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicGraphics;
import de.tzimom.javarobot.graphics.rendering.GraphicsComponent;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.shapes.DynamicEllipse;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.shapes.DynamicPolygon;

import java.awt.Color;
import java.util.List;

public class RobotArmComponent implements GraphicsComponent {
    private static final Color COLOR = new Color(200, 200, 200);

    private static final float ARM_WIDTH = 0.0225f;
    private static final float HALF_ARM_WIDTH = ARM_WIDTH / 2;

    private final Robot robot;

    public RobotArmComponent(Robot robot) {
        this.robot = robot;
    }

    public void render(int width, int height, DynamicGraphics graphics) {
        var currentAngleRadians = robot.getCurrentAngle() * Math.PI / 180;

        var normalized = new Vector2f((float) Math.sin(currentAngleRadians), (float) Math.cos(currentAngleRadians));
        var centerEnd = normalized.multiply(RobotView.ARM_LENGTH);

        var points = List.of(
                new Vector2f(-normalized.y(), +normalized.x()).multiply(HALF_ARM_WIDTH),
                new Vector2f(+normalized.y(), -normalized.x()).multiply(HALF_ARM_WIDTH),
                new Vector2f(+normalized.y(), -normalized.x()).multiply(HALF_ARM_WIDTH).add(centerEnd),
                new Vector2f(-normalized.y(), +normalized.x()).multiply(HALF_ARM_WIDTH).add(centerEnd)
        );

        graphics.fill(new DynamicPolygon(points), RobotView.ANCHOR_POINT, COLOR);

        var currentBall = robot.getCurrentBall();
        var gripRadius = RobotView.BALL_RADIUS;

        if (currentBall.isEmpty()) gripRadius *= RobotView.OPEN_GRIP_FACTOR;

        var gripLocation = RobotView.ANCHOR_POINT.add(centerEnd).add(normalized.subtract(new Vector2f(1)).multiply(gripRadius));

        currentBall.ifPresent(ball -> RobotView.renderBall(graphics, ball, gripLocation));

        graphics.draw(new DynamicEllipse(new Vector2f(gripRadius * 2f)), gripLocation, COLOR);
        graphics.fill(new DynamicEllipse(new Vector2f(ARM_WIDTH * 2f)), RobotView.ANCHOR_POINT.subtract(new Vector2f(ARM_WIDTH)), COLOR);
    }
}
