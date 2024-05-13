/**
 * Represents a doubly linkedlist data structure for storing nodes containing vocabulary items
 * Each node in the list contains a reference to the next, the previous node and a Vocab object
 * @author manelhellou
 *
 */
public class DoublyLinkedList {
	Node head; 
	Node tail; 
	
	/**
	 * Constructs an empty doubly linkedlist
	 * Head and tail are set to null
	 */
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * Adds a node containing a specified vocabulary item to the end of the doubly linkedlist
	 * @param vocab (the vocab object of the node) 
	 */
	public void addNodeToEnd(Vocab vocab) {
		Node endNode = new Node(vocab);
		if(head == null) {
			head = endNode;
			tail = endNode;
		}
		else {
			tail.next = endNode;
			endNode.prev = tail; 
			tail = endNode;
			
		}
	}
	
	/**
	 * Adds a node containing a specified vocabulary item after the specified node
	 * @param node (the node after which the new node will be added)
	 * @param vocab (the vocab object of the new node)
	 */
	public void addNodeAfter(Node node, Vocab vocab) {
		Node newNode = new Node(vocab);
		if(node == null) {
			if(head == null) {
				head = newNode;
				tail = newNode;
			}
			else {
				tail.next = newNode;
				newNode.prev = tail; 
				tail = newNode;
				
			}
		}
		else {
			newNode.next = node.next;
			if(node.next != null) {
				node.next.prev = newNode;
			}
			else {
				tail = newNode;
			}
			node.next = newNode;
			newNode.prev = node;
		}

	}
	
	/**
	 * Adds a node containing a specified vocabulary item before the specified node
	 * @param node (the node before which the new node will be added)
	 * @param vocab (the vocab object of the new node)
	 */
	public void addNodeBefore(Node node, Vocab vocab) {
		Node newNode = new Node(vocab);
		if(node == null) {
			if(head == null) {
				head = newNode;
				tail = newNode;
			}
			else {
				newNode.next = head;
				head.prev = newNode; 
				head = newNode;
				
			}
		}
		else {
			newNode.prev = node.prev;
			if(node.prev != null) {
				node.prev.next = newNode;
			}
			else {
				head = newNode;
			}
			node.prev = newNode;
			newNode.next = node;
		}
	}
	
	/**
	 * Removes the specified node from the list
	 * @param nodeToRemove (node to remove)
	 */
	public void removeNode(Node nodeToRemove) {
		if(nodeToRemove == null) {
			return;
		}
		
		if(nodeToRemove.prev != null) {
			nodeToRemove.prev.next = nodeToRemove.next;
		}
		else {
			head = nodeToRemove.next;
		}
		
		if(nodeToRemove.next != null) {
			nodeToRemove.next.prev = nodeToRemove.prev;
		}
		else {
			tail = nodeToRemove.prev;
		}
	}
}































