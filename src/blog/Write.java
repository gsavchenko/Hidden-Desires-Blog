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
 * Servlet implementation class Write
 * this is for data recording 
 */
@WebServlet(name = "WriteIt", urlPatterns = { "/WriteIt" })
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MySQLConnector mydb = null; 
	private PrintWriter out = null;
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Write() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
        out = response.getWriter();

        // write the HTML header
        out.println("<html><head><title>Java Web log</title></head>");
        out.println("<body bgcolor=\"white\">");

        // get the parameters "data" and "password" 
        // from the local environment (if they exist)
        String data = request.getParameter("data");
        String password = request.getParameter("password");
        if ( (data != null) && (password != null) )
        {
            //then the user has submitted an entry. call the writeData method 
            writeData(data,password);
        }
        else
        {
            // the user submitted either data or password, but not both   
            out.println("Missing data or password, please <a href=\"add_data.jsp\"> try again</a>.");
        }
        out.println("</body>");
        out.println("</html>");
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
	}

	// writeData checks for a valid password, 
	   // and writes the log entry to the db table 
	    @SuppressWarnings("deprecation")
		private void writeData(String data, String password)
	    {
	        try
	        {
	           // create a new MySQLConnector object 
	           mydb = new MySQLConnector();
	           // get a valid Statement object from the connector 
	           Statement stmt = mydb.connect();

	            // first some really trivial security
	            ResultSet rs = stmt.executeQuery("select count(*) as okay from blog_pass where password = \"" + password + "\"");
	            if ( rs.next() && rs.getInt("okay") > 0 ) // trivial password checked out okay
	            {
	                // write the log entry 
	                int written =  stmt.executeUpdate("insert into blog (data) values (\"" + data + "\")");
	                //out.println("<a href=\"blog/Show.java\"<View Entries</a>");
	                out.println("Your entry was recorded,<a href=\"Show\">View Entries</a>");
	                //this is suppose to show a link  to the show class
	            }
	            else
	            {
	                // bad password 
	                // redirect the jsp entry form
	                out.println("Bad password, please <a href=\"add_data.jsp?data=" + URLEncoder.encode(data) + "\">try again</a>.");
	            }
	            // disconnect from MySQL 
	            mydb.disconnect(stmt);
	        }
	        catch (Exception ex)
	        {
	            out.println("Whoops, your entry was not recorded! <br><br> " + ex.toString() );
	        }
	    }
}
