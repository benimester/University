import java.awt.*;

public class LightTheme implements ITheme{
    @Override
    public Font getFont() {
        return new Font("Monospaced", Font.PLAIN, 30);
    }

    @Override
    public Font getSmallFont() {
        return new Font("Monospaced", Font.PLAIN, 20);
    }

    @Override
    public Color getAccentColor() {
        return new Color(177, 148, 112);
    }

    @Override
    public Color getSecondAccentColor() {
        return new Color(67, 118, 108);
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(248, 250, 229);
    }

    @Override
    public Color getForegroundColor() {
        return new Color(118, 69, 59);
    }

    @Override
    public String toString() {
        return "Light Theme";
    }
}
