import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

public class Model {
    private View view;
    private Control control;
    private ITheme selectedTheme;
    public Model() {
        // Egy bemeneti katalogus kerese
        File selectedDir = askDirectory();
        if(selectedDir == null){
            System.out.print("No input was selected");
            return;
        }

        // tema valasztasa
        chooseTheme();

        // Zenek beolvasa
        AudioFileManager audioFileManager = new AudioFileManager(selectedDir);
        LinkedList<Track>list = audioFileManager.getPlaylist();

        control = new Control(this, list);
        view = new View(selectedTheme, control);
    }

    public void update(State state){
        view.update(state);
    }

    private File askDirectory(){
        JFrame selectDir = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Directory!");
        fileChooser.setCurrentDirectory(new File("C:\\Users\\czomp\\egyetem\\3_felev\\Java\\Spotify"));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(selectDir) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }

    private LinkedList<ITheme> gatherThemes(){
        LinkedList<ITheme>themes = new LinkedList<ITheme>();
        themes.add(new DarkTheme());
        themes.add(new LightTheme());
        themes.add(new AccentTheme());
        return themes;
    }

    private void chooseTheme(){
        JDialog themeChooser = new JDialog((Frame) null, "Choose a theme!", true);
        themeChooser.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        themeChooser.setLayout(new GridLayout(2, 1));
        themeChooser.setBounds(600, 350, 400, 200);

        JComboBox<ITheme> comboBox = new JComboBox<ITheme>();
        gatherThemes().forEach(comboBox::addItem);
        themeChooser.add(comboBox);

        JButton okButton = new JButton("Ok");
        okButton.setSize(new Dimension(50, 30));
        okButton.setPreferredSize(new Dimension(50, 30));

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themeChooser.dispose();
                selectedTheme = (ITheme) comboBox.getSelectedItem();
            }
        });
        themeChooser.add(okButton);

        themeChooser.setVisible(true);
    }
}
