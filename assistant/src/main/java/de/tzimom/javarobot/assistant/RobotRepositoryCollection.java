package de.tzimom.javarobot.assistant;

import de.tzimom.javarobot.repositories.ball.BallRepository;
import de.tzimom.javarobot.repositories.bucket.BucketRepository;
import de.tzimom.javarobot.repositories.conveyorbelt.ConveyorBeltRepository;

public class RobotRepositoryCollection {
    private final BallRepository ballRepository;
    private final ConveyorBeltRepository conveyorBeltRepository;
    private final BucketRepository bucketRepository;

    public RobotRepositoryCollection(BallRepository ballRepository, ConveyorBeltRepository conveyorBeltRepository, BucketRepository bucketRepository) {
        this.ballRepository = ballRepository;
        this.conveyorBeltRepository = conveyorBeltRepository;
        this.bucketRepository = bucketRepository;
    }

    public BallRepository getBallRepository() {
        return ballRepository;
    }

    public BucketRepository getBucketRepository() {
        return bucketRepository;
    }

    public ConveyorBeltRepository getConveyorBeltRepository() {
        return conveyorBeltRepository;
    }
}
