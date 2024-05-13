/**
 * Represents a vocabulary containing words related to a specific topic
 * Each vocab object has a topic and words (stored in a singly linkedlist)
 *
 */
public class Vocab {
	String topic;
	SinglyLinkedList words; 
	
	/**
	 * Constructs a vocabulary with the specified topic 
	 * Initializes the list of words with an empty singly linkedlist
	 * @param topic (the topic of the vocabulary)
	 */
	public Vocab(String topic) {
		this.topic = topic;
		this.words = new SinglyLinkedList();
	}
}
