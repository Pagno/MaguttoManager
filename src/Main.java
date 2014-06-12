import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import view.MainView;

import controller.MainController;
import model.ModelConnector;
import database.Database;
import database.DBException;


// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws DBException the DB exception
	 * @throws SQLException the SQL exception
	 * @throws UnsupportedLookAndFeelException the unsupported look and feel exception
	 * @throws ParseException the parse exception
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws DBException, SQLException, UnsupportedLookAndFeelException, ParseException, InterruptedException {
		// TODO Auto-generated method stub

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

		Database db=Database.getDatabase();
		ModelConnector m=ModelConnector.getModelConnector(db);
		MainView mainView = new MainView();
		MainController vis=new MainController(m,mainView);

	}

}