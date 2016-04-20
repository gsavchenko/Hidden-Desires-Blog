package blog;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostController
 */
@WebServlet("/PostController")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int blogId = Integer.parseInt(request.getParameter("blogId"));
			MySQLConnector mydb = new MySQLConnector();
			Statement st = mydb.connect();
			ResultSet rs = st.executeQuery(
					"SELECT idBlog, data, DATE_FORMAT(time,'%M %d, %Y %H:%i') as nice_date from blog where idBlog = "
							+ blogId);
			BlogPost bp = new BlogPost();
			while (rs.next()) {
				bp.setBlogId(rs.getInt("idBlog"));
				bp.setPost(rs.getString("data"));
				bp.setDate(rs.getString("nice_date"));
			}
			rs = st.executeQuery("SELECT comment from comments where blogId = " + blogId);
			List<String> allComments = new ArrayList<String>();
			while (rs.next()) {
				allComments.add(rs.getString("comment"));
			}
			bp.setComments(allComments);
			// after data has been modeled out above ^^^^^^^
			RequestDispatcher view = request.getRequestDispatcher("post.jsp");
			request.setAttribute("blogPost", bp);
			view.forward(request, response);
		} catch (Exception ex) {
			PrintWriter out = response.getWriter();
			out.println("Error " + ex.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int blogId = Integer.parseInt(request.getParameter("blogId"));	
			String comment = request.getParameter("comment");
			MySQLConnector mydb = new MySQLConnector();
			Statement st = mydb.connect();
			st.executeUpdate("INSERT INTO comments"
				+ "(blogId,comment) " + "VALUES"
				+ "(" + blogId + ",\"" + comment + "\")");
			PrintWriter out = response.getWriter();
			out.append("<br><br> Comment added! <br><br><a href='PostController?blogId=" + blogId + "'>Back to List</a>");
		}catch (Exception ex) {
			PrintWriter out = response.getWriter();
			out.println("Error " + ex.toString());
		}
	}
}
