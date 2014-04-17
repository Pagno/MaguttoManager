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
public class Database implements DatabaseInterface{
	
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

	public Database(){
		
	}
	
	/**
	 * Connessione al DB.
	 *
	 * @throws DBException the java db exception
	 */
	@Override
	public void connect() throws DBException{
		// Load the driver derby
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
		try {
			Class.forName(driver);
		}
		catch(Exception e){
			throw new DBException(1);
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
			throw new DBException(2);
		}
	}
	
	
	
	
	/**
	 * Controlla se esistono le tabelle nel DB.
	 *
	 * @throws DBException the java db exception
	 * @throws SQLException 
	 */
	@Override
	public boolean isEmpty() throws DBException{
		try{
			ResultSet table = con.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
			if(!table.next()){		// se non ci sono le tabelle
				return true;
			}
			else return false;
			}
		catch(SQLException e){
			throw new DBException(3);
		}
	}
	
	
	/**
	 * Interrogazione al DB.
	 *
	 * @param qry the qry
	 * @return the result set
	 * @throws DBException the java db exception
	 */
	@Override
	public ResultSet interrogate(String qry) throws DBException{
		a = true;
		
		// Query and save the results in a ResultSet object
		try {
			res = cmd.executeQuery(qry);
		} catch (SQLException e) {
			throw new DBException(4);	
		}
		return res;
	}
	
	
	
	/**
	 * Update table into DB.
	 *
	 * @param qry the qry
	 * @throws DBException the java db exception
	 */
	@Override
	public void update(String qry) throws DBException {
		a = false;
		try {
			cmd.executeUpdate(qry);
		} catch (SQLException e) {
			throw new DBException(5);
		}
	}
	
	
	
	/**
	 * Disconnect from DB.
	 *
	 * @throws DBException the java db exception
	 */
	@Override
	public void disconnect() throws DBException {
		if(a==true){		// if it is a query
			try {
				res.close();
			} 
			catch (SQLException e) {
				throw new DBException(6);
			}
		} 
		try {
			cmd.close();
		} catch (SQLException e) {
			throw new DBException(7);
		}
		try {
			con.close();
			System.out.println("Disconnection done");
		} catch (SQLException e) {
			throw new DBException(8);
		}
	}


	@Override
	public void emptyTable(String tableName) throws DBException{
		String qry = "delete table APP." + tableName;
		try {
			cmd.executeUpdate(qry);
		} catch (SQLException e) {
			throw new DBException(9);
		}
	}
	
	
}