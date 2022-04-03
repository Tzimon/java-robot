package de.tzimom.javarobot.graphics.animated;

import de.tzimom.javarobot.entities.Ball;
import de.tzimom.javarobot.entities.Bucket;
import de.tzimom.javarobot.entities.Robot;
import de.tzimom.javarobot.graphics.rendering.animation.Animator;

import java.util.Optional;

public class AnimatedRobot implements Robot {
    private final Robot robot;
    private final Animator turnAnimator;

    public AnimatedRobot(Robot robot, Animator turnAnimator) {
        this.robot = robot;
        this.turnAnimator = turnAnimator;
    }

    public Optional<Ball> grabBall() {
        return robot.grabBall();
    }

    public Optional<Bucket> dropBall() {
        return robot.dropBall();
    }

    public void turnTo(float angle) {
        turnAnimator.animateTo(angle);
        robot.turnTo(angle);
    }

    /**
     * @return The current angle of the animation
     */
    public float getCurrentAngle() {
        return (float) turnAnimator.getCurrentValue();
    }

    /**
     * @return The current real angle the robot arm has
     */
    public float getCurrentRealAngle() {
        return robot.getCurrentAngle();
    }

    public Optional<Ball> getCurrentBall() {
        return robot.getCurrentBall();
    }
}
