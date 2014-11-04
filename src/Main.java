import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import view.MainView;
import controller.ControllerConnector;
import model.ModelConnector;
import database.Database;
import database.DatabaseException;



/**
 *   La classe Main è la classe principale del programma, e si occupa di caricare e inizializzare i componenti in cui esso è 
 *   suddiviso.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class Main {
	
	/**
	 * Metodo main.
	 *
	 * @param args   arguments
	 * @throws DatabaseException   DB exception
	 * @throws SQLException   SQL exception
	 * @throws UnsupportedLookAndFeelException   unsupported look and feel exception
	 * @throws ParseException   parse exception
	 * @throws InterruptedException   interrupted exception
	 */
	public static void main(String[] args) throws DatabaseException, SQLException, UnsupportedLookAndFeelException, ParseException, InterruptedException {
		// 

		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set   GUI to another look and feel.
		}

		Database db=Database.getDatabase();
		ModelConnector m=ModelConnector.getModelConnector(db);
		ControllerConnector controller=ControllerConnector.getControllerConnector(m);
		MainView mainView = new MainView(controller);
		

	}

}
