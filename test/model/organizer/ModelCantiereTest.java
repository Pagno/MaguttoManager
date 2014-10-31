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
		mc.resetForTest();
		mc=ModelCantiere.getModelCantiere();
		mc.caricaCantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
	mc.caricaCantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priorita.MEDIA);
	mc.caricaCantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priorita.BASSA);
	
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
		assertEquals(mc.getProssimoCodice(),1);
		Database db=Database.getDatabase();
		ModelConnector m=ModelConnector.getModelConnector(db);
		MainView mainView = new MainView(ControllerConnector.getControllerConnector(m));
		mc=ModelCantiere.getModelCantiere();
		mc.svuotaCantieriForTest();
		assertEquals(mc.getProssimoCodice(),1);
		assertEquals(mc.getProssimoCodiceLavoro(),1);
		assertTrue(mc.getListaCantieri().isEmpty());
	}
	/**
	 * Test aggiungi cantiere.
	 */
	@Test
	public void testAggiungiCantiere() {
		assertEquals(mc.getProssimoCodice(),21);
		mc.aggiungiCantiere("MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.BASSA);
		assertEquals(mc.getProssimoCodice(),22);
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
		assertEquals(mc.getProssimoCodice(),21);
		mc.caricaCantiere(25,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
		assertEquals(mc.getProssimoCodice(),26);
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
		assertEquals(mc.getProssimoCodice(),21);
		mc.modificaCantiere(16,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
		assertEquals(mc.getProssimoCodice(),21);
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Cantiere(16,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA)));
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priorita.MEDIA)));
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
		assertEquals(mc.getLavoro(mc.getProssimoCodiceLavoro()-1),new Lavoro(mc.getProssimoCodiceLavoro()-1, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(99),null);
	}
	
	@Test
	public void testCaricaLavoro(){
		mc.caricaLavoro(16, 22, "Scavo", new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(99),null);
		assertEquals(mc.getProssimoCodiceLavoro()-1,22);
		mc.caricaLavoro(7, 5, "Svuotamento", new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20));
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		assertEquals(mc.getLavoro(99),null);
		assertEquals(mc.getProssimoCodiceLavoro()-1,22);
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
		mc.aggiungiRichiesta(16,22, rc);
		mc.aggiungiRichiesta(16,23, rg);
		mc.aggiungiRichiesta(7,5,rr);
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
	public void testLiberaRichiesta(){
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
		mc.aggiungiRichiesta(16,22, rc1);
		mc.aggiungiRichiesta(16,23, rg1);
		mc.aggiungiRichiesta(7,5,rr1);
		mc.aggiungiRichiesta(7,5, re1);
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
	public void testRimuoviRichiesta(){
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
		mc.aggiungiRichiesta(16,22, rc1);
		mc.aggiungiRichiesta(16,23, rg1);
		mc.aggiungiRichiesta(7,5,rr1);
		mc.aggiungiRichiesta(7,5, re1);
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
		mc.aggiungiRichiesta(16,22, rc1);
		mc.aggiungiRichiesta(16,23, rg1);
		mc.aggiungiRichiesta(7,5,rr1);
		mc.aggiungiRichiesta(7,5, re1);
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
	public void testSvuotaCantieriForTest(){
		mc.resetForTest();
		mc=ModelCantiere.getModelCantiere();
		mc.caricaCantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
		mc.caricaCantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priorita.MEDIA);
		mc.caricaCantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priorita.BASSA);
		assertEquals(3,mc.getListaCantieri().size());
		mc.svuotaCantieriForTest();
		assertEquals(0,mc.getListaCantieri().size());
	}
}
