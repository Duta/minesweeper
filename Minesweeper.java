import java.util.Random;
public class Minesweeper {
    private int size;
    private int numMines;
    private MineSpot[] board;

    public Minesweeper(int size, int numMines) {
        if(numMines > size * size) {
            throw new IllegalArgumentException(
                "You cannot have more mines than there are " +
                "spaces on the board to hold them.");
        }
        this.size = size;
        this.numMines = numMines;
        generateRandomBoard();
    }

    public int getSize() {
        return size;
    }

    public int getNumMines() {
        return numMines;
    }

    public boolean isRevealed(int x, int y) {
        return get(x, y).isRevealed();
    }

    public boolean isMine(int x, int y) {
        return get(x, y).isMine();
    }

    public boolean isFlag(int x, int y) {
        return get(x, y).isFlag();
    }

    public boolean hasRevealedMine() {
        for(int i = 0; i < board.length; i++) {
            if(board[i].isRevealed() && board[i].isMine()) {
                return true;
            }
        }
        return false;
    }

    public void toggleFlag(int x, int y) {
        get(x, y).setFlag(!isFlag(x, y));
    }

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
    }

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

    private MineSpot get(int x, int y) {
        return board[x + y*size];
    }

    private void generateRandomBoard() {
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

    private static class MineSpot {
        private boolean mine;
        private boolean revealed;
        private boolean flag;

        public MineSpot() {
            mine = false;
            revealed = false;
            flag = false;
        }

        public boolean isMine() {
            return mine;
        }

        public boolean isRevealed() {
            return revealed;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setMine(boolean mine) {
            this.mine = mine;
        }

        public void setRevealed(boolean revealed) {
            this.revealed = revealed;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}