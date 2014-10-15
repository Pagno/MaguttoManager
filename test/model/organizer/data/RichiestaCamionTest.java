package model.organizer.data;

import static org.junit.Assert.*;

import org.junit.Test;

// 
/**
 *   Class AssociazioneTest.
 */
public class RichiestaCamionTest {
	
	/**   a. */
	RichiestaCamion rCamion;

    /**
     * Instantiates a new associazione test.
     */
    public RichiestaCamionTest(){
    	//int minCapacita, int maxCapacita, int minPortata, int maxPortata, int minLunghezza
    	//, int maxLunghezza) 

    	rCamion=new RichiestaCamion(300,500,3000,4000,10,20);
    }
    
	/**
	 * Test associazione.
	 */
	@Test
	public void testRichiestaGruTest() {
		assertEquals(rCamion.getMinLunghezza(),10);
		assertEquals(rCamion.getMaxLunghezza(),20);
		assertEquals(rCamion.getMinCapacita(),300);
		assertEquals(rCamion.getMaxCapacita(),500);
		assertEquals(rCamion.getMinPortata(),3000);
		assertEquals(rCamion.getMaxPortata(),4000);	
	}

	@Test
	public void testRispettaRichiesta(){
		assertTrue(rCamion.rispettaRichiesta(new Camion(1,"New Holland","GRU212",300,3100,18)));
		assertFalse(rCamion.rispettaRichiesta(new Camion(1,"New Holland","GRU212",299,3100,18)));
		assertFalse(rCamion.rispettaRichiesta(new Camion(1,"New Holland","GRU212",300,4100,18)));
		assertFalse(rCamion.rispettaRichiesta(new Camion(1,"New Holland","GRU212",300,3100,21)));
		assertFalse(rCamion.rispettaRichiesta(new Ruspa(1,"New Holland","GRU212",150,412,16)));
		assertFalse(rCamion.rispettaRichiesta(null));
	}

	@Test
	public void testSetMinLunghezza() {
		rCamion.setMinLunghezza(35);
		assertEquals(rCamion.getMinLunghezza(),35);
	}
	@Test
	public void testSetMaxLunghezza() {
		rCamion.setMaxLunghezza(45);
		assertEquals(rCamion.getMaxLunghezza(),45);
	}
	@Test
	public void testSetMinCapacita() {
		rCamion.setMinCapacita(25);
		assertEquals(rCamion.getMinCapacita(),25);
	}
	@Test
	public void testSetMaxCapacita() {
		rCamion.setMaxCapacita(35);
		assertEquals(rCamion.getMaxCapacita(),35);
	}
	@Test
	public void testGetMinPortata() {
		rCamion.setMinPortata(3012);
		assertEquals(rCamion.getMinPortata(),3012);
	}
	@Test
	public void testGetMaxPortata() {
		rCamion.setMaxPortata(4627);
		assertEquals(rCamion.getMaxPortata(),4627);
	}

	
	
	
	@Test
	public void testEquals(){
		assertTrue(rCamion.equals(rCamion));
		assertFalse(rCamion.equals(null));
		assertTrue(rCamion.equals(new RichiestaCamion(300,500,3000,4000,10,20)));
		assertFalse(rCamion.equals("stringa"));
		assertFalse(rCamion.equals(new RichiestaCamion(301,500,3000,4000,10,20)));
		assertFalse(rCamion.equals(new RichiestaCamion(300,499,3000,4000,10,20)));
		assertFalse(rCamion.equals(new RichiestaCamion(300,500,3100,4000,10,20)));
		assertFalse(rCamion.equals(new RichiestaCamion(300,500,3000,3999,10,20)));
		assertFalse(rCamion.equals(new RichiestaCamion(300,500,3000,4000,11,20)));
		assertFalse(rCamion.equals(new RichiestaCamion(300,500,3000,4000,10,19)));
		}

	@Test
	public void testtoString(){
		assertEquals(rCamion.toString(),"Richiesta:Camion 300-500 3000-4000 10-20");
	}
}
