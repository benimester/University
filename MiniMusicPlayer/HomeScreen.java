import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    private MetaData metaData;
    private MusicList musicList;
    private MyPlayer myPlayer;

    public HomeScreen(ITheme theme, Control control) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Fullscreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Ha ki lesz veve fullscreenbol akkor 1980x1200 meretu lesz az ablak
        this.setBounds(0, 0, 1980, 1200);

        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        this.setBackground(theme.getBackgroundColor());

        // Kozepen a zene adatlapja
        metaData = new MetaData(theme, AudioFileManager.UNTITLED);
        metaData.prepareData();
        this.add(metaData, BorderLayout.CENTER);

        // Bal oldali panel letrehozas
        // Ez a lejatszasi listak/zenek listaja lesz
        musicList = new MusicList(theme, control.getPlaylist());
        this.add(musicList, BorderLayout.WEST);
        musicList.loadTracks();

        // Lent a lejatszo
        myPlayer = new MyPlayer(theme);
        this.add(myPlayer, BorderLayout.SOUTH);
        myPlayer.setPlayerActionListener(control.getPlayerController());
        this.setVisible(true);
    }

    public void updateView(State state) {
        metaData.setTrack(state.getNowPlaying());
        metaData.prepareData();
        metaData.revalidate();
        musicList.update(state);
        myPlayer.update(state.isCurrentlyPlaying());
    }
}
