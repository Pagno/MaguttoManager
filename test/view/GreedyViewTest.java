package view;

import java.util.GregorianCalendar;

import model.ModelConnector;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Test;

import com.toedter.calendar.JDateChooser;

import controller.MainController;
import database.Database;

@GUITest
public class GreedyViewTest {

	private FrameFixture frame;
	private DialogFixture editCantiere,associaMacchina;

	public GreedyViewTest() {
		Database db = Database.getDatabase();
		ModelConnector m = ModelConnector.getModelConnector(db);
		MainView mainView = new MainView();
		new MainController(m, mainView);
		frame = new FrameFixture(mainView);

		//Aggiungo una Ruspa
		frame.button("Ruspa").click();
		frame.table("table").requireContents(new String[0][0]);

		// INSERIMENTO
		frame.menuItem("aggiungiRuspa").click();
		DialogFixture editRuspa = frame.dialog();
		editRuspa.textBox("produttore").setText("Ruspa");
		editRuspa.textBox("modello").setText("R203");
		editRuspa.textBox("altezza").setText("6");
		editRuspa.textBox("portata").setText("1500");
		editRuspa.textBox("capacita").setText("350");
		editRuspa.button("OK").click();
		
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

		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("modifica").click();
		editCantiere = frame.dialog();

		// AGGIUNTA NUOVO LAVORO
		editCantiere.tree("tree").clickRow(1);// Aggiungi nuovo lavoro
		editCantiere.panel("pnlLavoro").textBox("nomeLavoro").setText("Scavi");
		dataInizio = editCantiere.robot.finder().findByName(
				"dataInizioLavoro", JDateChooser.class);
		dateInizioFixture = new JDateChooserFixture(
				editCantiere.robot, dataInizio);
		dateInizioFixture.setDate((new GregorianCalendar(2014, 10, 11))
				.getTime());// i mesi partono da 0=Gennaio

		dataFine = editCantiere.robot.finder().findByName(
				"dataFineLavoro", JDateChooser.class);
		dateFineFixture = new JDateChooserFixture(
				editCantiere.robot, dataFine);
		dateFineFixture
				.setDate((new GregorianCalendar(2014, 11, 11)).getTime());

		editCantiere.button("inserisciLavoro").click();

	}

	@After
	public void tearDown() {
		frame.cleanUp();

	}

	@Test
	public void testInserimentoAssociazione() {
		editCantiere.tree("tree").expandRow(1);
		editCantiere.tree("tree").selectRow(2);//Aggiungo richiesta
		editCantiere.panel("pnlAddRichiesta").comboBox("tipoMacchina").selectItem("Ruspa");
		editCantiere.panel("pnlAddRichiesta").textBox("minCapacita").setText("300");
		editCantiere.panel("pnlAddRichiesta").textBox("maxCapacita").setText("600");
		editCantiere.panel("pnlAddRichiesta").button("aggiungi").click();
		editCantiere.tree("tree").expandRow(1);
		editCantiere.tree("tree").selectRow(3);//Visualizzo la richiesta appena aggiunta
		
		editCantiere.panel("pnlVisualizzaPanel").button("associaMacchina").click();
		associaMacchina=editCantiere.dialog("associaMacchina");
		associaMacchina.comboBox("elencoMacchine").requireItemCount(1);
		associaMacchina.comboBox("elencoMacchine").selectItem(0);
		associaMacchina.button("aggiungiAssociazione").click();
		editCantiere.tree("tree").expandRow(1);
		editCantiere.tree("tree").selectRow(3);
		editCantiere.panel("pnlVisualizzaPanel").button("associaMacchina").requireText("Rimuovi Associazione");
	}
}
