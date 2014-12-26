package model;

import helper.FlashMessenger;

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

	public User(int id, String username, String password, String firstName,
			String lastName, String mail, String address, String city,
			String country, String zipcode) {
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

	public User(int id, String username, String password, String role,
			String firstName, String lastName, String mail, String address,
			String city, String country, String zipcode) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
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
					stmt.close();
					return true;
				}
				stmt.close();
				conn.close();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	private boolean isUserAndMailNotUsed(Connection conn) {
		String query = "SELECT COUNT(*) AS nb FROM user WHERE username=? OR mail=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, this.username);
			stmt.setString(2, this.mail);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("nb") == 0) {
					stmt.close();
					return false;
				} else {
					stmt.close();
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean insert(Connection conn) throws UserMailAlreadyUsedException {
		if (!this.isUserAndMailNotUsed(conn)) {
			String query = "INSERT INTO user (username, password, firstName, lastName, role, mail, address, city, country, zipcode) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);
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
					stmt.close();
					return true;
				}
				stmt.close();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			throw new UserMailAlreadyUsedException(
					"Username or mail already used");
		}
	}

	private boolean update(Connection conn) {
		String query = "UPDATE user SET address=?, country=?, city=?, zipcode=? WHERE id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, this.address);
			stmt.setString(2, this.country);
			stmt.setString(3, this.city);
			stmt.setString(4, this.zipcode);
			stmt.setInt(5, this.id);
			if (stmt.executeUpdate() > 0) {
				stmt.close();
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete() {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null && this.id > 0) {
			String query = "DELETE FROM user WHERE id=?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, this.id);
				if (stmt.executeUpdate() > 0) {
					stmt.close();
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean saveToDb() throws UserMailAlreadyUsedException {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			try {
				boolean ret;
				if (this.id == 0) {
					ret = this.insert(conn);
				} else {
					ret = this.update(conn);
				}
				conn.close();
				return ret;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean isSamePassword(String password) {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "SELECT COUNT(*) AS nb FROM user WHERE username=? AND password=?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, this.username);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getInt("nb") == 1) {
						stmt.close();
						return true;
					} else {
						stmt.close();
						return false;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean changePasswordToDb(String password) {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "UPDATE user SET password=? WHERE username=?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, password);
				stmt.setString(2, this.username);
				if (stmt.executeUpdate() > 0) {
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

	static public User getUserById(int id) {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "SELECT * FROM user WHERE ID=?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					User usr = new User(rs.getInt("ID"),
							rs.getString("username"), "*****",
							rs.getString("role"), rs.getString("firstName"),
							rs.getString("lastName"), rs.getString("mail"),
							rs.getString("address"), rs.getString("city"),
							rs.getString("country"), rs.getString("zipcode"));
					stmt.close();
					conn.close();
					return usr;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	static public User getUserByMail(String mail) {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "SELECT * FROM user WHERE mail=?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, mail);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					User user = new User(rs.getInt("ID"),
							rs.getString("username"), "*****",
							rs.getString("role"), rs.getString("firstName"),
							rs.getString("lastName"), rs.getString("mail"),
							rs.getString("address"), rs.getString("city"),
							rs.getString("country"), rs.getString("zipcode"));
					conn.close();
					return user;
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		}
		return null;
	}

	public void setNewInformation(String address, String country, String city,
			String zipcode) {
		this.address = address;
		this.country = country;
		this.city = city;
		this.zipcode = zipcode;
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
