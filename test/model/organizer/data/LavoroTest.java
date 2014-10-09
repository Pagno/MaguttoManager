package model.organizer.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

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
		cantiere.addLavoro(lavoro);
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

	@Test
	public void testSetCantiere() {
		fail("Not yet implemented");
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
	 * Test set Nome.
	 */
	@Test
	public void testSetNome() {
		lavoro.setNome("Fondamenta");
		assertEquals(lavoro.getNome(),"Fondamenta");
	}

	/**
	 * Test set Data Inizio.
	 */
	@Test
	public void testSetDataInizio() {
		lavoro.setDataInizio(new GregorianCalendar(2014, 10, 01));
		assertEquals(lavoro.getDataInizio(),new GregorianCalendar(2014, 10, 01));
	}

	@Test
	public void testGetStrDataInizio() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStrDataFine() {
		fail("Not yet implemented");
	}

	/**
	 * Test set Data Fine.
	 */
	@Test
	public void testSetDataFine() {
		lavoro.setDataFine(new GregorianCalendar(2014, 12, 01));
		assertEquals(lavoro.getDataFine(),new GregorianCalendar(2014, 12, 01));
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
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

	@Test
	public void testInserisciRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testCaricaRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testModificaRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasRichiestaInsoddisfatta() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminaRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testSvuotaRichieste() {
		fail("Not yet implemented");
	}

	@Test
	public void testSoddisfaRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testLiberaRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testLiberaMacchina() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsScoperto() {
		fail("Not yet implemented");
	}

	@Test
	public void testWhereScoperto() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetListaRichieste() {
		ArrayList<Richiesta>test=lavoro.getListaRichieste();
		assertTrue(test.isEmpty());
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		lavoro.caricaRichiesta(rc, 50, null);
		lavoro.caricaRichiesta(rc, 51, new Camion(3,"Yamaha","Camion",6,6,6));
		test=lavoro.getListaRichieste();
		assertFalse(test.isEmpty());
		assertEquals(test.size(),2);
		assertEquals(test.get(0),lavoro.getRichiesta(50));
		assertEquals(test.get(1),lavoro.getRichiesta(51));
		lavoro.inserisciRichiesta(rc);
		test=lavoro.getListaRichieste();
		assertEquals(test.size(),3);
		assertEquals(test.get(0),lavoro.getRichiesta(50));
		assertEquals(test.get(1),lavoro.getRichiesta(51));
		assertEquals(test.get(2),lavoro.getRichiesta(52));
	}

	@Test
	public void testGetPriorita() {
		assertEquals(lavoro.getPriorita(),cantiere.getPriorita());
		assertEquals(lavoro.getPriorita(),Priority.ALTA);
	}

	@Test
	public void testGetCodiceCantiere() {
		assertEquals(lavoro.getCodiceCantiere(),cantiere.getCodice());
		assertEquals(lavoro.getCodiceCantiere(),23);
	}

	@Test
	public void testGetRelatedWorks() {
		Lavoro l2=new Lavoro(6,"Fondamenta",cantiere, new GregorianCalendar(2015, 9, 01),new GregorianCalendar(2015, 11, 1));
		cantiere.addLavoro(l2);
		Lavoro l3=new Lavoro(7,"Muratura",cantiere, new GregorianCalendar(2016, 9, 01),new GregorianCalendar(2016, 11, 1));
		cantiere.addLavoro(l3);
		ArrayList<Lavoro>test=new ArrayList<Lavoro>();
		test.add(lavoro);
		test.add(l2);
		test.add(l3);
		assertEquals(lavoro.getRelatedWorks(),cantiere.getElencoLavori());
		assertEquals(lavoro.getRelatedWorks(),test);
	}

	@Test
	public void testGetDurata() {
		assertEquals(lavoro.getDurata(),61);
		lavoro.setDataInizio(new GregorianCalendar(2014,2,2));
		lavoro.setDataFine(new GregorianCalendar(2014,2,6));
		assertEquals(lavoro.getDurata(),4);
	}

}
