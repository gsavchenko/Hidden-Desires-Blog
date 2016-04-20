package blog;

import java.util.List;

public class BlogPost {
	private int blogId;
	private String post;
	private String date;
	private List<String> comments;
	
	public void setBlogId(int b) {
		this.blogId = b;
	}
	
	public int getBlogId() {
		return this.blogId;
	}
	
	public void setPost(String p) {
		this.post = p;
	}
	
	public String getPost() {
		return this.post;
	}
	
	public void setDate(String n) {
		this.date = n;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public List<String> getComments() {
		return this.comments;
	}
	
	public void setComments(List<String> ls) {
		this.comments = ls;
	}
}
