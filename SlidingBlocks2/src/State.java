import java.awt.Point;

public class State implements Comparable{
	int[][] board;
	double value;
	Point emptyFieldCoord;
	
	public State(int[][] board, double value, Point emptyFieldCoord) {
		this.board = board;
		this.value = value;
		this.emptyFieldCoord = emptyFieldCoord;
	}

	@Override
	public int compareTo(Object o) {
		State other = (State)o;
		if (other.value > this.value) {
			return -1;
		}
		else if (other.value < this.value) {
			return 1;
		}
		return 0;
	}
}
