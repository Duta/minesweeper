import javax.swing.*;
public class MinesweeperGUI {
    /**
     * The program's entry point.
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minesweeper");
        MineModel model = new MineModel(new Minesweeper(10, 15));
        ControlPanel controls = new ControlPanel(model);
        BoardView view = new BoardView(model);
        model.addObserver(view);
        frame.add(view);
        frame.setJMenuBar(controls);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
