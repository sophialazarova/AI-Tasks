import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


// This implementation does not detect impossible to solve boards. Please enter only boards with solutions!!!!!
public class Solution {
	static int n;
	static int[][] board;
	static int[][] solvedBoard;
	static Scanner inputScanner;
	static String deepStringSolution;
	static HashMap<Integer, Point> solutionCoordinates;
	
	static PriorityQueue<State> opened = new PriorityQueue<State>();
	
	static List<State> visited = new ArrayList<State>();

	public static void main(String[] args) {
		System.out.print("Enter N: ");
		inputScanner = new Scanner(System.in);
	    n = inputScanner.nextInt();
	    
	    board = new int[n][n];
	    solvedBoard = new int[n][n];
	    
	    solutionCoordinates = new HashMap<Integer, Point>();
	    
	    fillSolvedBoard();
	    deepStringSolution = Arrays.deepToString(solvedBoard);
	    Point emptyFieldCoordSolution = fillPlayingBoard();
	    
	    State currentState = new State(board, calculateHeuristic(board), emptyFieldCoordSolution);
	    
	    opened.add(currentState);
	    State current = currentState;
	    
	    while(opened.size() != 0) {
	    	current = opened.poll();
	    	if  (current.value == 0.0) {
	    		break;
	    	}
	    	if (visited.contains(current)){
	    		continue;
	    	}
	    	visited.add(current);
	    	
	    	ArrayList<State> neighbours = getNeighbours(current);
	    	for(int i = 0; i < neighbours.size(); i++ ) {
	    		if (visited.get(visited.size()-1) != neighbours.get(i)) {
	    			opened.add(neighbours.get(i));
	    		}
	    	}
	    }
	    
	    for(int i = 0; i < visited.size(); i++ ) {
	    	System.out.println(Arrays.deepToString(visited.get(i).board));
    	}
	    System.out.println(Arrays.deepToString(current.board));
	    
	}
	
	public static ArrayList<State> getNeighbours(State currentState) {
		ArrayList<State> result = new ArrayList<State>();
		Point emptyField = currentState.emptyFieldCoord;
		if (emptyField.getX() + 1 < currentState.board.length){
			int[][] copy =  createCopy(currentState.board);
			copy[emptyField.x][emptyField.y] = copy[emptyField.x+1][emptyField.y];
			copy[emptyField.x+1][emptyField.y] = 0;
			result.add(new State(copy, calculateHeuristic(copy), new Point(emptyField.x+1, emptyField.y)));
		}
		if (emptyField.getX() - 1 >= 0) {
			int[][] copy = createCopy(currentState.board);
			copy[emptyField.x][emptyField.y] = copy[emptyField.x-1][emptyField.y];
			copy[emptyField.x-1][emptyField.y] = 0;
			result.add(new State(copy, calculateHeuristic(copy), new Point(emptyField.x-1, emptyField.y)));
		}
		if (emptyField.getY() + 1 < currentState.board.length) {
			int[][] copy = createCopy(currentState.board);
			copy[emptyField.x][emptyField.y] = copy[emptyField.x][emptyField.y+1];
			copy[emptyField.x][emptyField.y+1] = 0;
			result.add(new State(copy, calculateHeuristic(copy), new Point(emptyField.x, emptyField.y+1)));
		}
		if (emptyField.getY() - 1 >= 0) {
			int[][] copy = createCopy(currentState.board);
			copy[emptyField.x][emptyField.y] = copy[emptyField.x][emptyField.y-1];
			copy[emptyField.x][emptyField.y-1] = 0;
			result.add(new State(copy, calculateHeuristic(copy), new Point(emptyField.x, emptyField.y-1)));
		}
		
		return result;
	}
	
	public static int[][] createCopy(int[][] board) {
		int[][] result = new int[board.length][board.length];
		for (int i = 0; i < board.length; i ++){
			for (int j = 0; j < board.length; j ++){
				result[i][j] = board[i][j];
			}
		}
		
		return result;
	}
	
	public static void fillSolvedBoard() {
		int counter = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j< n; j++) {
				if (counter == n*n) {
					solvedBoard[i][j] = 0;
					solutionCoordinates.put(0, new Point(i,j));
					
					break;
				}
				solvedBoard[i][j] = counter;
				solutionCoordinates.put(counter, new Point(i,j));
				counter++;
			}
		}
	}
	
	public static Point fillPlayingBoard() {	
		Point emptyCoord = null;
		for (int i = 0; i < n; i++) {
			System.out.print("Enter the " + n + " elements at row " + i + ": (with commas, example 1,2,3): " );
			String inputLine = inputScanner.next();
			String[] splitInput = inputLine.split(",");
			
			for(int j = 0; j < splitInput.length; j++) {
				int value = Integer.parseInt(splitInput[j]);
				board[i][j] = value;
				if (value == 0){
					emptyCoord = new Point(i,j);
				}
			}
		}
		
		return emptyCoord;
	}
	
	public static double calculateHeuristic(int[][] board) {
		double heuristic = 0;
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board.length; j ++){
				Point correctPosition = solutionCoordinates.get(board[i][j]);
				double xDistance =  Math.abs(i-correctPosition.getX());
				double yDistance = Math.abs(j-correctPosition.getY());
				heuristic += (xDistance + yDistance);
			}
		}
		
		return heuristic;
	}
}
