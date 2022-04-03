package de.tzimom.javarobot.graphics;

import de.tzimom.javarobot.entities.Ball;
import de.tzimom.javarobot.graphics.components.BucketComponent;
import de.tzimom.javarobot.graphics.components.ConveyorBeltComponent;
import de.tzimom.javarobot.graphics.components.RobotArmComponent;
import de.tzimom.javarobot.graphics.config.RobotViewConfig;
import de.tzimom.javarobot.graphics.rendering.GraphicsComponent;
import de.tzimom.javarobot.graphics.rendering.View;
import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicGraphics;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.TextRenderer;
import de.tzimom.javarobot.graphics.rendering.dynamic.elements.shapes.DynamicEllipse;

import java.awt.Color;
import java.awt.Font;
import java.util.Collection;

public class RobotView extends View {
    private static final Color BACKGROUND_COLOR = new Color(50, 50, 50);

    public static final TextRenderer.FontInfo FONT_INFO = new TextRenderer.FontInfo("Courier", Font.BOLD, true);

    public static final Vector2f ANCHOR_POINT = new Vector2f(0.5f, 0.25f);
    public static final float OPEN_GRIP_FACTOR = 1.4f;

    public static final float ARM_LENGTH = 0.35f;

    public static final float BALL_RADIUS = 0.025f;
    public static final float BALL_DIAMETER = 2 * BALL_RADIUS;

    public RobotView(RobotViewConfig config) {
        super(BACKGROUND_COLOR, config.displayConfig());

        addComponent(new RobotArmComponent(config.robot()));
        config.conveyorBeltRepository().getAllConveyorBelts().stream().map(ConveyorBeltComponent::new).forEach(this::addComponent);
        config.bucketRepository().getAllBuckets().stream().map(BucketComponent::new).forEach(this::addComponent);
    }

    public void startRendering(int fps) {
        new Thread(() -> {
            var lastFrame = System.nanoTime();

            while (true) {
                var now = System.nanoTime();
                var deltaTime = now - lastFrame;

                if (deltaTime < 1000 / fps) continue;

                try {
                    update();
                    lastFrame = now;
                } catch (IllegalStateException ignored) {}
            }
        }).start();
    }

    public static void renderBall(DynamicGraphics graphics, Ball ball, Vector2f location) {
        graphics.fill(new DynamicEllipse(new Vector2f(BALL_DIAMETER)), location, ball.getColor().getValue());

        var numberText = ball.getNumber().toString();
        var numberLocation = location.add(new Vector2f(BALL_DIAMETER).multiply(0.5f));

        var textColor = ball.getColor().isBright() ? Color.DARK_GRAY : Color.WHITE;

        graphics.drawText(new TextRenderer(numberText, numberLocation, BALL_RADIUS, FONT_INFO, textColor));
    }
}
