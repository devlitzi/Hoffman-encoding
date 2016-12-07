
public class Node implements Comparable<Node> {
	private boolean isLetter;
	private String letter;
	private int value;
	private Node leftChild;
	private Node rightChild;
	
	//This is the constructor for the type of node that have letters and values
	public Node (String letter, int value){
		this.isLetter = true;
		this.letter = letter;
		this.value = value;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	//This is the constructor for the type of node that initializes our tree (and is the head of that tree)
	public Node (Node leftChild, Node rightChild) {
		this.isLetter = false;
		this.value = leftChild.value + rightChild.value;
		if (leftChild.value > rightChild.value){
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		} else {
			this.leftChild = rightChild;
			this.rightChild = leftChild;
		}
		
	}
	
	public Node getLeftChild(){
		return this.leftChild;
	}
	
	public Node getRightChild(){
		return this.rightChild;
	}
	
	public static Node addtoTree(Node tree, Node node) {
		if (tree.value > node.value){
			return new Node(tree, node);
		} else {
			return new Node(node, tree);
		}
	}
	
	public String getLetter(){
		if (isLetter) {
			return this.letter;
		} else {
			throw new IndexOutOfBoundsException("You can't get a letter if there is no letter");
		}
	}
	
	public boolean isLetter(){
		return this.isLetter;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void setValue(int value){
		if (!isLetter){
			this.value = value;
		} else {
			throw new IndexOutOfBoundsException("You can't set the value if it has a letter");
		}
	}
	
	public String toString(){
		if (isLetter){
			return "{" + letter + ", " + value + "}";
		} else {
			return "{value: " + value + "; leftChild: " + leftChild.toString() + "; rightChild: " + rightChild.toString() +"}";
		}
		
	}
	
	public int compareTo(Node other){
		int compare = this.value - other.value;
	    return compare;
	}

}
