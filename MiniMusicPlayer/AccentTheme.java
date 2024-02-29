import java.awt.*;

public class AccentTheme implements ITheme {
    @Override
    public Font getFont() {
        return new Font("Serif", Font.BOLD, 30);
    }

    @Override
    public Font getSmallFont() {
        return new Font("Serif", Font.PLAIN, 20);
    }

    @Override
    public Color getAccentColor() {
        return new Color(0, 173, 181);
    }

    @Override
    public Color getSecondAccentColor() {
        return new Color(255, 87, 34);
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(48, 56, 65);
    }

    @Override
    public Color getForegroundColor() {
        return new Color(238, 238, 238);
    }

    @Override
    public String toString() {
        return "Accent Theme";
    }
}
