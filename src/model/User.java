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
	
	public User() {

	}
	
	public boolean fetchUserFromDb(String username, String password) {
		Context ctx;
		PreparedStatement stmt = null;
		try {
			ctx = new InitialContext();
			Context envContext  = (Context)ctx.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/jweb");
			Connection conn = ds.getConnection();
			String query = "SELECT * FROM User WHERE (username=? OR mail=?) AND password=?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, username);
			stmt.setString(3, password);
			ResultSet rs = stmt.executeQuery();
			System.out.println("It's a test and it works");
			if (rs.next()) {
				System.out.println("There is something in db");
				this.id = rs.getInt("ID");
				this.username = rs.getString("username");
				this.firstName = rs.getString("firstName");
				this.lastName = rs.getString("lastName");
				this.role = rs.getString("role");
				this.mail = rs.getString("mail");
				System.out.println("id: " + id + " username: " + username + " firstName: " + firstName);
				return true;
			}
			conn.commit();
			stmt.close();
			return true;
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
