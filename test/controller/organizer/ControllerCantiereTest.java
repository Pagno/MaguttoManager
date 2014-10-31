package controller.organizer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.ModelConnector;
import model.organizer.ModelCantiere;
import model.organizer.ModelGru;
import model.organizer.ModelRuspa;
import model.organizer.data.Cantiere;
import model.organizer.data.Gru;
import model.organizer.data.Lavoro;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaRuspa;
import model.organizer.data.Ruspa;

import org.fest.util.Arrays;
import org.junit.Test;

import controller.greedy.data.Associazione;
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
		ModelCantiere.getModelCantiere().aggiungiRichiesta(21, 45,richiestaGru);
		
		ArrayList<Macchina> arr=canCtrl.getElencoMacchineDisponibili(1);
		ArrayList<Macchina> arr2=new ArrayList<Macchina>(java.util.Arrays.asList(g));
		assertEquals(arr, arr2);
		canCtrl.soddisfaRichiesta(1,17);
		arr=canCtrl.getElencoMacchineDisponibili(1);
		assertNotEquals(arr, arr2);
	}
	
	@Test
	public void testModificaCantiere() {
		model.ResetAllForTest();

		ModelCantiere.getModelCantiere().caricaCantiere(12,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		canCtrl.modificaCantiere(12,"", "via Vittorio Alfieri", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2016, 12, 22), Priorita.BASSA);
		canCtrl.modificaCantiere(12,"Bottanuco", "", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2016, 12, 22), Priorita.BASSA);
		
		canCtrl.modificaCantiere(12,"Chignolo", "via Vittorio Alfieri", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2013, 12, 22), Priorita.BASSA);
		
		canCtrl.modificaCantiere(12,"Chignolo", "via Vittorio Alfieri", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2016, 12, 22), Priorita.BASSA);
		canCtrl.aggiungiLavoro(12, "Scavi", new GregorianCalendar(2015, 1, 1), new GregorianCalendar(2015, 12, 22));
		canCtrl.modificaCantiere(12,"Chignolo", "via Vittorio Alfieri", new GregorianCalendar(2015, 1, 1),new GregorianCalendar(2017, 12, 22), Priorita.BASSA);
		canCtrl.modificaCantiere(12,"Chignolo", "via Vittorio Alfieri", new GregorianCalendar(2014, 0, 1),new GregorianCalendar(2015, 3, 22), Priorita.BASSA);
		canCtrl.modificaCantiere(12,"Chignolo", "via Vittorio Alfieri", new GregorianCalendar(2015, 2, 1),new GregorianCalendar(2015, 3, 22), Priorita.BASSA);
		
		Cantiere m=canCtrl.getCantiere(12);

		System.out.println(m.getNomeCantiere());
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
		canCtrl.aggiungiLavoro(21, "", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		canCtrl.aggiungiLavoro(21, "Scavi", new GregorianCalendar(2013, 0, 1),new GregorianCalendar(2015, 1, 28));
		canCtrl.aggiungiLavoro(21, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2018, 1, 28));
		
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
		ModelCantiere.getModelCantiere().aggiungiRichiesta(21, 45, richiestaGru);
		
		assertFalse(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(1).isSoddisfatta());
		
		canCtrl.soddisfaRichiesta(1, 17);
		
		assertTrue(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(1).isSoddisfatta());
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
		ModelCantiere.getModelCantiere().aggiungiRichiesta(21, 45,richiestaGru);
		
		assertTrue(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).hasRichiesta(1));
		
		canCtrl.eliminaRichiesta(1);
		
		assertFalse(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).hasRichiesta(3));
		
	}

	@Test
	public void testLiberaRichiesta() {
		model.ResetAllForTest();
		ModelGru.getModelGru().caricaGru(17, "Gru", "G405", 360, 3000, 30, 21);
		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(21, 45, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		RichiestaGru richiestaGru=new RichiestaGru(25, 35, -1, -1, -1, -1, -1, -1);
		ModelCantiere.getModelCantiere().aggiungiRichiesta(21, 45, richiestaGru);
		
		assertFalse(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(1).isSoddisfatta());
		
		canCtrl.soddisfaRichiesta(1, 17);
		
		assertTrue(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(1).isSoddisfatta());
		canCtrl.liberaRichiesta(1);
		assertFalse(ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(45).getRichiesta(1).isSoddisfatta());
		
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
		ModelRuspa.getModelRuspa().caricaRuspa(7, "Ruspa", "R", 100, 100, 100);
		ModelCantiere.getModelCantiere().caricaCantiere(22,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(22,16, "Scavi", new GregorianCalendar(2015, 2, 1),new GregorianCalendar(2015, 2, 26));
		RichiestaRuspa rRuspa=new RichiestaRuspa(70, 110, -1, -1, -1, -1) ;
		ModelCantiere.getModelCantiere().aggiungiRichiesta(22, 16, rRuspa);
		ModelCantiere.getModelCantiere().soddisfaRichiesta(1, ModelRuspa.getModelRuspa().getRuspa(7));
		
		
		ModelCantiere.getModelCantiere().caricaCantiere(21,"Bottanuco", "via Chiusa", new GregorianCalendar(2014, 11, 1),new GregorianCalendar(2015, 12, 22), Priorita.ALTA);
		ModelCantiere.getModelCantiere().caricaLavoro(21,15, "Scavi", new GregorianCalendar(2015, 0, 1),new GregorianCalendar(2015, 1, 28));
		RichiestaRuspa rRuspa2=new RichiestaRuspa(70, 110, -1, -1, -1, -1) ;
		canCtrl.modificaLavoro(21,15, "Pilastri",new GregorianCalendar(2014, 0, 3), new GregorianCalendar(2015, 1, 25));
		canCtrl.modificaLavoro(21,15, "Pilastri",new GregorianCalendar(2015, 0, 3), new GregorianCalendar(2016, 3, 25));
		
		canCtrl.modificaLavoro(21,15, "Pilastri",new GregorianCalendar(2015, 0, 3), new GregorianCalendar(2015, 1, 25));
		
		
		ModelCantiere.getModelCantiere().aggiungiRichiesta(21, 15, rRuspa2);
		ModelCantiere.getModelCantiere().soddisfaRichiesta(2, ModelRuspa.getModelRuspa().getRuspa(7));
		
		
		
		//SE CLICCO NO PER ELIMINARE LA RICHIESTA la richiesta non deve essere liberata ed il lavoro non deve essere modificato
		canCtrl.modificaLavoro(21,15, "Fondamenta", new GregorianCalendar(2015, 2, 15), new GregorianCalendar(2015, 4, 1));
		Lavoro lavoro=ModelCantiere.getModelCantiere().getCantiere(21).getLavoro(15);
				
		assertEquals(21,lavoro.getCantiere().getCodice());
		assertEquals("Pilastri",lavoro.getNome());
		assertEquals( new GregorianCalendar(2015, 0, 3),lavoro.getDataInizio());
		assertEquals(new GregorianCalendar(2015, 1, 25),lavoro.getDataFine());
		
		assertTrue(ModelCantiere.getModelCantiere().getRichiesta(2).isSoddisfatta());
		
		//SE CLICCO YES PER ELIMINARE LA RICHIESTA la richiesta deve essere liberata
		canCtrl.modificaLavoro(21,15, "Fondamenta", new GregorianCalendar(2015, 2, 15), new GregorianCalendar(2015, 4, 1));
			
		assertEquals(21,lavoro.getCantiere().getCodice());
		assertEquals("Fondamenta",lavoro.getNome());
		assertEquals( new GregorianCalendar(2015, 2, 15),lavoro.getDataInizio());
		assertEquals(new GregorianCalendar(2015, 4, 1),lavoro.getDataFine());
		assertFalse(ModelCantiere.getModelCantiere().getRichiesta(2).isSoddisfatta());
		

	}

}
