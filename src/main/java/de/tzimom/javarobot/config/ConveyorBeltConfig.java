package de.tzimom.javarobot.config;

/**
 * Describes how a conveyor belt should be created
 *
 * @param queueLength The amount of balls lying on the conveyor belt are simultaneously
 * @param relativeAngle The angle of the conveyor belt relative to the robot
 */
public record ConveyorBeltConfig(int queueLength, float relativeAngle) { }
