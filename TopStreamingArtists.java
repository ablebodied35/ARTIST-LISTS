
import java.io.*;
import java.util.*;

public class TopStreamingArtists {

	public static void main(String[] args)throws IOException {
		
		//Files for input and output
		File input = new File("C:\\Users\\Shehryar\\Desktop\\CS\\Streamed Artists.csv");
		File output = new File("output.txt");
		Scanner read = new Scanner(input);
		PrintWriter out = new PrintWriter(output);
	
		//ArrayList in which I will read my data into. One holding strings for the names of artist
		//The other holding integers to count how many times the artist has shown up
		ArrayList<String> artist = new ArrayList<>();
		ArrayList<Integer> count = new ArrayList<>();
	
		//For some reason, to get the data to represent properly I have to skip the first line in the file 
		//I tried doing it without skipping and it would screw up the data
		String skip = read.nextLine();
	
		/**********************************************************************************************************
		While loop reads in data by line. The line is then split based on comma placements.
		Each word goes into a an array index. Index 2 holds the name as Index 0 holds their position and 
		index 1 holds the song name. After they are split the name is added to the artist arraylist 
		We also add an index for the count array list to match how many artist were read in.
	    You may be thinking that some of the artist names will be the same and I'm essentially making duplicates
		I am aware of that and I will fix up the arraylists later in the code
		**********************************************************************************************************/
		while(read.hasNext()) {
			String line = read.nextLine();
			String[] token = line.split(",");
			artist.add(token[2]);//Adds index and adds artist name to arraylist
			count.add(1);//Adds index to count arraylist
		}
		
		//The 0 index in the artist arraylist holds the term "Artist" instead of an actual artist. This is to delete that term
		artist.remove(0);
	
		/*******************************************************************************************************************************
		 This nested for loop will now count how many times each artist shows up and will start deleting 
		 any duplicates for the artists found in the artist arraylist. Essentially, the outer loop will act as a holder
		 for an artist name while the inner loop cycles through the arraylist starting from the end and going
		 until one place after the placeholder value. So if we are on position 7 it will search from the end
		 of the arraylist all the way to position 8. If it finds an artist name that matches its placeholder name
		 it will delete that name, it will remove the parallel count index to it and it will then add a 1 to the 
		 placeholders parallel count value. So for example, if position 20 is Selena Gomez in the artist arraylist, then in the parallel account 
		 arraylist we will have a value of 1 for index 20 as well. This nested for loop will look throughout the arraylist
		 for another selena gomez beyond position 20(if thats where the first selena gomez shows up). If lets say it
		 finds one at position 43, it will delete index 43 for BOTH arraylists and add a value of one to index 20 in the count arraylist
		 to represent that we have found that selena gomez shows up twice. Now we have one selena gomez and 2 counts to represent her properly.
		 *******************************************************************************************************************************/
		for(int i = 0; i<artist.size();i++) {
			System.out.println("SIZE OF THE ARRAY: " + artist.size() + " SIZE OF COUNT: " + count.size() ); //This is just for me to better visualise 
	
			for (int j = artist.size()-1; j> i; j--) {
				
				if (artist.get(j).contentEquals(artist.get(i))) {
					artist.remove(j);
					count.remove(j);
					count.set(i, count.get(i) + 1);		
				}
			}
		}
		
		//This loop just removes the "(quotation marks) at the beginning and end of some of the artist names. 
		//So a name like "Anuel AA" will now be Anuel AA.
		for(int i = 0; i < artist.size();i++) {
			if(artist.get(i).charAt(0) == '"') {
				
			   //Basically, finds whether a name contains a " at the start. Then using the substring method
			   //I took the string from its index 1 position to its index - 1 position to get the name out
			   artist.set(i, artist.get(i).substring(1, artist.get(i).length()-1));
			}
		}

		//2D array to hold our artist and counts
		String[][] art = new String[artist.size()][2];
		String times;//Will be used to convert our count arraylist from Integers into Strings
		
		/*****************************************************************************************************
		 The outer loop holds the rows and inner loop holds columns. There are rows equal to how many artist
		 were read in. If j(column) is equal to 0(the first column) then we will add an artist name. If j 
		 is equal to 1(The second column) then we will add the count of how many times that artist appeared
		******************************************************************************************************/
		for(int i = 0; i < artist.size(); i++){
			for(int j = 0; j<2;j++) {
				times =  count.get(i).toString();//Converts the count object, which is an Integer, into a string
				if ( j == 0) {
					art[i][j] = artist.get(i);//Artist name
				}
				else {
					art[i][j] = times;//Artist counts
				}
			}
		}
		
		/********************************************************************************************************************
		Sorts arraylist of Names alphabetically in descending order, Z-A.Why I did it in descending order? Well, I originally
	 	arranged it in ascending order but when printing the linked list, it printed the names in descending order.
		In order to combat this in the simplest terms I thought to just rearrange my arraylist in
		descending order. That fixed my problem. My linked list printed properly, A-Z in the file after I made this change
		I also chose to sort it after I had read the arraylists into the 2D array to avoid any problems with the parallel
		Count arraylist
		*********************************************************************************************************************/
		Collections.sort(artist, Collections.reverseOrder());
		

		//Prints the 2D array to output file. printF used for formatting and neatness.
		out.printf("%-20s %-14s","ARTIST","SONGS ON CHART");
		out.println();
		for(int i = 0; i< artist.size();i++){
			
			out.printf("%-20s %-2s ",art[i][0],art[i][1]);
			out.println();
		}
		
		//This is where the linked list is created. It runs through the arraylist. I used the arraylist instead of the 2D array
		//because it was extremely easy to sort using the arraylist methods. 
		ArtistList first  = new ArtistList();
		for (int i = 0; i < artist.size();i++) {
			first.insertFirst(artist.get(i));
		}
		
		//These 3 lines will print the Linked List inside the output file. 
		out.println();
		out.println("ARTIST LIST IN ALPHABETICAL ORDER");
		first.display(first,out);//out is PrintWriter object. I am shuffling it through the classes to make printing out the list easier
	
		//Closing our scanner and printwriter objects
		read.close();
		out.close();
	}

}
