import java.util.ArrayList;
 
public class Board {
    private int N;
    private int[][] blocks;
 
    public Board(int[][] blocks) {
        N = blocks[0].length;
        this.blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }
 
    public int dimension() {
        return N;
    }
 
    public int getDistance() {
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != 0) {
                    int rowSteps = (blocks[i][j] - 1)/N;
                    int colSteps = (blocks[i][j] - 1) % N;
                    distance += Math.abs(rowSteps - i) + Math.abs(colSteps - j);
                }
            }
        }
        return distance;
    }
 
    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != i*N + j + 1 && (i != N - 1 || j != N - 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.blocks.length != N || that.blocks[0].length != N) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
 
    public Iterable<Board> getNeighbours() {
        if (N < 2) {
            return null;
        }
        ArrayList<Board> boards = new ArrayList<Board>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) {
                    if (j > 0) {
                        boards.add(getNeighbour(i, j, i, j - 1));
                    }
                    if (j < N - 1) {
                        boards.add(getNeighbour(i, j, i, j + 1));
                    }
                    if (i > 0) {
                        boards.add(getNeighbour(i, j, i - 1, j));
                    }
                    if (i < N - 1) {
                        boards.add(getNeighbour(i, j, i + 1, j));
                    }
                    return boards;
                }
            }
        }
        return null;
    }
 
    private void exch(int i0, int j0, int i, int j) {
        int temp = blocks[i0][j0];
        blocks[i0][j0] = blocks[i][j];
        blocks[i][j] = temp;
    }
 
    private Board getNeighbour(int i0, int j0, int i, int j) {
        Board newBoard = new Board(blocks);
        newBoard.exch(i0, j0, i, j);
        return newBoard;
    }
 
    public String toString() {
        String s = "";
        for (int i = 0; i < N; i++) {
            s += " ";
            for (int j = 0; j < N; j++) {
                s += blocks[i][j] + "  ";
            }
            s += "\n";
        }
        return s;
    }
}