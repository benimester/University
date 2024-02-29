import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MusicList extends JPanel {
    LinkedList<Track> list;
    ITheme theme;
    int index = 0;

    public MusicList(ITheme theme, LinkedList<Track> list) {
        this.theme = theme;
        this.list = list;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(theme.getSecondAccentColor());
        this.setForeground(theme.getForegroundColor());
        this.setPreferredSize(new Dimension(300, 1200));
    }

    public void loadTracks() {
        list.stream().filter(track -> list.indexOf(track) >= index).forEach(track -> {
            add(new MusicListLabels(track.getTitle()));
        });
    }

    private class MusicListLabels extends JLabel {
        public MusicListLabels(String name) {
            super(name);
            this.setBackground(theme.getSecondAccentColor());
            this.setForeground(theme.getForegroundColor());
            this.setFont(theme.getSmallFont());
            this.setSize(new Dimension(250, 20));
        }
    }

    public void setList(LinkedList<Track> list) {
        this.list = list;
    }

    public void update(State state) {
        this.removeAll();
        list = state.getPlaylist();
        index = state.getNextIndex();
        loadTracks();
        this.revalidate();
    }
}
