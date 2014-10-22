package model.organizer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;

import org.junit.Test;

// 
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
		mr=ModelEscavatore.getModelEscavatore();
		mr.resetForTest();
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
		mr=ModelEscavatore.getModelEscavatore();
		mr.resetForTest();
		assertEquals(mr.getProssimoCodice(),1);
	}

	/**
	 * Test aggiungi escavatore.
	 */
	@Test
	public void testAggiungiEscavatore() {
		assertEquals(mr.getProssimoCodice(),10);
		mr.aggiungiEscavatore("JCB", "JS115", 6,12, 4, 2);
		assertEquals(mr.getProssimoCodice(),11);
		ArrayList<Escavatore>lista=mr.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Escavatore(10,"JCB", "JS115", 6,12, 4, 2)));
	}

	/**
	 * Test carica escavatore.
	 */
	@Test
	public void testCaricaEscavatore() {
		assertEquals(mr.getProssimoCodice(),10);
		mr.caricaEscavatore(13,"Komatsu", "PC88MR-8", 4,8, 5, 2);
		assertEquals(mr.getProssimoCodice(),14);
		ArrayList<Escavatore> lista=mr.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Escavatore(13,"Komatsu", "PC88MR-8", 4,8, 5, 2)));
	}

	/**
	 * Test modifica escavatore.
	 */
	@Test
	public void testModificaEscavatore() {
		assertEquals(mr.getProssimoCodice(),10);
		mr.modificaEscavatore(6,"XCMG", "XE215CLL",15,33,5, 3);
		assertEquals(mr.getProssimoCodice(),10);
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
		assertFalse(mr.eliminaEscavatore(99));
	}

	/**
	 * Test is escavatore.
	 */
	@Test
	public void testIsEscavatore() {
		mr.caricaEscavatore(8, "New Holland", "E140C R", 30,50,5,3);
		assertTrue(mr.isEscavatore(8));
		assertFalse(mr.isEscavatore(99));
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
		assertNull(mr.getEscavatore(99));
	}
	
	@Test
	public void testToString() {
		Escavatore a=new Escavatore(5,"Liebherr","R9250",16,32,4,3);
		Escavatore b=new Escavatore(6, "Hyundai", "R55-9", 9,15, 5,3);
		Escavatore c=new Escavatore(9, "Hyndai", "R260LC-9A", 25,50, 5, 4);
		String str= a.toString() + "\n" + b.toString() + "\n" + c.toString() + "\n";
		assertEquals(mr.toString(),str);
		
	}
	
	@Test
	public void testGetDisponibili() {
		//capacita, portata, altezza, profondita);
				//25,50,5,4
		ArrayList<Escavatore>test=mr.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertEquals(test.size(),3); 
		Escavatore e1=mr.getEscavatore(5);
		Escavatore e2=mr.getEscavatore(6);
		Escavatore e3=mr.getEscavatore(9);
		assertTrue(test.contains(e1));
		assertTrue(test.contains(e2));
		assertTrue(test.contains(e3));
		Lavoro l=new Lavoro(1, "Lavoro", null, new GregorianCalendar(2014,1,1), new GregorianCalendar(2014,10,10));
		RichiestaEscavatore re=new RichiestaEscavatore(1, 30, 1, 60, 1, 6, 1, 5);
		Richiesta ric2=new Richiesta(re, l, 2);
		ric2.setMacchina(e2);
		test=mr.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertEquals(test.size(),2);
		assertTrue(test.contains(e1));
		assertTrue(test.contains(e3));
		Richiesta ric1=new Richiesta(re, l, 1);
		ric1.setMacchina(e1);
		test=mr.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertEquals(test.size(),1);
		assertTrue(test.contains(e3));
		Richiesta ric3=new Richiesta(re, l, 3);
		ric3.setMacchina(e3);
		test=mr.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertTrue(test.isEmpty());
	}
	@Test
	public void testAggiungiEscavatoreForTest(){
		Escavatore e=new Escavatore(17, "Escavatore", "E104", 2000, 380, 9, 5);
		mr.aggiungiEscavatoreForTest(e);
		Escavatore a=mr.getEscavatore(17);
		assertEquals(e.getCodice(),a.getCodice());
		assertEquals(e.getProduttore(), a.getProduttore());
		assertEquals(e.getModello(),a.getModello() );
		assertEquals(e.getAltezzaMassima(),a.getAltezzaMassima());
		assertEquals(e.getPortataMassima(),a.getPortataMassima() );
		assertEquals(e.getProfonditaMassima(),a.getProfonditaMassima());
		assertEquals(e.getCapacitaMassima(),a.getCapacitaMassima() );
		
	}
}
