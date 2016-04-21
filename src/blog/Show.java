package blog;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.URLEncoder;

/**
 * Servlet implementation class Show
 * 
 * this will display the posts on another page 
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int numPerPage = 10; //this is the number of posts per page
	private PrintWriter out = null; 
	private MySQLConnector mydb = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Show() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void printEntries(int start) { 
    	try {
    		//create a new MySQLConnector Object 
    		mydb = new MySQLConnector();
    		//get the statement object aka the post 
    		Statement st = mydb.connect();
    		
    		//select the log entries from the db (most recent)
    		ResultSet rs = st.executeQuery("SELECT idBlog, data, DATE_FORMAT(time,'%M %d, %Y %H:%i') as nice_date from blog order by time desc limit " + start + ", " + numPerPage);
    		int counter = 0; 
    		while(rs.next()) { 
    			//this will go through the list on entities and printe dem on the page 
    			if ((counter % 2) == 0) {
    				out.println("<div class='apost'>");
    			} else {
    				out.println("<div class='bpost'>");
    			}
    			out.println("<br><b>"+ rs.getString("nice_date") + "</b><br>");
    			out.println("<a href='PostController?blogId=" + rs.getInt("idBlog") + "'>View This Post</a><br>");
    			out.println(rs.getString("data"));
    			out.println("</div>");
    			counter++;
    		}
    		
    		if(counter == numPerPage) {
    			int next = start + numPerPage;
    			out.println("<br><br><a href=\"/BlogSite/Show?start=" + next + "\">More</a><br><br>");
    		}
    		else {//this will happen if there are no more posts to show
    			out.println("That's it.  Go back to <a href=\"/BlogSite/Show\">The Beginning</a> <br>");
    		}
    		//disconnect from the db
    		mydb.disconnect(st);
    	}catch (Exception e) {
    		out.println("Error retrieving data: " + e.toString() + "<br>");
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		response.setContentType("text/html");
		out = response.getWriter();
		
		//this is just the header for the posts
		out.println("<html><head<title>Java Web log</title><link href='main.css' rel='stylesheet' /></head>");
		out.println("<body bgcolor=\"ivory\"><center>");
		out.println("<div id='navbar'><a href='Show'>Home</a><a href='Write'>Add Post</a></div>");
		out.println("<h1>The Drumpf Blog</h1>");
		int start;
		try {
            start = Integer.parseInt( request.getParameter("start") );
        }
        catch (NumberFormatException ne) {
            // bad number. start at zero
            start = 0;
        }
        // call the printEntries method to display log entries 
        printEntries(start);

        out.println("</center></body>");
        out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
	}

}
