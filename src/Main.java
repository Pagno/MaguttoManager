import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import view.MainView;
import controller.ControllerConnector;
import controller.organizer.ApplicationController;
import controller.organizer.CantiereController;
import controller.organizer.InsertController;
import model.ModelConnector;
import database.Database;
import database.DBException;


// 
/**
 *   Class Main.
 */
public class Main {
	//TODO per tutte le classi: se c'è un ciclo e all'interno si rimuovono degli elementi, controllare che funzioni!!!
	/**
	 *   main method.
	 *
	 * @param args   arguments
	 * @throws DBException   DB exception
	 * @throws SQLException   SQL exception
	 * @throws UnsupportedLookAndFeelException   unsupported look and feel exception
	 * @throws ParseException   parse exception
	 * @throws InterruptedException   interrupted exception
	 */
	public static void main(String[] args) throws DBException, SQLException, UnsupportedLookAndFeelException, ParseException, InterruptedException {
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
		MainView mainView=null ;
		mainView = new MainView();
		ControllerConnector controller=ControllerConnector.getControllerConnector(m, mainView);
		mainView.setControllerConnector(controller);
		

	}

}
