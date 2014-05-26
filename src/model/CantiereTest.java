package model;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.Before;

public class CantiereTest {
	
	Cantiere c;
	
	@Before
	public void init(){
		c=new Cantiere(11,"Ponte sullo stretto","Messina",new GregorianCalendar(2060,1,1),new GregorianCalendar(2090,12,31));
	}

	@Test
	public void testCantiere() {
		assertEquals(c.getCodice(),11);
		assertEquals(c.getNomeCantiere(),"Ponte sullo stretto");
		assertEquals(c.getIndirizzo(),"Messina");
		assertEquals(c.getDataApertura(),new GregorianCalendar(2060,1,1));
		assertEquals(c.getDataChiusura(),new GregorianCalendar(2090,12,31));
	}

	@Test
	public void testSetNomeCantiere() {
		c.setNomeCantiere("Grande Opera");
		assertEquals(c.getNomeCantiere(),"Grande Opera");
	}

	@Test
	public void testSetIndirizzo() {
		c.setIndirizzo("Reggio Calabria");
		assertEquals(c.getIndirizzo(),"Reggio Calabria");
	}

	@Test
	public void testSetDataApertura() {
		c.setDataChiusura(new GregorianCalendar(2061,4,25));
		assertEquals(c.getDataChiusura(),new GregorianCalendar(2061,4,25));
	}

	@Test
	public void testSetDataChiusura() {
		c.setDataChiusura(new GregorianCalendar(2091,5,5));
		assertEquals(c.getDataChiusura(),new GregorianCalendar(2091,5,5));
	}

	@Test
	public void testSetCodice() {
		c.setCodice(17);
		assertEquals(c.getCodice(),17);
	}

}
