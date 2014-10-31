package controller.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import model.organizer.data.Cantiere;
import model.organizer.data.Gru;
import model.organizer.data.Lavoro;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaRuspa;
import model.organizer.data.Ruspa;

import org.junit.Test;

import controller.greedy.data.Associazione;

public class AssociazioneTest {
	Associazione a;
	Richiesta richiesta;
	Macchina macchina;
	
	public AssociazioneTest() {
		Cantiere cantiere=new Cantiere(1, "Bottanuco", "via Chiusa,18",new GregorianCalendar(2014, 9, 30),
				new GregorianCalendar(2015, 10, 1), Priorita.ALTA);
		Lavoro lavoro=new Lavoro(1, "Scavi", cantiere, new GregorianCalendar(2014, 9, 30), new GregorianCalendar(2014, 11, 30)); 
		cantiere.aggiungiLavoro(lavoro);
		
		richiesta=new Richiesta(new RichiestaRuspa( -1, -1, 300, 400, -1, -1), lavoro);
		macchina=new Ruspa(101, "New Holland", "R101", 1200, 350, 3);
		a=new Associazione(richiesta, macchina);
	}

	@Test
	public void testAssociazione(){
		
		Cantiere cantiere=new Cantiere(1, "Bottanuco", "via Chiusa,18",new GregorianCalendar(2014, 9, 30),
				new GregorianCalendar(2015, 10, 1), Priorita.ALTA);
		Lavoro lavoro=new Lavoro(1, "Scavi", cantiere, new GregorianCalendar(2014, 9, 30), new GregorianCalendar(2014, 11, 30)); 
		cantiere.aggiungiLavoro(lavoro);
		
		assertEquals(a.getRichiesta(),richiesta);
		assertEquals(a.getMacchina(),macchina);
		assertFalse(a.getConfermata());
		a.setConfermata(true);
		assertTrue(a.getConfermata());
		Richiesta r2=new Richiesta(new RichiestaRuspa( 5, 10, 5, 10, 5, 10), lavoro);
		Ruspa m2=new Ruspa(105, "Yamaha", "Ruspa", 8, 8, 8);
		a=new Associazione(r2, m2);
		assertEquals(a.getRichiesta(),r2);
		assertEquals(a.getMacchina(),m2);
		assertFalse(a.getConfermata());
	}
	
	@Test
	public void testGetRichiesta() {
		assertEquals(a.getRichiesta(),richiesta);
		Cantiere cantiere=new Cantiere(1, "Bottanuco", "via Chiusa,18",new GregorianCalendar(2014, 9, 30),
				new GregorianCalendar(2015, 10, 1), Priorita.ALTA);
		Lavoro lavoro=new Lavoro(1, "Scavi", cantiere, new GregorianCalendar(2014, 9, 30), new GregorianCalendar(2014, 11, 30)); 
		Richiesta richiesta2=new Richiesta(new RichiestaRuspa(- -1, -1, 300, 400, -1, -1), lavoro);
		assertNotEquals(richiesta2, a.getRichiesta());
	}

	@Test
	public void testSetRichiesta() {
		Cantiere cantiere=new Cantiere(1, "Bottanuco", "via Chiusa,18",new GregorianCalendar(2014, 9, 30),
				new GregorianCalendar(2015, 10, 1), Priorita.ALTA);
		Lavoro lavoro=new Lavoro(1, "Scavi", cantiere, new GregorianCalendar(2014, 9, 30), new GregorianCalendar(2014, 11, 30)); 
		Richiesta richiesta2=new Richiesta(new RichiestaGru(-1, -1, -1, -1, 300, 400, -1, -1), lavoro);
		a.setRichiesta(richiesta2);
		assertNotEquals(richiesta, a.getRichiesta());
		assertEquals(richiesta2, a.getRichiesta());
	}

	@Test
	public void testGetMacchina() {
		Ruspa ruspa=new Ruspa(103, "Caterpillar", "RU103",3000, 600, 4);
		assertNotEquals(a.getRichiesta(),ruspa);
		Gru gru=new Gru(12, "New Holland", "G12a", 360, 1200, 35, 40);
		assertNotEquals(a.getRichiesta(),gru);
		
		assertEquals(a.getMacchina(),macchina);
	}

	@Test
	public void testSetMacchina() {
		Ruspa ruspa=new Ruspa(103, "Caterpillar", "RU103",3000, 600, 4);
		a.setMacchina(ruspa);
		assertNotEquals(a.getRichiesta(),macchina);
		Gru gru=new Gru(12, "New Holland", "G12a", 360, 1200, 35, 40);
		assertNotEquals(a.getRichiesta(),gru);
		assertEquals(a.getMacchina(),ruspa);
	}

