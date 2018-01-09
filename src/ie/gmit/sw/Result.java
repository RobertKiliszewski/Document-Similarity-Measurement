package ie.gmit.sw;

public class Result {
	
	private String newDocument;
	private String oldDocument;
	private double results;
	public Result(String newDoc, String oldDoc, double result) {
		super();
		newDocument = newDoc;
		this.oldDocument = oldDoc;
		this.results = result;
	}
	public String getNewDoc() {
		return newDocument;
	}
	public void setNewDoc(String newDoc) {
		newDocument = newDoc;
	}
	public String getOldDoc() {
		return oldDocument;
	}
	public void setOldDoc(String oldDoc) {
		this.oldDocument = oldDoc;
	}
	public double getResult() {
		return results;
	}
	public void setResult(double result) {
		this.results = result;
	}
	@Override
	public boolean equals(Object obj) {
		if((String) obj == this.getNewDoc())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}