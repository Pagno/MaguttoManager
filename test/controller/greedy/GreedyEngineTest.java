package controller.greedy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import model.ModelConnector;
import model.organizer.ModelCamion;
import model.organizer.ModelCantiere;
import model.organizer.data.Camion;
import model.organizer.data.Cantiere;
import model.organizer.data.Escavatore;
import model.organizer.data.Lavoro;
import model.organizer.data.Priorita;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaRuspa;
import model.organizer.data.Ruspa;

import org.junit.Test;

import view.MainView;
import controller.ControllerConnector;
import controller.data.Associazione;
import controller.data.Prenotazione;
import database.Database;

public class GreedyEngineTest {

	@Test
	public void testGeneraAssociazioni() {
		Database db=Database.getDatabase();
		ModelConnector m=ModelConnector.getModelConnector(db);
		MainView mainView = new MainView(ControllerConnector.getControllerConnector(m));
		m.ResetAllForTest();
		m.aggiungiCantiere("MoSe","Venezia",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.BASSA);//Cantiere 1
		m.aggiungiLavoro("Paratie", new GregorianCalendar(2014,02,22), new GregorianCalendar(2014,03,22), 1);//Lavoro 1
		
		//Richiesta coperta
		
		m.aggiungiRichiesta(1, 1, new RichiestaCamion(5,10,5,10,5,10));//Richiesta 1
		m.aggiungiCamion("Yamaha", "Camion", 7, 7, 7);//Macchina 1
		m.soddisfaRichiesta(1, 1);
		
		//Richiesta scoperta ma senza macchine per soddisfarla
		
		m.aggiungiRichiesta(1, 1, new RichiestaEscavatore(100,200,100,200,100,200,100,200));//Richiesta 2
		
		//Richiesta scoperta ma senza macchine libere per soddisfarla
		
		m.aggiungiRichiesta(1, 1, new RichiestaCamion(6,8,6,8,6,8));//Richiesta 3
		
		//Due richieste si contendono una macchina, solo la più prioritaria la prende
		
		m.aggiungiCantiere("Pedemontana","Monza",new GregorianCalendar(2014,4,3),new GregorianCalendar(2016,10,19),Priorita.MEDIA);//Cantiere 2
		m.aggiungiLavoro("Scavo", new GregorianCalendar(2014,2,3),new GregorianCalendar(2014,7,7), 2);//Lavoro 2
		m.aggiungiRichiesta(2, 2, new RichiestaRuspa(10, 20, 10, 20, 10, 20));//Richiesta 4
		m.aggiungiRichiesta(1, 1, new RichiestaRuspa(12, 22, 12, 22, 12, 22));//Richiesta 5
		m.aggiungiRuspa("Yamaha", "Ruspa", 15, 15, 15);//Macchina 2
		
		//Richieste di diversi cantieri con lavoro in stesso periodo e richiesta su stessa macchina, 
		//solo quello che ha prenotazione su macchina la prende, l'altra no
		
		m.aggiungiRichiesta(2,2,new RichiestaGru(8,18,8,18,8,18,8,18));//Richiesta 6
		m.aggiungiRichiesta(1,1,new RichiestaGru(7,17,7,17,7,17,7,17));//Richiesta 7
		m.aggiungiLavoro("Svuotamento", new GregorianCalendar(2014,03,24), new GregorianCalendar(2014,05,24), 1);//Lavoro 3
		m.aggiungiRichiesta(1,3,new RichiestaGru(9,19,9,19,9,19,9,19));//Richiesta 8
		m.aggiungiGru("Yamaha", "Gru", 10, 10, 10, 10);//Macchina 3
		m.aggiungiGru("Yamaha", "Gru", 10, 10, 10, 10);//Macchina 4
		m.soddisfaRichiesta(8, 3);
		
		//Tre richieste (priorità alta,media,bassa) soddisfatte da tre macchine, la prima non ha prenotazioni, 
		//la seconda prenota la seconda macchina e la terza prenota la prima e la terza, 
		//verificare che la prima richiesta prende la terza macchina, la seconda prende la seconda macchina 
		//e la terza prende la prima macchina
		
		m.aggiungiCantiere("Aeroporto Bergamo","Orio al Serio",new GregorianCalendar(2014,1,1),new GregorianCalendar(2020,10,10),Priorita.ALTA);//Cantiere 3
		m.aggiungiLavoro("Fondamenta", new GregorianCalendar(2014,6,1), new GregorianCalendar(2014,7,1), 1);//Lavoro 4
		m.aggiungiLavoro("Fondamenta", new GregorianCalendar(2014,6,1), new GregorianCalendar(2014,7,1), 2);//Lavoro 5
		m.aggiungiLavoro("Fondamenta", new GregorianCalendar(2014,6,1), new GregorianCalendar(2014,7,1), 3);//Lavoro 6
		m.aggiungiRichiesta(1,4,new RichiestaCamion(30,40,30,40,30,40));//Richiesta 9 Bassa
		m.aggiungiRichiesta(2,5,new RichiestaCamion(30,40,30,40,30,40));//Richiesta 10 Media
		m.aggiungiRichiesta(3,6,new RichiestaCamion(30,40,30,40,30,40));//Richiesta 11 Alta
		m.aggiungiLavoro("Asfalto", new GregorianCalendar(2014,7,5), new GregorianCalendar(2014,9,1), 2);//Lavoro 7
		m.aggiungiLavoro("Asfalto", new GregorianCalendar(2014,7,5), new GregorianCalendar(2014,9,1), 1);//Lavoro 8
		m.aggiungiRichiesta(2,7,new RichiestaCamion(30,40,30,40,30,40));//Richiesta 12 Media
		m.aggiungiRichiesta(1,8,new RichiestaCamion(30,40,30,40,30,40));//Richiesta 13 Bassa
		m.aggiungiRichiesta(1,8,new RichiestaCamion(30,40,30,40,30,40));//Richiesta 14 Bassa
		m.aggiungiCamion("Yamaha", "Camion Grande", 35, 35, 35);//Macchina 5
		m.aggiungiCamion("Yamaha", "Camion Grande", 35, 35, 35);//Macchina 6
		m.aggiungiCamion("Yamaha", "Camion Grande", 35, 35, 35);//Macchina 7
		m.soddisfaRichiesta(12, 6);
		m.soddisfaRichiesta(13, 5);
		m.soddisfaRichiesta(14, 7);
		
		//Camion, escavatore, gru, ruspa
		
		m.aggiungiRichiesta(1,1,new RichiestaCamion(1,2,1,2,1,2));//Richiesta 15
		m.aggiungiRichiesta(1,1,new RichiestaGru(1,2,1,2,1,2,1,2));//Richiesta 16
		m.aggiungiRichiesta(1,1,new RichiestaRuspa(1,2,1,2,1,2));//Richiesta 17
		m.aggiungiRichiesta(1,1,new RichiestaEscavatore(1,2,1,2,1,2,1,2));//Richiesta 18
		m.aggiungiCamion("Yamaha", "Camioncino", 2, 2, 2);//Macchina 8
		m.aggiungiGru("Yamaha", "Gru piccola", 2, 2, 2, 2);//Macchina 9
		m.aggiungiRuspa("Yamaha", "Ruspetta", 2, 2, 2);//Macchina 10
		m.aggiungiEscavatore("Yamaha", "Escavatore piccolo", 2, 2, 2, 2);//Macchina 10
		
		//Esecuzione metodo

		ArrayList<Associazione>test=GreedyEngine.generaAssociazioni(m);
		
		//Associazioni che devono esser generate per questo caso di test
		
		Associazione asso1=new Associazione(m.getRichiesta(4),m.getMacchina(2));
		Associazione asso2=new Associazione(m.getRichiesta(7),m.getMacchina(3));
		Associazione asso3=new Associazione(m.getRichiesta(11),m.getMacchina(7));
		Associazione asso4=new Associazione(m.getRichiesta(10),m.getMacchina(6));
		Associazione asso5=new Associazione(m.getRichiesta(9),m.getMacchina(5));
		Associazione asso6=new Associazione(m.getRichiesta(15),m.getMacchina(8));
		Associazione asso7=new Associazione(m.getRichiesta(16),m.getMacchina(9));
		Associazione asso8=new Associazione(m.getRichiesta(17),m.getMacchina(10));
		Associazione asso9=new Associazione(m.getRichiesta(18),m.getMacchina(11));
		Associazione asso10=new Associazione(m.getRichiesta(6),m.getMacchina(4));
		asso1.setConfermata(true);
		asso2.setConfermata(true);
		asso3.setConfermata(true);
		asso4.setConfermata(true);
		asso5.setConfermata(true);
		asso6.setConfermata(true);
		asso7.setConfermata(true);
		asso8.setConfermata(true);
		asso9.setConfermata(true);
		asso10.setConfermata(true);
		
		//Controllo

		assertEquals(test.size(),10);
		assertTrue(test.contains(asso1));
		assertTrue(test.contains(asso2));
		assertTrue(test.contains(asso3));
		assertTrue(test.contains(asso4));
		assertTrue(test.contains(asso5));
		assertTrue(test.contains(asso6));
		assertTrue(test.contains(asso7));
		assertTrue(test.contains(asso8));
		assertTrue(test.contains(asso9));
		assertTrue(test.contains(asso10));
		
		//provo a concretizzare alcune delle selezioni, e rilancio l'algoritmo
		for(Associazione a:test){
			if((a.getRichiesta().getCodice()%2)!=0){
				a.concretizza();
			}
		}
		
		test=GreedyEngine.generaAssociazioni(m);
		
		assertEquals(test.size(),5);
		assertTrue(test.contains(asso1));
		assertFalse(test.contains(asso2));
		assertFalse(test.contains(asso3));
		assertTrue(test.contains(asso4));
		assertFalse(test.contains(asso5));
		assertFalse(test.contains(asso6));
		assertTrue(test.contains(asso7));
		assertFalse(test.contains(asso8));
		assertTrue(test.contains(asso9));
		assertTrue(test.contains(asso10));
	}

