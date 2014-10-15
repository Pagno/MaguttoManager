package controller.organizer;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import model.ModelInterface;
import model.organizer.ModelGru;
import model.organizer.data.Cantiere;
import model.organizer.data.Gru;
import model.organizer.data.Priorita;
import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaMacchina;
import controller.data.Associazione;

public class ControllerCantiere{// implements AbstractCantieriController{

	/**   model. */
	ModelInterface model;

	private static ControllerCantiere istanza;

	/**
	 * Gets   model gru.
	 *
	 * @return   model gru
	 */
	public static synchronized ControllerCantiere getCantiereController(ModelInterface modelConnector){
		if(istanza==null){
			istanza=new ControllerCantiere(modelConnector);
		}
		return istanza;
	}
	/**
	 * Istanzia un nuovo controllore InsertController.
	 *
	 * @param modelConnector per manipolare i dati inseriti nell'applicazione
	 * 
	 */
	private ControllerCantiere(ModelInterface modelConnector) {
		model = modelConnector;
	}

	public Cantiere getCantiere(int codiceCantiere) {
		return model.getCantiere(codiceCantiere);
	}


	public boolean modificaCantiere(int codiceCantiere, String nomeCantiere,
			String indirizzo, GregorianCalendar dataApertura,
			GregorianCalendar dataChiusura, Priorita priorita) {
		
		
		return false;
	}


	public boolean aggiungiLavoro(int codiceCantiere, String nomeLavoro,
			GregorianCalendar dataInizio, GregorianCalendar dataFine) {
		model.aggiungiLavoro(nomeLavoro, dataInizio, dataFine, codiceCantiere);
		return true;
	}


	public boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina){
		return model.soddisfaRichiesta(codiceRichiesta, codiceMacchina);
	}

	public void aggiungiRichiestaObserver(Observer observer ) {
		model.aggiungiLavoroObserver(observer);
	}

	public boolean eliminaLavoro(int codiceLavoro) {
		return model.eliminaLavoro(codiceLavoro);
	}

	public boolean eliminaRichiesta(int codiceRichiesta) {
		return model.eliminaRichiesta(codiceRichiesta);
	}

	public ArrayList<Macchina> getElencoMacchineDisponibili(int codiceRichiesta) {
		return model.getElencoMacchineDisponibili(codiceRichiesta);
	}
	
	 
	public boolean liberaRichiesta(int codiceRichiesta){
		return model.liberaRichiesta(codiceRichiesta);
	}

	 
	public boolean addRichiesta(int codiceCantiere,int codiceLavoro,RichiestaMacchina richiesta){
		return model.aggiungiRichiesta( codiceCantiere, codiceLavoro, richiesta);
	}
	

	public ArrayList<Associazione> generateAssociations(){
		return controller.greedy.GreedyEngine.generaAssociazioni(model);
	}

	 
	public void confermaAssociazioniListener(ArrayList<Associazione> data) {
		for(Associazione associazione:data){
			associazione.concretizza();
		}		
	}
	
	 
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
					JOptionPane.showMessageDialog(editLavoro, "Non ������ stato possibile modificare il lavoro. Potrebbero esserci sovrapposizioni con le date di altri lavori.", "Error",JOptionPane.ERROR_MESSAGE);
				}
				System.out.println("CodiceLavoro: "+codiceLavoro);	
		*/
	}
}

