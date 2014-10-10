package model.organizer.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

// 
/**
 *   Class RuspaTest.
 */
public class RuspaTest {

	/**   r. */
	Ruspa r;
	
	/**
	 * Instantiates a new ruspa test.
	 */
	public RuspaTest(){
		r=new Ruspa(5,"Caterpillar","Ruspona",100,99,98);
	}
	
	/**
	 * Test ruspa.
	 */
	@Test
	public void testRuspa() {
		assertEquals(r.getCapacitaMassima(),100);
		assertEquals(r.getPortataMassima(),99);
		assertEquals(r.getAltezzaMassima(),98);
		assertEquals(r.getProduttore(),"Caterpillar");
		assertEquals(r.getModello(),"Ruspona");
	}

	/**
	 * Test set capacita massima.
	 */
	@Test
	public void testSetCapacitaMassima() {
		r.setCapacitaMassima(200);
		assertEquals(r.getCapacitaMassima(),200);
	}

	/**
	 * Test set portata massima.
	 */
	@Test
	public void testSetPortataMassima() {
		r.setPortataMassima(200);
		assertEquals(r.getPortataMassima(),200);
	}

	/**
	 * Test set altezza massima.
	 */
	@Test
	public void testSetAltezzaMassima() {
		r.setAltezzaMassima(200);
		assertEquals(r.getAltezzaMassima(),200);
	}

	/**
	 * Test set produttore.
	 */
	@Test
	public void testSetProduttore() {
		r.setProduttore("Volvo");
		assertEquals(r.getProduttore(),"Volvo");
	}

	/**
	 * Test set modello.
	 */
	@Test
	public void testSetModello() {
		r.setModello("Ruspetta");
		assertEquals(r.getModello(),"Ruspetta");
	}
	
	@Test
	public void testToString() {
		assertEquals(r.toString(),5 + " Caterpillar Ruspona " + 100 + " " + 99 + " " + 98);
	}
	
	@Test
	public void testEquals(){
		assertTrue(r.equals(r));
		assertTrue(r.equals(new Ruspa(5,"Caterpillar","Ruspona",100,99,98)));
		assertFalse(r.equals(new Ruspa(6,"Caterpillar","Ruspona",100,99,98)));
		assertFalse(r.equals(new Ruspa(5,"Volvo","Ruspona",100,99,98)));
		assertFalse(r.equals(new Ruspa(5,"Caterpillar","Ruspa",100,99,98)));
		assertFalse(r.equals(new Ruspa(5,"Caterpillar","Ruspona",101,99,98)));
		assertFalse(r.equals(new Ruspa(5,"Caterpillar","Ruspona",100,100,98)));
		assertFalse(r.equals(new Ruspa(5,"Caterpillar","Ruspona",100,99,99)));
		assertFalse(r.equals(new Gru(5,"Volvo","Grattacielo",360,100,99,98)));
		assertFalse(r.equals("Stringa"));
		assertFalse(r.equals(null));
	}



	@Test
	public void testAddRichiesta(){
		Richiesta.initCodice();
		RichiestaRuspa rr=new RichiestaRuspa(5, 10, 5, 10, 5, 10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta ric=new Richiesta(rr,lavoro);
		r.addRichiesta(ric);
		Richiesta test=r.getRichiesta(1);
		assertEquals(ric,test);
		test=r.getRichiesta(99);
		assertEquals(test,null);
	}
	
	@Test
	public void testRemoveRichiesta(){
		Richiesta.initCodice();
		RichiestaRuspa rr=new RichiestaRuspa(5, 10, 5, 10, 5, 10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta ric=new Richiesta(rr,lavoro);
		r.addRichiesta(ric);
		Richiesta test=r.getRichiesta(1);
		assertEquals(ric,test);
		r.removeRichiesta(ric);
		test=r.getRichiesta(1);
		assertEquals(test,null);
	}
	
	@Test
	public void testIsFree(){
		Richiesta.initCodice();
		RichiestaRuspa rr=new RichiestaRuspa(5, 10, 5, 10, 5, 10);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 4, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta ric=new Richiesta(rr,lavoro);
		r.addRichiesta(ric);
		assertTrue(r.isFree(new GregorianCalendar(2012, 9, 01), new GregorianCalendar(2013, 9, 01)));
		assertFalse(r.isFree(new GregorianCalendar(2012, 9, 01), new GregorianCalendar(2014, 9, 01)));
		assertFalse(r.isFree(new GregorianCalendar(2014, 6, 01), new GregorianCalendar(2014, 9, 01)));
		assertFalse(r.isFree(new GregorianCalendar(2014, 6, 01), new GregorianCalendar(2018, 9, 01)));
		assertTrue(r.isFree(new GregorianCalendar(2017, 6, 01), new GregorianCalendar(2018, 9, 01)));
	}
	
	@Test
	public void testLiberaRichieste(){
		Richiesta.initCodice();
		RichiestaRuspa rr=new RichiestaRuspa(90, 110, 90, 110, 90, 110);
		Lavoro lavoro=new Lavoro(5,"Scavi",null, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r1=new Richiesta(rr,lavoro);
		r.addRichiesta(r1);
		Richiesta r2=new Richiesta(rr,lavoro);
		r.addRichiesta(r2);
		Richiesta r3=new Richiesta(rr,lavoro);
		r.addRichiesta(r3);
		Richiesta test=r.getRichiesta(1);
		test.setMacchina(r);
		assertEquals(test.getMacchina(),r);
		assertEquals(r1,test);
		test=r.getRichiesta(2);
		test.setMacchina(r);
		assertEquals(test.getMacchina(),r);
		assertEquals(r2,test);
		test=r.getRichiesta(3);
		test.setMacchina(r);
		assertEquals(test.getMacchina(),r);
		assertEquals(r3,test);
		r.liberaRichieste();
		test=r.getRichiesta(1);
		assertEquals(test,null);
		test=r.getRichiesta(2);
		assertEquals(test,null);
		test=r.getRichiesta(3);
		assertEquals(test,null);
		assertEquals(r1.getMacchina(),null);
		assertEquals(r2.getMacchina(),null);
		assertEquals(r3.getMacchina(),null);
	}
}
