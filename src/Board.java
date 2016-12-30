/*
 * Board Class
 * Tomas Larson
 * Holds the board state and the functions that manipulate it.
 * 
 * 
 */


public class Board {
	
	//The board in a 2d Array
	int[][] board = { {0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}
					};
	
	//The working total of each number on the board, updated by the add function
	int[] boardTotal = {0, 0 ,0};
	
	//default constructor
	public Board() {
		
	}
	
	//copy constructor
	public Board(Board oldBoard) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j] = oldBoard.board[i][j];
			}
		}
		boardTotal[0] = oldBoard.boardTotal[0];
		boardTotal[1] = oldBoard.boardTotal[1];
		boardTotal[2] = oldBoard.boardTotal[2];
	}
	
	
	//Adds a move to the board in a given col, NO ERROR CHECKING
	//calls updateTotal to update the score of the color placed.
	public boolean addMove(int col, int color) {
		boolean placed = false;
		for (int row = 9; row > -1; row--) {
			if (board[row][col] == 0 ) {
				board[row][col] = color;
				boardTotal[color] += updateTotal(row, col, color);
				placed = true;
				break;
			}
		}
		return placed;
	}
	
	//Determines a valid "connection" acts as a sort of switch board for the board.
	//This function determines how much "score" is added for a given move
	public int updateTotal(int row, int col, int color) {
		int spotTotalc = 0;
		
		int rowMinusOne = row - 1;
		int colMinusOne = col - 1;
		int rowPlusOne = row + 1;
		int colPlusOne = col + 1;
		
		boolean validTop = (rowMinusOne > -1) ? true : false;
		boolean validLeft = (colMinusOne > -1) ? true : false;
		boolean validBottom = (rowPlusOne < 10) ? true : false;
		boolean validRight = (colPlusOne < 10) ? true : false;
		
		//diagonals
		if ((validTop && validLeft) && board[rowMinusOne][colMinusOne] == color)
			spotTotalc++;
		if ((validTop && validRight) && board[rowMinusOne][colPlusOne] == color)
			spotTotalc++;
		if ((validBottom && validLeft) && board[rowPlusOne][colMinusOne] == color)
			spotTotalc++;
		if ((validBottom && validRight) && board[rowPlusOne][colPlusOne] == color)
			spotTotalc++;
		
		if ((validTop) && board[rowMinusOne][col] == color)
			spotTotalc += 2;
		if ((validBottom) && board[rowPlusOne][col] == color)
			spotTotalc += 2;
		if ((validLeft) && board[row][colMinusOne] == color)
			spotTotalc += 2;
		if ((validRight) && board[row][colPlusOne] == color)
			spotTotalc += 2;
		
		return spotTotalc;
	}
	
	//prints the board in a nice way. 1s turn to "O"s and 2s turn to "X"s
	public void printBoard() {
		String s;
		String c = "";
		for (int i = 0; i < 10; i++) {
			s = "";
			for (int j = 0; j < 10; j++) {
				if (board[i][j] == 1) c = "O";
				else if (board[i][j] == 2) c = "X";
				else if (board[i][j] == 0) c = "·";
				s += " " + c; 
			}
			System.out.println(s);
		}
	}
	
	//Counts the amount of free spaces remaining on the board. In this model 
	//free spaces exists as 0s.
	public int countZeros() {
		int count = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (board[i][j] == 0) 
					count++;
			}
		}
		return count;
	}
 	
}
