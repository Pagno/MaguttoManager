package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GruTest {
	
	Gru g;
	
	@Before
	public void init(){
		g=new Gru(5,"Volvo","Grattacielo",360,100,99,98);
	}
	
	@Test
	public void testGru() {
		assertEquals(g.getAngoloRotazione(),360);
		assertEquals(g.getPortataMassima(),100);
		assertEquals(g.getLunghezza(),99);
		assertEquals(g.getAltezza(),98);
		assertEquals(g.getProduttore(),"Volvo");
		assertEquals(g.getModello(),"Grattacielo");
	}

	@Test
	public void testSetLunghezzaMassima() {
		g.setLunghezzaMassima(200);
		assertEquals(g.getLunghezza(),200);
	}

	@Test
	public void testSetPortataMassima() {
		g.setPortataMassima(200);
		assertEquals(g.getPortataMassima(),200);
	}

	@Test
	public void testSetAltezza() {
		g.setAltezza(200);
		assertEquals(g.getAltezza(),200);
	}

	@Test
	public void testSetAngoloRotazione() {
		g.setAngoloRotazione(270);
		assertEquals(g.getAngoloRotazione(),270);
	}

	@Test
	public void testSetProduttore() {
		g.setProduttore("Caterpillar");
		assertEquals(g.getProduttore(),"Caterpillar");
	}

	@Test
	public void testSetModello() {
		g.setModello("Villetta");
		assertEquals(g.getModello(),"Villetta");
	}

}
