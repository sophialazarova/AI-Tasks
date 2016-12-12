import java.awt.Point;

public class State {
	int[][] board;
	double value;
	Point emptyFieldCoord;
	
	public State(int[][] board, double value, Point emptyFieldCoord) {
		this.board = board;
		this.value = value;
		this.emptyFieldCoord = emptyFieldCoord;
	}
}
