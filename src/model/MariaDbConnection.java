package model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * The purpose of this class is to provide a simple way to access to Datasource
 * @author prieur_b
 *
 */
public class MariaDbConnection {
	private Connection conn;
	
	/**
	 * The constructor do all necessary work to get a sql connection from the pool
	 */
	public MariaDbConnection() {
		Context ctx;
		try {
			ctx = new InitialContext();
			Context envContext  = (Context)ctx.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/jweb");
			this.conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Return the connection fetched for the pool by the constructor
	 * @return Connection 
	 */
	public Connection getConn() {
		return this.conn;
	}
}
