package de.tzimom.javarobot.assistant;

import de.tzimom.javarobot.config.BucketConfig;
import de.tzimom.javarobot.config.ConveyorBeltConfig;
import de.tzimom.javarobot.entities.RenderColor;
import de.tzimom.javarobot.repositories.ball.RandomBallRepository;
import org.junit.Test;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class AssistantTests {
    private enum RobotColor implements RenderColor {
        RED(Color.RED, false),
        GREEN(Color.GREEN, true),
        BLUE(Color.BLUE, false);

        private final Color value;
        private final boolean bright;

        RobotColor(Color value, boolean bright) {
            this.value = value;
            this.bright = bright;
        }

        public Color getValue() {
            return value;
        }
        public boolean isBright() {
            return bright;
        }
    }

    private static final List<RenderColor> ballColors = Arrays.asList(RobotColor.values());

    @Test
    public void testAssistant() {
        new VisualRobotBuilder(new RandomBallRepository(ballColors))
                .addBuckets(new BucketConfig(-10, 8, RobotColor.BLUE))
                .addBuckets(new BucketConfig(10, 8, RobotColor.BLUE))
                .addConveyorBelts(new ConveyorBeltConfig(10, 90))
                .addConveyorBelts(new ConveyorBeltConfig(10, -90))
                .build();
    }
}
