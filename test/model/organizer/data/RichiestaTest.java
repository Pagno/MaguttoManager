package model.organizer.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class RichiestaTest {
	Richiesta r;
	
	public RichiestaTest(){
		RichiestaRuspa rr=new RichiestaRuspa(5,10,5,10,5,10);
		r=new Richiesta(rr,22);
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
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRispettaRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNextCodice() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMacchina() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject1() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString1() {
		fail("Not yet implemented");
	}

}
