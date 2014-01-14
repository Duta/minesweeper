import java.util.*;
public class Test {
    private List<Minesweeper> minesweepers;
    private List<MineModel> mineModels;

    private Test() {}

    /**
     * The program's entry point.
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        new Test().test();
    }

    private void test() {
        setUp();
        runTests();
    }

    private void setUp() {
        minesweepers = new ArrayList<Minesweeper>();
        mineModels = new ArrayList<MineModel>();

        minesweepers.add(new Minesweeper(20, 5));
        minesweepers.add(new Minesweeper(10, 8));

        for(Minesweeper m : minesweepers) {
            mineModels.add(new MineModel(m));
        }
    }

    private void runTests() {
        for(Minesweeper m : minesweepers) {
            for(int i = 0; i < m.getSize(); i++) {
                for(int j = 0; j < m.getSize(); j++) {
                    if(m.isRevealed(i, j)) {
                        fail("Newly created Minesweeper had revealed square at "+i+","+j);
                    }
                }
            }
            Random rgen = new Random();
            for(int i = 0; i < 15; i++) {
                int x = rgen.nextInt(m.getSize());
                int y = rgen.nextInt(m.getSize());
                boolean flagged = m.isFlag(x, y);
                m.toggleFlag(x, y);
                if(m.isFlag(x, y) == flagged) {
                    fail("Toggling flag in Minesweeper at "+x+","+y+" had no effect.");
                }
            }
        }
        for(MineModel m : mineModels) {
            for(int i = 0; i < m.getSize(); i++) {
                for(int j = 0; j < m.getSize(); j++) {
                    if(m.isRevealed(i, j)) {
                        fail("Newly created MineModel had revealed square at "+i+","+j);
                    }
                }
            }
            Random rgen = new Random();
            for(int i = 0; i < 15; i++) {
                int x = rgen.nextInt(m.getSize());
                int y = rgen.nextInt(m.getSize());
                boolean flagged = m.isFlag(x, y);
                m.toggleFlag(x, y);
                if(m.isFlag(x, y) == flagged) {
                    fail("Toggling flag in MineModel at "+x+","+y+" had no effect.");
                }
            }
        }
    }

    private void fail(String s) {
        System.out.println("TEST FAILED: " + s);
    }
}
