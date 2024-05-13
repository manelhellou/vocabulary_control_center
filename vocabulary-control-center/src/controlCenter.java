import java.io.*;
import java.util.*;

/**
 * Represents a control center to manage the menu, vocab words and topics
 *
 */
public class controlCenter {
	private DoublyLinkedList vocab_list; 
	private Scanner in; 
	
	/**
	 * Constructs a control center with an empty vocabulary list and initializes the empty doubly linkedlist and the scanner for user input
	 */
	public controlCenter() {
		this.vocab_list = new DoublyLinkedList();
		this.in = new Scanner(System.in);
	}
	
	/**
	 * Reads vocabulary data from the specified input file and populates the vocabulary list accordingly
	 * @param filename (name of file to read from)
	 */
	public void readInputFile(String filename) {
		Scanner scanner = null;
		try{
			scanner = new Scanner(new File(filename));
			String currentTopic = null;
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if(!line.isEmpty()) {
					if(line.startsWith("#")){
						currentTopic = line.substring(1).trim();
						vocab_list.addNodeToEnd(new Vocab(currentTopic));
					}
					else {
						if(currentTopic != null) {
							vocab_list.tail.vocab.words.addWordOrder(line);
						}
						else {
							System.out.println("Error: There is no topic before the vocab words");
							return;
						}
					}
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			scanner.close();
		}
	}
	
	/**
	 * Saves the current vocabulary data to the specified output file
	 * @param filename (name of file to write to)
	 */
	public void saveToFile(String filename) {
		try (FileWriter writer = new FileWriter(filename)){
			Node currentNode = vocab_list.head;
			while(currentNode != null) {
				writer.write("#" + currentNode.vocab.topic + "\n");
				Node currentWordNode = currentNode.vocab.words.head;
				
				while(currentWordNode != null) {
					writer.write(currentWordNode.word + "\n");
					currentWordNode = currentWordNode.next;
				}
				
				currentNode = currentNode.next;
			}
			System.out.println("Data saved to file " + filename + " successfully.");
		}
		catch (IOException e){
			System.out.println("An error occured when saving to file: " + filename);
		}
	}
	
	/**
	 * Displays the main menu and performs the appropriate operations depending on the otpion selected by user
	 */
	public void displayMenu() {
		int choice; 
		
		do {
			System.out.println("==============================");
			System.out.println(" Vocabulary Control Center");
			System.out.println("==============================");
			System.out.println("1. Browse Topics");
			System.out.println("2. Insert a new topic before another one");
			System.out.println("3. Insert a new topic after another one");
			System.out.println("4. Remove a topic");
			System.out.println("5. Modify a topic");
			System.out.println("6. Search topics for a word");
			System.out.println("7. Load from a file");
			System.out.println("8. Show all words starting with a given letter");
			System.out.println("9. Save to file");
			System.out.println("0. Exit");
			System.out.println("==============================");
			System.out.println("Enter your choice: ");
			
			choice = in.nextInt();
			in.nextLine();
			
			int topicChoice;
			
			switch(choice) {
			//Browse a topic
				case 1:
					do {
						System.out.println("==============================");
						System.out.println("        Pick a Topic");
						System.out.println("==============================");
						
						Node currentNode = vocab_list.head;
						int count = 1;
						
						while(currentNode != null) {
							System.out.println(count + ". " + currentNode.vocab.topic);
							currentNode = currentNode.next;
							count++;
						}
						
						
						System.out.println("0. Exit");
						System.out.println("==============================");
						System.out.println("Enter your choice: ");
						topicChoice = in.nextInt();
						in.nextLine();
						
						if(topicChoice < 0 || topicChoice >= count) {
							System.out.println("Please enter a valid option from 0 to " + (count-1) + ".\n");
							continue;
						}
						if(topicChoice != 0) {
							currentNode = vocab_list.head;
							count = 1;
							while(currentNode != null && count < topicChoice) {
								currentNode = currentNode.next;
								count++;
							}
							System.out.println("Topic: " + currentNode.vocab.topic);
							currentNode.vocab.words.printWithNumbers();
						}
						
					}
					while(topicChoice != 0);
					break;
				
				//Insert a new topic before another one
				case 2:
						System.out.println("==============================");
						System.out.println("        Pick a Topic");
						System.out.println("==============================");
						
						Node currentNode = vocab_list.head;
						int count = 1;
						
						while(currentNode != null) {
							System.out.println(count + ". " + currentNode.vocab.topic);
							currentNode = currentNode.next;
							count++;
						}
						
						
						System.out.println("0. Exit");
						System.out.println("==============================");
						System.out.println("Enter your choice: ");
						topicChoice = in.nextInt();
						in.nextLine();
						
						if(topicChoice < 0 || topicChoice >= count) {
							System.out.println("Please enter a valid option from 0 to " + (count-1) + ".\n");
							continue;
						}
						if(topicChoice != 0) {
							System.out.println("Enter a topic name: ");
							String newTopic = in.nextLine();
							System.out.println("Enter a word - to quit press Enter: ");
							String newWords = in.nextLine();
							
							Vocab newVocab = new Vocab(newTopic);
							String[] wordArray = newWords.split("\\s+");
							
							SinglyLinkedList newList = new SinglyLinkedList();
							for(String word : wordArray) {
								newList.addWordOrder(word);
							}
							
							newVocab.words = newList;
							
							Node newCurrentNode = vocab_list.head;
							int newCount = 1;
							while(newCurrentNode != null && newCount < topicChoice) {
								newCurrentNode = newCurrentNode.next;
								newCount++;
							}
							vocab_list.addNodeBefore(newCurrentNode, newVocab);
						}	
					break;
					
				//Insert a new topic after another one
				case 3:
						System.out.println("==============================");
						System.out.println("        Pick a Topic");
						System.out.println("==============================");
						
						currentNode = vocab_list.head;
						count = 1;
						
						while(currentNode != null) {
							System.out.println(count + ". " + currentNode.vocab.topic);
							currentNode = currentNode.next;
							count++;
						}
						
						
						System.out.println("0. Exit");
						System.out.println("==============================");
						System.out.println("Enter your choice: ");
						topicChoice = in.nextInt();
						in.nextLine();
						
						if(topicChoice < 0 || topicChoice >= count) {
							System.out.println("Please enter a valid option from 0 to " + (count-1) + ".\n");
							continue;
						}
						if(topicChoice != 0) {
							System.out.println("Enter a topic name: ");
							String newTopic = in.nextLine();
							System.out.println("Enter a word - to quit press Enter: ");
							String newWords = in.nextLine();
							
							Vocab newVocab = new Vocab(newTopic);
							String[] wordArray = newWords.split("\\s+");
							
							SinglyLinkedList newList = new SinglyLinkedList();
							for(String word : wordArray) {
								newList.addWordOrder(word);
							}
							
							newVocab.words = newList;
							
							Node newCurrentNode = vocab_list.head;
							int newCount = 1;
							while(newCurrentNode != null && newCount < topicChoice) {
								newCurrentNode = newCurrentNode.next;
								newCount++;
							}
							vocab_list.addNodeAfter(newCurrentNode, newVocab);
						}	
					break;
					
				//Remove a topic
				case 4:
						System.out.println("==============================");
						System.out.println("        Pick a Topic");
						System.out.println("==============================");
						
						currentNode = vocab_list.head;
						count = 1;
						
						while(currentNode != null) {
							System.out.println(count + ". " + currentNode.vocab.topic);
							currentNode = currentNode.next;
							count++;
						}
						
						System.out.println("0. Exit");
						System.out.println("==============================");
						
						System.out.println("Enter your choice: ");
						topicChoice = in.nextInt();
						in.nextLine();
						
						if(topicChoice < 0 || topicChoice >= count) {
							System.out.println("Please enter a valid option from 0 to " + (count-1) + ".\n");
							continue;
						}
						if(topicChoice != 0) {
							Node newCurrentNode = vocab_list.head;
							int newCount = 1;
							while(newCurrentNode != null && newCount < topicChoice) {
								newCurrentNode = newCurrentNode.next;
								newCount++;
							}
							vocab_list.removeNode(newCurrentNode);
						}	
					break;
				
				//Modify a topic 
				case 5:
						System.out.println("==============================");
						System.out.println("        Pick a Topic");
						System.out.println("==============================");
						
						currentNode = vocab_list.head;
						count = 1;
						
						while(currentNode != null) {
							System.out.println(count + ". " + currentNode.vocab.topic);
							currentNode = currentNode.next;
							count++;
						}
						
						
						System.out.println("0. Exit");
						System.out.println("==============================");
						System.out.println("Enter your choice: ");
						topicChoice = in.nextInt();
						in.nextLine();
						
						if(topicChoice < 0 || topicChoice >= count) {
							System.out.println("Please enter a valid option from 0 to " + (count-1) + ".\n");
							continue;
						}
						if(topicChoice != 0) {
							Node newCurrentNode = vocab_list.head;
							int newCount = 1;
							while(newCurrentNode != null && newCount < topicChoice) {
								newCurrentNode = newCurrentNode.next;
								newCount++;
							}
						
						String modifyTopic; 
							System.out.println("==============================");
							System.out.println("        Modify Topics Menu");
							System.out.println("==============================");
							System.out.println("a. Add a word");
							System.out.println("r. Remove a word");
							System.out.println("c. Change a word");
							System.out.println("0. Exit");
							System.out.println("==============================");
							System.out.println("Enter your choice: ");
							
							modifyTopic = in.next().toLowerCase();

							if(!modifyTopic.equals("0")) {
								switch(modifyTopic) {
									case "a":
										System.out.println("Type a word and press Enter, or press Enter to end input: ");
										String newWord = in.next();
										newCurrentNode.vocab.words.addWordOrder(newWord);
										break;
									case "r":
										System.out.println("Enter a word: ");
										String wordToRemove = in.next();
										newCurrentNode.vocab.words.removeWord(wordToRemove);
										break;
									case "c":
										System.out.println("Enter the word you want to change: ");
										String wordToChange= in.next();
										System.out.println("Enter the word you want to change it to: ");
										String newWordChange = in.next();
										newCurrentNode.vocab.words.changeWord(wordToChange, newWordChange);
										break;
									case "0":
										System.out.println("\nYou will be brought back to the main menu.\n");
										break;
									default:
										System.out.println("Please enter a valid option a, r, c or 0.");
										break;
								}
							}
						}	
					break;
					
				//Search topics for a word
				case 6:
						System.out.println("==============================");
						System.out.println("        Pick a Topic");
						System.out.println("==============================");
						
						currentNode = vocab_list.head;
						count = 1;
						
						while(currentNode != null) {
							System.out.println(count + ". " + currentNode.vocab.topic);
							currentNode = currentNode.next;
							count++;
						}
						
						
						System.out.println("0. Exit");
						System.out.println("==============================");
						System.out.println("Enter your choice: ");
						topicChoice = in.nextInt();
						in.nextLine();
						
						if(topicChoice < 0 || topicChoice >= count) {
							System.out.println("Please enter a valid option from 0 to " + (count-1) + ".\n");
							continue;
						}
						if(topicChoice != 0) {
							currentNode = vocab_list.head;
							int newCount = 1;
							while(currentNode != null && newCount < topicChoice) {
								currentNode = currentNode.next;
								newCount++;
							}
							System.out.println("Enter the word you are looking for: ");
							String wordToLookFor = in.next();
							
							boolean containsWord = currentNode.vocab.words.containsWord(wordToLookFor);
							if(containsWord) {
								System.out.println("The topic " + currentNode.vocab.topic + " contains the word " + wordToLookFor);
							}
							else {
								System.out.println("The topic " + currentNode.vocab.topic + " does not contain the word " + wordToLookFor);
							}
						}	
					break;
					
				//Load from file
				case 7:
					System.out.println("Enter the name of the input file: ");
					String inputFileName = in.next();
					readInputFile(inputFileName);
					System.out.println("Done loading.");
					break;
					
				//Show all words starting with a given letter
				case 8:
					System.out.println("Enter the letter to search for: ");
					char letter = in.next().toUpperCase().charAt(0);
					
					ArrayList<String> wordsStartingWithLetter = new ArrayList<>();
					
					currentNode = vocab_list.head;
					while(currentNode != null) {
						Node currentWordNode = currentNode.vocab.words.head;
						while(currentWordNode != null) {
							String word = currentWordNode.word;
						
							if(word.toUpperCase().charAt(0) == letter) {
								wordsStartingWithLetter.add(word);
							}
							currentWordNode = currentWordNode.next;
						}
						currentNode = currentNode.next;
					}
					
					Collections.sort(wordsStartingWithLetter);
					
					System.out.println("Words starting with letter: " + letter);
					for(int i = 0; i<wordsStartingWithLetter.size(); i++) {
						System.out.println((i+1) + ": " + wordsStartingWithLetter.get(i));
					}
				
				//Save to file
				case 9:
					System.out.println("Enter the name of the file to write to: ");
					String outputFileName = in.next();
					saveToFile(outputFileName);
					System.out.println("Done writing.");
					break;
				//Exit
				case 0:
					System.out.println("\nThe program will now terminate.\n");
					System.exit(0);
					break;
					
				//If user enters invalid input
				default:
					System.out.println("\nPlease enter a valid option from 0 to 9.\n");
					break;
				
				
			}
			
			
		}
		while(choice !=0);
	}
	
	//Main method to call control center and use it
	public static void main(String[] args) {
		controlCenter controlCenter1 = new controlCenter();
		controlCenter1.displayMenu();
	}

}
