public class View {
    private HomeScreen homeScreen;
    private Control control;

    public View(ITheme theme,Control control) {
        this.control = control;
        homeScreen = new HomeScreen(theme, control);
    }

    public void update(State state){
        homeScreen.updateView(state);
        homeScreen.revalidate();
        homeScreen.repaint();
    }

}
