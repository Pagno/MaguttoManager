package controller.organizer;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

import model.ModelInterface;
import model.organizer.ModelMacchina;
import model.organizer.data.Cantiere;
import model.organizer.data.Priorita;
import model.organizer.data.Macchina;
import model.organizer.data.RichiestaMacchina;
import controller.data.Associazione;

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
	public void modificaLavoro(int codiceLavoro, String nome,
			GregorianCalendar inizio, GregorianCalendar fine) {
		model.modificaLavoro(codiceLavoro, nome, inizio, fine);
		
		/*
		 * ArrayList<Richiesta> richieste=model.getElencoRichieste(codiceLavoro);
				//Prendo le vecchie date
				oldStartDate=((richieste.get(0)).getDataInizio());
				oldEndDate=((richieste.get(0)).getDataFine());
				/*
				 * Se la nuova data di inizio e posteriore alla vecchia data di inizio e la 
				 * se la nuova data di fine e antecedente alla vecchia data di fine 
				 * posso tranquillamente modificare il lavoro
				 * 
				boolean modifica=true;
				if(newStartDate.before(oldStartDate) || newEndDate.after(oldStartDate)){
					for(Richiesta richiesta:richieste){
						if(newStartDate.before(oldStartDate))
							modifica=modifica&&richiesta.getMacchina().isFree(newStartDate, oldStartDate);
						if(newEndDate.after(oldStartDate))
							modifica=modifica&&richiesta.getMacchina().isFree(oldEndDate, newEndDate);
					}
				}
				if(modifica){
					model.modificaLavoro(codiceLavoro, editLavoro.getNomeLavoro(), newStartDate, newEndDate); 
				}
				else{
					JOptionPane.showMessageDialog(editLavoro, "Non ������������������ stato possibile modificare il lavoro. Potrebbero esserci sovrapposizioni con le date di altri lavori.", "Error",JOptionPane.ERROR_MESSAGE);
				}
				System.out.println("CodiceLavoro: "+codiceLavoro);	
		*/
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

