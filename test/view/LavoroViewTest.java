package view;

import java.util.GregorianCalendar;

import model.ModelConnector;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Test;

import view.utils.JDateChooserFixture;

import com.toedter.calendar.JDateChooser;

import controller.ControllerConnector;
import database.Database;

@GUITest
public class LavoroViewTest {

	private FrameFixture frame;
	private DialogFixture editCantiere;
	
	public LavoroViewTest() {
		Database db = Database.getDatabase();
		ModelConnector m = ModelConnector.getModelConnector(db);
		MainView mainView = new MainView(ControllerConnector.getControllerConnector(m));
		frame = new FrameFixture(mainView);
		
		
		frame.button("Cantiere").click();
		frame.table("table").requireContents(new String[0][0]);

		frame.menuItem("aggiungiCantiere").click();
		editCantiere = frame.dialog();
		editCantiere.textBox("nome").setText("Bottanuco");
		editCantiere.textBox("indirizzo").setText("Chiusa");
		JDateChooser dataInizio = editCantiere.robot.finder().findByName(
				"dataInizio", JDateChooser.class);
		JDateChooserFixture dateInizioFixture = new JDateChooserFixture(
				editCantiere.robot, dataInizio);
		dateInizioFixture.setDate((new GregorianCalendar(2014, 10, 11))
				.getTime());// i mesi partono da 0=Gennaio

		JDateChooser dataFine = editCantiere.robot.finder().findByName(
				"dataFine", JDateChooser.class);
		JDateChooserFixture dateFineFixture = new JDateChooserFixture(
				editCantiere.robot, dataFine);
		dateFineFixture
				.setDate((new GregorianCalendar(2015, 11, 11)).getTime());

		editCantiere.button("OK").click();

		frame.button("Cantiere").click();
		
		frame.table("table").requireContents(
				new String[][] { { "Bottanuco", "Chiusa", "11/11/2014",
						"11/12/2015", "BASSA" } });

	}

	@After
	public void tearDown() {
		frame.cleanUp();

	}

	
	@Test
	public void testInserimentoLavoro(){
		frame.button("Cantiere").click();
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("modifica").click();
		editCantiere = frame.dialog();
		
		//AGGIUNTA NUOVO LAVORO
		editCantiere.tree("tree").clickRow(1);// Aggiungi nuovo lavoro
		editCantiere.panel("pnlLavoro").textBox("nomeLavoro").setText("Scavi");
		JDateChooser dataInizio = editCantiere.robot.finder().findByName(
				"dataInizioLavoro", JDateChooser.class);
		JDateChooserFixture dateInizioFixture = new JDateChooserFixture(
				editCantiere.robot, dataInizio);
		dateInizioFixture.setDate((new GregorianCalendar(2014, 10, 11))
				.getTime());// i mesi partono da 0=Gennaio
		
		JDateChooser dataFine = editCantiere.robot.finder().findByName(
				"dataFineLavoro", JDateChooser.class);
		JDateChooserFixture dateFineFixture = new JDateChooserFixture(
				editCantiere.robot, dataFine);
		dateFineFixture.setDate((new GregorianCalendar(2014, 11, 11))
				.getTime());// i mesi partono da 0=Gennaio
		
		editCantiere.button("inserisciLavoro").click();

		//Verifico il corretto inserimeno
		editCantiere.tree("tree").clickRow(0);
		editCantiere.tree("tree").expandRow(1);
		editCantiere.tree("tree").clickRow(1);
		editCantiere.panel("pnlLavoro").textBox("nomeLavoro").requireText("Scavi");
		dateInizioFixture.requireDate((new GregorianCalendar(2014, 10, 11))
				.getTime());// i mesi partono da 0=Gennaio
		dateFineFixture.requireDate((new GregorianCalendar(2014, 11, 11))
				.getTime());// i mesi partono da 0=Gennaio
		
		//Modifico in lavoro
		editCantiere.panel("pnlLavoro").textBox("nomeLavoro").setText("Fondamenta");
		dateFineFixture.setDate((new GregorianCalendar(2015, 5, 30))
				.getTime());// i mesi partono da 0=Gennaio
		dateInizioFixture.setDate((new GregorianCalendar(2015, 0, 11))
				.getTime());// i mesi partono da 0=Gennaio
		
		editCantiere.panel("pnlLavoro").button("inserisciLavoro").click();
		editCantiere.button("chiudi");
		
		
		//Verifico che le modifiche siano state salvate
		frame.button("Cantiere").click();
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("modifica").click();
		editCantiere = frame.dialog();
		editCantiere.panel("pnlLavoro").textBox("nomeLavoro").requireText("Fondamenta");
		dateInizioFixture.requireDate((new GregorianCalendar(2015, 0, 11))
				.getTime());// i mesi partono da 0=Gennaio
		dateFineFixture.requireDate((new GregorianCalendar(2015, 5, 30))
				.getTime());// i mesi partono da 0=Gennaio
	
	}
}
