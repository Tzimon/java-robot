package de.tzimom.javarobot.config;

import de.tzimom.javarobot.entities.RenderColor;

/**
 * Describes how a pot should be created
 *
 * @param relativeAngle The angle of the bucket relative to the robot
 * @param angleErrorMargin The margin of error of the angle at which a ball is dropped into the bucket
 * @param color The color the bucket should be rendered in
 */
public record BucketConfig(float relativeAngle, float angleErrorMargin, RenderColor color) { }
