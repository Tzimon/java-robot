package de.tzimom.javarobot.assistant;

import de.tzimom.javarobot.services.BucketService;
import de.tzimom.javarobot.services.ConveyorBeltService;

public class RobotServiceCollection {
    private final ConveyorBeltService conveyorBeltService;
    private final BucketService bucketService;

    public RobotServiceCollection(ConveyorBeltService conveyorBeltService, BucketService bucketService) {
        this.conveyorBeltService = conveyorBeltService;
        this.bucketService = bucketService;
    }

    public ConveyorBeltService getConveyorBeltService() {
        return conveyorBeltService;
    }

    public BucketService getBucketService() {
        return bucketService;
    }
}
