package de.tzimom.javarobot.config;

import de.tzimom.javarobot.repositories.ball.BallRepository;
import de.tzimom.javarobot.repositories.bucket.BucketRepository;
import de.tzimom.javarobot.repositories.conveyorbelt.ConveyorBeltRepository;

/**
 * Describes how the robot should be created
 *
 * @param ballRepository The ball repository the robot uses
 * @param conveyorBeltRepository The repository of conveyor belts the robot uses
 * @param bucketRepository The repository of buckets the robot uses
 */
public record RobotConfig(BallRepository ballRepository, ConveyorBeltRepository conveyorBeltRepository, BucketRepository bucketRepository) { }
