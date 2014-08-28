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
    	a=new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Lavoro(3,"Scavi",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)));
    }
    
	/**
	 * Test associazione.
	 */
	@Test
	public void testAssociazione() {
		//a=new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26));
		assertEquals(a.getID(),5);
		assertEquals(a.getLavoro(),new Lavoro(3,"Scavi",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)));
		assertEquals(a.getMacchina(),new Ruspa(4,"Caterpillar","Ruspone",100,100,100));
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
	public void testSetLavoro() {
		a.setLavoro(new Lavoro(4,"gettata",new GregorianCalendar(2016,5,22),new GregorianCalendar(2017,5,25)));
		assertEquals(a.getLavoro(),new Lavoro(4,"gettata",new GregorianCalendar(2016,5,22),new GregorianCalendar(2017,5,25)));
	}


	@Test
	public void testClone(){
		assertEquals(a,a.clone());
	}
	
	
	@Test
	public void testEquals(){
		assertTrue(a.equals(a));
		assertFalse(a.equals(null));
		assertTrue(a.equals(new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Lavoro(3,"Scavi",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)))));
		assertFalse(a.equals("stringa"));
		assertFalse(a.equals(new Associazione(6, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Lavoro(3,"Scavi",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)))));
		assertFalse(a.equals(new Associazione(5, new Ruspa(5,"Caterpillar","Ruspone",100,100,100), new Lavoro(3,"Scavi",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)))));
		assertFalse(a.equals(new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Lavoro(4,"Scavi",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)))));
		assertFalse(a.equals(new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Lavoro(3,"Scavi",new GregorianCalendar(2015,5,22),new GregorianCalendar(2014,5,25)))));
		assertFalse(a.equals(new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Lavoro(3,"Scavi",new GregorianCalendar(2014,5,22),new GregorianCalendar(2015,5,25)))));
	}
	
	@Test
	public void testtoString(){
		assertEquals(a.toString(),5 + " " + 4 + " " + 3);
	}
}