	@Test
	public void testRubaMacchineAdAssociazioni(){
		ArrayList<Associazione> aLibere=new ArrayList<Associazione>();
		ArrayList<Associazione> aPrenotate=new ArrayList<Associazione>();
		ArrayList<Associazione> check=new ArrayList<Associazione>();
		ArrayList<Richiesta> sortedRichieste=new ArrayList<Richiesta>();
		RichiestaCamion rc1=new RichiestaCamion(1,2,1,2,1,2); 
		
		//Caso nessuna richiesta scoperta
		Camion c1=new Camion(1,"Yamaha","Camion",1,1,1);
		aPrenotate.add(new Associazione(new Richiesta(rc1,null,1),c1));
		aLibere.add(new Associazione(new Richiesta(rc1,null,2),new Camion(2,"Yamaha","Camion",1,1,1)));
		check.addAll(aLibere);
		GreedyEngine.rubaMacchineAdAssociazioni(sortedRichieste, aPrenotate, aLibere);
		assertTrue(sortedRichieste.isEmpty());
		assertEquals(aLibere.size(),2);
		assertTrue(aLibere.containsAll(check));
		Associazione temp1=new Associazione(new Richiesta(rc1,null,1),c1);
		temp1.setConfermata(true);
		assertTrue(aLibere.contains(temp1));
		assertTrue(aPrenotate.isEmpty());
		
		
		//Caso nessuna associazione da prenotazioni
		aPrenotate.clear();
		aLibere.clear();
		aLibere.addAll(check);
		sortedRichieste.add(new Richiesta(rc1,null,1));
		GreedyEngine.rubaMacchineAdAssociazioni(sortedRichieste, aPrenotate, aLibere);
		assertEquals(aLibere.size(),1);
		for(Associazione a:check){
			a.setConfermata(true);
		}
		assertEquals(aLibere,check);
		assertTrue(sortedRichieste.isEmpty());
		assertTrue(aPrenotate.isEmpty());
		
		//Caso con associazioni compatibili ma non inferiori alla richiesta scoperta
		aPrenotate.clear();
		aLibere.clear();
		check.clear();
		sortedRichieste.clear();
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		Lavoro l1=new Lavoro(1,"l1",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,20));
		Lavoro l2=new Lavoro(2,"l2",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,15));
		c.aggiungiLavoro(l1);
		c.aggiungiLavoro(l2);
		l1.caricaRichiesta(rc1, 1, null);
		l2.caricaRichiesta(rc1, 2, null);
		Richiesta r1=l1.getRichiesta(1);
		Richiesta r2=l2.getRichiesta(2);
		aPrenotate.add(new Associazione(r2,c1));
		sortedRichieste.add(r1);
		assertEquals(check,aLibere);
		GreedyEngine.rubaMacchineAdAssociazioni(sortedRichieste, aPrenotate, aLibere);
		assertTrue(sortedRichieste.isEmpty());
		temp1=new Associazione(r2,c1);
		temp1.setConfermata(true);
		assertTrue(aLibere.contains(temp1));
		assertEquals(aLibere.size(),1);
		assertTrue(aPrenotate.isEmpty());
		
		
		//Caso con più associazioni compatibili inferiori, scelgo quella con priorità minore tra tutte
		//La richiesta r4 non riesce a sua volta a rubare una macchina, quindi resta scoperta.
		Lavoro l3=new Lavoro(3,"l3",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,17));
		Lavoro l4=new Lavoro(4,"l4",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,28));
		c.aggiungiLavoro(l3);
		c.aggiungiLavoro(l4);
		l3.caricaRichiesta(rc1, 3, null);
		l4.caricaRichiesta(rc1, 4, null);
		Richiesta r3=l3.getRichiesta(3);
		Richiesta r4=l4.getRichiesta(4);
		Camion c2=new Camion(2,"Yamaha","Camion",1,1,1);
		Camion c3=new Camion(3,"Yamaha","Camion",1,1,1);
		aPrenotate.clear();
		aLibere.clear();
		check.clear();
		sortedRichieste.clear();
		aPrenotate.add(new Associazione(r3,c2));
		aPrenotate.add(new Associazione(r1,c1));
		aPrenotate.add(new Associazione(r4,c3));
		assertEquals(aPrenotate.get(0),new Associazione(r3,c2));
		assertEquals(aPrenotate.get(1),new Associazione(r1,c1));
		assertEquals(aPrenotate.get(2),new Associazione(r4,c3));
		sortedRichieste.add(r2);
		GreedyEngine.rubaMacchineAdAssociazioni(sortedRichieste, aPrenotate, aLibere);
		assertTrue(sortedRichieste.isEmpty());
		assertTrue(aPrenotate.isEmpty());
		temp1=new Associazione(r3,c2);
		Associazione temp2=new Associazione(r1,c1);
		Associazione temp3=new Associazione(r2,c3);
		temp1.setConfermata(true);
		temp2.setConfermata(true);
		temp3.setConfermata(true);
		assertTrue(aLibere.contains(temp1));
		assertTrue(aLibere.contains(temp2));
		assertTrue(aLibere.contains(temp3));
		assertEquals(aLibere.size(),3);
		
		//Caso richiesta con priorità alta ma nessuna associazione a cui rubare
		RichiestaRuspa rr=new RichiestaRuspa(10,20,10,20,10,20);
		l2.caricaRichiesta(rr, 5, null);
		Richiesta r5=l2.getRichiesta(5);
		aPrenotate.clear();
		aLibere.clear();
		aPrenotate.add(new Associazione(r3,c2));
		aPrenotate.add(new Associazione(r1,c1));
		aPrenotate.add(new Associazione(r4,c3));
		sortedRichieste.clear();
		sortedRichieste.add(r5);
		GreedyEngine.rubaMacchineAdAssociazioni(sortedRichieste, aPrenotate, aLibere);
		assertTrue(sortedRichieste.isEmpty());
		assertTrue(aPrenotate.isEmpty());
		temp1=new Associazione(r3,c2);
		temp2=new Associazione(r1,c1);
		temp3=new Associazione(r4,c3);
		temp1.setConfermata(true);
		temp2.setConfermata(true);
		temp3.setConfermata(true);
		assertTrue(aLibere.contains(temp1));
		assertTrue(aLibere.contains(temp2));
		assertTrue(aLibere.contains(temp3));
		assertEquals(aLibere.size(),3);
		
		//Caso richiesta a cui rubo la macchina, che a sua volta può rubare la macchina a un altro
		RichiestaEscavatore re1=new RichiestaEscavatore(10,30,10,20,10,20,10,20);
		RichiestaEscavatore re2=new RichiestaEscavatore(20,50,10,20,10,20,10,20);
		RichiestaEscavatore re3=new RichiestaEscavatore(40,60,10,20,10,20,10,20);
		Escavatore e1=new Escavatore(4,"Yamaha","Escavatore piccolo",25,15,15,15);
		Escavatore e2=new Escavatore(5,"Yamaha","Escavatore grande",45,15,15,15);
		l2.caricaRichiesta(re1, 6, null);
		l3.caricaRichiesta(re2, 7, null);
		l4.caricaRichiesta(re3, 8, null);
		l3.soddisfaRichiesta(7, e1);
		l4.soddisfaRichiesta(8, e2);
		Richiesta r6=l2.getRichiesta(6);
		Richiesta r7=l3.getRichiesta(7);
		Richiesta r8=l4.getRichiesta(8);
		aPrenotate.clear();
		aLibere.clear();
		aPrenotate.add(new Associazione(r7,e1));
		aPrenotate.add(new Associazione(r8,e2));
		sortedRichieste.add(r6);
		GreedyEngine.rubaMacchineAdAssociazioni(sortedRichieste, aPrenotate, aLibere);
		assertTrue(sortedRichieste.isEmpty());
		assertTrue(aPrenotate.isEmpty());
		temp1=new Associazione(r6,e1);
		temp2=new Associazione(r7,e2);
		temp1.setConfermata(true);
		temp2.setConfermata(true);
		assertTrue(aLibere.contains(temp1));
		assertTrue(aLibere.contains(temp2));
		assertEquals(aLibere.size(),2);
	}
	
	@Test
	public void testSelezionaMacchineLibere(){
		Database db=Database.getDatabase();
		ModelConnector m=ModelConnector.getModelConnector(db);
		MainView mainView = new MainView(ControllerConnector.getControllerConnector(m));
		m.ResetAllForTest();
		ArrayList<Associazione>test;
		ArrayList<Associazione>aPrenotate=new ArrayList<Associazione>();
		ArrayList<Associazione>checkPrenotate=new ArrayList<Associazione>();
		ArrayList<Richiesta>sortedRichieste=new ArrayList<Richiesta>();
		ArrayList<Richiesta>check=new ArrayList<Richiesta>();
		m.aggiungiCantiere("c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);//Cantiere 1
		Cantiere c=m.getCantiere(1);
		m.aggiungiLavoro("l1",new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,20),1);//Lavoro 1
		
		//Caso senza macchine libere
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		m.aggiungiRichiesta(1, 1, rc);//Richiesta 1
		Richiesta r1=m.getRichiesta(1);
		m.aggiungiRichiesta(1, 1, rc);//Richiesta 2
		m.aggiungiCamion("Yamaha", "Camioncino", 15, 15, 15);//Macchina 1
		m.soddisfaRichiesta(2, 1);
		sortedRichieste.add(r1);
		check.addAll(sortedRichieste);
		assertEquals(check, sortedRichieste);
		assertEquals(checkPrenotate, aPrenotate);
		test=GreedyEngine.selezionaMacchineLibere(m, sortedRichieste, aPrenotate);
		assertEquals(checkPrenotate, aPrenotate);
		assertEquals(check, sortedRichieste);
		assertTrue(check.contains(r1));
		assertEquals(check.size(),1);
		assertTrue(test.isEmpty());
		
		//Caso con macchine libere ma già associate nella fase a prenotazioni
		m.aggiungiLavoro("l2",new GregorianCalendar(2014,04,21),new GregorianCalendar(2014,04,27),1);//Lavoro 2
		m.aggiungiRichiesta(1, 2, rc);//Richiesta 3
		m.aggiungiCamion("Yamaha", "Camioncino", 15, 15, 15);//Macchina 2
		m.soddisfaRichiesta(3, 2);
		//Genero una falsa associazione per fingere una prenotazione evoluta in associazione
		assertEquals(checkPrenotate, aPrenotate);
		assertTrue(aPrenotate.isEmpty());
		//La richiesta nella associazione fittizia è Richiesta 4
		aPrenotate.add(new Associazione(new Richiesta(rc,new Lavoro(3,"lfittizio",c,new GregorianCalendar(2014,04,9),new GregorianCalendar(2014,06,19))), m.getMacchina(2)));
		checkPrenotate.addAll(aPrenotate);
		assertEquals(check, sortedRichieste);
		assertTrue(check.contains(r1));
		assertEquals(check.size(),1);
		test=GreedyEngine.selezionaMacchineLibere(m, sortedRichieste, aPrenotate);
		assertEquals(checkPrenotate, aPrenotate);
		assertEquals(check, sortedRichieste);
		assertTrue(check.contains(r1));
		assertEquals(check.size(),1);
		assertTrue(test.isEmpty());
		
		//Caso con macchine libere e non associate tramite prenotazioni, ma prenotate in precedenza da una richiesta più prioritaria
		aPrenotate.clear();
		checkPrenotate.clear();
		m.aggiungiLavoro("l3",new GregorianCalendar(2014,04,13),new GregorianCalendar(2014,04,20),1);//Lavoro 3
		m.aggiungiRichiesta(1, 3, rc);//Richiesta 5
		Richiesta r2=m.getRichiesta(5);
		sortedRichieste.clear();
		check.clear();
		check.add(r2);
		check.add(r1);
		sortedRichieste=GreedyEngine.ordinaRichieste(check);
		assertEquals(sortedRichieste.size(),2);
		assertEquals(sortedRichieste.get(0),r2);
		assertEquals(sortedRichieste.get(1),r1);
		check.clear();
		check.addAll(sortedRichieste);
		assertEquals(aPrenotate,checkPrenotate);
		assertTrue(aPrenotate.isEmpty());
		test=GreedyEngine.selezionaMacchineLibere(m, sortedRichieste, aPrenotate);
		assertEquals(checkPrenotate, aPrenotate);
		assertTrue(aPrenotate.isEmpty());
		assertTrue(check.contains(r1));
		assertTrue(check.contains(r2));
		assertTrue(sortedRichieste.contains(r1));
		assertFalse(sortedRichieste.contains(r2));
		assertEquals(check.size(),2);
		assertEquals(sortedRichieste.size(),1);
		assertFalse(test.isEmpty());
		assertEquals(test.size(),1);
		assertTrue(test.contains(new Associazione(r2,m.getMacchina(2))));
		
		//Caso con tutte le richieste soddisfatte
		sortedRichieste.clear();
		check.clear();
		check.add(r2);
		check.add(r1);
		sortedRichieste=GreedyEngine.ordinaRichieste(check);
		assertEquals(sortedRichieste.size(),2);
		assertEquals(sortedRichieste.get(0),r2);
		assertEquals(sortedRichieste.get(1),r1);
		check.clear();
		check.addAll(sortedRichieste);
		assertEquals(aPrenotate,checkPrenotate);
		assertTrue(aPrenotate.isEmpty());
		m.aggiungiRichiesta(1, 2, rc);//Richiesta 6
		m.aggiungiCamion("Yamaha", "Camioncino", 15, 15, 15);//Macchina 3
		m.soddisfaRichiesta(6, 3);
		test=GreedyEngine.selezionaMacchineLibere(m, sortedRichieste, aPrenotate);
		assertEquals(checkPrenotate, aPrenotate);
		assertTrue(aPrenotate.isEmpty());
		assertTrue(check.contains(r1));
		assertTrue(check.contains(r2));
		assertFalse(sortedRichieste.contains(r1));
		assertFalse(sortedRichieste.contains(r2));
		assertEquals(check.size(),2);
		assertTrue(sortedRichieste.isEmpty());
		assertFalse(test.isEmpty());
		assertEquals(test.size(),2);
		assertTrue(test.contains(new Associazione(r2,m.getMacchina(2))));
		assertTrue(test.contains(new Associazione(r1,m.getMacchina(3))));
		
		//Caso camion,ruspa,escavatore,gru
		m.aggiungiRichiesta(1, 1, new RichiestaGru(10,20,10,20,10,20,10,20));//Richiesta 7
		m.aggiungiGru("Yamaha", "Gru", 15, 15, 15, 15);//Macchina 4
		m.aggiungiRichiesta(1, 1, new RichiestaEscavatore(10,20,10,20,10,20,10,20));//Richiesta 8
		m.aggiungiEscavatore("Yamaha", "Escavatore", 15, 15, 15, 15);//Macchina 5
		m.aggiungiRichiesta(1, 1, new RichiestaRuspa(10,20,10,20,10,20));//Richiesta 9
		m.aggiungiRuspa("Yamaha", "Ruspa", 15, 15, 15);//Macchina 6
		sortedRichieste.clear();
		check.clear();
		check.add(r2);
		check.add(r1);
		check.add(m.getRichiesta(7));
		check.add(m.getRichiesta(8));
		check.add(m.getRichiesta(9));
		sortedRichieste=GreedyEngine.ordinaRichieste(check);
		assertEquals(sortedRichieste.size(),5);
		assertEquals(sortedRichieste.get(0),r2);
		assertEquals(sortedRichieste.get(1),r1);
		assertEquals(sortedRichieste.get(2),m.getRichiesta(7));
		assertEquals(sortedRichieste.get(3),m.getRichiesta(8));
		assertEquals(sortedRichieste.get(4),m.getRichiesta(9));
		check.clear();
		check.addAll(sortedRichieste);
		assertEquals(aPrenotate,checkPrenotate);
		assertTrue(aPrenotate.isEmpty());
		test=GreedyEngine.selezionaMacchineLibere(m, sortedRichieste, aPrenotate);
		assertEquals(checkPrenotate, aPrenotate);
		assertTrue(aPrenotate.isEmpty());
		assertTrue(check.contains(r1));
		assertTrue(check.contains(r2));
		assertTrue(check.contains(m.getRichiesta(7)));
		assertTrue(check.contains(m.getRichiesta(8)));
		assertTrue(check.contains(m.getRichiesta(9)));
		assertFalse(sortedRichieste.contains(r1));
		assertFalse(sortedRichieste.contains(r2));
		assertFalse(sortedRichieste.contains(m.getRichiesta(7)));
		assertFalse(sortedRichieste.contains(m.getRichiesta(8)));
		assertFalse(sortedRichieste.contains(m.getRichiesta(9)));
		assertEquals(check.size(),5);
		assertTrue(sortedRichieste.isEmpty());
		assertFalse(test.isEmpty());
		assertEquals(test.size(),5);
		assertTrue(test.contains(new Associazione(r2,m.getMacchina(2))));
		assertTrue(test.contains(new Associazione(r1,m.getMacchina(3))));
		assertTrue(test.contains(new Associazione(m.getRichiesta(7),m.getMacchina(4))));
		assertTrue(test.contains(new Associazione(m.getRichiesta(8),m.getMacchina(5))));
		assertTrue(test.contains(new Associazione(m.getRichiesta(9),m.getMacchina(6))));
	}
	
	@Test
	public void testSelezionaConPrenotazioni(){
		ArrayList<Associazione>test;
		ArrayList<Richiesta>sortedRichieste=new ArrayList<Richiesta>();
		ArrayList<Richiesta>check=new ArrayList<Richiesta>();
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		Lavoro l=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,20));
		c.aggiungiLavoro(l);
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l.caricaRichiesta(rc, 10, null);
		Richiesta r1=l.getRichiesta(10);
		sortedRichieste.add(l.getRichiesta(10));
		check.addAll(sortedRichieste);
		assertEquals(check, sortedRichieste);
		//Senza alcuna prenotazione, non crea associazioni
		Lavoro l2=new Lavoro(12,"l2",c,new GregorianCalendar(2014,04,28),new GregorianCalendar(2014,05,1));
		c.aggiungiLavoro(l2);
		Camion cam1=new Camion(20, "Yamaha", "Camioncino", 15, 15, 15);
		l2.caricaRichiesta(rc, 11, cam1);
		test=GreedyEngine.selezionaConPrenotazioni(sortedRichieste);
		assertTrue(test.isEmpty());
		assertEquals(sortedRichieste,check);
		//Con una prenotazione, restituisce l'unica disponibile
		l2.setDataInizio(new GregorianCalendar(2014,04,22));
		test=GreedyEngine.selezionaConPrenotazioni(sortedRichieste);
		assertEquals(test.size(),1);
		assertEquals(test.get(0),new Associazione(r1,cam1));
		assertEquals(sortedRichieste.size(),check.size()-1);
		assertFalse(sortedRichieste.contains(r1));
		assertTrue(check.contains(r1));
		assertTrue(sortedRichieste.isEmpty());
		//Con due prenotazioni, restituisce la più promettente
		Camion cam2=new Camion(21, "Yamaha", "Camioncino", 15, 15, 15);
		Lavoro l3=new Lavoro(13,"l3",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2014,04,26));
		c.aggiungiLavoro(l3);
		l3.caricaRichiesta(rc, 12, cam2);
		sortedRichieste.clear();
		check.clear();
		sortedRichieste.add(r1);
		check.addAll(sortedRichieste);
		assertEquals(check, sortedRichieste);
		test=GreedyEngine.selezionaConPrenotazioni(sortedRichieste);
		assertEquals(test.size(),1);
		assertEquals(test.get(0),new Associazione(r1,cam2));
		assertEquals(sortedRichieste.size(),check.size()-1);
		assertFalse(sortedRichieste.contains(r1));
		assertTrue(check.contains(r1));
		assertTrue(sortedRichieste.isEmpty());
		//Con più prenotazioni, restituisce la più promettente
		Camion cam3=new Camion(22, "Yamaha", "Camioncino", 15, 15, 15);
		Lavoro l4=new Lavoro(14,"l4",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2014,04,24));
		c.aggiungiLavoro(l4);
		l4.caricaRichiesta(rc, 13, cam3);
		Camion cam4=new Camion(23, "Yamaha", "Camioncino", 15, 15, 15);
		Lavoro l5=new Lavoro(15,"l5",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2014,05,11));
		c.aggiungiLavoro(l5);
		l5.caricaRichiesta(rc, 14, cam4);
		sortedRichieste.clear();
		check.clear();
		sortedRichieste.add(r1);
		check.addAll(sortedRichieste);
		assertEquals(check, sortedRichieste);
		test=GreedyEngine.selezionaConPrenotazioni(sortedRichieste);
		assertEquals(test.size(),1);
		assertEquals(test.get(0),new Associazione(r1,cam3));
		assertEquals(sortedRichieste.size(),check.size()-1);
		assertFalse(sortedRichieste.contains(r1));
		assertTrue(check.contains(r1));
		assertTrue(sortedRichieste.isEmpty());
		//Se ci sono macchine prenotabili, anche più promettenti, ma di altro tipo le ignora
		Ruspa rus=new Ruspa(40,"Yamaha","Ruspa",15,15,15);
		RichiestaRuspa rr=new RichiestaRuspa(10,20,10,20,10,20);
		Lavoro l6=new Lavoro(16,"l6",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2014,05,23));
		c.aggiungiLavoro(l6);
		l6.caricaRichiesta(rr, 15, rus);
		sortedRichieste.clear();
		check.clear();
		sortedRichieste.add(r1);
		check.addAll(sortedRichieste);
		assertEquals(check, sortedRichieste);
		test=GreedyEngine.selezionaConPrenotazioni(sortedRichieste);
		assertEquals(test.size(),1);
		assertEquals(test.get(0),new Associazione(r1,cam3));
		assertEquals(sortedRichieste.size(),check.size()-1);
		assertFalse(sortedRichieste.contains(r1));
		assertTrue(check.contains(r1));
		assertTrue(sortedRichieste.isEmpty());
		//Se la prenotazione più promettente è già occupata, seleziona la più promettente libera
		Lavoro lpiuprom=new Lavoro(30,"l30",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,19));
		c.aggiungiLavoro(lpiuprom);
		lpiuprom.caricaRichiesta(rc, 30, null);
		Richiesta r2=lpiuprom.getRichiesta(30);
		sortedRichieste.clear();
		check.clear();
		sortedRichieste.add(r2);
		sortedRichieste.add(r1);
		check.addAll(sortedRichieste);
		assertEquals(check, sortedRichieste);
		test=GreedyEngine.selezionaConPrenotazioni(sortedRichieste);
		assertEquals(test.size(),2);
		assertEquals(test.get(0),new Associazione(r2,cam3));
		assertEquals(test.get(1),new Associazione(r1,cam2));
		assertEquals(sortedRichieste.size(),check.size()-2);
		assertFalse(sortedRichieste.contains(r2));
		assertTrue(check.contains(r2));
		assertFalse(sortedRichieste.contains(r1));
		assertTrue(check.contains(r1));
		assertTrue(sortedRichieste.isEmpty());
		//Se più richieste si contendono la stessa macchina, solo la richiesta più prioritaria può ottenerla
		l.caricaRichiesta(rc, 31, cam1);
		l.caricaRichiesta(rc, 32, cam2);
		l.caricaRichiesta(rc, 33, cam3);
		sortedRichieste.clear();
		check.clear();
		sortedRichieste.add(r2);
		sortedRichieste.add(r1);
		check.addAll(sortedRichieste);
		assertEquals(check, sortedRichieste);
		test=GreedyEngine.selezionaConPrenotazioni(sortedRichieste);
		assertEquals(test.size(),1);
		assertEquals(test.get(0),new Associazione(r2,cam4));
		assertEquals(sortedRichieste.size(),check.size()-1);
		assertFalse(sortedRichieste.contains(r2));
		assertTrue(check.contains(r2));
		assertTrue(sortedRichieste.contains(r1));
		assertTrue(check.contains(r1));
		assertEquals(sortedRichieste.size(),1);
		//Se una richiesta non genera prenotazioni, non viene associata anche se la macchina è libera
		Lavoro lesc=new Lavoro(31,"l31",c,new GregorianCalendar(2014,07,10),new GregorianCalendar(2014,07,20));
		c.aggiungiLavoro(lesc);
		lesc.caricaRichiesta(new RichiestaEscavatore(10,20,10,20,10,20,10,20), 35, new Escavatore(90,"Yamaha","esc",15,15,15,15));
		l.eliminaRichiesta(31);
		l.eliminaRichiesta(32);
		l.eliminaRichiesta(33);
		lpiuprom.caricaRichiesta(new RichiestaEscavatore(10,20,10,20,10,20,10,20), 34, null);
		Richiesta r3=lpiuprom.getRichiesta(34);
		sortedRichieste.clear();
		check.clear();
		sortedRichieste.add(r2);
		sortedRichieste.add(r3);
		sortedRichieste.add(r1);
		check=GreedyEngine.ordinaRichieste(sortedRichieste);
		sortedRichieste.clear();
		sortedRichieste.addAll(check);
		assertEquals(check, sortedRichieste);
		test=GreedyEngine.selezionaConPrenotazioni(sortedRichieste);
		assertEquals(test.size(),2);
		assertEquals(test.get(0),new Associazione(r2,cam3));
		assertEquals(test.get(1),new Associazione(r1,cam2));
		assertEquals(sortedRichieste.size(),check.size()-2);
		assertFalse(sortedRichieste.contains(r2));
		assertTrue(check.contains(r2));
		assertFalse(sortedRichieste.contains(r1));
		assertTrue(check.contains(r1));
		assertTrue(sortedRichieste.contains(r3));
		assertTrue(check.contains(r3));
		assertEquals(sortedRichieste.size(),1);
		//Se la richiesta attuale è già soddisfatta, anche se ha prenotazioni disponibili, non genero associazioni
		r1.setMacchina(new Camion(22, "Yamaha", "Camioncino", 15, 15, 15));
		sortedRichieste.clear();
		check.clear();
		sortedRichieste.add(r2);
		sortedRichieste.add(r3);
		sortedRichieste.add(r1);
		check=GreedyEngine.ordinaRichieste(sortedRichieste);
		sortedRichieste.clear();
		sortedRichieste.addAll(check);
		assertEquals(check, sortedRichieste);
		test=GreedyEngine.selezionaConPrenotazioni(sortedRichieste);
		assertEquals(test.size(),1);
		assertEquals(test.get(0),new Associazione(r2,cam3));
		assertEquals(sortedRichieste.size(),check.size()-1);
		assertFalse(sortedRichieste.contains(r2));
		assertTrue(check.contains(r2));
		assertTrue(sortedRichieste.contains(r1));
		assertTrue(check.contains(r1));
		assertTrue(sortedRichieste.contains(r3));
		assertTrue(check.contains(r3));
		assertEquals(sortedRichieste.size(),2);
	}
	
	@Test
	public void testSelezionaMacchinaSenzaPrenotazioni() {
		ArrayList<Camion>cList=new ArrayList<Camion>();
		Associazione a;
		ArrayList<Associazione>aLibere=new ArrayList<Associazione>();
		ArrayList<Associazione>aPrenotate=new ArrayList<Associazione>();
		assertTrue(aLibere.isEmpty());
		assertTrue(aPrenotate.isEmpty());
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		Lavoro l=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,20));
		Lavoro l2=new Lavoro(12,"l2",c,new GregorianCalendar(2014,04,9),new GregorianCalendar(2014,04,23));
		Lavoro l3=new Lavoro(33,"l3",c,new GregorianCalendar(2015,04,9),new GregorianCalendar(2015,04,23));
		c.aggiungiLavoro(l);
		c.aggiungiLavoro(l2);
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		Richiesta ric1=new Richiesta(rc,l,20);
		//Caso lista di macchine libere vuota
		a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric1, cList, aLibere, aPrenotate);
		assertEquals(a,null);
		//Caso di macchine associate nel periodo della prenotazione
		Camion cam1=new Camion(10,"Yamaha","Camion1",15,15,15);
		Associazione asso1=new Associazione(new Richiesta(rc,l2,30),cam1);
		aLibere.add(asso1);
		cList.add(cam1);
		a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric1, cList, aLibere, aPrenotate);
		assertEquals(a,null);
		Camion cam5=new Camion(15,"Yamaha","Camion1",15,15,15);
		Associazione asso3=new Associazione(new Richiesta(rc,l2,30),cam5);
		aPrenotate.add(asso3);
		cList.add(cam5);
		a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric1, cList, aLibere, aPrenotate);
		assertEquals(a,null);
		//Caso di macchina libera
		Camion cam2=new Camion(11,"Yamaha","Camion2",15,15,15);
		Camion cam3=new Camion(12,"Yamaha","Camion3",15,15,15);
		Camion cam4=new Camion(13,"Yamaha","Camion4",15,15,15);
		Associazione asso2=new Associazione(new Richiesta(rc,l3,31),cam2);
		aLibere.add(asso2);
		cList.add(cam2);
		cList.add(cam3);
		cList.add(cam4);
		a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric1, cList, aLibere, aPrenotate);
		assertEquals(a.getRichiesta(),ric1);
		assertEquals(a.getMacchina(),cam2);
		//caso richiesta già soddisfatta
		Richiesta ric3=new Richiesta(rc,l,22);
		ric3.setMacchina(new Camion(99,"Yamaha","Camion",15,15,15));
		a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric3, cList, aLibere, aPrenotate);
		assertEquals(a,null);
		//caso richiesta già associata
		ric3.setMacchina(null);
		aLibere.add(new Associazione(ric3,new Camion(99,"Yamaha","Camion",15,15,15)));
		a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric3, cList, aLibere, aPrenotate);
		assertEquals(a,null);
		aLibere.remove(new Associazione(ric3,new Camion(99,"Yamaha","Camion",15,15,15)));
		aPrenotate.add(new Associazione(ric3,new Camion(99,"Yamaha","Camion",15,15,15)));
		a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric3, cList, aLibere, aPrenotate);
		assertEquals(a,null);
		
	}

	@Test
	public void testSelezionaPrenotazionePiuPromettente() {
		ArrayList<Associazione>a=new ArrayList<Associazione>();
		ArrayList<Prenotazione>p;
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		Lavoro l=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,20));
		c.aggiungiLavoro(l);
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l.caricaRichiesta(rc, 10, null);
		Richiesta r=l.getRichiesta(10);
		//Senza alcuna prenotazione, restituisce null
		p=GreedyEngine.generaPrenotazioni(r);
		assertTrue(p.isEmpty());
		assertEquals(GreedyEngine.selezionaPrenotazionePiuPromettente(a,r),null);
		Lavoro l2=new Lavoro(12,"l2",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2014,05,1));
		c.aggiungiLavoro(l2);
		Camion cam1=new Camion(20, "Yamaha", "Camioncino", 15, 15, 15);
		l2.caricaRichiesta(rc, 11, cam1);
		p=GreedyEngine.generaPrenotazioni(r);
		assertEquals(p.size(),1);
		//Con una prenotazione, restituisce l'unica disponibile
		assertEquals(GreedyEngine.selezionaPrenotazionePiuPromettente(a,r),p.get(0).getAssociazione());
		Camion cam2=new Camion(21, "Yamaha", "Camioncino", 15, 15, 15);
		Lavoro l3=new Lavoro(13,"l3",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2014,04,26));
		c.aggiungiLavoro(l3);
		l3.caricaRichiesta(rc, 12, cam2);
		p=GreedyEngine.generaPrenotazioni(r);
		assertEquals(p.size(),2);
		//Con due prenotazioni, restituisce la più promettente
		assertEquals(GreedyEngine.selezionaPrenotazionePiuPromettente(a,r),p.get(1).getAssociazione());
		Camion cam3=new Camion(22, "Yamaha", "Camioncino", 15, 15, 15);
		Lavoro l4=new Lavoro(14,"l4",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2014,04,24));
		c.aggiungiLavoro(l4);
		l4.caricaRichiesta(rc, 13, cam3);
		Camion cam4=new Camion(23, "Yamaha", "Camioncino", 15, 15, 15);
		Lavoro l5=new Lavoro(15,"l5",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2014,05,11));
		c.aggiungiLavoro(l5);
		l5.caricaRichiesta(rc, 14, cam4);
		p=GreedyEngine.generaPrenotazioni(r);
		assertEquals(p.size(),4);
		//Con più prenotazioni, restituisce la più promettente
		assertEquals(GreedyEngine.selezionaPrenotazionePiuPromettente(a,r),p.get(2).getAssociazione());
		Ruspa rus=new Ruspa(40,"Yamaha","Ruspa",15,15,15);
		RichiestaRuspa rr=new RichiestaRuspa(10,20,10,20,10,20);
		Lavoro l6=new Lavoro(16,"l6",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2014,05,23));
		c.aggiungiLavoro(l6);
		l6.caricaRichiesta(rr, 15, rus);
		p=GreedyEngine.generaPrenotazioni(r);
		assertEquals(p.size(),4);
		//Considera solo le prenotazioni per la richiesta corrente, ignorando le prenotazioni più promettenti di altri
		assertEquals(GreedyEngine.selezionaPrenotazionePiuPromettente(a,r),p.get(2).getAssociazione());
		Lavoro l7=new Lavoro(7,"l7",c,new GregorianCalendar(2014,04,05),new GregorianCalendar(2014,04,15));
		a.add(new Associazione(new Richiesta(rc,l7,20), cam3));
		Lavoro l8=new Lavoro(8,"l8",c,new GregorianCalendar(2017,04,05),new GregorianCalendar(2017,04,15));
		a.add(new Associazione(new Richiesta(rc,l8,21), cam2));
		Lavoro l9=new Lavoro(9,"l9",c,new GregorianCalendar(2012,04,05),new GregorianCalendar(2012,04,15));
		a.add(new Associazione(new Richiesta(rc,l9,22), cam2));
		//Se la prenotazione più promettente è già occupata, seleziona la più promettente libera
		assertEquals(GreedyEngine.selezionaPrenotazionePiuPromettente(a,r),p.get(1).getAssociazione());
		//Anche se ci sono prenotazioni promettenti per altre richieste, restituisce null se non ce ne sono per la richiesta attuale
		assertEquals(GreedyEngine.selezionaPrenotazionePiuPromettente(a,new Richiesta(rc,l2,15)),null);
		a.add(new Associazione(new Richiesta(rc,l7,36), cam1));
		a.add(new Associazione(new Richiesta(rc,l7,37), cam2));
		a.add(new Associazione(new Richiesta(rc,l7,38), cam4));
		p=GreedyEngine.generaPrenotazioni(r);
		assertEquals(p.size(),4);
		//Se tutte le prenotazioni per tale richiesta sono occupate, restituisce null
		assertEquals(GreedyEngine.selezionaPrenotazionePiuPromettente(a,r),null);
		a.clear();
		r.setMacchina(new Camion(22, "Yamaha", "Camioncino", 15, 15, 15));
		//Se la richiesta attuale è già soddisfatta, anche se ha prenotazioni disponibili, restituisco null
		assertEquals(GreedyEngine.selezionaPrenotazionePiuPromettente(a,r),null);
		r.setMacchina(null);
		a.add(new Associazione(r, cam3));
		//Se la richiesta attuale è già stata associata, ma non è ancora soddisfatta, restituisco null
		assertEquals(GreedyEngine.selezionaPrenotazionePiuPromettente(a,r),null);
		
	}

	@Test
	public void testGeneraPrenotazioni(){
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,02),new GregorianCalendar(2014,10,27),Priorita.MEDIA);
		Lavoro base=new Lavoro(100,"base",c,new GregorianCalendar(2014,05,15),new GregorianCalendar(2014,05,20));
		Lavoro l1=new Lavoro(1,"l1",c,new GregorianCalendar(2014,05,02),new GregorianCalendar(2014,05,04));
		Lavoro l2=new Lavoro(2,"l2",c,new GregorianCalendar(2014,05,07),new GregorianCalendar(2014,05,9));
		Lavoro l3=new Lavoro(3,"l3",c,new GregorianCalendar(2014,05,10),new GregorianCalendar(2014,05,12));
		Lavoro l4=new Lavoro(4,"l4",c,new GregorianCalendar(2014,05,14),new GregorianCalendar(2014,05,16));
		Lavoro l5=new Lavoro(5,"l5",c,new GregorianCalendar(2014,05,17),new GregorianCalendar(2014,05,19));
		Lavoro l6=new Lavoro(6,"l6",c,new GregorianCalendar(2014,05,19),new GregorianCalendar(2014,05,21));
		Lavoro l7=new Lavoro(7,"l7",c,new GregorianCalendar(2014,05,23),new GregorianCalendar(2014,05,25));
		Lavoro l8=new Lavoro(8,"l8",c,new GregorianCalendar(2014,05,26),new GregorianCalendar(2014,05,28));
		Lavoro l9=new Lavoro(9,"l9",c,new GregorianCalendar(2014,05,28),new GregorianCalendar(2014,05,30));
		Lavoro loccupa=new Lavoro(10,"l10",c,new GregorianCalendar(2014,05,26),new GregorianCalendar(2014,05,28));
		c.aggiungiLavoro(base);
		c.aggiungiLavoro(l1);
		c.aggiungiLavoro(l2);
		c.aggiungiLavoro(l3);
		c.aggiungiLavoro(l4);
		c.aggiungiLavoro(l5);
		c.aggiungiLavoro(l6);
		c.aggiungiLavoro(l7);
		c.aggiungiLavoro(l8);
		c.aggiungiLavoro(l9);
		c.aggiungiLavoro(loccupa);
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		Camion c1=new Camion(1,"Yamaha","Camion",15,15,15);
		Camion c2=new Camion(2,"Yamaha","Camion",15,15,15);
		Camion c3=new Camion(3,"Yamaha","Camion",15,15,15);
		l1.caricaRichiesta(rc, 1, null);
		l2.caricaRichiesta(rc, 2, null);
		l3.caricaRichiesta(rc, 3, null);
		l4.caricaRichiesta(rc, 4, null);
		l5.caricaRichiesta(rc, 5, null);
		l6.caricaRichiesta(rc, 6, null);
		l7.caricaRichiesta(rc, 7, null);
		l8.caricaRichiesta(rc, 8, null);
		l9.caricaRichiesta(rc, 9, null);
		loccupa.caricaRichiesta(rc, 10, c2);
		Richiesta r1=l1.getRichiesta(1);
		Richiesta r2=l2.getRichiesta(2);
		Richiesta r3=l3.getRichiesta(3);
		Richiesta r4=l4.getRichiesta(4);
		Richiesta r5=l5.getRichiesta(5);
		Richiesta r6=l6.getRichiesta(6);
		Richiesta r7=l7.getRichiesta(7);
		Richiesta r8=l8.getRichiesta(8);
		Richiesta r9=l9.getRichiesta(9);
		base.caricaRichiesta(rc, 101, c1);
		base.caricaRichiesta(rc, 102, null);
		base.caricaRichiesta(rc, 103, c2);
		base.caricaRichiesta(rc, 104, c3);
		base.caricaRichiesta(new RichiestaRuspa(10,20,10,20,10,20), 105, new Ruspa(4,"Yamaha","Ruspa",15,15,15));
		Richiesta b2=base.getRichiesta(102);
		/*
		ArrayList<Richiesta>sortedRichieste=new ArrayList<Richiesta>();
		sortedRichieste.add(r1);
		sortedRichieste.add(r2);
		sortedRichieste.add(r3);
		sortedRichieste.add(r4);
		sortedRichieste.add(r5);
		sortedRichieste.add(r6);
		sortedRichieste.add(r7);
		sortedRichieste.add(r8);
		sortedRichieste.add(r9);
		sortedRichieste.add(b2);
		*/
		ArrayList<Prenotazione>prenotazioni;
		
		prenotazioni=GreedyEngine.generaPrenotazioni(r1);
		assertTrue(prenotazioni.isEmpty());
		
		prenotazioni=GreedyEngine.generaPrenotazioni(r2);
		assertEquals(prenotazioni.get(0).getRichiesta(),r2);
		assertEquals(prenotazioni.get(0).getMacchina(),c1);
		assertEquals(prenotazioni.get(0).getDurataLavoro(),5);
		assertEquals(prenotazioni.get(1).getRichiesta(),r2);
		assertEquals(prenotazioni.get(1).getMacchina(),c2);
		assertEquals(prenotazioni.get(1).getDurataLavoro(),5);
		assertEquals(prenotazioni.get(2).getRichiesta(),r2);
		assertEquals(prenotazioni.get(2).getMacchina(),c3);
		assertEquals(prenotazioni.get(2).getDurataLavoro(),5);
		assertEquals(prenotazioni.size(),3);
		
		prenotazioni=GreedyEngine.generaPrenotazioni(r3);
		assertEquals(prenotazioni.get(0).getRichiesta(),r3);
		assertEquals(prenotazioni.get(0).getMacchina(),c1);
		assertEquals(prenotazioni.get(0).getDurataLavoro(),5);
		assertEquals(prenotazioni.get(1).getRichiesta(),r3);
		assertEquals(prenotazioni.get(1).getMacchina(),c2);
		assertEquals(prenotazioni.get(1).getDurataLavoro(),5);
		assertEquals(prenotazioni.get(2).getRichiesta(),r3);
		assertEquals(prenotazioni.get(2).getMacchina(),c3);
		assertEquals(prenotazioni.get(2).getDurataLavoro(),5);
		assertEquals(prenotazioni.size(),3);
		
		prenotazioni=GreedyEngine.generaPrenotazioni(r4);
		assertTrue(prenotazioni.isEmpty());
		
		prenotazioni=GreedyEngine.generaPrenotazioni(r5);
		assertTrue(prenotazioni.isEmpty());
		
		prenotazioni=GreedyEngine.generaPrenotazioni(r6);
		assertTrue(prenotazioni.isEmpty());
		
		prenotazioni=GreedyEngine.generaPrenotazioni(r7);
		assertEquals(prenotazioni.get(0).getRichiesta(),r7);
		assertEquals(prenotazioni.get(0).getMacchina(),c1);
		assertEquals(prenotazioni.get(0).getDurataLavoro(),5);
		//Per c2, si ha che c2 è occupato sia da un lavoro lungo 5 giorni sia da loccupa, che dura due giorni.
		//Si ha quindi una sola prenotazione per c2, che considera solamente il lavoro più corto tra i due.
		assertEquals(prenotazioni.get(1).getRichiesta(),r7);
		assertEquals(prenotazioni.get(1).getMacchina(),c2);
		assertEquals(prenotazioni.get(1).getDurataLavoro(),2);
		assertEquals(prenotazioni.get(2).getRichiesta(),r7);
		assertEquals(prenotazioni.get(2).getMacchina(),c3);
		assertEquals(prenotazioni.get(2).getDurataLavoro(),5);
		assertEquals(prenotazioni.size(),3);
		
		prenotazioni=GreedyEngine.generaPrenotazioni(r8);
		assertEquals(prenotazioni.get(0).getRichiesta(),r8);
		assertEquals(prenotazioni.get(0).getMacchina(),c1);
		assertEquals(prenotazioni.get(0).getDurataLavoro(),5);
		assertEquals(prenotazioni.get(1).getRichiesta(),r8);
		assertEquals(prenotazioni.get(1).getMacchina(),c3);
		assertEquals(prenotazioni.get(1).getDurataLavoro(),5);
		assertEquals(prenotazioni.size(),2);
		
		prenotazioni=GreedyEngine.generaPrenotazioni(b2);
		assertTrue(prenotazioni.isEmpty());
		
	}
	
	@Test
	public void testPrenotaMacchineDaLavoro() {
		ArrayList<Prenotazione>p=new ArrayList<Prenotazione>();
		assertTrue(p.isEmpty());
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		Lavoro l1=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,10),new GregorianCalendar(2014,04,20));
		Lavoro l2=new Lavoro(12,"l2",c,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,8));
		Lavoro l3=new Lavoro(33,"l3",c,new GregorianCalendar(2014,04,9),new GregorianCalendar(2014,04,15));
		Lavoro l4=new Lavoro(44,"l4",c,new GregorianCalendar(2015,04,9),new GregorianCalendar(2015,04,15));
		c.aggiungiLavoro(l1);
		c.aggiungiLavoro(l2);
		c.aggiungiLavoro(l3);
		c.aggiungiLavoro(l4);
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l1.caricaRichiesta(rc, 10, null);
		Richiesta r=l1.getRichiesta(10);
		//Caso null
		GreedyEngine.prenotaMacchineDaLavoro(r,null,p);
		assertTrue(p.isEmpty());
		//Caso lavoro senza alcuna richiesta
		GreedyEngine.prenotaMacchineDaLavoro(r,l2,p);
		assertTrue(p.isEmpty());
		//Caso lavoro con richieste non soddisfatte
		l2.caricaRichiesta(rc, 11, null);
		GreedyEngine.prenotaMacchineDaLavoro(r,l2,p);
		assertTrue(p.isEmpty());
		//Caso lavoro con richieste soddisfatte che però non possono soddisfare ric
		l2.caricaRichiesta(new RichiestaRuspa(10,20,10,20,10,20), 12, new Ruspa(30,"Yamaha","Ruspa",15,15,15));
		l2.caricaRichiesta(new RichiestaCamion(5,10,5,10,5,10), 13, new Camion(40,"Yamaha","Camioncino",7,7,7));
		GreedyEngine.prenotaMacchineDaLavoro(r,l2,p);
		assertTrue(p.isEmpty());
		//Caso lavoro con richieste soddisfatte che soddisfano ric ma non sono libere per ric
		Camion cam=new Camion(40,"Yamaha","Camion",15,15,15);
		l3.caricaRichiesta(new RichiestaCamion(10,20,10,20,10,20), 14, cam);
		l2.caricaRichiesta(new RichiestaCamion(10,20,10,20,10,20), 15, cam);
		assertFalse(cam.isLibera(l1.getDataInizio(), l1.getDataFine()));
		GreedyEngine.prenotaMacchineDaLavoro(r,l2,p);
		assertTrue(p.isEmpty());
		//Caso lavoro con richieste soddisfatte che soddisfano ric e che sono libere per ric
		Camion c1=new Camion(41,"Yamaha","Camion",15,15,15);
		Camion c2=new Camion(42,"Yamaha","Camion",15,15,15);
		l2.caricaRichiesta(new RichiestaCamion(10,20,10,20,10,20), 16, c1);
		l2.caricaRichiesta(new RichiestaCamion(10,20,10,20,10,20), 17, c2);
		l4.caricaRichiesta(new RichiestaCamion(10,20,10,20,10,20), 18, c2);
		GreedyEngine.prenotaMacchineDaLavoro(r,l2,p);
		assertFalse(p.isEmpty());
		//assertEquals(p.size(),2);
		assertEquals(p.get(0).getRichiesta(),r);
		assertEquals(p.get(0).getMacchina(),c1);
		assertEquals(7,p.get(0).getDurataLavoro());
		assertEquals(p.get(1).getRichiesta(),r);
		assertEquals(p.get(1).getMacchina(),c2);
		assertEquals(7,p.get(1).getDurataLavoro());
	}

	@Test
	public void testLavoroFinisceMenoDiUnaSettimanaPrima() {
		Cantiere c1=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
		Lavoro base=new Lavoro(11,"l1",c1,new GregorianCalendar(2014,04,15),new GregorianCalendar(2014,04,20));
		Lavoro element=new Lavoro(12,"l2",c1,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,02));
		assertFalse(GreedyEngine.lavoroFinisceMenoDiUnaSettimanaPrima(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,13));
		assertTrue(GreedyEngine.lavoroFinisceMenoDiUnaSettimanaPrima(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,11));
		assertTrue(GreedyEngine.lavoroFinisceMenoDiUnaSettimanaPrima(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,18));
		assertFalse(GreedyEngine.lavoroFinisceMenoDiUnaSettimanaPrima(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,17));
		assertFalse(GreedyEngine.lavoroFinisceMenoDiUnaSettimanaPrima(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,25));
		assertFalse(GreedyEngine.lavoroFinisceMenoDiUnaSettimanaPrima(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,23));
		assertFalse(GreedyEngine.lavoroFinisceMenoDiUnaSettimanaPrima(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,31));
		assertFalse(GreedyEngine.lavoroFinisceMenoDiUnaSettimanaPrima(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,30));
		assertFalse(GreedyEngine.lavoroFinisceMenoDiUnaSettimanaPrima(element,base));
	}

	@Test
	public void testLavoroIniziaMenoDiUnaSettimanaDopo() {
		Cantiere c1=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
		Lavoro base=new Lavoro(11,"l1",c1,new GregorianCalendar(2014,04,15),new GregorianCalendar(2014,04,20));
		Lavoro element=new Lavoro(12,"l2",c1,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,02));
		assertFalse(GreedyEngine.lavoroIniziaMenoDiUnaSettimanaDopo(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,13));
		assertFalse(GreedyEngine.lavoroIniziaMenoDiUnaSettimanaDopo(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,11));
		assertFalse(GreedyEngine.lavoroIniziaMenoDiUnaSettimanaDopo(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,18));
		assertFalse(GreedyEngine.lavoroIniziaMenoDiUnaSettimanaDopo(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,17));
		assertFalse(GreedyEngine.lavoroIniziaMenoDiUnaSettimanaDopo(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,25));
		assertFalse(GreedyEngine.lavoroIniziaMenoDiUnaSettimanaDopo(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,23));
		assertTrue(GreedyEngine.lavoroIniziaMenoDiUnaSettimanaDopo(element,base));
		element.setDataFine(new GregorianCalendar(2014,04,30));
		assertTrue(GreedyEngine.lavoroIniziaMenoDiUnaSettimanaDopo(element,base));
		element.setDataInizio(new GregorianCalendar(2014,04,29));
		assertFalse(GreedyEngine.lavoroIniziaMenoDiUnaSettimanaDopo(element,base));
	}

	@Test
	public void testOrdinaRichieste() {
		
				ArrayList<Richiesta> richieste=new ArrayList<Richiesta>();
				ArrayList<Richiesta> sortedRichieste=new ArrayList<Richiesta>();
				assertTrue(richieste.isEmpty());
				assertTrue(sortedRichieste.isEmpty());
				Cantiere c1=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2015,01,01),new GregorianCalendar(2015,10,20),Priorita.ALTA);
				Cantiere c2=new Cantiere(2,"c2","Bergamo",new GregorianCalendar(2015,01,01),new GregorianCalendar(2015,10,20),Priorita.MEDIA);
				Cantiere c3=new Cantiere(3,"c3","Bergamo",new GregorianCalendar(2015,01,01),new GregorianCalendar(2015,10,20),Priorita.MEDIA);
				Cantiere c4=new Cantiere(4,"c4","Bergamo",new GregorianCalendar(2015,01,01),new GregorianCalendar(2015,10,20),Priorita.BASSA);
				Lavoro l11=new Lavoro(1,"l11",c1,new GregorianCalendar(2015,02,03),new GregorianCalendar(2015,02,13));
				Lavoro l12=new Lavoro(5,"l12",c1,new GregorianCalendar(2015,02,9),new GregorianCalendar(2015,02,11));
				Lavoro l21=new Lavoro(2,"l21",c2,new GregorianCalendar(2015,02,03),new GregorianCalendar(2015,02,13));
				Lavoro l22=new Lavoro(7,"l22",c2,new GregorianCalendar(2015,03,04),new GregorianCalendar(2015,03,06));
				Lavoro l23=new Lavoro(8,"l23",c2,new GregorianCalendar(2016,02,03),new GregorianCalendar(2016,02,13));
				Lavoro l31=new Lavoro(3,"l31",c3,new GregorianCalendar(2015,02,03),new GregorianCalendar(2015,02,13));
				Lavoro l32=new Lavoro(9,"l32",c3,new GregorianCalendar(2015,02,03),new GregorianCalendar(2015,02,13));
				Lavoro l41=new Lavoro(4,"l41",c4,new GregorianCalendar(2015,02,03),new GregorianCalendar(2015,02,13));
				Lavoro l42=new Lavoro(6,"l42",c4,new GregorianCalendar(2015,02,9),new GregorianCalendar(2015,02,11));
				c1.aggiungiLavoro(l11);
				c1.aggiungiLavoro(l12);
				c2.aggiungiLavoro(l21);
				c2.aggiungiLavoro(l22);
				c2.aggiungiLavoro(l23);
				c3.aggiungiLavoro(l31);
				c3.aggiungiLavoro(l32);
				c4.aggiungiLavoro(l41);
				c4.aggiungiLavoro(l42);
				RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
				l11.caricaRichiesta(rc, 1, null);
				l12.caricaRichiesta(rc, 5, null);
				l21.caricaRichiesta(rc, 2, null);
				l22.caricaRichiesta(rc, 7, null);
				l23.caricaRichiesta(rc, 8, null);
				l31.caricaRichiesta(rc, 3, null);
				l31.caricaRichiesta(rc, 10, null);
				l32.caricaRichiesta(rc, 9, null);
				l41.caricaRichiesta(rc, 4, null);
				l42.caricaRichiesta(rc, 6, null);
				Richiesta r111=l11.getRichiesta(1);
				Richiesta r121=l12.getRichiesta(5);
				Richiesta r211=l21.getRichiesta(2);
				Richiesta r221=l22.getRichiesta(7);
				Richiesta r231=l23.getRichiesta(8);
				Richiesta r311=l31.getRichiesta(3);
				Richiesta r312=l31.getRichiesta(10);
				Richiesta r321=l32.getRichiesta(9);
				Richiesta r411=l41.getRichiesta(4);
				Richiesta r421=l42.getRichiesta(6);
				
				// L'ordine ottenuto dalla funzione dovrebbe essere:
				// r121, r111, r221, r211, r311, r312, r321, r231, r421, r411
				
				/* L'analisi dell'ordine ï¿½ questa: 
				 * Le parentesi corrispondono alla selezione esatta della posizione, che non richiede ulteriori analisi.
				 * In realtï¿½ l'ordinamento delle prioritï¿½ avviene secondo l'ordine con cui le richieste sono poste in "richieste"
				 * quindi l'algoritmo usato qui non equivale a quello utilizzato dal programma, ma il risultato deve esser lo stesso.
				 *
				 *        | Richiesta | Prioritï¿½Can | DurataLav |  DataInizio  |  CodCan  |  CodLav  |  CodRic   |
				 *        |   r111    |    ALTA     |   10 (2)  |      X       |    X     |    X     |    X      |
				 *        |   r121    |    ALTA     |   2  (1)  |      X       |    X     |    X     |    X      |
				 *        |   r211    |    MEDIA    |   10      |  03/02/15    |    2 (4) |    X     |    X      |
				 *        |   r221    |    MEDIA    |   2  (3)  |      X       |    X     |    X     |    X      |
				 *        |   r231    |    MEDIA    |   10      |  03/02/16 (8)|    X     |    X     |    X      |
				 *        |   r311    |    MEDIA    |   10      |  03/02/15    |    3     |    3     |    3  (5) |
				 *        |   r312    |    MEDIA    |   10      |  03/02/15    |    3     |    3     |    10 (6) |
				 *        |   r321    |    MEDIA    |   10      |  03/02/15    |    3     |    9 (7) |    X      |
				 *        |   r411    |    BASSA    |   10 (10) |      X       |    X     |    X     |    X      |
				 *        |   r421    |    BASSA    |   2  (9)  |      X       |    X     |    X     |    X      |
				 *        
				 * Poniamo ora le richieste con ordini casuali all'interno di "richieste", e verifichiamo che l'ordine sia rispettato.
				 * L'ordinamento viene effettuato tramite algoritmo MergeSort
				 */
				
				// CASO 1
				// r121, r111, r421, r411, r311, r221, r231, r312, r321, r211
				
				richieste.add(r121);
				richieste.add(r111);
				richieste.add(r421);
				richieste.add(r411);
				richieste.add(r311);
				richieste.add(r221);
				richieste.add(r231);
				richieste.add(r312);
				richieste.add(r321);
				richieste.add(r211);
				assertEquals(richieste.get(0),r121);
				assertEquals(richieste.get(1),r111);
				assertEquals(richieste.get(2),r421);
				assertEquals(richieste.get(3),r411);
				assertEquals(richieste.get(4),r311);
				assertEquals(richieste.get(5),r221);
				assertEquals(richieste.get(6),r231);
				assertEquals(richieste.get(7),r312);
				assertEquals(richieste.get(8),r321);
				assertEquals(richieste.get(9),r211);
				sortedRichieste=GreedyEngine.ordinaRichieste(richieste);
				assertEquals(sortedRichieste.size(),richieste.size());
				assertEquals(sortedRichieste.size(),10);
				assertEquals(sortedRichieste.get(0),r121);
				assertEquals(sortedRichieste.get(1),r111);
				assertEquals(sortedRichieste.get(2),r221);
				assertEquals(sortedRichieste.get(3),r211);
				assertEquals(sortedRichieste.get(4),r311);
				assertEquals(sortedRichieste.get(5),r312);
				assertEquals(sortedRichieste.get(6),r321);
				assertEquals(sortedRichieste.get(7),r231);
				assertEquals(sortedRichieste.get(8),r421);
				assertEquals(sortedRichieste.get(9),r411);
				
				// CASO 2
				// r221, r111, r421, r121, r411, r231, r312, r311, r211, r321
				
				richieste.clear();
				sortedRichieste.clear();
				
				richieste.add(r221);
				richieste.add(r111);
				richieste.add(r421);
				richieste.add(r121);
				richieste.add(r411);
				richieste.add(r231);
				richieste.add(r312);
				richieste.add(r311);
				richieste.add(r211);
				richieste.add(r321);
				assertEquals(richieste.get(0),r221);
				assertEquals(richieste.get(1),r111);
				assertEquals(richieste.get(2),r421);
				assertEquals(richieste.get(3),r121);
				assertEquals(richieste.get(4),r411);
				assertEquals(richieste.get(5),r231);
				assertEquals(richieste.get(6),r312);
				assertEquals(richieste.get(7),r311);
				assertEquals(richieste.get(8),r211);
				assertEquals(richieste.get(9),r321);
				sortedRichieste=GreedyEngine.ordinaRichieste(richieste);
				assertEquals(sortedRichieste.size(),richieste.size());
				assertEquals(sortedRichieste.size(),10);
				assertEquals(sortedRichieste.get(0),r121);
				assertEquals(sortedRichieste.get(1),r111);
				assertEquals(sortedRichieste.get(2),r221);
				assertEquals(sortedRichieste.get(3),r211);
				assertEquals(sortedRichieste.get(4),r311);
				assertEquals(sortedRichieste.get(5),r312);
				assertEquals(sortedRichieste.get(6),r321);
				assertEquals(sortedRichieste.get(7),r231);
				assertEquals(sortedRichieste.get(8),r421);
				assertEquals(sortedRichieste.get(9),r411);
				
				// CASO 3
				// r311, r231, r411, r221, r321, r121, r111, r421, r312, r211
				
				
				
				richieste.clear();
				sortedRichieste.clear();
				
				richieste.add(r311);
				richieste.add(r231);
				richieste.add(r411);
				richieste.add(r221);
				richieste.add(r321);
				richieste.add(r121);
				richieste.add(r111);
				richieste.add(r421);
				richieste.add(r312);
				richieste.add(r211);
				assertEquals(richieste.get(0),r311);
				assertEquals(richieste.get(1),r231);
				assertEquals(richieste.get(2),r411);
				assertEquals(richieste.get(3),r221);
				assertEquals(richieste.get(4),r321);
				assertEquals(richieste.get(5),r121);
				assertEquals(richieste.get(6),r111);
				assertEquals(richieste.get(7),r421);
				assertEquals(richieste.get(8),r312);
				assertEquals(richieste.get(9),r211);
				sortedRichieste=GreedyEngine.ordinaRichieste(richieste);
				assertEquals(sortedRichieste.size(),richieste.size());
				assertEquals(sortedRichieste.size(),10);
				assertEquals(sortedRichieste.get(0),r121);
				assertEquals(sortedRichieste.get(1),r111);
				assertEquals(sortedRichieste.get(2),r221);
				assertEquals(sortedRichieste.get(3),r211);
				assertEquals(sortedRichieste.get(4),r311);
				assertEquals(sortedRichieste.get(5),r312);
				assertEquals(sortedRichieste.get(6),r321);
				assertEquals(sortedRichieste.get(7),r231);
				assertEquals(sortedRichieste.get(8),r421);
				assertEquals(sortedRichieste.get(9),r411);
	}
	
	@Test
	public void testOrdinaPerPriorita() {
		Cantiere c1=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.ALTA);
		Cantiere c2=new Cantiere(2,"c2","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		Cantiere c3=new Cantiere(3,"c3","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.BASSA);
		Lavoro l1_1=new Lavoro(11,"l1",c1,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,05));
		Lavoro l1_2=new Lavoro(12,"l2",c1,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,10));
		Lavoro l2_1=new Lavoro(13,"l3",c2,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,05));
		Lavoro l2_2=new Lavoro(14,"l4",c2,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,10));
		Lavoro l3_1=new Lavoro(15,"l5",c3,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,05));
		Lavoro l3_2=new Lavoro(16,"l6",c3,new GregorianCalendar(2014,04,01),new GregorianCalendar(2014,04,10));
		c1.aggiungiLavoro(l1_1);
		c1.aggiungiLavoro(l1_2);
		c2.aggiungiLavoro(l2_1);
		c2.aggiungiLavoro(l2_2);
		c3.aggiungiLavoro(l3_1);
		c3.aggiungiLavoro(l3_2);
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
		
		//Prioritï¿½ diverse
		//Alta-Media
		assertTrue(GreedyEngine.ordinaPerPriorita(r1_2, r2_1));
		assertFalse(GreedyEngine.ordinaPerDurata(r1_2, r2_1));
		assertTrue(GreedyEngine.ordinaPerPriorita(r1_1, r2_2));
		assertTrue(GreedyEngine.ordinaPerDurata(r1_1, r2_2));
		//Alta-Bassa
		assertTrue(GreedyEngine.ordinaPerPriorita(r1_2, r3_1));
		assertFalse(GreedyEngine.ordinaPerDurata(r1_2, r3_1));
		assertTrue(GreedyEngine.ordinaPerPriorita(r1_1, r3_2));
		assertTrue(GreedyEngine.ordinaPerDurata(r1_1, r3_2));
		//Media-Alta
		assertFalse(GreedyEngine.ordinaPerPriorita(r2_1, r1_2));
		assertTrue(GreedyEngine.ordinaPerDurata(r2_1, r1_2));
		assertFalse(GreedyEngine.ordinaPerPriorita(r2_2, r1_1));
		assertFalse(GreedyEngine.ordinaPerDurata(r2_2, r1_1));
		//Media-Bassa
		assertTrue(GreedyEngine.ordinaPerPriorita(r2_2, r3_1));
		assertFalse(GreedyEngine.ordinaPerDurata(r2_2, r3_1));
		assertTrue(GreedyEngine.ordinaPerPriorita(r2_1, r3_2));
		assertTrue(GreedyEngine.ordinaPerDurata(r2_1, r3_2));
		//Bassa-Alta
		assertFalse(GreedyEngine.ordinaPerPriorita(r3_1, r1_2));
		assertTrue(GreedyEngine.ordinaPerDurata(r3_1, r1_2));
		assertFalse(GreedyEngine.ordinaPerPriorita(r3_2, r1_1));
		assertFalse(GreedyEngine.ordinaPerDurata(r3_2, r1_1));
		//Bassa-Media
		assertFalse(GreedyEngine.ordinaPerPriorita(r3_1, r2_2));
		assertTrue(GreedyEngine.ordinaPerDurata(r3_1, r1_2));
		assertFalse(GreedyEngine.ordinaPerPriorita(r3_2, r2_1));
		assertFalse(GreedyEngine.ordinaPerDurata(r3_2, r1_1));
		
		//Prioritï¿½ uguali
		//Alta-Alta
		assertEquals(GreedyEngine.ordinaPerPriorita(r1_1, r1_2),GreedyEngine.ordinaPerDurata(r1_1, r1_2));
		assertTrue(GreedyEngine.ordinaPerDurata(r1_1, r1_2));
		assertEquals(GreedyEngine.ordinaPerPriorita(r1_2, r1_1),GreedyEngine.ordinaPerDurata(r1_2, r1_1));
		assertFalse(GreedyEngine.ordinaPerPriorita(r1_2, r1_1));
		//Media-Media
		assertEquals(GreedyEngine.ordinaPerPriorita(r2_1, r2_2),GreedyEngine.ordinaPerDurata(r2_1, r2_2));
		assertTrue(GreedyEngine.ordinaPerDurata(r2_1, r2_2));
		assertEquals(GreedyEngine.ordinaPerPriorita(r2_2, r2_1),GreedyEngine.ordinaPerDurata(r2_2, r2_1));
		assertFalse(GreedyEngine.ordinaPerPriorita(r2_2, r2_1));
		//Bassa-Bassa
		assertEquals(GreedyEngine.ordinaPerPriorita(r3_1, r3_2),GreedyEngine.ordinaPerDurata(r3_1, r3_2));
		assertTrue(GreedyEngine.ordinaPerDurata(r3_1, r3_2));
		assertEquals(GreedyEngine.ordinaPerPriorita(r3_2, r3_1),GreedyEngine.ordinaPerDurata(r3_2, r3_1));
		assertFalse(GreedyEngine.ordinaPerPriorita(r3_2, r3_1));
	}

	@Test
	public void testOrdinaPerDurata() {
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		Lavoro l1=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		Lavoro l2=new Lavoro(4,"l2",c,new GregorianCalendar(2014,04,23),new GregorianCalendar(2015,01,23));
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l1.caricaRichiesta(rc, 6, null);
		l2.caricaRichiesta(rc, 7, null);
		Richiesta r1,r2;
		r1=l1.getRichiesta(6);
		r2=l2.getRichiesta(7);
		
		//Se le durate sono uguali, vado a valutare le date d'inizio
		assertEquals(GreedyEngine.ordinaPerDurata(r1, r2),GreedyEngine.ordinaPerDataIniziale(r1, r2));
		assertTrue(GreedyEngine.ordinaPerDataIniziale(r1, r2));
		assertEquals(GreedyEngine.ordinaPerDurata(r2, r1),GreedyEngine.ordinaPerDataIniziale(r2, r1));
		assertFalse(GreedyEngine.ordinaPerDataIniziale(r2, r1));
		
		//Se le durate sono diverse (nei lavori), allora le valuto
		l1.setDataInizio(new GregorianCalendar(2014,03,01));
		l2.setDataInizio(new GregorianCalendar(2014,03,02));
		l1.setDataFine(new GregorianCalendar(2014,03,15));
		l2.setDataFine(new GregorianCalendar(2014,03,07));
		//d1>d2, quindi ordinaPerDurata(r1, r2, d1, d2) restituisce false anche se ordinaPerDataIniziale(r1, r2) ï¿½ true
		assertFalse(GreedyEngine.ordinaPerDurata(r1, r2));
		assertTrue(GreedyEngine.ordinaPerDataIniziale(r1, r2));
		//d2>d1, quindi ordinaPerDurata(r2, r1, d2, d1) restituisce true anche se ordinaPerDataIniziale(r2, r1) ï¿½ false
		assertTrue(GreedyEngine.ordinaPerDurata(r2, r1));
		assertFalse(GreedyEngine.ordinaPerDataIniziale(r2, r1));
	}

	@Test
	public void testOrdinaPerDataIniziale() {
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		Lavoro l1=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		Lavoro l2=new Lavoro(4,"l2",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l1.caricaRichiesta(rc, 6, null);
		l2.caricaRichiesta(rc, 7, null);
		Richiesta r1,r2;
		r1=l1.getRichiesta(6);
		r2=l2.getRichiesta(7);
		
		//Se le date iniziali sono uguali, vado a valutare i codici
		assertEquals(GreedyEngine.ordinaPerDataIniziale(r1, r2),GreedyEngine.ordinaPerCodice(r1, r2));
		assertTrue(GreedyEngine.ordinaPerCodice(r1, r2));
		assertEquals(GreedyEngine.ordinaPerDataIniziale(r2, r1),GreedyEngine.ordinaPerCodice(r2, r1));
		assertFalse(GreedyEngine.ordinaPerCodice(r2, r1));
		
		//Se le date sono diverse (nei lavori), allora le valuto
		l2.setDataInizio(new GregorianCalendar(2014,03,12));
		//r1>r2, quindi ordinaPerDataIniziale(r1, r2) restituisce false anche se ordinaPerCodice(r1, r2) ï¿½ true
		assertFalse(GreedyEngine.ordinaPerDataIniziale(r1, r2));
		assertTrue(GreedyEngine.ordinaPerCodice(r1, r2));
		//r1>r2, quindi ordinaPerDataIniziale(r2, r1) restituisce true anche se ordinaPerCodice(r2, r1) ï¿½ false
		assertTrue(GreedyEngine.ordinaPerDataIniziale(r2, r1));
		assertFalse(GreedyEngine.ordinaPerCodice(r2, r1));
		
	}

	@Test
	public void testOrdinaPerCodice() {
		Cantiere c1=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		Cantiere c2=new Cantiere(2,"c2","Milano",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		
		Lavoro l1=new Lavoro(3,"l1",c1,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		Lavoro l2=new Lavoro(4,"l2",c1,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		Lavoro l3=new Lavoro(10,"l3",c2,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		c1.aggiungiLavoro(l1);
		c1.aggiungiLavoro(l2);
		c2.aggiungiLavoro(l3);
		
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		l1.caricaRichiesta(rc, 6, null);
		l1.caricaRichiesta(rc, 7, null);
		l2.caricaRichiesta(rc, 10, null);
		l3.caricaRichiesta(rc, 17, null);
		
		Richiesta r1,r2;
	
		//Diverso cantiere
		r1=l1.getRichiesta(6);
		r2=l3.getRichiesta(17);
		assertTrue(GreedyEngine.ordinaPerCodice(r1, r2));
		assertFalse(GreedyEngine.ordinaPerCodice(r2, r1));
		
		//Uguale cantiere, diverso lavoro
		r2=l2.getRichiesta(10);
		assertTrue(GreedyEngine.ordinaPerCodice(r1, r2));
		assertFalse(GreedyEngine.ordinaPerCodice(r2, r1));
		
		//Uguale cantiere, uguale lavoro, diversa richiesta
		r2=l1.getRichiesta(7);
		assertTrue(GreedyEngine.ordinaPerCodice(r1, r2));
		assertFalse(GreedyEngine.ordinaPerCodice(r2, r1));
		
		//Stessa richiesta
		assertFalse(GreedyEngine.ordinaPerCodice(r1, r1));
		
		
	}
	
	@Test
	public void testRichiesteComparator(){
		Cantiere c=new Cantiere(1,"c1","Bergamo",new GregorianCalendar(2014,02,22),new GregorianCalendar(2015,02,22),Priorita.MEDIA);
		RichiestaCamion rc=new RichiestaCamion(10,20,10,20,10,20);
		Lavoro l=new Lavoro(3,"l1",c,new GregorianCalendar(2014,04,22),new GregorianCalendar(2015,01,22));
		Richiesta r1=new Richiesta(rc, l, 1);
		Richiesta r2=new Richiesta(rc, l, 2);
		Comparator<Richiesta> r=new GreedyEngine.RichiesteComparator();
		assertEquals(r.compare(r1, r1),0);
		assertEquals(r.compare(r1, r2),-1);
		assertEquals(r.compare(r2, r1),1);
	}

}
