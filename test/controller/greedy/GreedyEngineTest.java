package controller.greedy;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Priority;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;

import org.junit.Test;

public class GreedyEngineTest {

	@Test
	public void testGenerateAssociations() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertAssociation() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMacchinaWithoutReservation() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveReservationsByRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMostPromisingReservation() {
		fail("Not yet implemented");
	}

	@Test
	public void testReserveMacchineFromLavoro() {
		fail("Not yet implemented");
	}

	@Test
	public void testLavoroEndsLessThanAWeekBefore() {
		fail("Not yet implemented");
	}

	@Test
	public void testLavoroStartsLessThanAWeekAfter() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByPriority() {
		Cantiere c1=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		Cantiere c2=new Cantiere(2,"c2","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.MEDIA);
		Cantiere c3=new Cantiere(3,"c3","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.BASSA);
		Lavoro l1_1=new Lavoro(11,"l1",c1,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,05));
		Lavoro l1_2=new Lavoro(12,"l2",c1,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,10));
		Lavoro l2_1=new Lavoro(13,"l3",c2,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,05));
		Lavoro l2_2=new Lavoro(14,"l4",c2,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,10));
		Lavoro l3_1=new Lavoro(15,"l5",c3,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,05));
		Lavoro l3_2=new Lavoro(16,"l6",c3,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,10));
		c1.addLavoro(l1_1);
		c1.addLavoro(l1_2);
		c2.addLavoro(l2_1);
		c2.addLavoro(l2_2);
		c3.addLavoro(l3_1);
		c3.addLavoro(l3_2);
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l1_1.caricaRichiesta(rc,101,null);
		l1_2.caricaRichiesta(rc,102,null);
		l2_1.caricaRichiesta(rc,103,null);
		l2_2.caricaRichiesta(rc,104,null);
		l3_1.caricaRichiesta(rc,105,null);
		l3_2.caricaRichiesta(rc,106,null);
		Richiesta r1_1=l1_1.getRichiesta(101);
		Richiesta r1_2=l1_2.getRichiesta(102);
		Richiesta r2_1=l2_1.getRichiesta(103);
		Richiesta r2_2=l2_2.getRichiesta(104);
		Richiesta r3_1=l3_1.getRichiesta(105);
		Richiesta r3_2=l3_2.getRichiesta(106);
		Integer d1_1,d1_2,d2_1,d2_2,d3_1,d3_2;
		GregorianCalendar sx,dx;
		d1_1=0;
		sx=(GregorianCalendar)r1_1.getDataInizio().clone();
		dx=(GregorianCalendar)r1_1.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d1_1++;
		}
		d1_2=0;
		sx=(GregorianCalendar)r1_2.getDataInizio().clone();
		dx=(GregorianCalendar)r1_2.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d1_2++;
		}
		d2_1=0;
		sx=(GregorianCalendar)r2_1.getDataInizio().clone();
		dx=(GregorianCalendar)r2_1.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d2_1++;
		}
		d2_2=0;
		sx=(GregorianCalendar)r2_2.getDataInizio().clone();
		dx=(GregorianCalendar)r2_2.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d2_2++;
		}
		d3_1=0;
		sx=(GregorianCalendar)r3_1.getDataInizio().clone();
		dx=(GregorianCalendar)r3_1.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d3_1++;
		}
		d3_2=0;
		sx=(GregorianCalendar)r3_2.getDataInizio().clone();
		dx=(GregorianCalendar)r3_2.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d3_2++;
		}
		//Priorità diverse
		//Alta-Media
		assertTrue(GreedyEngine.sortByPriority(r1_2, r2_1, d1_2, d2_1));
		assertFalse(GreedyEngine.sortByDuration(r1_2, r2_1, d1_2, d2_1));
		assertTrue(GreedyEngine.sortByPriority(r1_1, r2_2, d1_1, d2_2));
		assertTrue(GreedyEngine.sortByDuration(r1_1, r2_2, d1_1, d2_2));
		//Alta-Bassa
		assertTrue(GreedyEngine.sortByPriority(r1_2, r3_1, d1_2, d3_1));
		assertFalse(GreedyEngine.sortByDuration(r1_2, r3_1, d1_2, d3_1));
		assertTrue(GreedyEngine.sortByPriority(r1_1, r3_2, d1_1, d3_2));
		assertTrue(GreedyEngine.sortByDuration(r1_1, r3_2, d1_1, d3_2));
		//Media-Alta
		assertFalse(GreedyEngine.sortByPriority(r2_1, r1_2, d2_1, d1_2));
		assertTrue(GreedyEngine.sortByDuration(r2_1, r1_2, d2_1, d1_2));
		assertFalse(GreedyEngine.sortByPriority(r2_2, r1_1, d2_2, d1_1));
		assertFalse(GreedyEngine.sortByDuration(r2_2, r1_1, d2_2, d1_1));
		//Media-Bassa
		assertTrue(GreedyEngine.sortByPriority(r2_2, r3_1, d2_2, d3_1));
		assertFalse(GreedyEngine.sortByDuration(r2_2, r3_1, d2_2, d3_1));
		assertTrue(GreedyEngine.sortByPriority(r2_1, r3_2, d2_1, d3_2));
		assertTrue(GreedyEngine.sortByDuration(r2_1, r3_2, d2_1, d3_2));
		//Bassa-Alta
		assertFalse(GreedyEngine.sortByPriority(r3_1, r1_2, d3_1, d1_2));
		assertTrue(GreedyEngine.sortByDuration(r3_1, r1_2, d3_1, d1_2));
		assertFalse(GreedyEngine.sortByPriority(r3_2, r1_1, d3_2, d1_1));
		assertFalse(GreedyEngine.sortByDuration(r3_2, r1_1, d3_2, d1_1));
		//Bassa-Media
		assertFalse(GreedyEngine.sortByPriority(r3_1, r2_2, d3_1, d2_2));
		assertTrue(GreedyEngine.sortByDuration(r3_1, r1_2, d3_1, d1_2));
		assertFalse(GreedyEngine.sortByPriority(r3_2, r2_1, d3_2, d2_1));
		assertFalse(GreedyEngine.sortByDuration(r3_2, r1_1, d3_2, d1_1));
	}

	@Test
	public void testSortByDuration() {
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.MEDIA);
		Lavoro l1=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		Lavoro l2=new Lavoro(4,"l2",c,new GregorianCalendar(2014,04,23),new GregorianCalendar(2015,01,23));
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l1.caricaRichiesta(rc, 6, null);
		l2.caricaRichiesta(rc, 7, null);
		Richiesta r1,r2;
		r1=l1.getRichiesta(6);
		r2=l2.getRichiesta(7);
		Integer d1,d2;
		GregorianCalendar sx,dx;
		d1=0;
		sx=(GregorianCalendar)r1.getDataInizio().clone();
		dx=(GregorianCalendar)r1.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d1++;
		}
		d2=0;
		sx=(GregorianCalendar)r2.getDataInizio().clone();
		dx=(GregorianCalendar)r2.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d2++;
		}
		
		//Se le durate sono uguali, vado a valutare le date d'inizio
		assertEquals(GreedyEngine.sortByDuration(r1, r2, d1, d2),GreedyEngine.sortByStartDate(r1, r2));
		assertTrue(GreedyEngine.sortByStartDate(r1, r2));
		assertEquals(GreedyEngine.sortByDuration(r2, r1, d2, d1),GreedyEngine.sortByStartDate(r2, r1));
		assertFalse(GreedyEngine.sortByStartDate(r2, r1));
		
		//Se le durate sono diverse (nei lavori), allora le valuto
		l1.setDataInizio(new GregorianCalendar(2014,03,01));
		l2.setDataInizio(new GregorianCalendar(2014,03,02));
		l1.setDataFine(new GregorianCalendar(2014,03,15));
		l2.setDataFine(new GregorianCalendar(2014,03,07));
		d1=0;
		sx=(GregorianCalendar)r1.getDataInizio().clone();
		dx=(GregorianCalendar)r1.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d1++;
		}
		d2=0;
		sx=(GregorianCalendar)r2.getDataInizio().clone();
		dx=(GregorianCalendar)r2.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d2++;
		}
		//d1>d2, quindi sortByDuration(r1, r2, d1, d2) restituisce false anche se sortByStartDate(r1, r2) è true
		assertFalse(GreedyEngine.sortByDuration(r1, r2, d1, d2));
		assertTrue(GreedyEngine.sortByStartDate(r1, r2));
		//d2>d1, quindi sortByDuration(r2, r1, d2, d1) restituisce true anche se sortByStartDate(r2, r1) è false
		assertTrue(GreedyEngine.sortByDuration(r2, r1, d2, d1));
		assertFalse(GreedyEngine.sortByStartDate(r2, r1));
	}

	@Test
	public void testSortByStartDate() {
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.MEDIA);
		Lavoro l1=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		Lavoro l2=new Lavoro(4,"l2",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l1.caricaRichiesta(rc, 6, null);
		l2.caricaRichiesta(rc, 7, null);
		Richiesta r1,r2;
		r1=l1.getRichiesta(6);
		r2=l2.getRichiesta(7);
		
		//Se le date iniziali sono uguali, vado a valutare i codici
		assertEquals(GreedyEngine.sortByStartDate(r1, r2),GreedyEngine.sortByCodes(r1, r2));
		assertTrue(GreedyEngine.sortByCodes(r1, r2));
		assertEquals(GreedyEngine.sortByStartDate(r2, r1),GreedyEngine.sortByCodes(r2, r1));
		assertFalse(GreedyEngine.sortByCodes(r2, r1));
		
		//Se le date sono diverse (nei lavori), allora le valuto
		l2.setDataInizio(new GregorianCalendar(2014,03,12));
		//r1>r2, quindi sortByStartDate(r1, r2) restituisce false anche se sortByCodes(r1, r2) è true
		assertFalse(GreedyEngine.sortByStartDate(r1, r2));
		assertTrue(GreedyEngine.sortByCodes(r1, r2));
		//r1>r2, quindi sortByStartDate(r2, r1) restituisce true anche se sortByCodes(r2, r1) è false
		assertTrue(GreedyEngine.sortByStartDate(r2, r1));
		assertFalse(GreedyEngine.sortByCodes(r2, r1));
		
	}

	@Test
	public void testSortByCodes() {
		Cantiere c1=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.MEDIA);
		Cantiere c2=new Cantiere(2,"c2","Milano",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.MEDIA);
		
		Lavoro l1=new Lavoro(3,"l1",c1,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		Lavoro l2=new Lavoro(4,"l2",c1,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		Lavoro l3=new Lavoro(10,"l3",c2,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		c1.addLavoro(l1);
		c1.addLavoro(l2);
		c2.addLavoro(l3);
		
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l1.caricaRichiesta(rc, 6, null);
		l1.caricaRichiesta(rc, 7, null);
		l2.caricaRichiesta(rc, 10, null);
		l3.caricaRichiesta(rc, 17, null);
		
		Richiesta r1,r2;
	
		//Diverso cantiere
		r1=l1.getRichiesta(6);
		r2=l3.getRichiesta(17);
		assertTrue(GreedyEngine.sortByCodes(r1, r2));
		assertFalse(GreedyEngine.sortByCodes(r2, r1));
		
		//Uguale cantiere, diverso lavoro
		r2=l2.getRichiesta(10);
		assertTrue(GreedyEngine.sortByCodes(r1, r2));
		assertFalse(GreedyEngine.sortByCodes(r2, r1));
		
		//Uguale cantiere, uguale lavoro, diversa richiesta
		r2=l1.getRichiesta(7);
		assertTrue(GreedyEngine.sortByCodes(r1, r2));
		assertFalse(GreedyEngine.sortByCodes(r2, r1));
		
		//Stessa richiesta
		assertFalse(GreedyEngine.sortByCodes(r1, r1));
		
		
	}

}
