package model.organizer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;



import model.organizer.data.Cantiere;
import model.organizer.data.Priority;

import org.junit.Test;

// 
/**
 *   Class ModelCantiereTest.
 */
public class ModelCantiereTest {

	/**   mc. */
	ModelCantiere mc;
	
	/**
	 * Instantiates a new model cantiere test.
	 */
	public ModelCantiereTest(){
		ModelCantiere.resetForTest();
		mc=ModelCantiere.getModelCantiere();
		mc.caricaCantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		mc.caricaCantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA);
		mc.caricaCantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priority.BASSA);
	}
	
	/**
	 * Test get model cantiere.
	 */
	@Test
	public void testGetModelCantiere() {
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA)));
		assertTrue(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA)));
		assertTrue(lista.contains(new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priority.BASSA)));
		assertFalse(lista.contains(new Cantiere(21,"Asfalto Viale","Dalmine",new GregorianCalendar(2015,06,06),new GregorianCalendar(2018,04,04),Priority.MEDIA)));
		ModelCantiere prova=ModelCantiere.getModelCantiere();
		assertSame(mc,prova);
		ModelCantiere.resetForTest();
		mc=ModelCantiere.getModelCantiere();
		assertEquals(mc.getNextCodice(),1);
	}

	/**
	 * Test aggiungi cantiere.
	 */
	@Test
	public void testAggiungiCantiere() {
		assertEquals(mc.getNextCodice(),21);
		mc.aggiungiCantiere("MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.BASSA);
		assertEquals(mc.getNextCodice(),22);
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Cantiere(21,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.BASSA)));
		assertFalse(lista.contains(new Cantiere(21,"Asfalto Viale","Dalmine",new GregorianCalendar(2015,06,06),new GregorianCalendar(2018,04,04),Priority.MEDIA)));
	}

	/**
	 * Test carica cantiere.
	 */
	@Test
	public void testCaricaCantiere() {
		mc.caricaCantiere(1,"Passerella sul Brembo","Dalmine",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.MEDIA);
		assertEquals(mc.getNextCodice(),21);
		mc.caricaCantiere(25,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		assertEquals(mc.getNextCodice(),26);
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),5);
		assertTrue(lista.contains(new Cantiere(25,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA)));
		assertFalse(lista.contains(new Cantiere(21,"Asfalto Viale","Dalmine",new GregorianCalendar(2015,06,06),new GregorianCalendar(2018,04,04),Priority.MEDIA)));
	}

	/**
	 * Test modifica cantiere.
	 */
	@Test
	public void testModificaCantiere() {
		assertEquals(mc.getNextCodice(),21);
		mc.modificaCantiere(16,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		assertEquals(mc.getNextCodice(),21);
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Cantiere(16,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA)));
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA)));
	}

	/**
	 * Test rimuovi cantiere string.
	 */
	@Test
	public void testRimuoviCantiereString() {
		assertTrue(mc.rimuoviCantiere("Pedemontana"));
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA)));
		assertFalse(mc.rimuoviCantiere("Aeroporto Orio al Serio"));
	}

	/**
	 * Test rimuovi cantiere int.
	 */
	@Test
	public void testRimuoviCantiereInt() {
		assertTrue(mc.rimuoviCantiere(16));
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA)));
		assertFalse(mc.rimuoviCantiere(99));
	}

	/**
	 * Test get cantiere.
	 */
	@Test
	public void testGetCantiere() {
		assertEquals(mc.getCantiere(16),new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA));
		assertEquals(mc.getCantiere(7),new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA));
		assertEquals(mc.getCantiere(20),new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priority.BASSA));
		assertEquals(mc.getCantiere(1),null);
	}
	
	@Test
	public void testToString() {
		Cantiere a=new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		Cantiere b=new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA);
		Cantiere c=new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priority.BASSA);
		String str=a.toString() + "\n" + b.toString() + "\n" + c.toString() + "\n";
		assertEquals(mc.toString(),str);
	}

}
