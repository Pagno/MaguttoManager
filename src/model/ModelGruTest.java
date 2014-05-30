package model;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class ModelGruTest {

	
	ModelGru ea;
	@Before
	public void init(){
		ModelGru.resetForTest();
		ea=ModelGru.getModelGru();
		ea.caricaGru(3, "Raimondi", "MRT33+3", 360, 2000, 35,30);
		ea.caricaGru(11, "Vicaro", "OMV 168 A", 23, 1500, 20,26);
		ea.caricaGru(26, "Cattaneo", "CM 78A", 45, 2200, 28,27);
	}
	@Test
	public void testGetModelGru() {
		fail("Not yet implemented");
	}

	@Test
	public void testAggiungiGru() {
		fail("Not yet implemented");
	}

	@Test
	public void testCaricaGru() {
		fail("Not yet implemented");
	}

	@Test
	public void testModificaGru() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminaGru() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsGru() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGru() {
		fail("Not yet implemented");
	}

}
