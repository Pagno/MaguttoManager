package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.EditCamion;
import view.EditEscavatore;
import view.EditGru;
import view.EditRuspa;
import model.ModelInterface;


/**
 * 
 * Questa classe permette di gestire gli eventi,
 * legati all'inserimento di nuovi macchinari 
 * e alla modifica di quelli esistenti
 * 
 */
public class InsertController {

	/**   model. */
	ModelInterface model;

	/**
	 * Istanzia un nuovo controllore InsertController.
	 *
	 * @param modelConnector per manipolare i dati inseriti nell'applicazione
	 * 
	 */
	public InsertController(ModelInterface modelConnector) {
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
	public ActionListener insertNewGruListener( EditGru editGru) {
		final EditGru dialog=editGru;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int ang = Integer.parseInt(dialog.getAngoloRotazione());
					int alt = Integer.parseInt(dialog.getAltezza());
					int lung = Integer.parseInt(dialog.getLunghezza());
					int max = Integer.parseInt(dialog.getPortataMassima());
					
					model.aggiungiGru(dialog.getProduttore(),dialog.getModello(),ang,max,lung,alt);
					
					
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
	 * Gestisce l'inserimento di una nuova <em>"Ruspa"</em>.
	 *
	 * @param editRuspa view da cui leggere i dati del nuovo Ruspa
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener insertNewRuspaListener(EditRuspa editRuspa) {
		final EditRuspa dialog=editRuspa;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int capacita = Integer.parseInt(dialog.getCapacita());
					int alt = Integer.parseInt(dialog.getAltezza());
					int portata = Integer.parseInt(dialog.getPortataMassima());

					model.aggiungiRuspa(dialog.getProduttore(),dialog.getModello(),capacita,portata,alt);
					
					
					dialog.dispose();
				} catch (NumberFormatException exc) {
					JOptionPane
							.showMessageDialog(
									null,
									"I campi:\n - Capacita\n - Altezza\n - Portata Massima\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}
	
	/**
	 * Gestisce l'inserimento di un nuovo <em>"Camion"</em>.
	 *
	 * @param editCamion view da cui leggere i dati del nuovo Camion
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener insertNewCamionListener(EditCamion editCamion) {
		final EditCamion dialog=editCamion;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int capacita = Integer.parseInt(dialog.getCapacita());
					int lunghezza = Integer.parseInt(dialog.getLunghezza());
					int portata = Integer.parseInt(dialog.getPortataMassima());

					model.aggiungiCamion(dialog.getProduttore(),dialog.getModello(),capacita,portata,lunghezza);
					
					
					dialog.dispose();
				} catch (NumberFormatException exc) {
					JOptionPane
							.showMessageDialog(
									null,
									"I campi:\n - Capacita\n - Altezza\n - Portata Massima\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}
	
	/**
	 * Gestisce l'inserimento di un nuovo <em>"Escavatore"</em>.
	 *
	 * @param editEscavatore view da cui leggere i dati del nuovo Escavatore
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener insertNewEscavatoreListener(EditEscavatore editEscavatore) {
		final EditEscavatore dialog=editEscavatore;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int capacita = Integer.parseInt(dialog.getCapacita());
					int altezza = Integer.parseInt(dialog.getAltezza());
					int portata = Integer.parseInt(dialog.getPortataMassima());
					int profondita=Integer.parseInt(dialog.getProfondita());

					model.aggiungiEscavatore(dialog.getProduttore(),dialog.getModello(), capacita, portata, altezza,profondita);
					
					
					dialog.dispose();
				} catch (NumberFormatException exc) {
					JOptionPane
							.showMessageDialog(
									null,
									"I campi:\n - Capacita\n - Altezza\n - Portata Massima\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
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
	public ActionListener EditGruListener( EditGru editGru,final int codice) {
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
	}
	
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
	public ActionListener EditRuspaListener( EditRuspa editRuspa,final int codice) {
		final EditRuspa dialog=editRuspa;
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
	public ActionListener EditCamionListener( EditCamion editCamion,final int codice) {
		final EditCamion dialog=editCamion;
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
	public ActionListener EditEscavatoreListener( EditEscavatore editEscavatore,final int codice) {
		final EditEscavatore dialog=editEscavatore;
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
