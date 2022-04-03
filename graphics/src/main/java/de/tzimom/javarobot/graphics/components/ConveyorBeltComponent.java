package de.tzimom.javarobot.graphics.components;

import de.tzimom.javarobot.entities.Ball;
import de.tzimom.javarobot.entities.ConveyorBelt;
import de.tzimom.javarobot.graphics.RobotView;
import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicGraphics;
import de.tzimom.javarobot.graphics.rendering.GraphicsComponent;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.shapes.DynamicRectangle;

import java.awt.*;
import java.util.Queue;

public class ConveyorBeltComponent implements GraphicsComponent {
    private static final Color COLOR = new Color(230, 230, 80);

    private static final float CONVEYOR_BELT_WIDTH = 0.02f;
    private static final float BALL_SEPARATION_FACTOR = 0.1f;

    private final ConveyorBelt conveyorBelt;

    public ConveyorBeltComponent(ConveyorBelt conveyorBelt) {
        this.conveyorBelt = conveyorBelt;
    }

    public void render(int width, int height, DynamicGraphics graphics) {
        float spacedBallWidth = RobotView.BALL_DIAMETER * (1 + BALL_SEPARATION_FACTOR);

        double relativeAngleRadians = conveyorBelt.getConfig().relativeAngle() * Math.PI / 180;
        boolean leftSided = (conveyorBelt.getConfig().relativeAngle() + 360) % 360 > 180;

        Vector2f normalized = new Vector2f((float) Math.sin(relativeAngleRadians), (float) Math.cos(relativeAngleRadians));
        Vector2f relativeLocation = normalized.multiply(RobotView.ARM_LENGTH + RobotView.BALL_RADIUS * RobotView.OPEN_GRIP_FACTOR).subtract(new Vector2f(RobotView.BALL_RADIUS));
        Vector2f location = RobotView.ANCHOR_POINT.add(relativeLocation);

        Queue<Ball> balls = conveyorBelt.getBallQueue();
        float ballSpacing = leftSided ? -spacedBallWidth : spacedBallWidth;

        int index = 0;

        for (Ball ball : balls) {
            RobotView.renderBall(graphics, ball, location.add(new Vector2f(index++ * ballSpacing, 0f)));
        }

        float length = conveyorBelt.getConfig().queueLength() * spacedBallWidth;
        Vector2f locationOffset = new Vector2f(leftSided ? -(conveyorBelt.getConfig().queueLength() - 1) * spacedBallWidth : 0, RobotView.BALL_DIAMETER);

        graphics.fill(new DynamicRectangle(new Vector2f(length, CONVEYOR_BELT_WIDTH)), location.add(locationOffset), COLOR);
    }
}
