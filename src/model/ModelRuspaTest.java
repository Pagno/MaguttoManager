package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ModelRuspaTest {

	ModelRuspa mr;
	
	public ModelRuspaTest() {
		ModelRuspa.resetForTest();
		mr=ModelRuspa.getModelRuspa();
		mr.caricaRuspa(5,"Caterpillar","Ruspona",1,5000,2);
		mr.caricaRuspa(6, "Hitachi", "ZW65", 2,3500, 3);
		mr.caricaRuspa(9, "Liebherr", "L524", 1, 2000, 2);
	}
	
	@Test
	public void testGetModelRuspa() {
		ArrayList<Ruspa> lista=mr.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Ruspa(5,"Caterpillar","Ruspona",1,5000,2)));
		assertTrue(lista.contains(new Ruspa(6, "Hitachi", "ZW65", 2,3500, 3)));
		assertTrue(lista.contains(new Ruspa(9, "Liebherr", "L524", 1, 2000, 2)));
		ModelRuspa prova=ModelRuspa.getModelRuspa();
		assertSame(mr,prova);
	}

	@Test
	public void testCaricaRuspa() {
		assertEquals(mr.getNextCodice(),10);
		mr.caricaRuspa(19,"XCMG", "LW500KL", 3, 17000, 5);
		assertEquals(mr.getNextCodice(),20);
		ArrayList<Ruspa> lista=mr.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Ruspa(19,"XCMG", "LW500KL", 3, 17000, 5)));
	}
	
	@Test
	public void testAggiungiRuspa() {
		assertEquals(mr.getNextCodice(),20);
		mr.aggiungiRuspa("Hyundai", "HL730-9", 2, 9800, 3);
		assertEquals(mr.getNextCodice(),21);
		ArrayList<Ruspa>lista=mr.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Ruspa(20,"Hyundai", "HL730-9", 2, 9800, 3)));
	}


	@Test
	public void testModificaRuspa() {
		assertEquals(mr.getNextCodice(),21);
		mr.modificaRuspa(5,"XCMG", "LW500", 2, 2700, 3);
		assertEquals(mr.getNextCodice(),21);
		ArrayList<Ruspa>lista=mr.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Ruspa(5,"XCMG", "LW500", 2, 2700, 3)));
	}

	@Test
	public void testEliminaRuspa() {
		assertTrue(mr.eliminaRuspa(6));
		ArrayList<Ruspa> lista=mr.getLista();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Ruspa(6, "Hitachi", "ZW65", 2,3500, 3)));
	}

	@Test
	public void testIsRuspa() {
		mr.caricaRuspa(8, "JCB", "411", 3, 8000, 3);
		assertTrue(mr.isRuspa(8));
	}

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
	}

}
