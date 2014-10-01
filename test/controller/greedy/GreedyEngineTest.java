package controller.greedy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.organizer.data.Camion;
import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Priority;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaRuspa;
import model.organizer.data.Ruspa;

import org.junit.Test;

import controller.data.Associazione;
import controller.data.Prenotazione;

public class GreedyEngineTest {

	@Test
	public void testGenerateAssociations() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertAssociation() {
		ArrayList<Associazione>a=new ArrayList<Associazione>();
		assertTrue(a.isEmpty());
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		GreedyEngine.insertAssociation(new Richiesta(rc,null,20), new Camion(1,"Yamaha","Camion",15,15,15), a);
		assertFalse(a.isEmpty());
		assertEquals(a.size(),1);
		assertTrue(a.get(0).getMacchina().equals(new Camion(1,"Yamaha","Camion",15,15,15)));
		assertTrue(a.get(0).getRichiesta().equals(new Richiesta(rc,null,20)));
		RichiestaRuspa rr=new RichiestaRuspa(10,20,10,20,10,20);
		GreedyEngine.insertAssociation(new Richiesta(rr,null,21), new Ruspa(40,"Yamaha","Ruspa",15,15,15), a);
		assertFalse(a.isEmpty());
		assertEquals(a.size(),2);
		assertTrue(a.get(0).getMacchina().equals(new Camion(1,"Yamaha","Camion",15,15,15)));
		assertTrue(a.get(0).getRichiesta().equals(new Richiesta(rc,null,20)));
		assertTrue(a.get(1).getMacchina().equals(new Ruspa(40,"Yamaha","Ruspa",15,15,15)));
		assertTrue(a.get(1).getRichiesta().equals(new Richiesta(rr,null,21)));
	}

