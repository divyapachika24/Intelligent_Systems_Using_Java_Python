

package astaralgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Astar {
	public PriorityQueue<Node> fringe;
	public static Node inputnode;
	public static Node goalnode;
	int numberofnodes = 0;

	public static void main(String[] args) {
		
		/*
		 * Taking the input Goal state, Type of Heuristic and Input state from the User
		 */

		System.out.println("Enter input state");
		inputnode = createNode();
		System.out.println("Enter goal state");
		goalnode = createNode();
		if (!checkIfValid(inputnode.getState()) || !checkIfValid(goalnode.getState()))
			return;
		System.out.println("Enter Heuristic");
		String heuristictype;
		Scanner sc = new Scanner(System.in);
		heuristictype = sc.next();
		sc.close();
		System.out.println();
		Astar obj = new Astar();
		
		/*
		 * Sending all the inputs to find the best path with the selected heuristic type
		 */
		
		
		obj.getBestPath(inputnode, goalnode, heuristictype);

	}

	private void getBestPath(Node inputnode, Node goalnode, String heuristic) {
		// TODO Auto-generated method stub
		int inputstate = 0;
		inputnode.setGcost(0);
		inputnode.setStateId(inputstate++);
		inputnode.setParentId(0);
		if (heuristic.equalsIgnoreCase("Manhattan"))
			inputnode.setHcost(getManhattanCost(inputnode.getState()));
		if (heuristic.equalsIgnoreCase("Misplaced"))
			inputnode.setHcost(getMisplacedCost(inputnode.getState()));
		ArrayList<Node> explored = new ArrayList<Node>();
		/*
		 * Creating a Priority Queue which will sort the nodes based upon the Fcost
		 */
		fringe = new PriorityQueue<>(200, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.getFcost() - o2.getFcost();
			}
		});
		fringe.add(inputnode);
		while (!fringe.isEmpty()) {
			Node expandnode = fringe.poll();
			explored.add(expandnode);
			
			/*
			 * Checking if the expanding node as hcost=0 to exit the loop while reaching the goal state
			 */
		
			if (expandnode.getHcost() == 0) {

				System.out.println(heuristic);
				System.out.println();
				System.out.println("==========================================");
				printpath(expandnode, explored);
				System.out.println("Number of Nodes Generated : " + numberofnodes);
				System.out.println();
				System.out.println("Number of Nodes explored : " + explored.size());
				System.out.println();
				System.out.println("==========================================");
				break;
			}
			/*
			 * Generate fringe with return all the possible children for each expanding node and later add them into the fringe.
			 */
			ArrayList<Node> childstates = generatFringe(expandnode);
			for (int i = 0; i < childstates.size(); i++) {
				Node child = childstates.get(i);
				child.setStateId(inputstate++);
				/*
				 * Checking if the node is in explored or fringe Queue
				 */
				if (!checkinExplored(child, explored) || !checkinPriorityQueue(child, fringe)) {
					if (heuristic.equalsIgnoreCase("Misplaced")) {
						int hvalue = getMisplacedCost(child.getState());
						child.setHcost(hvalue);
						child.setFcost(child.getGcost() + child.getHcost());
					}

					if (heuristic.equalsIgnoreCase("Manhattan")) {
						int hvalue = getManhattanCost(child.getState());
						child.setHcost(hvalue);
						child.setFcost(child.getGcost() + child.getHcost());
					}

				}
				/*
				 * Checking if the child is repeated before copying
				 */
				for (int ii = 0; ii <= fringe.size(); ii++) {
					if (fringe.size() == 0) {
						fringe.add(child);
					}

					else {
						if (!fringe.contains(child))
							fringe.add(child);
					}
				}
			}
		}

	}

	private boolean checkinPriorityQueue(Node next, PriorityQueue<Node> fringe2) {
		// TODO Auto-generated method stub
		//checking in Priority Queue if node is available
		Iterator<Node> it = fringe2.iterator();
		while (it.hasNext()) {
			if (checkEqual(next, it.next()))
				return true;
		}
		return false;
	}

	private boolean checkinExplored(Node next, ArrayList<Node> explored) {
		// TODO Auto-generated method stub
		//checking in Explored List if node is avaialable
		for (int i = 0; i < explored.size(); i++) {
			if (checkEqual(next, explored.get(i))) {
				return true;
			}
		}
		return false;
	}

	private boolean checkEqual(Node node1, Node node2) {
		//Checking if two nodes are equal
		// TODO Auto-generated method stub
		int[][] node1State = node1.getState();
		int[][] node2State = node2.getState();
		for (int i = 0; i < node1State.length; i++)
			for (int j = 0; j < node2State.length; j++)
				if (node1State[i][j] != node2State[i][j])
					return false;
		return true;

	}

	private void printpath(Node expandnode, ArrayList<Node> explored) {
		// TODO Auto-generated method stub
		for (int i = 0; i < explored.size(); i++) {
			printPuzzle(explored.get(i));
		}

	}

	private static int getMisplacedCost(int[][] arr) {
		// TODO Auto-generated method stub
		int costm = 0;
		int[][] goalstate = goalnode.getState();
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (arr[i][j] != 0) {
					if (arr[i][j] != goalstate[i][j])
						costm++;
				}
			}
		}
		return costm;
	}

	private static int getManhattanCost(int[][] arr) {
		// TODO Auto-generated method stub
		int searchelement[] = new int[2];
		int cost = 0;
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (arr[i][j] != 0) {
					searchelement = getSearchIndex(arr[i][j]);
					int k = searchelement[0];
					int l = searchelement[1];
					// int heuristic=(int) Math.sqrt(Math.pow(k-i, 2)+Math.pow(l-j, 2));
					int heuristic = Math.abs(k - i) + Math.abs(l - j);
					cost = cost + heuristic;
				}
			}
		}
		return cost;

	}

	private static int[] getSearchIndex(int value) {
		// TODO Auto-generated method stub
		int[] valueindex = new int[2];
		int[][] goalstate = goalnode.getState();
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (goalstate[i][j] == value) {
					valueindex[0] = i;
					valueindex[1] = j;
				}
			}
		}
		return valueindex;
	}

	private ArrayList<Node> generatFringe(Node expandingnode) {
		// TODO Auto-generated method stub
		//Generating the children for the expanding node
		int zerox = 0, zeroy = 0;
		ArrayList<Node> childstates = new ArrayList<Node>();
		Node leftchild = new Node();
		Node rightchild = new Node();
		Node topchild = new Node();
		Node bottomchild = new Node();
		int left[][] = new int[3][3];
		int right[][] = new int[3][3];
		int top[][] = new int[3][3];
		int bottom[][] = new int[3][3];
		int input2[][] = expandingnode.getState();
		for (int i = 0; i < input2.length; i++)
			for (int j = 0; j < input2[i].length; j++) {
				left[i][j] = input2[i][j];
				right[i][j] = input2[i][j];
				top[i][j] = input2[i][j];
				bottom[i][j] = input2[i][j];
			}
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (input2[i][j] == 0) {
					zerox = i;
					zeroy = j;
				}

			}
		}

		if (zeroy - 1 >= 0) {
			int temp;
			temp = left[zerox][zeroy];
			left[zerox][zeroy] = left[zerox][zeroy - 1];
			left[zerox][zeroy - 1] = temp;

		}
		if (zeroy + 1 <= 2) {
			int temp;
			temp = right[zerox][zeroy];
			right[zerox][zeroy] = right[zerox][zeroy + 1];
			right[zerox][zeroy + 1] = temp;
		}
		if (zerox - 1 >= 0) {
			int temp;
			temp = top[zerox][zeroy];
			top[zerox][zeroy] = top[zerox - 1][zeroy];
			top[zerox - 1][zeroy] = temp;
		}
		if (zerox + 1 <= 2) {
			int temp;
			temp = bottom[zerox][zeroy];
			bottom[zerox][zeroy] = bottom[zerox + 1][zeroy];
			bottom[zerox + 1][zeroy] = temp;
		}
		if (!EqualArrays.checkequal(left, input2)) {
			leftchild.setState(left);
			leftchild.setGcost(expandingnode.getGcost() + 1);
			numberofnodes++;
			childstates.add(leftchild);
		}

		if (!EqualArrays.checkequal(right, input2)) {
			rightchild.setState(right);
			rightchild.setGcost(expandingnode.getGcost() + 1);
			numberofnodes++;
			childstates.add(rightchild);
		}
		if (!EqualArrays.checkequal(top, input2)) {
			topchild.setState(top);
			topchild.setGcost(expandingnode.getGcost() + 1);
			numberofnodes++;
			childstates.add(topchild);
		}
		if (!EqualArrays.checkequal(bottom, input2)) {
			bottomchild.setState(bottom);
			bottomchild.setGcost(expandingnode.getGcost() + 1);
			numberofnodes++;
			childstates.add(bottomchild);
		}
		return childstates;
	}

	private static void printPuzzle(Node gnode) {
		// TODO Auto-generated method stub
		//Printing the 8 puzzle
		int[][] input = new int[3][3];
		input = gnode.state;
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {

				System.out.print(input[i][j] + " ");

			}
			System.out.println();
			System.out.println();

		}
		System.out.println("G(n) = " + gnode.getGcost() + "  " + "F(n)= " + gnode.getFcost() + "  " + "H(n)= "
				+ gnode.getHcost());
		System.out.println();
	}

	private static boolean checkIfValid(int[][] state) {
		// TODO Auto-generated method stub
		Set<Integer> values = new HashSet<>();

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (!values.add(state[i][j])) {
					System.out.print("Please enter unique values");
					return false;
				}
			}
		}

		return true;
	}

	private static Node createNode() {
		//This method is used to create the node
		// TODO Auto-generated method stub
		int input[][] = new int[3][3];
		String inputstring = "";
		BufferedReader IR = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				try {
					inputstring = IR.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				input[i][j] = Integer.parseInt(inputstring);
			}
		}
		Node node = new Node();
		node.state = input;
		return node;
	}

}
