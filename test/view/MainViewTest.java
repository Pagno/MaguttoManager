package view;

import model.ModelConnector;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Test;

import controller.MainController;
import database.Database;

@GUITest
public class MainViewTest {

	private FrameFixture frame;

	public MainViewTest() {

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
		frame.table("table").requireContents(
				new String[][] { { "Gru", "G101", "35", "45", "12", "13" } });

		frame.menuItem("aggiungiGru").click();
		DialogFixture editGru = frame.dialog();
		editGru.textBox("produttore").setText("GRU2");
		editGru.textBox("modello").setText("G203");
		editGru.textBox("altezza").setText("56");
		editGru.textBox("lunghezza").setText("65");
		editGru.textBox("angolo").setText("300");
		editGru.textBox("portata").setText("2500");
		editGru.button("OK").click();

		frame.button("Gru").click();
		frame.table("table").requireContents(
				new String[][] { { "Gru", "G101", "35", "45", "12", "13" },
						{ "GRU2", "G203", "65", "56", "2500", "300" } });
	}

	@Test
	public void testClickBtnRuspa() {
		frame.button("Ruspa").click();
		frame.table("table")
				.requireContents(
						new String[][] { { "New Holland", "R101", "4", "350",
								"3000" } });

		frame.menuItem("aggiungiRuspa").click();
		DialogFixture editRuspa = frame.dialog();
		editRuspa.textBox("produttore").setText("Ruspa");
		editRuspa.textBox("modello").setText("R203");
		editRuspa.textBox("altezza").setText("6");
		editRuspa.textBox("portata").setText("1500");
		editRuspa.textBox("capacita").setText("300");
		editRuspa.button("OK").click();

		frame.button("Ruspa").click();
		frame.table("table")
				.requireContents(
						new String[][] { { "New Holland", "R101", "4", "350","3000" },
										{ "Ruspa", "R203", "6", "300","1500" }});
	}

	@Test
	public void testClickBtnCamion() {
		frame.button("Camion").click();
		frame.table("table").requireContents(new String[0][0]);
		
		frame.menuItem("aggiungiCamion").click();
		DialogFixture editRuspa = frame.dialog();
		editRuspa.textBox("produttore").setText("Camion");
		editRuspa.textBox("modello").setText("C101");
		editRuspa.textBox("lunghezza").setText("10");
		editRuspa.textBox("portata").setText("3500");
		editRuspa.textBox("capacita").setText("800");
		editRuspa.button("OK").click();

		frame.button("Camion").click();
		frame.table("table")
				.requireContents(
						new String[][] { { "Camion", "C101", "10", "800","3500" }});
	}

	@Test
	public void testClickBtnEscavatore() {
		frame.button("Escavatore").click();
		frame.table("table").requireContents(new String[0][0]);
		
		frame.menuItem("aggiungiEscavatore").click();
		DialogFixture editRuspa = frame.dialog();
		editRuspa.textBox("produttore").setText("Escavatore");
		editRuspa.textBox("modello").setText("E101");
		editRuspa.textBox("altezza").setText("10");
		editRuspa.textBox("profondita").setText("5");
		editRuspa.textBox("capacita").setText("200");
		editRuspa.textBox("portata").setText("1200");
		editRuspa.button("OK").click();

		frame.button("Escavatore").click();
		frame.table("table")
				.requireContents(
						new String[][] { { "Escavatore", "E101", "10", "5","200","1200" }});
	}
}
