package database;

import java.sql.ResultSet;

/**
 *   L'interfaccia DatabaseInterface permette di accedere alle funzionalit&agrave; del database.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public interface DatabaseInterface {
	
	/**
	 * Connette il DB.
	 *
	 * @throws DatabaseException   DB exception
	 */
	public void connect() throws DatabaseException;
	
	/**
	 * Disconnette il DB.
	 *
	 * @throws DatabaseException   DB exception
	 */
	public void disconnect() throws DatabaseException;
	
	/**
	 * Interroga in DB.
	 *
	 * @param qry   qry
	 * @return   result set
	 * @throws DatabaseException   DB exception
	 */
	public ResultSet interrogate(String qry) throws DatabaseException;
	
	/**
	 * Aggiorna il DB.
	 *
	 * @param qry   qry
	 * @throws DatabaseException   DB exception
	 */
	public void update(String qry) throws DatabaseException;
	
	/**
	 * Svuota una tabella del DB.
	 *
	 * @param tableName   table name
	 * @throws DatabaseException   DB exception
	 */
	public void emptyTable(String tableName) throws DatabaseException;
	
	/**
	 * Verifica se il DB è vuoto.
	 *
	 * @return true, if is empty
	 * @throws DatabaseException   DB exception
	 */
	public boolean isEmpty() throws DatabaseException;
}
