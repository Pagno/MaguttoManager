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

// TODO: Auto-generated Javadoc
/**
 *   L'interfaccia ModelInterface definisce le funzioni necessarie per accedere alle funzionalit&agrave; del componente model da
 *   parte degli altri componenti.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public interface ModelInterface {
	
	/**
	 * Salva i dati su database.
	 */
	public void storeData();
	
	/**
	 * Ricarica i dati dal database.
	 */
	public void refreshData();
	
	//GRU
	/**
	 * Aggiunge una nuova gru.
	 *
	 * @param produttore Il produttore della gru
	 * @param modello Il modello della gru
	 * @param rotazione L'angolo di rotazione della gru
	 * @param portata La portata della gru
	 * @param lunghezza La lunghezza della gru
	 * @param altezza L'altezza della gru
	 */
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);
	
	/**
	 * Modifica la gru indicata dal codice inserito.
	 *
	 * @param codice Il codice della gru da modificare
	 * @param produttore Il produttore della gru
	 * @param modello Il modello della gru
	 * @param rotazione L'angolo di rotazione della gru
	 * @param portata La portata della gru
	 * @param lunghezza La lunghezza della gru
	 * @param altezza L'altezza della gru
	 */
	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);

	//CAMION
	/**
	 * Aggiunge un nuovo camion.
	 *
	 * @param produttore Il produttore del camion
	 * @param modello Il modello del camion
	 * @param capacita La capacit&agrave; del camion
	 * @param portata La portata del camion
	 * @param lunghezza La lunghezza del camion
	 */
	public void aggiungiCamion(String produttore,String modello,int capacita,int portata,int lunghezza);
	
	/**
	 * Modifica il camion indicato dal codice inserito.
	 *
	 * @param codice the codice
	 * @param produttore Il produttore del camion
	 * @param modello Il modello del camion
	 * @param capacita La capacit&agrave; del camion
	 * @param portata La portata del camion
	 * @param lunghezza La lunghezza del camion
	 */
	public void modificaCamion(int codice,String produttore,String modello,int capacita,int portata,int lunghezza);

	//RUSPA
	/**
	 * Aggiunge una nuova ruspa.
	 *
	 * @param produttore Il produttore della ruspa
	 * @param modello Il modello della ruspa
	 * @param capacita La capacit&agrave; della ruspa
	 * @param portata La portata della ruspa
	 * @param altezza L'altezza della ruspa
	 */
	public void aggiungiRuspa(String produttore, String modello,int capacita,int portata,int altezza);
	
	/**
	 * Modifica la ruspa indicata dal codice inserito.
	 *
	 * @param codice Il codice della ruspa da modificare
	 * @param produttore Il produttore della ruspa
	 * @param modello Il modello della ruspa
	 * @param capacita La capacit&agrave; della ruspa
	 * @param portata La portata della ruspa
	 * @param altezza L'altezza della ruspa
	 */
	public void modificaRuspa(int codice, String produttore, String modello,int capacita,int portata,int altezza);

	//ESCAVATORE
	/**
	 * Aggiunge un nuovo escavatore.
	 *
	 * @param produttore Il produttore dell'escavatore
	 * @param modello Il modello dell'escavatore
	 * @param capacita La capacit&agrave; dell'escavatore
	 * @param portata La portata dell'escavatore
	 * @param altezza L'altezza dell'escavatore
	 * @param profondita La profondit&agrave; dell'escavatore
	 */
	public void aggiungiEscavatore(String produttore, String modello,int capacita,int portata,int altezza,int profondita);
	
	/**
	 * Modifica l'escavatore indicato dal codice corrente.
	 *
	 * @param codice Il codice dell'escavatore da modificare
	 * @param produttore Il produttore dell'escavatore
	 * @param modello Il modello dell'escavatore
	 * @param capacita La capacit&agrave; dell'escavatore
	 * @param portata La portata dell'escavatore
	 * @param altezza L'altezza dell'escavatore
	 * @param profondita La profondit&agrave; dell'escavatore
	 */
	public void modificaEscavatore(int codice, String produttore, String modello,int capacita,int portata,int altezza,int profondita);
	
	//COMUNE A TUTTE LE MACCHINE
	/**
	 * Elimina la macchina indicata dal codice inserito.
	 *
	 * @param codice Il codice della macchina da eliminare
	 * @return true, se la macchina viene eliminata correttamente
	 */
	public boolean eliminaMacchina(int codice);
	
	//CANTIERE
	/**
	 * Aggiunge un nuovo cantiere.
	 *
	 * @param nomeCantiere Il nome del cantiere
	 * @param indirizzo L'indirizzo del cantiere
	 * @param dataApertura La data di apertura del cantiere
	 * @param dataChiusura La data di chiusura del cantiere 
	 * @param priorita La priorit&agrave; del cantiere
	 */
	public void aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita);
	
	/**
	 * Modifica il cantiere indicato dal codice inserito.
	 *
	 * @param codice Il codice del cantiere da modificare
	 * @param nomeCantiere Il nome del cantiere
	 * @param indirizzo L'indirizzo del cantiere
	 * @param dataApertura La data di apertura del cantiere
	 * @param dataChiusura La data di chiusura del cantiere 
	 * @param priorita La priorit&agrave; del cantiere
	 */
	public void modificaCantiere(int codice, String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita);
	
	/**
	 * Elimina il cantiere indicato dal codice inserito.
	 *
	 * @param codice Il codice del cantiere da eliminare
	 * @return true, se il cantiere viene eliminato correttamente
	 */
	public boolean eliminaCantiere(int codice);
	
	/**
	 * Inserisce un nuovo lavoro.
	 *
	 * @param nome Il nome del lavoro
	 * @param inizio La data di inizio del lavoro
	 * @param fine La data di fine del lavoro
	 * @param idCantiere Il codice del cantiere cui appartiene il lavoro
	 * 
	 */
	public void aggiungiLavoro(String nome,GregorianCalendar inizio,GregorianCalendar fine,int idCantiere);
	
	/**
	 * Modifica il lavoro indicato dal codice inserito.
	 *
	 * @param codiceLavoro Il codice del lavoro da modificare
	 * @param nome Il nome del lavoro
	 * @param dataInizio the data inizio
	 * @param dataFine the data fine
	 */
	public void modificaLavoro(int codiceLavoro, String nome, GregorianCalendar dataInizio,GregorianCalendar dataFine);
		
	/**
	 * Elimina il lavoro indicato dal codice inserito.
	 *
	 * @param codiceLavoro Il codice del lavoro da eliminare
	 * @return true, se il lavoro viene eliminato correttamente
	 */

	public boolean eliminaLavoro(int codiceLavoro);
	
	/**
	 * Aggiunge una nuova Richiesta.
	 *
	 * @param codiceCantiere Il codice del cantiere cui appartiene il lavoro
	 * @param codiceLavoro Il codice del lavoro cui appartiene la richiesta
	 * @param richiesta Le caratteristiche della macchina richiesta
	 * @return true, se la richiesta viene inserita correttamente
	 * 
	 */
	public boolean aggiungiRichiesta(int codiceCantiere, int codiceLavoro,RichiestaMacchina richiesta);
	
	/**
	 * Cancella la richiesta cui appartiene il codice inserito.
	 *
	 * @param codiceRichiesta Il codice della richiesta da cancellare
	 * @return true, se la richiesta viene eliminata correttamente
	 */

	public boolean eliminaRichiesta(int codiceRichiesta);
	
	/**
	 * Associa una macchina ad una richiesta.
	 *
	 * @param codiceRichiesta Il codice della richiesta da associare
	 * @param codiceMacchina Il codice della macchina da associare
	 * @return true, se la macchina viene associata correttamente
	 */
	public boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina);	
	
	/**
	 * Restituisce il cantiere indicato dal codice inserito.
	 *
	 * @param codiceCantiere Il codice del cantiere
	 * @return Il cantiere desiderato
	 */
	public Cantiere getCantiere(int codiceCantiere);
	
	

	/**
	 * Restituisce l'elenco delle ruspe disponibili.
	 *
	 * @param codiceRichiesta Il codice della richiesta per cui si sta ricercando una ruspa
	 * @param codiceLavoro Il codice del lavoro cui appartiene la richiesta
	 * @return L'elenco delle ruspe disponibili
	 */
	public ArrayList<Ruspa> getElencoRuspeDisponibili(int codiceRichiesta,int codiceLavoro);
	
	/**
	 * Restituisce l'elenco delle gru disponibili.
	 *
	 * @param codiceRichiesta Il codice della richiesta per cui si sta ricercando una gru
	 * @param codiceLavoro Il codice del lavoro cui appartiene la richiesta
	 * @return L'elenco delle gru disponibili
	 */
	public ArrayList<Gru> getElencoGruDisponibili(int codiceRichiesta,int codiceLavoro);
	
	/**
	 * Restituisce l'elenco dei camion disponibili.
	 *
	 * @param codiceRichiesta Il codice della richiesta per cui si sta ricercando un camion
	 * @param codiceLavoro Il codice del lavoro cui appartiene la richiesta
	 * @return L'elenco dei camion disponibili
	 */
	public ArrayList<Camion> getElencoCamionDisponibili(int codiceRichiesta,int codiceLavoro);
	
	/**
	 * Restituisce l'elenco degli escavatori disponibili.
	 *
	 * @param codiceRichiesta Il codice della richiesta per cui si sta ricercando un escavatore
	 * @param codiceLavoro Il codice del lavoro cui appartiene la richiesta
	 * @return L'elenco degli escavatori disponibili
	 */
	public ArrayList<Escavatore> getElencoEscavatoriDisponibili(int codiceRichiesta,int codiceLavoro);
	
	/**
	 * Restituisce la lista dei cantieri.
	 *
	 * @return La lista dei cantieri
	 */
	public ArrayList<Cantiere> getListaCantieri();
	
	/**
	 * Restituisce la lista delle richieste scoperte.
	 *
	 * @return La lista delle richieste ancora non associate a una macchina
	 */
	public ArrayList<Richiesta> getRichiesteScoperte();
	
	
	/**
	 * Pubblica l'elenco completo delle macchine, dei cantieri, dei lavori e delle richieste.
	 */
	public void pubblicaContenuto();

	/**
	 * Aggiunge un gru observer.
	 *
	 * @param observer L'observer da inserire
	 */
	public void aggiungiGruObserver(Observer observer);
	
	/**
	 * Aggiunge un ruspa observer.
	 *
	 * @param observer L'observer da inserire
	 */
	public void aggiungiRuspaObserver(Observer observer);
	
	/**
	 * Aggiunge un camion observer.
	 *
	 * @param observer L'observer da inserire
	 */
	public void aggiungiCamionObserver(Observer observer);	
	
	/**
	 * Aggiunge un escavatore observer.
	 *
	 * @param observer L'observer da inserire
	 */
	public void aggiungiEscavatoreObserver(Observer observer);	
	
	/**
	 * Aggiunge un cantiere observer.
	 *
	 * @param observer L'observer da inserire
	 */
	public void aggiungiCantiereObserver(Observer observer);
	
	/*public void aggiungiLavoroObserver(Observer observer);
	public void aggiungiRichiestaObserver(Observer observer);*/
	

	/*/**
	 * Restituisce un'arraylist lista dei lavori di un particolare cantiere.
	 *
	 * @param codiceCantiere Il codice del cantiere desiderato
	 * @return La lista dei lavori appartenenti al cantiere
	 */
	/*public ArrayList<ArrayList<String>> getLavoriCantiereList(int codiceCantiere);*/
	
	/*/**
	 * Restituisce la lista delle richieste di un particolare l
	 *
	 * @param codiceCantiere the codice cantiere
	 * @return the richieste lavoro list
	 */
	/*public ArrayList<ArrayList<String>> getRichiesteLavoroList(int codiceCantiere);*/
	
	/**
	 * Libera la richiesta, cancellando l'associazione con la macchina.
	 *
	 * @param codiceRichiesta Il codice della richiesta da liberare
	 * @return true, se la disassociazione &egrave; andata a buon fine
	 */
	public boolean liberaRichiesta(int codiceRichiesta);
	
	/**
	 * Restituisce l'elenco delle macchine disponibili per una particolare richiesta.
	 *
	 * @param codiceRichiesta Il codice della richiesta che deve esser soddisfatta dalle macchine
	 * @return L'elenco macchine disponibili per la richiesta corrente
	 */
	public ArrayList<Macchina> getElencoMacchineDisponibili(int codiceRichiesta);

	/**
	 * Restituisce l'elenco delle richieste appartenenti a un particolare lavoro.
	 *
	 * @param codicelavoro Il codice del lavoro richiesto
	 * @return L'elenco delle richieste appartenenti al lavoro richiesto
	 */
	public ArrayList<Richiesta> getElencoRichieste(int codicelavoro);
	
	/**
	 * Restituisce la richiesta indicata dal codice inserito.
	 *
	 * @param codiceRichiesta Il codice della richiesta desiderata
	 * @return La richiesta desiderata
	 */
	public Richiesta getRichiesta(int codiceRichiesta);
	
	/**
	 * Restituisce la macchina indicata dal codice inserito.
	 *
	 * @param codice Il codice della macchina desiderata
	 * @return La macchina desiderata
	 */
	public Macchina getMacchina(int codice);

}
