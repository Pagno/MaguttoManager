package model.organizer.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class RichiestaMacchinaTest {

	@Test
	public void testRichiestaMacchina(){
		RichiestaMacchina ric;
		ric=new RichiestaCamion(5,10,5,10,5,10);
		assertFalse(ric.equals(null));
		ric=new RichiestaRuspa(5,10,5,10,5,10);
		assertFalse(ric.equals(null));
		ric=new RichiestaEscavatore(5,10,5,10,5,10,5,10);
		assertFalse(ric.equals(null));
		ric=new RichiestaGru(5,10,5,10,5,10,5,10);
		assertFalse(ric.equals(null));
	}
	
	@Test
	public void testGestisciLimiti(){
		int aMin, aMax, bMin, bMax;
		aMin=5;
		aMax=10;
		bMin=15;
		bMax=20;
		assertFalse(RichiestaMacchina.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertFalse(RichiestaMacchina.gestisciLimiti(bMin, bMax, aMin, aMax));
		aMax=15;
		bMin=10;
		assertTrue(RichiestaMacchina.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(RichiestaMacchina.gestisciLimiti(bMin, bMax, aMin, aMax));
		aMin=-1;
		aMax=-1;
		assertTrue(RichiestaMacchina.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(RichiestaMacchina.gestisciLimiti(bMin, bMax, aMin, aMax));
		aMax=15;
		bMin=-1;
		assertTrue(RichiestaMacchina.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(RichiestaMacchina.gestisciLimiti(bMin, bMax, aMin, aMax));
		bMin=10;
		assertTrue(RichiestaMacchina.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(RichiestaMacchina.gestisciLimiti(bMin, bMax, aMin, aMax));
		bMin=17;
		assertFalse(RichiestaMacchina.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertFalse(RichiestaMacchina.gestisciLimiti(bMin, bMax, aMin, aMax));
		aMin=10;
		aMax=-1;
		bMax=-1;
		assertTrue(RichiestaMacchina.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(RichiestaMacchina.gestisciLimiti(bMin, bMax, aMin, aMax));
		bMax=20;
		assertTrue(RichiestaMacchina.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(RichiestaMacchina.gestisciLimiti(bMin, bMax, aMin, aMax));
		bMin=6;
		bMax=7;
		assertFalse(RichiestaMacchina.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertFalse(RichiestaMacchina.gestisciLimiti(bMin, bMax, aMin, aMax));
	}

}
