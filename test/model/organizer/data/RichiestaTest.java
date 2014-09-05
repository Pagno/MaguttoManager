package model.organizer.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class RichiestaTest {
	Richiesta r;
	
	public RichiestaTest(){
		r=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),22);
	}
	
	@Test
	public void testRichiestaRichiestaMacchina() {
		int code;
		code=Richiesta.getNextCodice();
		r=new Richiesta(new RichiestaCamion(10,20,10,20,10,20));
		assertEquals(r.getCaratteristiche(),new RichiestaCamion(10,20,10,20,10,20));
		assertEquals(r.getCodice(),code);
		assertEquals(Richiesta.getNextCodice(),code+1);
		assertEquals(r.getMacchina(),null);
		assertFalse(r.isSoddisfatta());
	}

	@Test
	public void testRichiestaRichiestaMacchinaInt() {
		assertEquals(r.getCaratteristiche(),new RichiestaRuspa(5,10,5,10,5,10));
		assertEquals(r.getCodice(),22);
		assertEquals(Richiesta.getNextCodice(),23);
		assertEquals(r.getMacchina(),null);
		assertFalse(r.isSoddisfatta());
	}

	@Test
	public void testInitCodice() {
		Richiesta.initCodice();
		assertEquals(Richiesta.getNextCodice(),1);
	}

	@Test
	public void testSetCaratteristiche() {
		r.setCaratteristiche(new RichiestaCamion(10,20,10,20,10,20));
		assertEquals(r.getCaratteristiche(),new RichiestaCamion(10,20,10,20,10,20));
	}

	@Test
	public void testIsSoddisfatta() {
		assertFalse(r.isSoddisfatta());
		r.setMacchina(new Ruspa(10,"Pippo","Pippo",7,7,7));
		assertTrue(r.isSoddisfatta());
	}

	@Test
	public void testToString() {
		assertEquals(r.toString(), "22 Richiesta:Ruspa 5-10 5-10 5-10 false null");
		r.setMacchina(new Ruspa(10,"Pippo","Pluto",7,8,9));
		assertEquals(r.toString(), "22 Richiesta:Ruspa 5-10 5-10 5-10 true 10");
	}

	@Test
	public void testEquals() {
		assertTrue(r.equals(r));
		assertFalse(r.equals(null));
		assertFalse(r.equals("Stringa"));
		Richiesta a=new Richiesta(null,22);
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		assertFalse(a.equals(new Richiesta(null,23)));
		assertTrue(a.equals(new Richiesta(null,22)));
		assertTrue(r.equals(new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(6,10,5,10,5,10),22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,11,5,10,5,10),22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,10,6,10,5,10),22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,10,5,11,5,10),22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,10,5,10,6,10),22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,10,5,10,5,11),22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),23)));
		a=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),22);
		assertTrue(r.equals(a));
		assertTrue(a.equals(r));
		a.setMacchina(new Ruspa(7,"Yamaha","Ruspa",7,7,7));
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		r.setMacchina(new Ruspa(7,"Yamaha","Ruspa",7,7,7));
		assertTrue(r.equals(a));
		assertTrue(a.equals(r));
		a.setMacchina(new Ruspa(8,"Yamaha","Ruspa",7,7,7));
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		a.setMacchina(new Ruspa(7,"Toyota","Ruspa",7,7,7));
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		a.setMacchina(new Ruspa(7,"Yamaha","Ruspetta",7,7,7));
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		a.setMacchina(new Ruspa(7,"Yamaha","Ruspa",8,7,7));
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		a.setMacchina(new Ruspa(7,"Yamaha","Ruspa",7,8,7));
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		a.setMacchina(new Ruspa(7,"Yamaha","Ruspa",7,7,8));
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		a.setMacchina(new Ruspa(7,"Yamaha","Ruspa",7,7,7));
		r.setMacchina(null);
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		
	}

	@Test
	public void testRispettaRichiesta() {
		assertTrue(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pippo",7,7,7)));
		assertTrue(r.rispettaRichiesta(new Ruspa(9,"Pippo","Pippo",7,7,7)));
		assertTrue(r.rispettaRichiesta(new Ruspa(10,"Pluto","Pippo",7,7,7)));
		assertTrue(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pluto",7,7,7)));
		assertTrue(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pippo",8,7,7)));
		assertTrue(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pippo",7,8,7)));
		assertTrue(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pippo",7,7,8)));
		assertFalse(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pippo",1,7,7)));
		assertFalse(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pippo",7,1,7)));
		assertFalse(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pippo",7,7,1)));
		assertFalse(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pippo",99,7,7)));
		assertFalse(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pippo",7,99,7)));
		assertFalse(r.rispettaRichiesta(new Ruspa(10,"Pippo","Pippo",7,7,99)));
		assertFalse(r.rispettaRichiesta(new Camion(10,"Pippo","Pippo",7,7,7)));
	}

	@Test
	public void testGetNextCodice() {
		assertEquals(Richiesta.getNextCodice(),23);
		Richiesta.initCodice();
		assertEquals(Richiesta.getNextCodice(),1);
		r=new Richiesta(new RichiestaCamion(10,20,10,20,10,20));
		assertEquals(Richiesta.getNextCodice(),2);
		r=new Richiesta(new RichiestaCamion(10,20,10,20,10,20),99);
		assertEquals(Richiesta.getNextCodice(),100);
		r=new Richiesta(new RichiestaCamion(10,20,10,20,10,20));
		assertEquals(Richiesta.getNextCodice(),101);
	}

	@Test
	public void testSetMacchina() {
		assertFalse(r.isSoddisfatta());
		assertEquals(r.getMacchina(),null);
		r.setMacchina(new Camion(10,"Pippo","Pippo",7,7,7));
		assertFalse(r.isSoddisfatta());
		assertEquals(r.getMacchina(),null);
		r.setMacchina(new Ruspa(10,"Pippo","Pippo",7,7,7));
		assertTrue(r.isSoddisfatta());
		assertEquals(r.getMacchina(),new Ruspa(10,"Pippo","Pippo",7,7,7));
		r.setMacchina(new Camion(10,"Pippo","Pippo",7,7,7));
		assertFalse(r.isSoddisfatta());
		assertEquals(r.getMacchina(),null);
		r.setMacchina(new Ruspa(10,"Pippo","Pippo",7,7,7));
		r.setMacchina(null);
		assertFalse(r.isSoddisfatta());
		assertEquals(r.getMacchina(),null);
	}


}
