package ie.gmit.sw;


public class ServiceJob {

    private Document document; 
	
	public ServiceJob(Document document) {
		super();
		this.setDocument(document);
	}
	
	
	public Document getDocument() {
		return document;
	}

	
	public void setDocument(Document document) {
		this.document = document;
	}
}