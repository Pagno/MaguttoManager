package controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import view.MainView;
import model.ModelInterface;
import model.organizer.ModelGru;
import model.organizer.data.Cantiere;
import model.organizer.data.Gru;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
import model.organizer.data.RichiestaMacchina;
import controller.data.Associazione;
import controller.organizer.*;


public class ControllerConnector implements ControllerInterface{
	private ControllerApplicazione appCtrl;
	private ControllerInserimento insCtrl;
	private ControllerCantiere canCtrl;
	
	private static ControllerConnector istanza;


	/**
	 * Gets   model gru.
	 *
	 * @return   model gru
	 */
	public static synchronized ControllerConnector getControllerConnector(ModelInterface modelInterface){
		if(istanza==null){
			istanza=new ControllerConnector(modelInterface);
		}
		return istanza;
	}
	
	private ControllerConnector(ModelInterface modelConnector) {
		appCtrl=ControllerApplicazione.getControllerApplicazione(modelConnector);
		insCtrl=ControllerInserimento.getControllerInserimento(modelConnector);
		canCtrl=ControllerCantiere.getControllerCantiere(modelConnector);
	}
	

	public void aggiungiGruObserver(Observer observer){
		appCtrl.aggiungiGruObserver(observer);
	}
	public void aggiungiRuspaObserver(Observer observer){
		appCtrl.aggiungiRuspaObserver(observer);
	}
	public void aggiungiCamionObserver(Observer observer){
		appCtrl.aggiungiCamionObserver(observer);
	}	
	public void aggiungiEscavatoreObserver(Observer observer){
		appCtrl.aggiungiEscavatoreObserver(observer);
	}
	public void aggiungiCantiereObserver(Observer observer){
		appCtrl.aggiungiCantiereObserver(observer);
	}
	
	
	@Override
	public boolean eliminaMacchina(Integer codiceMacchina) {
		return appCtrl.eliminaMacchina(codiceMacchina);
	}

	@Override
	public boolean eliminaCantiere(Integer codiceCantiere) {
		return appCtrl.eliminaCantiere(codiceCantiere);
	}

	@Override
	public void exitManager() {
		appCtrl.exitManager();
	}

	@Override
	public void caricaDatiListener() {
		appCtrl.caricaDatiListener();
	}

	@Override
	public void salvaDatiListener() {
		appCtrl.salvaDatiListener();
	}

	@Override
	public void chiusuraProgramma() {
		appCtrl.chiusuraProgramma();
	}

	@Override
	public Cantiere getCantiere(int codiceCantiere) {
		return canCtrl.getCantiere(codiceCantiere);
	}

	@Override
	public boolean modificaCantiere(int codiceCantiere, String nomeCantiere,
			String indirizzo, GregorianCalendar dataApertura,
			GregorianCalendar dataChiusura, Priorita priorita) {
		
		return canCtrl.modificaCantiere(codiceCantiere, nomeCantiere, indirizzo, dataApertura, dataChiusura, priorita);
	}

	@Override
	public boolean aggiungiLavoro(int codiceCantiere, String nomeLavoro,
			GregorianCalendar dataInizio, GregorianCalendar dataFine) {
		// TODO Auto-generated method stub
		return canCtrl.aggiungiLavoro(codiceCantiere, nomeLavoro, dataInizio, dataFine);
	}

	@Override
	public boolean eliminaLavoro(int codiceLavoro) {
		return canCtrl.eliminaLavoro(codiceLavoro);
	}

	@Override
	public boolean eliminaRichiesta(int codiceRichiesta) {
		return canCtrl.eliminaRichiesta(codiceRichiesta);
	}

	@Override
	public ArrayList<Macchina> getElencoMacchineDisponibili(int codiceRichiesta) {
		return canCtrl.getElencoMacchineDisponibili(codiceRichiesta);
	}

	@Override
	public boolean liberaRichiesta(int codiceRichiesta) {
		return canCtrl.liberaRichiesta(codiceRichiesta);
	}

	@Override
	public boolean aggiungiRichiesta(int codiceCantiere, int codiceLavoro,
			RichiestaMacchina richiesta) {

		return canCtrl.aggiungiRichiesta(codiceCantiere, codiceLavoro, richiesta);
	}

	@Override
	public boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina) {
		return canCtrl.soddisfaRichiesta(codiceRichiesta, codiceMacchina);
	}

	@Override
	public ArrayList<Associazione> generateAssociations() {
		return canCtrl.generaAssociazioni();
	}

	@Override
	public void confermaAssociazioniListener(ArrayList<Associazione> data) {
		canCtrl.confermaAssociazioniListener(data);
	}

	@Override
	public void modificaLavoro(int codiceLavoro, String nome,
			GregorianCalendar inizio, GregorianCalendar fine) {
		canCtrl.modificaLavoro(codiceLavoro, nome, inizio, fine);
		
	}

	@Override
	public boolean aggiungiGru(String produttore, String modello,
			int rotazione, int portata, int lunghezza, int altezza) {
		
		return insCtrl.aggiungiGru(produttore, modello, rotazione, portata, lunghezza, altezza);
	}

	@Override
	public boolean aggiungiRuspa(String produttore, String modello,
			int capacita, int portata, int altezza) {
		
		return insCtrl.aggiungiRuspa(produttore, modello, capacita, portata, altezza);
	}

	@Override
	public boolean aggiungiEscavatore(String produttore, String modello,
			int capacita, int portata, int altezza, int profondita) {
		return insCtrl.aggiungiEscavatore(produttore, modello, capacita, portata, altezza, profondita);
	}

	@Override
	public boolean aggiungiCamion(String produttore, String modello,
			int capacita, int portata, int lunghezza) {

		return insCtrl.aggiungiCamion(produttore, modello, capacita, portata, lunghezza);
	}

	@Override
	public boolean aggiungiCantiere(String nomeCantiere,
			String indirizzo, GregorianCalendar dataApertura,
			GregorianCalendar dataChiusura, Priorita priorita) {

		return insCtrl.aggiungiCantiere(nomeCantiere, indirizzo, dataApertura, dataChiusura, priorita);
	}

	@Override
	public boolean modificaCamion(int codiceCamion, String produttore,
			String modello, int capacita, int portata, int lunghezza) {
		return insCtrl.modificaCamion(codiceCamion, produttore, modello, capacita, portata, lunghezza);
	}

	@Override
	public boolean modificaGru(int codiceGru, String produttore,
			String modello, int rotazione, int portata, int lunghezza,
			int altezza) {

		return insCtrl.modificaGru(codiceGru, produttore, modello, rotazione, portata, lunghezza, altezza);
	}

	@Override
	public boolean modificaRuspa(int codiceRuspa, String produttore,
			String modello, int capacita, int portata, int altezza) {
		
		return insCtrl.modificaRuspa(codiceRuspa, produttore, modello, capacita, portata, altezza);
	}

	@Override
	public boolean modificaEscavatore(int codiceEscavatore, String produttore,
			String modello, int capacita, int portata, int altezza,
			int profondita) {
		return insCtrl.modificaEscavatore(codiceEscavatore, produttore, modello, capacita, portata, altezza, profondita);
	}

}
