package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RuspaTest {

	Ruspa r;
	
	@Before
	public void init(){
		r=new Ruspa(5,"Caterpillar","Ruspona",100,99,98);
	}
	
	@Test
	public void testRuspa() {
		assertEquals(r.getCapacitaMassima(),100);
		assertEquals(r.getPortataMassima(),99);
		assertEquals(r.getAltezzaMassima(),98);
		assertEquals(r.getProduttore(),"Caterpillar");
		assertEquals(r.getModello(),"Ruspona");
	}

	@Test
	public void testSetCapacitaMassima() {
		r.setCapacitaMassima(200);
		assertEquals(r.getCapacitaMassima(),200);
	}

	@Test
	public void testSetPortataMassima() {
		r.setPortataMassima(200);
		assertEquals(r.getPortataMassima(),200);
	}

	@Test
	public void testSetAltezzaMassima() {
		r.setAltezzaMassima(200);
		assertEquals(r.getAltezzaMassima(),200);
	}

	@Test
	public void testSetProduttore() {
		r.setProduttore("Volvo");
		assertEquals(r.getProduttore(),"Volvo");
	}

	@Test
	public void testSetModello() {
		r.setModello("Ruspetta");
		assertEquals(r.getModello(),"Ruspetta");
	}

}
