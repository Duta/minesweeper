import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
public class BoardView extends JPanel implements Observer {
    private MineModel model;
    private MineButton[][] buttons;

    /**
     * Creates a BoardView of the given MineModel.
     *
     * @param model The model to view
     */
    public BoardView(MineModel model) {
        GridLayout layout = new GridLayout(model.getSize(), model.getSize());
        layout.setHgap(2);
        layout.setVgap(2);
        setLayout(layout);
        this.model = model;
        buttons = new MineButton[model.getSize()][model.getSize()];
        for(int y = 0; y < model.getSize(); y++) {
            for(int x = 0; x < model.getSize(); x++) {
                buttons[x][y] = new MineButton(model, x, y);
                add(buttons[x][y]);
            }
        }
        setBackground(Color.BLACK);
        update();
    }

    /**
     * This method is called whenever
     * the observable object is changed.
     *
     * @param o The observable object
     * @param arg An argument passed to the
     *            notifyObservers method
     */
    public void update(Observable o, Object arg) {
        update();
    }

    /**
     * Updates the BoardView.
     * Sets each MineButton to be
     * the correct type.
     */
    private void update() {
        boolean hasWon = true;
        for(int x = 0; x < model.getSize(); x++) {
            for(int y = 0; y < model.getSize(); y++) {
                if(model.isRevealed(x, y)) {
                    if(model.isMine(x, y)) {
                        buttons[x][y].setMine();
                        hasWon = false;
                    } else {
                        buttons[x][y].setNumber(model.getNumAdjacentMines(x, y));
                    }
                } else {
                    buttons[x][y].setFlag(model.isFlag(x, y));
                    if(!model.isMine(x, y)) {
                        hasWon = false;
                    }
                }
                buttons[x][y].repaint();
            }
        }
        if(hasWon) {
            for(int x = 0; x < model.getSize(); x++) {
                for(int y = 0; y < model.getSize(); y++) {
                    if(!model.isRevealed(x, y)) {
                        buttons[x][y].setMine();
                        buttons[x][y].repaint();
                    }
                }
            }
        }
    }

    /**
     * Represents a single (clickable) spot
     * in the minesweeper grid.
     */
    private static class MineButton extends JComponent {
        private static final Rectangle
            FILL_RECT = new Rectangle(0, 0, 20, 20);
        private static Paint[]
            NUMBER_PAINTS = new Paint[9];
        private static Paint
            NORMAL_PAINT,
            FLAG_PAINT,
            MINE_PAINT;

        private Paint paint;
        private int x, y;
        private MineModel model;

        /**
         * Creates a MineButton with
         * the given information.
         *
         * @param model The game's model
         * @param x The x co-ord of the button
         * @param y The y co-ord of the button
         */
        public MineButton(MineModel model, int x, int y) {
            this.model = model;
            this.x = x;
            this.y = y;
            setPreferredSize(new Dimension(20, 20));
            setFlag(false);
            addMouseListener(new SquareListener(model, x, y));
        }

        /**
         * Sets the MineButton to
         * visually represent a mine.
         */
        public void setMine() {
            if(MINE_PAINT == null) {
                MINE_PAINT = loadPaint("Mine");
            }
            paint = MINE_PAINT;
        }

        /**
         * Sets the MineButton to
         * visually represent either
         * a flag or a 'normal' spot.
         *
         * @param flag True iff it should
         *             represent a flag
         */
        public void setFlag(boolean flag) {
            if(flag) {
                if(FLAG_PAINT == null) {
                    FLAG_PAINT = loadPaint("Flag");
                }
                paint = FLAG_PAINT;
            } else {
                if(NORMAL_PAINT == null) {
                    NORMAL_PAINT = loadPaint("Normal");
                }
                paint = NORMAL_PAINT;
            }
        }

        /**
         * Sets the MineButton to
         * visually represent a number.
         *
         * @param n A number between 0
         *          and 8 inclusive
         *
         * @throws IllegalArgumentException
         *         if n isn't a valid number
         */
        public void setNumber(int n) {
            if(n < 0 || n > 8) {
                throw new IllegalArgumentException(
                    "n must be between 0 and 8 inclusive.");
            }
            if(NUMBER_PAINTS[n] == null) {
                NUMBER_PAINTS[n] = loadPaint("" + n);
            }
            paint = NUMBER_PAINTS[n];
        }

        /**
         * Paints the MineButton onto the
         * given Graphics object.
         *
         * @param g The Graphics object
         *          to paint onto
         */
        public void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D)g;
            g2D.setPaint(paint);
            g2D.fill(FILL_RECT);
        }

        /**
         * Loads the given image into a Paint
         * (specifically a TexturePaint) and returns it.
         * The image name shouldn't have an extension.
         *
         * @param imageFile The image to load
         *
         * @return The image loaded into a Paint
         *
         * @throws RuntimeException
         *         if loading the image failed
         */
        private Paint loadPaint(String imageFile) {
            imageFile = "images/" + imageFile + ".png";
            try {
                InputStream is =
                    getClass()
                    .getClassLoader()
                    .getResourceAsStream(imageFile);
                BufferedImage image = ImageIO.read(is);
                return new TexturePaint(
                    image,
                    new Rectangle2D.Double(0, 0, 20, 20));
            } catch(IOException e) {
                throw new RuntimeException(
                    "Failed to load image \"" + imageFile + "\".");
            }
        }
    }
}
