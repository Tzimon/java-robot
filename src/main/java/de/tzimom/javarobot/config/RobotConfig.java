package de.tzimom.javarobot.config;

import de.tzimom.javarobot.repositories.ball.BallRepository;
import de.tzimom.javarobot.repositories.bucket.BucketRepository;
import de.tzimom.javarobot.repositories.conveyorbelt.ConveyorBeltRepository;

/**
 * Describes how the robot should be created
 */
public final class RobotConfig {
    private final BallRepository ballRepository;
    private final ConveyorBeltRepository conveyorBeltRepository;
    private final BucketRepository bucketRepository;

    /**
     * @param ballRepository The ball repository the robot uses
     * @param conveyorBeltRepository The repository of conveyor belts the robot uses
     * @param bucketRepository The repository of buckets the robot uses
     */
    public RobotConfig(BallRepository ballRepository, ConveyorBeltRepository conveyorBeltRepository, BucketRepository bucketRepository) {
        this.ballRepository = ballRepository;
        this.conveyorBeltRepository = conveyorBeltRepository;
        this.bucketRepository = bucketRepository;
    }

    public BallRepository ballRepository() {
        return ballRepository;
    }

    public ConveyorBeltRepository conveyorBeltRepository() {
        return conveyorBeltRepository;
    }

    public BucketRepository bucketRepository() {
        return bucketRepository;
    }
}
