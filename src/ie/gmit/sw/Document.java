// Package usage declaration
package ie.gmit.sw;

//Imports
import java.util.*;

public class Document {
	
	//Variables
	private List shingleList;
	private String documentID;
	private String documentName;
	
	//Document Constructor
	public Document(String ID, String name, List list) {
		super();
		this.documentID = ID;
		this.documentName = name;
		this.shingleList = list;
	}//Document Constructor
	
	//GETTERS AND SETTERS
	
	//----------------------------------------------------GETSHINGLELIST----------------------------------------------------
	public List getShingleList() {
		return shingleList;
	}
	//----------------------------------------------------GETSHINGLELIST----------------------------------------------------
	
	
	//----------------------------------------------------SETSHINGLELIST----------------------------------------------------
	public void setShingleList(List shingleList) {
		this.shingleList = shingleList;
	}
	//----------------------------------------------------SETSHINGLELIST----------------------------------------------------
	
	
	
	//----------------------------------------------------GETDOCUMENTID----------------------------------------------------
	public String getDocumentID() {
		return documentID;
	}
	//----------------------------------------------------GETDOCUMENTID----------------------------------------------------
	
	
	//----------------------------------------------------SETDOCUMENTID----------------------------------------------------
	public void setDocumentID(String ID) {
		this.documentID = ID;
	}
	//----------------------------------------------------SETDOCUMENTID----------------------------------------------------
	
	
	//----------------------------------------------------GETDOCUMENTNAME----------------------------------------------------
	public String getDocumentName() {
		return documentName;
	}
	//----------------------------------------------------GETDOCUMENTNAME----------------------------------------------------
	
	
	//----------------------------------------------------SETDOCUMENTNAME----------------------------------------------------
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	//----------------------------------------------------SETDOCUMENTNAME----------------------------------------------------
}//Document