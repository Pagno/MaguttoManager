package model.organizer.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.TreeSet;

import org.junit.Test;

// 
/**
 *   Class CamionTest.
 */
public class CamionTest {
	
	/**   c. */
	Camion c;
	
	/**
	 * Instantiates a new camion test.
	 */
	public CamionTest(){
		c=new Camion(5,"Yamaha","Prova",100,99,98);
	}

	/**
	 * Test camion.
	 */
	@Test
	public void testCamion() {
		assertEquals(c.getCodice(),5);
		assertEquals(c.getProduttore(),"Yamaha");
		assertEquals(c.getModello(),"Prova");
		assertEquals(c.getCapacitaMassima(),100);
		assertEquals(c.getPortataMassima(),99);
		assertEquals(c.getLunghezza(),98);
	}

	/**
	 * Test set capacita massima.
	 */
	@Test
	public void testSetCapacitaMassima() {
		c.setCapacitaMassima(200);
		assertEquals(c.getCapacitaMassima(),200);
	}

	/**
	 * Test set portata massima.
	 */
	@Test
	public void testSetPortataMassima() {
		c.setPortataMassima(200);
		assertEquals(c.getPortataMassima(),200);
	}

	/**
	 * Test set lunghezza.
	 */
	@Test
	public void testSetLunghezza() {
		c.setLunghezza(200);
		assertEquals(c.getLunghezza(),200);
	}

	/**
	 * Test set produttore.
	 */
	@Test
	public void testSetProduttore() {
		c.setProduttore("Caterpillar");
		assertEquals(c.getProduttore(),"Caterpillar");
	}

	/**
	 * Test set modello.
	 */
	@Test
	public void testSetModello() {
		c.setModello("Tir");
		assertEquals(c.getModello(),"Tir");
	}
	
	@Test
	public void testToString() {
		assertEquals(c.toString(),5 + " Yamaha Prova " + 100 + " " + 99 + " " + 98);
	}
	
	@Test
	public void testEquals() {
		assertTrue(c.equals(c));
		assertFalse(c.equals(new Ruspa(5,"Yamaha","Prova",100,99,98)));
		assertFalse(c.equals("Stringa"));
		assertFalse(c.equals(null));
		assertTrue(c.equals(new Camion(5,"Yamaha","Prova",100,99,98)));
		assertFalse(c.equals(new Camion(6,"Yamaha","Prova",100,99,98)));
		assertFalse(c.equals(new Camion(5,"Yamaha","Test",100,99,98)));
		assertFalse(c.equals(new Camion(5,"Caterpillar","Prova",100,99,98)));
		assertFalse(c.equals(new Camion(5,"Yamaha","Prova",101,99,98)));
		assertFalse(c.equals(new Camion(5,"Yamaha","Prova",100,100,98)));
		assertFalse(c.equals(new Camion(5,"Yamaha","Prova",100,99,99)));
	}
	
	@Test
	public void testAggiungiRichiesta(){
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r=new Richiesta(rc,lavoro);
		c.aggiungiRichiesta(r);
		Richiesta test=c.getRichiesta(1);
		assertEquals(r,test);
		test=c.getRichiesta(99);
		assertEquals(test,null);
	}
	
	@Test
	public void testRimuoviRichiesta(){
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r=new Richiesta(rc,lavoro);
		c.aggiungiRichiesta(r);
		Richiesta test=c.getRichiesta(1);
		assertEquals(r,test);
		c.rimuoviRichiesta(r);
		test=c.getRichiesta(1);
		assertEquals(test,null);
	}
	
	@Test
	public void testIsLibera(){
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 4, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r=new Richiesta(rc,lavoro);
		c.aggiungiRichiesta(r);
		assertTrue(c.isLibera(new GregorianCalendar(2012, 9, 01), new GregorianCalendar(2013, 9, 01)));
		assertFalse(c.isLibera(new GregorianCalendar(2012, 9, 01), new GregorianCalendar(2014, 9, 01)));
		assertFalse(c.isLibera(new GregorianCalendar(2014, 6, 01), new GregorianCalendar(2014, 9, 01)));
		assertFalse(c.isLibera(new GregorianCalendar(2014, 6, 01), new GregorianCalendar(2018, 9, 01)));
		assertTrue(c.isLibera(new GregorianCalendar(2017, 6, 01), new GregorianCalendar(2018, 9, 01)));
	}
	
	@Test
	public void testLiberaRichieste(){
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(90, 110, 90, 110, 90, 110);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r1=new Richiesta(rc,lavoro);
		c.aggiungiRichiesta(r1);
		Richiesta r2=new Richiesta(rc,lavoro);
		c.aggiungiRichiesta(r2);
		Richiesta r3=new Richiesta(rc,lavoro);
		c.aggiungiRichiesta(r3);
		Richiesta test=c.getRichiesta(1);
		test.setMacchina(c);
		assertEquals(test.getMacchina(),c);
		assertEquals(r1,test);
		test=c.getRichiesta(2);
		test.setMacchina(c);
		assertEquals(test.getMacchina(),c);
		assertEquals(r2,test);
		test=c.getRichiesta(3);
		test.setMacchina(c);
		assertEquals(test.getMacchina(),c);
		assertEquals(r3,test);
		c.liberaRichieste();
		test=c.getRichiesta(1);
		assertEquals(test,null);
		test=c.getRichiesta(2);
		assertEquals(test,null);
		test=c.getRichiesta(3);
		assertEquals(test,null);
		assertEquals(r1.getMacchina(),null);
		assertEquals(r2.getMacchina(),null);
		assertEquals(r3.getMacchina(),null);
	}

}
