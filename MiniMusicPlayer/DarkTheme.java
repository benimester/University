import java.awt.*;

public class DarkTheme implements ITheme{
    @Override
    public Font getFont() {
        return new Font("Dialog", Font.PLAIN, 30);
    }

    @Override
    public Font getSmallFont() {
        return new Font("Dialog", Font.PLAIN, 20);
    }

    @Override
    public Color getAccentColor() {
        return new Color(14, 131, 136);
    }

    @Override
    public Color getSecondAccentColor() {
        return new Color(46, 79, 79);
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(44, 51, 51);
    }

    @Override
    public Color getForegroundColor() {
        return new Color(203, 228, 222);
    }

    @Override
    public String toString() {
        return "Dark Theme";
    }
}
