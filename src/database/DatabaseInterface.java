package database;

import java.sql.ResultSet;

// TODO: Auto-generated Javadoc
/**
 * The Interface DatabaseInterface.
 */
public interface DatabaseInterface {
	
	/**
	 * Connect.
	 *
	 * @throws DBException the DB exception
	 */
	public void connect() throws DBException;
	
	/**
	 * Disconnect.
	 *
	 * @throws DBException the DB exception
	 */
	public void disconnect() throws DBException;
	
	/**
	 * Interrogate.
	 *
	 * @param qry the qry
	 * @return the result set
	 * @throws DBException the DB exception
	 */
	public ResultSet interrogate(String qry) throws DBException;
	
	/**
	 * Update.
	 *
	 * @param qry the qry
	 * @throws DBException the DB exception
	 */
	public void update(String qry) throws DBException;
	
	/**
	 * Empty table.
	 *
	 * @param tableName the table name
	 * @throws DBException the DB exception
	 */
	public void emptyTable(String tableName) throws DBException;
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 * @throws DBException the DB exception
	 */
	public boolean isEmpty() throws DBException;
}
