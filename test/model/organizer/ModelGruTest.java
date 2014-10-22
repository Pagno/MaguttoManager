package model.organizer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaGru;

import org.junit.Test;

// 
/**
 *   Class ModelGruTest.
 */
public class ModelGruTest {

	/**   mg. */
	ModelGru mg;
	
	/**
	 * Instantiates a new model gru test.
	 */
	public ModelGruTest(){
		mg=ModelGru.getModelGru();
		mg.resetForTest();
		mg.caricaGru(3, "Raimondi", "MRT33+3", 360, 2000, 35,30);
		mg.caricaGru(11, "Vicaro", "OMV 168 A", 23, 1500, 20,26);
		mg.caricaGru(26, "Cattaneo", "CM 78A", 45, 2200, 28,27);
	}
	
	/**
	 * Test get model gru.
	 */
	@Test
	public void testGetModelGru() {
		ArrayList<Gru>lista=mg.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Gru(3, "Raimondi", "MRT33+3", 360, 2000, 35,30)));
		assertTrue(lista.contains(new Gru(11, "Vicaro", "OMV 168 A", 23, 1500, 20,26)));
		assertTrue(lista.contains(new Gru(26, "Cattaneo", "CM 78A", 45, 2200, 28,27)));
		ModelGru prova=ModelGru.getModelGru();
		assertSame(mg,prova);
		mg=ModelGru.getModelGru();
		mg.resetForTest();
		assertEquals(mg.getProssimoCodice(),1);
	}

	/**
	 * Test aggiungi gru.
	 */
	@Test
	public void testAggiungiGru() {
		assertEquals(mg.getProssimoCodice(),27);
		mg.aggiungiGru("Raimondi", "MRT33+3", 360, 2000, 35,30);
		assertEquals(mg.getProssimoCodice(),28);
		ArrayList<Gru>lista=mg.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Gru(27,"Raimondi", "MRT33+3", 360, 2000, 35,30)));
	}

	/**
	 * Test carica gru.
	 */
	@Test
	public void testCaricaGru() {
		assertEquals(mg.getProssimoCodice(),27);
		mg.caricaGru(31, "Raimondi", "MRT33+3", 360, 2000, 35,30);
		assertEquals(mg.getProssimoCodice(),32);
		ArrayList<Gru>lista=mg.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Gru(31, "Raimondi", "MRT33+3", 360, 2000, 35,30)));
	}

	/**
	 * Test modifica gru.
	 */
	@Test
	public void testModificaGru() {
		assertEquals(mg.getProssimoCodice(),27);
		mg.modificaGru(3, "Vicaro", "OMV 168 A", 23, 1500, 20,26);
		assertEquals(mg.getProssimoCodice(),27);
		ArrayList<Gru>lista=mg.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Gru(3, "Vicaro", "OMV 168 A", 23, 1500, 20,26)));
	}

	/**
	 * Test elimina gru.
	 */
	@Test
	public void testEliminaGru() {
		assertTrue(mg.eliminaGru(26));
		ArrayList<Gru>lista=mg.getLista();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Gru(26, "Cattaneo", "CM 78A", 45, 2200, 28,27)));
		assertFalse(mg.eliminaGru(99));
	
	}

	/**
	 * Test is gru.
	 */
	@Test
	public void testIsGru() {
		mg.caricaGru(15, "Raimondi", "MRT33+3", 360, 2000, 35,30);
		assertTrue(mg.isGru(15));
		assertFalse(mg.isGru(99));
	}

	/**
	 * Test get gru.
	 */
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
		assertNull(mg.getGru(99));
	}

	@Test
	public void testToString() {
		Gru a =new Gru(3, "Raimondi", "MRT33+3", 360, 2000, 35,30);
		Gru b =new Gru(11, "Vicaro", "OMV 168 A", 23, 1500, 20,26);
		Gru c =new Gru(26, "Cattaneo", "CM 78A", 45, 2200, 28,27);
		String str= a.toString() + "\n" + b.toString() + "\n" + c.toString() + "\n";
		assertEquals(mg.toString(),str);
		
	}
	
	@Test
	public void testGetDisponibili() {

		ArrayList<Gru>test=mg.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertEquals(test.size(),3); 
		Gru g1=mg.getGru(3);
		Gru g2=mg.getGru(11);
		Gru g3=mg.getGru(26);
		assertTrue(test.contains(g1));
		assertTrue(test.contains(g2));
		assertTrue(test.contains(g3));
		Lavoro l=new Lavoro(1, "Lavoro", null, new GregorianCalendar(2014,1,1), new GregorianCalendar(2014,10,10));
		RichiestaGru rg=new RichiestaGru(1, 40, 1, 35, 1, 3000, 1, 400);
		Richiesta ric2=new Richiesta(rg, l, 2);
		ric2.setMacchina(g2);
		test=mg.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertEquals(test.size(),2);
		assertTrue(test.contains(g1));
		assertTrue(test.contains(g3));
		Richiesta ric1=new Richiesta(rg, l, 1);
		ric1.setMacchina(g1);
		test=mg.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertEquals(test.size(),1);
		assertTrue(test.contains(g3));
		Richiesta ric3=new Richiesta(rg, l, 3);
		ric3.setMacchina(g3);
		test=mg.getDisponibili(new GregorianCalendar(2014,2,2), new GregorianCalendar(2014,9,9));
		assertTrue(test.isEmpty());
	}
	@Test
	public void testAggiungiGruForTest(){
		Gru e=new Gru(17, "Escavatore", "E104", 360,2000, 49, 45);
		mg.aggiungiGruForTest(e);
		Gru a=mg.getGru(17);
		assertEquals(e.getCodice(),a.getCodice());
		assertEquals(e.getProduttore(), a.getProduttore());
		assertEquals(e.getModello(),a.getModello() );
		assertEquals(e.getAltezza(),a.getAltezza());
		assertEquals(e.getPortataMassima(),a.getPortataMassima() );
		assertEquals(e.getAngoloRotazione(),a.getAngoloRotazione());
		assertEquals(e.getLunghezza(),a.getLunghezza() );
		
	}
	
}
