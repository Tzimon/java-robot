package de.tzimom.javarobot.graphics.config;

import de.tzimom.javarobot.entities.Robot;
import de.tzimom.javarobot.graphics.rendering.config.DisplayConfig;
import de.tzimom.javarobot.repositories.bucket.BucketRepository;
import de.tzimom.javarobot.repositories.conveyorbelt.ConveyorBeltRepository;

/**
 * Describes how a robot view should be created
 */
public final class RobotViewConfig {
    private final DisplayConfig displayConfig;
    private final Robot robot;
    private final ConveyorBeltRepository conveyorBeltRepository;
    private final BucketRepository bucketRepository;

    /**
     * @param displayConfig The config used to initialized the display (= window)
     * @param robot The robot that is displayed
     * @param conveyorBeltRepository The conveyor belt repository used by the robot
     * @param bucketRepository The bucket repository used by the robot
     */
    public RobotViewConfig(DisplayConfig displayConfig, Robot robot, ConveyorBeltRepository conveyorBeltRepository, BucketRepository bucketRepository) {
        this.displayConfig = displayConfig;
        this.robot = robot;
        this.conveyorBeltRepository = conveyorBeltRepository;
        this.bucketRepository = bucketRepository;
    }

    public DisplayConfig displayConfig() {
        return displayConfig;
    }

    public Robot robot() {
        return robot;
    }

    public ConveyorBeltRepository conveyorBeltRepository() {
        return conveyorBeltRepository;
    }

    public BucketRepository bucketRepository() {
        return bucketRepository;
    }
}
