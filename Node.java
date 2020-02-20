import java.io.*;
public class Node {
	private String name;
	private Node next;
	
	public Node(String name) {
		this.name = name;
	}
	
	//This method prints the name out to a file. It is used in conjunction with the display method 
	//in class ArtistList
	public void display(PrintWriter out) {
		out.println(name);
	}
	
	//This method is for my tinkering around with. Prints name to console
	public void display() {
		System.out.println(name);
	}
	
	//To string for the name object
	public String toString() {
		String line = String.format("%-20s", name);
		return line;
	}
	
	//Obligatory getters and setters for the node.
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
	
	

}
