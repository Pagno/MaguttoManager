package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class ModelGruTest {

	
	ModelGru mg;
	@Before
	public void init(){
		ModelGru.resetForTest();
		mg=ModelGru.getModelGru();
		mg.caricaGru(3, "Raimondi", "MRT33+3", 360, 2000, 35,30);
		mg.caricaGru(11, "Vicaro", "OMV 168 A", 23, 1500, 20,26);
		mg.caricaGru(26, "Cattaneo", "CM 78A", 45, 2200, 28,27);
	}
	@Test
	public void testGetModelGru() {
		ArrayList<Gru>lista=mg.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Gru(3, "Raimondi", "MRT33+3", 360, 2000, 35,30)));
		assertTrue(lista.contains(new Gru(11, "Vicaro", "OMV 168 A", 23, 1500, 20,26)));
		assertTrue(lista.contains(new Gru(26, "Cattaneo", "CM 78A", 45, 2200, 28,27)));
		ModelGru prova=ModelGru.getModelGru();
		assertSame(mg,prova);
	}

	@Test
	public void testAggiungiGru() {
		assertEquals(mg.getNextCodice(),27);
		mg.aggiungiGru("Raimondi", "MRT33+3", 360, 2000, 35,30);
		assertEquals(mg.getNextCodice(),28);
		ArrayList<Gru>lista=mg.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Gru(27,"Raimondi", "MRT33+3", 360, 2000, 35,30)));
	}

	@Test
	public void testCaricaGru() {
		assertEquals(mg.getNextCodice(),28);
		mg.caricaGru(31, "Raimondi", "MRT33+3", 360, 2000, 35,30);
		assertEquals(mg.getNextCodice(),32);
		ArrayList<Gru>lista=mg.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Gru(31, "Raimondi", "MRT33+3", 360, 2000, 35,30)));
	}

	@Test
	public void testModificaGru() {
		assertEquals(mg.getNextCodice(),32);
		mg.modificaGru(3, "Vicaro", "OMV 168 A", 23, 1500, 20,26);
		assertEquals(mg.getNextCodice(),32);
		ArrayList<Gru>lista=mg.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Gru(3, "Vicaro", "OMV 168 A", 23, 1500, 20,26)));
	}

	@Test
	public void testEliminaGru() {
		assertTrue(mg.eliminaGru(26));
		ArrayList<Gru>lista=mg.getLista();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Gru(26, "Cattaneo", "CM 78A", 45, 2200, 28,27)));
	
	}

	@Test
	public void testIsGru() {
		mg.caricaGru(15, "Raimondi", "MRT33+3", 360, 2000, 35,30);
		assertTrue(mg.isGru(15));
	}

	@Test
	public void testGetGru() {
		mg.caricaGru(7, "Raimondi", "MRT33+3", 360, 2000, 35,30);

		Gru g=mg.getGru(7);
		assertEquals(g.getCodice(),7);
		assertEquals(g.getProduttore(),"Raimondi");
		assertEquals(g.getModello(),"MRT33+3");
		assertEquals(g.getAngoloRotazione(),360);
		assertEquals(g.getPortataMassima(),2000);
		assertEquals(g.getLunghezza(),35);
		assertEquals(g.getAltezza(),30);
		
	}

}
