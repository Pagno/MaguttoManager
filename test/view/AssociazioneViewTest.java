package view;

import java.util.GregorianCalendar;

import model.ModelConnector;
import model.organizer.data.Priorita;

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
public class AssociazioneViewTest {

	private FrameFixture frame;
	private DialogFixture editCantiere,associaMacchina;

	public AssociazioneViewTest() {
		Database db = Database.getDatabase();
		ModelConnector m = ModelConnector.getModelConnector(db);
		MainView mainView = new MainView();
		ControllerConnector.getControllerConnector(m, mainView);
		frame = new FrameFixture(mainView);

		//Aggiungo una Ruspa
		frame.button("Ruspa").click();
		frame.table("table").requireContents(new String[0][0]);

		m.aggiungiRuspa("Ruspa", "R203", 350, 1500, 6);
		m.aggiungiCantiere("Bottanuco", "Chiusa", new GregorianCalendar(2014, 10, 11), new GregorianCalendar(2015, 11, 11), Priorita.BASSA);
		m.aggiungiLavoro("Scavi", new GregorianCalendar(2014, 10, 11), new GregorianCalendar(2014, 11, 11), 1);


	}

	@After
	public void tearDown() {
		frame.cleanUp();

	}

	@Test
	public void testInserimentoAssociazione() {
		frame.button("Cantiere").click();
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("modifica").click();
		editCantiere = frame.dialog();
		
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
