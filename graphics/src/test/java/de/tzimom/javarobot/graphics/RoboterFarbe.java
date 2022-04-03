package de.tzimom.javarobot.graphics;

import de.tzimom.javarobot.entities.RenderColor;

import java.awt.Color;
import java.util.List;

public enum RoboterFarbe implements RenderColor {
    ROT(new Color(255, 100, 100), false),
    GRÜN(new Color(100, 255, 100), true),
    BLAU(new Color(100, 100, 255), false),
    GELB(new Color(255, 255, 100), true),
    MAGENTA(new Color(255, 100, 255), false);

    private final Color value;
    private final boolean bright;

    RoboterFarbe(Color value, boolean bright) {
        this.value = value;
        this.bright = bright;
    }

    public static List<RenderColor> alleFarben() {
        return List.of(values());
    }

    public Color getValue() {
        return value;
    }

    public boolean isBright() {
        return bright;
    }
}
