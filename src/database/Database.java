package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * Classe che gestisce le connessioni, le interrogazioni, le modifiche e le disconnessioni al database.
 * 
 * @author Mauro
 */
public class Database {
	
	/** The driver. */
	public static String driver = null;
	
	/** The con. */
	public static Connection con = null;
	
	/** The res. */
	public static ResultSet res = null;
	
	/** The cmd. */
	public static Statement cmd = null;
	
	/** The query. */
	public static String query = null;
	
	/** The a. */
	static boolean a;

	
	/**
	 * Connessione al DB.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public static void connect() throws JavaDBException{
		// Load the driver derby
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
		try {
			Class.forName(driver);
		}
		catch(Exception e){
			throw new JavaDBException(1);
		}
		// Create the connection string
		String url = "jdbc:derby:MyDB;create=true";
		// Get a connection
		try{
			con = DriverManager.getConnection(url);
			System.out.println("Connection done");
			
			cmd = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
		}
		catch(SQLException e){
			throw new JavaDBException(2);
		}
	}
	
	
	
	
	/**
	 * Controlla se esistono le tabelle nel DB.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public static void initDB() throws JavaDBException{
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
		try {
			Class.forName(driver);
		}
		catch(Exception e){
			throw new JavaDBException(1);
		}
		// Create the connection string
		String url = "jdbc:derby:MyDB;create=true";
		// Get a connection
		try{
			con = DriverManager.getConnection(url);
			cmd = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			ResultSet table = con.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
			if(!table.next()){		// se non ci sono le tabelle
				System.out.println("tabelle non esistenti!!!!!!!!!!!!!!!!!!!!!!!!!");
				InitDB idb = new InitDB();
				idb.createTables();
				
				idb.popola();
			}
				
			System.out.println("Connection done");
		}
		catch(SQLException e){
			throw new JavaDBException(2);
		}
	}
	
	
	public static void popola() throws JavaDBException{
		InitDB idb = new InitDB();
		
		idb.popola();
	}
	
	
	/**
	 * Interrogazione al DB.
	 *
	 * @param qry the qry
	 * @return the result set
	 * @throws JavaDBException the java db exception
	 */
	public static ResultSet interrogate(String qry) throws JavaDBException{
		a = true;
		
		// Query and save the results in a ResultSet object
		try {
			res = cmd.executeQuery(qry);
		} catch (SQLException e) {
			throw new JavaDBException(4);	
		}
		return res;
	}
	
	
	
	/**
	 * Update table into DB.
	 *
	 * @param qry the qry
	 * @throws JavaDBException the java db exception
	 */
	public static void update(String qry) throws JavaDBException {
		a = false;
		try {
			cmd.executeUpdate(qry);
		} catch (SQLException e) {
			throw new JavaDBException(5);
		}
	}
	
	
	
	/**
	 * Disconnect from DB.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public static void disconnect() throws JavaDBException {
		if(a==true){		// if it is a query
			try {
				res.close();
			} 
			catch (SQLException e) {
				throw new JavaDBException(6);
			}
		} 
		try {
			cmd.close();
		} catch (SQLException e) {
			throw new JavaDBException(7);
		}
		try {
			con.close();
			System.out.println("Disconnection done");
		} catch (SQLException e) {
			throw new JavaDBException(8);
		}
	}
	
	
}