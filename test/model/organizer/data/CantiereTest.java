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
		c=new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priorita.MEDIA);
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
		assertEquals(c.getPriorita(),Priorita.MEDIA);
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
		c.setPriorita(Priorita.ALTA);
		assertEquals(c.getPriorita(),Priorita.ALTA);
	}

	@Test
	public void testToString(){
		//GregorianCalendar parte da 00
		assertEquals(c.toString(), 11 + " Ponte sullo stretto Messina 2060-02-01 2090-12-31 - Priorit� Media" );
		c.setPriorita(Priorita.ALTA);
		assertEquals(c.toString(), 11 + " Ponte sullo stretto Messina 2060-02-01 2090-12-31 - Priorit� Alta" );
		c.setPriorita(Priorita.BASSA);
		assertEquals(c.toString(), 11 + " Ponte sullo stretto Messina 2060-02-01 2090-12-31 - Priorit� Bassa" );
	}
	
	@Test
	public void testEquals(){
		assertTrue(c.equals(c));
		assertFalse(c.equals(null));
		assertFalse(c.equals("Stringa"));
		assertTrue(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priorita.MEDIA)));
		assertFalse(c.equals(new Cantiere(12,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priorita.MEDIA)));
		assertFalse(c.equals(new Cantiere(11,"Ponte stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priorita.MEDIA)));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Reggio",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priorita.MEDIA)));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2061,1,1),new GregorianCalendar(2090,11,31),Priorita.MEDIA)));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2091,11,31),Priorita.MEDIA)));
		assertFalse(c.equals(new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priorita.BASSA)));
		Cantiere test=new Cantiere(11,"Ponte sullo stretto","Messina",null,new GregorianCalendar(2090,11,31),Priorita.MEDIA);
		Cantiere test2=new Cantiere(11,"Ponte sullo stretto","Messina",null,new GregorianCalendar(2090,11,31),Priorita.MEDIA);
		assertFalse(test.equals(c));
		assertTrue(test.equals(test2));
		assertFalse(c.equals(test));
		test=new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),null,Priorita.MEDIA);
		test2.setDataApertura(new GregorianCalendar(2060,1,1));
		test2.setDataChiusura(null);
		assertFalse(test.equals(c));
		assertTrue(test.equals(test2));
		assertFalse(c.equals(test));
		test=new Cantiere(11,"Ponte sullo stretto",null,new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priorita.MEDIA);
		test2.setDataChiusura(new GregorianCalendar(2090,11,31));
		test2.setIndirizzo(null);
		assertFalse(test.equals(c));
		assertTrue(test.equals(test2));
		assertFalse(c.equals(test));
		test=new Cantiere(11,null,"Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31),Priorita.MEDIA);
		test2.setIndirizzo("Messina");
		test2.setNomeCantiere(null);
		assertFalse(test.equals(c));
		assertTrue(test.equals(test2));
		assertFalse(c.equals(test));
		test.aggiungiLavoro(new Lavoro(1,"Scavo",c,new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31)));
		assertFalse(test.equals(test2));
		assertFalse(test2.equals(test));
		test2.aggiungiLavoro(new Lavoro(1,"Scavo",c,new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31)));
		assertTrue(test.equals(test2));
		assertTrue(test2.equals(test));
	}
	
	@Test
	public void testaggiungiLavoro(){
		ArrayList<Lavoro>lista=c.getElencoLavori();
		assertTrue(lista.equals(new ArrayList<Lavoro>()));
		assertEquals(lista.size(),0);
		Lavoro l1=new Lavoro(1,"Scavo",c,new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31));
		c.aggiungiLavoro(l1);
		lista=c.getElencoLavori();
		assertEquals(lista.size(),1);
		assertTrue(lista.contains(l1));
		Lavoro l2=new Lavoro(2,"Costruzione",c,new GregorianCalendar(2062,1,1),new GregorianCalendar(2088,11,31));
		c.aggiungiLavoro(l2);
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
		Lavoro l1=new Lavoro(1,"Scavo",c,new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31));
		c.aggiungiLavoro(l1);
		Lavoro l2=new Lavoro(2,"Costruzione",c,new GregorianCalendar(2062,1,1),new GregorianCalendar(2088,11,31));
		c.aggiungiLavoro(l2);
		Lavoro l3=new Lavoro(3,"Gettata",c,new GregorianCalendar(2064,1,1),new GregorianCalendar(2070,11,31));
		c.aggiungiLavoro(l3);
		lista=c.getElencoLavori();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(l1));
		assertTrue(lista.contains(l2));
		assertTrue(lista.contains(l3));
		assertFalse(c.rimuoviLavoro(99));
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(l1));
		assertTrue(lista.contains(l2));
		assertTrue(lista.contains(l3));
		assertTrue(c.rimuoviLavoro(2));
		assertEquals(lista.size(),2);
		assertTrue(lista.contains(l1));
		assertFalse(lista.contains(l2));
		assertTrue(lista.contains(l3));
	}
	
	@Test
	public void testSvuotaLavori(){
		ArrayList<Lavoro>lista=c.getElencoLavori();
		assertTrue(lista.equals(new ArrayList<Lavoro>()));
		assertEquals(lista.size(),0);
		c.svuotaLavori();
		lista=c.getElencoLavori();
		assertTrue(lista.equals(new ArrayList<Lavoro>()));
		assertEquals(lista.size(),0);
		Lavoro l1=new Lavoro(1,"Scavo",c,new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31));
		c.aggiungiLavoro(l1);
		Lavoro l2=new Lavoro(2,"Costruzione",c,new GregorianCalendar(2062,1,1),new GregorianCalendar(2088,11,31));
		c.aggiungiLavoro(l2);
		Lavoro l3=new Lavoro(3,"Gettata",c,new GregorianCalendar(2064,1,1),new GregorianCalendar(2070,11,31));
		c.aggiungiLavoro(l3);
		lista=c.getElencoLavori();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(l1));
		assertTrue(lista.contains(l2));
		assertTrue(lista.contains(l3));
		c.svuotaLavori();
		lista=c.getElencoLavori();
		assertTrue(lista.isEmpty());
	}
	
	@Test
	public void testHasLavoro(){
		ArrayList<Lavoro>lista=c.getElencoLavori();
		assertTrue(lista.equals(new ArrayList<Lavoro>()));
		assertEquals(lista.size(),0);
		Lavoro l1=new Lavoro(1,"Scavo",c,new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31));
		c.aggiungiLavoro(l1);
		Lavoro l2=new Lavoro(2,"Costruzione",c,new GregorianCalendar(2062,1,1),new GregorianCalendar(2088,11,31));
		c.aggiungiLavoro(l2);
		Lavoro l3=new Lavoro(3,"Gettata",c,new GregorianCalendar(2064,1,1),new GregorianCalendar(2070,11,31));
		c.aggiungiLavoro(l3);
		lista=c.getElencoLavori();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(l1));
		assertTrue(lista.contains(l2));
		assertTrue(lista.contains(l3));
		assertTrue(c.hasLavoro(1));
		assertTrue(c.hasLavoro(2));
		assertTrue(c.hasLavoro(3));
		assertFalse(c.hasLavoro(99));
		assertTrue(c.rimuoviLavoro(2));
		assertTrue(c.hasLavoro(1));
		assertFalse(c.hasLavoro(2));
		assertTrue(c.hasLavoro(3));
		assertFalse(c.hasLavoro(99));
		
	}
	
	@Test
	public void testGetLavoro(){
		ArrayList<Lavoro>lista=c.getElencoLavori();
		assertTrue(lista.equals(new ArrayList<Lavoro>()));
		assertEquals(lista.size(),0);
		Lavoro l1=new Lavoro(1,"Scavo",c,new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31));
		c.aggiungiLavoro(l1);
		Lavoro l2=new Lavoro(2,"Costruzione",c,new GregorianCalendar(2062,1,1),new GregorianCalendar(2088,11,31));
		c.aggiungiLavoro(l2);
		Lavoro l3=new Lavoro(3,"Gettata",c,new GregorianCalendar(2064,1,1),new GregorianCalendar(2070,11,31));
		c.aggiungiLavoro(l3);
		lista=c.getElencoLavori();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(l1));
		assertTrue(lista.contains(l2));
		assertTrue(lista.contains(l3));
		Lavoro test=c.getLavoro(2);
		assertEquals(l2,test);
		test=c.getLavoro(3);
		assertEquals(l3,test);
		test=c.getLavoro(99);
		assertEquals(null,test);
		test=c.getLavoro(1);
		assertEquals(l1,test);
	}
	
	@Test
	public void testIsScoperto(){
		Richiesta.initCodice();
		ArrayList<Lavoro>lista=c.getElencoLavori();
		assertTrue(lista.equals(new ArrayList<Lavoro>()));
		assertEquals(lista.size(),0);
		Lavoro l1=new Lavoro(1,"Scavo",c,new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,11,31));
		c.aggiungiLavoro(l1);
		Lavoro l2=new Lavoro(2,"Costruzione",c,new GregorianCalendar(2062,1,1),new GregorianCalendar(2088,11,31));
		c.aggiungiLavoro(l2);
		Lavoro l3=new Lavoro(3,"Gettata",c,new GregorianCalendar(2064,1,1),new GregorianCalendar(2070,11,31));
		c.aggiungiLavoro(l3);
		assertFalse(c.isScoperto());
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		l2.inserisciRichiesta(rc);
		assertTrue(c.isScoperto());
		l2.soddisfaRichiesta(1, new Camion(1,"Yamaha","Camion",7,7,7));
		assertFalse(c.isScoperto());
		l3.inserisciRichiesta(rc);
		assertTrue(c.isScoperto());
	}
	
	@Test
	public void testGetPrioritaString(){
		assertEquals(c.getPrioritaString(),"MEDIA");
		c.setPriorita(Priorita.ALTA);
		assertEquals(c.getPrioritaString(),"ALTA");
		c.setPriorita(Priorita.BASSA);
		assertEquals(c.getPrioritaString(),"BASSA");
	}
}
