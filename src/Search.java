/*
 * Search Class
 * Tomas Larson
 * Performs that Minimax search on a given ply and starting board State.
 * 
 * 
 */
public class Search {
	
	//default constructor
	public Search() {
		
	}
	
	//Initiates the AI search.
	//After the AI finds the best Score Node it is brought up to the top.
	public Board mmSearch(Board board, int ply) {
		Node playerMove = new Node(null, board, true);
		Node aiMove = search(ply, playerMove);
		return trace(aiMove).boardState;
	}
	
	//Performs the recursive search 
	public Node search(int ply, Node node) {
		Node advNode = new Node();
			
		if ((ply == 0) || (node.gameOver()) ){
			node.setScore();
			return node;
		}
		successor(node, node.isMax);
		if (node.isMax) {
			boolean maxSet = false;
			for (Node n : node.childNodes) {
				Node scoreNode = new Node();
				scoreNode = search(ply - 1, n);
				if ((!maxSet) || (advNode.score < scoreNode.score)) {
					advNode = scoreNode;
					maxSet = true;
				}
			}
			return advNode;
		} else {
			boolean minSet = false;
			for (Node n : node.childNodes) {
				Node scoreNode = new Node();
				scoreNode = search(ply - 1, n);
				if ((!minSet) || (advNode.score > scoreNode.score)) {
					advNode = scoreNode;
					minSet = true;
				}
			}
			return advNode;
		}
	}
	
	//Generates the child nodes for a given node.
	public void successor(Node cur, boolean isMax) {
		
		Board tempBoard = new Board();
		int turnColor = 0;
		
		if (isMax) turnColor = 2;
		else turnColor = 1;
		
		for (int row = 0; row < 10; row++) {
			Board b = new Board(cur.boardState);
			b.addMove(row, turnColor);
			tempBoard = new Board(b);
			Node n = new Node(cur, tempBoard, !isMax);
			cur.addChild(n);
		}
	}
	
	//Traces the final advNode back to its initial move.
	public Node trace(Node node) {
		if (node.parent.parent == null) 
			return node;
		return trace(node.parent);
	}
	
		
	
}
