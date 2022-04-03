package de.tzimom.javarobot.graphics;

import de.tzimom.javarobot.entities.Ball;
import de.tzimom.javarobot.entities.Robot;
import de.tzimom.javarobot.graphics.components.BucketComponent;
import de.tzimom.javarobot.graphics.components.ConveyorBeltComponent;
import de.tzimom.javarobot.graphics.components.RobotArmComponent;
import de.tzimom.javarobot.graphics.rendering.View;
import de.tzimom.javarobot.graphics.rendering.config.DisplayConfig;
import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicGraphics;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.TextRenderer;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.shapes.DynamicEllipse;
import de.tzimom.javarobot.repositories.bucket.BucketRepository;
import de.tzimom.javarobot.repositories.conveyorbelt.ConveyorBeltRepository;

import java.awt.Color;
import java.awt.Font;

public class RobotView extends View {
    private static final Color BACKGROUND_COLOR = new Color(50, 50, 50);

    public static final TextRenderer.FontInfo FONT_INFO = new TextRenderer.FontInfo("Courier", Font.BOLD, true);

    public static final Vector2f ANCHOR_POINT = new Vector2f(0.5f, 0.25f);
    public static final float OPEN_GRIP_FACTOR = 1.4f;

    public static final float ARM_LENGTH = 0.35f;

    public static final float BALL_RADIUS = 0.025f;
    public static final float BALL_DIAMETER = 2 * BALL_RADIUS;

    public RobotView(DisplayConfig displayConfig, ConveyorBeltRepository conveyorBeltRepository, BucketRepository bucketRepository, Robot robot) {
        super(BACKGROUND_COLOR, displayConfig);

        addComponent(new RobotArmComponent(robot));
        conveyorBeltRepository.getAllConveyorBelts().stream().map(ConveyorBeltComponent::new).forEach(this::addComponent);
        bucketRepository.getAllBuckets().stream().map(BucketComponent::new).forEach(this::addComponent);
    }

    public static void renderBall(DynamicGraphics graphics, Ball ball, Vector2f location) {
        graphics.fill(new DynamicEllipse(new Vector2f(BALL_DIAMETER)), location, ball.getColor().getValue());

        String numberText = ball.getNumber().toString();
        Vector2f numberLocation = location.add(new Vector2f(BALL_DIAMETER).multiply(0.5f));

        Color textColor = ball.getColor().isBright() ? Color.DARK_GRAY : Color.WHITE;

        graphics.drawText(new TextRenderer(numberText, numberLocation, BALL_RADIUS, FONT_INFO, textColor));
    }
}
