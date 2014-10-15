package controller.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
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
				new GregorianCalendar(2015, 10, 1), Priorita.ALTA);
		Lavoro lavoro=new Lavoro(1, "Scavi", cantiere, new GregorianCalendar(2014, 9, 30), new GregorianCalendar(2014, 11, 30)); 
		cantiere.aggiungiLavoro(lavoro);
		
		richiesta=new Richiesta(new RichiestaRuspa( -1, -1, 300, 400, -1, -1), lavoro);
		macchina=new Ruspa(101, "New Holland", "R101", 1200, 350, 3);
		a=new Associazione(richiesta, macchina);
		prenotazione=new Prenotazione(a, 12);
	}


	@Test
	public void testPrenotazione(){
		assertEquals(prenotazione.getAssociazione(),a);
		assertEquals(prenotazione.getDurataLavoro(),12);
		
		Cantiere cantiere=new Cantiere(1, "Bottanuco", "via Chiusa,18",new GregorianCalendar(2014, 9, 30),
				new GregorianCalendar(2015, 10, 1), Priorita.ALTA);
		Lavoro lavoro=new Lavoro(1, "Scavi", cantiere, new GregorianCalendar(2014, 9, 30), new GregorianCalendar(2014, 11, 30)); 
		cantiere.aggiungiLavoro(lavoro);
		
		Richiesta r1=new Richiesta(new RichiestaRuspa( 5, 10, 5, 10, 5, 10), lavoro);
		Ruspa m1=new Ruspa(150, "New Holland", "R101", 8, 8, 8);
		Associazione a2=new Associazione(r1, m1);
		prenotazione=new Prenotazione(a2,87);
		assertEquals(prenotazione.getAssociazione(),a2);
		assertEquals(prenotazione.getDurataLavoro(),87);
	}
	
	@Test
	public void testToString(){
		assertEquals(prenotazione.toString(),"Prenotazione [associazione=" + a + ", durataLavoro=12]");
	
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
		assertEquals(prenotazione.getDurataLavoro(), 12);
		assertNotEquals(prenotazione.getDurataLavoro(), 11);
		assertNotEquals(prenotazione.getDurataLavoro(), 13);
	}

	@Test
	public void testSetDurataLavoro() {
		prenotazione.setDurataLavoro(15);
		assertEquals(prenotazione.getDurataLavoro(), 15);
		assertNotEquals(prenotazione.getDurataLavoro(), 14);
		assertNotEquals(prenotazione.getDurataLavoro(), 16);
		assertNotEquals(prenotazione.getDurataLavoro(), 12);
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
