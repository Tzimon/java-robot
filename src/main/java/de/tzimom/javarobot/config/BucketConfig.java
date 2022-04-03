package de.tzimom.javarobot.config;

import de.tzimom.javarobot.entities.RenderColor;

/**
 * Describes how a pot should be created
 */
public final class BucketConfig {
    private final float relativeAngle;
    private final float angleErrorMargin;
    private final RenderColor color;

    /**
     * @param relativeAngle The angle of the bucket relative to the robot
     * @param angleErrorMargin The margin of error of the angle at which a ball is dropped into the bucket
     * @param color The color the bucket should be rendered in
     */
    public BucketConfig(float relativeAngle, float angleErrorMargin, RenderColor color) {
        this.relativeAngle = relativeAngle;
        this.angleErrorMargin = angleErrorMargin;
        this.color = color;
    }

    public float relativeAngle() {
        return relativeAngle;
    }

    public float angleErrorMargin() {
        return angleErrorMargin;
    }

    public RenderColor color() {
        return color;
    }
}
