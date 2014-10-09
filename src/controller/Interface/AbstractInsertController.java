package controller.Interface;

import java.util.GregorianCalendar;

import model.organizer.data.Priority;

public abstract class AbstractInsertController {
	public abstract boolean aggiungiNuovaGru(String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza);
	public abstract boolean inserisciNuovaRuspa(String produttore, String modello,int capacita,int portata,int altezza);
	public abstract boolean inserisciNuovoEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	public abstract boolean inserisciNuovoCamion(String produttore,String Modello,int capacita,int portata,int lunghezza);
	public abstract boolean inserisciNuovoCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita );
	
	
	
	public abstract boolean modificaCamion(int codiceCamion,String produttore,String modello,int capacita,int portata,int lunghezza);
	public abstract boolean modificaGru(int codiceGru,String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza) ;
	public abstract boolean modificaRuspa(int codiceRuspa,String produttore, String modello,int capacita,int portata,int altezza) ;
	public abstract boolean modificaEscavatore(int codiceEscavatore,String produttore, String modello,int capacita,int portata,int altezza,int profondita);
	public abstract boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita );
		
}

