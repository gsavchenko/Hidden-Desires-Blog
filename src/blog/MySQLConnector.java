package blog;

import java.io.*; 
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.URLEncoder;

//this is a simple MySQL connection handler class
public class MySQLConnector {
	static final String USER = "root";
	static final String PASS = "153077";
	private static String DBUrl = "jdbc:mysql://localhost:3306/blogsitedb";
	private Connection conn = null;
	
	//returns a statement when conection is successful 
	
	public Statement connect() throws SQLException, Exception
	{
		//make the connection to the database ("BlogSiteDb")
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogsitedb","root","admin"); 
		Statement st = conn.createStatement();
		//return the statement 
		return st;
	}
	
	public void disconnect(Statement st) {
		//close the connection if there is one 
		if(st != null) {
			try {
				//closes the statemet
				st.close();
			}
			catch(SQLException sqlEx) {
				//this will do the stuff 
			}
		}
		//this closes the connection 
		if(conn != null) {
			try {
				conn.close();
			}
			catch (SQLException sqlEx) {
				//this will also do the stuff
			}
		}
	}
}