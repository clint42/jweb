package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class News {
	private int id = 0;
	private String title = "";
	private String content = "";
	private int createdBy = 0;
	private Date createdDate = null;
	
	public News() {
		
	}
	
	public News(int id, String title, String content, int createdBy) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdBy = createdBy;
	}
	
	public News(int id, String title, String content, int createdBy, Date createdDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}
	
	static public ArrayList<News> fetchAll() {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null) {
			String query = "SELECT * FROM news";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				ArrayList<News> results = new ArrayList<News>();
				while (rs.next()) {
					News news = new News(rs.getInt("ID"), rs.getString("title"), rs.getString("content"), rs.getInt("createdBy"), rs.getDate("CreatedDate"));					
					results.add(news);
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
			String query = "SELECT * FROM news WHERE ID=?";
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("ID");
					this.title = rs.getString("title");
					this.content = rs.getString("content");
					this.createdBy = rs.getInt("createdBy");
					this.createdDate = rs.getDate("createdDate");
					stmt.close();
					conn.close();
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		return false;
	}
	
	private boolean insert(Connection conn) {
		String query = "INSERT INTO news (title, content, createdBy) VALUES(?,?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, this.title);
			stmt.setString(2, this.content);
			if (createdBy != 0) {
				stmt.setInt(3, this.createdBy);
			}
			else {
				stmt.setNull(3, java.sql.Types.INTEGER);
			}
			if (stmt.executeUpdate() > 0) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				while (generatedKeys.next()) {
					this.id = (int)generatedKeys.getLong(1);
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean update(Connection conn) {
		String query = "UPDATE news SET title=?, content=? WHERE id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, this.title);
			stmt.setString(2, this.content);
			stmt.setInt(3, this.id);
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
	
	public boolean delete() {
		Connection conn = new MariaDbConnection().getConn();
		if (conn != null && this.id > 0) {
			String query = "DELETE FROM news WHERE id=?";	
			try {
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
	
	public int getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public int getCreatedBy() {
		return this.createdBy;
	}
	
	public Date getCreatedDate() {
		return this.createdDate;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
