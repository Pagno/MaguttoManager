package model;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
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

}
