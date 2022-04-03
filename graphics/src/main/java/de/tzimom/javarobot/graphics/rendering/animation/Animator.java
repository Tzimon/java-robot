package de.tzimom.javarobot.graphics.rendering.animation;

public class Animator {
    private final long duration;
    private final Curve curve;

    private double lastValue;
    private Animation currentAnimation;

    public Animator(long duration, Curve curve, double initialValue) {
        this.duration = duration;
        this.curve = curve;

        lastValue = initialValue;
    }

    public void animateTo(double targetValue) {
        if (currentAnimation == null) currentAnimation = Animation.start(lastValue, targetValue, duration, curve);
        else currentAnimation = Animation.recalculate(currentAnimation, targetValue, duration);
    }

    public double getCurrentValue() {
        if (currentAnimation == null) return lastValue;

        if (currentAnimation.hasFinishedNow()) {
            lastValue = currentAnimation.endValue();
            currentAnimation = null;

            return lastValue;
        }

        return lastValue = currentAnimation.getValueNow();
    }
}
