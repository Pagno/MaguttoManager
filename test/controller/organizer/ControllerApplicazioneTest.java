package controller.organizer;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import model.ModelConnector;
import model.ModelInterface;
import model.organizer.ModelCamion;
import model.organizer.ModelCantiere;
import model.organizer.ModelEscavatore;
import model.organizer.ModelGru;
import model.organizer.ModelRuspa;
import model.organizer.data.Camion;
import model.organizer.data.Priorita;
import model.organizer.data.Ruspa;

import org.junit.Before;
import org.junit.Test;

import database.Database;
import database.DatabaseInterface;

public class ControllerApplicazioneTest {

	ModelConnector model;
	ControllerApplicazione appCtrl;
	Database db;

	public ControllerApplicazioneTest() {
		db = Database.getDatabase();
		model = ModelConnector.getModelConnector(db);
		appCtrl = ControllerApplicazione.getControllerApplicazione(model);
	}

	@Test
	public void testGetControllerApplicazione() {
		assertEquals(appCtrl,ControllerApplicazione.getControllerApplicazione(model));
	}

	@Test
	public void testAggiungiGruObserver() {
		model.ResetAllForTest();	
		
		Object[] v1 = {1, "Gru", "G67",35,45,3000,360};
		testObserver observer = new testObserver();
		appCtrl.aggiungiGruObserver(observer);
		ModelGru.getModelGru().caricaGru(1, "Gru", "G67", 360, 3000, 35, 45);
		assertArrayEquals(observer.s, v1);
	}
	

	@Test
	public void testAggiungiRuspaObserver() {
		model.ResetAllForTest();	
		
		Object[] v1 = {2, "Ruspa", "R101",220, 200, 150};
		testObserver observer = new testObserver();
		appCtrl.aggiungiRuspaObserver(observer);
		ModelRuspa.getModelRuspa().caricaRuspa(2, "Ruspa", "R101", 200, 150, 220);
		assertArrayEquals(observer.s, v1);
	}

	@Test
	public void testAggiungiCamionObserver() {
		model.ResetAllForTest();

		Object[] v1 = { 1, "Camion", "C101", 220, 200, 150 };
		testObserver observer = new testObserver();
		appCtrl.aggiungiCamionObserver(observer);
		ModelCamion.getModelCamion().caricaCamion(1, "Camion", "C101", 200,
				150, 220);
		assertArrayEquals(observer.s, v1);
	}

	@Test
	public void testAggiungiEscavatoreObserver() {
		model.ResetAllForTest();

		Object[] v1 = { 1, "Escavatore", "E302", 20, 8, 3000, 450 };
		testObserver observer = new testObserver();
		appCtrl.aggiungiEscavatoreObserver(observer);
		ModelEscavatore.getModelEscavatore().caricaEscavatore(1, "Escavatore",
				"E302", 3000, 450, 20, 8);
		assertArrayEquals(observer.s, v1);
	}

	@Test
	public void testAggiungiCantiereObserver() {
		model.ResetAllForTest();
		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("dd/MM/yyyy");
		
		Object[] v1={1, "Bottanuco",
				"via Chiusa",df.format((new GregorianCalendar(2014, 10, 21)).getTime()),df.format((new GregorianCalendar(2015, 8, 30)).getTime()),Priorita.ALTA.toString()};
		
		testObserver observer = new testObserver();
		appCtrl.aggiungiCantiereObserver(observer);
		ModelCantiere.getModelCantiere().caricaCantiere(1, "Bottanuco",
				"via Chiusa", new GregorianCalendar(2014, 10, 21),
				new GregorianCalendar(2015, 8, 30), Priorita.ALTA);
		assertArrayEquals(observer.s, v1);
	}

	@Test
	public void testSalvaDatiListener() {
		model.ResetAllForTest();

		ModelCamion.getModelCamion().caricaCamion(1, "Camion", "C101", 200,
				150, 220);
		Camion c = new Camion(1, "Camion", "C101", 200, 150, 220);
		ModelRuspa.getModelRuspa().caricaRuspa(2, "Ruspa", "R101", 200, 150,
				220);
		Ruspa r = new Ruspa(2, "Ruspa", "R101", 200, 150, 220);
		appCtrl.salvaDatiListener();
		model.refreshData();
		assertEquals(c, model.getMacchina(1));
		assertEquals(r, model.getMacchina(2));
	}

	@Test
	public void testCaricaDatiListener() {
		model.ResetAllForTest();
		Camion c = new Camion(1, "Camion", "C101", 200, 150, 220);
		System.out.println(c);
		ModelCamion a = ModelCamion.getModelCamion();
		a.aggiungiCamionForTest(c);

		Ruspa r = new Ruspa(2, "Ruspa", "R101", 200, 150, 220);
		ModelRuspa.getModelRuspa().aggiungiRuspaForTest(r);

		appCtrl.salvaDatiListener();
		appCtrl.caricaDatiListener();

		assertEquals(c, model.getMacchina(1));
		assertEquals(r, model.getMacchina(2));
	}

	@Test
	public void testEliminaMacchina() {
		model.ResetAllForTest();
		model.ResetAllForTest();
		ModelCamion.getModelCamion().caricaCamion(1, "Camion", "C101", 200,
				150, 220);
		Camion c = new Camion(1, "Camion", "C101", 200, 150, 220);
		ModelRuspa.getModelRuspa().caricaRuspa(2, "Ruspa", "R101", 200, 150,
				220);
		Ruspa r = new Ruspa(2, "Ruspa", "R101", 200, 150, 220);
		appCtrl.eliminaMacchina(1);
		assertEquals(null, model.getMacchina(1));
	}

	@Test
	public void testEliminaCantiere() {
		model.ResetAllForTest();
		ModelCantiere.getModelCantiere().caricaCantiere(1, "Bottanuco",
				"via CHiusa", new GregorianCalendar(2014, 10, 21),
				new GregorianCalendar(2015, 8, 30), Priorita.ALTA);
		appCtrl.eliminaCantiere(1);
		assertEquals(null, model.getCantiere(1));
	}

}
