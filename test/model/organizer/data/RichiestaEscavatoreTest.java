package model.organizer.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;




import org.junit.Test;

// 
/**
 *   Class AssociazioneTest.
 */
public class RichiestaEscavatoreTest {
	
	/**   a. */
	RichiestaEscavatore rEscavatore;

    /**
     * Instantiates a new associazione test.
     */
    public RichiestaEscavatoreTest(){
    	/*
    	 * (int minCapacita, int maxCapacita,
			int minPortata, int maxPortata, int minAltezza, int maxAltezza,
			int minProfondita, int maxProfondita
    	 * 
    	 * */
    	rEscavatore=new RichiestaEscavatore(30,40,120,130,10,15,5,8);
    }
    
	/**
	 * Test associazione.
	 */
	@Test
	public void testRichiestaGruTest() {
		assertEquals(rEscavatore.getMinCapacita(),30);
		assertEquals(rEscavatore.getMaxCapacita(),40);
		assertEquals(rEscavatore.getMinPortata(),120);
		assertEquals(rEscavatore.getMaxPortata(),130);
		assertEquals(rEscavatore.getMinAltezza(),10);
		assertEquals(rEscavatore.getMaxAltezza(),15);
		assertEquals(rEscavatore.getMinProfondita(),5);
		assertEquals(rEscavatore.getMaxProfondita(),8);	
	}

	@Test
	public void testRispettaRichiesta(){
		//int codice, String produttore, String Modello,
		//int capacita,int portata,int altezza,int profondita
		assertTrue(rEscavatore.rispettaRichiesta(new Escavatore(1,"New Holland","GRU212",35,125,13,7)));
		assertFalse(rEscavatore.rispettaRichiesta(new Escavatore(1,"New Holland","GRU212",89,3100,38,24)));
		assertFalse(rEscavatore.rispettaRichiesta(new Escavatore(1,"New Holland","GRU212",100,4001,38,24)));
		assertFalse(rEscavatore.rispettaRichiesta(new Escavatore(1,"New Holland","GRU212",100,3100,29,24)));
		assertFalse(rEscavatore.rispettaRichiesta(new Escavatore(1,"New Holland","GRU212",100,3100,38,19)));
	}

	@Test
	public void testSetMinCapacita() {
		rEscavatore.setMinCapacita(35);
		assertEquals(rEscavatore.getMinCapacita(),35);
	}
	@Test
	public void testSetMaxCapacita() {
		rEscavatore.setMaxCapacita(45);
		assertEquals(rEscavatore.getMaxCapacita(),45);
	}
	@Test
	public void testSetMinAltezza() {
		rEscavatore.setMinAltezza(25);
		assertEquals(rEscavatore.getMinAltezza(),25);
	}
	@Test
	public void testSetMaxAltezza() {
		rEscavatore.setMaxAltezza(35);
		assertEquals(rEscavatore.getMaxAltezza(),35);
	}
	@Test
	public void testSetMinPortata() {
		rEscavatore.setMinPortata(3012);
		assertEquals(rEscavatore.getMinPortata(),3012);
	}
	@Test
	public void testSetMaxPortata() {
		rEscavatore.setMaxPortata(4627);
		assertEquals(rEscavatore.getMaxPortata(),4627);
	}

	@Test
	public void testSetMinProfondita() {
		rEscavatore.setMinProfondita(61);
		assertEquals(rEscavatore.getMinProfondita(),61);
	}
	@Test
	public void testSetMaxProfondita() {
		rEscavatore.setMaxProfondita(261);
		assertEquals(rEscavatore.getMaxProfondita(),261);
	}
	
	
	
	@Test
	public void testEquals(){
		assertTrue(rEscavatore.equals(rEscavatore));
		assertFalse(rEscavatore.equals(null));
		assertTrue(rEscavatore.equals(new RichiestaEscavatore(30,40,120,130,10,15,5,8)));
		assertFalse(rEscavatore.equals("stringa"));
		assertFalse(rEscavatore.equals(new RichiestaEscavatore(29,40,120,130,10,15,5,8)));
		assertFalse(rEscavatore.equals(new RichiestaEscavatore(30,41,120,130,10,15,5,8)));
		assertFalse(rEscavatore.equals(new RichiestaEscavatore(30,40,110,130,10,15,5,8)));
		assertFalse(rEscavatore.equals(new RichiestaEscavatore(30,40,120,129,10,15,5,8)));
		assertFalse(rEscavatore.equals(new RichiestaEscavatore(30,40,120,130,9,15,5,8)));
		assertFalse(rEscavatore.equals(new RichiestaEscavatore(30,40,120,130,10,16,5,8)));
		assertFalse(rEscavatore.equals(new RichiestaEscavatore(30,40,120,130,10,15,3,8)));
		assertFalse(rEscavatore.equals(new RichiestaEscavatore(30,40,120,130,10,15,5,9)));
		}

	@Test
	public void testtoString(){
		System.out.println(rEscavatore.toString());
		assertEquals(rEscavatore.toString(),"30-40 120-130 10-15 5-8");
	}
}
