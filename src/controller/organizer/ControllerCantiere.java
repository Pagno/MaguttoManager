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
 * 
 */
public class ControllerCantiere{// implements AbstractCantieriController{

	/**   model. */
	ModelInterface model;

	/** The istanza. */
	private static ControllerCantiere istanza;

	/**
	 * Ritorna l'istanza di ControllerCantiere. Se non era ancora instanziato la crea.
	 *
	 * @param ModelInterface per manipolare i dati gestiti dall'applicazione
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
	 * @param ModelInterface per manipolare i dati inseriti nell'applicazione
	 * 
	 */
	private ControllerCantiere(ModelInterface modelConnector) {
		model = modelConnector;
	}

	/**
	 * Gets the cantiere.
	 *
	 * @param codiceCantiere codice del cantiere che si sta cercando
	 * @return il cantiere con il dato codice, null se non esiste un cantiere con quel codice
	 */
	public Cantiere getCantiere(int codiceCantiere) {
		return model.getCantiere(codiceCantiere);
	}


	/**
	 * Modifica cantiere.
	 *
	 * @param codiceCantiere Codice del cantiere
	 * @param nomeCantiere Nome del cantiere
	 * @param indirizzo Indirizzo del cantiere
	 * @param dataApertura Data di apertura del cantiere
	 * @param dataChiusura Data di chiusura del cantiere
	 * @param priorita Priorita  legata al cantiere
	 * @return true se il cantiere è stato correttamente modificato.
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
	 * Aggiungi nuovo lavoro al Cantiere.
	 *
	 * @param codiceCantiere Codice del cantiere dove inserire il Lavoro
	 * @param nomeLavoro Nome lavoro
	 * @param dataInizio Data inizio dellavoro
	 * @param dataFine Data fine del lavoro
	 * @return true se il lavoro è stato correttamente inserito.
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
	 * Collega una richiesta scoperta con una macchina
	 *
	 * @param codiceRichiesta Codice richiesta da soddisfare
	 * @param codiceMacchina Codice macchina da associare alla richiesta
	 * @return true se la macchina è stata correttamente assegnata alla richiesta
	 */
	public boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina){
		return model.soddisfaRichiesta(codiceRichiesta, codiceMacchina);
	}

	/*public void aggiungiRichiestaObserver(Observer observer ) {
		model.aggiungiLavoroObserver(observer);
	}*/

	/**
	 * Elimina lavoro.
	 *
	 * @param codiceLavoro Codice lavoro da eliminare
	 * @return true se correttamente eliminato
	 */
	public boolean eliminaLavoro(int codiceLavoro) {
		return model.eliminaLavoro(codiceLavoro);
	}

	/**
	 * Elimina richiesta.
	 *
	 * @param codiceRichiesta Codice richiesta da eliminare
	 * @return true se correttamente eliminata.
	 */
	public boolean eliminaRichiesta(int codiceRichiesta) {
		return model.eliminaRichiesta(codiceRichiesta);
	}

	/**
	 * Elenco macchine disponibili per una richiesta.
	 *
	 * @param codiceRichiesta Codice richiesta
	 * @return Elenco macchine disponibili
	 */
	public ArrayList<Macchina> getElencoMacchineDisponibili(int codiceRichiesta) {
		return model.getElencoMacchineDisponibili(codiceRichiesta);
	}
	
	 
	/**
	 * Elimina l'associazione tra la richiesta identificata dal codiceRichiesta e la macchina ad essa colllegata
	 *
	 * @param codiceRichiesta Codice richiesta da liberare
	 * @return true se la richiesta è stata correttamente liberata.
	 */
	public boolean liberaRichiesta(int codiceRichiesta){
		return model.liberaRichiesta(codiceRichiesta);
	}

	 
	/**
	 * Aggiungi una nuova richiesta.
	 *
	 * @param codiceCantiere Codice cantiere da associare alla richiesta
	 * @param codiceLavoro Codice lavoro da associare alla richiesta
	 * @param richiesta Tipo di macchina richiesta con le relative caratteristiche
	 * @return true se la richiesta viene correttamente inserita
	 */
	public boolean aggiungiRichiesta(int codiceCantiere,int codiceLavoro,RichiestaMacchina richiesta){
		return model.aggiungiRichiesta( codiceCantiere, codiceLavoro, richiesta);
	}
	

	/**
	 * Genera le migliori associazioni automaticamente dall'algoritmo greedy.
	 *
	 * @return associazioni generata dall'algoritmo Greedy.
	 */
	public ArrayList<Associazione> generaAssociazioni(){
		return controller.greedy.GreedyEngine.generaAssociazioni(model);
	}

	 
	/**
	 * Conferma le associazioni generate dall'algoritmo Greddy
	 *
	 * @param data elenco delle associazioni generate dall'algoritmo Greddy
	 */
	public void confermaAssociazioniListener(ArrayList<Associazione> data) {
		for(Associazione associazione:data){
			associazione.concretizza();
		}		
	}
	
	 
	/**
	 * Modifica lavoro.
	 *
	 * @param codiceLavoro Codice lavoro da modificare
	 * @param nome Nuovo nome del lavoro
	 * @param inizio Nuova data di inizio
	 * @param fine Nuova data di fine
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
	 * Inits the for test.
	 */
	public void initForTest(){
		if(istanza!=null){
			ModelMacchina.initCodice();
			istanza=null;
		}
	}
}

