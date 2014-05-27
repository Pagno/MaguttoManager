package model;

import java.util.GregorianCalendar;

public interface ModelInterface {
	public void storeData();
	public void refreshData();
	
	//GRU
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);
	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);

	//CAMION
	public void aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza);
	public void modificaCamion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza);

	//RUSPA
	public void aggiungiRuspa(String produttore, String Modello,int capacita,int portata,int altezza);
	public void modificaRuspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza);

	//ESCAVATORE
	public void aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	public void modificaEscavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	
	//COMUNE A TUTTE LE MACCHINE
	public boolean eliminaMacchina(int codice);
	
	//CANTIERE
	public int aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura);
	public void modificaCantiere(int codice, String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura);
	public boolean eliminaCantiere(int codice);
	
	//ASSOCIAZIONE
	public void aggiungiAssociazione(Integer codiceMacchina,Integer codiceCantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine);
	public void modificaAssociazione(Integer codice, Integer codiceMacchina,Integer codiceCantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine);
	public boolean eliminaAssociazione(int codice);
}