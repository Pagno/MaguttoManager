package controller.organizer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.ModelConnector;
import model.organizer.ModelCantiere;
import model.organizer.ModelGru;
import model.organizer.data.Cantiere;
import model.organizer.data.Gru;
import model.organizer.data.Lavoro;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaGru;

import org.fest.util.Arrays;
import org.junit.Test;

import controller.data.Associazione;
import database.Database;

public class ControllerCantiereTest {
	ModelConnector model;
	ControllerCantiere canCtrl;
	Database db;
	

	public ControllerCantiereTest() {
		db = Database.getDatabase();
		model = ModelConnector.getModelConnector(db);
		canCtrl = ControllerCantiere.getControllerCantiere(model);
	}
	
	
	@Test
	public void testGetControllerCantiere() {
		assertEquals(canCtrl,ControllerCantiere.getControllerCantiere(model));
	}
	@Test
	public void testConstructor(){
		canCtrl.initForTest();
		assertNotNull(ControllerCantiere.getControllerCantiere(model));
	}
	@Test
	public void testGetCantiere() {
		model.ResetAllForTest();

		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		Cantiere m=canCtrl.getCantiere(21);

		
		assertEquals("Bottanuco",m.getNomeCantiere());
		assertEquals("via Chiusa",m.getIndirizzo());
		assertEquals(new GregorianCalendar(2014, 11, 1),m.getDataApertura());
		assertEquals(new GregorianCalendar(2015, 12, 22),m.getDataChiusura());
		assertEquals(Priorita.ALTA,m.getPriorita());
	}
	@Test
	public void testConfermaAssociazioniListener(){
		model.ResetAllForTest();
		ModelGru.getModelGru().caricaGru(17, "Gru", "G405", 360, 3000, 30, 21);
		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(21, 45, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		Lavoro lavoro=ModelCantiere.getModelCantiere().getLavoro(21);
		RichiestaGru richiestaGru=new RichiestaGru(25, 35, -1, -1, -1, -1, -1, -1);
		//ModelCantiere.getModelCantiere().caricaRichiesta(21, 45, 3, richiestaGru, null);
		Richiesta richiesta=new Richiesta(richiestaGru, lavoro);
		assertFalse(richiesta.isSoddisfatta());
		
		Associazione a=new Associazione(richiesta, model.getMacchina(17));
		ArrayList<Associazione> data=new ArrayList<Associazione>();
		data.add(a);
		canCtrl.confermaAssociazioniListener(data);
		
		assertTrue(richiesta.isSoddisfatta());
	}
	@Test
	public void testGetElencoMacchineDisponibili(){
		model.ResetAllForTest();
		Gru g=new Gru(17, "Gru", "G405", 360, 3000, 30, 21);
		
		ModelGru.getModelGru().aggiungiGruForTest(g);
		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(21, 45, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));

		RichiestaGru richiestaGru=new RichiestaGru(25, 35, -1, -1, -1, -1, -1, -1);
		ModelCantiere.getModelCantiere().caricaRichiesta(21, 45, 17, richiestaGru, null);
		
		ArrayList<Macchina> arr=canCtrl.getElencoMacchineDisponibili(17);
		ArrayList<Macchina> arr2=new ArrayList<Macchina>(java.util.Arrays.asList(g));
		assertEquals(arr, arr2);
		canCtrl.soddisfaRichiesta(17,17);
		arr=canCtrl.getElencoMacchineDisponibili(17);
		assertNotEquals(arr, arr2);
	}
	
	@Test
	public void testModificaCantiere() {
		model.ResetAllForTest();

		ModelCantiere.getModelCantiere().caricaCantiere(12,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		canCtrl.modificaCantiere(12,"", "via Vittorio Alfieri", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2016, 12, 22), Priorita.BASSA);
		
		canCtrl.modificaCantiere(12,"Chignolo", "via Vittorio Alfieri", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2013, 12, 22), Priorita.BASSA);
		
