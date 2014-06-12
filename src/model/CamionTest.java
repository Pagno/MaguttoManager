package model;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
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

}