	@Test
	public void testSetConfermata() {
		a.setConfermata(true);
		assertTrue(a.getConfermata());
		a.setConfermata(false);
		assertFalse(a.getConfermata());
		
	}

	@Test
	public void testGetConfermata() {
		a.setConfermata(true);
		assertTrue(a.getConfermata());
		a.setConfermata(false);
		assertFalse(a.getConfermata());
	}

	@Test
	public void testConcretizza() {
		assertFalse(richiesta.isSoddisfatta());
		a.concretizza();
		assertTrue(richiesta.isSoddisfatta());
		assertEquals(a.getRichiesta().getMacchina(), macchina);
		Cantiere cantiere=new Cantiere(1, "Bottanuco", "via Chiusa,18",new GregorianCalendar(2014, 9, 30),
				new GregorianCalendar(2015, 10, 1), Priorita.ALTA);
		Lavoro lavoro=new Lavoro(1, "Scavi", cantiere, new GregorianCalendar(2014, 9, 30), new GregorianCalendar(2014, 11, 30)); 
		cantiere.aggiungiLavoro(lavoro);
		Richiesta r2=new Richiesta(new RichiestaRuspa( 5, 10, 5, 10, 5, 10), lavoro);
		Ruspa m2=new Ruspa(105, "Yamaha", "Ruspa", 40, 40, 40);
		Associazione b=new Associazione(r2, m2);
		b.concretizza();
		assertFalse(r2.isSoddisfatta());
		assertEquals(b.getRichiesta().getMacchina(), null);
	}

	@Test
	public void testToString() {
		assertEquals(a.toString(), "Cantiere:Bottanuco Lavoro:Scavi -->  Macchina: New Holland - R101");
	}

	@Test
	public void testGetDataInizio() {
		assertEquals(a.getDataInizio(), new GregorianCalendar(2014, 9, 30));
		assertNotEquals(a.getDataInizio(), new GregorianCalendar(2014, 9, 29));
		assertNotEquals(a.getDataInizio(), new GregorianCalendar(2014, 9, 31));
	}

	@Test
	public void testGetDataFine() {
		assertEquals(a.getDataFine(), new GregorianCalendar(2014, 11, 30));
		assertNotEquals(a.getDataFine(), new GregorianCalendar(2014, 11, 29));
		assertNotEquals(a.getDataFine(), new GregorianCalendar(2014,11, 31));
	}
	@Test
	public void testCollide() {
		Cantiere cantiere=new Cantiere(1, "Bottanuco", "via Chiusa,18",new GregorianCalendar(2014, 9, 30),
				new GregorianCalendar(2015, 10, 1), Priorita.ALTA);
		Lavoro lavoro=new Lavoro(1, "Scavi", cantiere, new GregorianCalendar(2014, 9, 30), new GregorianCalendar(2014, 11, 30)); 
			Richiesta richiesta2=new Richiesta(new RichiestaGru(-1, -1, -1, -1, 300, 400, -1, -1), lavoro);
		assertTrue(a.collide(richiesta2));
		
		/*Gru g=new Gru(34, "Gru", "G", 360, 350, 45, 50);
		Associazione a2=new Associazione(richiesta2, g);
		assertTrue(a.collide(a2));*/
	}
	
	@Test
	public void testEquals() {
		assertTrue(a.equals(a));
		assertFalse(a.equals(null));
		assertFalse(a.equals(macchina));
		Cantiere cantiere=new Cantiere(1, "Bottanuco", "via Chiusa,18",new GregorianCalendar(2014, 9, 30),
				new GregorianCalendar(2015, 10, 1), Priorita.ALTA);
		Lavoro lavoro=new Lavoro(1, "Scavi", cantiere, new GregorianCalendar(2014, 9, 30), new GregorianCalendar(2014, 11, 30)); 
			Richiesta richiesta2=new Richiesta(new RichiestaGru(-1, -1, -1, -1, 300, 400, -1, -1), lavoro);
		assertTrue(a.collide(richiesta2));
		
		Gru g=new Gru(34, "Gru", "G", 360, 350, 45, 50);
		Associazione a2=new Associazione(richiesta2, macchina);
		a.setConfermata(true);
		assertFalse(a.equals(a2));
		a2.setConfermata(true);
		a.setMacchina(null);
		assertFalse(a.equals(a2));
		a.setMacchina(macchina);
		a2.setMacchina(null);
		assertFalse(a.equals(a2));
		a2.setMacchina(macchina);
		
		a.setRichiesta(null);
		a2.setRichiesta(richiesta2);
		assertFalse(a.equals(a2));
		a2.setRichiesta(richiesta2);
		assertFalse(a.equals(a2));
		a.setRichiesta(richiesta);
		assertFalse(a.equals(a2));

		a2.setRichiesta(richiesta);
		assertTrue(a.equals(a2));
		
	}
}
