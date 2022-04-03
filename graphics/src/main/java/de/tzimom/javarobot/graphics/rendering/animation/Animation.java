package de.tzimom.javarobot.graphics.rendering.animation;

public final class Animation {
    private final double startValue;
    private final double endValue;
    private final long startTime;
    private final long endTime;
    private final Curve curve;

    public Animation(double startValue, double endValue, long startTime, long endTime, Curve curve) {
        this.startValue = startValue;
        this.endValue = endValue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.curve = curve;
    }

    public static Animation start(double startValue, double endValue, long duration, Curve curve) {
        long now = System.currentTimeMillis();
        return new Animation(startValue, endValue, now, now + duration, curve);
    }

    public static Animation recalculate(Animation original, double newEndValue, long duration) {
        long now = System.currentTimeMillis();

        long oldEndTime = original.endTime();
        long newEndTime = now + duration;

        double oldCurveValue = original.curve().getValueAt(inverseLerpAt(original.startTime(), oldEndTime, now));
        double newCurveValue = original.curve().getValueAt(inverseLerpAt(original.startTime(), newEndTime, now));

        double startValue = (original.startValue() + (original.endValue() - original.startValue()) * oldCurveValue - newEndValue * newCurveValue) / (1 - newCurveValue);

        return new Animation(startValue, newEndValue, original.startTime(), newEndTime, original.curve());
    }

    private static double inverseLerpAt(long startTime, long endTime, long time) {
        return (time - startTime) / (double) (endTime - startTime);
    }

    private double getCurveValueAt(long time) {
        double x = inverseLerpAt(startTime, endTime, time);
        return curve.getValueAt(x);
    }

    public double getValueAt(long time) {
        return startValue + (endValue - startValue) * getCurveValueAt(time);
    }

    public boolean hasFinishedAt(long time) {
        return time >= endTime;
    }

    public double getValueNow() {
        return getValueAt(System.currentTimeMillis());
    }

    public boolean hasFinishedNow() {
        return hasFinishedAt(System.currentTimeMillis());
    }

    public double startValue() {
        return startValue;
    }

    public double endValue() {
        return endValue;
    }

    public long startTime() {
        return startTime;
    }

    public long endTime() {
        return endTime;
    }

    public Curve curve() {
        return curve;
    }
}
