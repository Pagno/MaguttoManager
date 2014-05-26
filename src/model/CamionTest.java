package model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class CamionTest {
	Camion c;
	@Before
	public void init(){
		c=new Camion(5,"Yamaha","Prova",100,99,98);
	}

	@Test
	public void testCamion() {
		assertEquals(c.getCodice(),5);
		assertEquals(c.getProduttore(),"Yamaha");
		assertEquals(c.getModello(),"Prova");
		assertEquals(c.getCapacitaMassima(),100);
		assertEquals(c.getPortataMassima(),99);
		assertEquals(c.getLunghezza(),98);
	}

	@Test
	public void testSetCapacitaMassima() {
		c.setCapacitaMassima(200);
		assertEquals(c.getCapacitaMassima(),200);
	}

	@Test
	public void testSetPortataMassima() {
		c.setPortataMassima(200);
		assertEquals(c.getPortataMassima(),200);
	}

	@Test
	public void testSetLunghezza() {
		c.setLunghezza(200);
		assertEquals(c.getLunghezza(),200);
	}

	@Test
	public void testSetProduttore() {
		c.setProduttore("Caterpillar");
		assertEquals(c.getProduttore(),"Caterpillar");
	}

	@Test
	public void testSetModello() {
		c.setModello("Tir");
		assertEquals(c.getModello(),"Tir");
	}

}
