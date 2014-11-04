package controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import model.organizer.data.Cantiere;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
import model.organizer.data.RichiestaMacchina;
import controller.greedy.data.Associazione;


/**
 *   L'interfaccia ControllerInterface definisce i metodi necessari a mettere in comunicazione i componenti view e model.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public interface ControllerInterface {
	
	
	
	//Application Interface
	/**
	 * Elimina la macchina indicata dal codice inserito.
	 *
	 * @param codiceMacchina Il codice della macchina da rimuovere
	 * @return true, se la macchina viene eliminata
	 */
	public boolean eliminaMacchina(Integer codiceMacchina);	
	
	/**
	 * Elimina il cantiere indicato dal codice inserito.
	 *
	 * @param codiceCantiere Il codice del cantiere da rimuovere
	 * @return true, se il cantiere viene eliminato
	 */
	public boolean eliminaCantiere(Integer codiceCantiere);	

	/**
	 * Carica i dati dell'applicazione.
	 */
	public void caricaDati();
	
	/**
	 * Salva i dati dell'applicazione.
	 */
	public void salvaDati();
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
	 * Restituisce il cantiere avente quel dato codice.
	 *
	 * @param codiceCantiere Il codice del cantiere desiderato
	 * @return Il cantiere desiderato
	 */
	public  Cantiere getCantiere(int codiceCantiere);
	
	/**
	 * Modifica il cantiere avente il codice indicato.
	 *
	 * @param codiceCantiere Il codice del cantiere
	 * @param nomeCantiere Il nome del cantiere
	 * @param indirizzo L'indirizzo del cantiere
	 * @param dataApertura La data di apertura del cantiere
	 * @param dataChiusura La data di chiusura del cantiere
	 * @param priorita La prioritY&agrave; del cantiere
	 * @return true, se la modifica va a buon fine
	 */
	public  boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita );
	
	/**
	 * Aggiunge un lavoro.
	 *
	 * @param codiceCantiere Il codice del cantiere
	 * @param nomeLavoro Il nome del lavoro
	 * @param dataInizio La data di inizio
	 * @param dataFine La data di fine
	 * @return true, se il lavoro viene inserito correttamente
	 */
	public  boolean aggiungiLavoro(int codiceCantiere,String nomeLavoro,GregorianCalendar dataInizio,GregorianCalendar dataFine);


	
	/**
	 * Elimina il lavoro indicato dal codice.
	 *
	 * @param codiceLavoro Il codice del lavoro
	 * @return true, se il lavoro viene eliminato correttamente
	 */
	public  boolean eliminaLavoro(int codiceLavoro);
	
	/**
	 * Elimina la richiesta.
	 *
	 * @param codiceRichiesta Il codice della richiesta
	 * @return true, se la richiesta viene eliminata correttamente
	 */
	public  boolean eliminaRichiesta(int codiceRichiesta);
	
	/**
	 * Restituisce l'elenco delle macchine disponibili.
	 *
	 * @param codiceRichiesta Il codice della richiesta
	 * @return L'elenco delle macchine disponibili
	 */
	public   ArrayList<Macchina>  getElencoMacchineDisponibili(int codiceRichiesta);
	
	/**
	 * Libera la richiesta.
	 *
	 * @param codiceRichiesta Il codice della richiesta
	 * @return true, se la richiesta viene liberata correttamente
	 */
	public  boolean liberaRichiesta(int codiceRichiesta);
	
	/**
	 * Aggiunge una richiesta.
	 *
	 * @param codiceCantiere Il codice del cantiere
	 * @param codiceLavoro Il codice del lavoro
	 * @param richiesta La richiesta
	 * @return true, se la richiesta viene aggiunta correttamente
	 */
	public  boolean aggiungiRichiesta(int codiceCantiere,int codiceLavoro,RichiestaMacchina richiesta);
	
	/**
	 * Soddisfa la richiesta indicata dal codice.
	 *
	 * @param codiceRichiesta Il codice della richiesta
	 * @param codiceMacchina Il codice della macchina da associare
	 * @return true, se la richiesta viene associata correttamente alla macchina
	 */
	public  boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina) ;
	
	/**
	 * Genera le associazioni utilizzando il GreedyEngine.
	 *
	 * @return array list
	 */
	public  ArrayList<Associazione> generaAssociazioni();
	
	/**
	 * Conferma le associazioni generate dall'algoritmo Greedy.	
	 * 
	 * @param data the data
	 */
	public  void confermaAssociazioniListener(ArrayList<Associazione> data);
	
	/**
	 * Modifica il lavoro indicato dal codice.
	 *
	 * @param codiceLavoro Il codice del lavoro
	 * @param nome Il nome del lavoro
	 * @param inizio La data d'inizio del lavoro
	 * @param fine La data di fine del lavoro
	 */
	public  boolean modificaLavoro(int codiceCantiere,int codiceLavoro, String nome,GregorianCalendar inizio, GregorianCalendar fine);

	//Insert Interface
	/**
	 * Aggiunge una gru.
	 *
	 * @param produttore Il produttore della gru
	 * @param modello Il modello della gru
	 * @param rotazione L'angolo di rotazione della gru
	 * @param portata La portata della gru
	 * @param lunghezza La lunghezza della gru
	 * @param altezza L'altezza della gru
	 * @return true, se la gru viene aggiunta correttamente
	 */
	public  boolean aggiungiGru(String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza);
	
	/**
	 * Aggiunge una ruspa.
	 *
	 * @param produttore Il produttore della ruspa
	 * @param modello Il modello della ruspa
	 * @param capacita La capacit&agrave; della ruspa
	 * @param portata La portata della ruspa
	 * @param altezza L'altezza della ruspa
	 * @return true, se la ruspa viene aggiunta correttamente
	 */
	public  boolean aggiungiRuspa(String produttore, String modello,int capacita,int portata,int altezza);
	
	/**
	 * Aggiunge un escavatore.
	 *
	 * @param produttore Il produttore dell'escavatore
	 * @param modello Il modello dell'escavatore
	 * @param capacita La capacit&agrave; dell'escavatore
	 * @param portata La portata dell'escavatore
	 * @param altezza L'altezza dell'escavatore
	 * @param profondita La profondit&agrave; dell'escavatore
	 * @return true, se l'escavatore viene aggiunto correttamente
	 */
	public  boolean aggiungiEscavatore(String produttore, String modello,int capacita,int portata,int altezza,int profondita);
	
	/**
	 * Aggiunge un camion.
	 *
	 * @param produttore Il produttore del camion
	 * @param modello Il modello del camion
	 * @param capacita La capacit&agrave; del camion
	 * @param portata La portata del camion
	 * @param lunghezza La lunghezza del camion
	 * @return true, se il camion viene aggiunto correttamente
	 */
	public  boolean aggiungiCamion(String produttore,String modello,int capacita,int portata,int lunghezza);
	
	/**
	 * Aggiunge il cantiere.
	 *
	 * @param nomeCantiere Il nome del cantiere
	 * @param indirizzo L'indirizzo del cantiere
	 * @param dataApertura La data di apertura del cantiere
	 * @param dataChiusura La data di chiusura del cantiere
	 * @param priorita La priorit&agrave; del cantiere
	 * @return true, se il cantiere viene aggiunto correttamente
	 */
	public  boolean aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita );
	
	
	
	/**
	 * Modifica il camion indicato dal codice.
	 *
	 * @param codiceCamion Il codice camion da modificare
	 * @param produttore Il produttore del camion
	 * @param modello Il modello del camion
	 * @param capacita La capacit&agrave; del camion
	 * @param portata La portata del camion
	 * @param lunghezza La lunghezza del camion
	 * @return true, se il camion viene modificato correttamente
	 */
	public  boolean modificaCamion(int codiceCamion,String produttore,String modello,int capacita,int portata,int lunghezza);
	
	/**
	 * Modifica la gru indicata dal codice.
	 *
	 * @param codiceGru Il codice della gru da modificare
	 * @param produttore Il produttore della gru
	 * @param modello Il modello della gru
	 * @param rotazione L'angolo di rotazione della gru
	 * @param portata La portata della gru
	 * @param lunghezza La lunghezza della gru
	 * @param altezza L'altezza della gru
	 * @return true, se la gru viene modificata correttamente
	 */
	public  boolean modificaGru(int codiceGru,String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza) ;
	
	/**
	 * Modifica la ruspa indicata dal codice.
	 *
	 * @param codiceRuspa Il codice della ruspa da modificare
	 * @param produttore Il produttore della ruspa
	 * @param modello Il modello della ruspa
	 * @param capacita La capacit&agrave; della ruspa
	 * @param portata La portata della ruspa
	 * @param altezza L'altezza della ruspa
	 * @return true, se la ruspa viene modificata correttamente
	 */
	public  boolean modificaRuspa(int codiceRuspa,String produttore, String modello,int capacita,int portata,int altezza) ;
	
	/**
	 * Modifica l'escavatore indicato dal codice.
	 *
	 * @param codiceEscavatore Il codice dell'escavatore da modificare
	 * @param produttore Il produttore dell'escavatore
	 * @param modello Il modello dell'escavatore
	 * @param capacita La capacit&agrave; dell'escavatore
	 * @param portata La portata dell'escavatore
	 * @param altezza L'altezza dell'escavatore
	 * @param profondita La profondit&agrave; dell'escavatore
	 * @return true, se l'escavatore viene modificato correttamente
	 */
	public  boolean modificaEscavatore(int codiceEscavatore,String produttore, String modello,int capacita,int portata,int altezza,int profondita);
		
}


