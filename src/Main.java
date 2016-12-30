import java.util.Scanner;

/*
 * Tomas Larson
 * Main Class
 * 
 * Runs from this function.
 * Contains all the UI code and Program Object.
 * 
 */

public class Main {

	//Main Function
	public static void main(String[] args){
		Search s = new Search();
		int userMove = 0;
		int ply = 0;
		Board board = new Board();
		boolean gameover = false;
		
		Scanner in = new Scanner(System.in);
		
		//get ply input
		System.out.println("Enter a ply: ");
		ply = in.nextInt();
		
		//loop
		while (!gameover) {
		
			
			//get user input board
			System.out.println("Enter a row (0 - 9): ");
			userMove = in.nextInt();
			
			board.addMove(userMove, 1);
			if (board.countZeros() == 0) 
				gameover = true;
			else { 
			//Computer Moves
				board = s.mmSearch(new Board(board), ply);
				
				board.printBoard();
				System.out.println("Player Score: " + board.boardTotal[1]);
				System.out.println("AI Score: " + board.boardTotal[2]);
				
				if (board.countZeros() == 0)
					gameover = true;
				
			}
		}
		in.close();
		//end loop
		
	}	
}