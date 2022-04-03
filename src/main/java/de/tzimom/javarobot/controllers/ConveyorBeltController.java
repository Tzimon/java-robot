package de.tzimom.javarobot.controllers;

import de.tzimom.javarobot.entities.Ball;
import de.tzimom.javarobot.entities.ConveyorBelt;
import de.tzimom.javarobot.config.ConveyorBeltConfig;
import de.tzimom.javarobot.repositories.ball.BallRepository;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Queue;

public class ConveyorBeltController implements ConveyorBelt {
    private final ConveyorBeltConfig config;
    private final BallRepository ballRepository;

    private final Queue<Ball> queuedBalls;

    private BigInteger currentNumber = BigInteger.valueOf(0);

    public ConveyorBeltController(ConveyorBeltConfig config, BallRepository ballRepository) {
        this.config = config;
        this.ballRepository = ballRepository;

        queuedBalls = new ArrayDeque<>(config.queueLength());

        for (int i = 0; i < config.queueLength(); i++) appendNewBall();
    }

    private void appendNewBall() {
        currentNumber = currentNumber.add(BigInteger.valueOf(1));
        queuedBalls.offer(ballRepository.createNewBall(currentNumber));
    }

    public Ball takeBall() {
        Ball ball = queuedBalls.poll();
        appendNewBall();

        return ball;
    }

    public Queue<Ball> getBallQueue() {
        return queuedBalls;
    }

    public ConveyorBeltConfig getConfig() {
        return config;
    }
}
