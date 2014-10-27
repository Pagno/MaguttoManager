package controller.organizer;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import model.ModelInterface;
import model.organizer.ModelMacchina;
import model.organizer.data.Priorita;


// TODO: Auto-generated Javadoc
/**
 * Questa classe permette di gestire gli eventi,
 * legati all'inserimento di nuovi macchinari 
 * e alla modifica di quelli esistenti.
 */
public class ControllerInserimento{// implements AbstractInsertController{

	/**   model. */
	ModelInterface model;

	/** The istanza. */
	private static ControllerInserimento istanza;


	/**
	 * Ritorna l'istanza di ControllerInserimento. Se non era ancora instanziato la crea.
	 *
	 * @param ModelInterface per manipolare i dati gestiti dall'applicazione
	 * @return  Istanza di ControllerInserimento
	 */
	public static synchronized ControllerInserimento getControllerInserimento(ModelInterface modelConnector){
		if(istanza==null){
			istanza=new ControllerInserimento(modelConnector);
		}
		return istanza;
	}
	/**
	 * Istanzia un nuovo controllore InsertController.
	 *
	 * @param ModelInterface per manipolare i dati inseriti nell'applicazione
	 * 
	 */
	private ControllerInserimento(ModelInterface modelConnector) {
		model = modelConnector;
	}
	
