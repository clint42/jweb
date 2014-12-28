package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Product class is linked to product table
 * @author prieur_b
 *
 */
public class Abonewsletter {
	private int id = 0;
	private String mail = "";
	private String firstName = null;
	private String lastName = null;
	private int userId = 0;
	
	public Abonewsletter() {
		
	}
	
	public Abonewsletter(int id, String mail, String firstName, String lastName, int userId) {
		this.id = id;
		this.mail = mail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
	}

	/**
	 * Check if an specified email address is already present in the newsletter table
	 * @param mail
	 * @return true if the specified address already exist in the newsletter table. False otherwise or if an error occurred
	 */
	static public boolean isMailRegistered(String mail) {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "SELECT COUNT(*) AS nb FROM abonewsletter WHERE mail=?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, mail);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getInt("nb") == 0) {
						stmt.close();
						conn.close();
						return false;
					}
					else {
						stmt.close();
						conn.close();
						return true;
					}
				}
			} catch (SQLException e) {
				
			}
		}
		//TODO: throw an exception
		return false;
	}
	
	/**
	 * Save the current instance data to database
	 * @return false if an error occurred, true otherwise
	 */
	public boolean saveToDb() {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "INSERT INTO abonewsletter (mail, firstname, lastname, fk_userID) VALUES(?, ?, ?, ?)";
			try {
				PreparedStatement stmt = conn.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, this.mail);
				if (this.firstName != null) {
					stmt.setString(2, this.firstName);
				}
				else {
					stmt.setNull(2, java.sql.Types.VARCHAR);
				}
				if (this.lastName != null) {
					stmt.setString(3, this.lastName);
				}
				else {
					stmt.setNull(3, java.sql.Types.VARCHAR);
				}
				if (this.userId > 0) {
					stmt.setInt(4, this.userId);
				}
				else {
					stmt.setNull(4,  java.sql.Types.INTEGER);
				}
				if (stmt.executeUpdate() != 0) {
					stmt.close();
					conn.close();
					return true;
				}
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
