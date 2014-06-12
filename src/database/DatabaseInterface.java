package database;

import java.sql.ResultSet;

// TODO: Auto-generated Javadoc
/**
 *   Interface DatabaseInterface.
 */
public interface DatabaseInterface {
	
	/**
	 * Connect.
	 *
	 * @throws DBException   DB exception
	 */
	public void connect() throws DBException;
	
	/**
	 * Disconnect.
	 *
	 * @throws DBException   DB exception
	 */
	public void disconnect() throws DBException;
	
	/**
	 * Interrogate.
	 *
	 * @param qry   qry
	 * @return   result set
	 * @throws DBException   DB exception
	 */
	public ResultSet interrogate(String qry) throws DBException;
	
	/**
	 * Update.
	 *
	 * @param qry   qry
	 * @throws DBException   DB exception
	 */
	public void update(String qry) throws DBException;
	
	/**
	 * Empty table.
	 *
	 * @param tableName   table name
	 * @throws DBException   DB exception
	 */
	public void emptyTable(String tableName) throws DBException;
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 * @throws DBException   DB exception
	 */
	public boolean isEmpty() throws DBException;
}
