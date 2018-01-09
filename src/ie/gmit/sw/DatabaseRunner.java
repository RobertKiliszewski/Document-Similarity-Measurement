package ie.gmit.sw;

//Imports
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentActivationSupport;
import com.db4o.ta.TransparentPersistenceSupport;

import xtea_db4o.XTEA;
import xtea_db4o.XTeaEncryptionStorage;

public class DatabaseRunner implements Database{
	
	//Variables
	private ObjectContainer db = null;
	private List<Document> dList = new ArrayList<Document>();
	private Reader r;
	private List<Shingle> shingles = new ArrayList<Shingle>();
	private Document d;

	//Database Runner Class for the database 
	public DatabaseRunner() throws IOException {
		System.out.println("In db40 : ");
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().add(new TransparentActivationSupport()); 
		config.common().add(new TransparentPersistenceSupport()); 
		config.common().updateDepth(7);
		
		//Encryption
		config.file().storage(new XTeaEncryptionStorage("password", XTEA.ITERATIONS64));

		//Open local DB 
		db = Db4oEmbedded.openFile(config, "documents.data");
		dList = getDocuments();
		
		if(dList.size() == 0) {
			init(); //Populate files and insert into db40
		}
		
		showDocuments();
		
	
	}

	private void init() throws IOException {
		System.out.println("in init");
		int i = 0;
		//Find Existing Files in This Directory
		File dir = new File("C:/Users/Robert/Desktop/Document Similarity Measurer/Document Similarity Measurer/uploader");
		//Loop through the folder for files, put those files into shingles, then save them to the database
		for (File file : dir.listFiles()) {
	   	   i++;
		   r = new Reader(file, "r" + i);
		   shingles = r.newShingle();
		   d = new Document("r" + i, file.getName(), shingles);
		   // Save 
		   dList.add(d);
		}
		addFilesToDatabase();
	}
	
	private void addFilesToDatabase(){
		for(Document d: dList)
		{
			//store the objects in the database
			db.store(d);
		}
		//commit the change
		db.commit();
	}
	
	//Add documents to the database
	public void addDocumentsToDatabase(Document d) {
		//Store and commit
		db.store(d);
		db.commit();	
	}
	
	public void showDocuments()
	{
		//ObjectSet is a list to store files
		ObjectSet<Document> documents = db.query(Document.class);
		for (Document document : documents) {
			System.out.println("[Document] " + document.getDocumentName() + "\t ***Database Object ID: " + db.ext().getID(document));
			db.commit();
		}
	}
	
	//Close the database when finished
	public void closeDB()
	{
		db.close();
	}
	
	//Get the documents in theh folder
	public List getDocuments()
	{
		List<Document> temp = new ArrayList<Document>();
		ObjectSet<Document> documents = db.query(Document.class);
		System.out.println("In Docs: " + documents.size());
		for (Document document : documents) {
			temp.add(document);
			db.commit();
		}
		return temp;	
	}
	
	//Main for database Runner
	public static void main(String[] args) throws IOException
	{
		new DatabaseRunner();
	}
}