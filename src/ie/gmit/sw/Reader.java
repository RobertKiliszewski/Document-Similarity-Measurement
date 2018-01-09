package ie.gmit.sw;

//Imports
import java.io.*;
import java.util.*;
import javax.servlet.http.Part;


public class Reader {
	
	// Variables
	private List<Shingle> shingleList = new ArrayList<Shingle>();
	private BufferedReader br;
	private Part part;
	private String documentID;
	private String line = null;
	private String[] words = null;
	private Shingle shingle;
	private File textFile;
	
	
	//Constructor
	public Reader(Part part, String ID) {
		super();
		this.part = part;
		this.documentID = ID;
	}//Constructor
	
	
	//Second Constructor
	public Reader(File textFile, String ID){
		super();
		this.textFile = textFile;
		this.documentID = ID;
	}// Second Constructor
	
	
	// Create a New Shingle
	public List newShingle() throws IOException{
		if(part != null){
			//Buffer Reader for reading from document
			br = new BufferedReader(new InputStreamReader(part.getInputStream()));
		}
		else{
			// reads in from our document
			br = new BufferedReader(new FileReader(textFile));
		}
		
		// Count in order to see if the shingle number was reached
		int count = 0;
		
		//Document Reader
		BufferedReader br = new BufferedReader(new InputStreamReader(part.getInputStream()));
		
		// Create New Shingle
		StringBuilder sb = new StringBuilder();
		
		// While line is not equal to null keep looping
		while((line = br.readLine()) != null) {
			
			//Ignore punctuation
			words = line.split("\\W+");
			
			// For loop 
			for(int i = 0; i < words.length; i++) {
				// Increment count
				count++;
				
				// Add new Word
				sb.append(words[i]);
				
				//When count = 3 create new shingle
				if(count == 3) {
					
					System.out.println(sb.toString());
					shingle = new Shingle(this.documentID, sb.toString().hashCode());
					shingleList.add(shingle);
					
					// Whehn shingle is made return to 0
					sb.delete(0,  sb.length());
					count = 0;
					
				}// End of if
				
			}// End of for loop
			
		}// End of while loop
		
		// Return shingle list
		return shingleList;
		
	}//New Shingle
}//Reader