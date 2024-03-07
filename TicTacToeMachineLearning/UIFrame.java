import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIFrame extends JFrame implements ActionListener {
    Playground playground;

    JLabel leaderboard;
    int computer;
    int player;
    int draw;
    Champion champion;
    public UIFrame() {
        setBounds(100, 100, 1080, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        playground = new Playground(this);
        this.add(playground, BorderLayout.CENTER);

        computer = 0;
        player = 0;
        draw = 0;
        leaderboard = new JLabel("Player: " + player + "   Computer: " + computer + "   Draw: " + draw + "   Total: 0");
        leaderboard.setFont(new Font("Courier", Font.BOLD, 20));
        this.add(leaderboard, BorderLayout.NORTH);

        champion = new Champion(this);

        setVisible(true);
    }


    public boolean checkBoard(){
        String board = playground.getBoard();
        Character winner = ' ';
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board.charAt(i * 3) == board.charAt(i * 3 + 1) &&
                    board.charAt(i * 3 + 1) == board.charAt(i * 3 + 2) &&
                    board.charAt(i * 3) != ' ') {
                winner = board.charAt(i * 3);
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board.charAt(i) == board.charAt(i + 3) &&
                    board.charAt(i + 3) == board.charAt(i + 6) &&
                    board.charAt(i) != ' ') {
                winner = board.charAt(i);
            }
        }

        // Check diagonals
        if (board.charAt(0) == board.charAt(4) &&
                board.charAt(4) == board.charAt(8) &&
                board.charAt(0) != ' ') {
            winner = board.charAt(0);
        }
        if (board.charAt(2) == board.charAt(4) &&
                board.charAt(4) == board.charAt(6) &&
                board.charAt(2) != ' ') {
            winner = board.charAt(2);
        }

        // Check for draw
        boolean isDraw = true;
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == ' ') {
                isDraw = false;
                break;
            }
        }
        if (isDraw) {
            winner = 'd';
        }

        if(winner == ' '){
            return false;
        }

        champion.newGame(winner);
        playground.clear();

        if(winner == 'x'){
            ++player;
        } else if (winner == 'o') {
            ++computer;
        } else{
            ++draw;
        }
        leaderboard.setText("Player: " + player + "   Computer: " + computer + "   Draw: " + draw + "   Total: " + (player + computer + draw));
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = Integer.parseInt(((JButton) e.getSource()).getName()) / 10;
        int col = Integer.parseInt(((JButton) e.getSource()).getName()) % 10;
        playground.setValue(row, col, 'x');
        champion.play(playground.getBoard());
    }

    public class Playground extends JPanel {
        JButton[] buttons;
        Color[] colors;

        Character[] matrix;
        public Playground(UIFrame frame) {
            setLayout(new GridLayout(3, 3));
            buttons = new JButton[9];

            matrix = new Character[9];

            colors = new Color[2];
            colors[0] = new Color(229, 240, 180);
            colors[1] = new Color(240, 209, 180);

            int tmp = 1;
            for(int i = 0; i < 9; ++i){
                matrix[i] = ' ';
                buttons[i] = new JButton();
                buttons[i].setBackground(colors[tmp]);
                buttons[i].setText(matrix[i].toString());
                buttons[i].addActionListener(frame);
                buttons[i].setName(i / 3 + "" +  i % 3);
                buttons[i].setFont(new Font("Courier", Font.BOLD, 70));
                buttons[i].setForeground(Color.BLACK);
                this.add(buttons[i]);
                tmp  = 1 - tmp;
            }
        }

        public void setValue(int row, int col, Character c){
            matrix[row * 3 + col] = c;
            buttons[row * 3 + col].setText(c.toString());
            buttons[row * 3 + col].setEnabled(false);
            this.revalidate();
        }

        public Character[] getValues(){
            return matrix;
        }

        public String getBoard(){
            String board = "";
            for(int i = 0; i < 9; ++i){
                board += buttons[i].getText();
            }
            return board;
        }

        public void clear(){
            for(int i = 0; i < 9; ++i){
                buttons[i].setText(" ");
                buttons[i].setEnabled(true);
            }
        }
    }
}
