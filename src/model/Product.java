package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Product {
	private int id = 0;
	private String name = "";
	private String description = "";
	private int category = 0;
	private double price = 0.0;
	private int quantity = 0;
	private int createdBy = 0;
	private Date creationDate = null;
	private Date updateDate = null;
	
	private ArrayList<Review> reviews = null;
	private Double averageRank = -1.0;
	
	public Product() {
		
	}
	
	public Product(int id, String name, String description, double price, int quantity, int createdBy, Date creationDate, Date updateDate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}
	
	public Product(String name, String description, double price, int quantity, int createdBy) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.createdBy = createdBy;
	}
	
	static public ArrayList<Product> fetchAll() {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "SELECT * FROM product";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				ArrayList<Product> results = new ArrayList<Product>();
				while (rs.next()) {
					results.add(new Product(rs.getInt("ID"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"), rs.getInt("quantity"), rs.getInt("createdBy"), rs.getDate("creationDate"), rs.getDate("updateDate")));
				}
				stmt.close();
				conn.close();
				return results;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean fetchById(int id) {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "SELECT * FROM product WHERE ID=?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("ID");
					this.name = rs.getString("name");
					this.description = rs.getString("description");
					this.price = rs.getDouble("price");
					this.quantity = rs.getInt("quantity");
					this.createdBy = rs.getInt("createdBy");
					this.creationDate = rs.getDate("creationDate");
					this.updateDate = rs.getDate("updateDate");
					stmt.close();
					conn.close();
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private boolean insert(Connection conn) {
		String query = "INSERT INTO product (name, description, category, price, quantity, createdBy) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, this.name);
			stmt.setString(2, description);
			if (this.category == 0) {
				stmt.setNull(3, java.sql.Types.INTEGER);
			}
			else {
				stmt.setInt(3, this.category);
			}
			stmt.setDouble(4, this.price);
			stmt.setInt(5, this.quantity);
			stmt.setInt(6, this.createdBy);
			if (stmt.executeUpdate() > 0) {
				stmt.close();
				return true;
			}
			stmt.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean update(Connection conn) {
		/*
		 * Not ready yet
		 */
		return false;
	}
	
	public boolean saveToDb() {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			boolean ret = false;
			if (this.id == 0) {
				ret = this.insert(conn);
			}
			else {
				ret = this.update(conn);
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ret;
		}
		return false;
	}
	public ArrayList<Review> getReviews() {
		if (this.id > 0 && this.reviews == null) {
			this.reviews = Review.fetchAllProductReview(this.id);
		}
		return this.reviews;
	}
	
	public double getAverageRank() {
		if (this.averageRank == -1.0 && this.reviews != null) {
			double totalRank = 0.0;
			for (Review review: this.reviews) {
				totalRank += review.getRank();
			}
			this.averageRank = totalRank / this.reviews.size();
		}
		return this.averageRank;
	}

	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}

	public double getPrice() {
		return this.price;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	public Date getUpdateDate() {
		return this.updateDate;
	}
	
	public int getCreatedBy() {
		return this.createdBy;
	}
	
	/*
	 * Product isNew return true if the product creation date is maximum one week before the current date
	 */
	public boolean isNew() {
		Date currentDate = new Date();
		Date productDateCpy = (Date)(this.creationDate.clone());
		productDateCpy.setTime(productDateCpy.getTime() + (14*24*60*60*1000));
		return currentDate.before(productDateCpy);
	}
}
