package controller.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Macchina;
import model.organizer.data.Priority;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaRuspa;
import model.organizer.data.Ruspa;

import org.junit.Test;

public class PrenotazioneTest {
	Prenotazione prenotazione;
	Associazione a;
	Richiesta richiesta;
	Macchina macchina;
	
	public PrenotazioneTest(){
		Cantiere cantiere=new Cantiere(1, "Bottanuco", "via Chiusa,18",new GregorianCalendar(2014, 9, 30),
				new GregorianCalendar(2015, 10, 1), Priority.ALTA);
		Lavoro lavoro=new Lavoro(1, "Scavi", cantiere, new GregorianCalendar(2014, 9, 30), new GregorianCalendar(2014, 11, 30)); 
		cantiere.addLavoro(lavoro);
		
		richiesta=new Richiesta(new RichiestaRuspa( -1, -1, 300, 400, -1, -1), lavoro);
		macchina=new Ruspa(101, "New Holland", "R101", 1200, 350, 3);
		a=new Associazione(richiesta, macchina);
		prenotazione=new Prenotazione(a, 12);
	}


	@Test
	public void testGetAssociazione() {
		assertEquals(prenotazione.getAssociazione(), a);
	}

	@Test
	public void testSetAssociazione() {
		Macchina macchina2=new Ruspa(102, "New Holland", "R102", 2400, 550, 4);
		Associazione ass=new Associazione(richiesta, macchina2);
		prenotazione.setAssociazione(ass);
		assertNotEquals(prenotazione.getAssociazione(), a);
		assertEquals(prenotazione.getAssociazione(), ass);
	}

	@Test
	public void testGetDurataLavoro() {
		assertEquals(prenotazione.getDurataLavoro(), new Integer(12));
		assertNotEquals(prenotazione.getDurataLavoro(), new Integer(11));
		assertNotEquals(prenotazione.getDurataLavoro(), new Integer(13));
	}

	@Test
	public void testSetDurataLavoro() {
		prenotazione.setDurataLavoro(15);
		assertEquals(prenotazione.getDurataLavoro(), new Integer(15));
		assertNotEquals(prenotazione.getDurataLavoro(), new Integer(14));
		assertNotEquals(prenotazione.getDurataLavoro(), new Integer(16));
		assertNotEquals(prenotazione.getDurataLavoro(), new Integer(12));
	}

	@Test
	public void testSelect() {
		assertFalse(prenotazione.getAssociazione().getConfermata());
		assertTrue(prenotazione.select().getConfermata());
	}

	@Test
	public void testGetRichiesta() {
		assertEquals(prenotazione.getRichiesta(), richiesta);
	}

	@Test
	public void testGetMacchina() {
		assertEquals(prenotazione.getMacchina(), macchina);
	}

	@Test
	public void testGetDataInizio() {
		assertEquals(prenotazione.getDataInizio(),  new GregorianCalendar(2014, 9, 30));
	}

	@Test
	public void testGetDataFine() {
		assertEquals(prenotazione.getDataFine(), new GregorianCalendar(2014, 11, 30));
	}

}
