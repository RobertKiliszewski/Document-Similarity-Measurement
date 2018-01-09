package ie.gmit.sw;

import java.io.IOException;
import java.util.*;

public class MinHash {
	//Variables
	private List<Document> documentList = new ArrayList<Document>();
	private Document d;
	private TreeSet<Integer> hashCodes = new TreeSet<Integer>();
	private List<Integer> newDocuments = new ArrayList<Integer>();
	private List<Integer> previousDocuments = new ArrayList<Integer>();
	private List<Integer> common = new ArrayList<Integer>();
	private List<Result> resultsList = new ArrayList<Result>();
	private final int MAX_MIN_HASH = 200;
	private double jaccard;
	private Result results;
	
	//Constructor
	public MinHash(List<Document> dList, Document d) {
		super();
		this.documentList = dList;
		this.d = d;
	}
	
	//Compute the numbers
	public List Compute(){
		hashCodes = generateNumbers();
		newDocuments = generateMinHashes(d);
		for(Document doc : documentList){
			previousDocuments = generateMinHashes(doc);
			common.addAll(newDocuments);
			common.retainAll(previousDocuments);
			System.out.println("New Document Size After Change: "+ newDocuments.size());
			System.out.println("Similarity After Change: "+ common.size());
			jaccard = ((double)common.size()) / newDocuments.size();
			System.out.println("Jaccard is: " + jaccard);
			results = new Result(d.getDocumentName(), doc.getDocumentName(), jaccard);
			resultsList.add(results);
			common.clear();
		}
		
		return resultsList;
		
	}
	
	//Generate The Numbers
	public TreeSet<Integer> generateNumbers(){
		hashCodes = new TreeSet<Integer>();
		Random r = new Random();
		for(int i=0; i < MAX_MIN_HASH; i++){
			hashCodes.add(r.nextInt());
		}
		return hashCodes;
	}
	
	public List<Integer> generateMinHashes(Document doc){
		List<Integer> temp = new ArrayList<Integer>();
		List<Shingle> s = new ArrayList<Shingle>();
		s = doc.getShingleList();
		System.out.println(doc.getDocumentName());
		
		for(Integer hash : hashCodes){
			int min = Integer.MAX_VALUE;
			for(int i = 0 ; i < s.size(); i++){
				int minHash = s.get(i).getHashCode()^hash;
		        if(minHash < min){
		        	min = minHash;
			    }//if
		        
			}//inner for
			
			temp.add(min);
		}//outter for 
		
		return temp;
	}//GenerateMinHhashes
}
