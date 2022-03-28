package de.tzimom.javarobot.api;

import de.tzimom.javarobot.exceptions.IllegalRobotStateException;

import java.util.Optional;

public interface Robot {
    Optional<Ball> grabBall() throws IllegalRobotStateException;
    Optional<Bucket> dropBall() throws IllegalRobotStateException;

    void turnTo(float angle);

    float getCurrentAngle();
    Optional<Ball> getCurrentBall();
}
