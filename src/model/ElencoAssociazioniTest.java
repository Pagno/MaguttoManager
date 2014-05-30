package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class ElencoAssociazioniTest {

	ElencoAssociazioni ea;
	@Before
	public void init(){
		ElencoAssociazioni.resetForTest();
		ea=ElencoAssociazioni.getElencoAssociazioni();
		ea.caricaAssociazione(3, new Ruspa(3,"Yamaha","YRuspa",100,99,98) , new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01));
		ea.caricaAssociazione(4, new Camion(7,"Iveco","ICamion",97,96,95) , new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)), new GregorianCalendar(2014,04,25), new GregorianCalendar(2015,11,13));
		ea.caricaAssociazione(9, new Escavatore(11,"Caterpillar","CEscavatore",94,93,92,91) , new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05)), new GregorianCalendar(2014,06,06), new GregorianCalendar(2017,01,29));
	}
	
	@Test
	public void testGetElencoAssociazioni() {
		ArrayList<Associazione>lista=ea.getElencoAssociazioniList();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Associazione(3, new Ruspa(3,"Yamaha","YRuspa",100,99,98) , new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01))));
		assertTrue(lista.contains(new Associazione(4, new Camion(7,"Iveco","ICamion",97,96,95) , new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)), new GregorianCalendar(2014,04,25), new GregorianCalendar(2015,11,13))));
		assertTrue(lista.contains(new Associazione(9, new Escavatore(11,"Caterpillar","CEscavatore",94,93,92,91) , new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05)), new GregorianCalendar(2014,06,06), new GregorianCalendar(2017,01,29))));
	}

	@Test
	public void testInserisciAssociazione() {
		assertEquals(ea.getNextCodice(),10);
		ea.inserisciAssociazione(new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01));
		assertEquals(ea.getNextCodice(),11);
		ArrayList<Associazione>lista=ea.getElencoAssociazioniList();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Associazione(10,new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01))));
	}

	@Test
	public void testCaricaAssociazione() {
		assertEquals(ea.getNextCodice(),10);
		ea.caricaAssociazione(15,new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01));
		assertEquals(ea.getNextCodice(),16);
		ArrayList<Associazione>lista=ea.getElencoAssociazioniList();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Associazione(15,new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01))));
	}

	@Test
	public void testModificaAssociazione() {
		ea.modificaAssociazione(4,new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01));
		ArrayList<Associazione>lista=ea.getElencoAssociazioniList();
		assertEquals(lista.size(),3);
		assertFalse(lista.contains(new Associazione(4, new Camion(7,"Iveco","ICamion",97,96,95) , new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)), new GregorianCalendar(2014,04,25), new GregorianCalendar(2015,11,13))));
		assertTrue(lista.contains(new Associazione(4,new Gru(13,"G.C.","GGru",360,90,89,88), new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22)), new GregorianCalendar(2014,03,11), new GregorianCalendar(2014,12,01))));
	}

	@Test
	public void testEliminaAssociazione() {
		assertTrue(ea.eliminaAssociazione(4));
		ArrayList<Associazione>lista=ea.getElencoAssociazioniList();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Associazione(4, new Camion(7,"Iveco","ICamion",97,96,95) , new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01)), new GregorianCalendar(2014,04,25), new GregorianCalendar(2015,11,13))));
	}

}
