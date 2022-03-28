package de.tzimom.javarobot.entities;

import de.tzimom.javarobot.api.Ball;
import de.tzimom.javarobot.api.ConveyorBelt;
import de.tzimom.javarobot.config.ConveyorBeltConfig;
import de.tzimom.javarobot.repositories.BallRepository;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Queue;

public class BasicConveyorBelt implements ConveyorBelt {
    private final BallRepository ballRepository;
    private final ConveyorBeltConfig config;

    private final Queue<Ball> queuedBalls;

    private BigInteger currentNumber = BigInteger.valueOf(0);

    public BasicConveyorBelt(BallRepository ballRepository, ConveyorBeltConfig config) {
        this.ballRepository = ballRepository;
        this.config = config;

        queuedBalls = createQueue(config.queueLength());
    }

    private Queue<Ball> createQueue(int queueLength) {
        var queue = new ArrayDeque<Ball>(queueLength);

        for (var i = 0; i < queueLength; i++) appendNewBall();

        return queue;
    }

    private void appendNewBall() {
        currentNumber = currentNumber.add(BigInteger.valueOf(1));
        queuedBalls.offer(ballRepository.createNewBall(currentNumber));
    }

    public Ball takeBall() {
        var ball = queuedBalls.poll();
        appendNewBall();

        return ball;
    }

    public ConveyorBeltConfig getConfig() {
        return config;
    }
}
