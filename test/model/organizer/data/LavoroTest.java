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
	Lavoro lavoro;
	Cantiere cantiere;
	/**
	 * Instantiates a new Lavoro test.
	 */
	public LavoroTest(){
		cantiere=new Cantiere(23,"Bottanuco","via Chiusa,18",new GregorianCalendar(2014, 9, 24),new GregorianCalendar(2015,7,12),Priority.ALTA);
		lavoro=new Lavoro(5,"Scavi",cantiere, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
	}

	/**
	 * Test Lavoro.
	 */
	@Test
	public void testLavoro() {
		assertEquals(lavoro.getCodice(),5);
		assertEquals(lavoro.getNome(),"Scavi");
		assertEquals(lavoro.getDataInizio(),new GregorianCalendar(2014, 9, 01));
		assertEquals(lavoro.getDataFine(),new GregorianCalendar(2014, 11, 1));
	}

	/**
	 * Test set Nome.
	 */
	@Test
	public void testSetNome() {
		lavoro.setNome("Fondamenta");
		assertEquals(lavoro.getNome(),"Fondamenta");
	}

	/**
	 * Test set Codice.
	 */
	@Test
	public void testSetCodice() {
		lavoro.setCodice(8);
		assertEquals(lavoro.getCodice(),8);
	}

	/**
	 * Test set Data Inizio.
	 */
	@Test
	public void testSetDataInizio() {
		lavoro.setDataInizio(new GregorianCalendar(2014, 10, 01));
		assertEquals(lavoro.getDataInizio(),new GregorianCalendar(2014, 10, 01));
	}
	/**
	 * Test set Data Fine.
	 */
	@Test
	public void testSetDataFine() {
		lavoro.setDataFine(new GregorianCalendar(2014, 12, 01));
		assertEquals(lavoro.getDataFine(),new GregorianCalendar(2014, 12, 01));
	}
	/**
	 * Test set produttore.
	 */
	
	public void testEquals() {
		assertTrue(lavoro.equals(lavoro));
		assertFalse(lavoro.equals(new Ruspa(5,"Yamaha","Prova",100,99,98)));
		assertFalse(lavoro.equals("Stringa"));
		assertFalse(lavoro.equals(null));
		assertTrue(lavoro.equals(new Camion(5,"Yamaha","Prova",100,99,98)));
		assertFalse(lavoro.equals(new Camion(6,"Yamaha","Prova",100,99,98)));
		assertFalse(lavoro.equals(new Camion(5,"Yamaha","Test",100,99,98)));
		assertFalse(lavoro.equals(new Camion(5,"Caterpillar","Prova",100,99,98)));
		assertFalse(lavoro.equals(new Camion(5,"Yamaha","Prova",101,99,98)));
		assertFalse(lavoro.equals(new Camion(5,"Yamaha","Prova",100,100,98)));
		assertFalse(lavoro.equals(new Camion(5,"Yamaha","Prova",100,99,99)));
	}

}
