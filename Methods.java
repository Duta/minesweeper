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
    public Minesweeper(int size, int numMines)

    /**
     * Generates a new random board.
     */
    public void reset()

    /**
     * Returns the size (edge length) of the board.
     *
     * @return the size (edge length) of the board
     */
    public int getSize()

    /**
     * Returns the number of mines in the board.
     *
     * @return the number of mines in the board
     */
    public int getNumMines()

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
    public boolean isRevealed(int x, int y)

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
    public boolean isMine(int x, int y)

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
    public boolean isFlag(int x, int y)

    /**
     * Returns true iff there exists
     * at least one revealed mine.
     *
     * @return true iff there exists at least
     *              one revealed mine
     */
    public boolean hasRevealedMine()

    /**
     * Reveals all mines.
     */
    public void revealMines()

    /**
     * Toggles whether x,y is flagged.
     *
     * @param x The x co-ord
     * @param y The y co-ord
     */
    public void toggleFlag(int x, int y)

    /**
     * Reveals x,y. If x,y has 0 adjacent
     * mines, floods outwards, recursively
     * revealing until the edges are reached.
     * If x,y is a mine, reveals all mines.
     *
     * @param x The x co-ord
     * @param y The y co-ord
     */
    public void reveal(int x, int y)

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
    public int getNumAdjacentMines(int x, int y)

    /**
     * Sets the number of mines.
     *
     * @param numMines The number of mines
     */
    public void setNumMines(int numMines)

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
    private MineSpot get(int x, int y)

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
        public MineSpot()

        /**
         * Returns true iff this is a mine.
         *
         * @return true iff this is a mine
         */
        public boolean isMine()

        /**
         * Returns true iff this is revealed.
         *
         * @return true iff this is revealed
         */
        public boolean isRevealed()

        /**
         * Returns true iff this is flagged.
         *
         * @return true iff this is flagged
         */
        public boolean isFlag()

        /**
         * Sets whether or not this is a mine.
         *
         * @param mine True iff this is a mine
         */
        public void setMine(boolean mine)

        /**
         * Sets whether or not this is revealed.
         *
         * @param revealed True iff this is revealed
         */
        public void setRevealed(boolean revealed)

        /**
         * Sets whether or not this is flagged.
         *
         * @param flag True iff this is flagged
         */
        public void setFlag(boolean flag)
    }
}
