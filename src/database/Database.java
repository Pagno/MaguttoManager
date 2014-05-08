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
 */
public class Database implements DatabaseInterface{
	
	/** Driver. */
	public static String driver = null;
	
	/** Connessione. */
	public static Connection con = null;
	
	/** Risultati query. */
	public static ResultSet res = null;
	
	/** The cmd. */
	public static Statement cmd = null;
	
	/** Stringa della query. */
	public static String query = null;
	
	static boolean a;

	public Database(){
		
	}
	
	/**
	 * Crea una connesione al DB.
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
	 * 
	 * Controlla se il database è vuoti.
	 *
	 * @return ritorna true se il database è vuoto, false altrimenti
	 *
	 * @throws DBException the java db exception
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
	 * Effettua una interrogazione al DB.
	 *
	 * @param qry Query di interrogazione del database
	 * 
	 * @return Ritorna i risultati della query di interrogazione
	 * 
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
	 * Aggiorna  DB.
	 *
	 * @param qry Query di aggiornamento del database
	 * 
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
	 * Disconnessione dal DB.
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

	/**
	 * Elimina tutti i record presenti in una tabella
	 * 
	 * @param tableName nome della tabella che si vuole svuotare
	 *
	 * @throws DBException the java db exception
	 */
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