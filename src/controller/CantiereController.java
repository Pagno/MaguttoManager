package controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import model.ModelInterface;
import model.organizer.data.Cantiere;
import model.organizer.data.Priority;
import model.organizer.data.Macchina;
import controller.Interface.AbstractCantieriController;

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
	public boolean aggiungiRichiestaGru() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean soddisfaRichiestaRuspa(Observer observer ) {
		// TODO Auto-generated method stub
		return false;
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

}
