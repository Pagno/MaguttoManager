package model.organizer.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

public class RichiestaTest {
	Richiesta r;
	Lavoro lavoro;
	Cantiere cantiere;
	
	public RichiestaTest(){
		cantiere=new Cantiere(23,"Bottanuco","via Chiusa,18",new GregorianCalendar(2014, 9, 24),new GregorianCalendar(2015,7,12),Priorita.ALTA);
		lavoro=new Lavoro(5,"Scavi",cantiere, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		cantiere.aggiungiLavoro(lavoro);
		r=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),lavoro,22);
	}
	
	@Test
	public void testRichiestaRichiestaMacchina() {
		int code;
		code=Richiesta.getNextCodice();
		r=new Richiesta(new RichiestaCamion(10,20,10,20,10,20),lavoro);
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
		assertEquals(Richiesta.getNextCodice(),105);
		assertEquals(r.getMacchina(),null);
		assertFalse(r.isSoddisfatta());
	}

	@Test
	public void testInitCodice() {
		Richiesta.initCodice();
		assertEquals(Richiesta.getNextCodice(),1);
	}
	
	/*@Test
	public void testSetCaratteristiche() {
		r.setCaratteristiche(new RichiestaCamion(10,20,10,20,10,20));
		assertEquals(r.getCaratteristiche(),new RichiestaCamion(10,20,10,20,10,20));
	}*/

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
		Richiesta a=new Richiesta(null,lavoro,22);
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		assertFalse(a.equals(new Richiesta(null,lavoro,23)));
		assertTrue(a.equals(new Richiesta(null,lavoro,22)));
		assertTrue(r.equals(new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),lavoro,22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(6,10,5,10,5,10),lavoro,22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,11,5,10,5,10),lavoro,22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,10,6,10,5,10),lavoro,22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,10,5,11,5,10),lavoro,22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,10,5,10,6,10),lavoro,22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,10,5,10,5,11),lavoro,22)));
		assertFalse(r.equals(new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),lavoro,23)));
		a=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),lavoro,22);
		assertTrue(r.equals(a));
		assertTrue(a.equals(r));
		a.setMacchina(new Ruspa(7,"Yamaha","Ruspa",7,7,7));
		assertFalse(r.equals(a));
		assertFalse(a.equals(r));
		
		
		//Ruspa ruspa=new Ruspa(7,"Yamaha","Ruspa",7,7,7);
		//r.setMacchina(ruspa);
		//assertTrue(r.equals(a));
		//assertTrue(a.equals(r));
		
		
		
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
		Lavoro l1=new Lavoro(10,"l1",cantiere,new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta b=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),l1,22);
		assertFalse(r.equals(b));
		assertFalse(b.equals(r));
		Richiesta c=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),null,22);
		assertFalse(r.equals(c));
		assertFalse(c.equals(r));
		Richiesta d=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),null,22);
		assertTrue(c.equals(d));
		assertTrue(d.equals(c));
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
	public void testGetProssimoCodice() {
		assertEquals(Richiesta.getNextCodice(),23);
		Richiesta.initCodice();
		assertEquals(Richiesta.getNextCodice(),1);
		r=new Richiesta(new RichiestaCamion(10,20,10,20,10,20),lavoro);
		assertEquals(Richiesta.getNextCodice(),2);
		r=new Richiesta(new RichiestaCamion(10,20,10,20,10,20),lavoro,99);
		assertEquals(Richiesta.getNextCodice(),100);
		r=new Richiesta(new RichiestaCamion(10,20,10,20,10,20),lavoro);
		assertEquals(Richiesta.getNextCodice(),101);
	}
	@Test
	public void testCompareTo() {
		r=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),lavoro,22);
		r=new Richiesta(new RichiestaCamion(10,20,10,20,10,20),lavoro,12);
		assertEquals(0, r.compareTo(r));
		
		Lavoro lavoro2=new Lavoro(5,"Scavi",cantiere, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta r2=new Richiesta(new RichiestaCamion(10,20,10,20,10,20),lavoro2,22);
		
		assertEquals(-10,r.compareTo(r2));
		
		lavoro2=new Lavoro(5,"Scavi",cantiere, new GregorianCalendar(2014, 10, 01),new GregorianCalendar(2014, 11, 1));
		r2=new Richiesta(new RichiestaCamion(10,20,10,20,10,20),lavoro2,22);
		

		assertEquals(-1,r.compareTo(r2));
		
		
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
		assertTrue(r.getMacchina() instanceof Ruspa);
		assertEquals(r.getMacchina().getCodice(),10);
		assertEquals(r.getMacchina().getProduttore(),"Pippo");
		assertEquals(r.getMacchina().getModello(),"Pippo");
		assertEquals(((Ruspa)r.getMacchina()).getCapacitaMassima(),7);
		assertEquals(((Ruspa)r.getMacchina()).getAltezzaMassima(),7);
		assertEquals(((Ruspa)r.getMacchina()).getPortataMassima(),7);
		r.setMacchina(new Camion(10,"Pippo","Pippo",7,7,7));
		assertFalse(r.isSoddisfatta());
		assertEquals(r.getMacchina(),null);
		r.setMacchina(new Ruspa(10,"Pippo","Pippo",7,7,7));
		r.setMacchina(null);
		assertFalse(r.isSoddisfatta());
		assertEquals(r.getMacchina(),null);
	}

	@Test
	public void testGetDataInizio() {
		assertEquals(r.getDataInizio(),new GregorianCalendar(2014, 9, 01));
	}
	
	@Test
	public void testGetDataFine() {
		assertEquals(r.getDataFine(),new GregorianCalendar(2014, 11, 1));
	}
	
	@Test
	public void testGetPriorita(){
		assertEquals(r.getPriorita(),Priorita.ALTA);
	}
	
	@Test
	public void testGetCodiceLavoro(){
		assertEquals(r.getCodiceLavoro(),5);
	}
	
	@Test
	public void testGetCodiceCantiere(){
		assertEquals(r.getCodiceCantiere(),23);
	}
	
	@Test
	public void testGetLavoro(){
		assertEquals(r.getLavoro(),lavoro);
	}
	
	@Test
	public void testGetLavoriConnessi(){
		Lavoro l2=new Lavoro(6,"Fondamenta",cantiere, new GregorianCalendar(2015, 9, 01),new GregorianCalendar(2015, 11, 1));
		cantiere.aggiungiLavoro(l2);
		Lavoro l3=new Lavoro(7,"Muratura",cantiere, new GregorianCalendar(2016, 9, 01),new GregorianCalendar(2016, 11, 1));
		cantiere.aggiungiLavoro(l3);
		ArrayList<Lavoro>test=new ArrayList<Lavoro>();
		test.add(lavoro);
		test.add(l2);
		test.add(l3);
		assertEquals(r.getLavoriConnessi(),lavoro.getLavoriConnessi());
		assertEquals(r.getLavoriConnessi(),test);
	}
	
	@Test
	public void testGetDurata(){
		assertEquals(r.getDurata(),lavoro.getDurata());
	}
	
	@Test
	public void testGetData(){
		ArrayList<String>test=r.getData();
		assertEquals(test.get(0),"22");
		assertEquals(test.get(1),"Ruspa");
		assertEquals(test.get(2),"5");//MinCapacita
		assertEquals(test.get(3),"10");//MaxCapacita
		assertEquals(test.get(4),"5");//MinPortata
		assertEquals(test.get(5),"10");//MaxPortata
		assertEquals(test.get(6),"0");//MinLunghezza
		assertEquals(test.get(7),"0");//MaxLunghezza
		assertEquals(test.get(8),"5");//MinAltezza
		assertEquals(test.get(9),"10");//MaxAltezza
		assertEquals(test.get(10),"0");//MinProfondita
		assertEquals(test.get(11),"0");//MaxProfondita
		assertEquals(test.get(12),"0");//MinAngoloRotazione
		assertEquals(test.get(13),"0");//MaxAngoloRotazione
		assertEquals(test.size(),14);
		RichiestaRuspa rr=new RichiestaRuspa(1,2,3,4,5,6);
		r=new Richiesta(rr, lavoro);
		test=r.getData();
		assertEquals(test.get(0),"101");
		assertEquals(test.get(1),"Ruspa");
		assertEquals(test.get(2),"1");//MinCapacita
		assertEquals(test.get(3),"2");//MaxCapacita
		assertEquals(test.get(4),"3");//MinPortata
		assertEquals(test.get(5),"4");//MaxPortata
		assertEquals(test.get(6),"0");//MinLunghezza
		assertEquals(test.get(7),"0");//MaxLunghezza
		assertEquals(test.get(8),"5");//MinAltezza
		assertEquals(test.get(9),"6");//MaxAltezza
		assertEquals(test.get(10),"0");//MinProfondita
		assertEquals(test.get(11),"0");//MaxProfondita
		assertEquals(test.get(12),"0");//MinAngoloRotazione
		assertEquals(test.get(13),"0");//MaxAngoloRotazione
		assertEquals(test.size(),14);
		RichiestaCamion rc=new RichiestaCamion(1, 2, 3, 4, 5, 6);
		r=new Richiesta(rc, lavoro);
		test=r.getData();
		assertEquals(test.get(0),"102");
		assertEquals(test.get(1),"Camion");
		assertEquals(test.get(2),"1");//MinCapacita
		assertEquals(test.get(3),"2");//MaxCapacita
		assertEquals(test.get(4),"3");//MinPortata
		assertEquals(test.get(5),"4");//MaxPortata
		assertEquals(test.get(6),"5");//MinLunghezza
		assertEquals(test.get(7),"6");//MaxLunghezza
		assertEquals(test.get(8),"0");//MinAltezza
		assertEquals(test.get(9),"0");//MaxAltezza
		assertEquals(test.get(10),"0");//MinProfondita
		assertEquals(test.get(11),"0");//MaxProfondita
		assertEquals(test.get(12),"0");//MinAngoloRotazione
		assertEquals(test.get(13),"0");//MaxAngoloRotazione
		assertEquals(test.size(),14);
		RichiestaGru rg=new RichiestaGru(1, 2, 3, 4, 5, 6, 7, 8);
		r=new Richiesta(rg, lavoro);
		test=r.getData();
		assertEquals(test.get(0),"103");
		assertEquals(test.get(1),"Gru");
		assertEquals(test.get(2),"0");//MinCapacita
		assertEquals(test.get(3),"0");//MaxCapacita
		assertEquals(test.get(4),"5");//MinPortata
		assertEquals(test.get(5),"6");//MaxPortata
		assertEquals(test.get(6),"1");//MinLunghezza
		assertEquals(test.get(7),"2");//MaxLunghezza
		assertEquals(test.get(8),"3");//MinAltezza
		assertEquals(test.get(9),"4");//MaxAltezza
		assertEquals(test.get(10),"0");//MinProfondita
		assertEquals(test.get(11),"0");//MaxProfondita
		assertEquals(test.get(12),"7");//MinAngoloRotazione
		assertEquals(test.get(13),"8");//MaxAngoloRotazione
		assertEquals(test.size(),14);
		RichiestaEscavatore re=new RichiestaEscavatore(1, 2, 3, 4, 5, 6, 7, 8);
		r=new Richiesta(re, lavoro);
		test=r.getData();
		assertEquals(test.get(0),"104");
		assertEquals(test.get(1),"Escavatore");
		assertEquals(test.get(2),"1");//MinCapacita
		assertEquals(test.get(3),"2");//MaxCapacita
		assertEquals(test.get(4),"3");//MinPortata
		assertEquals(test.get(5),"4");//MaxPortata
		assertEquals(test.get(6),"0");//MinLunghezza
		assertEquals(test.get(7),"0");//MaxLunghezza
		assertEquals(test.get(8),"5");//MinAltezza
		assertEquals(test.get(9),"6");//MaxAltezza
		assertEquals(test.get(10),"7");//MinProfondita
		assertEquals(test.get(11),"8");//MaxProfondita
		assertEquals(test.get(12),"0");//MinAngoloRotazione
		assertEquals(test.get(13),"0");//MaxAngoloRotazione
		assertEquals(test.size(),14);
	}

	@Test
	public void testInConflitto(){
		Richiesta ric1=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),lavoro,1);
		Richiesta ric2=new Richiesta(new RichiestaRuspa(7,12,7,12,7,12),lavoro,2);
		assertTrue(ric1.inConflitto(ric2));
		assertTrue(ric2.inConflitto(ric1));
		ric2=new Richiesta(new RichiestaRuspa(20,30,7,12,7,12),lavoro,2);
		assertFalse(ric1.inConflitto(ric2));
		assertFalse(ric2.inConflitto(ric1));
		ric2=new Richiesta(new RichiestaRuspa(7,12,20,30,7,12),lavoro,2);
		assertFalse(ric1.inConflitto(ric2));
		assertFalse(ric2.inConflitto(ric1));
		ric2=new Richiesta(new RichiestaRuspa(7,12,7,12,20,30),lavoro,2);
		assertFalse(ric1.inConflitto(ric2));
		assertFalse(ric2.inConflitto(ric1));
		
		Richiesta ric3=new Richiesta(new RichiestaCamion(5,10,5,10,5,10),lavoro,1);
		Richiesta ric4=new Richiesta(new RichiestaCamion(7,12,7,12,7,12),lavoro,2);
		assertTrue(ric3.inConflitto(ric4));
		assertTrue(ric4.inConflitto(ric3));
		ric4=new Richiesta(new RichiestaCamion(20,30,7,12,7,12),lavoro,2);
		assertFalse(ric3.inConflitto(ric4));
		assertFalse(ric4.inConflitto(ric3));
		ric4=new Richiesta(new RichiestaCamion(7,12,20,30,7,12),lavoro,2);
		assertFalse(ric3.inConflitto(ric4));
		assertFalse(ric4.inConflitto(ric3));
		ric4=new Richiesta(new RichiestaCamion(7,12,7,12,20,30),lavoro,2);
		assertFalse(ric3.inConflitto(ric4));
		assertFalse(ric4.inConflitto(ric3));
		assertFalse(ric1.inConflitto(ric3));
		assertFalse(ric3.inConflitto(ric1));
		
		Richiesta ric5=new Richiesta(new RichiestaGru(5,10,5,10,5,10,5,10),lavoro,1);
		Richiesta ric6=new Richiesta(new RichiestaGru(7,12,7,12,7,12,7,12),lavoro,2);
		assertTrue(ric5.inConflitto(ric6));
		assertTrue(ric6.inConflitto(ric5));
		ric6=new Richiesta(new RichiestaGru(20,30,7,12,7,12,7,12),lavoro,2);
		assertFalse(ric5.inConflitto(ric6));
		assertFalse(ric6.inConflitto(ric5));
		ric6=new Richiesta(new RichiestaGru(7,12,20,30,7,12,7,12),lavoro,2);
		assertFalse(ric5.inConflitto(ric6));
		assertFalse(ric6.inConflitto(ric5));
		ric6=new Richiesta(new RichiestaGru(7,12,7,12,20,30,7,12),lavoro,2);
		assertFalse(ric5.inConflitto(ric6));
		assertFalse(ric6.inConflitto(ric5));
		ric6=new Richiesta(new RichiestaGru(7,12,7,12,7,12,20,30),lavoro,2);
		assertFalse(ric5.inConflitto(ric6));
		assertFalse(ric6.inConflitto(ric5));
		
		Richiesta ric7=new Richiesta(new RichiestaEscavatore(5,10,5,10,5,10,5,10),lavoro,1);
		Richiesta ric8=new Richiesta(new RichiestaEscavatore(7,12,7,12,7,12,7,12),lavoro,2);
		assertTrue(ric7.inConflitto(ric8));
		assertTrue(ric7.inConflitto(ric8));
		ric8=new Richiesta(new RichiestaEscavatore(20,30,7,12,7,12,7,12),lavoro,2);
		assertFalse(ric7.inConflitto(ric8));
		assertFalse(ric7.inConflitto(ric8));
		ric8=new Richiesta(new RichiestaEscavatore(7,12,20,30,7,12,7,12),lavoro,2);
		assertFalse(ric7.inConflitto(ric8));
		assertFalse(ric7.inConflitto(ric8));
		ric8=new Richiesta(new RichiestaEscavatore(7,12,7,12,20,30,7,12),lavoro,2);
		assertFalse(ric7.inConflitto(ric8));
		assertFalse(ric7.inConflitto(ric8));
		ric8=new Richiesta(new RichiestaEscavatore(7,12,7,12,7,12,20,30),lavoro,2);
		assertFalse(ric7.inConflitto(ric8));
		assertFalse(ric7.inConflitto(ric8));
		assertFalse(ric5.inConflitto(ric7));
		assertFalse(ric7.inConflitto(ric5));
		
		Richiesta ric9=new Richiesta(new RichiestaEscavatore(5,10,5,10,5,10,5,10),lavoro,1);
		Richiesta ric10=new Richiesta(new RichiestaEscavatore(17,112,17,112,17,112,17,112),lavoro,2);
		assertFalse(ric9.inConflitto(ric10));
		assertFalse(ric10.inConflitto(ric9));
		
	}
	@Test
	public void testCollide(){
		//cantiere=new Cantiere(23,"Bottanuco","via Chiusa,18",new GregorianCalendar(2014, 9, 24),new GregorianCalendar(2015,7,12),Priorita.ALTA);
		Lavoro lavoro=new Lavoro(5,"Scavi",cantiere, new GregorianCalendar(2014, 8, 01),new GregorianCalendar(2014, 10, 1));
		//cantiere.aggiungiLavoro(lavoro);
		Richiesta ric1=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),lavoro,22);
		Lavoro lavoro2=new Lavoro(5,"Scavi",cantiere, new GregorianCalendar(2014, 9, 01),new GregorianCalendar(2014, 11, 1));
		Richiesta ric2=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),lavoro2,12);
		assertTrue(ric1.collide(ric2));
		assertTrue(ric2.collide(ric1));
		
		Lavoro lavoro3=new Lavoro(5,"Scavi",cantiere, new GregorianCalendar(2015, 9, 01),new GregorianCalendar(2015, 11, 1));
		Richiesta ric3=new Richiesta(new RichiestaRuspa(5,10,5,10,5,10),lavoro3,12);
		
		assertFalse(ric1.collide(ric3));
		assertFalse(ric3.collide(ric1));
	}
	
	@Test
	public void testGestisciLimiti(){
		int aMin, aMax, bMin, bMax;
		aMin=5;
		aMax=10;
		bMin=15;
		bMax=20;
		assertFalse(Richiesta.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertFalse(Richiesta.gestisciLimiti(bMin, bMax, aMin, aMax));
		aMax=15;
		bMin=10;
		assertTrue(Richiesta.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(Richiesta.gestisciLimiti(bMin, bMax, aMin, aMax));
		aMin=-1;
		aMax=-1;
		assertTrue(Richiesta.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(Richiesta.gestisciLimiti(bMin, bMax, aMin, aMax));
		aMax=15;
		bMin=-1;
		assertTrue(Richiesta.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(Richiesta.gestisciLimiti(bMin, bMax, aMin, aMax));
		bMin=10;
		assertTrue(Richiesta.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(Richiesta.gestisciLimiti(bMin, bMax, aMin, aMax));
		bMin=17;
		assertFalse(Richiesta.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertFalse(Richiesta.gestisciLimiti(bMin, bMax, aMin, aMax));
		aMin=10;
		aMax=-1;
		bMax=-1;
		assertTrue(Richiesta.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(Richiesta.gestisciLimiti(bMin, bMax, aMin, aMax));
		bMax=20;
		assertTrue(Richiesta.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertTrue(Richiesta.gestisciLimiti(bMin, bMax, aMin, aMax));
		bMin=6;
		bMax=7;
		assertFalse(Richiesta.gestisciLimiti(aMin, aMax, bMin, bMax));
		assertFalse(Richiesta.gestisciLimiti(bMin, bMax, aMin, aMax));
	}
}