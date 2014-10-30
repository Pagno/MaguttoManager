package controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import model.ModelInterface;
import model.organizer.data.Cantiere;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
import model.organizer.data.RichiestaMacchina;
import controller.greedy.data.Associazione;
import controller.organizer.*;



/**
 * The Class ControllerConnector.
 */
public class ControllerConnector implements ControllerInterface{
	
	/** The app ctrl. */
	private ControllerApplicazione appCtrl;
	
	/** The ins ctrl. */
	private ControllerInserimento insCtrl;
	
	/** The can ctrl. */
	private ControllerCantiere canCtrl;
	
	/** The istanza. */
	private static ControllerConnector istanza;


	/**
	 * Gets   model gru.
	 *
	 * @param modelInterface the model interface
	 * @return   model gru
	 */
	public static synchronized ControllerConnector getControllerConnector(ModelInterface modelInterface){
		if(istanza==null){
			istanza=new ControllerConnector(modelInterface);
		}
		return istanza;
	}
	
	/**
	 * Instantiates a new controller connector.
	 *
	 * @param modelConnector the model connector
	 */
	private ControllerConnector(ModelInterface modelConnector) {
		appCtrl=ControllerApplicazione.getControllerApplicazione(modelConnector);
		insCtrl=ControllerInserimento.getControllerInserimento(modelConnector);
		canCtrl=ControllerCantiere.getControllerCantiere(modelConnector);
	}
	

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiGruObserver(java.util.Observer)
	 */
	public void aggiungiGruObserver(Observer observer){
		appCtrl.aggiungiGruObserver(observer);
	}
	
	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiRuspaObserver(java.util.Observer)
	 */
	public void aggiungiRuspaObserver(Observer observer){
		appCtrl.aggiungiRuspaObserver(observer);
	}
	
	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiCamionObserver(java.util.Observer)
	 */
	public void aggiungiCamionObserver(Observer observer){
		appCtrl.aggiungiCamionObserver(observer);
	}	
	
	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiEscavatoreObserver(java.util.Observer)
	 */
	public void aggiungiEscavatoreObserver(Observer observer){
		appCtrl.aggiungiEscavatoreObserver(observer);
	}
	
	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiCantiereObserver(java.util.Observer)
	 */
	public void aggiungiCantiereObserver(Observer observer){
		appCtrl.aggiungiCantiereObserver(observer);
	}
	
	
	/* (non-Javadoc)
	 * @see controller.ControllerInterface#eliminaMacchina(java.lang.Integer)
	 */
	@Override
	public boolean eliminaMacchina(Integer codiceMacchina) {
		return appCtrl.eliminaMacchina(codiceMacchina);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#eliminaCantiere(java.lang.Integer)
	 */
	@Override
	public boolean eliminaCantiere(Integer codiceCantiere) {
		return appCtrl.eliminaCantiere(codiceCantiere);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#exitManager()
	 */
	@Override
	public void exitManager() {
		appCtrl.exitManager();
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#caricaDatiListener()
	 */
	@Override
	public void caricaDatiListener() {
		appCtrl.caricaDatiListener();
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#salvaDatiListener()
	 */
	@Override
	public void salvaDatiListener() {
		appCtrl.salvaDatiListener();
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#chiusuraProgramma()
	 */
	@Override
	public void chiusuraProgramma() {
		appCtrl.chiusuraProgramma();
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#getCantiere(int)
	 */
	@Override
	public Cantiere getCantiere(int codiceCantiere) {
		return canCtrl.getCantiere(codiceCantiere);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#modificaCantiere(int, java.lang.String, java.lang.String, java.util.GregorianCalendar, java.util.GregorianCalendar, model.organizer.data.Priorita)
	 */
	@Override
	public boolean modificaCantiere(int codiceCantiere, String nomeCantiere,
			String indirizzo, GregorianCalendar dataApertura,
			GregorianCalendar dataChiusura, Priorita priorita) {
		
		return canCtrl.modificaCantiere(codiceCantiere, nomeCantiere, indirizzo, dataApertura, dataChiusura, priorita);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiLavoro(int, java.lang.String, java.util.GregorianCalendar, java.util.GregorianCalendar)
	 */
	@Override
	public boolean aggiungiLavoro(int codiceCantiere, String nomeLavoro,
			GregorianCalendar dataInizio, GregorianCalendar dataFine) {
		return canCtrl.aggiungiLavoro(codiceCantiere, nomeLavoro, dataInizio, dataFine);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#eliminaLavoro(int)
	 */
	@Override
	public boolean eliminaLavoro(int codiceLavoro) {
		return canCtrl.eliminaLavoro(codiceLavoro);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#eliminaRichiesta(int)
	 */
	@Override
	public boolean eliminaRichiesta(int codiceRichiesta) {
		return canCtrl.eliminaRichiesta(codiceRichiesta);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#getElencoMacchineDisponibili(int)
	 */
	@Override
	public ArrayList<Macchina> getElencoMacchineDisponibili(int codiceRichiesta) {
		return canCtrl.getElencoMacchineDisponibili(codiceRichiesta);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#liberaRichiesta(int)
	 */
	@Override
	public boolean liberaRichiesta(int codiceRichiesta) {
		return canCtrl.liberaRichiesta(codiceRichiesta);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiRichiesta(int, int, model.organizer.data.RichiestaMacchina)
	 */
	@Override
	public boolean aggiungiRichiesta(int codiceCantiere, int codiceLavoro,
			RichiestaMacchina richiesta) {

		return canCtrl.aggiungiRichiesta(codiceCantiere, codiceLavoro, richiesta);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#soddisfaRichiesta(int, int)
	 */
	@Override
	public boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina) {
		return canCtrl.soddisfaRichiesta(codiceRichiesta, codiceMacchina);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#generaAssociazioni()
	 */
	@Override
	public ArrayList<Associazione> generaAssociazioni() {
		return canCtrl.generaAssociazioni();
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#confermaAssociazioniListener(java.util.ArrayList)
	 */
	@Override
	public void confermaAssociazioniListener(ArrayList<Associazione> data) {
		canCtrl.confermaAssociazioniListener(data);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#modificaLavoro(int, java.lang.String, java.util.GregorianCalendar, java.util.GregorianCalendar)
	 */
	@Override
	public boolean modificaLavoro(int codiceCantiere,int codiceLavoro, String nome,
			GregorianCalendar inizio, GregorianCalendar fine) {
		return canCtrl.modificaLavoro(codiceCantiere,codiceLavoro, nome, inizio, fine);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiGru(java.lang.String, java.lang.String, int, int, int, int)
	 */
	@Override
	public boolean aggiungiGru(String produttore, String modello,
			int rotazione, int portata, int lunghezza, int altezza) {
		
		return insCtrl.aggiungiGru(produttore, modello, rotazione, portata, lunghezza, altezza);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiRuspa(java.lang.String, java.lang.String, int, int, int)
	 */
	@Override
	public boolean aggiungiRuspa(String produttore, String modello,
			int capacita, int portata, int altezza) {
		
		return insCtrl.aggiungiRuspa(produttore, modello, capacita, portata, altezza);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiEscavatore(java.lang.String, java.lang.String, int, int, int, int)
	 */
	@Override
	public boolean aggiungiEscavatore(String produttore, String modello,
			int capacita, int portata, int altezza, int profondita) {
		return insCtrl.aggiungiEscavatore(produttore, modello, capacita, portata, altezza, profondita);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiCamion(java.lang.String, java.lang.String, int, int, int)
	 */
	@Override
	public boolean aggiungiCamion(String produttore, String modello,
			int capacita, int portata, int lunghezza) {

		return insCtrl.aggiungiCamion(produttore, modello, capacita, portata, lunghezza);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#aggiungiCantiere(java.lang.String, java.lang.String, java.util.GregorianCalendar, java.util.GregorianCalendar, model.organizer.data.Priorita)
	 */
	@Override
	public boolean aggiungiCantiere(String nomeCantiere,
			String indirizzo, GregorianCalendar dataApertura,
			GregorianCalendar dataChiusura, Priorita priorita) {

		return insCtrl.aggiungiCantiere(nomeCantiere, indirizzo, dataApertura, dataChiusura, priorita);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#modificaCamion(int, java.lang.String, java.lang.String, int, int, int)
	 */
	@Override
	public boolean modificaCamion(int codiceCamion, String produttore,
			String modello, int capacita, int portata, int lunghezza) {
		return insCtrl.modificaCamion(codiceCamion, produttore, modello, capacita, portata, lunghezza);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#modificaGru(int, java.lang.String, java.lang.String, int, int, int, int)
	 */
	@Override
	public boolean modificaGru(int codiceGru, String produttore,
			String modello, int rotazione, int portata, int lunghezza,
			int altezza) {

		return insCtrl.modificaGru(codiceGru, produttore, modello, rotazione, portata, lunghezza, altezza);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#modificaRuspa(int, java.lang.String, java.lang.String, int, int, int)
	 */
	@Override
	public boolean modificaRuspa(int codiceRuspa, String produttore,
			String modello, int capacita, int portata, int altezza) {
		
		return insCtrl.modificaRuspa(codiceRuspa, produttore, modello, capacita, portata, altezza);
	}

	/* (non-Javadoc)
	 * @see controller.ControllerInterface#modificaEscavatore(int, java.lang.String, java.lang.String, int, int, int, int)
	 */
	@Override
	public boolean modificaEscavatore(int codiceEscavatore, String produttore,
			String modello, int capacita, int portata, int altezza,
			int profondita) {
		return insCtrl.modificaEscavatore(codiceEscavatore, produttore, modello, capacita, portata, altezza, profondita);
	}



}
