import java.util.Observable;
public class MineModel extends Observable {
    private Minesweeper minesweeper;

    public MineModel(Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
    }

    public int getSize() {
        return minesweeper.getSize();
    }

    public int getNumMines() {
        return minesweeper.getNumMines();
    }

    public boolean isRevealed(int x, int y) {
        return minesweeper.isRevealed(x, y);
    }

    public boolean isMine(int x, int y) {
        return minesweeper.isMine(x, y);
    }

    public boolean isFlag(int x, int y) {
        return minesweeper.isFlag(x, y);
    }

    public int getNumAdjacentMines(int x, int y) {
        return minesweeper.getNumAdjacentMines(x, y);
    }

    public boolean hasRevealedMine() {
        return minesweeper.hasRevealedMine();
    }

    public void reveal(int x, int y) {
        minesweeper.reveal(x, y);
        setChanged();
        notifyObservers();
    }

    public void toggleFlag(int x, int y) {
        minesweeper.toggleFlag(x, y);
        setChanged();
        notifyObservers();
    }
}