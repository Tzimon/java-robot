package de.tzimom.javarobot.graphics.config;

import de.tzimom.javarobot.entities.Robot;
import de.tzimom.javarobot.graphics.rendering.config.DisplayConfig;
import de.tzimom.javarobot.repositories.bucket.BucketRepository;
import de.tzimom.javarobot.repositories.conveyorbelt.ConveyorBeltRepository;

/**
 * Describes how a robot view should be created
 *
 * @param displayConfig The config used to initialized the display (= window)
 * @param robot The robot that is displayed
 * @param conveyorBeltRepository The conveyor belt repository used by the robot
 * @param bucketRepository The bucket repository used by the robot
 */
public record RobotViewConfig(DisplayConfig displayConfig, Robot robot, ConveyorBeltRepository conveyorBeltRepository, BucketRepository bucketRepository) { }
