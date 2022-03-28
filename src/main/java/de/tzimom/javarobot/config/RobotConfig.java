package de.tzimom.javarobot.config;

import de.tzimom.javarobot.repositories.BallRepository;

import java.util.List;

/**
 * Describes how the robot should be created
 *
 * @param ballRepository The ball repository the robot uses
 * @param conveyorBelts The available conveyor belts for the robot to operate on
 * @param buckets The available buckets for the robot to drop balls in
 */
public record RobotConfig(BallRepository ballRepository, List<ConveyorBeltConfig> conveyorBelts, List<BucketConfig> buckets) { }
