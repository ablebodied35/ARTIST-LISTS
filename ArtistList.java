import java.io.*;

public class ArtistList {

	//Basically the first link, the first node, or the "head" of the arraylist
	private Node head;

	
	public ArtistList(){
		head = null;
	}
	
	//Obligatory getter method I always do when I have private data members
	public Node getHead() {
		return head;
	}
	
	public boolean isEmpty(){
	     return (head == null);
	}
	
	
	/******************************************************************************************
	 I am still not very confident in my understandings of arraylist. I understand how 
	 each node contains the address of the next node but I can't seem to wrap my head 
	 around the code. I will try my best to explain what I did here. I created a node first,
	 then I set the next, which is a node object, to equal head which is null. Then, I 
	 set head equal to the artist I had given the method in its parameter. So the head contains
	 the first ever name for the linked list.
	 ******************************************************************************************/
	public void insertFirst(String name) {
		Node artist = new Node(name);
		
		artist.setNext(head); //artist.next = head
		
		head = artist;
	}
	
	
	//This method was for my own tinkering around with. It just prints the linked list to the console
	public void display(ArtistList artist) {
		
		Node current = head;
		while (current != null) {
			
			current.display();
			
			current = (current.getNext());  
		}
		
	}
		
	//This method prints the linked list to a file. That is why it contains a printwriter object.
	//Doing it this way made the whole process much faster and used less code.
	public void display(ArtistList artist, PrintWriter out) {
		
		Node current = head;
		while (current != null) {	
			current.display(out);
			current = (current.getNext());  
		}
	}
}
