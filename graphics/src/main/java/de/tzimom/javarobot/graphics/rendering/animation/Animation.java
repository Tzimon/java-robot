package de.tzimom.javarobot.graphics.rendering.animation;

public record Animation(double startValue, double endValue, long startTime, long endTime, Curve curve) {
    public static Animation start(double startValue, double endValue, long duration, Curve curve) {
        var now = System.currentTimeMillis();
        return new Animation(startValue, endValue, now, now + duration, curve);
    }

    public static Animation recalculate(Animation original, double newEndValue, long duration) {
        var now = System.currentTimeMillis();

        var oldEndTime = original.endTime();
        var newEndTime = now + duration;

        var oldCurveValue = original.curve().getValueAt(inverseLerpAt(original.startTime(), oldEndTime, now));
        var newCurveValue = original.curve().getValueAt(inverseLerpAt(original.startTime(), newEndTime, now));

        var startValue = (original.startValue() + (original.endValue() - original.startValue()) * oldCurveValue - newEndValue * newCurveValue) / (1 - newCurveValue);

        return new Animation(startValue, newEndValue, original.startTime(), newEndTime, original.curve());
    }

    private static double inverseLerpAt(long startTime, long endTime, long time) {
        return (time - startTime) / (double) (endTime - startTime);
    }

    private double getCurveValueAt(long time) {
        var x = inverseLerpAt(startTime, endTime, time);
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
}
