import java.awt.event.*;
public class SquareListener extends MouseAdapter {
    private MineModel model;
    private int x, y;

    /**
     * Creates a SquareListener for the button
     * representing the x,y spot in the given model.
     *
     * @param model The MineModel
     * @param x The x co-ord
     * @param y The y co-ord
     */
    public SquareListener(MineModel model, int x, int y) {
        this.model = model;
        this.x = x;
        this.y = y;
    }

    /**
     * Handles the mouse being pressed.
     * If the square is revealed or mines
     * have been revealed, does nothing.
     * If it is a left-mouse click,
     * reveals x,y iff it isn't flagged.
     * If it is a right-mouse click,
     * toggles whether x,y is flagged.
     *
     * @param e The MouseEvent for the event
     */
    public void mousePressed(MouseEvent e) {
        if(!model.isRevealed(x, y)
        && !model.hasRevealedMine()) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                if(!model.isFlag(x, y)) {
                    model.reveal(x, y);
                }
            } else if(e.getButton() == MouseEvent.BUTTON3) {
                model.toggleFlag(x, y);
            }
        }
    }
}
