import javax.swing.*;
import java.awt.*;

public class MetaData extends JPanel {
    ITheme theme;
    Track track;
    SpringLayout springLayout;

    public MetaData(ITheme theme, Track track) {
        this.theme = theme;
        this.track = track;

        this.setBackground(theme.getBackgroundColor());
        this.setForeground(theme.getForegroundColor());

        springLayout = new SpringLayout();
        this.setLayout(springLayout);
    }

    public void prepareData() {
        this.removeAll();

        ImageIcon albumCoverImage = track.getAlbumCover();
        JLabel albumCover = new JLabel(albumCoverImage);
        albumCover.setPreferredSize(new Dimension(400, 400));
        this.add(albumCover);

        // Feliratok letrehozasa
        JLabel title = new JLabel("Title: " + track.getTitle());
        title.setBackground(theme.getBackgroundColor());
        title.setForeground(theme.getForegroundColor());
        title.setFont(theme.getFont());
        this.add(title);
        JLabel artist = new JLabel("Artist: " + track.getArtist());
        artist.setBackground(theme.getBackgroundColor());
        artist.setForeground(theme.getForegroundColor());
        artist.setFont(theme.getFont());
        this.add(artist);
        JLabel album = new JLabel("Album: " + track.getAlbum());
        album.setBackground(theme.getBackgroundColor());
        album.setForeground(theme.getForegroundColor());
        album.setFont(theme.getFont());
        this.add(album);
        JLabel genre = new JLabel("Genre: " + track.getGenre());
        genre.setBackground(theme.getBackgroundColor());
        genre.setForeground(theme.getForegroundColor());
        genre.setFont(theme.getFont());
        this.add(genre);
        JLabel year = new JLabel("Year: " + track.getYear());
        year.setBackground(theme.getBackgroundColor());
        year.setForeground(theme.getForegroundColor());
        year.setFont(theme.getFont());
        this.add(year);

        springLayout.putConstraint(SpringLayout.NORTH, albumCover, 50, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, albumCover, 50, SpringLayout.WEST, this);

        int padding = 20;
        // A kephez kepest
        springLayout.putConstraint(SpringLayout.WEST, title, padding, SpringLayout.EAST, albumCover);
        springLayout.putConstraint(SpringLayout.WEST, artist, padding, SpringLayout.EAST, albumCover);
        springLayout.putConstraint(SpringLayout.WEST, album, padding, SpringLayout.EAST, albumCover);
        springLayout.putConstraint(SpringLayout.WEST, genre, padding, SpringLayout.EAST, albumCover);
        springLayout.putConstraint(SpringLayout.WEST, year, padding, SpringLayout.EAST, albumCover);

        // Egymashoz kepest
        int itemPadding = 5;
        springLayout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, albumCover);
        springLayout.putConstraint(SpringLayout.NORTH, artist, itemPadding, SpringLayout.SOUTH, title);
        springLayout.putConstraint(SpringLayout.NORTH, album, itemPadding, SpringLayout.SOUTH, artist);
        springLayout.putConstraint(SpringLayout.NORTH, genre, itemPadding, SpringLayout.SOUTH, album);
        springLayout.putConstraint(SpringLayout.NORTH, year, itemPadding, SpringLayout.SOUTH, genre);
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public void setTheme(ITheme theme) {
        this.theme = theme;
    }

    public Track getTrack() {
        return track;
    }
}