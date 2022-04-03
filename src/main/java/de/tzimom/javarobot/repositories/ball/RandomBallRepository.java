package de.tzimom.javarobot.repositories.ball;

import de.tzimom.javarobot.entities.Ball;
import de.tzimom.javarobot.entities.RenderColor;
import de.tzimom.javarobot.controllers.BallController;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RandomBallRepository implements BallRepository {
    private final Random random;
    private final List<RenderColor> ballColors;

    public RandomBallRepository(List<RenderColor> ballColors) {
        this(new Random(), ballColors);
    }

    public RandomBallRepository(Random random, List<RenderColor> ballColors) {
        this.random = random;
        this.ballColors = ballColors;
    }

    public Ball createNewBall(BigInteger number) {
        return new BallController(number, ballColors.get(random.nextInt(ballColors.size())));
    }
}
