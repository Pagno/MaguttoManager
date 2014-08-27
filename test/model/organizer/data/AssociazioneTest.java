package model.organizer.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;



import org.junit.Test;

// 
/**
 *   Class AssociazioneTest.
 */
public class AssociazioneTest {
	
	/**   a. */
	Associazione a;

    /**
     * Instantiates a new associazione test.
     */
    public AssociazioneTest(){
    	a=new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26));
    }
    
	/**
	 * Test associazione.
	 */
	@Test
	public void testAssociazione() {
		//a=new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26));
		assertEquals(a.getID(),5);
		assertEquals(a.getMacchina(),new Ruspa(4,"Caterpillar","Ruspone",100,100,100));
		assertEquals(a.getCantiere(),new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)));
		assertEquals(a.getDataInizio(),new GregorianCalendar(2014,5,23));
		assertEquals(a.getDataFine(),new GregorianCalendar(2014,5,26));
	}

	/**
	 * Test set macchina.
	 */
	@Test
	public void testSetMacchina() {
		a.setMacchina(new Camion(6,"yamaha","Ruspina",50,50,50) );
		assertEquals(a.getMacchina(),new Camion(6,"yamaha","Ruspina",50,50,50));
	}

	/**
	 * Test set cantiere.
	 */
	@Test
	public void testSetCantiere() {
		a.setCantiere(new Cantiere(7,"IPB","Osio Sotto",new GregorianCalendar(2019,1,22),new GregorianCalendar(2020,7,13)));
		assertEquals(a.getCantiere(),new Cantiere(7,"IPB","Osio Sotto",new GregorianCalendar(2019,1,22),new GregorianCalendar(2020,7,13)));
	}

	/**
	 * Test set data inizio.
	 */
	@Test
	public void testSetDataInizio() {
		a.setDataInizio(new GregorianCalendar(2018,5,25));
		assertEquals(a.getDataInizio(),new GregorianCalendar(2018,5,25));
	}

	/**
	 * Test set data fine.
	 */
	@Test
	public void testSetDataFine() {
		a.setDataFine(new GregorianCalendar(2021,6,2));
		assertEquals(a.getDataFine(),new GregorianCalendar(2021,6,2));
	}

	@Test
	public void testClone(){
		assertEquals(a,a.clone());
	}
	
	@Test
	public void testGetStrDataInizio(){
		//I mesi del GregorianCalendar partono da 0, quindi il mese 5 inserito all'inizio corrisponder� a Giugno.
		assertEquals(a.getStrDataInizio(),"2014-06-23");
	}
	
	@Test
	public void testGetStrDataFine(){
		//I mesi del GregorianCalendar partono da 0, quindi il mese 5 inserito all'inizio corrisponder� a Giugno.
		assertEquals(a.getStrDataFine(),"2014-06-26");
	}
	
	@Test
	public void testEquals(){
		assertTrue(a.equals(a));
		assertFalse(a.equals(null));
		assertTrue(a.equals(new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26))));
		assertFalse(a.equals("stringa"));
		assertFalse(a.equals(new Associazione(6, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26))));
		assertFalse(a.equals(new Associazione(5, new Ruspa(5,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26))));
		assertFalse(a.equals(new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(4,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26))));
		assertFalse(a.equals(new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2015,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26))));
		assertFalse(a.equals(new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2015,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26))));
		assertFalse(a.equals(new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2015,5,23),new GregorianCalendar(2014,5,26))));
		assertFalse(a.equals(new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2015,5,26))));
	}
	
	@Test
	public void testtoString(){
		assertEquals(a.toString(),5 + " " + 4 + " " + 3 + " 2014-06-23 2014-06-26");
	}
}
