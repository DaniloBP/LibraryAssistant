package database;

import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {
	
	private static DatabaseHandler handler;
	
	private static String DB_URL = "jdbc:derby:database;create=true";
	private static Connection conn = null;
	private static Statement stmt = null;
	
	
	public DatabaseHandler() {
		createConnection();
		setupBookTable();
	}
	
	void createConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			conn = DriverManager.getConnection(DB_URL);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	void setupBookTable() {
		
		String TABLE_NAME = "BOOK";
		
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if (tables.next()) {
				System.out.println("Table " + TABLE_NAME + " already exists. We're good to go!");
			} else {
				stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
						+ "		id varchar(200) primary key, \n"
						+ "		title varchar(200),\n"
						+ "		author varchar(200),\n"
						+ "		publisher varchar(100),\n"
						+ " 	isAvail boolean default true"
						+ " )" );
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage() + " ... set up Database");
			e.printStackTrace();
		}
		
		
	}
	
	public ResultSet executeQuery(String query) {
		
		ResultSet result;	
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
		} catch (SQLException e) {
//			System.err.println();
			System.out.println("Exception at executeQuery:dataHandler" + e.getLocalizedMessage());
			return null;
		}
		
		return result;
	}
	
	public boolean executeAction(String query) {
			
		try {
			stmt = conn.createStatement();
			stmt.execute(query);
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
			System.out.println("Exception at executeAction:dataHandler" + e.getLocalizedMessage());
			return false;
		}		
	}
	
	
}
