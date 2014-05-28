package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EscavatoreTest {
 
	Escavatore e;
	
	@Before
	public void init(){
		e=new Escavatore(5,"Volvo","Prova",100,99,98,97);
	}
	
	@Test
	public void testEscavatore() {
		assertEquals(e.getCapacitaMassima(),100);
		assertEquals(e.getPortataMassima(),99);
		assertEquals(e.getProfonditaMassima(),97);
		assertEquals(e.getAltezzaMassima(),98);
		assertEquals(e.getProduttore(),"Volvo");
		assertEquals(e.getModello(),"Prova");
	}

	@Test
	public void testSetCapacitaMassima() {
		e.setCapacitaMassima(200);
		assertEquals(e.getCapacitaMassima(),200);
	}

	@Test
	public void testSetPortataMassima() {
		e.setPortataMassima(200);
		assertEquals(e.getPortataMassima(),200);
	}

	@Test
	public void testSetProfonditaMassima() {
		e.setProfonditaMassima(200);
		assertEquals(e.getProfonditaMassima(),200);
	}

	@Test
	public void testSetAltezzaMassima() {
		e.setAltezzaMassima(200);
		assertEquals(e.getAltezzaMassima(),200);
	}

	@Test
	public void testSetProduttore() {
		e.setProduttore("Caterpillar");
		assertEquals(e.getProduttore(),"Caterpillar");
	}

	@Test
	public void testSetModello() {
		e.setModello("ModProva");
		assertEquals(e.getModello(),"ModProva");
	}

}
