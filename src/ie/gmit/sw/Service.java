package ie.gmit.sw;

//Imports
import java.util.concurrent.BlockingQueue;

import com.db4o.ObjectContainer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Service implements Runnable{
	
	//Variables
	private BlockingQueue<ServiceJob> inQ = new ArrayBlockingQueue<ServiceJob>(80);
	private BlockingQueue<List<Result>> outQ = new ArrayBlockingQueue<List<Result>>(80);
	private ServiceJob sj = null;
	private ObjectContainer db = null;
	private List<Document> dList = new ArrayList<Document>();
	private List<Result> rList = new ArrayList<Result>();
	private MinHash mh;
	
	//Constructor
	public Service() {
			
	}
	
	//Constructor with blocking queue
	public Service(BlockingQueue <ServiceJob> inQ, BlockingQueue<List<Result>> outQ) {
		super();
		this.inQ = inQ;
		this.outQ = outQ;
	}
	
	//Run
	public void run() {
		
		//run if true
		while(true) {
			
			//Check every couple of sec
			inQ = Global.getInQ();
			sj = inQ.poll();
			
			//do nothing if thread is unavailable
			if(sj != null) {
				DatabaseRunner db;
				try {
					db = new DatabaseRunner();
					dList = db.getDocuments();
					mh = new MinHash(dList, sj.getDocument());
					rList = mh.Compute();
					db.addDocumentsToDatabase(sj.getDocument());
					db.closeDB();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				//Compare Docs

				System.out.println("list size result"+rList.size());
				Global.addToOutQ(rList);
				outQ =  Global.getOutQ();

			}//if
			
		}//while
		
	}//run
	
}//Runner