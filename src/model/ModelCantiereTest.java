package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelCantiereTest.
 */
public class ModelCantiereTest {

	/** The mc. */
	ModelCantiere mc;
	
	/**
	 * Instantiates a new model cantiere test.
	 */
	public ModelCantiereTest(){
		ModelCantiere.resetForTest();
		mc=ModelCantiere.getModelCantiere();
		mc.caricaCantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22));
		mc.caricaCantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01));
		mc.caricaCantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05));
	}
	
	/**
	 * Test get model cantiere.
	 */
	@Test
	public void testGetModelCantiere() {
		ArrayList<Cantiere>lista=mc.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22))));
		assertTrue(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01))));
		assertTrue(lista.contains(new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05))));
		ModelCantiere prova=ModelCantiere.getModelCantiere();
		assertSame(mc,prova);
	}

	/**
	 * Test aggiungi cantiere.
	 */
	@Test
	public void testAggiungiCantiere() {
		assertEquals(mc.getNextCodice(),21);
		mc.aggiungiCantiere("MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22));
		assertEquals(mc.getNextCodice(),22);
		ArrayList<Cantiere>lista=mc.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Cantiere(21,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22))));
	}

	/**
	 * Test carica cantiere.
	 */
	@Test
	public void testCaricaCantiere() {
		assertEquals(mc.getNextCodice(),21);
		mc.caricaCantiere(25,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22));
		assertEquals(mc.getNextCodice(),26);
		ArrayList<Cantiere>lista=mc.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Cantiere(25,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22))));
	}

	/**
	 * Test modifica cantiere.
	 */
	@Test
	public void testModificaCantiere() {
		assertEquals(mc.getNextCodice(),21);
		mc.modificaCantiere(16,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22));
		assertEquals(mc.getNextCodice(),21);
		ArrayList<Cantiere>lista=mc.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Cantiere(16,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22))));
	}

	/**
	 * Test rimuovi cantiere string.
	 */
	@Test
	public void testRimuoviCantiereString() {
		assertTrue(mc.rimuoviCantiere("Pedemontana"));
		ArrayList<Cantiere>lista=mc.getLista();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01))));
	}

	/**
	 * Test rimuovi cantiere int.
	 */
	@Test
	public void testRimuoviCantiereInt() {
		assertTrue(mc.rimuoviCantiere(16));
		ArrayList<Cantiere>lista=mc.getLista();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01))));
	}

	/**
	 * Test get cantiere.
	 */
	@Test
	public void testGetCantiere() {
		assertEquals(mc.getCantiere(16),new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)));
		assertEquals(mc.getCantiere(7),new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)));
		assertEquals(mc.getCantiere(20),new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05)));
		assertEquals(mc.getCantiere(1),null);
	}

}