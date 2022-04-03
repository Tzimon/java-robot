package de.tzimom.javarobot.graphics.rendering.dynamic.elements;

import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicElement;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TextRenderer implements DynamicElement {
    private final String text;
    private final Vector2f location;
    private final float size;
    private final FontInfo fontInfo;
    private final Color color;

    public TextRenderer(String text, Vector2f location, float size, FontInfo fontInfo, Color color) {
        this.text = text;
        this.location = location;
        this.size = size;
        this.fontInfo = fontInfo;
        this.color = color;
    }

    public void render(Graphics2D graphics, int scale) {
        var colorBefore = graphics.getColor();
        var fontBefore = graphics.getFont();

        var scaledSize = size * scale;
        var scaledLocation = location.multiply(scale);

        var font = new Font(fontInfo.name, fontInfo.style, (int) scaledSize);

        graphics.setColor(color);
        graphics.setFont(font);

        if (fontInfo.centered()) {
            var metrics = graphics.getFontMetrics(font);
            var delta = new Vector2f(-metrics.stringWidth(text) / 2f, -metrics.getHeight() / 2f + metrics.getAscent());
            var centeredLocation = scaledLocation.add(delta);

            graphics.drawString(text, centeredLocation.x(), centeredLocation.y());
        } else graphics.drawString(text, scaledLocation.x(), scaledLocation.y());

        graphics.setColor(colorBefore);
        graphics.setFont(fontBefore);
    }

    public static record FontInfo(String name, int style, boolean centered) {}
}
