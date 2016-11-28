import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Solution {

	private static class Board {
		int board[];
		Random random = new Random();
		int n;
		
		Board(int n) {
			board = new int[n];
			this.n = n;
		}
		
		void initializeBoard() {			
			for (int i = 0; i < n; i++) {
				board[i] = i;
            }
            for (int i = 0, n = board.length; i < n; i++) {
                int j = random.nextInt(n);
                int rowToSwap = board[i];
                board[i] = board[j];
                board[j] = rowToSwap;
            }
		}
		
		int countConflictsForPosition(int row, int col) {
			int conflicts = 0;
			
			for (int i = 0; i < n; i++) {
				if (i == col) {
					continue;
				}
				
				int currentRow = board[i];
				if (currentRow == row || Math.abs(currentRow-row) == Math.abs(i-col)){
					conflicts++;
				}
			}
			
			return conflicts;
		}
	}
	
	public static void solve(Board board) {
		int moves = 0; 
		Random random = new Random();
		
		ArrayList<Integer> candidates = new ArrayList<Integer>(board.n);

		while (true) {
			int maxConflicts = 0;
			candidates.clear();
			
			for (int i = 0; i < board.n; i++) {
                int conflicts = board.countConflictsForPosition(board.board[i], i);
                if (conflicts == maxConflicts) {
                    candidates.add(i);
                } else if (conflicts > maxConflicts) {
                    maxConflicts = conflicts;
                    candidates.clear();
                    candidates.add(i);
                }
			}
			
			if (maxConflicts == 0) {
				return;
			}
			
			int queenToBeMoved = board.board[random.nextInt(candidates.size())];
			
			int minConflicts = board.n;
            candidates.clear();
            for (int r = 0; r < board.n; r++) {
                int conflicts = board.countConflictsForPosition(r, queenToBeMoved);
                if (conflicts == minConflicts) {
                    candidates.add(r);
                } else if (conflicts < minConflicts) {
                    minConflicts = conflicts;
                    candidates.clear();
                    candidates.add(r);
                }
            }
            
            if (!candidates.isEmpty()) {
                board.board[queenToBeMoved] =
                    candidates.get(random.nextInt(candidates.size()));
            }

            moves++;
            if (moves == board.board.length) {
                board.initializeBoard();
                moves = 0;
            }
		}
	}
	
    public static void print(PrintStream stream, Board board) {
        for (int r = 0; r < board.board.length; r++) {
            for (int c = 0; c < board.board.length; c++) {
                stream.print(board.board[c] == r ? "Q " : "* ");
            }
            stream.println();
        }
    }
	
	public static void main(String[] args) {
		System.out.println("Enter number of QUEENS: ");
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		Board board = new Board(n);
		board.initializeBoard();
		long start = System.currentTimeMillis();
		solve(board);
		long stop = System.currentTimeMillis();
		System.out.println("Time " +  (stop-start) + "ms.");
		print(System.out, board);

	}
	
}
