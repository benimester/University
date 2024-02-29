import javax.swing.*;
import java.io.File;

public class Track {
    private String title;
    private String artist;
    private String genre;
    private String album;
    private String year;
    private ImageIcon albumCover;
    private String path;
    private File file;

    public Track(String title, String artist, String genre, String album, String year, ImageIcon albumCover, String path) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.year = year;
        this.path = path;
        file = new File(path);
        this.albumCover = albumCover;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }

    public String getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }
    public ImageIcon getAlbumCover() {
        return albumCover;
    }
}
