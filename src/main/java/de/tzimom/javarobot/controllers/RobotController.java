package de.tzimom.javarobot.controllers;

import de.tzimom.javarobot.entities.Ball;
import de.tzimom.javarobot.entities.Bucket;
import de.tzimom.javarobot.entities.Robot;
import de.tzimom.javarobot.exceptions.IllegalRobotStateException;
import de.tzimom.javarobot.services.BucketService;
import de.tzimom.javarobot.services.ConveyorBeltService;

import java.util.Optional;

public class RobotController implements Robot {
    private final ConveyorBeltService conveyorBeltService;
    private final BucketService bucketService;

    private Ball currentBall = null;
    private float currentAngle = 0f;

    public RobotController(ConveyorBeltService conveyorBeltService, BucketService bucketService) {
        this.conveyorBeltService = conveyorBeltService;
        this.bucketService = bucketService;
    }

    public Optional<Ball> grabBall() {
        if (currentBall != null) throw new IllegalRobotStateException(this);

        var ball = conveyorBeltService.provideBall(currentAngle);
        currentBall = ball.orElse(null);

        return ball;
    }

    public Optional<Bucket> dropBall() {
        if (currentBall == null) throw new IllegalRobotStateException(this);
        currentBall = null;

        return bucketService.dropBall(currentAngle);
    }

    public void turnTo(float angle) {
        currentAngle = angle;
    }

    public Optional<Ball> getCurrentBall() {
        return Optional.ofNullable(currentBall);
    }

    public float getCurrentAngle() {
        return currentAngle;
    }
}
