/**
 * Houda Filali 40276607
 * Manel Hellou 40284245
 * 
 *Class SinglyLinkedList that represents singly linkedlist data to store the words
 *Each node contains a word and a reference to the next node
 */

public class SinglyLinkedList {
	Node head;
	
	/**
	 * Constructs an empty singly linkedlist
	 */
	public SinglyLinkedList() {
		this.head = null;
	}
	
	/**
	 * Checks if singly linkedlist contains a certain word
	 * @param word (word we are searching for)
	 * @return	true if the word is found; false otherwise
	 */
	public boolean containsWord(String word) {
		Node currentNode = head;
		while(currentNode != null) {
			if(currentNode.word.toLowerCase().equals(word.toLowerCase())) {
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}
	
	/**
	 * Adds a word to the list and places it at the appropriate place to keep words alphabetically sorted
	 * If the word already exists in the list, it won't get added
	 * @param word (the word to add to the list)
	 */
	public void addWordOrder(String word) {
		if(containsWord(word)) {
			System.out.println("The word " + word + " is already listed. You can't list a word twice.");
			return;
		}
		
		Node newNode = new Node(word);
		if(head == null || word.toLowerCase().compareTo(head.word.toLowerCase()) < 0) {
			newNode.next = head;
			head = newNode;
			return;
		}
		Node currentNode = head; 
		while (currentNode.next != null && word.toLowerCase().compareTo(currentNode.next.word.toLowerCase()) >= 0) {
			currentNode = currentNode.next;
		}
		
		newNode.next = currentNode.next;
		currentNode.next = newNode;
	}
	

	/**
	 * Removes the specified word from the list
	 * @param word (the word to remove)
	 */
	public void removeWord(String word) {
		if(!containsWord(word)) {
			System.out.println("The word " + word + " is not listed.");
			return;
		}
		Node currentNode = head;
		Node prevNode = null;
		
		while(currentNode!= null && !currentNode.word.toLowerCase().equals(word.toLowerCase())) {
			prevNode = currentNode;
			currentNode = currentNode.next;
		}
		if(currentNode != null) {
			if(currentNode == head) {
				head = currentNode.next;
			}
			else {
				prevNode.next = currentNode.next;
			}
		}
	}
	
	/**
	 * Changes a specified word in the list for another one
	 * @param wordToChange (word in the list that we want to change)
	 * @param newWord (word we want to change it to)
	 */
	public void changeWord(String wordToChange, String newWord) {
		if(!containsWord(wordToChange)) {
			System.out.println("The word " + wordToChange + "is not listed.");
			return;
		}
		Node currentNode = head;
		while(currentNode!= null && !currentNode.word.toLowerCase().equals(wordToChange.toLowerCase())) {
			currentNode = currentNode.next;
		}
		currentNode.word = newWord;
	}
	
	/**
	 * Prints the words in the linked list in their corresponding numbers
	 */
	public void printWithNumbers() {
		Node currentNode = head;
		int count = 1;
		
		while(currentNode != null) {
			System.out.println(count + ": " + currentNode.word);
			currentNode = currentNode.next;
			count++;
		}
	}
	
	
	
}
