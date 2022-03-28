package de.tzimom.javarobot.entities;

import de.tzimom.javarobot.api.Ball;
import de.tzimom.javarobot.api.Bucket;
import de.tzimom.javarobot.api.ConveyorBelt;
import de.tzimom.javarobot.api.Robot;
import de.tzimom.javarobot.config.RobotConfig;
import de.tzimom.javarobot.exceptions.IllegalRobotStateException;
import de.tzimom.javarobot.repositories.BallRepository;
import de.tzimom.javarobot.repositories.RandomBallRepository;
import de.tzimom.javarobot.services.BucketService;
import de.tzimom.javarobot.services.ConveyorBeltService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BasicRobot implements Robot {
    private final ConveyorBeltService conveyorBeltService;
    private final BucketService bucketService;

    private Ball currentBall = null;
    private float currentAngle = 0f;

    public BasicRobot(ConveyorBeltService conveyorBeltService, BucketService bucketService) {
        this.conveyorBeltService = conveyorBeltService;
        this.bucketService = bucketService;
    }

    public static Robot fromConfig(RobotConfig config) {
        var ballRepository = config.ballRepository();

        List<ConveyorBelt> conveyorBelts = config.conveyorBelts().stream()
                .map(conveyorBeltConfig -> new BasicConveyorBelt(ballRepository, conveyorBeltConfig))
                .collect(Collectors.toList());

        List<Bucket> buckets = config.buckets().stream()
                .map(BasicBucket::new)
                .collect(Collectors.toList());

        var conveyorBeltService = new ConveyorBeltService(conveyorBelts);
        var bucketService = new BucketService(buckets);

        return new BasicRobot(conveyorBeltService, bucketService);
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