	//INSERT LISTENER
	/**
	 * Inserisci una nuova <em>"Gru"</em>.
	 *
	 * @param produttore Produttore della Gru
	 * @param modello Modello della Gru
	 * @param rotazione Massima rotazione della Gru
	 * @param portata Massima portata della Gru
	 * @param lunghezza Massima lunghezza della Gru
	 * @param altezza Massima altezza della Gru
	 * @return <strong>True</strong> se la gru viene correttamente inserita nel parco Macchine
	 */
	public boolean aggiungiGru(String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza){
		if(produttore=="" || modello==""){
			JOptionPane
			.showMessageDialog(
					null,
					"I campi:\n - Produttore\n - Modello\ndevono contenere numeri. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		model.aggiungiGru(produttore,modello,rotazione,portata,lunghezza,altezza);
		return true;
	}
	
	
	/**
	 * Modifica gru esistente.
	 *
	 * @param codiceGru Codice della gru da modificare
	 * @param produttore Produttore della gru da modificare
	 * @param modello Modello della gru da modificare
	 * @param rotazione Rotazione della gru da modificare
	 * @param portata Portata della gru da modificare
	 * @param lunghezza Lunghezza della gru da modificare
	 * @param altezza Altezza della gru da modificare
	 * @return <strong>True</strong> se la gru viene correttamente modifica.
	 */
	public boolean modificaGru(int codiceGru,String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza) {
		if(produttore=="" || modello==""){
			JOptionPane
			.showMessageDialog(
					null,
					"I campi:\n - Produttore\n - Modello\ndevono contenere numeri. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		model.modificaGru(codiceGru,produttore,modello,rotazione,portata,lunghezza,altezza);
		return true;
	}
	
	/**
	 * Inserisci una nuova <em>"Ruspa"</em>.
	 *
	 * @param produttore Nome produttore della Gru
	 * @param modello Nome modello della Gru
	 * @param capacita Massima capacita della Gru
	 * @param portata Massima portata della Gru
	 * @param altezza Massima altezza della Gru
	 * @return <strong>True</strong> se la Ruspa viene correttamente inserita nel parco Macchine
	 *
	 */
	public boolean aggiungiRuspa(String produttore, String modello,int capacita,int portata,int altezza) {
		if(produttore=="" || modello==""){
			JOptionPane
			.showMessageDialog(
					null,
					"I campi:\n - Produttore\n - Modello\ndevono contenere numeri. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		model.aggiungiRuspa(produttore,modello,capacita,portata,altezza);
		return true;
	}
	
	/**
	 * Modifica ruspa.
	 *
	 * @param codiceRuspa Codice della Ruspa da modificare.
	 * @param produttore Produttore della Ruspa da modificare.
	 * @param modello Modello della Ruspa da modificare.
	 * @param capacita Capacita della Ruspa da modificare.
	 * @param portata Portata della Ruspa da modificare.
	 * @param altezza Altezza della Ruspa da modificare.
	 * 
	 * @return <strong>True</strong> se la gru viene correttamente modificata.
	 */
	public boolean modificaRuspa(int codiceRuspa,String produttore, String modello,int capacita,int portata,int altezza) {
		if(produttore=="" || modello==""){
			JOptionPane
			.showMessageDialog(
					null,
					"I campi:\n - Produttore\n - Modello\ndevono contenere numeri. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		model.modificaRuspa(codiceRuspa,produttore,modello,capacita,portata,altezza);
		return true;
	}
	
	
	/**
	 * Inserisci un nuovo <em>"Escavatore"</em>.
	 *
	 * @param produttore Nome produttore dell'Escavatore
	 * @param modello Nome modello dell'Escavatore
	 * @param capacita Massima capacita dell'Escavatore
	 * @param portata Massima portata dell'Escavatore
	 * @param altezza Massima altezza dell'Escavatore
	 * @param profondita Massima profondita dell'Escavatore
	 * @return <strong>True</strong> se l'Escavatore viene correttamente inserito nel parco Macchine
	 */
	public boolean aggiungiEscavatore(String produttore, String modello,int capacita,int portata,int altezza,int profondita){
		if(produttore=="" || modello==""){
			JOptionPane
			.showMessageDialog(
					null,
					"I campi:\n - Produttore\n - Modello\ndevono contenere numeri. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		model.aggiungiEscavatore(produttore, modello, capacita, portata, altezza, profondita);
		return true;
	}
	
	/**
	 * Modifica escavatore esistente.
	 *
	 * @param codiceEscavatore Codice escavatore da modificare
	 * @param produttore Nome produttore da modificare
	 * @param modello Modello da modificare
	 * @param capacita Capacita da modificare
	 * @param portata Portata da modificare
	 * @param altezza Altezza da modificare
	 * @param profondita Profondita da modificare
	 * 
	 * @return <strong>True</strong> se l'escavatore viene correttamente modificato.
	 */
	public boolean modificaEscavatore(int codiceEscavatore,String produttore, String modello,int capacita,int portata,int altezza,int profondita){
		if(produttore=="" || modello==""){
			JOptionPane
			.showMessageDialog(
					null,
					"I campi:\n - Produttore\n - Modello\ndevono contenere numeri. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		model.modificaEscavatore(codiceEscavatore,produttore, modello, capacita, portata, altezza, profondita);
		return true;
	}
	
	
	
	/**
	 * Inserisci un nuovo <em>"Camion"</em>.
	 *
	 * @param produttore Produttore del camion.
	 * @param modello Modello del camion.
	 * @param capacita Massima  capacita del camion.
	 * @param portata Massima  portata del camion.
	 * @param lunghezza Massima  lunghezza del camion.
	 * @return <strong>True</strong> se il Camion viene correttamente inserito nel parco Macchine
	 */
	public boolean aggiungiCamion(String produttore,String modello,int capacita,int portata,int lunghezza){
		if(produttore=="" || modello==""){
			JOptionPane
			.showMessageDialog(
					null,
					"I campi:\n - Produttore\n - Modello\ndevono contenere numeri. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		model.aggiungiCamion(produttore, modello, capacita, portata, lunghezza);
		return true;
	}
	
	/**
	 * Modifica camion esistente.
	 *
	 * @param codiceCamion Codice camion da modificare
	 * @param produttore Nome produttore da modificare
	 * @param modello Nome modello da modificare
	 * @param capacita Capacita da modificare
	 * @param portata Portata da modificare
	 * @param lunghezza Lunghezza da modificare
	 * @return <strong>True</strong> se il camion viene correttamente modificato.
	 */
	public boolean modificaCamion(int codiceCamion,String produttore,String modello,int capacita,int portata,int lunghezza){
		if(produttore=="" || modello==""){
			JOptionPane
			.showMessageDialog(
					null,
					"I campi:\n - Produttore\n - Modello\ndevono contenere numeri. ",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		model.modificaCamion(codiceCamion,produttore, modello, capacita, portata, lunghezza);
		return true;
	}
	
	
	

	/**
	 * Aggiungi nuovo cantiere.
	 *
	 * @param nomeCantiere Nome cantiere
	 * @param indirizzo Indirizzo
	 * @param dataApertura Data apertura
	 * @param dataChiusura Data chiusura
	 * @param priorita Priorita associata al cantiere
	* @return <strong>True</strong> se il Cantiere viene correttamente inserito.
	 */
	public boolean aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita ){
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
		model.aggiungiCantiere(nomeCantiere, indirizzo, dataApertura, dataChiusura, priorita);
		return true;
	}

	/*public boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita ){
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
	}*/
	//EDIT LISTENER
	/*
	 * Gestisce la modifica di una <em>"Gru"</em>.
	 *
	 * @return istanza classe ActionListener
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 */
	/*public ActionListener EditGruListener( EditGru editGru,final int codice) {
		final EditGru dialog=editGru;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int ang = Integer.parseInt(dialog.getAngoloRotazione());
					int alt = Integer.parseInt(dialog.getAltezza());
					int lung = Integer.parseInt(dialog.getLunghezza());
					int max = Integer.parseInt(dialog.getPortataMassima());

					model.modificaGru(codice,dialog.getProduttore(),dialog.getModello(),ang,max,lung,alt);
					
					
					dialog.dispose();
				} catch (NumberFormatException exc) {
					JOptionPane
							.showMessageDialog(
									null,
									"I campi:\n - Lunghezza\n - Altezza\n - Portata Massima\n - Angolo Rotazione\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}*/
	/*
	 * Gestisce la modifica di una <em>"Ruspa"</em>.
	 *
	 * @param editRuspa view da cui leggere i nuovi dati della Ruspa
	 * @param codice codice  della Ruspa da modificare
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	/*public ActionListener ModificaRuspaListener( ViewRuspa editRuspa,final int codice) {
		final ViewRuspa dialog=editRuspa;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int capacita = Integer.parseInt(dialog.getCapacita());
					int alt = Integer.parseInt(dialog.getAltezza());
					int portata = Integer.parseInt(dialog.getPortataMassima());

					model.modificaRuspa(codice,dialog.getProduttore(),dialog.getModello(),capacita,portata,alt);
					
					
					dialog.dispose();
				} catch (NumberFormatException exc) {
					JOptionPane
							.showMessageDialog(
									null,
									"I campi:\n - Lunghezza\n - Altezza\n - Portata Massima\n - Angolo Rotazione\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}*/
	
	/*
	 * Gestisce la modifica di un <em>"Camion"</em>.
	 *
	 * @param editCamion view da cui leggere i nuovi dati del Camion
	 * @param codice codice  del Camion da modificare
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	/*public ActionListener ModificaCamionListener( ViewCamion editCamion,final int codice) {
		final ViewCamion dialog=editCamion;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int cap= Integer.parseInt(dialog.getCapacita());
					int lung = Integer.parseInt(dialog.getLunghezza());
					int max = Integer.parseInt(dialog.getPortataMassima());

					model.modificaCamion(codice,dialog.getProduttore(),dialog.getModello(),cap,max,lung);
					
					
					dialog.dispose();
				} catch (NumberFormatException exc) {
					JOptionPane
							.showMessageDialog(
									null,
									"I campi:\n - Lunghezza\n - Altezza\n - Portata Massima\n - Angolo Rotazione\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}*/
	
	/*
	 * Gestisce la modifica di un <em>"Escavatore"</em>.
	 *
	 * @param editEscavatore view da cui leggere i nuovi dati dell'escavatore
	 * @param codice codice  dell'escavatore da modificare
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	/*public ActionListener ModificaEscavatoreListener( ViewEscavatore editEscavatore,final int codice) {
		final ViewEscavatore dialog=editEscavatore;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int capacita= Integer.parseInt(dialog.getCapacita());
					int altezza = Integer.parseInt(dialog.getAltezza());
					int portata= Integer.parseInt(dialog.getPortataMassima());
					int profondita =Integer.parseInt(dialog.getProfondita());
					model.modificaEscavatore(codice, dialog.getProduttore(), dialog.getModello(), capacita, portata, altezza, profondita);
					
					
					dialog.dispose();
				} catch (NumberFormatException exc) {
					JOptionPane
							.showMessageDialog(
									null,
									"I campi:\n - Lunghezza\n - Altezza\n - Portata Massima\n - Angolo Rotazione\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}*/
	public void initForTest(){
		if(istanza!=null){
			ModelMacchina.initCodice();
			istanza=null;
		}
	}
}
