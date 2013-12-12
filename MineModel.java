import java.util.Observable;
public class MineModel extends Observable {
    private Minesweeper minesweeper;

    /**
     * Creates a MineModel backed by
     * the given Minesweeper.
     *
     * @param minesweeper The backing object
     */
    public MineModel(Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
    }

    /**
     * Returns the size (edge length) of the board.
     *
     * @return the size (edge length) of the board
     */
    public int getSize() {
        return minesweeper.getSize();
    }

    /**
     * Returns the number of mines in the board.
     *
     * @return the number of mines in the board
     */
    public int getNumMines() {
        return minesweeper.getNumMines();
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
        return minesweeper.isRevealed(x, y);
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
        return minesweeper.isMine(x, y);
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
        return minesweeper.isFlag(x, y);
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
        return minesweeper.getNumAdjacentMines(x, y);
    }

    /**
     * Returns true iff there exists
     * at least one revealed mine.
     *
     * @return true iff there exists at least
     *              one revealed mine
     */
    public boolean hasRevealedMine() {
        return minesweeper.hasRevealedMine();
    }

    /**
     * Generates a new random board.
     */
    public void reset() {
        minesweeper.reset();
        setChanged();
        notifyObservers();
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
        minesweeper.reveal(x, y);
        setChanged();
        notifyObservers();
    }

    /**
     * Reveals all mines.
     */
    public void revealMines() {
        minesweeper.revealMines();
        setChanged();
        notifyObservers();
    }

    /**
     * Toggles whether x,y is flagged.
     *
     * @param x The x co-ord
     * @param y The y co-ord
     */
    public void toggleFlag(int x, int y) {
        minesweeper.toggleFlag(x, y);
        setChanged();
        notifyObservers();
    }

    /**
     * Sets the number of mines.
     *
     * @param numMines The number of mines
     */
    public void setNumMines(int numMines) {
        minesweeper.setNumMines(numMines);
        setChanged();
        notifyObservers();
    }
}
