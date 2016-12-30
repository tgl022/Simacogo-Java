import java.util.ArrayList;
/*
 * Tomas Larson
 * Node Class
 * 
 * Contains a Board object, Array of children, and a parent Node.
 * holds a total cost of all its parents and itself
 * 
 */

public class Node {

	Node parent;
		
	boolean isMax;
	ArrayList<Node> childNodes = new ArrayList<Node>();
	
	//boolean isScored = false;
	int score = 0;
	
	
	Board boardState;
	
	public Node() {
		
	}
	
	public Node(Node par) {
		parent = par;
		boardState = new Board();
	}
	
	//Node constructor
	//adds the previous totalCost to the current Node.state.cost
	public Node(Node p, Board newBoard, boolean max) {
		parent = p;
		isMax = max;
		boardState = new Board(newBoard);	
	}
		
	//Add a child not to the childNodes ArrayList
	public void addChild(Node n) {
		childNodes.add(n);
	}
	
	public void setScore() {
		score = boardState.boardTotal[2] - boardState.boardTotal[1];
		//isScored = true;
	}
	
	public boolean gameOver() {
		//return true if number of '0's equals 0
		if (boardState.countZeros() == 0) 
			return true;
		return false;
	}

}
