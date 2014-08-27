package model.organizer.data;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;



import org.junit.Test;

// 
/**
 *   Class CantiereTest.
 */
public class CantiereTest {
	
	/**   c. */
	Cantiere c;
	
	/**
	 * Instantiates a new cantiere test.
	 */
	public CantiereTest(){
		c=new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31));
	}

	/**
	 * Test cantiere.
	 */
	@Test
	public void testCantiere() {
		assertEquals(c.getCodice(),11);
		assertEquals(c.getNomeCantiere(),"Ponte sullo stretto");
		assertEquals(c.getIndirizzo(),"Messina");
		assertEquals(c.getDataApertura(),new GregorianCalendar(2060,1,1));
		assertEquals(c.getDataChiusura(),new GregorianCalendar(2090,11,31));
	}

	/**
	 * Test set nome cantiere.
	 */
	@Test
	public void testSetNomeCantiere() {
		c.setNomeCantiere("Grande Opera");
		assertEquals(c.getNomeCantiere(),"Grande Opera");
	}

	/**
	 * Test set indirizzo.
	 */
	@Test
	public void testSetIndirizzo() {
		c.setIndirizzo("Reggio Calabria");
		assertEquals(c.getIndirizzo(),"Reggio Calabria");
	}

	/**
	 * Test set data apertura.
	 */
	@Test
	public void testSetDataApertura() {
		c.setDataApertura(new GregorianCalendar(2061,4,25));
		assertEquals(c.getDataApertura(),new GregorianCalendar(2061,4,25));
	}

	/**
	 * Test set data chiusura.
	 */
	@Test
	public void testSetDataChiusura() {
		c.setDataChiusura(new GregorianCalendar(2091,5,5));
		assertEquals(c.getDataChiusura(),new GregorianCalendar(2091,5,5));
	}

	/**
	 * Test set codice.
	 */
	@Test
	public void testSetCodice() {
		c.setCodice(17);
		assertEquals(c.getCodice(),17);
	}

	@Test
	public void testToString(){
		//GregorianCalendar parte da 00
		assertEquals(c.toString(), 11 + " Ponte sullo stretto Messina 2060-02-01 2090-12-31" );
	}
	
	@Test
	public void testEquals(){
		assertTrue(c.equals(c));
		assertFalse(c.equals(null));
		assertFalse(c.equals("Stringa"));
		assertTrue(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31))));
		assertFalse(c.equals(new Cantiere(12,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31))));
		assertFalse(c.equals(new Cantiere(11,"Ponte stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31))));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Reggio",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31))));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2061,1,1),new GregorianCalendar(2090,11,31))));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2091,11,31))));
	}
}
