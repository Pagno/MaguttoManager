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
	public void testToString(){
		assertEquals(rCamion.toString(),"Richiesta:Camion 300-500 3000-4000 10-20");
		rCamion.setMinCapacita(-1);
		assertEquals(rCamion.toString(),"Richiesta:Camion *-500 3000-4000 10-20");
		rCamion.setMinCapacita(300);
		rCamion.setMaxCapacita(-1);
		assertEquals(rCamion.toString(),"Richiesta:Camion 300-* 3000-4000 10-20");
		rCamion.setMaxCapacita(500);
		rCamion.setMinPortata(-1);
		assertEquals(rCamion.toString(),"Richiesta:Camion 300-500 *-4000 10-20");
		rCamion.setMinPortata(3000);
		rCamion.setMaxPortata(-1);
		assertEquals(rCamion.toString(),"Richiesta:Camion 300-500 3000-* 10-20");
		rCamion.setMaxPortata(4000);
		rCamion.setMinLunghezza(-1);
		assertEquals(rCamion.toString(),"Richiesta:Camion 300-500 3000-4000 *-20");
		rCamion.setMinLunghezza(10);
		rCamion.setMaxLunghezza(-1);
		assertEquals(rCamion.toString(),"Richiesta:Camion 300-500 3000-4000 10-*");
	}
	
	@Test
	public void testInConflitto(){
		RichiestaRuspa ric1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaCamion ric3=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaCamion ric4=new RichiestaCamion(7,12,7,12,7,12);
		assertTrue(ric3.inConflitto(ric4));
		assertTrue(ric4.inConflitto(ric3));
		ric4=new RichiestaCamion(20,30,7,12,7,12);
		assertFalse(ric3.inConflitto(ric4));
		assertFalse(ric4.inConflitto(ric3));
		ric4=new RichiestaCamion(7,12,20,30,7,12);
		assertFalse(ric3.inConflitto(ric4));
		assertFalse(ric4.inConflitto(ric3));
		ric4=new RichiestaCamion(7,12,7,12,20,30);
		assertFalse(ric3.inConflitto(ric4));
		assertFalse(ric4.inConflitto(ric3));
		assertFalse(ric3.inConflitto(ric1));
	}
}
