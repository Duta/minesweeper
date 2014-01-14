import javax.swing.*;
public class MineComponent extends JComponent {
    /**
     * Creates a MineComponent
     * with the given MineModel.
     *
     * @param model The MineModel to view
     */
    public MineComponent(MineModel model) {
        BoardView view = new BoardView(model);
        model.addObserver(view);
        add(view);
    }
}
