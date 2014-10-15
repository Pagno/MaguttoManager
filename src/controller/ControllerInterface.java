package controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import model.organizer.data.Cantiere;
import model.organizer.data.Macchina;
import model.organizer.data.Priority;
import model.organizer.data.RichiestaMacchina;
import controller.data.Associazione;

public interface ControllerInterface {
	
	//Application Interface
	public boolean eliminaMacchina(Integer codiceMacchina);	
	public boolean eliminaCantiere(Integer codiceCantiere);	
	public void exitManager();
	public void caricaDatiListener();
	public void salvaDatiListener();
	public void chiusuraProgramma();
	
	//CantieriInterface
	public  Cantiere getCantiere(int codiceCantiere);
	public  boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita );
	public  boolean aggiungiLavoro(int codiceCantiere,String nomeLavoro,GregorianCalendar dataInizio,GregorianCalendar dataFine);
	
	public  void aggiungiRichiestaObserver(Observer observer );
	
	public  boolean eliminaLavoro(int codiceLavoro);
	public  boolean eliminaRichiesta(int codiceRichiesta);
	public   ArrayList<Macchina>  getElencoMacchineDisponibili(int codiceRichiesta);
	
	public  boolean liberaRichiesta(int codiceRichiesta);
	public  boolean addRichiesta(int codiceCantiere,int codiceLavoro,RichiestaMacchina richiesta);
	public  boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina) ;
	public  ArrayList<Associazione> generateAssociations();
	public  void confermaAssociazioniListener(ArrayList<Associazione> data);
	public  void modificaLavoro(int codiceLavoro, String nome,GregorianCalendar inizio, GregorianCalendar fine);

	//Insert Interface
	public  boolean aggiungiNuovaGru(String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza);
	public  boolean inserisciNuovaRuspa(String produttore, String modello,int capacita,int portata,int altezza);
	public  boolean inserisciNuovoEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	public  boolean inserisciNuovoCamion(String produttore,String Modello,int capacita,int portata,int lunghezza);
	public  boolean inserisciNuovoCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita );
	
	
	
	public  boolean modificaCamion(int codiceCamion,String produttore,String modello,int capacita,int portata,int lunghezza);
	public  boolean modificaGru(int codiceGru,String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza) ;
	public  boolean modificaRuspa(int codiceRuspa,String produttore, String modello,int capacita,int portata,int altezza) ;
	public  boolean modificaEscavatore(int codiceEscavatore,String produttore, String modello,int capacita,int portata,int altezza,int profondita);
		
}


