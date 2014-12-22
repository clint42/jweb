package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Product {
	private int id = 0;
	private String name = "";
	private String description = "";
	private double price = 0.0;
	private int quantity = 0;
	private int createdBy = 0;
	private Date creationDate = null;
	private Date updateDate = null;
	
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
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
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
