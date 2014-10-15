package model.organizer.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

// 
/**
 *   Class EscavatoreTest.
 */
public class EscavatoreTest {
 
	/**   e. */
	Escavatore e;
	
	/**
	 * Instantiates a new escavatore test.
	 */
	public EscavatoreTest(){
		e=new Escavatore(5,"Volvo","Prova",100,99,98,97);
	}
	
	/**
	 * Test escavatore.
	 */
	@Test
	public void testEscavatore() {
		assertEquals(e.getCapacitaMassima(),100);
		assertEquals(e.getPortataMassima(),99);
		assertEquals(e.getProfonditaMassima(),97);
		assertEquals(e.getAltezzaMassima(),98);
		assertEquals(e.getProduttore(),"Volvo");
		assertEquals(e.getModello(),"Prova");
	}

	/**
	 * Test set capacita massima.
	 */
	@Test
	public void testSetCapacitaMassima() {
		e.setCapacitaMassima(200);
		assertEquals(e.getCapacitaMassima(),200);
	}

	/**
	 * Test set portata massima.
	 */
	@Test
	public void testSetPortataMassima() {
		e.setPortataMassima(200);
		assertEquals(e.getPortataMassima(),200);
	}

	/**
	 * Test set profondita massima.
	 */
	@Test
	public void testSetProfonditaMassima() {
		e.setProfonditaMassima(200);
		assertEquals(e.getProfonditaMassima(),200);
	}

	/**
	 * Test set altezza massima.
	 */
	@Test
	public void testSetAltezzaMassima() {
		e.setAltezzaMassima(200);
		assertEquals(e.getAltezzaMassima(),200);
	}

	/**
	 * Test set produttore.
	 */
	@Test
	public void testSetProduttore() {
		e.setProduttore("Caterpillar");
		assertEquals(e.getProduttore(),"Caterpillar");
	}

	/**
	 * Test set modello.
	 */
	@Test
	public void testSetModello() {
		e.setModello("ModProva");
		assertEquals(e.getModello(),"ModProva");
	}
	
	@Test
	public void testToString() {
		assertEquals(e.toString(),5 + " Volvo Prova " + 100 + " " + 99 + " " + 98 + " " + 97);
	}
	
	@Test
	public void testEquals() {
		assertTrue(e.equals(e));
		assertTrue(e.equals(new Escavatore(5,"Volvo","Prova",100,99,98,97)));
		assertFalse(e.equals(new Escavatore(6,"Volvo","Prova",100,99,98,97)));
		assertFalse(e.equals(new Escavatore(5,"Caterpillar","Prova",100,99,98,97)));
		assertFalse(e.equals(new Escavatore(5,"Volvo","Esc",100,99,98,97)));
		assertFalse(e.equals(new Escavatore(5,"Volvo","Prova",101,99,98,97)));
		assertFalse(e.equals(new Escavatore(5,"Volvo","Prova",100,100,98,97)));
		assertFalse(e.equals(new Escavatore(5,"Volvo","Prova",100,99,99,97)));
		assertFalse(e.equals(new Escavatore(5,"Volvo","Prova",100,99,98,98)));
		assertFalse(e.equals(new Ruspa(5,"Volvo","Prova",100,99,98)));
		assertFalse(e.equals("Stringa"));
		assertFalse(e.equals(null));
	}

	@Test
	public void testAggiungiRichiesta(){
		Richiesta.initCodice();
		RichiestaEscavatore re=new RichiestaEscavatore(5, 10, 5, 10, 5, 10,5,10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r=new Richiesta(re,lavoro);
		e.aggiungiRichiesta(r);
		Richiesta test=e.getRichiesta(1);
		assertEquals(r,test);
		test=e.getRichiesta(99);
		assertEquals(test,null);
	}
	
	@Test
	public void testRimuoviRichiesta(){
		Richiesta.initCodice();
		RichiestaEscavatore re=new RichiestaEscavatore(5, 10, 5, 10, 5, 10,5,10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r=new Richiesta(re,lavoro);
		e.aggiungiRichiesta(r);
		Richiesta test=e.getRichiesta(1);
		assertEquals(r,test);
		e.rimuoviRichiesta(r);
		test=e.getRichiesta(1);
		assertEquals(test,null);
	}
	
	@Test
	public void testIsLibera(){
		Richiesta.initCodice();
		RichiestaEscavatore re=new RichiestaEscavatore(5, 10, 5, 10, 5, 10,5,10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 4, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r=new Richiesta(re,lavoro);
		e.aggiungiRichiesta(r);
		assertTrue(e.isLibera(new GregorianCalendar(2012, 9, 01), new GregorianCalendar(2013, 9, 01)));
		assertFalse(e.isLibera(new GregorianCalendar(2012, 9, 01), new GregorianCalendar(2014, 9, 01)));
		assertFalse(e.isLibera(new GregorianCalendar(2014, 6, 01), new GregorianCalendar(2014, 9, 01)));
		assertFalse(e.isLibera(new GregorianCalendar(2014, 6, 01), new GregorianCalendar(2018, 9, 01)));
		assertTrue(e.isLibera(new GregorianCalendar(2017, 6, 01), new GregorianCalendar(2018, 9, 01)));
	}
	
	@Test
	public void testLiberaRichieste(){
		Richiesta.initCodice();
		RichiestaEscavatore re=new RichiestaEscavatore(90, 110, 90, 110, 90, 110,90,110);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r1=new Richiesta(re,lavoro);
		e.aggiungiRichiesta(r1);
		Richiesta r2=new Richiesta(re,lavoro);
		e.aggiungiRichiesta(r2);
		Richiesta r3=new Richiesta(re,lavoro);
		e.aggiungiRichiesta(r3);
		Richiesta test=e.getRichiesta(1);
		test.setMacchina(e);
		assertEquals(test.getMacchina(),e);
		assertEquals(r1,test);
		test=e.getRichiesta(2);
		test.setMacchina(e);
		assertEquals(test.getMacchina(),e);
		assertEquals(r2,test);
		test=e.getRichiesta(3);
		test.setMacchina(e);
		assertEquals(test.getMacchina(),e);
		assertEquals(r3,test);
		e.liberaRichieste();
		test=e.getRichiesta(1);
		assertEquals(test,null);
		test=e.getRichiesta(2);
		assertEquals(test,null);
		test=e.getRichiesta(3);
		assertEquals(test,null);
		assertEquals(r1.getMacchina(),null);
		assertEquals(r2.getMacchina(),null);
		assertEquals(r3.getMacchina(),null);
	}
}
