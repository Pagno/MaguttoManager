package controller.organizer;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import model.ModelInterface;
import model.organizer.ModelCantiere;
import model.organizer.ModelMacchina;
import model.organizer.data.Cantiere;
import model.organizer.data.Priorita;
import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaMacchina;
import controller.greedy.data.Associazione;

/**
 * 
 * Questa Classe permette di gestire gli eventi,
 * legati all'interazione con le view per la gestione dei Cantieri
 * 
 * @author Matteo Pagnoncelli
 * @author Mauro Valota
 */
public class ControllerCantiere{// implements AbstractCantieriController{

	/**   Model. */
	ModelInterface model;

	/** Istanza. */
	private static ControllerCantiere istanza;

	/**
	 * Restituisce l'istanza di ControllerCantiere. Se non era ancora instanziato la crea, implementando il pattern Singleton.
	 *
	 * @param ModelInterface Interfaccia per manipolare i dati gestiti dall'applicazione
	 * @return  Istanza di ControllerCantiere
	 */
	public static synchronized ControllerCantiere getControllerCantiere(ModelInterface modelConnector){
		if(istanza==null){
			istanza=new ControllerCantiere(modelConnector);
		}
		return istanza;
	}
	/**
	 * Istanzia un nuovo controllore.
	 *
	 * @param ModelInterface Interfaccia per manipolare i dati inseriti nell'applicazione
	 * 
	 */
	private ControllerCantiere(ModelInterface modelConnector) {
		model = modelConnector;
	}

	/**
	 * Restituisce il cantiere.
	 *
	 * @param codiceCantiere Il codice del cantiere che si sta cercando
	 * @return il cantiere con il dato codice, null se non esiste un cantiere con quel codice
	 */
	public Cantiere getCantiere(int codiceCantiere) {
		return model.getCantiere(codiceCantiere);
	}


