import java.util.LinkedList;

public class Control {
    private PlayerController playerController;
    private Model model;

    public Control(Model model, LinkedList<Track> l) {
        this.model = model;
        playerController = new PlayerController(l);
        playerController.setMainController(this);
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public LinkedList<Track> getPlaylist() {
        return playerController.getPlaylist();
    }

    public void update(State state) {
        model.update(state);
    }
}
