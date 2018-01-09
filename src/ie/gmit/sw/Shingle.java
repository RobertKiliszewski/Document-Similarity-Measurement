package ie.gmit.sw;

public class Shingle {
	private String documentID;
	private int hashCode;

	
	public Shingle(String docID, int hashCode) {
		super();
		this.setDocID(docID);
		this.setHashCode(hashCode);
	}
	
	
	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	
	public String getDocID() {
		return documentID;
	}
	

	public void setDocID(String docID) {
		this.documentID = docID;
	}
}