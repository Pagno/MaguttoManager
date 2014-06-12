package model;

import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * The Interface ModelInterface.
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
	 * @param produttore the produttore
	 * @param modello the modello
	 * @param rotazione the rotazione
	 * @param portata the portata
	 * @param lunghezza the lunghezza
	 * @param altezza the altezza
	 */
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);
	
	/**
	 * Modifica gru.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param modello the modello
	 * @param rotazione the rotazione
	 * @param portata the portata
	 * @param lunghezza the lunghezza
	 * @param altezza the altezza
	 */
	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);

	//CAMION
	/**
	 * Aggiungi camion.
	 *
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param lunghezza the lunghezza
	 */
	public void aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza);
	
	/**
	 * Modifica camion.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param lunghezza the lunghezza
	 */
	public void modificaCamion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza);

	//RUSPA
	/**
	 * Aggiungi ruspa.
	 *
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
	 */
	public void aggiungiRuspa(String produttore, String Modello,int capacita,int portata,int altezza);
	
	/**
	 * Modifica ruspa.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
	 */
	public void modificaRuspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza);

	//ESCAVATORE
	/**
	 * Aggiungi escavatore.
	 *
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
	 * @param profondita the profondita
	 */
	public void aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	
	/**
	 * Modifica escavatore.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
	 * @param profondita the profondita
	 */
	public void modificaEscavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	
	//COMUNE A TUTTE LE MACCHINE
	/**
	 * Elimina macchina.
	 *
	 * @param codice the codice
	 * @return true, if successful
	 */
	public boolean eliminaMacchina(int codice);
	
	//CANTIERE
	/**
	 * Aggiungi cantiere.
	 *
	 * @param nomeCantiere the nome cantiere
	 * @param indirizzo the indirizzo
	 * @param dataApertura the data apertura
	 * @param dataChiusura the data chiusura
	 * @return the int
	 */
	public int aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura);
	
	/**
	 * Modifica cantiere.
	 *
	 * @param codice the codice
	 * @param nomeCantiere the nome cantiere
	 * @param indirizzo the indirizzo
	 * @param dataApertura the data apertura
	 * @param dataChiusura the data chiusura
	 */
	public void modificaCantiere(int codice, String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura);
	
	/**
	 * Elimina cantiere.
	 *
	 * @param codice the codice
	 * @return true, if successful
	 */
	public boolean eliminaCantiere(int codice);
	
	//ASSOCIAZIONE
	/**
	 * Aggiungi associazione.
	 *
	 * @param codiceMacchina the codice macchina
	 * @param codiceCantiere the codice cantiere
	 * @param dataInizio the data inizio
	 * @param dataFine the data fine
	 */
	public void aggiungiAssociazione(Integer codiceMacchina,Integer codiceCantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine);
	
	/**
	 * Modifica associazione.
	 *
	 * @param codice the codice
	 * @param codiceMacchina the codice macchina
	 * @param codiceCantiere the codice cantiere
	 * @param dataInizio the data inizio
	 * @param dataFine the data fine
	 */
	public void modificaAssociazione(Integer codice, Integer codiceMacchina,Integer codiceCantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine);
	
	/**
	 * Elimina associazione.
	 *
	 * @param codice the codice
	 * @return true, if successful
	 */
	public boolean eliminaAssociazione(int codice);
}