package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import model.organizer.data.Camion;
import model.organizer.data.Cantiere;
import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaMacchina;
import model.organizer.data.Priorita;
import model.organizer.data.Ruspa;

// 
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
	 * @param priorita priorit�
	 */
	public void aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita);
	
	/**
	 * Modifica cantiere.
	 *
	 * @param codice   codice
	 * @param nomeCantiere   nome cantiere
	 * @param indirizzo   indirizzo
	 * @param dataApertura   data apertura
	 * @param dataChiusura   data chiusura
	 * @param priorita priorit�
	 */
	public void modificaCantiere(int codice, String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita);
	/**
	 * Elimina cantiere.
	 *
	 * @param codice   codice
	 * @return true, if successful
	 */
	public boolean eliminaCantiere(int codice);
	
	/**
	 * Inserisci un nuovo Lavoro.
	 *
	 * @param nome   Codice del lavoro da inserire
	 * @param inizio   dataInizio del Lavoro
	 * @param fine   dataFine del Lavoro
	 * @param idCantiere   Codice del cantiere cui appartiene il lavoro
	 * 
	 */
	public void aggiungiLavoro(String nome,GregorianCalendar inizio,GregorianCalendar fine,int idCantiere);
	
	/**
	 * Modifica un Lavoro.
	 *
	 * @param codiceLavoro    Codice del lavoro da modificare
	 * @param nome   Nome del lavoro da modificare
	 * @param inizio   dataInizio del Lavoro
	 * @param fine   dataFine del Lavoro
	 * 
	 */
	public void modificaLavoro(int codiceLavoro, String nome, GregorianCalendar dataInizio,GregorianCalendar dataFine);
		
	/**
	 * Cancella Lavoro.
	 *
	 * @param codiceRichiesta   Codice del lavoro da cancellare
	 * 
	 */
	public boolean eliminaLavoro(int codiceLavoro);
	
	/**
	 * Aggiungi Richiesta.
	 *
	 * @param codiceCantiere   codice del cantiere
	 * @param codiceLavoro   codice del Lavoro
	 * @param richiesta   Richiesta da Inserire
	 * 
	 */
	public boolean aggiungiRichiesta(int codiceCantiere, int codiceLavoro,RichiestaMacchina richiesta);
	/**
	 * Cancella Richiesta.
	 *
	 * @param codiceRichiesta   Codice della richiesta da cancellare
	 * 
	 */
	public boolean eliminaRichiesta(int codiceRichiesta);
	
	/**
	 * Associa una macchina ad una richiesta.
	 *
	 * @param codiceRichiesta   Codice della richiesta da associare
	 * @param codiceMacchina  codice della macchina da associare
	 * 
	 */
	public boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina);	
	public Cantiere getCantiere(int codiceCantiere);
	
	

	public ArrayList<Ruspa> getElencoRuspeDisponibili(int codiceRichiesta,int codiceLavoro);
	public ArrayList<Gru> getElencoGruDisponibili(int codiceRichiesta,int codiceLavoro);
	public ArrayList<Camion> getElencoCamionDisponibili(int codiceRichiesta,int codiceLavoro);
	public ArrayList<Escavatore> getElencoEscavatoriDisponibili(int codiceRichiesta,int codiceLavoro);
	public ArrayList<Cantiere> getListaCantieri();
	public ArrayList<Richiesta> getRichiesteScoperte();
	
	
	public void pubblicaContenuto();

	public void aggiungiGruObserver(Observer observer);
	public void aggiungiRuspaObserver(Observer observer);
	public void aggiungiCamionObserver(Observer observer);	
	public void aggiungiEscavatoreObserver(Observer observer);	
	public void aggiungiCantiereObserver(Observer observer);
	

	public ArrayList<ArrayList<String>> getLavoriCantiereList(int codiceCantiere);
	public ArrayList<ArrayList<String>> getRichiesteLavoroList(int codiceCantiere);
	
	public boolean liberaRichiesta(int codiceRichiesta);
	public ArrayList<Macchina> getElencoMacchineDisponibili(int codiceRichiesta);

	public ArrayList<Richiesta> getElencoRichieste(int codicelavoro);
	
	public Richiesta getRichiesta(int codiceRichiesta);
	
	public Macchina getMacchina(int codice);

}
