package de.tzimom.javarobot.exceptions;

import de.tzimom.javarobot.entities.Robot;

public class IllegalRobotStateException extends RuntimeException {
    private final Robot robot;

    public IllegalRobotStateException(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }
}
