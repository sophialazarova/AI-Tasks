import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Solver {
    private PriorityQueue<Node> pq = new PriorityQueue<Node>();
    private int minMoves = -1;
    private Node bestNode;
    private boolean solved;
 
    private class Node implements Comparable<Node> {
        private Board board;
        private int moves, dist;
        private Node prev;
 
        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            dist = board.getDistance();
        }
 
        @Override
        public int compareTo(Node that) {
            return this.moves + this.dist - that.moves - that.dist;
        }
    }
 
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        pq.add(new Node(initial, 0, null)); 
        
        findBestNode(initial);
    }
    
    private void findBestNode(Board initial) {
    	 while (!pq.isEmpty()) {
             Node current = pq.poll();
             if (current.board.isGoal()) {
                 Node root = getRootNode(current);
                 if (!root.board.equals(initial)) {
                     break;
                 }
                 solved = true;
                 if (minMoves == -1 || current.moves < minMoves) {
                     minMoves = current.moves;
                     bestNode = current;
                 }
             } 
             if (minMoves == -1 || current.moves + current.dist < minMoves) {
                 Iterable<Board> it = current.board.getNeighbours();
                 for (Board b : it) {
                     if (current.prev == null || !b.equals(current.prev.board)) {
                         pq.add(new Node(b, current.moves + 1, current));
                     }
                 }
             } else {
                 break;
             }
         }
    }
 
    private Node getRootNode(Node node) {
        Node current = node;
        while (current.prev != null) {
            current = current.prev;
        }
        return current;
    }
    public boolean isSolvable() {
        return solved;
    }
 
    public int moves() {
        return minMoves;
    }
 
    public Iterable<Board> getSolution() {
        if (isSolvable()) {
            Stack<Board> sol = new Stack<Board>();
            Node current = bestNode;
            while (current != null) {
                sol.push(current.board);
                current = current.prev;
            }
            return sol;
        }
        return null;
    }
 
    public static void main(String[] args) {
		System.out.print("Enter N: ");
		Scanner inputScanner = new Scanner(System.in);
	    int N = inputScanner.nextInt();
	    
        int[][] blocks = new int[N][N];
        
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++){
        		System.out.print("Enter the " + j + "th element at row " + i +": ");
    			int element = inputScanner.nextInt();
    			blocks[i][j] = element;
        	}     
        }
            
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        if (!solver.isSolvable())
            System.out.println("No solution possible");
        else {
        	System.out.println("Minimum number of moves = " + solver.moves());
        	Stack<Board> solution = (Stack<Board>) solver.getSolution();
            while (solution.size() != 0)
            	System.out.println(solution.pop());
        }
    }
}

 
