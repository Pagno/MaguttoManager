import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.crypto.Data;


import database.Database;
import database.JavaDBException;
import model.ParcoMacchine;


public class Main {

	/**
	 * @param args
	 * @throws JavaDBException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws JavaDBException, SQLException {
		// TODO Auto-generated method stub
		//ParcoMacchine pm=new ParcoMacchine();
		Database.connect();
		//Database.popola();
		String qry="SELECT * FROM APP.Macchina";
		ResultSet res = Database.interrogate(qry);
		while (res.next()) {
			System.out.println(res.getInt("Codice")+res.getString("Produttore")+" - "+res.getInt("PortataMax"));
		}
		
		//Database.initDB();
		Database.disconnect();
		//Manager man=new Manager();
	}

}