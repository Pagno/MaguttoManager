package model;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
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

}
