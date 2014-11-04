package controller.organizer;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import model.ModelInterface;
import model.organizer.ModelMacchina;
import model.organizer.data.Priorita;


/**
 * Questa classe permette di gestire gli eventi,
 * legati all'inserimento di nuovi macchinari 
 * e alla modifica di quelli esistenti.
 * 
 * @author Matteo Pagnoncelli
 * @author Mauro Valota
 */
public class ControllerInserimento{// implements AbstractInsertController{

	/**   Model. */
	ModelInterface model;

	/** L'istanza. */
	private static ControllerInserimento istanza;


	/**
	 * Restituisce l'istanza di ControllerInserimento. Se non era ancora instanziato la crea, implementando il pattern Singleton.
	 *
	 * @param modelConnector Interfaccia per manipolare i dati gestiti dall'applicazione
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
	 * @param modelConnector Interfaccia per manipolare i dati inseriti nell'applicazione
	 * 
	 */
	private ControllerInserimento(ModelInterface modelConnector) {
		model = modelConnector;
	}
	
	//INSERT LISTENER
	/**
	 * Inserisci una nuova <em>"Gru"</em>.
	 *
	 * @param produttore Il produttore della Gru
	 * @param modello Il modello della Gru
	 * @param rotazione La massima rotazione della Gru
	 * @param portata La massima portata della Gru
	 * @param lunghezza La massima lunghezza della Gru
	 * @param altezza La massima altezza della Gru
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
	 * Modifica una gru esistente.
	 *
	 * @param codiceGru Il codice della gru da modificare
	 * @param produttore Il produttore della gru da modificare
	 * @param modello Il modello della gru da modificare
	 * @param rotazione L'angolo di rotazione della gru da modificare
	 * @param portata La portata della gru da modificare
	 * @param lunghezza La lunghezza della gru da modificare
	 * @param altezza L'altezza della gru da modificare
	 * @return <strong>True</strong> se la gru viene correttamente modificata.
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
	 * Inserisce una nuova <em>"Ruspa"</em>.
	 *
	 * @param produttore Il nome produttore della Gru
	 * @param modello Il nome modello della Gru
	 * @param capacita La massima capacita della Gru
	 * @param portata La massima portata della Gru
	 * @param altezza La massima altezza della Gru
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
	 * Modifica la ruspa.
	 *
	 * @param codiceRuspa Il codice della Ruspa da modificare.
	 * @param produttore Il produttore della Ruspa da modificare.
	 * @param modello Il modello della Ruspa da modificare.
	 * @param capacita La capacita della Ruspa da modificare.
	 * @param portata La portata della Ruspa da modificare.
	 * @param altezza L'altezza della Ruspa da modificare.
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
	 * @param produttore Il nome produttore dell'Escavatore
	 * @param modello Il nome modello dell'Escavatore
	 * @param capacita La massima capacita dell'Escavatore
	 * @param portata La massima portata dell'Escavatore
	 * @param altezza La massima altezza dell'Escavatore
	 * @param profondita La massima profondita dell'Escavatore
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
	 * Modifica un escavatore esistente.
	 *
	 * @param codiceEscavatore Il codice dell'escavatore da modificare
	 * @param produttore Il nome produttore da modificare
	 * @param modello Il modello da modificare
	 * @param capacita La capacit&agrave; da modificare
	 * @param portata La portata da modificare
	 * @param altezza L'altezza da modificare
	 * @param profondita La profondit&agrave; da modificare
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
	 * @param produttore Il produttore del camion.
	 * @param modello Il modello del camion.
	 * @param capacita La massima  capacit&agrave; del camion.
	 * @param portata La massima  portata del camion.
	 * @param lunghezza La massima  lunghezza del camion.
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
	 * Modifica il camion esistente.
	 *
	 * @param codiceCamion Il codice del camion da modificare
	 * @param produttore Il nome del produttore da modificare
	 * @param modello Il nome del modello da modificare
	 * @param capacita La capacit&agrave; da modificare
	 * @param portata La portata da modificare
	 * @param lunghezza La lunghezza da modificare
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
	 * Aggiunge un nuovo cantiere.
	 *
	 * @param nomeCantiere Il nome cantiere
	 * @param indirizzo L'indirizzo
	 * @param dataApertura La data di apertura
	 * @param dataChiusura La data di chiusura
	 * @param priorita La priorit&agrave; associata al cantiere
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
	
	/**
	 * Inizializza per effettuare i test
	 */
	public void initForTest(){
		if(istanza!=null){
			ModelMacchina.initCodice();
			istanza=null;
		}
	}
}
