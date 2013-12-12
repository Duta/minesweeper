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

    public void update(Observable o, Object arg) {
        update();
    }

    private void update() {
        boolean revealMines = model.hasRevealedMine();
        for(int x = 0; x < model.getSize(); x++) {
            for(int y = 0; y < model.getSize(); y++) {
                if(model.isMine(x, y) && revealMines) {
                    buttons[x][y].setMine();
                } else if(model.isRevealed(x, y)) {
                    buttons[x][y].setNumber(model.getNumAdjacentMines(x, y));
                } else {
                    buttons[x][y].setFlag(model.isFlag(x, y));
                }
                buttons[x][y].repaint();
            }
        }
    }

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

        public MineButton(MineModel model, int x, int y) {
            this.model = model;
            this.x = x;
            this.y = y;
            setPreferredSize(new Dimension(20, 20));
            setFlag(false);
            addMouseListener();
        }

        public void setMine() {
            if(MINE_PAINT == null) {
                MINE_PAINT = loadPaint("Mine");
            }
            paint = MINE_PAINT;
        }

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

        public void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D)g;
            g2D.setPaint(paint);
            g2D.fill(FILL_RECT);
        }

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

        private void addMouseListener() {
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if(!model.isRevealed(x, y) && !model.hasRevealedMine()) {
                        if(e.getButton() == MouseEvent.BUTTON1) {
                            model.reveal(x, y);
                        } else if(e.getButton() == MouseEvent.BUTTON3) {
                            model.toggleFlag(x, y);
                        }
                    }
                }
            });
        }
    }
}