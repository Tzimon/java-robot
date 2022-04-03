package de.tzimom.javarobot.graphics.config;

import de.tzimom.javarobot.graphics.rendering.animation.Curve;

/**
 * Describes how the robot should be animated
 */
public class AnimatedRobotConfig {
    private final int fps;
    private final long animationDuration;
    private final Curve curve;

    /**
     * @param fps The frames per second to render the robot at
     */
    public AnimatedRobotConfig(int fps, long animationDuration, Curve curve) {
        this.fps = fps;
        this.animationDuration = animationDuration;
        this.curve = curve;
    }

    public int fps() {
        return fps;
    }

    public long animationDuration() {
        return animationDuration;
    }

    public Curve curve() {
        return curve;
    }
}
