package controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import model.organizer.data.Cantiere;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
import model.organizer.data.RichiestaMacchina;
import controller.data.Associazione;


/**
 * The Interface ControllerInterface.
 */
public interface ControllerInterface {
	
	//Application Interface
	/**
	 * Elimina macchina.
	 *
	 * @param codiceMacchina codice della macchina
	 * @return true, if successful
	 */
	public boolean eliminaMacchina(Integer codiceMacchina);	
	
	/**
	 * Elimina cantiere.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @return true, if successful
	 */
	public boolean eliminaCantiere(Integer codiceCantiere);	
	
	/**
	 * Exit manager.
	 */
	public void exitManager();
	
	/**
	 * Carica dati listener.
	 */
	public void caricaDatiListener();
	
	/**
	 * Salva dati listener.
	 */
	public void salvaDatiListener();
	
	/**
	 * Chiusura programma.
	 */
	public void chiusuraProgramma();
	//
	/**
	 * Aggiungi gru observer.
	 *
	 * @param observer the observer
	 */
	public void aggiungiGruObserver(Observer observer);
	
	/**
	 * Aggiungi ruspa observer.
	 *
	 * @param observer the observer
	 */
	public void aggiungiRuspaObserver(Observer observer);
	
	/**
	 * Aggiungi camion observer.
	 *
	 * @param observer the observer
	 */
	public void aggiungiCamionObserver(Observer observer);	
	
	/**
	 * Aggiungi escavatore observer.
	 *
	 * @param observer the observer
	 */
	public void aggiungiEscavatoreObserver(Observer observer);	
	
	/**
	 * Aggiungi cantiere observer.
	 *
	 * @param observer the observer
	 */
	public void aggiungiCantiereObserver(Observer observer);
	
	//CantieriInterface
	/**
	 * Gets the cantiere.
	 *
	 * @param codiceCantiere codice del antiere
	 * @return the cantiere
	 */
	public  Cantiere getCantiere(int codiceCantiere);
	
