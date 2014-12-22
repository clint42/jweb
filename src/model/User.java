package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class User {
	private boolean userExist = false;
	private int id = 0;
	private String username = "";
	private String firstName = "";
	private String lastName = "";
	private String role = "ND";
	private String mail = "";
	private String password = "";
	private String address = "";
	private String city = "";
	private String country = "";
	private String zipcode = "";
	
	public User() {

	}
	
	public User(int id, String username, String password, String firstName, String lastName, String mail, String address, String city, String country, String zipcode) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.address = address;
		this.city = city;
		this.country = country;
		this.zipcode = zipcode;
	}
	
	public boolean fetchUserFromDb(String username, String password) {
		PreparedStatement stmt = null;
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			try {
				String query = "SELECT * FROM User WHERE (username=? OR mail=?) AND password=?";
				conn.setAutoCommit(false);
				stmt = conn.prepareStatement(query);
				stmt.setString(1, username);
				stmt.setString(2, username);
				stmt.setString(3, password);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					this.id = rs.getInt("ID");
					this.username = rs.getString("username");
					this.firstName = rs.getString("firstName");
					this.lastName = rs.getString("lastName");
					this.role = rs.getString("role");
					this.mail = rs.getString("mail");
					this.address = rs.getString("address");
					this.city = rs.getString("city");
					this.country = rs.getString("country");
					this.zipcode = rs.getString("zipcode");
					return true;
				}
				conn.commit();
				stmt.close();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public boolean saveToDb() {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "INSERT INTO user (username, password, firstName, lastName, role, mail, address, city, country, zipcode) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, this.username);
				stmt.setString(2, this.password);
				stmt.setString(3, this.firstName);
				stmt.setString(4, this.lastName);
				stmt.setString(5, "U");
				stmt.setString(6, this.mail);
				stmt.setString(7, this.address);
				stmt.setString(8, this.city);
				stmt.setString(9, this.country);
				stmt.setString(10, this.zipcode);
				if (stmt.executeUpdate() > 0) {
					return true;
				}
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getMail() {
		return mail;
	}

	public String getAddress() {
		return address;
	}

	public String getCountry() {
		return country;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public String getRole() {
		return role;
	}
	
	public int getId() {
		return id;
	}
}
