package controller.organizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import view.macchina.ViewCamion;
import view.macchina.ViewEscavatore;
import view.macchina.ViewGru;
import view.macchina.ViewRuspa;
import model.ModelInterface;
import model.organizer.ModelGru;
import model.organizer.data.Gru;
import model.organizer.data.Priorita;


/**
 * 
 * Questa classe permette di gestire gli eventi,
 * legati all'inserimento di nuovi macchinari 
 * e alla modifica di quelli esistenti
 * 
 */
public class ControllerInserimento{// implements AbstractInsertController{

	/**   model. */
	ModelInterface model;

	private static ControllerInserimento istanza;


	/**
	 * Gets   model gru.
	 *
	 * @return   model gru
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
	 * @param modelConnector per manipolare i dati inseriti nell'applicazione
	 * 
	 */
	private ControllerInserimento(ModelInterface modelConnector) {
		model = modelConnector;
	}
	
	//INSERT LISTENER
	/**
	 * Gestisce l'inserimento di una nuova <em>"Gru"</em>.
	 *
	 * @param editGru view da cui leggere i dati della nuova Gru
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
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
	 * Gestisce l'inserimento di una nuova <em>"Ruspa"</em>.
	 *
	 * @param editRuspa view da cui leggere i dati del nuovo Ruspa
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
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

	public boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita ){
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
	//EDIT LISTENER
	/**
	 * Gestisce la modifica di una <em>"Gru"</em>.
	 *
	 *@param editGru view da cui leggere i nuovi dati della Gru
	 *@param codice codice  della Gru da modificare
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
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
	/**
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
	public ActionListener EditRuspaListener( ViewRuspa editRuspa,final int codice) {
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
	}
	
	/**
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
	public ActionListener EditCamionListener( ViewCamion editCamion,final int codice) {
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
	}
	
	/**
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
	public ActionListener EditEscavatoreListener( ViewEscavatore editEscavatore,final int codice) {
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
	}
}
