package model.organizer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaRuspa;
import model.organizer.data.Ruspa;

import org.junit.Test;

// 
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
		assertEquals(mr.getProssimoCodice(),1);
	}

	/**
	 * Test carica ruspa.
	 */
	@Test
	public void testCaricaRuspa() {
		assertEquals(mr.getProssimoCodice(),10);
		mr.caricaRuspa(19,"XCMG", "LW500KL", 3, 17000, 5);
		assertEquals(mr.getProssimoCodice(),20);
		ArrayList<Ruspa> lista=mr.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Ruspa(19,"XCMG", "LW500KL", 3, 17000, 5)));
	}
	
	/**
	 * Test aggiungi ruspa.
	 */
	@Test
	public void testAggiungiRuspa() {
		assertEquals(mr.getProssimoCodice(),10);
		mr.aggiungiRuspa("Hyundai", "HL730-9", 2, 9800, 3);
		assertEquals(mr.getProssimoCodice(),11);
		ArrayList<Ruspa>lista=mr.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Ruspa(10,"Hyundai", "HL730-9", 2, 9800, 3)));
	}


	/**
	 * Test modifica ruspa.
	 */
	@Test
	public void testModificaRuspa() {
		assertEquals(mr.getProssimoCodice(),10);
		mr.modificaRuspa(5,"XCMG", "LW500", 2, 2700, 3);
		assertEquals(mr.getProssimoCodice(),10);
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
	public void testGetDisponibili() {

		ArrayList<Ruspa>test=mr.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertEquals(test.size(),3);
		Ruspa r1=mr.getRuspa(5);
		Ruspa r2=mr.getRuspa(6);
		Ruspa r3=mr.getRuspa(9);
		assertTrue(test.contains(r1));
		assertTrue(test.contains(r2));
		assertTrue(test.contains(r3));
		Lavoro l=new Lavoro(1, "Lavoro", null, new GregorianCalendar(2014,1,1), new GregorianCalendar(2014,10,10));
		RichiestaRuspa rr=new RichiestaRuspa(1,5,1,6000,1,10);
		Richiesta ric2=new Richiesta(rr, l, 2);
		ric2.setMacchina(r2);
		test=mr.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertEquals(test.size(),2);
		assertTrue(test.contains(r1));
		assertTrue(test.contains(r3));
		Richiesta ric1=new Richiesta(rr, l, 1);
		ric1.setMacchina(r1);
		test=mr.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertEquals(test.size(),1);
		assertTrue(test.contains(r3));
		Richiesta ric3=new Richiesta(rr, l, 3);
		ric3.setMacchina(r3);
		test=mr.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertTrue(test.isEmpty());
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
