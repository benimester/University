import java.awt.*;

public interface ITheme {
    public Font getFont();
    public Font getSmallFont();
    public Color getAccentColor();
    public Color getSecondAccentColor();
    public Color getBackgroundColor();
    public Color getForegroundColor();
    @Override
    public String toString();
}
