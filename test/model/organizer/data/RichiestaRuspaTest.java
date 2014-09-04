package model.organizer.data;

import static org.junit.Assert.*;





import org.junit.Test;

// 
/**
 *   Class AssociazioneTest.
 */
public class RichiestaRuspaTest {
	
	/**   a. */
	RichiestaRuspa rRuspa;

    /**
     * Instantiates a new associazione test.
     */
    public RichiestaRuspaTest(){
    	//int minCapacita, int maxCapacita, int minPortata,
		//int maxPortata, int minAltezza, int maxAltezza

    	rRuspa=new RichiestaRuspa(100,200,300,500,10,15);
    }
    
	/**
	 * Test associazione.
	 */
	@Test
	public void testRichiestaGruTest() {
		assertEquals(rRuspa.getMinAltezza(),10);
		assertEquals(rRuspa.getMaxAltezza(),15);
		assertEquals(rRuspa.getMinCapacita(),100);
		assertEquals(rRuspa.getMaxCapacita(),200);
		assertEquals(rRuspa.getMinPortata(),300);
		assertEquals(rRuspa.getMaxPortata(),500);	
	}

	@Test
	public void testRispettaRichiesta(){
		//int codice, String produttore, String Modello,int capacita,int portata,int altezza
		assertTrue(rRuspa.rispettaRichiesta(new Ruspa(1,"New Holland","GRU212",150,412,11)));
		assertFalse(rRuspa.rispettaRichiesta(new Camion(1,"New Holland","GRU212",99,412,11)));
		assertFalse(rRuspa.rispettaRichiesta(new Camion(1,"New Holland","GRU212",150,501,11)));
		assertFalse(rRuspa.rispettaRichiesta(new Camion(1,"New Holland","GRU212",150,412,16)));
	}

	@Test
	public void testSetMinAltezza() {
		rRuspa.setMinAltezza(35);
		assertEquals(rRuspa.getMinAltezza(),35);
	}
	@Test
	public void testSetMaxLunghezza() {
		rRuspa.setMaxAltezza(45);
		assertEquals(rRuspa.getMaxAltezza(),45);
	}
	@Test
	public void testSetMinCapacita() {
		rRuspa.setMinCapacita(25);
		assertEquals(rRuspa.getMinCapacita(),25);
	}
	@Test
	public void testSetMaxCapacita() {
		rRuspa.setMaxCapacita(35);
		assertEquals(rRuspa.getMaxCapacita(),35);
	}
	@Test
	public void testGetMinPortata() {
		rRuspa.setMinPortata(3012);
		assertEquals(rRuspa.getMinPortata(),3012);
	}
	@Test
	public void testGetMaxPortata() {
		rRuspa.setMaxPortata(4627);
		assertEquals(rRuspa.getMaxPortata(),4627);
	}

	
	
	
	@Test
	public void testEquals(){
		assertTrue(rRuspa.equals(rRuspa));
		assertFalse(rRuspa.equals(null));
		assertTrue(rRuspa.equals(new RichiestaRuspa(100,200,300,500,10,15)));
		assertFalse(rRuspa.equals("stringa"));
		assertFalse(rRuspa.equals(new RichiestaRuspa(101,200,300,500,10,15)));
		assertFalse(rRuspa.equals(new RichiestaRuspa(100,199,300,500,10,15)));
		assertFalse(rRuspa.equals(new RichiestaRuspa(100,200,310,500,10,15)));
		assertFalse(rRuspa.equals(new RichiestaRuspa(100,200,300,600,10,15)));
		assertFalse(rRuspa.equals(new RichiestaRuspa(100,200,300,500,11,15)));
		assertFalse(rRuspa.equals(new RichiestaRuspa(100,200,300,500,10,17)));
	}

	@Test
	public void testtoString(){
		System.out.println(rRuspa);
		assertEquals(rRuspa.toString(),"100-200 300-500 10-15");
	}
}
