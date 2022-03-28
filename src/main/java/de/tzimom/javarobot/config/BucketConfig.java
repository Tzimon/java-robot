package de.tzimom.javarobot.config;

/**
 * Describes how a pot should be created
 *
 * @param relativeAngle The angle of the bucket relative to the robot
 * @param angleErrorMargin The margin of error of the angle at which a ball is dropped into the bucket
 */
public record BucketConfig(float relativeAngle, float angleErrorMargin) { }
