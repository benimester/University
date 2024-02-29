import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyPlayer extends JPanel {
    private ITheme theme;

    private JButton playButton;
    private JButton pauseButton;
    private JButton skipLeftButton;
    private JButton skipRightButton;
    private JPanel playerPanel;

    public MyPlayer(ITheme theme) {
        this.theme = theme;
        this.setBackground(theme.getAccentColor());
        this.setForeground(theme.getForegroundColor());
        this.setFont(theme.getFont());
        this.setLayout(new BorderLayout());

        // Lejatszo
        playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout());
        playerPanel.setBackground(theme.getAccentColor());

        Dimension buttonSize = new Dimension(50, 50);

        playButton = new JButton();
        playButton.setIcon(new ImageIcon("images/play.png"));
        playButton.setBackground(theme.getAccentColor());
        playButton.setSize(buttonSize);
        playButton.setPreferredSize(buttonSize);
        playButton.setBorderPainted(false);
        playButton.setName("playButton");

        pauseButton = new JButton();
        pauseButton.setIcon(new ImageIcon("images/pause.png"));
        pauseButton.setBackground(theme.getAccentColor());
        pauseButton.setSize(buttonSize);
        pauseButton.setPreferredSize(buttonSize);
        pauseButton.setBorderPainted(false);
        pauseButton.setName("pauseButton");

        skipLeftButton = new JButton();
        skipLeftButton.setIcon(new ImageIcon("images/skipLeft.png"));
        skipLeftButton.setBackground(theme.getAccentColor());
        skipLeftButton.setSize(buttonSize);
        skipLeftButton.setPreferredSize(buttonSize);
        skipLeftButton.setBorderPainted(false);
        skipLeftButton.setName("skipLeftButton");

        skipRightButton = new JButton();
        skipRightButton.setIcon(new ImageIcon("images/skipRight.png"));
        skipRightButton.setBackground(theme.getAccentColor());
        skipRightButton.setSize(buttonSize);
        skipRightButton.setPreferredSize(buttonSize);
        skipRightButton.setBorderPainted(false);
        skipRightButton.setName("skipRightButton");

        playerPanel.add(skipLeftButton);
        playerPanel.add(playButton);
        playerPanel.add(skipRightButton);

        this.add(playerPanel, BorderLayout.CENTER);
    }

    public void setPlayerActionListener(ActionListener a) {
        playButton.addActionListener(a);
        pauseButton.addActionListener(a);
        skipLeftButton.addActionListener(a);
        skipRightButton.addActionListener(a);
    }

    public void update(boolean isPlaying) {
        playerPanel.removeAll();
        if (isPlaying) {
            playerPanel.add(skipLeftButton);
            playerPanel.add(pauseButton);
            playerPanel.add(skipRightButton);
        } else {
            playerPanel.add(skipLeftButton);
            playerPanel.add(playButton);
            playerPanel.add(skipRightButton);
        }
        playerPanel.revalidate();
        this.revalidate();
    }
}
