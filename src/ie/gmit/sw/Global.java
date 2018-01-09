package ie.gmit.sw;

//Imports
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Global {
	private static BlockingQueue<ServiceJob> inQ;
	private static BlockingQueue<List<Result>> outQ;
	
	//Constructor
	private Global()
	{
		
	}//Constructor

	//Blocking queue for in and out 
	public static synchronized Boolean init()
	{
		inQ = new ArrayBlockingQueue<ServiceJob>(80);
		outQ = new ArrayBlockingQueue<List<Result>>(80);
		return true;
	}

	//BLOCKING QUEUE
	public static BlockingQueue<ServiceJob> getInQ() {
		return inQ;
	}
	//BLOCKING QUEUE
	
	
	//SETINQ
	public static void setInQ(BlockingQueue<ServiceJob> inQueue) {
		Global.inQ = inQueue;
	}
	//SETINQ
	
	//BLOCKING QUEUE FOR RESULT
	public static BlockingQueue<List<Result>> getOutQ() {
		return outQ;
	}
	//BLOCKING QUEUE FOR RESULT
	
	//SETOUTQ
	public static void setOutQ(BlockingQueue<List<Result>> outQueue) {
		Global.outQ = outQueue;
	}
	//SETOUTQ
	
	//ADDTOINQ
	public static void addToInQ(ServiceJob j)
	{
		Global.inQ.add(j);
	}
	//ADDTOINQ
	
	
	//ADDTOOUTQ
	public static void addToOutQ(List<Result> r)
	{
		Global.outQ.add(r);
	}
	//ADDTOOUTQ

}
