package de.tzimom.javarobot.repositories;

import de.tzimom.javarobot.api.Ball;
import de.tzimom.javarobot.api.BallColor;
import de.tzimom.javarobot.entities.BasicBall;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class RandomBallRepository implements BallRepository {
    private final Random random;
    private final List<BallColor> ballColors;

    public RandomBallRepository(List<BallColor> ballColors) {
        this(new Random(), ballColors);
    }

    public RandomBallRepository(Random random, List<BallColor> ballColors) {
        this.random = random;
        this.ballColors = ballColors;
    }

    public Ball createNewBall(BigInteger number) {
        return new BasicBall(number, ballColors.get(random.nextInt(ballColors.size())));
    }
}
