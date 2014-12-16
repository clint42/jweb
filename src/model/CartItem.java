package model;

import java.sql.Connection;
import java.util.Date;

public class CartItem {
	private int ID = 0;
	private int orderID = 0;
	private int productID = 0;
	private Product product = null;
	private Date addDate = null;
	
	public CartItem() {
		
	}
	
	public CartItem(int ID, int orderID, int productID, Date addDate, boolean fetchProduct) {
		this.ID = ID;
		this.orderID = orderID;
		this.productID = productID;
		this.addDate = addDate;
		if (fetchProduct) {
			Connection conn = new MariaDbConnection().getConn();
			String 
			PreparedStatement stmt = conn.prepareStatement(query);
		}
	}
}
