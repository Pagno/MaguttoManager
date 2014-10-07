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
public class InsertTest {

	private FrameFixture frame;

	public InsertTest() {

		Database db = Database.getDatabase();
		ModelConnector m = ModelConnector.getModelConnector(db);
		MainView mainView = new MainView();
		new MainController(m, mainView);
		frame = new FrameFixture(mainView);
	}

	@After
	public void tearDown() {
		frame.cleanUp();
	}

	@Test
	public void testClickBtnGru() {
		frame.button("Gru").click();
		frame.table("table").requireContents(new String[0][0]);

		// INSERIMENTO
		frame.menuItem("aggiungiGru").click();
		DialogFixture editGru = frame.dialog();
		editGru.textBox("produttore").setText("GRU2");
		editGru.textBox("modello").setText("G203");
		editGru.textBox("altezza").setText("56");
		editGru.textBox("lunghezza").setText("65");
		editGru.textBox("angolo").setText("300");
		editGru.textBox("portata").setText("2500");
		editGru.button("OK").click();

		// MODIFICA
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("modifica").click();
		editGru = frame.dialog();
		editGru.textBox("produttore").requireText("GRU2");
		editGru.textBox("modello").requireText("G203");
		editGru.textBox("altezza").requireText("56");
		editGru.textBox("lunghezza").requireText("65");
		editGru.textBox("angolo").requireText("300");
		editGru.textBox("portata").requireText("2500");

		editGru.textBox("produttore").setText("GRU3");
		editGru.button("OK").click();

		frame.table("table")
				.requireContents(
						new String[][] { { "GRU3", "G203", "65", "56", "2500",
								"300" } });

		// CANCELLAZIONE
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("elimina").click();
		frame.table("table").requireContents(new String[0][0]);

	}

	@Test
	public void testClickBtnRuspa() {
		frame.button("Ruspa").click();
		frame.table("table").requireContents(new String[0][0]);

		// INSERIMENTO
		frame.menuItem("aggiungiRuspa").click();
		DialogFixture editRuspa = frame.dialog();
		editRuspa.textBox("produttore").setText("Ruspa");
		editRuspa.textBox("modello").setText("R203");
		editRuspa.textBox("altezza").setText("6");
		editRuspa.textBox("portata").setText("1500");
		editRuspa.textBox("capacita").setText("300");
		editRuspa.button("OK").click();

		frame.button("Ruspa").click();
		frame.table("table").requireContents(
				new String[][] { { "Ruspa", "R203", "6", "300", "1500" } });

		// MODIFICA
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("modifica").click();
		editRuspa = frame.dialog();
		editRuspa.textBox("produttore").requireText("Ruspa");
		editRuspa.textBox("modello").requireText("R203");
		editRuspa.textBox("altezza").requireText("6");
		editRuspa.textBox("portata").requireText("1500");
		editRuspa.textBox("capacita").requireText("300");

		editRuspa.textBox("portata").setText("500");
		editRuspa.button("OK").click();

		frame.table("table").requireContents(
				new String[][] { { "Ruspa", "R203", "6", "300", "500" } });

		// CANCELLAZIONE
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("elimina").click();
		frame.table("table").requireContents(new String[0][0]);
	}

	@Test
	public void testClickBtnCamion() {
		frame.button("Camion").click();
		frame.table("table").requireContents(new String[0][0]);

		// INSERIMENTO
		frame.menuItem("aggiungiCamion").click();
		DialogFixture editCamion = frame.dialog();
		editCamion.textBox("produttore").setText("Camion");
		editCamion.textBox("modello").setText("C101");
		editCamion.textBox("lunghezza").setText("10");
		editCamion.textBox("portata").setText("3500");
		editCamion.textBox("capacita").setText("800");
		editCamion.button("OK").click();

		frame.button("Camion").click();
		frame.table("table").requireContents(
				new String[][] { { "Camion", "C101", "10", "800", "3500" } });

		// MODIFICA
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("modifica").click();
		editCamion = frame.dialog();

		editCamion.textBox("produttore").requireText("Camion");
		editCamion.textBox("modello").requireText("C101");
		editCamion.textBox("lunghezza").requireText("10");
		editCamion.textBox("portata").requireText("3500");
		editCamion.textBox("capacita").requireText("800");

		editCamion.textBox("portata").setText("1500");

		editCamion.button("OK").click();
		frame.table("table").requireContents(
				new String[][] { { "Camion", "C101", "10", "800", "1500" } });

		// CANCELLAZIONE
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("elimina").click();
		frame.table("table").requireContents(new String[0][0]);

	}

	@Test
	public void testClickBtnEscavatore() {
		frame.button("Escavatore").click();
		frame.table("table").requireContents(new String[0][0]);

		// INSERIMENTO
		frame.menuItem("aggiungiEscavatore").click();
		DialogFixture editEscavatore = frame.dialog();
		editEscavatore.textBox("produttore").setText("Escavatore");
		editEscavatore.textBox("modello").setText("E101");
		editEscavatore.textBox("altezza").setText("10");
		editEscavatore.textBox("profondita").setText("5");
		editEscavatore.textBox("capacita").setText("200");
		editEscavatore.textBox("portata").setText("1200");
		editEscavatore.button("OK").click();

		frame.button("Escavatore").click();
		frame.table("table").requireContents(
				new String[][] { { "Escavatore", "E101", "10", "5", "200",
						"1200" } });

		// MODIFICA
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("modifica").click();
		editEscavatore = frame.dialog();
		editEscavatore.textBox("produttore").requireText("Escavatore");
		editEscavatore.textBox("modello").requireText("E101");
		editEscavatore.textBox("altezza").requireText("10");
		editEscavatore.textBox("profondita").requireText("5");
		editEscavatore.textBox("capacita").requireText("200");
		editEscavatore.textBox("portata").requireText("1200");

		editEscavatore.textBox("altezza").setText("110");
		editEscavatore.button("OK").click();

		frame.table("table").requireContents(
				new String[][] { { "Escavatore", "E101", "110", "5", "200",
						"1200" } });

		// CANCELLAZIONE
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("elimina").click();
		frame.table("table").requireContents(new String[0][0]);
	}

	@Test
	public void testClickBtnCantiere() {
		frame.button("Cantiere").click();
		frame.table("table").requireContents(new String[0][0]);

		// INSERIMENTO
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

		// CANCELLAZIONE
		frame.table("table").selectRows(frame.table("table").rowCount() - 1);
		frame.button("elimina").click();
		frame.table("table").requireContents(new String[0][0]);

	}

}
