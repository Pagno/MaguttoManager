package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 *   Class ModelEscavatoreTest.
 */
public class ModelEscavatoreTest {

	/**   mr. */
	ModelEscavatore mr;
	
	/**
	 * Instantiates a new model escavatore test.
	 */
	public ModelEscavatoreTest() {
		ModelEscavatore.resetForTest();
		mr=ModelEscavatore.getModelEscavatore();
		mr.caricaEscavatore(5,"Liebherr","R9250",16,32,4,3);
		mr.caricaEscavatore(6, "Hyundai", "R55-9", 9,15, 5,3);
		mr.caricaEscavatore(9, "Hyndai", "R260LC-9A", 25,50, 5, 4);
	}
	
	/**
	 * Test get model escavatore.
	 */
	@Test
	public void testGetModelEscavatore() {
		ArrayList<Escavatore> lista=mr.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Escavatore(5,"Liebherr","R9250",16,32,4,3)));
		assertTrue(lista.contains(new Escavatore(6, "Hyundai", "R55-9", 9,15, 5,3)));
		assertTrue(lista.contains(new Escavatore(9, "Hyndai", "R260LC-9A", 25,50, 5, 4)));
		ModelEscavatore prova=ModelEscavatore.getModelEscavatore();
		assertSame(mr,prova);
	}

	/**
	 * Test aggiungi escavatore.
	 */
	@Test
	public void testAggiungiEscavatore() {
		assertEquals(mr.getNextCodice(),14);
		mr.aggiungiEscavatore("JCB", "JS115", 6,12, 4, 2);
		assertEquals(mr.getNextCodice(),15);
		ArrayList<Escavatore>lista=mr.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Escavatore(14,"JCB", "JS115", 6,12, 4, 2)));
	}

	/**
	 * Test carica escavatore.
	 */
	@Test
	public void testCaricaEscavatore() {
		assertEquals(mr.getNextCodice(),10);
		mr.caricaEscavatore(13,"Komatsu", "PC88MR-8", 4,8, 5, 2);
		assertEquals(mr.getNextCodice(),14);
		ArrayList<Escavatore> lista=mr.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Escavatore(13,"Komatsu", "PC88MR-8", 4,8, 5, 2)));
	}

	/**
	 * Test modifica escavatore.
	 */
	@Test
	public void testModificaEscavatore() {
		assertEquals(mr.getNextCodice(),10);
		mr.modificaEscavatore(6,"XCMG", "XE215CLL",15,33,5, 3);
		assertEquals(mr.getNextCodice(),10);
		ArrayList<Escavatore>lista=mr.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Escavatore(6,"XCMG", "XE215CLL",15,33,5, 3)));
	}

	/**
	 * Test elimina escavatore.
	 */
	@Test
	public void testEliminaEscavatore() {
		assertTrue(mr.eliminaEscavatore(6));
		ArrayList<Escavatore> lista=mr.getLista();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Escavatore(6,"XCMG", "XE215CLL",15,33,5, 3)));
	}

	/**
	 * Test is escavatore.
	 */
	@Test
	public void testIsEscavatore() {
		mr.caricaEscavatore(8, "New Holland", "E140C R", 30,50,5,3);
		assertTrue(mr.isEscavatore(8));
	}

	/**
	 * Test get escavatore.
	 */
	@Test
	public void testGetEscavatore() {

		mr.caricaEscavatore(2, "New Holland", "E140C R", 30,50,5,3);

		Escavatore g=mr.getEscavatore(2);
		assertEquals(g.getCodice(),2);
		assertEquals(g.getProduttore(),"New Holland");
		assertEquals(g.getModello(),"E140C R");
		assertEquals(g.getAltezzaMassima(),5);
		assertEquals(g.getPortataMassima(),50);
		assertEquals(g.getCapacitaMassima(),30);
		assertEquals(g.getProfonditaMassima(),3);
	}

}
