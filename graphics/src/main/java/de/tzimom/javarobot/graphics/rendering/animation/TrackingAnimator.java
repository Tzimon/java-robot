package de.tzimom.javarobot.graphics.rendering.animation;

import java.util.function.Supplier;

public class TrackingAnimator extends Animator {
    private double lastTrackedValue;
    private final Supplier<Double> trackedValue;

    public TrackingAnimator(long duration, Curve curve, Supplier<Double> trackedValue) {
        super(duration, curve, trackedValue.get());

        this.trackedValue = trackedValue;
        this.lastTrackedValue = trackedValue.get();
    }

    public double getCurrentValue() {
        if (trackedValue.get() == lastTrackedValue) return super.getCurrentValue();

        animateTo(lastTrackedValue = trackedValue.get());
        return super.getCurrentValue();
    }
}
