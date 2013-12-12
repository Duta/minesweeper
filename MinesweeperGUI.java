import javax.swing.*;
public class MinesweeperGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minesweeper");
        MineModel model = new MineModel(new Minesweeper(16, 40));
        BoardView view = new BoardView(model);
        model.addObserver(view);
        frame.add(view);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}