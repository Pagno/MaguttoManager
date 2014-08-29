package model.organizer.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;




import org.junit.Test;

// 
/**
 *   Class AssociazioneTest.
 */
public class LavoroTest {
	
	/**   a. */
	Lavoro lavoro;

    /**
     * Instantiates a new associazione test.
     */
    public LavoroTest(){
    	lavoro=new Lavoro(5,"Scavi fondamenta" ,new GregorianCalendar(2014,8,29), new GregorianCalendar(2014,9,29));
    	
    	//Inserisco alcune richieste di macchine
    	RichiestaGru richiestaGru=new RichiestaGru(30,40,20,30,3000,4000,90,120);
    	RichiestaCamion richiestaCamion=new RichiestaCamion(200,500,1000,2000,10,15);
    	lavoro.inserisciRichiesta(richiestaGru);
    	Camion camion=new Camion(12,"New Holland","MR12",325,1090,11);
    	lavoro.inserisciRichiesta(richiestaCamion);
    }
    
	/**
	 * Test associazione.
	 */
	@Test
	public void testLavoro() {
		assertEquals(lavoro.getCodice(),5);
		assertEquals(lavoro.getNome(),"Scavi fondamenta" );
		assertEquals(lavoro.getDataFine(),new GregorianCalendar(2014,9,29));
		assertEquals(lavoro.getDataInizio(),new GregorianCalendar(2014,8,29));
	}

	/**
	 * Test getListaRichieste.
	 */
	@Test
	public void testGetListaRichieste(){
		ArrayList<Richiesta> test=new ArrayList<Richiesta>();
		test.add(new Richiesta(new RichiestaCamion(200,500,1000,2000,10,15)));
		test.add(new Richiesta(new RichiestaGru(30,40,20,30,3000,4000,90,120)));
		assertEquals(lavoro.getListaRichieste(),test);
	}
}
