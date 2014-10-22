package controller.organizer;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import model.ModelConnector;
import model.organizer.ModelCamion;
import model.organizer.ModelEscavatore;
import model.organizer.ModelGru;
import model.organizer.ModelRuspa;
import model.organizer.data.Camion;
import model.organizer.data.Cantiere;
import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Priorita;
import model.organizer.data.Ruspa;

import org.junit.Test;

import database.Database;

public class ControllerInserimentoTest {
	ModelConnector model;
	ControllerInserimento insCtrl;
	Database db;

	public ControllerInserimentoTest() {
		db = Database.getDatabase();
		model = ModelConnector.getModelConnector(db);
		insCtrl = ControllerInserimento.getControllerInserimento(model);
	}

	@Test
	public void testGetControllerInserimento() {
		assertEquals(insCtrl,
				ControllerInserimento.getControllerInserimento(model));
	}

	@Test
	public void testAggiungiGru() {
		model.ResetAllForTest();

		insCtrl.aggiungiGru("", "G402", 360, 3000, 45, 60);
		insCtrl.aggiungiGru("Gru", "G402", 360, 3000, 45, 60);
		Gru m=(Gru)model.getMacchina(1);
		
		
		assertEquals("Gru",m.getProduttore());
		assertEquals("G402",m.getModello());
		assertEquals(360,m.getAngoloRotazione());
		assertEquals(3000,m.getPortataMassima());
		assertEquals(45,m.getLunghezza());
		assertEquals(60,m.getAltezza());
	}

	@Test
	public void testModificaGru() {
		model.ResetAllForTest();

		Gru g = new Gru(1, "Gru", "G402", 360, 3000, 45, 60);
		insCtrl.aggiungiGru("Gru", "G402", 360, 3000, 45, 60);
		assertEquals(g, model.getMacchina(1));

		g.setProduttore("GRu1");
		g.setAltezza(25);
		g.setAngoloRotazione(300);
		g.setLunghezzaMassima(21);
		g.setModello("G309");
		g.setPortataMassima(2500);

		insCtrl.modificaGru(1, "", "G309", 300, 2500, 21, 25);
		insCtrl.modificaGru(1, "GRu1", "G309", 300, 2500, 21, 25);

		assertEquals(g, model.getMacchina(1));

	}

	@Test
	public void testAggiungiRuspa() {
		model.ResetAllForTest();

		insCtrl.aggiungiRuspa("", "R908", 1500, 450, 9);
		insCtrl.aggiungiRuspa("Ruspa", "R908", 1500, 450, 9);
		Ruspa g=(Ruspa)model.getMacchina(1);

		
		assertEquals("Ruspa",g.getProduttore());
		assertEquals("R908",g.getModello());
		assertEquals(1500,g.getCapacitaMassima());
		assertEquals(450,g.getPortataMassima());
		assertEquals(9,g.getAltezzaMassima());
	}

	@Test
	public void testModificaRuspa() {
		model.ResetAllForTest();
		
		Ruspa g = new Ruspa(1, "Ruspa", "R908",1500, 450, 9);
		ModelRuspa.getModelRuspa().aggiungiRuspaForTest(g);
		assertEquals(g, model.getMacchina(1));
		
		g.setProduttore("RUSPA1");
		g.setModello("R809");
		g.setCapacitaMassima(1750);
		g.setPortataMassima(560);
		g.setAltezzaMassima(11);

		insCtrl.modificaRuspa(1, "", "R809", 1750, 560, 11);
		insCtrl.modificaRuspa(1, "RUSPA1", "R809", 1750, 560, 11);
		assertEquals(g, model.getMacchina(1));
	}

	@Test
	public void testAggiungiEscavatore() {
		model.ResetAllForTest();

		insCtrl.aggiungiEscavatore("", "E456", 2300, 780, 13,9);
		insCtrl.aggiungiEscavatore("Escavatore", "E456", 2300, 780, 13,9);
		Escavatore m=(Escavatore)model.getMacchina(1);

		
		assertEquals("Escavatore",m.getProduttore());
		assertEquals("E456",m.getModello());
		assertEquals(2300,m.getCapacitaMassima());
		assertEquals(780,m.getPortataMassima());
		assertEquals(13,m.getAltezzaMassima());
		assertEquals(9,m.getProfonditaMassima());
	}

	@Test
	public void testModificaEscavatore() {
		model.ResetAllForTest();
		
		insCtrl.aggiungiEscavatore("Escavatore", "E501", 2100, 130, 9, 6);

		insCtrl.modificaEscavatore(1, "", "E498", 1750, 560, 11,5);
		insCtrl.modificaEscavatore(1, "Escavatore2000", "E498", 1750, 560, 11,5);
		Escavatore g=(Escavatore)model.getMacchina(1);
		
		
		assertEquals("Escavatore2000",g.getProduttore());
		assertEquals("E498",g.getModello());
		assertEquals(1750,g.getCapacitaMassima());
		assertEquals(560,g.getPortataMassima());
		assertEquals(11,g.getAltezzaMassima());
		assertEquals(5,g.getProfonditaMassima());
	}

	@Test
	public void testAggiungiCamion() {
		model.ResetAllForTest();

		insCtrl.aggiungiCamion("","C121", 9800, 3200, 23);
		insCtrl.aggiungiCamion("Camion","C121", 9800, 3200, 23);
		Camion m=(Camion)model.getMacchina(1);

		
		assertEquals("Camion",m.getProduttore());
		assertEquals("C121",m.getModello());
		assertEquals(9800,m.getCapacitaMassima());
		assertEquals(3200,m.getPortataMassima());
		assertEquals(23,m.getLunghezza());
	}

	@Test
	public void testModificaCamion() {
		model.ResetAllForTest();

		insCtrl.aggiungiCamion("Camion","C121", 9800, 3200, 23);

		Camion m=(Camion)model.getMacchina(1);
		

		insCtrl.modificaCamion(1,"","SC2100", 7000, 1200, 13);
		insCtrl.modificaCamion(1,"SCANIA","SC2100", 7000, 1200, 13);
		
		assertEquals("SCANIA",m.getProduttore());
		assertEquals("SC2100",m.getModello());
		assertEquals(7000,m.getCapacitaMassima());
		assertEquals(1200,m.getPortataMassima());
		assertEquals(13,m.getLunghezza());
	}

	@Test
	public void testAggiungiCantiere() {
		model.ResetAllForTest();
		insCtrl.aggiungiCantiere("", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		insCtrl.aggiungiCantiere("Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2013, 12, 22), Priorita.ALTA);
		insCtrl.aggiungiCantiere("Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		Cantiere m=(Cantiere)model.getCantiere(1);

		
		assertEquals("Bottanuco",m.getNomeCantiere());
		assertEquals("via Chiusa",m.getIndirizzo());
		assertEquals(new GregorianCalendar(2014, 11, 1),m.getDataApertura());
		assertEquals(new GregorianCalendar(2015, 12, 22),m.getDataChiusura());
		assertEquals(Priorita.ALTA,m.getPriorita());
	}

}
