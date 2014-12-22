package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Review {
	private int id = 0;
	private String title = "";
	private String text = "";
	private double rank = 0.0;
	private int userId = 0;
	private int productId = 0;
 	private Date creationDate = null;
	
 	private User user = null;
 	
	public Review() {
		
	}
	
	public Review(int id, String title, String text, double rank, int userId, int productId, Date creationDate) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.rank = rank;
		this.userId = userId;
		this.productId = productId;
		this.creationDate = creationDate;
	}
	
	public Review(String title, String text, double rank, int userId, int productId) {
		this.title = title;
		this.text = text;
		this.rank = rank;
		this.userId = userId;
		this.productId = productId;
	}
	
	static public ArrayList<Review> fetchAllProductReview(int productId) {
		Connection conn = new MariaDbConnection().getConn();
		ArrayList<Review> results = new ArrayList<Review>();
		if (conn != null) {
			String query = "SELECT * FROM review WHERE productID=?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, productId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					results.add(new Review(rs.getInt("ID"), rs.getString("title"), rs.getString("text"), rs.getDouble("rank"), rs.getInt("userId"), rs.getInt("productId"), rs.getDate("creationDate")));
				}
				stmt.close();
				conn.close();
				return results;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
	
	private boolean insert(Connection conn) {
		String query = "INSERT INTO review (title, text, rank, userID, productID) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, this.title);
			stmt.setString(2, this.text);
			stmt.setDouble(3, this.rank);
			stmt.setInt(4, this.userId);
			stmt.setInt(5, this.productId);
			if (stmt.executeUpdate() > 0) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				while (generatedKeys.next()) {
					this.id = (int)generatedKeys.getLong(1);
				}
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean update(Connection conn) {
		/* Not ready yet */
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean saveToDb() {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			if (this.id == 0) {
				return this.insert(conn);
			}
			else {
				return this.update(conn);
			}
		}
		return false;
	}
	
	public User getUser() {
		if (this.user == null) {
			this.user = User.getUserById(this.id);
		}
		return this.user;
	}
	
	public String getUserFirstName() {
		if (this.user == null) {
			this.getUser();
		}
		if (user != null) {
			return this.user.getFirstName();
		}
		else {
			return "";
		}
	}
	
	public String getUserLastName() {
		if (this.user == null) {
			this.getUser();
		}
		if (user != null) {
			return this.user.getLastName();
		}
		else {
			return "";
		}
	}
	
	public double getRank() {
		return this.rank;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getText() {
		return this.text;
	}
	
	public Date getCreationDate() {
		return this.creationDate;
	}
}