	@Test
	public void testSelectMacchinaWithoutReservation() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveReservationsByRequest() {
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.MEDIA);
		Lavoro l=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,20));
		c.addLavoro(l);
		ArrayList<Prenotazione>p=new ArrayList<Prenotazione>();
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		Richiesta ric1=new Richiesta(rc,l,20);
		Richiesta ric2=new Richiesta(rc,l,30);
		p=GreedyEngine.removeReservationsByRequest(p, ric1);
		assertTrue(p.isEmpty());
		Associazione a1=new Associazione(ric1,new Camion(1,"Yamaha","Camion",15,15,15));
		Prenotazione p1=new Prenotazione(a1,10);
		Associazione a2=new Associazione(ric1,new Camion(2,"Yamaha","Camion",15,15,15));
		Prenotazione p2=new Prenotazione(a2,10);
		Associazione a3=new Associazione(ric2,new Camion(3,"Yamaha","Camion",15,15,15));
		Prenotazione p3=new Prenotazione(a3,10);
		Associazione a4=new Associazione(ric2,new Camion(4,"Yamaha","Camion",15,15,15));
		Prenotazione p4=new Prenotazione(a4,10);
		p.add(p1);
		p.add(p3);
		p.add(p2);
		p.add(p4);
		assertEquals(p.size(),4);
		p=GreedyEngine.removeReservationsByRequest(p, new Richiesta(rc,null,40));
		assertEquals(p.size(),4);
		assertTrue(p.contains(p1));
		assertTrue(p.contains(p2));
		assertTrue(p.contains(p3));
		assertTrue(p.contains(p4));
		p=GreedyEngine.removeReservationsByRequest(p, ric1);
		assertEquals(2,p.size());
		assertFalse(p.contains(p1));
		assertFalse(p.contains(p2));
		assertTrue(p.contains(p3));
		assertTrue(p.contains(p4));
		p=GreedyEngine.removeReservationsByRequest(p, ric2);
		assertTrue(p.isEmpty());
		assertFalse(p.contains(p1));
		assertFalse(p.contains(p2));
		assertFalse(p.contains(p3));
		assertFalse(p.contains(p4));
	}

	@Test
	public void testSelectMostPromisingReservation() {
		ArrayList<Associazione>a=new ArrayList<Associazione>();
		ArrayList<Prenotazione>p=new ArrayList<Prenotazione>();
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.MEDIA);
		Lavoro l=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,20));
		c.addLavoro(l);
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l.caricaRichiesta(rc, 10, null);
		Richiesta r=l.getRichiesta(10);
		//Senza alcuna prenotazione, restituisce null
		assertEquals(GreedyEngine.selectMostPromisingReservation(a,p,r),null);
		Camion cam1=new Camion(20, "Yamaha", "Camioncino", 15, 15, 15);
		Prenotazione p1=new Prenotazione(new Associazione(r,cam1),10 );
		p.add(p1);
		//Con una prenotazione, restituisce l'unica disponibile
		assertEquals(GreedyEngine.selectMostPromisingReservation(a,p,r),p1);
		Camion cam2=new Camion(21, "Yamaha", "Camioncino", 15, 15, 15);
		Prenotazione p2=new Prenotazione(new Associazione(r,cam2),4 );
		p.add(p2);
		//Con due prenotazioni, restituisce la più promettente
		assertEquals(GreedyEngine.selectMostPromisingReservation(a,p,r),p2);
		Camion cam3=new Camion(22, "Yamaha", "Camioncino", 15, 15, 15);
		Prenotazione p3=new Prenotazione(new Associazione(r,cam3),2 );
		p.add(p3);
		Camion cam4=new Camion(23, "Yamaha", "Camioncino", 15, 15, 15);
		Prenotazione p4=new Prenotazione(new Associazione(r,cam4),20 );
		p.add(p4);
		//Con più prenotazioni, restituisce la più promettente
		assertEquals(GreedyEngine.selectMostPromisingReservation(a,p,r),p3);
		Ruspa rus=new Ruspa(40,"Yamaha","Ruspa",15,15,15);
		RichiestaRuspa rr=new RichiestaRuspa(10,20,10,20,10,20);
		Prenotazione p5=new Prenotazione(new Associazione(new Richiesta(rr,l,40),rus),1);
		p.add(p5);
		//Considera solo le prenotazioni per la richiesta corrente, ignorando le prenotazioni più promettenti di altri
		assertEquals(GreedyEngine.selectMostPromisingReservation(a,p,r),p3);
		Lavoro l2=new Lavoro(4,"l2",c,new GregorianCalendar(2014,04,05),new GregorianCalendar(2014,04,15));
		a.add(new Associazione(new Richiesta(rc,l2,11), cam3));
		//Se la prenotazione più promettente è già occupata, seleziona la più promettente libera
		assertEquals(GreedyEngine.selectMostPromisingReservation(a,p,r),p2);
		//Anche se ci sono prenotazioni promettenti per altre richieste, restituisce null se non ce ne sono per la richiesta attuale
		assertEquals(GreedyEngine.selectMostPromisingReservation(a,p,new Richiesta(rc,l2,15)),null);
		a.add(new Associazione(new Richiesta(rc,l2,16), cam1));
		a.add(new Associazione(new Richiesta(rc,l2,17), cam2));
		a.add(new Associazione(new Richiesta(rc,l2,18), cam4));
		//Se tutte le prenotazioni per tale richiesta sono occupate, restituisce null
		assertEquals(GreedyEngine.selectMostPromisingReservation(a,p,r),null);
		a.clear();
		r.setMacchina(new Camion(22, "Yamaha", "Camioncino", 15, 15, 15));
		//Se la richiesta attuale è già soddisfatta, anche se ha prenotazioni disponibili, restituisco null
		assertEquals(GreedyEngine.selectMostPromisingReservation(a,p,r),null);
		r.setMacchina(null);
		a.add(new Associazione(r, cam3));
		//Se la richiesta attuale è già stata associata, ma non è ancora soddisfatta, restituisco null
		assertEquals(GreedyEngine.selectMostPromisingReservation(a,p,r),null);
		
	}

	@Test
	public void testReserveMacchineFromLavoro() {
		ArrayList<Prenotazione>p=new ArrayList<Prenotazione>();
		assertTrue(p.isEmpty());
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.MEDIA);
		Lavoro l1=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,20));
		Lavoro l2=new Lavoro(12,"l2",c,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,8));
		Lavoro l3=new Lavoro(33,"l3",c,new GregorianCalendar(2014,04,9),new GregorianCalendar(2014,04,15));
		Lavoro l4=new Lavoro(44,"l4",c,new GregorianCalendar(2015,04,9),new GregorianCalendar(2015,04,15));
		c.addLavoro(l1);
		c.addLavoro(l2);
		c.addLavoro(l3);
		c.addLavoro(l4);
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l1.caricaRichiesta(rc, 10, null);
		Richiesta r=l1.getRichiesta(10);
		//Caso null
		GreedyEngine.reserveMacchineFromLavoro(r,null,p);
		assertTrue(p.isEmpty());
		//Caso lavoro senza alcuna richiesta
		GreedyEngine.reserveMacchineFromLavoro(r,l2,p);
		assertTrue(p.isEmpty());
		//Caso lavoro con richieste non soddisfatte
		l2.caricaRichiesta(rc, 11, null);
		GreedyEngine.reserveMacchineFromLavoro(r,l2,p);
		assertTrue(p.isEmpty());
		//Caso lavoro con richieste soddisfatte che però non possono soddisfare ric
		l2.caricaRichiesta(new RichiestaRuspa(10,20,10,20,10,20), 12, new Ruspa(30,"Yamaha","Ruspa",15,15,15));
		l2.caricaRichiesta(new RichiestaCamion(5,10,5,10,5,10), 13, new Camion(40,"Yamaha","Camioncino",7,7,7));
		GreedyEngine.reserveMacchineFromLavoro(r,l2,p);
		assertTrue(p.isEmpty());
		//Caso lavoro con richieste soddisfatte che soddisfano ric ma non sono libere per ric
		Camion cam=new Camion(40,"Yamaha","Camion",15,15,15);
		l3.caricaRichiesta(new RichiestaCamion(10,20,10,20,10,20), 14, cam);
		l2.caricaRichiesta(new RichiestaCamion(10,20,10,20,10,20), 15, cam);
		assertFalse(cam.isFree(l1.getDataInizio(), l1.getDataFine()));
		GreedyEngine.reserveMacchineFromLavoro(r,l2,p);
		assertTrue(p.isEmpty());
		//Caso lavoro con richieste soddisfatte che soddisfano ric e che sono libere per ric
		Camion c1=new Camion(41,"Yamaha","Camion",15,15,15);
		Camion c2=new Camion(41,"Yamaha","Camion",15,15,15);
		l2.caricaRichiesta(new RichiestaCamion(10,20,10,20,10,20), 16, c1);
		l2.caricaRichiesta(new RichiestaCamion(10,20,10,20,10,20), 17, c2);
		l4.caricaRichiesta(new RichiestaCamion(10,20,10,20,10,20), 18, c2);
		GreedyEngine.reserveMacchineFromLavoro(r,l2,p);
		assertFalse(p.isEmpty());
		assertEquals(p.size(),2);
		assertEquals(p.get(0).getRichiesta(),r);
		assertEquals(p.get(0).getMacchina(),c1);
		assertEquals(new Integer(7),p.get(0).getDurataLavoro());
		assertEquals(p.get(1).getRichiesta(),r);
		assertEquals(p.get(1).getMacchina(),c2);
		assertEquals(new Integer(7),p.get(1).getDurataLavoro());
	}

	@Test
	public void testLavoroEndsLessThanAWeekBefore() {
		Cantiere c1=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		Lavoro base=new Lavoro(11,"l1",c1,new GregorianCalendar(2014,04,15),new GregorianCalendar(2014,04,20));
		Lavoro element=new Lavoro(12,"l2",c1,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,02));
		assertFalse(GreedyEngine.lavoroEndsLessThanAWeekBefore(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,13));
		assertTrue(GreedyEngine.lavoroEndsLessThanAWeekBefore(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,11));
		assertTrue(GreedyEngine.lavoroEndsLessThanAWeekBefore(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,18));
		assertFalse(GreedyEngine.lavoroEndsLessThanAWeekBefore(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,17));
		assertFalse(GreedyEngine.lavoroEndsLessThanAWeekBefore(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,25));
		assertFalse(GreedyEngine.lavoroEndsLessThanAWeekBefore(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,23));
		assertFalse(GreedyEngine.lavoroEndsLessThanAWeekBefore(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,31));
		assertFalse(GreedyEngine.lavoroEndsLessThanAWeekBefore(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,30));
		assertFalse(GreedyEngine.lavoroEndsLessThanAWeekBefore(element,base));
	}

	@Test
	public void testLavoroStartsLessThanAWeekAfter() {
		Cantiere c1=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priority.ALTA);
		Lavoro base=new Lavoro(11,"l1",c1,new GregorianCalendar(2014,04,15),new GregorianCalendar(2014,04,20));
		Lavoro element=new Lavoro(12,"l2",c1,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,02));
		assertFalse(GreedyEngine.lavoroStartsLessThanAWeekAfter(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,13));
		assertFalse(GreedyEngine.lavoroStartsLessThanAWeekAfter(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,11));
		assertFalse(GreedyEngine.lavoroStartsLessThanAWeekAfter(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,18));
		assertFalse(GreedyEngine.lavoroStartsLessThanAWeekAfter(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,17));
		assertFalse(GreedyEngine.lavoroStartsLessThanAWeekAfter(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,25));
		assertFalse(GreedyEngine.lavoroStartsLessThanAWeekAfter(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,23));
		assertTrue(GreedyEngine.lavoroStartsLessThanAWeekAfter(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,29));
		assertTrue(GreedyEngine.lavoroStartsLessThanAWeekAfter(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,28));
		assertFalse(GreedyEngine.lavoroStartsLessThanAWeekAfter(element,base));
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
		
		//Priorità uguali
		//Alta-Alta
		assertEquals(GreedyEngine.sortByPriority(r1_1, r1_2, d1_1, d1_2),GreedyEngine.sortByDuration(r1_1, r1_2, d1_1, d1_2));
		assertTrue(GreedyEngine.sortByDuration(r1_1, r1_2, d1_1, d1_2));
		assertEquals(GreedyEngine.sortByPriority(r1_2, r1_1, d1_2, d1_1),GreedyEngine.sortByDuration(r1_2, r1_1, d1_2, d1_1));
		assertFalse(GreedyEngine.sortByPriority(r1_2, r1_1, d1_2, d1_1));
		//Media-Media
		assertEquals(GreedyEngine.sortByPriority(r2_1, r2_2, d2_1, d2_2),GreedyEngine.sortByDuration(r2_1, r2_2, d2_1, d2_2));
		assertTrue(GreedyEngine.sortByDuration(r2_1, r2_2, d2_1, d2_2));
		assertEquals(GreedyEngine.sortByPriority(r2_2, r2_1, d2_2, d2_1),GreedyEngine.sortByDuration(r2_2, r2_1, d2_2, d2_1));
		assertFalse(GreedyEngine.sortByPriority(r2_2, r2_1, d2_2, d2_1));
		//Bassa-Bassa
		assertEquals(GreedyEngine.sortByPriority(r3_1, r3_2, d3_1, d3_2),GreedyEngine.sortByDuration(r3_1, r3_2, d3_1, d3_2));
		assertTrue(GreedyEngine.sortByDuration(r3_1, r3_2, d3_1, d3_2));
		assertEquals(GreedyEngine.sortByPriority(r3_2, r3_1, d3_2, d3_1),GreedyEngine.sortByDuration(r3_2, r3_1, d3_2, d3_1));
		assertFalse(GreedyEngine.sortByPriority(r3_2, r3_1, d3_2, d3_1));
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
