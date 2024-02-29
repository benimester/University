import java.util.LinkedList;

public class State {
    private LinkedList<Track> playlist;
    private int nextIndex;
    private Track nowPlaying;
    private boolean currentlyPlaying;

    public State(LinkedList<Track> playlist, int nextIndex, Track nowPlaying, boolean currentlyPlaying) {
        this.playlist = playlist;
        this.nextIndex = nextIndex;
        this.nowPlaying = nowPlaying;
        this.currentlyPlaying = currentlyPlaying;
    }

    public LinkedList<Track> getPlaylist() {
        return playlist;
    }

    public Track getNowPlaying() {
        return nowPlaying;
    }

    public boolean isCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public int getNextIndex() {
        return nextIndex;
    }
}
