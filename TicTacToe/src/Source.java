import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class PossibleMove {
    int score;
    Point point;

    PossibleMove(int score, Point point) {
        this.score = score;
        this.point = point;
    }
}

public class Source {
	private static int[][] board = { {-1,-1,-1}, {-1,-1,-1}, {-1,-1,-1} };
	private static List<Point> availablePoints;
	private static List<PossibleMove> possibleMoves = new ArrayList<>();
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		System.out.println("Tic Tac Toe!");
		System.out.println("You are first, your symbol is 'X'! Type the index of your move in the current format **  row col  **");

		while (!isGameOver()) {
			parseUserMove();
			
			if (isGameOver()) {
				break;
			}
			
			alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
			
			Point bestMove = returnBestMove();
            fillMoveOnBoard(bestMove.x, bestMove.y, 0);
            System.out.println();
            System.out.println("Computer's move: ");
            printBoard();
		}
		
        if (checkIfWins(0)) {
            System.out.println("Loser! You lost!");
        } else if (checkIfWins(1)) {
            System.out.println("Great one, champ! You won!");
        } else {
            System.out.println("DRAW! Maybe next time..");
        }
	}
	
    private static Point returnBestMove() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < possibleMoves.size(); ++i) {
            if (MAX < possibleMoves.get(i).score) {
                MAX = possibleMoves.get(i).score;
                best = i;
            }
        }

        return possibleMoves.get(best).point;
    }

	private static void parseUserMove() throws IOException {
		boolean valid = false;
		int row = -1;
		int col = -1;
		System.out.println();
		System.out.println("Enter your move:");
		while (valid == false) {
			String moveString = br.readLine();
			String[] index = moveString.split(" ");
			row = Integer.parseInt(index[0]);
			col = Integer.parseInt(index[1]);
			valid = checkIfUserMoveIsValid(row,col);
			if (!valid) {
				System.out.println("Invalid move. Your turn again!");
			}
		}
		
		fillMoveOnBoard(row, col, 1);
		printBoard();
	}
	
	private static boolean checkIfUserMoveIsValid(int row, int col) {
		if (row > board.length-1 || col > board.length-1 || board[row][col] != -1) {
			return false;
		}
		
		return true;
	}
	
	private static void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == -1) {
					System.out.print(" | " + " ");
				}
				else {
					System.out.print(" | " + (board[i][j] == 1 ? "X" : "O"));
				}
			}
			System.out.print(" |");
			System.out.println();
		}
	}
	
	private static void fillMoveOnBoard(int x, int y, int playerSymbol) {
		board[x][y] = playerSymbol;
	}
	
    private static boolean isGameOver() {
        return (checkIfWins(0) || checkIfWins(1) || getAvailableStates().isEmpty());
    }
    
    private static boolean checkIfWins(int playerSymbol) {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == playerSymbol) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == playerSymbol)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == playerSymbol)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == playerSymbol))) {
                return true;
            }
        }
        return false;
    }
    
    private static int evaluateBoard() {
        int score = 0;

        //Rows
        for (int i = 0; i < 3; ++i) {
            int userSymbolsCount = 0;
            int computerSymbolsCount = 0;
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 1) {
                	userSymbolsCount++;
                } else if (board[i][j] == 0) {
                	computerSymbolsCount++;
                }
            } 
            score += evaluateCombination(computerSymbolsCount, userSymbolsCount); 
        }

        //Columns
        for (int j = 0; j < 3; ++j) {
            int userSymbolsCount = 0;
            int computerSymbolsCount = 0;
            for (int i = 0; i < 3; ++i) {
                if (board[i][j] == 1) {
                	userSymbolsCount++;
                } else if(board[i][j] == 0){
                	computerSymbolsCount++;
                } 
            }
            score+=evaluateCombination(computerSymbolsCount, userSymbolsCount);
        }

        int userSymbolsCount = 0;
        int computerSymbolsCount = 0;

        //Diagonal
        for (int i = 0, j = 0; i < 3; ++i, ++j) {
            if (board[i][j] == 1) {
            	userSymbolsCount++;
            } else if (board[i][j] == 0) {
            	computerSymbolsCount++;
            }
        }

        score+=evaluateCombination(computerSymbolsCount, userSymbolsCount);

        userSymbolsCount = 0;
        computerSymbolsCount = 0;

        //Diagonal
        for (int i = 2, j = 0; i > -1; --i, ++j) {
            if (board[i][j] == 1) {
            	userSymbolsCount++;
            } else if (board[i][j] == 0) {
            	computerSymbolsCount++;
            }
        }

        score+=evaluateCombination(computerSymbolsCount, userSymbolsCount);

        return score;
    }
    
    // +100 for 3 in-a-line for computer
    // +10 for 2 in-a-line for computer (with empty cell)
    // +1 for 1 in-a-line for computer (with two empty cells)
    // Negative scores for user (respectively -100, -10, -1)
    // Otherwise 0 (both user and computer symbols are presented in sample or empty line)
    private static int evaluateCombination(int computerSymbols, int userSymbols){
        int evaluation;
        if (computerSymbols == 3) {
        	evaluation = 100;
        } else if (computerSymbols == 2 && userSymbols == 0) {
        	evaluation = 10;
        } else if (computerSymbols == 1 && userSymbols == 0) {
        	evaluation = 1;
        } else if (userSymbols == 3) {
        	evaluation = -100;
        } else if (userSymbols == 2 && computerSymbols == 0) {
        	evaluation = -10;
        } else if (userSymbols == 1 && computerSymbols == 0) {
        	evaluation = -1;
        } else {
        	evaluation = 0;
        } 
        return evaluation;
    }
	
    private static List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == -1) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }
    
private static int alphaBetaMinimax(int alpha, int beta, int depth, int turn){
        
        if(beta<=alpha){ 
        	if(turn == 0) {
        		return Integer.MAX_VALUE;
        	} else {
        		return Integer.MIN_VALUE; 
        	}
        }
        
        if(isGameOver()) {
        	return evaluateBoard();
        }
        
        List<Point> pointsAvailable = getAvailableStates();
        
        if(pointsAvailable.isEmpty()) {
        	return 0;
        }
        
        if (depth == 0) {
        	possibleMoves.clear(); 
        }
        
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        
        for (int i=0;i<pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);
            
            int currentScore = 0;
            
            if (turn == 0) {
            	fillMoveOnBoard(point.x, point.y, 0); 
                currentScore = alphaBetaMinimax(alpha, beta, depth+1, 1);
                maxValue = Math.max(maxValue, currentScore); 
                
                alpha = Math.max(currentScore, alpha);
                
                if (depth == 0) {
                	possibleMoves.add(new PossibleMove(currentScore, point));
                }
                   
            } else if (turn == 1) {
            	fillMoveOnBoard(point.x, point.y, 1);
                currentScore = alphaBetaMinimax(alpha, beta, depth+1, 0); 
                minValue = Math.min(minValue, currentScore);
                
                beta = Math.min(currentScore, beta);
            }
            
            board[point.x][point.y] = -1; 
            
            if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) {
            	break;
            }
        }
        return turn == 0 ? maxValue : minValue;
    }  
}
