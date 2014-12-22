package model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

public class Review {
	private int id = 0;
	private String title = "";
	private String text = "";
	private int userId = 0;
	private Date creationDate = null;
	
	public Review() {
		
	}
	
	static public ArrayList<Review> fetchAllProductReview(int productId) {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "SELECT * FROM review WHERE "
		}
		return null;
	}
}
