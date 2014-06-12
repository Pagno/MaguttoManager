package model;

import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 *   Interface ModelInterface.
 */
public interface ModelInterface {
	
	/**
	 * Store data.
	 */
	public void storeData();
	
	/**
	 * Refresh data.
	 */
	public void refreshData();
	
	//GRU
	/**
	 * Aggiungi gru.
	 *
	 * @param produttore   produttore
	 * @param modello   modello
	 * @param rotazione   rotazione
	 * @param portata   portata
	 * @param lunghezza   lunghezza
	 * @param altezza   altezza
	 */
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);
	
	/**
	 * Modifica gru.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param modello   modello
	 * @param rotazione   rotazione
	 * @param portata   portata
	 * @param lunghezza   lunghezza
	 * @param altezza   altezza
	 */
	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);

	//CAMION
	/**
	 * Aggiungi camion.
	 *
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param lunghezza   lunghezza
	 */
	public void aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza);
	
	/**
	 * Modifica camion.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param lunghezza   lunghezza
	 */
	public void modificaCamion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza);

	//RUSPA
	/**
	 * Aggiungi ruspa.
	 *
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param altezza   altezza
	 */
	public void aggiungiRuspa(String produttore, String Modello,int capacita,int portata,int altezza);
	
	/**
	 * Modifica ruspa.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param altezza   altezza
	 */
	public void modificaRuspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza);

	//ESCAVATORE
	/**
	 * Aggiungi escavatore.
	 *
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param altezza   altezza
	 * @param profondita   profondita
	 */
	public void aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	
	/**
	 * Modifica escavatore.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param altezza   altezza
	 * @param profondita   profondita
	 */
	public void modificaEscavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	
	//COMUNE A TUTTE LE MACCHINE
	/**
	 * Elimina macchina.
	 *
	 * @param codice   codice
	 * @return true, if successful
	 */
	public boolean eliminaMacchina(int codice);
	
	//CANTIERE
	/**
	 * Aggiungi cantiere.
	 *
	 * @param nomeCantiere   nome cantiere
	 * @param indirizzo   indirizzo
	 * @param dataApertura   data apertura
	 * @param dataChiusura   data chiusura
	 * @return   int
	 */
	public int aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura);
	
	/**
	 * Modifica cantiere.
	 *
	 * @param codice   codice
	 * @param nomeCantiere   nome cantiere
	 * @param indirizzo   indirizzo
	 * @param dataApertura   data apertura
	 * @param dataChiusura   data chiusura
	 */
	public void modificaCantiere(int codice, String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura);
	
	/**
	 * Elimina cantiere.
	 *
	 * @param codice   codice
	 * @return true, if successful
	 */
	public boolean eliminaCantiere(int codice);
	
	//ASSOCIAZIONE
	/**
	 * Aggiungi associazione.
	 *
	 * @param codiceMacchina   codice macchina
	 * @param codiceCantiere   codice cantiere
	 * @param dataInizio   data inizio
	 * @param dataFine   data fine
	 */
	public void aggiungiAssociazione(Integer codiceMacchina,Integer codiceCantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine);
	
	/**
	 * Modifica associazione.
	 *
	 * @param codice   codice
	 * @param codiceMacchina   codice macchina
	 * @param codiceCantiere   codice cantiere
	 * @param dataInizio   data inizio
	 * @param dataFine   data fine
	 */
	public void modificaAssociazione(Integer codice, Integer codiceMacchina,Integer codiceCantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine);
	
	/**
	 * Elimina associazione.
	 *
	 * @param codice   codice
	 * @return true, if successful
	 */
	public boolean eliminaAssociazione(int codice);
}