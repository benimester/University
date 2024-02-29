import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;
import java.util.ListIterator;

public class PlayerController implements ActionListener {
    private LinkedList<Track> playlist;
    private ListIterator<Track> i;
    private Track nowPlaying;
    private boolean isTrackLoaded;
    private boolean currentlyPlaying;
    private playerThread playerThread;
    private Control mainController;

    public PlayerController() {
        playlist = new LinkedList<Track>();
        i = playlist.listIterator();
        isTrackLoaded = false;
        currentlyPlaying = false;
    }

    public PlayerController(LinkedList<Track> playlist) {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });
        this.playlist = playlist;
        i = playlist.listIterator();
        isTrackLoaded = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        switch (b.getName()) {
            case "playButton":
                currentlyPlaying = false;
                if (!isTrackLoaded && i.hasNext()) {
                    nowPlaying = i.next();
                    isTrackLoaded = true;
                }
                if (isTrackLoaded) {
                    currentlyPlaying = true;
                    playerThread = new playerThread();
                }
                break;
            case "pauseButton":
                playerThread.stop();
                currentlyPlaying = false;
                break;
            case "skipLeftButton":
                playerThread.stop();
                if (i.hasPrevious()) {
                    nowPlaying = i.previous();
                    currentlyPlaying = true;
                    playerThread = new playerThread();
                } else {
                    nowPlaying = AudioFileManager.UNTITLED;
                    currentlyPlaying = false;
                }
                isTrackLoaded = true;
                break;
            case "skipRightButton":
                playerThread.stop();
                isTrackLoaded = true;
                if (i.hasNext()) {
                    nowPlaying = i.next();
                    currentlyPlaying = true;
                    playerThread = new playerThread();
                } else {
                    nowPlaying = AudioFileManager.UNTITLED;
                    currentlyPlaying = false;
                }
                isTrackLoaded = true;
                break;
        }
        invokeUpdate();
    }

    public LinkedList<Track> getPlaylist() {
        return playlist;
    }

    private class playerThread implements Runnable {

        private boolean exit;

        playerThread() {
            Thread t = new Thread(this);
            exit = false;
            t.start();
        }

        @Override
        public void run() {

            Media sound = new Media(nowPlaying.getFile().toURI().toString());
            MediaPlayer player = new MediaPlayer(sound);

            player.play();

            while (!exit) {
                try {
                    Thread.sleep(1);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            player.stop();

            //com.sun.javafx.application.PlatformImpl.exit();
        }

        public void stop() {
            exit = true;
        }
    }

    private void invokeUpdate() {
        mainController.update(new State(playlist, i.nextIndex(), nowPlaying, currentlyPlaying));
    }

    public void setMainController(Control mainController) {
        this.mainController = mainController;
    }
}
