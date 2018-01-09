
package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/UploadServlet")
//Size threshold 2MB, max size is 10MB and if multiple files then 50MB
@MultipartConfig(fileSizeThreshold=1024*1024*2, maxFileSize=1024*1024*10, maxRequestSize=1024*1024*50)
public class ServiceHandler extends HttpServlet {
	//Variables
	private static final long serialVersionUID = 1L;
	private String parameterVariable = null; 
	private static long jobNumber = 0;
	private Service service;
	private Reader r;
	private List<Shingle> shingleList = new ArrayList<Shingle>();
	private ServiceJob sj;
	private Document d;

	//Constructor
	public ServiceHandler() {
		super();
		Global.init();
		service = new Service();
		new Thread(service).start();
	}

	
	public void init() throws ServletException {
		ServletContext ctx = getServletContext(); 
		

		parameterVariable = ctx.getInitParameter("VARIABLE"); 
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html"); 
		PrintWriter out = resp.getWriter(); 
		String title = req.getParameter("txtTitle");
		String taskNumber = req.getParameter("frmTaskNumber");
		Part section = req.getPart("txtDocument");

		out.print("<html><head><title>A JEE Application for Measuring Document Similarity</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			
		}else{
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/poll");
			dispatcher.forward(req,resp);
	
		}
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<H3>Document Title: " + title + "</H3>");
		
		out.print("<div id=\"r\"></div>");
		out.print("<font color=\"#993333\"><b>");
		out.print("Environmental Variable Read from web.xml: " + parameterVariable);
		out.print("<br>This servlet should only be responsible for handling client request and returning responses. Everything else should be handled by different objects.");
		out.print("Note that any variables declared inside this doGet() method are thread safe. Anything defined at a class level is shared between HTTP requests.");				
		out.print("</b></font>");
		
		out.print("<h3>Compiling and Packaging this Application</h3>");
		out.print("Place any servlets or Java classes in the WEB-INF/classes directory. Alternatively package "); 
		out.print("these resources as a JAR archive in the WEB-INF/lib directory using by executing the ");  
		out.print("following command from the WEB-INF/classes directory jar -cf my-library.jar *");
		
		out.print("<ol>");
		out.print("<li><b>Compile on Mac/Linux:</b> javac -cp .:$TOMCAT_HOME/lib/servlet-api.jar WEB-INF/classes/ie/gmit/sw/*.java");
		out.print("<li><b>Compile on Windows:</b> javac -cp .;%TOMCAT_HOME%/lib/servlet-api.jar WEB-INF/classes/ie/gmit/sw/*.java");
		out.print("<li><b>Build JAR Archive:</b> jar -cf jaccard.war *");
		out.print("</ol>");
		
		out.print("<form name=\"frmRequestDetails\" action=\"poll\">");
		out.print("<input name=\"txtTitle\" type=\"hidden\" value=\"" + title + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 1000000);"); 
		out.print("</script>");
		
		out.print("<h3>Uploaded Document</h3>");	
		out.print("<font color=\"0000ff\">");	

		
		r = new Reader(section, taskNumber);
		shingleList = r.newShingle();
		d = new Document(taskNumber, title, shingleList);
		sj = new ServiceJob(d);
		
		Global.addToInQ(sj);
		
		out.print("</font>");	
	}//doGet

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}//doPost
}//ServiceHandler