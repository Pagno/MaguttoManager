package model.organizer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.organizer.ModelCamion;
import model.organizer.data.Camion;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 *   Class ModelCamionTest.
 */
public class ModelCamionTest {


	/**   mc. */
	ModelCamion mc;
	
	/**
	 * Instantiates a new model camion test.
	 */
	public ModelCamionTest() {
		ModelCamion.resetForTest();
		mc=ModelCamion.getModelCamion();
		mc.caricaCamion(5, "Iveco", "Daily 35C10", 2000, 1340, 4);
		mc.caricaCamion(7, "Iveco", "Daily", 1800,1045, 3);
		mc.caricaCamion(9, "Volkswagen", "Crafter", 1680, 1000, 1);
	}

	/**
	 * Test get model camion.
	 */
	@Test
	public void testGetModelCamion() {
		ArrayList<Camion> lista=mc.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Camion(5, "Iveco", "Daily 35C10", 2000, 1340, 4)));
		assertTrue(lista.contains(new Camion(7, "Iveco", "Daily", 1800,1045, 3)));
		assertTrue(lista.contains(new Camion(9, "Volkswagen", "Crafter", 1680, 1000, 1)));
		ModelCamion prova=ModelCamion.getModelCamion();
		assertSame(mc,prova);
		ModelCamion.resetForTest();
		mc=ModelCamion.getModelCamion();
		assertEquals(mc.getNextCodice(),1);
	}

	/**
	 * Test aggiungi camion.
	 */
	@Test
	public void testAggiungiCamion() {
		assertEquals(mc.getNextCodice(),10);
		mc.aggiungiCamion("Iveco", "35C10", 1360, 1030, 3);
		assertEquals(mc.getNextCodice(),11);
		ArrayList<Camion>lista=mc.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Camion(10,"Iveco", "35C10", 1360, 1030, 3)));
	}
	
	/**
	 * Test carica camion.
	 */
	@Test
	public void testCaricaCamion() {
		assertEquals(mc.getNextCodice(),10);
		mc.caricaCamion(15,"Iveco", "35C10", 1360, 1030, 3);
		assertEquals(mc.getNextCodice(),16);
		ArrayList<Camion> lista=mc.getLista();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Camion(15,"Iveco", "35C10", 1360, 1030, 3)));
	}
	
	/**
	 * Test modifica camion.
	 */
	@Test
	public void testModificaCamion() {
		assertEquals(mc.getNextCodice(),10);
		mc.modificaCamion(5, "Iveco", "Daily", 1800,1045, 3);
		assertEquals(mc.getNextCodice(),10);
		ArrayList<Camion>lista=mc.getLista();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Camion(5, "Iveco", "Daily", 1800,1045, 3)));
	}

	/**
	 * Test elimina camion.
	 */
	@Test
	public void testEliminaCamion() {
		assertTrue(mc.eliminaCamion(7));
		ArrayList<Camion> lista=mc.getLista();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Camion(7, "Iveco", "Daily", 1800,1045, 3)));
		assertFalse(mc.eliminaCamion(34));
	}

	/**
	 * Test is camion.
	 */
	@Test
	public void testIsCamion() {
		mc.caricaCamion(8, "Iveco", "Daily", 1200, 800, 3);
		assertTrue(mc.isCamion(8));
		assertFalse(mc.isCamion(34));
	}

	/**
	 * Test get camion.
	 */
	@Test
	public void testGetCamion() {

		mc.caricaCamion(2, "Volvo", "Trucks", 3600, 2000, 9);

		Camion g=mc.getCamion(2);
		assertEquals(g.getCodice(),2);
		assertEquals(g.getProduttore(),"Volvo");
		assertEquals(g.getModello(),"Trucks");
		assertEquals(g.getLunghezza(),9);
		assertEquals(g.getPortataMassima(),2000);
		assertEquals(g.getCapacitaMassima(),3600);
		assertNull(mc.getCamion(34));
	}
	
	@Test
	public void testToString() {
		Camion a=new Camion(5, "Iveco", "Daily 35C10", 2000, 1340, 4);
		Camion b=new Camion(7, "Iveco", "Daily", 1800,1045, 3);
		Camion c=new Camion(9, "Volkswagen", "Crafter", 1680, 1000, 1);
		String str=a.toString() + "\n" + b.toString() + "\n" + c.toString() + "\n";
		assertEquals(mc.toString(),str);
	}

}
