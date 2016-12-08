import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


public class Huffman {

	private static Scanner reader;

	public static void main(String[] args){
		System.out.println("input your language");
		reader = new Scanner(System.in);
		String userInput1 = reader.nextLine();

		//System.out.println(userInput1);
		if (userInput1.equals("x")){
			System.out.println("Goodbye! :)");
			return;
		}

		ArrayList<String> input = new ArrayList<String>();
		String[] args1 = userInput1.split(" ");
		for (String arg : args1){
			input.add(arg);
		}
		//make our nodes
		ArrayList<String> letters = getLetters(input);
		ArrayList<Node> nodes = createNodes(input);
		//		System.out.println(letters.toString());
		//		System.out.println(nodes.toString());
		//process our nodes to create tree
		Node tree = constructTree(nodes);
		//System.out.println(tree.toString());
		//Get encoding for each input and dump in Hashmap
		HashMap<String, String> coding = new HashMap<String, String>();
		for (String letter : letters){
			coding.put(letter, getCode(tree, letter, ""));
		}
		System.out.println(coding.toString());
		while (true){
			System.out.println("encode or decode your word");
			ArrayList<String> input2 = new ArrayList<String>();
			String userInput2 = reader.nextLine();
			String[] args2 = userInput2.split(" ");
			for (String arg : args2){
				input2.add(arg);
			}
			if (input2.get(0).equals("e")){
				Object encodedString = encode(input2.get(1), coding);
				System.out.println("encoding: " + encodedString);

			} else if (input2.get(0).equals("d")){
				Object decodedString = decode(input2.get(1), coding);
				System.out.println("decoding: " + decodedString);

			} else if (input2.get(0).equals("x")){
				System.out.println("Goodbye! :)");
				break;
			} else {
				System.out.println("invalid input, start over");
			}

		}
	}



	private static String decode(String word, HashMap<String, String> coding) {
		String encodedString = "";
		while (word.length() > 0){
			for (int i = 1; i < word.length() + 1; i++){
				if (coding.containsValue(word.substring(0, i))) {
					encodedString += getkey(word.substring(0, i), coding);		
					word = word.substring(i);
				}
			}
		}
		return encodedString;

	}



	private static String getkey(String substring, HashMap<String, String> coding) {
		Object[] keys = coding.keySet().toArray();
		for (Object key : keys) {
			if (coding.get(key).equals(substring)) {
				return (String) key;
			}
		}
		return null;
	}



	private static String encode(String word, HashMap<String, String> coding) {
		String encodedString = "";
		for (String letter : word.split("")){
			if (coding.containsKey(letter)){
				encodedString += coding.get(letter);
			}
		}
		return encodedString;

	}



	private static String getCode(Node tree, String letter, String code) {
		if (tree.getLeftChild().isLetter() && tree.getLeftChild().getLetter() == letter) {
			code += 1;
		} else if (tree.getRightChild().isLetter() && tree.getRightChild().getLetter() == letter){
			code += 0;
		} else if (!tree.getLeftChild().isLetter()){
			code = getCode(tree.getLeftChild(), letter, code + "1");
		} else if (!tree.getRightChild().isLetter()) {
			code = getCode(tree.getRightChild(), letter, code + "0");	
		}
		return code;
	}

	private static ArrayList<String> getLetters(ArrayList<String> input) {
		ArrayList<String> nodes = new ArrayList<String>();
		for (int i = 0; i < input.size(); i = i + 2){
			String temp = input.get(i);
			nodes.add(temp);
		}
		return nodes;
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
