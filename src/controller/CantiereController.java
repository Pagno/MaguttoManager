package controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import model.ModelInterface;
import model.organizer.data.Cantiere;
import model.organizer.data.Priority;
import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaMacchina;
import controller.Interface.AbstractCantieriController;
import controller.data.Associazione;

public class CantiereController extends AbstractCantieriController{

	/**   model. */
	ModelInterface model;

	/**
	 * Istanzia un nuovo controllore InsertController.
	 *
	 * @param modelConnector per manipolare i dati inseriti nell'applicazione
	 * 
	 */
	public CantiereController(ModelInterface modelConnector) {
		model = modelConnector;
	}
	@Override
	public Cantiere getCantiere(int codiceCantiere) {
		return model.getCantiere(codiceCantiere);
	}

	@Override
	public boolean modificaCantiere(int codiceCantiere, String nomeCantiere,
			String indirizzo, GregorianCalendar dataApertura,
			GregorianCalendar dataChiusura, Priority priorita) {
		
		
		return false;
	}

	@Override
	public boolean aggiungiLavoro(int codiceCantiere, String nomeLavoro,
			GregorianCalendar dataInizio, GregorianCalendar dataFine) {
		model.insertLavoro(nomeLavoro, dataInizio, dataFine, codiceCantiere);
		return true;
	}

	@Override
	public boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina){
		return model.soddisfaRichiesta(codiceRichiesta, codiceMacchina);
	}
	@Override
	public void aggiungiRichiestaObserver(Observer observer ) {
		model.aggiungiLavoroObserver(observer);
	}
	@Override
	public boolean eliminaLavoro(int codiceLavoro) {
		return model.deleteLavoro(codiceLavoro);
	}
	@Override
	public boolean eliminaRichiesta(int codiceRichiesta) {
		return model.deleteRichiesta(codiceRichiesta);
	}
	@Override
	public ArrayList<Macchina> getElencoMacchineDisponibili(int codiceRichiesta) {
		return model.getElencoMacchineDisponibili(codiceRichiesta);
	}
	
	@Override
	public boolean liberaRichiesta(int codiceRichiesta){
		return model.liberaRichiesta(codiceRichiesta);
	}

	@Override
	public boolean addRichiesta(int codiceCantiere,int codiceLavoro,RichiestaMacchina richiesta){
		return model.addRichiesta( codiceCantiere, codiceLavoro, richiesta);
	}
	
	@Override
	public ArrayList<Associazione> generateAssociations(){
		return controller.greedy.GreedyEngine.generateAssociations(model);
	}

	@Override
	public void confermaAssociazioniListener(ArrayList<Associazione> data) {
		for(Associazione associazione:data){
			associazione.concretizza();
		}		
	}
	
	@Override
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
					JOptionPane.showMessageDialog(editLavoro, "Non è stato possibile modificare il lavoro. Potrebbero esserci sovrapposizioni con le date di altri lavori.", "Error",JOptionPane.ERROR_MESSAGE);
				}
				System.out.println("CodiceLavoro: "+codiceLavoro);	
		*/
	}
}

