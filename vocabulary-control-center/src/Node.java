/**
 * Represents a node in the linked list (doubly or singly)
 *
 */
public class Node {
	Node prev;  //Reference to the previous node (only for doubly)
	Node next;  //Reference to the next node (for doubly and singly)
	Vocab vocab; //Vocabulary item stored in the node (for doubly)
	String word; //Word stored in the ndoe (for singly)
	
	/**
	 * For doubly linkedlist
	 * Constructs a node with a Vocab object
	 * @param vocab (the Vocab object to store in node)
	 */
	public Node(Vocab vocab){
		this.vocab = vocab;
	}	
	
	/**
	 * For singly linkedlist
	 * Constructs a node with a word
	 * @param word (the word to store in node)
	 */
	public Node(String word) {
		this.word = word;
	}

}
