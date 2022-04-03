package de.tzimom.javarobot.config;

/**
 * Describes how a conveyor belt should be created
 */
public final class ConveyorBeltConfig {
    private final int queueLength;
    private final float relativeAngle;

    /**
     * @param queueLength The amount of balls lying on the conveyor belt are simultaneously
     * @param relativeAngle The angle of the conveyor belt relative to the robot
     */
    public ConveyorBeltConfig(int queueLength, float relativeAngle) {
        this.queueLength = queueLength;
        this.relativeAngle = relativeAngle;
    }

    public int queueLength() {
        return queueLength;
    }

    public float relativeAngle() {
        return relativeAngle;
    }
}
