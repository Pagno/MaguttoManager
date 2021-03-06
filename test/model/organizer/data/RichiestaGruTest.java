package model.organizer.data;

import static org.junit.Assert.*;

import org.junit.Test;

// 
/**
 *   Class AssociazioneTest.
 */
public class RichiestaGruTest {
	
	/**   a. */
	RichiestaGru rGru;

    /**
     * Instantiates a new associazione test.
     */
    public RichiestaGruTest(){
    	rGru=new RichiestaGru(30,40,20,30,3000,4000,90,120);
    }
    
	/**
	 * Test associazione.
	 */
	@Test
	public void testRichiestaGruTest() {
		assertEquals(rGru.getMinLunghezza(),30);
		assertEquals(rGru.getMaxLunghezza(),40);
		assertEquals(rGru.getMinAltezza(),20);
		assertEquals(rGru.getMaxAltezza(),30);
		assertEquals(rGru.getMinPortata(),3000);
		assertEquals(rGru.getMaxPortata(),4000);
		assertEquals(rGru.getMinAngoloRotazione(),90);
		assertEquals(rGru.getMaxAngoloRotazione(),120);	
	}

	@Test
	public void testRispettaRichiesta(){
		assertTrue(rGru.rispettaRichiesta(new Gru(1,"New Holland","GRU212",100,3100,38,24)));
		assertFalse(rGru.rispettaRichiesta(new Gru(1,"New Holland","GRU212",100,4001,38,24)));
		assertFalse(rGru.rispettaRichiesta(new Gru(1,"New Holland","GRU212",100,3100,29,24)));
		assertFalse(rGru.rispettaRichiesta(new Gru(1,"New Holland","GRU212",89,3100,38,24)));
		assertFalse(rGru.rispettaRichiesta(new Gru(1,"New Holland","GRU212",100,3100,38,19)));
		assertFalse(rGru.rispettaRichiesta(new Camion(1,"New Holland","GRU212",150,412,16)));
		assertFalse(rGru.rispettaRichiesta(null));
	}

	@Test
	public void testSetMinLunghezza() {
		rGru.setMinLunghezza(35);
		assertEquals(rGru.getMinLunghezza(),35);
	}
	@Test
	public void testSetMaxLunghezza() {
		rGru.setMaxLunghezza(45);
		assertEquals(rGru.getMaxLunghezza(),45);
	}
	@Test
	public void testSetMinAltezza() {
		rGru.setMinAltezza(25);
		assertEquals(rGru.getMinAltezza(),25);
	}
	@Test
	public void testSetMaxAltezza() {
		rGru.setMaxAltezza(35);
		assertEquals(rGru.getMaxAltezza(),35);
	}
	@Test
	public void testSetMinPortata() {
		rGru.setMinPortata(3012);
		assertEquals(rGru.getMinPortata(),3012);
	}
	@Test
	public void testSetMaxPortata() {
		rGru.setMaxPortata(4627);
		assertEquals(rGru.getMaxPortata(),4627);
	}

	@Test
	public void testSetMinAngoloRotazione() {
		rGru.setMinAngoloRotazione(61);
		assertEquals(rGru.getMinAngoloRotazione(),61);
	}
	@Test
	public void testSetMaxAngoloRotazione() {
		rGru.setMaxAngoloRotazione(261);
		assertEquals(rGru.getMaxAngoloRotazione(),261);
	}
	
	
	
	@Test
	public void testEquals(){
		assertTrue(rGru.equals(rGru));
		assertFalse(rGru.equals(null));
		assertTrue(rGru.equals(new RichiestaGru(30,40,20,30,3000,4000,90,120)));
		assertFalse(rGru.equals("stringa"));
		assertFalse(rGru.equals(new RichiestaGru(31,40,20,30,3000,4000,90,120)));
		assertFalse(rGru.equals(new RichiestaGru(30,39,20,30,3000,4000,90,120)));
		assertFalse(rGru.equals(new RichiestaGru(30,40,27,30,3000,4000,90,120)));
		assertFalse(rGru.equals(new RichiestaGru(30,40,20,15,3000,4000,90,120)));
		assertFalse(rGru.equals(new RichiestaGru(30,40,20,30,300,4000,90,120)));
		assertFalse(rGru.equals(new RichiestaGru(30,40,20,30,3000,4500,90,120)));
		assertFalse(rGru.equals(new RichiestaGru(30,40,20,30,3000,4000,190,120)));
		assertFalse(rGru.equals(new RichiestaGru(30,40,20,30,3000,4000,90,119)));
		}

	@Test
	public void testToString(){
		assertEquals(rGru.toString(),"Richiesta:Gru 30-40 20-30 3000-4000 90-120");
		rGru.setMinLunghezza(-1);
		assertEquals(rGru.toString(),"Richiesta:Gru *-40 20-30 3000-4000 90-120");
		rGru.setMinLunghezza(30);
		rGru.setMaxLunghezza(-1);
		assertEquals(rGru.toString(),"Richiesta:Gru 30-* 20-30 3000-4000 90-120");
		rGru.setMaxLunghezza(40);
		rGru.setMinAltezza(-1);
		assertEquals(rGru.toString(),"Richiesta:Gru 30-40 *-30 3000-4000 90-120");
		rGru.setMinAltezza(20);
		rGru.setMaxAltezza(-1);
		assertEquals(rGru.toString(),"Richiesta:Gru 30-40 20-* 3000-4000 90-120");
		rGru.setMaxAltezza(30);
		rGru.setMinPortata(-1);
		assertEquals(rGru.toString(),"Richiesta:Gru 30-40 20-30 *-4000 90-120");
		rGru.setMinPortata(3000);
		rGru.setMaxPortata(-1);
		assertEquals(rGru.toString(),"Richiesta:Gru 30-40 20-30 3000-* 90-120");
		rGru.setMaxPortata(4000);
		rGru.setMinAngoloRotazione(-1);
		assertEquals(rGru.toString(),"Richiesta:Gru 30-40 20-30 3000-4000 *-120");
		rGru.setMinAngoloRotazione(90);
		rGru.setMaxAngoloRotazione(-1);
		assertEquals(rGru.toString(),"Richiesta:Gru 30-40 20-30 3000-4000 90-*");
	}
	
	@Test
	public void testInConflitto(){
		RichiestaGru ric5=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaGru ric6=new RichiestaGru(7,12,7,12,7,12,7,12);
		RichiestaEscavatore ric7=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		assertTrue(ric5.inConflitto(ric6));
		assertTrue(ric6.inConflitto(ric5));
		ric6=new RichiestaGru(20,30,7,12,7,12,7,12);
		assertFalse(ric5.inConflitto(ric6));
		assertFalse(ric6.inConflitto(ric5));
		ric6=new RichiestaGru(7,12,20,30,7,12,7,12);
		assertFalse(ric5.inConflitto(ric6));
		assertFalse(ric6.inConflitto(ric5));
		ric6=new RichiestaGru(7,12,7,12,20,30,7,12);
		assertFalse(ric5.inConflitto(ric6));
		assertFalse(ric6.inConflitto(ric5));
		ric6=new RichiestaGru(7,12,7,12,7,12,20,30);
		assertFalse(ric5.inConflitto(ric6));
		assertFalse(ric6.inConflitto(ric5));
		assertFalse(ric5.inConflitto(ric7));
	}
}
