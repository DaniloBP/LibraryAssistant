package database;

import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {
	
	private static DatabaseHandler handler = null;
	
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/library?useTimezone=true&serverTimezone=UTC"; 
	private final String USER = "root"; 
	private final String PASS = "1234"; 
	
	private static Connection conn = null;
	private static Statement stmt = null;
	
	
	private DatabaseHandler() {
		createConnection();
		setupBookTable();
		setupMemberTable();
	}
	
	public static DatabaseHandler getInstance() {
			if (handler == null) {
				handler = new DatabaseHandler();
			}
			return handler;
	}
	
	void createConnection() {
		try {
			Class.forName(DRIVER).newInstance();
			conn = DriverManager.getConnection(URL, USER, PASS);
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Erro na conex�o com BD: ", e);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
				stmt.execute(	"CREATE TABLE " + TABLE_NAME + " ("
							+ "		id varchar(50) NOT NULL primary key, \n"
							+ "		title varchar(200) NOT NULL,\n"
							+ "		author varchar(100),\n"
							+ "		publisher varchar(100),\n"
							+ " 	isAvail boolean default true"
							+ " ) DEFAULT CHARSET = UTF8MB4;" 
							);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage() + " ... set up Database");
			e.printStackTrace();
		}
		
		
	}
	
	private void setupMemberTable() {
		
		String TABLE_NAME = "MEMBER";
		
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if (tables.next()) {
				System.out.println("Table " + TABLE_NAME + " already exists. We're good to go!");
			} else {
				stmt.execute("	CREATE TABLE " + TABLE_NAME + " ("
							+ "		id varchar(50) NOT NULL primary key, \n"
							+ "		name varchar(100),\n"
							+ "		phone varchar(20),\n"
							+ "		email varchar(100)\n"
							+ " ) DEFAULT CHARSET = UTF8MB4;" 
							);
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
