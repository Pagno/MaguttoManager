package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseInterface {
	
	public void connect() throws DBException;
	public void disconnect() throws DBException;
	public ResultSet interrogate(String qry) throws DBException;
	public void update(String qry) throws DBException;
	public void emptyTable(String tableName);
	public boolean isEmpty() throws DBException, SQLException;
}
