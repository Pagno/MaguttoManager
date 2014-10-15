package controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import model.organizer.data.Cantiere;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
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
	//
	public void aggiungiGruObserver(Observer observer);
	public void aggiungiRuspaObserver(Observer observer);
	public void aggiungiCamionObserver(Observer observer);	
	public void aggiungiEscavatoreObserver(Observer observer);	
	public void aggiungiCantiereObserver(Observer observer);
	
	//CantieriInterface
	public  Cantiere getCantiere(int codiceCantiere);
	public  boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita );
	public  boolean aggiungiLavoro(int codiceCantiere,String nomeLavoro,GregorianCalendar dataInizio,GregorianCalendar dataFine);


	//public  void aggiungiRichiestaObserver(Observer observer );
	
	public  boolean eliminaLavoro(int codiceLavoro);
	public  boolean eliminaRichiesta(int codiceRichiesta);
	public   ArrayList<Macchina>  getElencoMacchineDisponibili(int codiceRichiesta);
	
	public  boolean liberaRichiesta(int codiceRichiesta);
	public  boolean aggiungiRichiesta(int codiceCantiere,int codiceLavoro,RichiestaMacchina richiesta);
	public  boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina) ;
	public  ArrayList<Associazione> generaAssociazioni();
	public  void confermaAssociazioniListener(ArrayList<Associazione> data);
	public  void modificaLavoro(int codiceLavoro, String nome,GregorianCalendar inizio, GregorianCalendar fine);

	//Insert Interface
	public  boolean aggiungiGru(String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza);
	public  boolean aggiungiRuspa(String produttore, String modello,int capacita,int portata,int altezza);
	public  boolean aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	public  boolean aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza);
	public  boolean aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita );
	
	
	
	public  boolean modificaCamion(int codiceCamion,String produttore,String modello,int capacita,int portata,int lunghezza);
	public  boolean modificaGru(int codiceGru,String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza) ;
	public  boolean modificaRuspa(int codiceRuspa,String produttore, String modello,int capacita,int portata,int altezza) ;
	public  boolean modificaEscavatore(int codiceEscavatore,String produttore, String modello,int capacita,int portata,int altezza,int profondita);
		
}