		canCtrl.modificaCantiere(12,"Chignolo", "via Vittorio Alfieri", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2016, 12, 22), Priorita.BASSA);
		
		
		Cantiere m=canCtrl.getCantiere(12);

		
		assertEquals("Chignolo",m.getNomeCantiere());
		assertEquals("via Vittorio Alfieri",m.getIndirizzo());
		assertEquals(new GregorianCalendar(2015, 0, 1),m.getDataApertura());
		assertEquals(new GregorianCalendar(2016, 12, 22),m.getDataChiusura());
		assertEquals(Priorita.BASSA,m.getPriorita());
	}

	@Test
	public void testAggiungiLavoro() {
		model.ResetAllForTest();

		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		canCtrl.aggiungiLavoro(21, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		
		Lavoro lavoro=ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(1);
		
		assertEquals(21,lavoro.getCantiere().getCodice());
		assertEquals("Scavi",lavoro.getNome());
		assertEquals( new GregorianCalendar(2015, 0, 1),lavoro.getDataInizio());
		assertEquals(new GregorianCalendar(2015, 1, 28),lavoro.getDataFine());
	}

	@Test
	public void testSoddisfaRichiesta() {
		model.ResetAllForTest();
		ModelGru.getModelGru().caricaGru(17, "Gru", "G405", 360, 3000, 30, 21);
		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(21, 45, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		RichiestaGru richiestaGru=new RichiestaGru(25, 35, -1, -1, -1, -1, -1, -1);
		ModelCantiere.getModelCantiere().caricaRichiesta(21, 45, 3, richiestaGru, null);
		
		assertFalse(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(3).isSoddisfatta());
		
		canCtrl.soddisfaRichiesta(3, 17);
		
		assertTrue(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(3).isSoddisfatta());
	}

	@Test
	public void testEliminaLavoro() {
		model.ResetAllForTest();

		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(21, 45, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		assertTrue(ModelCantiere.getModelCantiere().getCantiere(21).hasLavoro(45));
		canCtrl.eliminaLavoro(45);
		assertFalse(ModelCantiere.getModelCantiere().getCantiere(21).hasLavoro(45));
		
	}

	@Test
	public void testEliminaRichiesta() {
		model.ResetAllForTest();

		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(21, 45, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		RichiestaGru richiestaGru=new RichiestaGru(25, 35, -1, -1, -1, -1, -1, -1);
		ModelCantiere.getModelCantiere().caricaRichiesta(21, 45, 3, richiestaGru, null);
		
		assertTrue(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).hasRichiesta(3));
		
		canCtrl.eliminaRichiesta(3);
		
		assertFalse(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).hasRichiesta(3));
		
	}

	@Test
	public void testLiberaRichiesta() {
		model.ResetAllForTest();
		ModelGru.getModelGru().caricaGru(17, "Gru", "G405", 360, 3000, 30, 21);
		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(21, 45, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		RichiestaGru richiestaGru=new RichiestaGru(25, 35, -1, -1, -1, -1, -1, -1);
		ModelCantiere.getModelCantiere().caricaRichiesta(21, 45, 3, richiestaGru, null);
		
		assertFalse(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(3).isSoddisfatta());
		
		canCtrl.soddisfaRichiesta(3, 17);
		
		assertTrue(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(3).isSoddisfatta());
		canCtrl.liberaRichiesta(3);
		assertFalse(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(3).isSoddisfatta());
		
	}

	@Test
	public void testAggiungiRichiesta() {
		model.ResetAllForTest();		
		
		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(21, 45, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		RichiestaGru richiestaGru=new RichiestaGru(25, 35, -1, -1, -1, -1, -1, -1);
		canCtrl.aggiungiRichiesta(21, 45, richiestaGru);
		
		assertNotNull(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(1));
	}

	@Test
	public void testModificaLavoro() {
		model.ResetAllForTest();

		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(21,15, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		
		
		canCtrl.modificaLavoro(15, "Fondamenta", new GregorianCalendar(2015, 2, 15), new GregorianCalendar(2015, 4, 1));
		Lavoro lavoro=ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(15);
		
		assertEquals(21,lavoro.getCantiere().getCodice());
		assertEquals("Fondamenta",lavoro.getNome());
		assertEquals( new GregorianCalendar(2015, 2, 15),lavoro.getDataInizio());
		assertEquals(new GregorianCalendar(2015, 4, 1),lavoro.getDataFine());
	}

}
