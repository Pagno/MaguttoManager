package model.organizer.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;

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
		c=new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priority.MEDIA);
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
		assertEquals(c.getPriorita(),Priority.MEDIA);
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
	
	/**
	 * Test set priorita.
	 */
	@Test
	public void testSetPriorita() {
		c.setPriorita(Priority.ALTA);
		assertEquals(c.getPriorita(),Priority.ALTA);
	}

	@Test
	public void testToString(){
		//GregorianCalendar parte da 00
		assertEquals(c.toString(), 11 + " Ponte sullo stretto Messina 2060-02-01 2090-12-31 - Priorità Media" );
	}
	
	@Test
	public void testEquals(){
		assertTrue(c.equals(c));
		assertFalse(c.equals(null));
		assertFalse(c.equals("Stringa"));
		assertTrue(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priority.MEDIA)));
		assertFalse(c.equals(new Cantiere(12,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priority.MEDIA)));
		assertFalse(c.equals(new Cantiere(11,"Ponte stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priority.MEDIA)));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Reggio",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priority.MEDIA)));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2061,1,1),new GregorianCalendar(2090,11,31),Priority.MEDIA)));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2091,11,31),Priority.MEDIA)));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priority.BASSA)));
	}
	
	@Test
	public void testAddLavoro(){
		ArrayList<Lavoro>lista=c.getElencoLavori();
		assertTrue(lista.equals(new ArrayList<Lavoro>()));
		assertEquals(lista.size(),0);
		Lavoro l1=new Lavoro(1,"Scavo",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31));
		c.addLavoro(l1);
		lista=c.getElencoLavori();
		assertEquals(lista.size(),1);
		assertTrue(lista.contains(l1));
		Lavoro l2=new Lavoro(2,"Costruzione",new GregorianCalendar(2062,1,1),new GregorianCalendar(2088,11,31));
		c.addLavoro(l2);
		lista=c.getElencoLavori();
		assertEquals(lista.size(),2);
		assertTrue(lista.contains(l1));
		assertTrue(lista.contains(l2));
	}
	
	@Test
	public void testRimuoviLavoro(){
		ArrayList<Lavoro>lista=c.getElencoLavori();
		assertTrue(lista.equals(new ArrayList<Lavoro>()));
		assertEquals(lista.size(),0);
		Lavoro l1=new Lavoro(1,"Scavo",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31));
		c.addLavoro(l1);
		Lavoro l2=new Lavoro(2,"Costruzione",new GregorianCalendar(2062,1,1),new GregorianCalendar(2088,11,31));
		c.addLavoro(l2);
		Lavoro l3=new Lavoro(3,"Gettata",new GregorianCalendar(2064,1,1),new GregorianCalendar(2070,11,31));
		c.addLavoro(l3);
		lista=c.getElencoLavori();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(l1));
		assertTrue(lista.contains(l2));
		assertTrue(lista.contains(l3));
		c.rimuoviLavoro(2);
		assertEquals(lista.size(),2);
		assertTrue(lista.contains(l1));
		assertFalse(lista.contains(l2));
		assertTrue(lista.contains(l3));
	}
}
