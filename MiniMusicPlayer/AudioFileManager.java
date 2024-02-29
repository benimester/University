import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

public class
AudioFileManager {
    private LinkedList<Track> playlist;
    public static Track UNTITLED = new Track("Untitled", "Nobody", "Nothing", "N/A", "2000", new ImageIcon("images/cover.jpg"), "Nothing");

    public AudioFileManager(File file) {
        playlist = new LinkedList<Track>();
        // Az audio fileok kiszurese
        Arrays.stream(file.listFiles()).filter(AudioFileManager::isAudioFile).forEach(track -> playlist.add(extractAudio(track)));
    }

    public Track extractAudio(File file) {
        try {
            // Beolvassuk a zenet
            AudioFile audio = AudioFileIO.read(file);

            // A metadata szurese
            Tag tag = audio.getTag();

            // Eloaalitjuk a szukseges informaciokat
            String title = tag.getFirst(FieldKey.TITLE);
            String artist = tag.getFirst(FieldKey.ARTIST);
            String album = tag.getFirst(FieldKey.ALBUM);
            String year = tag.getFirst(FieldKey.YEAR);
            String genre = tag.getFirst(FieldKey.GENRE);

            // Az album borito kep keresese
            Artwork artwork = tag.getFirstArtwork();

            ImageIcon image = new ImageIcon("images/cover.png");
            if (artwork != null) {
                // Van kep
                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(artwork.getBinaryData()));
                image = new ImageIcon(bufferedImage);
            }
            return new Track(title, artist, genre, album, year, image, file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isAudioFile(File file) {
        return (file.getName().endsWith(".mp3") || file.getName().endsWith(".waw") || file.getName().endsWith(".aac"));
    }

    public LinkedList<Track> getPlaylist() {
        return playlist;
    }
}
