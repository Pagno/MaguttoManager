package model.organizer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;







import model.organizer.data.Camion;
import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Priority;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;

import org.junit.Test;

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
		ModelCantiere.resetForTest();
		mc=ModelCantiere.getModelCantiere();
		mc.caricaCantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		mc.caricaCantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA);
		mc.caricaCantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priority.BASSA);
		
	}
	
	/**
	 * Test get model cantiere.
	 */
	@Test
	public void testGetModelCantiere() {
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA)));
		assertTrue(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA)));
		assertTrue(lista.contains(new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priority.BASSA)));
		assertFalse(lista.contains(new Cantiere(21,"Asfalto Viale","Dalmine",new GregorianCalendar(2015,06,06),new GregorianCalendar(2018,04,04),Priority.MEDIA)));
		ModelCantiere prova=ModelCantiere.getModelCantiere();
		assertSame(mc,prova);
		ModelCantiere.resetForTest();
		mc=ModelCantiere.getModelCantiere();
		assertEquals(mc.getNextCodice(),1);
		
	}

	/**
	 * Test aggiungi cantiere.
	 */
	@Test
	public void testAggiungiCantiere() {
		assertEquals(mc.getNextCodice(),21);
		mc.aggiungiCantiere("MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.BASSA);
		assertEquals(mc.getNextCodice(),22);
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),4);
		assertTrue(lista.contains(new Cantiere(21,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.BASSA)));
		assertFalse(lista.contains(new Cantiere(21,"Asfalto Viale","Dalmine",new GregorianCalendar(2015,06,06),new GregorianCalendar(2018,04,04),Priority.MEDIA)));
	}

	/**
	 * Test carica cantiere.
	 */
	@Test
	public void testCaricaCantiere() {
		mc.caricaCantiere(1,"Passerella sul Brembo","Dalmine",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.MEDIA);
		assertEquals(mc.getNextCodice(),21);
		mc.caricaCantiere(25,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		assertEquals(mc.getNextCodice(),26);
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),5);
		assertTrue(lista.contains(new Cantiere(25,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA)));
		assertFalse(lista.contains(new Cantiere(21,"Asfalto Viale","Dalmine",new GregorianCalendar(2015,06,06),new GregorianCalendar(2018,04,04),Priority.MEDIA)));
	}

	/**
	 * Test modifica cantiere.
	 */
	@Test
	public void testModificaCantiere() {
		assertEquals(mc.getNextCodice(),21);
		mc.modificaCantiere(16,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		assertEquals(mc.getNextCodice(),21);
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),3);
		assertTrue(lista.contains(new Cantiere(16,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA)));
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA)));
	}

	/**
	 * Test rimuovi cantiere string.
	 */
	@Test
	public void testRimuoviCantiereString() {
		assertTrue(mc.rimuoviCantiere("Pedemontana"));
		ArrayList<Cantiere>lista=mc.getListaCantieri();
		assertEquals(lista.size(),2);
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA)));
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
		assertFalse(lista.contains(new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA)));
		assertFalse(mc.rimuoviCantiere(99));
	}

	/**
	 * Test get cantiere.
	 */
	@Test
	public void testGetCantiere() {
		assertEquals(mc.getCantiere(16),new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA));
		assertEquals(mc.getCantiere(7),new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA));
		assertEquals(mc.getCantiere(20),new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priority.BASSA));
		assertEquals(mc.getCantiere(1),null);
	}
	
	@Test
	public void testToString() {
		Cantiere a=new Cantiere(7,"MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		Cantiere b=new Cantiere(16,"Pedemontana","Osio Sotto",new GregorianCalendar(2014,01,01),new GregorianCalendar(2016,01,01),Priority.MEDIA);
		Cantiere c=new Cantiere(20,"Circonvallazione","Stezzano",new GregorianCalendar(2014,05,05),new GregorianCalendar(2017,05,05),Priority.BASSA);
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
		mc.rimuoviLavoro(99, 22);
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.rimuoviLavoro(16, 99);
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.rimuoviLavoro(16, 22);
		assertEquals(mc.getLavoro(22),null);
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.rimuoviLavoro(7, 5);
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
		mc.rimuoviLavoro(99);
		assertEquals(mc.getLavoro(22),new Lavoro(22, "Scavo", mc.getCantiere(16),  new GregorianCalendar(2014,02,01),new GregorianCalendar(2014,05,01)));
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.rimuoviLavoro(22);
		assertEquals(mc.getLavoro(22),null);
		assertEquals(mc.getLavoro(5),new Lavoro(5, "Svuotamento", mc.getCantiere(7),  new GregorianCalendar(2014,03,01),new GregorianCalendar(2014,03,20)));
		mc.rimuoviLavoro(5);
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
		assertEquals(list,null);
	}
	
	@Test
	public void testGetListaLavori(){
		ArrayList<Lavoro>list=mc.getListaLavori();
		assertEquals(list,null);
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
		assertEquals(list,null);
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
		assertEquals(list,null);
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
		assertEquals(list,null);
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
		assertEquals(list,null);
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
		assertEquals(list,null);
	}
	
	@Test
	public void testGetListaCoperti(){
		ArrayList<Lavoro>list=mc.getListaCoperti();
		assertEquals(list,null);
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
		ArrayList<Lavoro>list=mc.getListaScoperti(16);
		assertEquals(list,null);
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
		assertEquals(list,null);
		list=mc.getListaScoperti(99);
		assertEquals(list,null);
	}
	
	
	@Test
	public void testSoddisfaRichiesta(){
		//TODO e anche aggiungirichiesta
	}

}
