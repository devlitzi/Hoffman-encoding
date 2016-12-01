import java.util.ArrayList;
import java.util.Collections;


public class Huffman {
	
	public static void main(String[] args){
		ArrayList<String> input = new ArrayList<String>();
		for (String arg : args){
			input.add(arg);
		}
		//make our nodes	
		ArrayList<Node> nodes = createNodes(input);
		System.out.println(nodes.toString());
		//process our nodes to create tree
		Node tree = constructTree(nodes);
		System.out.println(tree.toString());
		makeCodes(tree);
		
	}

	private static void makeCodes(Node tree) {
		//need to iterate the whole tree
		
	}

	private static Node constructTree(ArrayList<Node> nodes) {
		//sort in reverse order to start at beginning
		Collections.sort(nodes);
		//initialize tree
		Node tree = new Node(nodes.remove(0), nodes.remove(0));
		for (Node node : nodes){
			tree = Node.addtoTree(tree, node);
		}
		return tree;
	}

	private static ArrayList<Node> createNodes(ArrayList<String> input) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < input.size(); i = i + 2){
			Node tempNode = new Node(input.get(i), Integer.parseInt(input.get(i+1)));
			nodes.add(tempNode);
		}
		return nodes;
	}
}