	/**
	 * Modifica cantiere.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param nomeCantiere nome del cantiere
	 * @param indirizzo indirizzo del cantiere
	 * @param dataApertura data apertura
	 * @param dataChiusura data chiusura
	 * @param priorita priorita del cantiere
	 * @return true, if successful
	 */
	public  boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita );
	
	/**
	 * Aggiungi lavoro.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param nomeLavoro nome del lavoro
	 * @param dataInizio data inizio
	 * @param dataFine data fine
	 * @return true, if successful
	 */
	public  boolean aggiungiLavoro(int codiceCantiere,String nomeLavoro,GregorianCalendar dataInizio,GregorianCalendar dataFine);


	
	/**
	 * Elimina lavoro.
	 *
	 * @param codiceLavoro codice del lavoro
	 * @return true, if successful
	 */
	public  boolean eliminaLavoro(int codiceLavoro);
	
	/**
	 * Elimina richiesta.
	 *
	 * @param codiceRichiesta codice della richiesta
	 * @return true, if successful
	 */
	public  boolean eliminaRichiesta(int codiceRichiesta);
	
	/**
	 * Gets the elenco macchine disponibili.
	 *
	 * @param codiceRichiesta codice della richiesta
	 * @return elenco delle macchine disponibili
	 */
	public   ArrayList<Macchina>  getElencoMacchineDisponibili(int codiceRichiesta);
	
	/**
	 * Libera richiesta.
	 *
	 * @param codiceRichiesta codice della richiesta
	 * @return true, if successful
	 */
	public  boolean liberaRichiesta(int codiceRichiesta);
	
	/**
	 * Aggiungi richiesta.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 * @param richiesta richiesta
	 * @return true, if successful
	 */
	public  boolean aggiungiRichiesta(int codiceCantiere,int codiceLavoro,RichiestaMacchina richiesta);
	
	/**
	 * Soddisfa richiesta.
	 *
	 * @param codiceRichiesta codice della richiesta
	 * @param codiceMacchina codice della macchina
	 * @return true, if successful
	 */
	public  boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina) ;
	
	/**
	 * Genera associazioni.
	 *
	 * @return array list
	 */
	public  ArrayList<Associazione> generaAssociazioni();
	
	/**
	 * Conferma associazioni listener.
	 *
	 * @param data the data
	 */
	public  void confermaAssociazioniListener(ArrayList<Associazione> data);
	
	/**
	 * Modifica lavoro.
	 *
	 * @param codiceLavoro the codice lavoro
	 * @param nome the nome
	 * @param inizio the inizio
	 * @param fine the fine
	 */
	public  void modificaLavoro(int codiceLavoro, String nome,GregorianCalendar inizio, GregorianCalendar fine);

	//Insert Interface
	/**
	 * Aggiungi gru.
	 *
	 * @param produttore produttore
	 * @param modello modello
	 * @param rotazione rotazione
	 * @param portata portata
	 * @param lunghezza lunghezza
	 * @param altezza altezza
	 * @return true, if successful
	 */
	public  boolean aggiungiGru(String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza);
	
	/**
	 * Aggiungi ruspa.
	 *
	 * @param produttore produttore
	 * @param modello modello
	 * @param capacita capacita
	 * @param portata portata
	 * @param altezza altezza
	 * @return true, if successful
	 */
	public  boolean aggiungiRuspa(String produttore, String modello,int capacita,int portata,int altezza);
	
	/**
	 * Aggiungi escavatore.
	 *
	 * @param produttore produttore
	 * @param Modello modello
	 * @param capacita capacita
	 * @param portata portata
	 * @param altezza altezza
	 * @param profondita profondita
	 * @return true, if successful
	 */
	public  boolean aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	
	/**
	 * Aggiungi camion.
	 *
	 * @param produttore produttore
	 * @param Modello modello
	 * @param capacita capacita
	 * @param portata portata
	 * @param lunghezza lunghezza
	 * @return true, if successful
	 */
	public  boolean aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza);
	
	/**
	 * Aggiungi cantiere.
	 *
	 * @param nomeCantiere nome cantiere
	 * @param indirizzo indirizzo
	 * @param dataApertura data apertura
	 * @param dataChiusura data chiusura
	 * @param priorita priorita
	 * @return true, if successful
	 */
	public  boolean aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita );
	
	
	
	/**
	 * Modifica camion.
	 *
	 * @param codiceCamion codice camion
	 * @param produttore produttore
	 * @param modello modello
	 * @param capacita capacita
	 * @param portata portata
	 * @param lunghezza lunghezza
	 * @return true, if successful
	 */
	public  boolean modificaCamion(int codiceCamion,String produttore,String modello,int capacita,int portata,int lunghezza);
	
	/**
	 * Modifica gru.
	 *
	 * @param codiceGru codice gru
	 * @param produttore produttore
	 * @param modello modello
	 * @param rotazione rotazione
	 * @param portata portata
	 * @param lunghezza lunghezza
	 * @param altezza altezza
	 * @return true, if successful
	 */
	public  boolean modificaGru(int codiceGru,String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza) ;
	
	/**
	 * Modifica ruspa.
	 *
	 * @param codiceRuspa codice ruspa
	 * @param produttore produttore
	 * @param modello modello
	 * @param capacita capacita
	 * @param portata portata
	 * @param altezza altezza
	 * @return true, if successful
	 */
	public  boolean modificaRuspa(int codiceRuspa,String produttore, String modello,int capacita,int portata,int altezza) ;
	
	/**
	 * Modifica escavatore.
	 *
	 * @param codiceEscavatore codice escavatore
	 * @param produttore produttore
	 * @param modello modello
	 * @param capacita capacita
	 * @param portata portata
	 * @param altezza altezza
	 * @param profondita profondita
	 * @return true, if successful
	 */
	public  boolean modificaEscavatore(int codiceEscavatore,String produttore, String modello,int capacita,int portata,int altezza,int profondita);
		
}


