import java.util.Random;
public class Minesweeper {
    private int size;
    private int numMines;
    private MineSpot[] board;

    /**
     * Generates a random minesweeper board
     * of the given size with the given number of mines.
     *
     * @param size The size (edge length) of the board
     * @param numMines The number of mines in the board
     */
    public Minesweeper(int size, int numMines) {
        if(numMines > size * size) {
            throw new IllegalArgumentException(
                "You cannot have more mines than there are " +
                "spaces on the board to hold them.");
        }
        this.size = size;
        this.numMines = numMines;
        reset();
    }

    /**
     * Generates a new random board.
     */
    public void reset() {
        int numSpots = size * size;
        Random rgen = new Random();
        // Create an empty board
        board = new MineSpot[numSpots];
        for(int i = 0; i < board.length; i++) {
            board[i] = new MineSpot();
        }
        // Place the mines
        for(int i = 0; i < numMines; i++) {
            // Generate a random spot for the mine
            int index = rgen.nextInt(numSpots);
            // If there is a mine at the chosen
            // spot, skip forwards to the first
            // free location and put it there
            while(board[index].isMine()) {
                index++;
                index %= numSpots;
            }
            // Place the mine
            board[index].setMine(true);
        }
    }

    /**
     * Returns the size (edge length) of the board.
     *
     * @return the size (edge length) of the board
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the number of mines in the board.
     *
     * @return the number of mines in the board
     */
    public int getNumMines() {
        return numMines;
    }

    /**
     * Returns true iff the spot
     * at x,y is revealed.
     *
     * @param x The x co-ord
     * @param y The y co-ord
     *
     * @return true iff the spot at
     *              x,y is revealed
     */
    public boolean isRevealed(int x, int y) {
        return get(x, y).isRevealed();
    }

    /**
     * Returns true iff the spot
     * at x,y is a mine.
     *
     * @param x The x co-ord
     * @param y The y co-ord
     *
     * @return true iff the spot at
     *              x,y is a mine
     */
    public boolean isMine(int x, int y) {
        return get(x, y).isMine();
    }

    /**
     * Returns true iff the spot
     * at x,y is flagged.
     *
     * @param x The x co-ord
     * @param y The y co-ord
     *
     * @return true iff the spot at
     *              x,y is flagged
     */
    public boolean isFlag(int x, int y) {
        return get(x, y).isFlag();
    }

    /**
     * Returns true iff there exists
     * at least one revealed mine.
     *
     * @return true iff there exists at least
     *              one revealed mine
     */
    public boolean hasRevealedMine() {
        for(MineSpot spot : board) {
            if(spot.isRevealed() && spot.isMine()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reveals all mines.
     */
    public void revealMines() {
        for(MineSpot spot : board) {
            if(spot.isMine()) {
                spot.setRevealed(true);
            }
        }
    }

    /**
     * Toggles whether x,y is flagged.
     *
     * @param x The x co-ord
     * @param y The y co-ord
     */
    public void toggleFlag(int x, int y) {
        get(x, y).setFlag(!isFlag(x, y));
    }

    /**
     * Reveals x,y. If x,y has 0 adjacent
     * mines, floods outwards, recursively
     * revealing until the edges are reached.
     * If x,y is a mine, reveals all mines.
     *
     * @param x The x co-ord
     * @param y The y co-ord
     */
    public void reveal(int x, int y) {
        get(x, y).setRevealed(true);

        if(getNumAdjacentMines(x, y) == 0) {
            for(int i = x - 1; i <= x + 1; i++) {
                for(int j = y - 1; j <= y + 1; j++) {
                    if(i == x && j == y) continue;
                    if(i < 0 || j < 0) continue;
                    if(i >= size || j >= size) continue;
                    if(!isRevealed(i, j)) {
                        reveal(i, j);
                    }
                }
            }
        }

        if(get(x, y).isMine()) {
            revealMines();
        }
    }

    /**
     * Returns the number of mines
     * adjacent to x,y.
     *
     * @param x The x co-ord
     * @param y The y co-ord
     *
     * @return the number of mines
     *         adjacent to x,y
     */
    public int getNumAdjacentMines(int x, int y) {
        int num = 0;
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                if(i == x && j == y) continue;
                if(i < 0 || j < 0) continue;
                if(i >= size || j >= size) continue;
                if(isMine(i, j)) {
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * Sets the number of mines.
     *
     * @param numMines The number of mines
     */
    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }

    /**
     * Returns the MineSpot at x,y
     * from the board array.
     *
     * @param x The x co-ord
     * @param y The y co-ord
     *
     * @return the MineSpot at x,y
     *         from the board array
     */
    private MineSpot get(int x, int y) {
        return board[x + y*size];
    }

    /**
     * Represents a single spot
     * in the minesweeper board
     */
    private static class MineSpot {
        private boolean mine;
        private boolean revealed;
        private boolean flag;

        /**
         * Creates a MineSpot.
         * By default, it is not
         * a mine, not revealed
         * and not flagged.
         */
        public MineSpot() {
            mine = false;
            revealed = false;
            flag = false;
        }

        /**
         * Returns true iff this is a mine.
         *
         * @return true iff this is a mine
         */
        public boolean isMine() {
            return mine;
        }

        /**
         * Returns true iff this is revealed.
         *
         * @return true iff this is revealed
         */
        public boolean isRevealed() {
            return revealed;
        }

        /**
         * Returns true iff this is flagged.
         *
         * @return true iff this is flagged
         */
        public boolean isFlag() {
            return flag;
        }

        /**
         * Sets whether or not this is a mine.
         *
         * @param mine True iff this is a mine
         */
        public void setMine(boolean mine) {
            this.mine = mine;
        }

        /**
         * Sets whether or not this is revealed.
         *
         * @param revealed True iff this is revealed
         */
        public void setRevealed(boolean revealed) {
            this.revealed = revealed;
        }

        /**
         * Sets whether or not this is flagged.
         *
         * @param flag True iff this is flagged
         */
        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}
