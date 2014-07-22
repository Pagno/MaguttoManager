package model.organizer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Associazione;
import model.organizer.data.Camion;
import model.organizer.data.Cantiere;
import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Ruspa;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 *   Class ElencoAssociazioniTest.
 */
public class ElencoAssociazioniTest {

	/**   ea. */
	ElencoAssociazioni ea;
	
	/**
	 * Instantiates a new elenco associazioni test.
	 */
	public ElencoAssociazioniTest(){
		ElencoAssociazioni.resetForTest();
		ea=ElencoAssociazioni.getElencoAssociazioni();
		ea.caricaAssociazione(3, new Ruspa(3,"Yamaha","YRuspa",100,99,98) , new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01));
		ea.caricaAssociazione(4, new Camion(7,"Iveco","ICamion",97,96,95) , new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)), new GregorianCalendar(2014,04,25), new GregorianCalendar(2015,11,13));
		ea.caricaAssociazione(9, new Escavatore(11,"Caterpillar","CEscavatore",94,93,92,91) , new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05)), new GregorianCalendar(2014,06,06), new GregorianCalendar(2017,01,29));
	}
	
	/**
	 * Test get elenco associazioni.
	 */
	@Test
	public void testGetElencoAssociazioni() {
		ArrayList<Associazione>lista=ea.getElencoAssociazioniList();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Associazione(3, new Ruspa(3,"Yamaha","YRuspa",100,99,98) , new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01))));
		assertTrue(lista.contains(new Associazione(4, new Camion(7,"Iveco","ICamion",97,96,95) , new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)), new GregorianCalendar(2014,04,25), new GregorianCalendar(2015,11,13))));
		assertTrue(lista.contains(new Associazione(9, new Escavatore(11,"Caterpillar","CEscavatore",94,93,92,91) , new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05)), new GregorianCalendar(2014,06,06), new GregorianCalendar(2017,01,29))));
		ElencoAssociazioni prova=ElencoAssociazioni.getElencoAssociazioni();
		assertSame(ea,prova);
		ElencoAssociazioni.resetForTest();
		ea=ElencoAssociazioni.getElencoAssociazioni();
		assertEquals(ea.getNextCodice(),1);
	}

	/**
	 * Test inserisci associazione.
	 */
	@Test
	public void testInserisciAssociazione() {
		assertEquals(ea.getNextCodice(),10);
		ea.inserisciAssociazione(new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01));
		assertEquals(ea.getNextCodice(),11);
		ArrayList<Associazione>lista=ea.getElencoAssociazioniList();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Associazione(10,new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01))));
	}

	/**
	 * Test carica associazione.
	 */
	@Test
	public void testCaricaAssociazione() {
		assertEquals(ea.getNextCodice(),10);
		ea.caricaAssociazione(15,new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01));
		assertEquals(ea.getNextCodice(),16);
		ArrayList<Associazione>lista=ea.getElencoAssociazioniList();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Associazione(15,new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01))));
	}

	/**
	 * Test modifica associazione.
	 */
	@Test
	public void testModificaAssociazione() {
		ea.modificaAssociazione(4,new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01));
		ArrayList<Associazione>lista=ea.getElencoAssociazioniList();
		assertEquals(lista.size(),3);
		assertFalse(lista.contains(new Associazione(4, new Camion(7,"Iveco","ICamion",97,96,95) , new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)), new GregorianCalendar(2014,04,25), new GregorianCalendar(2015,11,13))));
		assertTrue(lista.contains(new Associazione(4,new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01))));
	}

	/**
	 * Test elimina associazione.
	 */
	@Test
	public void testEliminaAssociazione() {
		assertTrue(ea.eliminaAssociazione(4));
		ArrayList<Associazione>lista=ea.getElencoAssociazioniList();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Associazione(4, new Camion(7,"Iveco","ICamion",97,96,95) , new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)), new GregorianCalendar(2014,04,25), new GregorianCalendar(2015,11,13))));
		assertFalse(ea.eliminaAssociazione(34));
	}
	
	@Test
	public void testToString() {
		Associazione a=new Associazione(3, new Ruspa(3,"Yamaha","YRuspa",100,99,98) , new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01));
		Associazione b=new Associazione(4, new Camion(7,"Iveco","ICamion",97,96,95) , new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)), new GregorianCalendar(2014,04,25), new GregorianCalendar(2015,11,13));
		Associazione c=new Associazione(9, new Escavatore(11,"Caterpillar","CEscavatore",94,93,92,91) , new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05)), new GregorianCalendar(2014,06,06), new GregorianCalendar(2017,01,29));
		String str= a.toString() + "\n" + b.toString() + "\n" +c.toString() + "\n";
		assertEquals(ea.toString(),str);
	}
	
	@Test
	public void testGetElencoAssociazioniList() {
		Cantiere c=new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01));
		ea.inserisciAssociazione(new Camion(11,"Iveco","ICamion",97,96,95), c, new GregorianCalendar(2014,01,01), new GregorianCalendar(2016,01,01));
		ea.inserisciAssociazione(new Camion(13,"Iveco","ICamion",97,96,95), c, new GregorianCalendar(2014,01,01), new GregorianCalendar(2016,01,01));
		ArrayList<Associazione> arrGet=ea.getElencoAssociazioniList(c.getCodice());
		ArrayList<Associazione> arrGen=new ArrayList<Associazione>();
		arrGen.add(new Associazione(4, new Camion(7,"Iveco","ICamion",97,96,95) , new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)), new GregorianCalendar(2014,04,25), new GregorianCalendar(2015,11,13)));
		arrGen.add(new Associazione(10,new Camion(11,"Iveco","ICamion",97,96,95), c, new GregorianCalendar(2014,01,01), new GregorianCalendar(2016,01,01)));
		arrGen.add(new Associazione(11,new Camion(13,"Iveco","ICamion",97,96,95), c, new GregorianCalendar(2014,01,01), new GregorianCalendar(2016,01,01)));
		assertEquals(arrGet, arrGen);
	}

}
