package model.organizer.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.ModelCantiere;

import org.junit.Test;

public class LavoroTest {
	/**   c. */
	Lavoro lavoro;
	Cantiere cantiere;
	/**
	 * Instantiates a new Lavoro test.
	 */
	public LavoroTest(){
		cantiere=new Cantiere(23,"Bottanuco","via Chiusa,18",new GregorianCalendar(2014, 9, 24),new GregorianCalendar(2015,7,12),Priorita.ALTA);
		lavoro=new Lavoro(5,"Scavi",cantiere, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		cantiere.aggiungiLavoro(lavoro);
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
		lavoro.setCantiere(new Cantiere(50,"Bergamo","Mura",new GregorianCalendar(2015, 5, 5),new GregorianCalendar(2016,9,24),Priorita.MEDIA));
		assertEquals(lavoro.getCantiere(),new Cantiere(50,"Bergamo","Mura",new GregorianCalendar(2015, 5, 5),new GregorianCalendar(2016,9,24),Priorita.MEDIA));
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
		String test=lavoro.getStrDataInizio();
		assertEquals(test,"2014-10-01");
	}

	@Test
	public void testGetStrDataFine() {
		String test=lavoro.getStrDataFine();
		assertEquals(test,"2014-12-01");
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
		String test=lavoro.toString();
		assertEquals(test,"5 Scavi 2014-10-01 2014-12-01");
	}

	@Test
	public void testEquals() {
		Lavoro l=new Lavoro(5,"Scavi",cantiere, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		assertTrue(lavoro.equals(lavoro));
		assertTrue(lavoro.equals(l));
		l.setCodice(6);
		assertFalse(lavoro.equals("Stringa"));
		assertFalse(lavoro.equals(null));
		l.setCantiere(null);
		assertFalse(lavoro.equals(l));
		assertFalse(l.equals(lavoro));
		lavoro.setCantiere(null);
		assertFalse(l.equals(lavoro));
		l.setCodice(5);
		assertTrue(l.equals(lavoro));
		assertTrue(lavoro.equals(l));
		l.setDataInizio(null);
		assertFalse(l.equals(lavoro));
		assertFalse(lavoro.equals(l));
		lavoro.setDataInizio(null);
		assertTrue(lavoro.equals(l));
		assertTrue(l.equals(lavoro));
		l.setDataFine(null);
		assertFalse(l.equals(lavoro));
		assertFalse(lavoro.equals(l));
		lavoro.setDataFine(null);
		assertTrue(lavoro.equals(l));
		assertTrue(l.equals(lavoro));
		l.setNome(null);
		assertFalse(l.equals(lavoro));
		assertFalse(lavoro.equals(l));
		lavoro.setNome(null);
		assertTrue(lavoro.equals(l));
		assertTrue(l.equals(lavoro));
		lavoro.caricaRichiesta(new RichiestaCamion(5, 10, 5, 10, 5, 10),1,null);
		assertFalse(l.equals(lavoro));
		assertFalse(lavoro.equals(l));
		
	}

	@Test
	public void testAggiungiRichiesta() {
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		int code=lavoro.aggiungiRichiesta(rc);
		Richiesta r1=lavoro.getRichiesta(1);
		assertEquals(r1.getMacchina(),null);
		assertEquals(r1.getCaratteristiche(),rc);
		assertEquals(r1.getCodice(),1);
		assertEquals(r1.getLavoro(),lavoro);
		assertEquals(code,1);
	}

	@Test
	public void testCaricaRichiesta() {
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Camion m=new Camion(3,"Yamaha","Camion",6,6,6);
		lavoro.caricaRichiesta(rc, 50, null);
		Richiesta r1=lavoro.getRichiesta(50);
		assertEquals(r1.getMacchina(),null);
		assertEquals(r1.getCaratteristiche(),rc);
		assertEquals(r1.getCodice(),50);
		assertEquals(r1.getLavoro(),lavoro);
		lavoro.caricaRichiesta(rc, 51, m);
		r1=lavoro.getRichiesta(51);
		assertEquals(r1.getMacchina(),m);
		assertEquals(r1.getCaratteristiche(),rc);
		assertEquals(r1.getCodice(),51);
		assertEquals(r1.getLavoro(),lavoro);
		lavoro.caricaRichiesta(rc, 52, new Ruspa(99,"yamaha","Ruspa",1,2,3));
		r1=lavoro.getRichiesta(52);
		assertEquals(r1.getMacchina(),null);
		assertEquals(r1.getCaratteristiche(),rc);
		assertEquals(r1.getCodice(),52);
		assertEquals(r1.getLavoro(),lavoro);
	}

	@Test
	public void testModificaRichiesta() {
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Camion m=new Camion(3,"Yamaha","Camion",6,6,6);
		lavoro.caricaRichiesta(rc, 50, null);
		lavoro.caricaRichiesta(rc, 51, m);
		lavoro.aggiungiRichiesta(rc);
		lavoro.aggiungiRichiesta(rc);
		assertEquals(lavoro.getRichiesta(50).getCaratteristiche(),rc);
		assertEquals(lavoro.getRichiesta(51).getCaratteristiche(),rc);
		assertEquals(lavoro.getRichiesta(52).getCaratteristiche(),rc);
		assertEquals(lavoro.getRichiesta(53).getCaratteristiche(),rc);
	}

	@Test
	public void testHasRichiesta() {
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Camion m=new Camion(3,"Yamaha","Camion",6,6,6);
		lavoro.caricaRichiesta(rc, 50, null);
		lavoro.caricaRichiesta(rc, 51, m);
		lavoro.aggiungiRichiesta(rc);
		lavoro.aggiungiRichiesta(rc);
		assertTrue(lavoro.hasRichiesta(50));
		assertTrue(lavoro.hasRichiesta(51));
		assertTrue(lavoro.hasRichiesta(52));
		assertTrue(lavoro.hasRichiesta(53));
		assertFalse(lavoro.hasRichiesta(99));
	}

	@Test
	public void testGetRichiesta() {
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Camion m=new Camion(3,"Yamaha","Camion",6,6,6);
		lavoro.caricaRichiesta(rc, 50, null);
		lavoro.caricaRichiesta(rc, 51, m);
		lavoro.aggiungiRichiesta(rc);
		lavoro.aggiungiRichiesta(rc);
		Richiesta r1=lavoro.getRichiesta(50);
		assertEquals(r1.getMacchina(),null);
		assertEquals(r1.getCaratteristiche(),rc);
		assertEquals(r1.getCodice(),50);
		assertEquals(r1.getLavoro(),lavoro);
		r1=lavoro.getRichiesta(51);
		assertEquals(r1.getMacchina(),m);
		assertEquals(r1.getCaratteristiche(),rc);
		assertEquals(r1.getCodice(),51);
		assertEquals(r1.getLavoro(),lavoro);
		r1=lavoro.getRichiesta(52);
		assertEquals(r1.getMacchina(),null);
		assertEquals(r1.getCaratteristiche(),rc);
		assertEquals(r1.getCodice(),52);
		assertEquals(r1.getLavoro(),lavoro);
		r1=lavoro.getRichiesta(53);
		assertEquals(r1.getMacchina(),null);
		assertEquals(r1.getCaratteristiche(),rc);
		assertEquals(r1.getCodice(),53);
		assertEquals(r1.getLavoro(),lavoro);
		r1=lavoro.getRichiesta(99);
		assertEquals(r1,null);
	}

	@Test
	public void testEliminaRichiesta() {
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Camion m=new Camion(3,"Yamaha","Camion",6,6,6);
		lavoro.caricaRichiesta(rc, 50, null);
		lavoro.caricaRichiesta(rc, 51, m);
		lavoro.aggiungiRichiesta(rc);
		lavoro.aggiungiRichiesta(rc);
		assertTrue(lavoro.hasRichiesta(50));
		assertTrue(lavoro.hasRichiesta(51));
		assertTrue(lavoro.hasRichiesta(52));
		assertTrue(lavoro.hasRichiesta(53));
		
		assertTrue(lavoro.eliminaRichiesta(51));
		assertTrue(lavoro.hasRichiesta(50));
		assertFalse(lavoro.hasRichiesta(51));
		assertTrue(lavoro.hasRichiesta(52));
		assertTrue(lavoro.hasRichiesta(53));
		
		assertFalse(lavoro.eliminaRichiesta(51));
		assertTrue(lavoro.hasRichiesta(50));
		assertFalse(lavoro.hasRichiesta(51));
		assertTrue(lavoro.hasRichiesta(52));
		assertTrue(lavoro.hasRichiesta(53));
		
		assertTrue(lavoro.eliminaRichiesta(50));
		assertFalse(lavoro.hasRichiesta(50));
		assertFalse(lavoro.hasRichiesta(51));
		assertTrue(lavoro.hasRichiesta(52));
		assertTrue(lavoro.hasRichiesta(53));
		
		assertFalse(lavoro.eliminaRichiesta(99));
		assertFalse(lavoro.hasRichiesta(50));
		assertFalse(lavoro.hasRichiesta(51));
		assertTrue(lavoro.hasRichiesta(52));
		assertTrue(lavoro.hasRichiesta(53));
		
		assertTrue(lavoro.eliminaRichiesta(52));
		assertFalse(lavoro.hasRichiesta(50));
		assertFalse(lavoro.hasRichiesta(51));
		assertFalse(lavoro.hasRichiesta(52));
		assertTrue(lavoro.hasRichiesta(53));
		
		assertTrue(lavoro.eliminaRichiesta(53));
		assertFalse(lavoro.hasRichiesta(50));
		assertFalse(lavoro.hasRichiesta(51));
		assertFalse(lavoro.hasRichiesta(52));
		assertFalse(lavoro.hasRichiesta(53));
	}

	@Test
	public void testSvuotaRichieste() {
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Camion m=new Camion(3,"Yamaha","Camion",6,6,6);
		lavoro.caricaRichiesta(rc, 50, null);
		lavoro.caricaRichiesta(rc, 51, m);
		lavoro.aggiungiRichiesta(rc);
		lavoro.aggiungiRichiesta(rc);
		ArrayList<Richiesta>test=lavoro.getListaRichieste();
		assertFalse(test.isEmpty());
		assertEquals(test.size(),4);
		lavoro.svuotaRichieste();
		test=lavoro.getListaRichieste();
		assertTrue(test.isEmpty());
	}

	@Test
	public void testSoddisfaRichiesta() {
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Camion m=new Camion(3,"Yamaha","Camion",6,6,6);
		Camion c=new Camion(5,"Yamaha","Camion grande",9,9,9);
		lavoro.caricaRichiesta(rc, 50, null);
		lavoro.caricaRichiesta(rc, 51, m);
		lavoro.aggiungiRichiesta(rc);
		lavoro.aggiungiRichiesta(rc);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),null);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),null);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),null);
		lavoro.soddisfaRichiesta(52, m);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),null);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),null);
		lavoro.soddisfaRichiesta(53, c);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),null);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),c);
		lavoro.soddisfaRichiesta(50, m);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),c);
		lavoro.soddisfaRichiesta(53, new Ruspa(9,"Yahoo","Ruspa",9,9,9));
		assertEquals(lavoro.getRichiesta(50).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),c);
		lavoro.soddisfaRichiesta(53, null);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),c);
	}

	@Test
	public void testLiberaRichiesta() {
		Richiesta.initCodice();
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		Camion m=new Camion(3,"Yamaha","Camion",6,6,6);
		Camion c=new Camion(5,"Yamaha","Camion grande",9,9,9);
		lavoro.caricaRichiesta(rc, 50, null);
		lavoro.caricaRichiesta(rc, 51, m);
		lavoro.aggiungiRichiesta(rc);
		lavoro.aggiungiRichiesta(rc);
		lavoro.soddisfaRichiesta(52, m);
		lavoro.soddisfaRichiesta(53, c);
		lavoro.soddisfaRichiesta(50, m);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),c);
		lavoro.liberaRichiesta(99);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),c);
		lavoro.liberaRichiesta(50);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),null);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),c);
		lavoro.liberaRichiesta(50);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),null);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),c);
		lavoro.liberaRichiesta(52);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),null);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),null);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),c);
		lavoro.liberaRichiesta(53);
		assertEquals(lavoro.getRichiesta(50).getMacchina(),null);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),m);
		assertEquals(lavoro.getRichiesta(52).getMacchina(),null);
		assertEquals(lavoro.getRichiesta(53).getMacchina(),null);
	}

	@Test
	public void testIsScoperto() {
		Richiesta.initCodice();
		assertFalse(lavoro.isScoperto());
		RichiestaCamion rc=new RichiestaCamion(5, 10, 5, 10, 5, 10);
		lavoro.caricaRichiesta(rc, 50, null);
		lavoro.caricaRichiesta(rc, 51, new Camion(3,"Yamaha","Camion",6,6,6));
		lavoro.aggiungiRichiesta(rc);
		assertTrue(lavoro.isScoperto());
		lavoro.soddisfaRichiesta(52, new Camion(4,"Yamaha","Camion",6,6,6));
		assertTrue(lavoro.isScoperto());
		lavoro.soddisfaRichiesta(50, new Camion(5,"Yamaha","Camion",6,6,6));
		assertFalse(lavoro.isScoperto());
		lavoro.liberaRichiesta(51);
		assertEquals(lavoro.getRichiesta(51).getMacchina(),null);
		assertTrue(lavoro.isScoperto());
		lavoro.soddisfaRichiesta(51, new Camion(3,"Yamaha","Camion",6,6,6));
		assertFalse(lavoro.isScoperto());
	}

	@Test
	public void testGetListaRichieste() {
		Richiesta.initCodice();
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
		lavoro.aggiungiRichiesta(rc);
		test=lavoro.getListaRichieste();
		assertEquals(test.size(),3);
		assertEquals(test.get(0),lavoro.getRichiesta(50));
		assertEquals(test.get(1),lavoro.getRichiesta(51));
		assertEquals(test.get(2),lavoro.getRichiesta(52));
	}

	@Test
	public void testGetPriorita() {
		assertEquals(lavoro.getPriorita(),cantiere.getPriorita());
		assertEquals(lavoro.getPriorita(),Priorita.ALTA);
	}

	@Test
	public void testGetCodiceCantiere() {
		assertEquals(lavoro.getCodiceCantiere(),cantiere.getCodice());
		assertEquals(lavoro.getCodiceCantiere(),23);
	}

	@Test
	public void testGetLavoriConnessi() {
		Lavoro l2=new Lavoro(6,"Fondamenta",cantiere, new GregorianCalendar(2015, 9, 01),new GregorianCalendar(2015, 11, 1));
		cantiere.aggiungiLavoro(l2);
		Lavoro l3=new Lavoro(7,"Muratura",cantiere, new GregorianCalendar(2016, 9, 01),new GregorianCalendar(2016, 11, 1));
		cantiere.aggiungiLavoro(l3);
		ArrayList<Lavoro>test=new ArrayList<Lavoro>();
		test.add(lavoro);
		test.add(l2);
		test.add(l3);
		assertEquals(lavoro.getLavoriConnessi(),cantiere.getElencoLavori());
		assertEquals(lavoro.getLavoriConnessi(),test);
	}

	@Test
	public void testGetDurata() {
		assertEquals(lavoro.getDurata(),61);
		lavoro.setDataInizio(new GregorianCalendar(2014,2,2));
		lavoro.setDataFine(new GregorianCalendar(2014,2,6));
		assertEquals(lavoro.getDurata(),4);
	}

}
