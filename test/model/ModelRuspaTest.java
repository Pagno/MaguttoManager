package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 *   Class ModelRuspaTest.
 */
public class ModelRuspaTest {

	/**   mr. */
	ModelRuspa mr;
	
	/**
	 * Instantiates a new model ruspa test.
	 */
	public ModelRuspaTest() {
		ModelRuspa.resetForTest();
		mr=ModelRuspa.getModelRuspa();
		mr.caricaRuspa(5,"Caterpillar","Ruspona",1,5000,2);
		mr.caricaRuspa(6, "Hitachi", "ZW65", 2,3500, 3);
		mr.caricaRuspa(9, "Liebherr", "L524", 1, 2000, 2);
	}
	
	/**
	 * Test get model ruspa.
	 */
	@Test
	public void testGetModelRuspa() {
		ArrayList<Ruspa> lista=mr.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Ruspa(5,"Caterpillar","Ruspona",1,5000,2)));
		assertTrue(lista.contains(new Ruspa(6, "Hitachi", "ZW65", 2,3500, 3)));
		assertTrue(lista.contains(new Ruspa(9, "Liebherr", "L524", 1, 2000, 2)));
		ModelRuspa prova=ModelRuspa.getModelRuspa();
		assertSame(mr,prova);
		ModelRuspa.resetForTest();
		mr=ModelRuspa.getModelRuspa();
		assertEquals(mr.getNextCodice(),1);
	}

	/**
	 * Test carica ruspa.
	 */
	@Test
	public void testCaricaRuspa() {
		assertEquals(mr.getNextCodice(),10);
		mr.caricaRuspa(19,"XCMG", "LW500KL", 3, 17000, 5);
		assertEquals(mr.getNextCodice(),20);
		ArrayList<Ruspa> lista=mr.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Ruspa(19,"XCMG", "LW500KL", 3, 17000, 5)));
	}
	
	/**
	 * Test aggiungi ruspa.
	 */
	@Test
	public void testAggiungiRuspa() {
		assertEquals(mr.getNextCodice(),10);
		mr.aggiungiRuspa("Hyundai", "HL730-9", 2, 9800, 3);
		assertEquals(mr.getNextCodice(),11);
		ArrayList<Ruspa>lista=mr.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Ruspa(10,"Hyundai", "HL730-9", 2, 9800, 3)));
	}


	/**
	 * Test modifica ruspa.
	 */
	@Test
	public void testModificaRuspa() {
		assertEquals(mr.getNextCodice(),10);
		mr.modificaRuspa(5,"XCMG", "LW500", 2, 2700, 3);
		assertEquals(mr.getNextCodice(),10);
		ArrayList<Ruspa>lista=mr.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Ruspa(5,"XCMG", "LW500", 2, 2700, 3)));
	}

	/**
	 * Test elimina ruspa.
	 */
	@Test
	public void testEliminaRuspa() {
		assertTrue(mr.eliminaRuspa(6));
		ArrayList<Ruspa> lista=mr.getLista();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Ruspa(6, "Hitachi", "ZW65", 2,3500, 3)));
		assertFalse(mr.eliminaRuspa(99));
	}

	/**
	 * Test is ruspa.
	 */
	@Test
	public void testIsRuspa() {
		mr.caricaRuspa(8, "JCB", "411", 3, 8000, 3);
		assertTrue(mr.isRuspa(8));
		assertFalse(mr.isRuspa(99));
	}

	/**
	 * Test get ruspa.
	 */
	@Test
	public void testGetRuspa() {

		mr.caricaRuspa(2, "Shantui", "SL50WA", 3, 16000, 6);

		Ruspa g=mr.getRuspa(2);
		assertEquals(g.getCodice(),2);
		assertEquals(g.getProduttore(),"Shantui");
		assertEquals(g.getModello(),"SL50WA");
		assertEquals(g.getAltezzaMassima(),6);
		assertEquals(g.getPortataMassima(),16000);
		assertEquals(g.getCapacitaMassima(),3);
		assertNull(mr.getRuspa(99));
	}
	
	@Test
	public void testToString() {
		Ruspa a =new Ruspa(5,"Caterpillar","Ruspona",1,5000,2);
		Ruspa b =new Ruspa(6, "Hitachi", "ZW65", 2,3500, 3);
		Ruspa c =new Ruspa(9, "Liebherr", "L524", 1, 2000, 2);
		String str= a.toString() + "\n" + b.toString() + "\n" + c.toString() + "\n";
		assertEquals(mr.toString(),str);
		
	}

}
