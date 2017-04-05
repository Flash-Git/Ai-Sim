package dev.flash.aisim;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Flash on 30/03/2017.
 */

public class AStar {
	
	//sorts a given list of Nodes by F cost
	private static final Comparator<Node> listCompF = new Comparator<Node>() {
		@Override
		public int compare(Node a, Node b) {
			if(a.calcF() < b.calcF())
				return -1;
			else if(a.calcF() > b.calcF())
				return 1;
			return 0;
		}
	};
	
	//sorts a given list of Nodes by H cost
	private static final Comparator<Node> listCompH = new Comparator<Node>() {
		@Override
		public int compare(Node a, Node b) {
			if(a.calcH() < b.calcH())
				return -1;
			else if(a.calcH() > b.calcH())
				return 1;
			return 0;
		}
	};
	
	private AStar() {
	}
	
	public static void generatePath(Handler handler, ArrayList<Node> path, Vector2 start, Vector2 target) {
		
		//reset static variables
		for(Node n : World.allNodes) {
			n.setParent(null);
			n.setWeight(0);
		}
		
		int checkNum = 100;
		ArrayList<Node> open = new ArrayList<>();//open list of Nodes (to check)
		ArrayList<Node> closed = new ArrayList<>();//closed list of Nodes (checked)
		
		//Clear all 3 lists
		open.clear();
		closed.clear();
		path.clear();
		
		Node parent = containsNode((int) start.x, (int) start.y, World.allNodes);//Node that points to the Node that led to it's creation //Initial Node
		
		if(parent == null) {
			System.err.println("no node found on allNodes list at " + parent.getX() + ", " + parent.getY() + " for initial parent node");
			return;
		}
		Node n = null;//Node that takes the values of the nodes as we handle them
		
		open.add(parent);//Adds our initial node to the open list (to check and get available children)
		Node.dest = containsNode((int) target.x, (int) target.y, World.allNodes);//static destination node
		if(Node.dest == null) {
			System.err.println("no node found on allNodes list at " + Node.dest.getX() + ", " + Node.dest.getY() + " for destination node");
			return;
		}
		//Start of for loop
		for(int q = 0; q < checkNum; ++q) {
			open.sort(listCompF);//Sorts open list by f cost
			if(open.size() == 0) {//temp TODO
				break;
			}
			
			n = open.get(0);//n is now lowest F cost Node in open list
			
			open.remove(0);//remove n from open list
			closed.add(n);//add n to closed list
			
			//TODO CRASH HERE WHEN PUTTING CREATURES OUT OF MAP
			
			if(n.getX() == Node.dest.getX() && n.getY() == Node.dest.getY()) {//if n is the target, exit the loop //might need switching up later
				break;
			}
			
			int nX = n.getX();
			int nY = n.getY();
			
			//Check neighbours
			
			boolean top;
			boolean bot;
			boolean left;
			boolean right;
			
			
			//Check Top
			if(top = checkValid(handler, nX, nY - 1)) {
				checkNode(handler, nX, nY - 1, n, open, closed);
			}
			
			//Check Bot
			if(bot = checkValid(handler, nX, nY + 1)) {
				checkNode(handler, nX, nY + 1, n, open, closed);
			}
			
			//Check Left
			if(left = checkValid(handler, nX - 1, nY)) {
				checkNode(handler, nX - 1, nY, n, open, closed);
			}
			
			//Check Right
			if(right = checkValid(handler, nX + 1, nY)) {
				checkNode(handler, nX + 1, nY, n, open, closed);
			}
			
			if(top && left) {//Check Top Left
				if(checkValid(handler, nX - 1, nY - 1)) {
					checkNode(handler, nX - 1, nY - 1, n, open, closed);
				}
			}
			
			if(top && right) {//Check Top Right
				if(checkValid(handler, nX + 1, nY - 1)) {
					checkNode(handler, nX + 1, nY - 1, n, open, closed);
				}
			}
			
			if(bot && left) {//Check Bot Left
				if(checkValid(handler, nX - 1, nY + 1)) {
					checkNode(handler, nX - 1, nY + 1, n, open, closed);
				}
			}
			
			if(bot && right) {//Check Bot Right
				if(checkValid(handler, nX + 1, nY + 1)) {
					checkNode(handler, nX + 1, nY + 1, n, open, closed);
				}
			}
		}//end of for loop
		
		//if q has reached it's limit (target unreachable or out of path searching range)  or finished? (does this cause problems?)
		closed.sort(listCompH);//sort list of Nodes by distance to target
		n = closed.get(0);//hovering position if wanted
		
		//wipe open list and use it to generate the path from end to start by looping through parents
		open.clear();
		open.add(n);//adds best Node to fresh open list
		Node p = n.parent;
		while(p != null) {//backtracks through the parent relationship until there are no more parents (starting position)
			open.add(p);
			p = p.parent;
		}
		
		//reverse this new open list, adding each node to path
		for(int i = open.size() - 1; i >= 0; --i) {//create the path
			path.add(open.get(i));
		}
		path.remove(0);//remove starting position
	}
	
	private static boolean checkValid(Handler handler, int x, int y) {
		if((x > handler.getWorld().getWidth()-1)) {
			return false;//if node is out of bounds
		}
		if((x < 0)) {
			return false;//if node is out of bounds
		}
		if((y > handler.getWorld().getHeight()-1)) {
			return false;//if node is out of bounds
		}
		if((y < 0)) {
			return false;//if node is out of bounds
		}
		
		if(!handler.getEntityManager().posClear(new Vector2(x, y))) {
			return false;
		}
		return true;
	}
	
	//check the node, if it is an available Node, add it to open list
	private static boolean checkNode(Handler handler, int x, int y, Node parent, ArrayList<Node> open, ArrayList<Node> closed) {
		
		if(containsNode(x, y, closed) != null) {
			return false;//if node is in closed list
		}
		
		
		Node oldNode = containsNode(x, y, open);//is not null if Node has previously been used (on open list)
		Node newNode = containsNode(x, y, World.allNodes);
		newNode.setParent(parent);
		
		
		if(oldNode != null) {//if this Node is already on open list
			if(oldNode.calcG() > newNode.calcG()) {//if new Node has lower G cost, leave it's parent as is//TODO check if f or g is better here
				oldNode.setParent(parent);//if new Node has higher G cost, change old Node's parent to that of the new one
				return true;//don't add this new Node to open list (it's already on it)
			}
			return true;
		}//else if the Node was not on open list
		open.add(newNode);//add this Node to open list
		return true;
	}
	
	//checks if a Node exists on the given list at x, y
	public static Node containsNode(int x, int y, ArrayList<Node> list) {
		for(Node n : list) {
			if(n.getX() == x && n.getY() == y) {
				return n;
			}
		}
		return null;//if Node isn't on list, return null
	}
	
}
