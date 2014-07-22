package model;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
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

}
