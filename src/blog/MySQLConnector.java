package blog;

import java.sql.*;

//this is a simple MySQL connection handler class
public class MySQLConnector {
	static final String USER = "root";
	static final String PASS = "admin";
	private static String DBUrl = "jdbc:mysql://localhost/BlogSiteDb";
	private Connection conn = null;
	
	//returns a statement when conection is successful 
	
	public Statement connect() throws SQLException, Exception
	{
		//make the connection to the database ("BlogSiteDb")
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(DBUrl,USER,PASS);
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