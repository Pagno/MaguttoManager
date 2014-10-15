package model.organizer.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

// 
/**
 *   Class GruTest.
 */
public class GruTest {
	
	/**   g. */
	Gru g;
	
	/**
	 * Instantiates a new gru test.
	 */
	public GruTest(){
		g=new Gru(5,"Volvo","Grattacielo",360,100,99,98);
	}
	
	/**
	 * Test gru.
	 */
	@Test
	public void testGru() {
		assertEquals(g.getAngoloRotazione(),360);
		assertEquals(g.getPortataMassima(),100);
		assertEquals(g.getLunghezza(),99);
		assertEquals(g.getAltezza(),98);
		assertEquals(g.getProduttore(),"Volvo");
		assertEquals(g.getModello(),"Grattacielo");
	}

	/**
	 * Test set lunghezza massima.
	 */
	@Test
	public void testSetLunghezzaMassima() {
		g.setLunghezzaMassima(200);
		assertEquals(g.getLunghezza(),200);
	}

	/**
	 * Test set portata massima.
	 */
	@Test
	public void testSetPortataMassima() {
		g.setPortataMassima(200);
		assertEquals(g.getPortataMassima(),200);
	}

	/**
	 * Test set altezza.
	 */
	@Test
	public void testSetAltezza() {
		g.setAltezza(200);
		assertEquals(g.getAltezza(),200);
	}

	/**
	 * Test set angolo rotazione.
	 */
	@Test
	public void testSetAngoloRotazione() {
		g.setAngoloRotazione(270);
		assertEquals(g.getAngoloRotazione(),270);
	}

	/**
	 * Test set produttore.
	 */
	@Test
	public void testSetProduttore() {
		g.setProduttore("Caterpillar");
		assertEquals(g.getProduttore(),"Caterpillar");
	}

	/**
	 * Test set modello.
	 */
	@Test
	public void testSetModello() {
		g.setModello("Villetta");
		assertEquals(g.getModello(),"Villetta");
	}
	
	@Test
	public void testToString() {
		assertEquals(g.toString(),5 + " Volvo Grattacielo " + 99 + " " + 100 + " " + 98 + " " + 360);
	}

	@Test
	public void testEquals(){
		assertTrue(g.equals(g));
		assertTrue(g.equals(new Gru(5,"Volvo","Grattacielo",360,100,99,98)));
		assertFalse(g.equals(new Gru(6,"Volvo","Grattacielo",360,100,99,98)));
		assertFalse(g.equals(new Gru(5,"Caterpillar","Grattacielo",360,100,99,98)));
		assertFalse(g.equals(new Gru(5,"Volvo","Piano Terra",360,100,99,98)));
		assertFalse(g.equals(new Gru(5,"Volvo","Grattacielo",180,100,99,98)));
		assertFalse(g.equals(new Gru(5,"Volvo","Grattacielo",360,101,99,98)));
		assertFalse(g.equals(new Gru(5,"Volvo","Grattacielo",360,100,100,98)));
		assertFalse(g.equals(new Gru(5,"Volvo","Grattacielo",360,100,99,99)));
		assertFalse(g.equals(new Ruspa(5,"Volvo","Grattacielo",100,99,98)));
		assertFalse(g.equals("Stringa"));
		assertFalse(g.equals(null));
	}
	


	@Test
	public void testAggiungiRichiesta(){
		Richiesta.initCodice();
		RichiestaGru rg=new RichiestaGru(5, 10, 5, 10, 5, 10,5,10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta ric=new Richiesta(rg,lavoro);
		g.aggiungiRichiesta(ric);
		Richiesta test=g.getRichiesta(1);
		assertEquals(ric,test);
		test=g.getRichiesta(99);
		assertEquals(test,null);
	}
	
	@Test
	public void testRimuoviRichiesta(){
		Richiesta.initCodice();
		RichiestaGru rg=new RichiestaGru(5, 10, 5, 10, 5, 10,5,10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta ric=new Richiesta(rg,lavoro);
		g.aggiungiRichiesta(ric);
		Richiesta test=g.getRichiesta(1);
		assertEquals(ric,test);
		g.rimuoviRichiesta(ric);
		test=g.getRichiesta(1);
		assertEquals(test,null);
	}
	
	@Test
	public void testIsLibera(){
		Richiesta.initCodice();
		RichiestaGru rg=new RichiestaGru(5, 10, 5, 10, 5, 10,5,10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 4, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta ric=new Richiesta(rg,lavoro);
		g.aggiungiRichiesta(ric);
		assertTrue(g.isLibera(new GregorianCalendar(2012, 9, 01), new GregorianCalendar(2013, 9, 01)));
		assertFalse(g.isLibera(new GregorianCalendar(2012, 9, 01), new GregorianCalendar(2014, 9, 01)));
		assertFalse(g.isLibera(new GregorianCalendar(2014, 6, 01), new GregorianCalendar(2014, 9, 01)));
		assertFalse(g.isLibera(new GregorianCalendar(2014, 6, 01), new GregorianCalendar(2018, 9, 01)));
		assertTrue(g.isLibera(new GregorianCalendar(2017, 6, 01), new GregorianCalendar(2018, 9, 01)));
	}
	
	@Test
	public void testLiberaRichieste(){
		Richiesta.initCodice();
		RichiestaGru rg=new RichiestaGru(90, 110, 90, 110, 90, 110, 180, 360);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r1=new Richiesta(rg,lavoro);
		g.aggiungiRichiesta(r1);
		Richiesta r2=new Richiesta(rg,lavoro);
		g.aggiungiRichiesta(r2);
		Richiesta r3=new Richiesta(rg,lavoro);
		g.aggiungiRichiesta(r3);
		Richiesta test=g.getRichiesta(1);
		test.setMacchina(g);
		assertEquals(test.getMacchina(),g);
		assertEquals(r1,test);
		test=g.getRichiesta(2);
		test.setMacchina(g);
		assertEquals(test.getMacchina(),g);
		assertEquals(r2,test);
		test=g.getRichiesta(3);
		test.setMacchina(g);
		assertEquals(test.getMacchina(),g);
		assertEquals(r3,test);
		g.liberaRichieste();
		test=g.getRichiesta(1);
		assertEquals(test,null);
		test=g.getRichiesta(2);
		assertEquals(test,null);
		test=g.getRichiesta(3);
		assertEquals(test,null);
		assertEquals(r1.getMacchina(),null);
		assertEquals(r2.getMacchina(),null);
		assertEquals(r3.getMacchina(),null);
	}
}
