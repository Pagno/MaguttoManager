package view;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import model.ModelConnector;
import model.organizer.data.Priorita;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaRuspa;

import org.fest.assertions.AssertExtension;
import org.fest.swing.annotation.GUITest;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import view.utils.JDateChooserFixture;

import com.toedter.calendar.JDateChooser;

import controller.ControllerConnector;
import database.Database;

@GUITest
public class GreedyViewTest {

	private FrameFixture frame;
	private DialogFixture editCantiere, associaMacchina;
	private ModelConnector model;

	public GreedyViewTest() {
		Database db = Database.getDatabase();
		model = ModelConnector.getModelConnector(db);
		MainView mainView = new MainView(ControllerConnector.getControllerConnector(model));
		frame = new FrameFixture(mainView);

		// INSERISCO I DATI DI PARTENZA
		model.aggiungiRuspa("Ruspa", "R203", 350, 1500, 6);//CodiceMacchina 1
		model.aggiungiGru("Gru", "G201", 360, 300, 35, 60);//CodiceMacchina 2
		model.aggiungiRuspa("Ruspa", "R3002", 750, 3500, 8);//CodiceMacchina 3
		model.aggiungiCantiere("Bottanuco", "Chiusa", new GregorianCalendar(
				2014, 10, 11), new GregorianCalendar(2015, 11, 11),
				Priorita.BASSA);//codiceCantiere 1
		model.aggiungiLavoro("Scavi", new GregorianCalendar(2014, 10, 11),
				new GregorianCalendar(2014, 11, 11), 1);//codiceLavoro 1
		model.aggiungiRichiesta(1, 1, new RichiestaRuspa(300, 400, -1, -1, -1, -1));//codiceRichiesta 1
		model.aggiungiRichiesta(1, 1, new RichiestaGru(-1, -1, -1, -1, 250, 400, -1,-1));//codiceRichiesta 2
		model.soddisfaRichiesta(2, 2);

	}

	@Test
	public void testInserimentoAssociazione() {
		// Genero le associazioni tramite algoritmo
		frame.menuItem("itemAssocia").click();
		associaMacchina = frame.dialog("associazioneGreedy");
		associaMacchina.button("generaAssociazioni").click();
		String[] content = associaMacchina.list("listModel").contents();
		associaMacchina.button("confermaAssociazioni").click();

		// Controllo se la macchina associata �� corretta
		String[] s = { "Cantiere:Bottanuco Lavoro:Scavi -->  Macchina: Ruspa - R203" };
		assertArrayEquals(content, s);
	}
	
	@After
	public void testVerificaInserimentoAssociazione() {
		frame.button("Cantiere").click();
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("modifica").click();
		editCantiere = frame.dialog();
		
		editCantiere.tree("tree").expandRow(1);
		editCantiere.tree("tree").selectRow(2);//Aggiungo richiesta
		
		frame.cleanUp();
	}

}
