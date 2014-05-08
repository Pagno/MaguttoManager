import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import controller.VisualizzaCtr;

import view.viewPrincipale;
import model.ModelConnector;
import database.Database;
import database.DBException;


public class Main {

	/**
	 * @param args
	 * @throws DBException 
	 * @throws SQLException
	 */
	public static void main(String[] args) throws DBException, SQLException, UnsupportedLookAndFeelException, ParseException, InterruptedException {
		// TODO Auto-generated method stub
		//ParcoMacchine pm=new ParcoMacchine();

		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		Database db=new Database();
		ModelConnector m=new ModelConnector(db);
		VisualizzaCtr vis=new VisualizzaCtr(m);
		
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