package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Order {
	private int ID;
	private String status;
	private Date createdDate;
	private ArrayList<CartItem> items = new ArrayList<CartItem>();
	
	public Order() {
		
	}
	
	public boolean getOrderById(int ID) {
		return false;
	}
	
	public boolean fetchUserOrders(int userID, boolean populateCartItem) {
		Connection conn = new MariaDbConnection().getConn();
		PreparedStatement stmt = null;
		
		try {
			conn.setAutoCommit(false);
			String query = "SELECT * FROM order WHERE userID=?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				this.ID = rs.getInt("ID");
				this.status = rs.getString("status");
				this.createdDate = rs.getDate("createdDate");
				query = "SELECT * FROM cartItem WHERE orderID=?";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, this.ID);
				ResultSet rsCartItems = stmt.executeQuery();
				while (rsCartItems.next()) {
					
				}
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
