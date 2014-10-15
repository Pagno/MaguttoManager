package view;

import java.util.GregorianCalendar;

import model.ModelConnector;
import model.organizer.data.Priority;

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
public class CantiereViewTest {

	private FrameFixture frame;

	public CantiereViewTest() {
		Database db = Database.getDatabase();
		ModelConnector m = ModelConnector.getModelConnector(db);
		MainView mainView = new MainView();
		ControllerConnector.getControllerConnector(m, mainView);
		frame = new FrameFixture(mainView);
		
		
		frame.button("Cantiere").click();
		frame.table("table").requireContents(new String[0][0]);

		frame.menuItem("aggiungiCantiere").click();
		DialogFixture editCantiere = frame.dialog();
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
				.setDate((new GregorianCalendar(2014, 11, 11)).getTime());

		editCantiere.button("OK").click();

		frame.button("Cantiere").click();
		String[][] a = frame.table("table").contents();
		System.out.println(a[0][0] + " - " + a[0][1] + " - " + a[0][2] + " - "
				+ a[0][3] + " - " + a[0][4]);
		frame.table("table").requireContents(
				new String[][] { { "Bottanuco", "Chiusa", "11/11/2014",
						"11/12/2014", "BASSA" } });

	}

	@After
	public void tearDown() {
		frame.cleanUp();

	}
	

	@Test 
	public void testModificaCantiere(){
		frame.button("Cantiere").click();
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.table("table").selectRows(0);
		frame.button("modifica").click();
		DialogFixture editCantiere = frame.dialog();
		//MODIFICA CANTIERE
		editCantiere.tree("tree").clickRow(1);//Seleziono nuovo lavoro
		editCantiere.tree("tree").clickRow(0);//Seleziono il cantiere
		
		//Verifico se i dati nelle textBox sono corretti
		editCantiere.panel("pnlCantiere").textBox("nomeCantiere").requireText("Bottanuco");
		editCantiere.panel("pnlCantiere").textBox("indirizzoCantiere").requireText("Chiusa");
		JDateChooser dataInizio = editCantiere.robot.finder().findByName(
				"dataInizioCantiere", JDateChooser.class);
		JDateChooserFixture dateInizioFixture = new JDateChooserFixture(
				editCantiere.robot, dataInizio);
		dateInizioFixture.requireDate((new GregorianCalendar(2014, 10, 11).getTime()));
		
		JDateChooser dataFine = editCantiere.robot.finder().findByName(
				"dataFineCantiere", JDateChooser.class);
		JDateChooserFixture dateFineFixture = new JDateChooserFixture(
				editCantiere.robot, dataFine);
		dateFineFixture.requireDate((new GregorianCalendar(2014, 11, 11)).getTime());// i mesi partono da 0=Gennaio
		editCantiere.panel("pnlCantiere").comboBox("prioritaCantiere").requireSelection(Priority.BASSA.toString());
		
		
		//Modifico i dati del Cantiere
		editCantiere.panel("pnlCantiere").textBox("nomeCantiere").setText("Chignolo d'Isola");
		editCantiere.panel("pnlCantiere").textBox("indirizzoCantiere").setText("Vittorio Alfieri");
		dateInizioFixture = new JDateChooserFixture(editCantiere.robot, dataInizio);
		dateInizioFixture.setDate((new GregorianCalendar(2014, 9, 11).getTime()));
		dateFineFixture = new JDateChooserFixture(editCantiere.robot, dataFine);
		dateFineFixture.setDate((new GregorianCalendar(2014, 11, 21)).getTime());
		editCantiere.panel("pnlCantiere").comboBox("prioritaCantiere").selectItem(Priority.ALTA.toString());
		
		editCantiere.panel("pnlCantiere").button("edit").click();
		
		//Verifico che i dati siano stati modificati correttamente
		frame.table("table").requireContents(
				new String[][] { { "Chignolo d'Isola", "Vittorio Alfieri", "11/10/2014",
						"21/12/2014", "ALTA" } });
		
	}
}
