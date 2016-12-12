import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;


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
	    
	    fillSolvedBoard();
	    Point emptyFieldCoordSolution = fillPlayingBoard();
	    
	    deepStringSolution = Arrays.deepToString(solvedBoard);
	    solutionCoordinates = new HashMap<Integer, Point>();
	    
	    opened.add(new State(board, calculateHeuristic(board), emptyFieldCoordSolution));
	   
	    do {
	    	State current = opened.poll();
	    	visited.add(current);
	    
	    }
	    while(Arrays.deepToString(board) != deepStringSolution);
	    
	    
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
			System.out.print("Enter the " + n + " elements at row " + i + ": " );
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
