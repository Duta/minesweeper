import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class ControlPanel extends JMenuBar implements Observer {
    private MineModel model;

    public ControlPanel(MineModel model) {
        this.model = model;

        // Components
        JMenu game = new JMenu("Game");
        JMenuItem reveal = new JMenuItem("Reveal Mines");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem exit = new JMenuItem("Exit");
        JMenu difficulty = new JMenu("Difficulty");
        ButtonGroup buttonGroup = new ButtonGroup();
        JMenuItem easy = new JRadioButtonMenuItem("Easy");
        JMenuItem medium = new JRadioButtonMenuItem("Medium");
        JMenuItem hard = new JRadioButtonMenuItem("Hard");

        // Listeners
        ActionListener revealListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControlPanel.this.model.revealMines();
            }
        };
        ActionListener restartListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControlPanel.this.model.reset();
            }
        };
        ActionListener exitListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        ActionListener difficultyListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int numMines = Integer.parseInt(e.getActionCommand());
                setNumMines(numMines);
            }
        };

        // Game menu
        reveal.addActionListener(revealListener);
        restart.addActionListener(restartListener);
        exit.addActionListener(exitListener);
        game.add(reveal);
        game.add(restart);
        game.add(exit);
        add(game);

        // Difficulty menu
        medium.setSelected(true);
        easy.setActionCommand("10");
        medium.setActionCommand("15");
        hard.setActionCommand("20");
        easy.addActionListener(difficultyListener);
        medium.addActionListener(difficultyListener);
        hard.addActionListener(difficultyListener);
        buttonGroup.add(easy);
        buttonGroup.add(medium);
        buttonGroup.add(hard);
        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);
        add(difficulty);
    }

    public void update(Observable o, Object arg) {
        // TODO
    }

    private void setNumMines(int numMines) {
        model.setNumMines(numMines);
        model.reset();
    }
}