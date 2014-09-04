package model.organizer.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

// 
/**
 *   Class CamionTest.
 */
public class LavoroTest {
	
	/**   c. */
	Lavoro c;
	
	/**
	 * Instantiates a new Lavoro test.
	 */
	public LavoroTest(){
		c=new Lavoro(5,"Scavi",new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
	}

	/**
	 * Test Lavoro.
	 */
	@Test
	public void testLavoro() {
		assertEquals(c.getCodice(),5);
		assertEquals(c.getNome(),"Scavi");
		assertEquals(c.getDataInizio(),new GregorianCalendar(2014, 9, 01));
		assertEquals(c.getDataFine(),new GregorianCalendar(2014, 11, 1));
	}

	/**
	 * Test set Nome.
	 */
	@Test
	public void testSetNome() {
		c.setNome("Fondamenta");
		assertEquals(c.getNome(),"Fondamenta");
	}

	/**
	 * Test set Codice.
	 */
	@Test
	public void testSetCodice() {
		c.setCodice(8);
		assertEquals(c.getCodice(),8);
	}

	/**
	 * Test set Data Inizio.
	 */
	@Test
	public void testSetDataInizio() {
		c.setDataInizio(new GregorianCalendar(2014, 10, 01));
		assertEquals(c.getDataInizio(),new GregorianCalendar(2014, 10, 01));
	}
	/**
	 * Test set Data Fine.
	 */
	@Test
	public void testSetDataFine() {
		c.setDataFine(new GregorianCalendar(2014, 12, 01));
		assertEquals(c.getDataFine(),new GregorianCalendar(2014, 12, 01));
	}
	/**
	 * Test set produttore.
	 */
	
	public void testEquals() {
		assertTrue(c.equals(c));
		assertFalse(c.equals(new Ruspa(5,"Yamaha","Prova",100,99,98)));
		assertFalse(c.equals("Stringa"));
		assertFalse(c.equals(null));
		assertTrue(c.equals(new Camion(5,"Yamaha","Prova",100,99,98)));
		assertFalse(c.equals(new Camion(6,"Yamaha","Prova",100,99,98)));
		assertFalse(c.equals(new Camion(5,"Yamaha","Test",100,99,98)));
		assertFalse(c.equals(new Camion(5,"Caterpillar","Prova",100,99,98)));
		assertFalse(c.equals(new Camion(5,"Yamaha","Prova",101,99,98)));
		assertFalse(c.equals(new Camion(5,"Yamaha","Prova",100,100,98)));
		assertFalse(c.equals(new Camion(5,"Yamaha","Prova",100,99,99)));
	}

}
