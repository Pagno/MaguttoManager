package model;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class AssociazioneTest {
	Associazione a;

    @Before
    public void init(){
    	a=new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26));
    }
    
	@Test
	public void testAssociazione() {
		//a=new Associazione(5, new Ruspa(4,"Caterpillar","Ruspone",100,100,100), new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)),new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26));
		assertSame(5,a.getID());
		assertEquals(a.getMacchina(),new Ruspa(4,"Caterpillar","Ruspone",100,100,100));
		assertEquals(a.getCantiere(),new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25)));
		assertEquals(a.getDataInizio(),new GregorianCalendar(2014,5,23));
		assertEquals(a.getDataFine(),new GregorianCalendar(2014,5,26));
	}

	@Test
	public void testSetMacchina() {
		a.setMacchina(new Camion(6,"yamaha","Ruspina",50,50,50) );
		assertEquals(a.getMacchina(),new Camion(6,"yamaha","Ruspina",50,50,50));
	}

	@Test
	public void testSetCantiere() {
		a.setCantiere(new Cantiere(7,"IPB","Osio Sotto",new GregorianCalendar(2019,1,22),new GregorianCalendar(2020,7,13)));
		assertEquals(a.getCantiere(),new Cantiere(7,"IPB","Osio Sotto",new GregorianCalendar(2019,1,22),new GregorianCalendar(2020,7,13)));
	}

	@Test
	public void testSetDataInizio() {
		a.setDataInizio(new GregorianCalendar(2018,5,25));
		assertEquals(a.getDataInizio(),new GregorianCalendar(2018,5,25));
	}

	@Test
	public void testSetDataFine() {
		a.setDataFine(new GregorianCalendar(2021,6,2));
		assertEquals(a.getDataFine(),new GregorianCalendar(2021,6,2));
	}

}