	/**
	 * Modifica il cantiere.
	 *
	 * @param codiceCantiere Il codice del cantiere
	 * @param nomeCantiere Il nome del cantiere
	 * @param indirizzo L'indirizzo del cantiere
	 * @param dataApertura La data di apertura del cantiere
	 * @param dataChiusura La data di chiusura del cantiere
	 * @param priorita La priorit&agrave; legata al cantiere
	 * @return true se il cantiere &egrave; stato correttamente modificato.
	 */
	public boolean modificaCantiere(int codiceCantiere, String nomeCantiere,
			String indirizzo, GregorianCalendar dataApertura,
			GregorianCalendar dataChiusura, Priorita priorita) {
		
		
		
		if(nomeCantiere=="" || indirizzo==""){
			JOptionPane
			.showMessageDialog(
					null,
					"I campi:\n - Nome cantiere\n - Indirizzo Cantiere\ndevono essere compilati. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(dataChiusura.before(dataApertura)){
			JOptionPane
			.showMessageDialog(
					null,
					"La data di inizio deve essere inferiore alla data di chiusura. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(model.getCantiere(codiceCantiere).getElencoLavori().size()!=0){
			//Se il cantiere ha lavori non puo restringere le date ma solo allargarle
			GregorianCalendar inizio=model.getCantiere(codiceCantiere).getDataApertura();
			GregorianCalendar fine=model.getCantiere(codiceCantiere).getDataChiusura();
			if(dataApertura.after(inizio) || dataChiusura.before(fine)){
				JOptionPane
				.showMessageDialog(
						null,
						"Se il cantiere ha gia dei lavori non si possono restringere le date. ",
						"Alert", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
			
		model.modificaCantiere(codiceCantiere,nomeCantiere, indirizzo, dataApertura, dataChiusura, priorita);
		
		return true;
	}


	/**
	 * Aggiunge un nuovo lavoro al Cantiere.
	 *
	 * @param codiceCantiere Il codice del cantiere dove inserire il Lavoro
	 * @param nomeLavoro Il nome lavoro
	 * @param dataInizio La data d'inizio del lavoro
	 * @param dataFine La data di fine del lavoro
	 * @return true se il lavoro &egrave; stato correttamente inserito.
	 */
	public boolean aggiungiLavoro(int codiceCantiere, String nomeLavoro,
			GregorianCalendar dataInizio, GregorianCalendar dataFine) {
		Cantiere c=model.getCantiere(codiceCantiere);
		if(nomeLavoro==""){

			JOptionPane
			.showMessageDialog(
					null,
					"I campi:\n - Nome cantiere\n devono essere compilati. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
		}
		if(dataInizio.before(c.getDataApertura()) ||  dataFine.after(c.getDataChiusura())){
			JOptionPane
			.showMessageDialog(
					null,
					"Le date del nuovo lavoro devono essere comprese tra le date di apertura e chiusura del cantiere. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		
		model.aggiungiLavoro(nomeLavoro, dataInizio, dataFine, codiceCantiere);
		return true;
	}


	/**
	 * Collega una richiesta scoperta con una macchina.
	 *
	 * @param codiceRichiesta Il codice della richiesta da soddisfare
	 * @param codiceMacchina Il codice della macchina da associare alla richiesta
	 * @return true se la macchina &egrave; stata correttamente assegnata alla richiesta
	 */
	public boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina){
		return model.soddisfaRichiesta(codiceRichiesta, codiceMacchina);
	}

	/*public void aggiungiRichiestaObserver(Observer observer ) {
		model.aggiungiLavoroObserver(observer);
	}*/

	/**
	 * Elimina il lavoro.
	 *
	 * @param codiceLavoro Il codice del lavoro da eliminare
	 * @return true se il lavoro viene correttamente eliminato
	 */
	public boolean eliminaLavoro(int codiceLavoro) {
		return model.eliminaLavoro(codiceLavoro);
	}

	/**
	 * Elimina la richiesta.
	 *
	 * @param codiceRichiesta Il codice della richiesta da eliminare
	 * @return true se correttamente eliminata.
	 */
	public boolean eliminaRichiesta(int codiceRichiesta) {
		return model.eliminaRichiesta(codiceRichiesta);
	}

	/**
	 * Elenco delle macchine disponibili per una richiesta.
	 *
	 * @param codiceRichiesta Il codice della richiesta
	 * @return Elenco delle macchine disponibili
	 */
	public ArrayList<Macchina> getElencoMacchineDisponibili(int codiceRichiesta) {
		return model.getElencoMacchineDisponibili(codiceRichiesta);
	}
	
	 
	/**
	 * Elimina l'associazione tra la richiesta identificata dal codiceRichiesta e la macchina ad essa colllegata
	 *
	 * @param codiceRichiesta Il codice della richiesta da liberare
	 * @return true se la richiesta &egrave; stata correttamente liberata.
	 */
	public boolean liberaRichiesta(int codiceRichiesta){
		return model.liberaRichiesta(codiceRichiesta);
	}

	 
	/**
	 * Aggiunge una nuova richiesta.
	 *
	 * @param codiceCantiere Il codice del cantiere da associare alla richiesta
	 * @param codiceLavoro Il codice del lavoro da associare alla richiesta
	 * @param richiesta Il tipo di macchina richiesta con le relative caratteristiche
	 * @return true se la richiesta viene correttamente inserita
	 */
	public boolean aggiungiRichiesta(int codiceCantiere,int codiceLavoro,RichiestaMacchina richiesta){
		return model.aggiungiRichiesta( codiceCantiere, codiceLavoro, richiesta);
	}
	

	/**
	 * Genera le migliori associazioni automaticamente utilizzando l'algoritmo greedy.
	 *
	 * @return Le associazioni generate dall'algoritmo Greedy.
	 */
	public ArrayList<Associazione> generaAssociazioni(){
		return controller.greedy.GreedyEngine.generaAssociazioni(model);
	}

	 
	/**
	 * Conferma le associazioni generate dall'algoritmo Greedy
	 *
	 * @param data L'elenco delle associazioni generate dall'algoritmo Greedy
	 */
	public void confermaAssociazioniListener(ArrayList<Associazione> data) {
		for(Associazione associazione:data){
			associazione.concretizza();
		}		
	}
	
	 
	/**
	 * Modifica il lavoro.
	 *
	 * @param codiceLavoro Il codice del lavoro da modificare
	 * @param nome Il nuovo nome del lavoro
	 * @param inizio La nuova data di inizio
	 * @param fine La nuova data di fine
	 */
	public boolean modificaLavoro(int codiceCantiere,int codiceLavoro, String nome,
			GregorianCalendar inizio, GregorianCalendar fine) {
		
		Cantiere c=model.getCantiere(codiceCantiere);
		if(inizio.before(c.getDataApertura()) || fine.after(c.getDataChiusura())){
			JOptionPane.showMessageDialog(null, "Le date del Lavoro non possono uscire dai limiti del cantiere.");
			return false;
		}
		
		
		  ArrayList<Richiesta> richieste=model.getElencoRichieste(codiceLavoro);
		  int codiceMacchina;
		  
		  
		  
		  ArrayList<Integer> codiceRichieste=new ArrayList<Integer>();
		  
		  for(Richiesta richiesta:richieste){
			  if(richiesta.isSoddisfatta()){
				 
				  Macchina m=richiesta.getMacchina();
				  codiceMacchina=m.getCodice();
				  model.liberaRichiesta(richiesta.getCodice());
				  if(!m.isLibera(inizio, fine)){
					  codiceRichieste.add(richiesta.getCodice());
				  }
				  soddisfaRichiesta(richiesta.getCodice(), codiceMacchina);
				  
			  }
		  }
		  
		   if(codiceRichieste.size()!=0){
			  int i=JOptionPane.showConfirmDialog(null, "Modificando le date del lavoro corrente, le associazioni delle sue richieste entrano in conflitto con quelle di altri lavori.\n"
			  		+ "Confermando la modifica, le richieste in conflitto verranno liberate.\nContinuare?", "",JOptionPane.YES_NO_OPTION);
			  if(i==JOptionPane.YES_OPTION){
				  for(Integer codice:codiceRichieste){
					  model.liberaRichiesta(codice);
				  }
				  model.modificaLavoro(codiceLavoro, nome, inizio, fine); 
				  return true;
			  }else{
				  return false;
			  }
			  
		  }else{
			  model.modificaLavoro(codiceLavoro, nome, inizio, fine);
			  return true;
		  }
	}
	
	/**
	 * Inizializza per effettuare i test.
	 */
	public void initForTest(){
		if(istanza!=null){
			ModelMacchina.initCodice();
			istanza=null;
		}
	}
}

