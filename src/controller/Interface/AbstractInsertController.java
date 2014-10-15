package controller.Interface;

import java.util.GregorianCalendar;

import model.organizer.data.Priority;

public interface AbstractInsertController {
	public  boolean aggiungiNuovaGru(String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza);
	public  boolean inserisciNuovaRuspa(String produttore, String modello,int capacita,int portata,int altezza);
	public  boolean inserisciNuovoEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	public  boolean inserisciNuovoCamion(String produttore,String Modello,int capacita,int portata,int lunghezza);
	public  boolean inserisciNuovoCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita );
	
	
	
	public  boolean modificaCamion(int codiceCamion,String produttore,String modello,int capacita,int portata,int lunghezza);
	public  boolean modificaGru(int codiceGru,String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza) ;
	public  boolean modificaRuspa(int codiceRuspa,String produttore, String modello,int capacita,int portata,int altezza) ;
	public  boolean modificaEscavatore(int codiceEscavatore,String produttore, String modello,int capacita,int portata,int altezza,int profondita);
	public  boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita );
		
}

