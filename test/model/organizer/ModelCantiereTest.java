package model.organizer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;













import java.util.Observable;
import java.util.Observer;

import model.ModelConnector;
import model.organizer.data.Camion;
import model.organizer.data.Cantiere;
import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Lavoro;
import model.organizer.data.Priorita;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaRuspa;
import model.organizer.data.Ruspa;

import org.junit.Test;

import controller.ControllerConnector;
import database.Database;
import view.MainView;
import view.MainViewTableModel;

// 
/**
 *   Class ModelCantiereTest.
 */
public class ModelCantiereTest {

	/**   mc. */
	ModelCantiere mc;
	
	/**
	 * Instantiates a new model cantiere test.
	 */
	public ModelCantiereTest(){
		Database db=Database.getDatabase();
		ModelConnector m=ModelConnector.getModelConnector(db);
		MainView mainView = new MainView(ControllerConnector.getControllerConnector(m));
		mc=ModelCantiere.getModelCantiere();
		m.ResetAllForTest();
		mc.caricaCantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
		mc.caricaCantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priorita.MEDIA);
		mc.caricaCantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priorita.BASSA);
		
		
	}
	
	/**
	 * Test get model cantiere.
	 */
	@Test
	public void testGetModelCantiere() {
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA)));
		assertTrue(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priorita.MEDIA)));
		assertTrue(lista.contains(new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priorita.BASSA)));
		assertFalse(lista.contains(new Cantiere(21,"Asfalto Viale","Dalmine",new GregorianCalendar(2015,06,06),new GregorianCalendar(2018,04,04),Priorita.MEDIA)));
		ModelCantiere prova=ModelCantiere.getModelCantiere();
		assertSame(mc,prova);
		ModelCantiere.resetForTest();
		mc=ModelCantiere.getModelCantiere();
		assertEquals(mc.getNextCodice(),1);
		Database db=Database.getDatabase();
		ModelConnector m=ModelConnector.getModelConnector(db);
		MainView mainView = new MainView(ControllerConnector.getControllerConnector(m));
		mc=ModelCantiere.getModelCantiere();
		mc.svuotaCantieriForTest();
		assertEquals(mc.getNextCodice(),1);
		assertEquals(mc.getNextCodiceLavoro(),1);
		assertTrue(mc.getListaCantieri().isEmpty());
	}

	/**
	 * Test aggiungi cantiere.
	 */
	@Test
	public void testAggiungiCantiere() {
		assertEquals(mc.getNextCodice(),21);
		mc.aggiungiCantiere("MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.BASSA);
		assertEquals(mc.getNextCodice(),22);
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Cantiere(21,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.BASSA)));
		assertFalse(lista.contains(new Cantiere(21,"Asfalto Viale","Dalmine",new GregorianCalendar(2015,06,06),new GregorianCalendar(2018,04,04),Priorita.MEDIA)));
	}

	/**
	 * Test carica cantiere.
	 */
	@Test
	public void testCaricaCantiere() {
		mc.caricaCantiere(1,"Passerella sul Brembo","Dalmine",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		assertEquals(mc.getNextCodice(),21);
		mc.caricaCantiere(25,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
		assertEquals(mc.getNextCodice(),26);
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),5);
		assertTrue(lista.contains(new Cantiere(25,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA)));
		assertFalse(lista.contains(new Cantiere(21,"Asfalto Viale","Dalmine",new GregorianCalendar(2015,06,06),new GregorianCalendar(2018,04,04),Priorita.MEDIA)));
	}

	/**
	 * Test modifica cantiere.
	 */
	@Test
	public void testModificaCantiere() {
		assertEquals(mc.getNextCodice(),21);
		mc.modificaCantiere(16,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
		assertEquals(mc.getNextCodice(),21);
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Cantiere(16,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA)));
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priorita.MEDIA)));
	}

	/**
	 * Test rimuovi cantiere string.
	 */
	@Test
	public void testRimuoviCantiereString() {
		assertTrue(mc.rimuoviCantiere("Pedemontana"));
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priorita.MEDIA)));
		assertFalse(mc.rimuoviCantiere("Aeroporto Orio al Serio"));
	}

	/**
	 * Test rimuovi cantiere int.
	 */
	@Test
	public void testRimuoviCantiereInt() {
		assertTrue(mc.rimuoviCantiere(16));
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priorita.MEDIA)));
		assertFalse(mc.rimuoviCantiere(99));
	}

	/**
	 * Test get cantiere.
	 */
	@Test
	public void testGetCantiere() {
		assertEquals(mc.getCantiere(16),new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priorita.MEDIA));
		assertEquals(mc.getCantiere(7),new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA));
		assertEquals(mc.getCantiere(20),new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priorita.BASSA));
		assertEquals(mc.getCantiere(1),null);
	}
	
	@Test
	public void testToString() {
		Cantiere a=new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
		Cantiere b=new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priorita.MEDIA);
		Cantiere c=new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priorita.BASSA);
		String str=a.toString() + "\n" + b.toString() + "\n" + c.toString() + "\n";
		assertEquals(mc.toString(),str);
	}
	
	@Test
	public void testAggiungiLavoro(){
		mc.aggiungiLavoro(16, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(mc.getNextCodiceLavoro()-1),new Lavoro(mc.getNextCodiceLavoro()-1, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(99),null);
	}
	
	@Test
	public void testCaricaLavoro(){
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(99),null);
		assertEquals(mc.getNextCodiceLavoro()-1,22);
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(99),null);
		assertEquals(mc.getNextCodiceLavoro()-1,22);
	}
	
	@Test
	public void testRimuoviLavoroCodiceCantiereLavoro(){
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.eliminaLavoro(99, 22);
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.eliminaLavoro(16, 99);
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.eliminaLavoro(16, 22);
		assertEquals(mc.getLavoro(22),null);
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.eliminaLavoro(7, 5);
		assertEquals(mc.getLavoro(22),null);
		assertEquals(mc.getLavoro(5),null);
	}
	
	@Test
	public void testRimuoviLavoroCodiceLavoro(){
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.eliminaLavoro(99);
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.eliminaLavoro(22);
		assertEquals(mc.getLavoro(22),null);
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.eliminaLavoro(5);
		assertEquals(mc.getLavoro(22),null);
		assertEquals(mc.getLavoro(5),null);
	}
	
	@Test
	public void testModificaLavoroCodiceLavoro(){
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.modificaLavoro(99, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.modificaLavoro(22, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Asfalto", mc.getCantiere(16),  new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.modificaLavoro(5, "Paratie", new GregorianCalendar(2016,06,06),new GregorianCalendar(2017,07,07));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Asfalto", mc.getCantiere(16),  new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Paratie", mc.getCantiere(7), new GregorianCalendar(2016,06,06),new GregorianCalendar(2017,07,07)));
		
	}
	
	@Test
	public void testModificaLavoroCodiceCantiereLavoro(){
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.modificaLavoro(16,99, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.modificaLavoro(99,22, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.modificaLavoro(16,22, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Asfalto", mc.getCantiere(16),  new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.modificaLavoro(7,5, "Paratie", new GregorianCalendar(2016,06,06),new GregorianCalendar(2017,07,07));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Asfalto", mc.getCantiere(16),  new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Paratie", mc.getCantiere(7), new GregorianCalendar(2016,06,06),new GregorianCalendar(2017,07,07)));
		
	}
	
	@Test
	public void testGetListaLavoriCodiceCantiere(){
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		ArrayList<Lavoro>list=mc.getListaLavori(16);
		assertEquals(list.size(),2);
		assertTrue(list.contains(new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01))));
		assertTrue(list.contains(new Lavoro(23, "Asfalto", mc.getCantiere(16),  new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06))));
		list=mc.getListaLavori(7);
		assertEquals(list.size(),1);
		assertTrue(list.contains(new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20))));
		list=mc.getListaLavori(99);
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testGetListaLavori(){
		ArrayList<Lavoro>list=mc.getListaLavori();
		assertTrue(list.isEmpty());
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		list=mc.getListaLavori();
		assertEquals(list.size(),3);
		assertTrue(list.contains(new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01))));
		assertTrue(list.contains(new Lavoro(23, "Asfalto", mc.getCantiere(16),  new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06))));
		assertTrue(list.contains(new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20))));

	}
	
	@Test
	public void testGetListaScoperti(){
		ArrayList<Lavoro>list=mc.getListaScoperti();
		assertTrue(list.isEmpty());
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		list=mc.getListaScoperti();
		assertTrue(list.isEmpty());
		RichiestaCamion rc=new RichiestaCamion(5,10,5,10,5,10);
		Richiesta.initCodice();
		mc.aggiungiRichiesta(22, rc);
		mc.aggiungiRichiesta(23, rc);
		mc.aggiungiRichiesta(5,rc);
		mc.soddisfaRichiesta(3, new Camion(1,"Yamaha","Corteccia",8,8,8));
		list=mc.getListaScoperti();
		assertEquals(list.size(),2);
		assertTrue(list.contains(l22));
		assertTrue(list.contains(l23));
		assertFalse(list.contains(l5));

	}
	
	@Test
	public void testGetListaScopertiCodiceCantiere(){
		ArrayList<Lavoro>list=mc.getListaScoperti(16);
		assertTrue(list.isEmpty());
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		list=mc.getListaScoperti(16);
		assertTrue(list.isEmpty());
		RichiestaCamion rc=new RichiestaCamion(5,10,5,10,5,10);
		Richiesta.initCodice();
		mc.aggiungiRichiesta(22, rc);
		mc.aggiungiRichiesta(23, rc);
		mc.aggiungiRichiesta(5,rc);
		mc.soddisfaRichiesta(2, new Camion(1,"Yamaha","Corteccia",8,8,8));
		list=mc.getListaScoperti(16);
		assertEquals(list.size(),1);
		assertTrue(list.contains(l22));
		assertFalse(list.contains(l23));
		list=mc.getListaScoperti(7);
		assertEquals(list.size(),1);
		assertTrue(list.contains(l5));
		list=mc.getListaScoperti(99);
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testGetListaCoperti(){
		ArrayList<Lavoro>list=mc.getListaCoperti();
		assertTrue(list.isEmpty());
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		list=mc.getListaCoperti();
		assertEquals(list.size(),3);
		assertTrue(list.contains(l22));
		assertTrue(list.contains(l23));
		assertTrue(list.contains(l5));
		RichiestaCamion rc=new RichiestaCamion(5,10,5,10,5,10);
		Richiesta.initCodice();
		mc.aggiungiRichiesta(22, rc);
		mc.aggiungiRichiesta(23, rc);
		mc.aggiungiRichiesta(5,rc);
		mc.soddisfaRichiesta(3, new Camion(1,"Yamaha","Corteccia",8,8,8));
		list=mc.getListaCoperti();
		assertEquals(list.size(),1);
		assertFalse(list.contains(l22));
		assertFalse(list.contains(l23));
		assertTrue(list.contains(l5));

	}
	
	@Test
	public void testGetListaCopertiCodiceCantiere(){
		ArrayList<Lavoro>list=mc.getListaCoperti(16);
		assertTrue(list.isEmpty());
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		list=mc.getListaCoperti(16);
		assertEquals(list.size(),2);
		assertTrue(list.contains(l22));
		assertTrue(list.contains(l23));
		assertFalse(list.contains(l5));
		RichiestaCamion rc=new RichiestaCamion(5,10,5,10,5,10);
		Richiesta.initCodice();
		mc.aggiungiRichiesta(22, rc);
		mc.aggiungiRichiesta(23, rc);
		mc.aggiungiRichiesta(5,rc);
		mc.soddisfaRichiesta(2, new Camion(1,"Yamaha","Corteccia",8,8,8));
		list=mc.getListaCoperti(16);
		assertEquals(list.size(),1);
		assertTrue(list.contains(l23));
		assertFalse(list.contains(l22));
		list=mc.getListaCoperti(7);
		assertTrue(list.isEmpty());
		list=mc.getListaCoperti(99);
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testAggiungiRichiestaCodiceLavoro(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr=new RichiestaRuspa(5,10,5,10,5,10);
		mc.aggiungiRichiesta(22, rc);
		mc.aggiungiRichiesta(23, rg);
		mc.aggiungiRichiesta(5,rr);
		assertEquals(mc.getRichiesta(99),null);
		assertEquals(mc.getRichiesta(2),new Richiesta(rg, l23, 2));
		assertEquals(mc.getRichiesta(1),new Richiesta(rc, l22, 1));
		assertEquals(mc.getRichiesta(3),new Richiesta(rr, l5, 3));
	}
	
	@Test
	public void testAggiungiRichiestaCodiceCantiereLavoro(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr=new RichiestaRuspa(5,10,5,10,5,10);
		mc.aggiungiRichiesta(16,22, rc);
		mc.aggiungiRichiesta(16,23, rg);
		mc.aggiungiRichiesta(99,22, rc);
		mc.aggiungiRichiesta(16,99, rc);
		mc.aggiungiRichiesta(7,5,rr);
		assertEquals(mc.getRichiesta(99),null);
		assertEquals(mc.getRichiesta(2),new Richiesta(rg, l23, 2));
		assertEquals(mc.getRichiesta(1),new Richiesta(rc, l22, 1));
		assertEquals(mc.getRichiesta(3),new Richiesta(rr, l5, 3));
	}
	
	@Test
	public void testCaricaRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr=new RichiestaRuspa(5,10,5,10,5,10);
		mc.caricaRichiesta(16,22,14,rc,null);
		Gru g=new Gru(60,"Yamaha","Gru",1,2,3,4);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		mc.caricaRichiesta(16,23,60,rg,g);
		mc.caricaRichiesta(99,22,7,rc,null);
		mc.caricaRichiesta(16,99,8,rc,null);
		mc.caricaRichiesta(7,5,44,rr,r);
		mc.aggiungiRichiesta(5,rr);
		Richiesta ric1=new Richiesta(rg, l23, 60);
		Richiesta ric2=new Richiesta(rr, l5, 44);
		ric2.setMacchina(r);
		ric1.setMacchina(g);
		assertEquals(ric2.getMacchina(),r);
		assertEquals(ric1.getMacchina(),null);//g non soddisfa le caratteristiche!!!
		assertEquals(mc.getRichiesta(99),null);
		assertEquals(mc.getRichiesta(60),ric1);
		assertEquals(mc.getRichiesta(14),new Richiesta(rc, l22, 14));
		assertEquals(mc.getRichiesta(61),new Richiesta(rr, l5, 61));
		assertEquals(mc.getRichiesta(44),ric2);
		assertEquals(mc.getRichiesta(7),null);
		assertEquals(mc.getRichiesta(8),null);
	}
	
	
	@Test
	public void testSoddisfaRichiestaCodiceRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr=new RichiestaRuspa(5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",1,2,3,4);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		mc.aggiungiRichiesta(22, rc);
		mc.aggiungiRichiesta(23, rg);
		mc.aggiungiRichiesta(5,rr);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(99, g);
		Richiesta ric1=new Richiesta(rg, l23, 2);
		Richiesta ric2=new Richiesta(rr, l5, 3);
		ric2.setMacchina(r);
		ric1.setMacchina(g);
		assertEquals(ric2.getMacchina(),r);
		assertEquals(ric1.getMacchina(),null);//g non soddisfa le caratteristiche!!!
		assertEquals(mc.getRichiesta(99),null);
		assertEquals(mc.getRichiesta(2),ric1);
		assertEquals(mc.getRichiesta(1),new Richiesta(rc, l22, 1));
		assertEquals(mc.getRichiesta(3),ric2);
	}
	
	@Test
	public void testSoddisfaRichiestaCodiceLavoroRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr=new RichiestaRuspa(5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",1,2,3,4);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		
		mc.aggiungiRichiesta(22, rc);
		mc.aggiungiRichiesta(23, rg);
		mc.aggiungiRichiesta(5,rr);
		mc.soddisfaRichiesta(5,3, r);
		mc.soddisfaRichiesta(23,2, g);
		mc.soddisfaRichiesta(5,99, g);
		mc.soddisfaRichiesta(99,1, new Camion(2, "Yamaha", "Corteccia",7,7,7));
		Richiesta ric1=new Richiesta(rg, l23, 2);
		Richiesta ric2=new Richiesta(rr, l5, 3);
		ric2.setMacchina(r);
		ric1.setMacchina(g);
		assertEquals(ric2.getMacchina(),r);
		assertEquals(ric1.getMacchina(),null);//g non soddisfa le caratteristiche!!!
		assertEquals(mc.getRichiesta(99),null);
		assertEquals(mc.getRichiesta(2),ric1);
		assertEquals(mc.getRichiesta(1),new Richiesta(rc, l22, 1));
		assertEquals(mc.getRichiesta(3),ric2);
	}
	
	@Test
	public void testSoddisfaRichiestaCodiceCantiereLavoroRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr=new RichiestaRuspa(5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",1,2,3,4);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		mc.aggiungiRichiesta(22, rc);
		mc.aggiungiRichiesta(23, rg);
		mc.aggiungiRichiesta(5,rr);
		mc.soddisfaRichiesta(7,5,3, r);
		mc.soddisfaRichiesta(16,23,2, g);
		mc.soddisfaRichiesta(16,22,99, g);
		mc.soddisfaRichiesta(16,99,1, new Camion(2, "Yamaha", "Corteccia",7,7,7));
		mc.soddisfaRichiesta(99,22,1, new Camion(2, "Yamaha", "Corteccia",7,7,7));
		Richiesta ric1=new Richiesta(rg, l23, 2);
		Richiesta ric2=new Richiesta(rr, l5, 3);
		ric2.setMacchina(r);
		ric1.setMacchina(g);
		assertEquals(ric2.getMacchina(),r);
		assertEquals(ric1.getMacchina(),null);//g non soddisfa le caratteristiche!!!
		assertEquals(mc.getRichiesta(99),null);
		assertEquals(mc.getRichiesta(2),ric1);
		assertEquals(mc.getRichiesta(1),new Richiesta(rc, l22, 1));
		assertEquals(mc.getRichiesta(3),ric2);
	}

	@Test
	public void testModificaRichiestaCodiceRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		RichiestaCamion rc2=new RichiestaCamion(5,100,5,100,5,100);
		RichiestaGru rg2=new RichiestaGru(50,100,50,100,50,100,50,100);
		RichiestaRuspa rr2=new RichiestaRuspa(5,100,5,100,5,100);
		mc.modificaRichiesta(1, rc2);
		mc.modificaRichiesta(2, rg2);
		mc.modificaRichiesta(99,rr2);
		mc.modificaRichiesta(4,rr2);
		Richiesta ric1=new Richiesta(rc2, l22, 1);
		Richiesta ric2=new Richiesta(rg2, l23, 2);
		Richiesta ric3=new Richiesta(rr1, l5, 3);
		Richiesta ric4=new Richiesta(rr2, l5, 4);
		ric3.setMacchina(r);
		assertEquals(mc.getRichiesta(99),null);
		assertEquals(mc.getRichiesta(1),ric1);
		assertEquals(mc.getRichiesta(2),ric2);
		assertEquals(mc.getRichiesta(3),ric3);
		assertEquals(mc.getRichiesta(4),ric4);
	}
	
	@Test
	public void testModificaRichiestaCodiceLavoroRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		RichiestaCamion rc2=new RichiestaCamion(5,100,5,100,5,100);
		RichiestaGru rg2=new RichiestaGru(50,100,50,100,50,100,50,100);
		RichiestaRuspa rr2=new RichiestaRuspa(5,100,5,100,5,100);
		mc.modificaRichiesta(22,1, rc2);
		mc.modificaRichiesta(23,2, rg2);
		mc.modificaRichiesta(99,3,rr2);
		mc.modificaRichiesta(5,99,rr2);
		mc.modificaRichiesta(5,4,rr2);
		Richiesta ric1=new Richiesta(rc2, l22, 1);
		Richiesta ric2=new Richiesta(rg2, l23, 2);
		Richiesta ric3=new Richiesta(rr1, l5, 3);
		Richiesta ric4=new Richiesta(rr2, l5, 4);
		ric3.setMacchina(r);
		assertEquals(mc.getRichiesta(99),null);
		assertEquals(mc.getRichiesta(1),ric1);
		assertEquals(mc.getRichiesta(2),ric2);
		assertEquals(mc.getRichiesta(3),ric3);
		assertEquals(mc.getRichiesta(4),ric4);
	}
	
	@Test
	public void testModificaRichiestaCodiceCantiereLavoroRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		RichiestaCamion rc2=new RichiestaCamion(5,100,5,100,5,100);
		RichiestaGru rg2=new RichiestaGru(50,100,50,100,50,100,50,100);
		RichiestaRuspa rr2=new RichiestaRuspa(5,100,5,100,5,100);
		mc.modificaRichiesta(16,22,1, rc2);
		mc.modificaRichiesta(16,23,2, rg2);
		mc.modificaRichiesta(7,99,3,rr2);
		mc.modificaRichiesta(7,5,99,rr2);
		mc.modificaRichiesta(99,5,3,rr2);
		mc.modificaRichiesta(7,5,4,rr2);
		Richiesta ric1=new Richiesta(rc2, l22, 1);
		Richiesta ric2=new Richiesta(rg2, l23, 2);
		Richiesta ric3=new Richiesta(rr1, l5, 3);
		Richiesta ric4=new Richiesta(rr2, l5, 4);
		ric3.setMacchina(r);
		assertEquals(mc.getRichiesta(99),null);
		assertEquals(mc.getRichiesta(1),ric1);
		assertEquals(mc.getRichiesta(2),ric2);
		assertEquals(mc.getRichiesta(3),ric3);
		assertEquals(mc.getRichiesta(4),ric4);
	}
	
	@Test
	public void testLiberaRichiestaCodiceRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertTrue(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(2);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(99);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(1);
		assertFalse(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(3);
		assertFalse(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertFalse(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(4);
		assertFalse(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertFalse(mc.getRichiesta(3).isSoddisfatta());
		assertFalse(mc.getRichiesta(4).isSoddisfatta());
	}
	
	@Test
	public void testLiberaRichiestaCodiceLavoroRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertTrue(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(23,2);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(22,99);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(99,1);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(22,1);
		assertFalse(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(5,3);
		assertFalse(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertFalse(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(5,4);
		assertFalse(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertFalse(mc.getRichiesta(3).isSoddisfatta());
		assertFalse(mc.getRichiesta(4).isSoddisfatta());
	}
	
	@Test
	public void testLiberaRichiestaCodiceCantiereLavoroRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertTrue(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(16,23,2);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(16,22,99);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(16,99,1);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(99,22,1);
		assertTrue(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(16,22,1);
		assertFalse(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertTrue(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(7,5,3);
		assertFalse(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertFalse(mc.getRichiesta(3).isSoddisfatta());
		assertTrue(mc.getRichiesta(4).isSoddisfatta());
		mc.liberaRichiesta(7,5,4);
		assertFalse(mc.getRichiesta(1).isSoddisfatta());
		assertFalse(mc.getRichiesta(2).isSoddisfatta());
		assertFalse(mc.getRichiesta(3).isSoddisfatta());
		assertFalse(mc.getRichiesta(4).isSoddisfatta());
	}
	
	@Test
	public void testRimuoviRichiestaCodiceRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		Richiesta r1=new Richiesta(rc1, l22, 1);
		Richiesta r2=new Richiesta(rg1, l23, 2);
		Richiesta r3=new Richiesta(rr1, l5, 3);
		Richiesta r4=new Richiesta(re1, l5, 4);
		r1.setMacchina(c);
		r2.setMacchina(g);
		r3.setMacchina(r);
		r4.setMacchina(e);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),r2);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(2);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(99);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(1);
		assertEquals(mc.getRichiesta(1),null);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(3);
		assertEquals(mc.getRichiesta(1),null);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),null);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(4);
		assertEquals(mc.getRichiesta(1),null);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),null);
		assertEquals(mc.getRichiesta(4),null);
	}
	
	@Test
	public void testRimuoviRichiestaCodiceLavoroRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		Richiesta r1=new Richiesta(rc1, l22, 1);
		Richiesta r2=new Richiesta(rg1, l23, 2);
		Richiesta r3=new Richiesta(rr1, l5, 3);
		Richiesta r4=new Richiesta(re1, l5, 4);
		r1.setMacchina(c);
		r2.setMacchina(g);
		r3.setMacchina(r);
		r4.setMacchina(e);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),r2);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(23,2);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(99,1);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(22,99);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(22,1);
		assertEquals(mc.getRichiesta(1),null);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(5,3);
		assertEquals(mc.getRichiesta(1),null);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),null);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(5,4);
		assertEquals(mc.getRichiesta(1),null);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),null);
		assertEquals(mc.getRichiesta(4),null);
	}
	
	@Test
	public void testRimuoviRichiestaCodiceCantiereLavoroRichiesta(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		Lavoro l22=mc.getLavoro(22);
		Lavoro l23=mc.getLavoro(23);
		Lavoro l5=mc.getLavoro(5);
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		Richiesta r1=new Richiesta(rc1, l22, 1);
		Richiesta r2=new Richiesta(rg1, l23, 2);
		Richiesta r3=new Richiesta(rr1, l5, 3);
		Richiesta r4=new Richiesta(re1, l5, 4);
		r1.setMacchina(c);
		r2.setMacchina(g);
		r3.setMacchina(r);
		r4.setMacchina(e);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),r2);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(16,23,2);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(16,99,1);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(16,22,99);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(99,22,1);
		assertEquals(mc.getRichiesta(1),r1);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(16,22,1);
		assertEquals(mc.getRichiesta(1),null);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),r3);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(7,5,3);
		assertEquals(mc.getRichiesta(1),null);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),null);
		assertEquals(mc.getRichiesta(4),r4);
		mc.eliminaRichiesta(7,5,4);
		assertEquals(mc.getRichiesta(1),null);
		assertEquals(mc.getRichiesta(2),null);
		assertEquals(mc.getRichiesta(3),null);
		assertEquals(mc.getRichiesta(4),null);
	}
	
	@Test
	public void testGetListaSoddisfatteCodiceCantiereLavoro(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		Richiesta r1=mc.getRichiesta(1);
		Richiesta r2=mc.getRichiesta(2);
		Richiesta r3=mc.getRichiesta(3);
		Richiesta r4=mc.getRichiesta(4);
		assertTrue(r1.isSoddisfatta());
		ArrayList<Richiesta>test=mc.getListaSoddisfatte(16,22);
		assertEquals(test.size(),1);
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		test=mc.getListaSoddisfatte(16,99);
		assertTrue(test.isEmpty());
		test=mc.getListaSoddisfatte(99,22);
		assertTrue(test.isEmpty());
		test=mc.getListaSoddisfatte(16,23);
		assertFalse(test.contains(r1));
		assertTrue(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		test=mc.getListaSoddisfatte(7,5);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),2);
		mc.liberaRichiesta(2);
		test=mc.getListaSoddisfatte(16,23);
		assertTrue(test.isEmpty());
		mc.liberaRichiesta(4);
		test=mc.getListaSoddisfatte(7,5);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
	}
	
	@Test
	public void testGetListaSoddisfatteCodiceCantiere(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		Richiesta r1=mc.getRichiesta(1);
		Richiesta r2=mc.getRichiesta(2);
		Richiesta r3=mc.getRichiesta(3);
		Richiesta r4=mc.getRichiesta(4);
		ArrayList<Richiesta>test=mc.getListaSoddisfatte(16);
		assertTrue(test.contains(r1));
		assertTrue(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),2);
		test=mc.getListaSoddisfatte(99);
		assertTrue(test.isEmpty());
		test=mc.getListaSoddisfatte(7);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),2);
		mc.liberaRichiesta(4);
		test=mc.getListaSoddisfatte(7);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		mc.liberaRichiesta(1);
		mc.liberaRichiesta(2);
		test=mc.getListaSoddisfatte(16);
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void testGetListaSoddisfatte(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		mc.soddisfaRichiesta(1, c);
		mc.soddisfaRichiesta(4, e);
		Richiesta r1=mc.getRichiesta(1);
		Richiesta r2=mc.getRichiesta(2);
		Richiesta r3=mc.getRichiesta(3);
		Richiesta r4=mc.getRichiesta(4);
		ArrayList<Richiesta>test=mc.getListaSoddisfatte();
		assertTrue(test.contains(r1));
		assertTrue(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),4);
		mc.liberaRichiesta(2);
		test=mc.getListaSoddisfatte();
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),3);
		mc.liberaRichiesta(4);
		test=mc.getListaSoddisfatte();
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),2);
		mc.liberaRichiesta(3);
		test=mc.getListaSoddisfatte();
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		mc.liberaRichiesta(1);
		test=mc.getListaSoddisfatte();
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void testGetListaInsoddisfatteCodiceCantiereLavoro(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		Richiesta r1=mc.getRichiesta(1);
		Richiesta r2=mc.getRichiesta(2);
		Richiesta r3=mc.getRichiesta(3);
		Richiesta r4=mc.getRichiesta(4);
		ArrayList<Richiesta>test=mc.getListaInsoddisfatte(16,22);
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		test=mc.getListaInsoddisfatte(16,99);
		assertTrue(test.isEmpty());
		test=mc.getListaInsoddisfatte(99,22);
		assertTrue(test.isEmpty());
		test=mc.getListaInsoddisfatte(16,23);
		assertFalse(test.contains(r1));
		assertTrue(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		test=mc.getListaInsoddisfatte(7,5);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),2);
		mc.soddisfaRichiesta(2, g);
		test=mc.getListaInsoddisfatte(16,23);
		assertTrue(test.isEmpty());
		mc.soddisfaRichiesta(4, e);
		test=mc.getListaInsoddisfatte(7,5);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
	}
	
	@Test
	public void testGetListaInsoddisfatteCodiceCantiere(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		Richiesta r1=mc.getRichiesta(1);
		Richiesta r2=mc.getRichiesta(2);
		Richiesta r3=mc.getRichiesta(3);
		Richiesta r4=mc.getRichiesta(4);
		ArrayList<Richiesta>test=mc.getListaInsoddisfatte(16);
		assertTrue(test.contains(r1));
		assertTrue(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),2);
		test=mc.getListaInsoddisfatte(99);
		assertTrue(test.isEmpty());
		test=mc.getListaInsoddisfatte(7);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),2);
		mc.soddisfaRichiesta(4, e);
		test=mc.getListaInsoddisfatte(7);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		mc.soddisfaRichiesta(3, r);
		mc.soddisfaRichiesta(2, g);
		test=mc.getListaInsoddisfatte(16);
		assertEquals(test.size(),1);
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
	}
	
	@Test
	public void testGetListaInsoddisfatte(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		Gru g=new Gru(60,"Yamaha","Gru",7,7,7,7);
		Ruspa r=new Ruspa(35,"Yamaha","Bufera",7,7,7);
		Camion c=new Camion(14,"Yamaha","Camion",7,7,7);
		Escavatore e= new Escavatore(9,"Yamaha","Escavatore",7,7,7,7);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		Richiesta r1=mc.getRichiesta(1);
		Richiesta r2=mc.getRichiesta(2);
		Richiesta r3=mc.getRichiesta(3);
		Richiesta r4=mc.getRichiesta(4);
		ArrayList<Richiesta>test=mc.getListaInsoddisfatte();
		assertTrue(test.contains(r1));
		assertTrue(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),4);
		mc.soddisfaRichiesta(2, g);
		test=mc.getListaInsoddisfatte();
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),3);
		mc.soddisfaRichiesta(4, e);
		test=mc.getListaInsoddisfatte();
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),2);
		mc.soddisfaRichiesta(3, r);
		test=mc.getListaInsoddisfatte();
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		mc.soddisfaRichiesta(1, c);
		test=mc.getListaInsoddisfatte();
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void testGetListaRichiesteCodiceCantiereLavoro(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		Richiesta r1=mc.getRichiesta(1);
		Richiesta r2=mc.getRichiesta(2);
		Richiesta r3=mc.getRichiesta(3);
		Richiesta r4=mc.getRichiesta(4);
		ArrayList<Richiesta>test=mc.getListaRichieste(16,22);
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		test=mc.getListaRichieste(16,23);
		assertFalse(test.contains(r1));
		assertTrue(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		test=mc.getListaRichieste(7,99);
		assertTrue(test.isEmpty());
		test=mc.getListaRichieste(99,5);
		assertTrue(test.isEmpty());
		test=mc.getListaRichieste(7,5);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),2);
		mc.eliminaRichiesta(1);
		test=mc.getListaRichieste(16,22);
		assertTrue(test.isEmpty());
		test=mc.getListaRichieste(16,23);
		assertFalse(test.contains(r1));
		assertTrue(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		mc.eliminaRichiesta(2);
		test=mc.getListaRichieste(16,22);
		assertTrue(test.isEmpty());
		mc.eliminaRichiesta(3);
		mc.eliminaRichiesta(4);
		test=mc.getListaRichieste(7,5);
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void testGetListaRichiesteCodiceCantiere(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		Richiesta r1=mc.getRichiesta(1);
		Richiesta r2=mc.getRichiesta(2);
		Richiesta r3=mc.getRichiesta(3);
		Richiesta r4=mc.getRichiesta(4);
		ArrayList<Richiesta>test=mc.getListaRichieste(16);
		assertTrue(test.contains(r1));
		assertTrue(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),2);
		test=mc.getListaRichieste(99);
		assertTrue(test.isEmpty());
		test=mc.getListaRichieste(7);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),2);
		mc.eliminaRichiesta(1);
		test=mc.getListaRichieste(16);
		assertFalse(test.contains(r1));
		assertTrue(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		mc.eliminaRichiesta(2);
		test=mc.getListaRichieste(16);
		assertTrue(test.isEmpty());
		test=mc.getListaRichieste(7);
		assertFalse(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),2);
		mc.eliminaRichiesta(3);
		mc.eliminaRichiesta(4);
		test=mc.getListaRichieste(7);
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void testGetListaRichieste(){
		Richiesta.initCodice();
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		mc.caricaLavoro(16, 23, "Asfalto", new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(23),new Lavoro(23, "Asfalto", mc.getCantiere(16), new GregorianCalendar(2015,05,05),new GregorianCalendar(2015,06,06)));
		RichiestaCamion rc1=new RichiestaCamion(5,10,5,10,5,10);
		RichiestaGru rg1=new RichiestaGru(5,10,5,10,5,10,5,10);
		RichiestaRuspa rr1=new RichiestaRuspa(5,10,5,10,5,10);
		RichiestaEscavatore re1=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		mc.aggiungiRichiesta(22, rc1);
		mc.aggiungiRichiesta(23, rg1);
		mc.aggiungiRichiesta(5,rr1);
		mc.aggiungiRichiesta(5, re1);
		Richiesta r1=mc.getRichiesta(1);
		Richiesta r2=mc.getRichiesta(2);
		Richiesta r3=mc.getRichiesta(3);
		Richiesta r4=mc.getRichiesta(4);
		ArrayList<Richiesta>test=mc.getListaRichieste();
		assertTrue(test.contains(r1));
		assertTrue(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),4);
		mc.eliminaRichiesta(2);
		test=mc.getListaRichieste();
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertTrue(test.contains(r4));
		assertEquals(test.size(),3);
		mc.eliminaRichiesta(4);
		test=mc.getListaRichieste();
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertTrue(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),2);
		mc.eliminaRichiesta(3);
		test=mc.getListaRichieste();
		assertTrue(test.contains(r1));
		assertFalse(test.contains(r2));
		assertFalse(test.contains(r3));
		assertFalse(test.contains(r4));
		assertEquals(test.size(),1);
		mc.eliminaRichiesta(1);
		test=mc.getListaRichieste();
		assertTrue(test.isEmpty());
		
	}
}
