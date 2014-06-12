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

	/**   driver. */
	public static String driver = null;

	/**   con. */
	public static Connection con = null;

	/**   res. */
	public static ResultSet res = null;

	/**   cmd. */
	public static Statement cmd = null;

	/**   query. */
	public static String query = null;

	/**   a. */
	static boolean a;

	/**   istanza. */
	private static Database istanza;
	
	/**
	 * Instantiates a new database.
	 */
	private Database(){

	}
	
	/**
	 * Gets   database.
	 *
	 * @return   database
	 */
	public static synchronized Database getDatabase(){
		if(istanza==null){
			istanza=new Database();
		}
		return istanza;
	}

	/**
	 * Connessione al DB.
	 *
	 * @throws DBException   java db exception
	 */
	@Override
	public void connect() throws DBException{
		// Load   driver derby
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
		try {
			Class.forName(driver);
		}
		catch(Exception e){
			throw new DBException(1);
		}
		// Create   connection string
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
	 * @return true, if is empty
	 * @throws DBException   java db exception
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
	 * @param qry   qry
	 * @return   result set
	 * @throws DBException   java db exception
	 */
	@Override
	public ResultSet interrogate(String qry) throws DBException{
		a = true;

		// Query and save   results in a ResultSet object
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
	 * @param qry   qry
	 * @throws DBException   java db exception
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
	 * @throws DBException   java db exception
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


	/* (non-Javadoc)
	 * @see database.DatabaseInterface#emptyTable(java.lang.String)
	 */
	@Override
	public void emptyTable(String tableName) throws DBException{
		String qry = "delete from APP." + tableName;
		try {
			cmd.executeUpdate(qry);
		} catch (SQLException e) {
			throw new DBException(9);
		}
	}


}