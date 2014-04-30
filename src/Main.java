import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.crypto.Data;

import model.ModelConnector;


import database.Database;
import database.DBException;


public class Main {

	/**
	 * @param args
	 * @throws DBException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws DBException, SQLException {
		// TODO Auto-generated method stub
		//ParcoMacchine pm=new ParcoMacchine();
		Database db=new Database();
		ModelConnector m=new ModelConnector(db);
		/*Database.connect();
		//Database.popola();
		String qry="SELECT * FROM APP.Macchina";
		ResultSet res = Database.interrogate(qry);
		while (res.next()) {
			System.out.println(res.getInt("Codice")+res.getString("Produttore")+" - "+res.getInt("PortataMax"));
		}
		
		//Database.initDB();
		Database.disconnect();
		//Manager man=new Manager();*/
	}

}