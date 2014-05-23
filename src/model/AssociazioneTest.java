package model;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

public class AssociazioneTest {
Associazione a;
	@Test
	public void testAssociazione() {
		Ruspa r=new Ruspa(4,"Caterpillar","Ruspone",100,100,100) ;
		Cantiere c=new Cantiere(3,"BrebeMi","Bottanuco",new GregorianCalendar(2014,5,22),new GregorianCalendar(2014,5,25));
		a=new Associazione(5, r, c,new GregorianCalendar(2014,5,23),new GregorianCalendar(2014,5,26));
		assertSame(5,a.getID());
		assertEquals(a.getMacchina(),r);
		assertEquals(a.getCantiere(),c);
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
		fail("Not yet implemented");
	}

	@Test
	public void testSetDataInizio() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDataFine() {
		fail("Not yet implemented");
	}

}
