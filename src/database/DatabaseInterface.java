package database;

import java.sql.ResultSet;

// 
/**
 *   Interface DatabaseInterface.
 */
public interface DatabaseInterface {
	
	/**
	 * Connect.
	 *
	 * @throws DatabaseException   DB exception
	 */
	public void connect() throws DatabaseException;
	
	/**
	 * Disconnect.
	 *
	 * @throws DatabaseException   DB exception
	 */
	public void disconnect() throws DatabaseException;
	
	/**
	 * Interrogate.
	 *
	 * @param qry   qry
	 * @return   result set
	 * @throws DatabaseException   DB exception
	 */
	public ResultSet interrogate(String qry) throws DatabaseException;
	
	/**
	 * Update.
	 *
	 * @param qry   qry
	 * @throws DatabaseException   DB exception
	 */
	public void update(String qry) throws DatabaseException;
	
	/**
	 * Empty table.
	 *
	 * @param tableName   table name
	 * @throws DatabaseException   DB exception
	 */
	public void emptyTable(String tableName) throws DatabaseException;
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 * @throws DatabaseException   DB exception
	 */
	public boolean isEmpty() throws DatabaseException;
}
