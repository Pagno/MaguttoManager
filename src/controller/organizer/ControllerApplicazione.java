package controller.organizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JOptionPane;

import view.MainView;
import view.cantiere.ViewCantiere;
import view.lavoro.ViewLavoro;
import view.macchina.ViewCamion;
import view.macchina.ViewEscavatore;
import view.macchina.ViewGru;
import view.macchina.ViewRuspa;
import model.ModelInterface;
import model.organizer.ModelGru;
import model.organizer.data.Gru;

// 
/**
 * 
 * Questa Classe permette di gestire gli eventi,
 * legati all'interazione con le view,
 * provenienti dalla View Principale.
 * 
 * @see MainView
 * 
 */
public class ControllerApplicazione{// implements AbstractApplicationController{

	/**   model. */
	private ModelInterface model;
	
	/**   main view. */
	private static ControllerApplicazione istanza;

	/**
	 * Gets   model gru.
	 *
	 * @return   model gru
	 */
	public static synchronized ControllerApplicazione getControllerApplicazione(ModelInterface modelConnector){
		if(istanza==null){
			istanza=new ControllerApplicazione(modelConnector);
		}
		return istanza;
	}
	/**
	 * Istanzia un nuovo controllore MainController.
	 *
	 * @param modelConnector per manipolare i dati gestiti dall'applicazione
	 * @param view per registrare gli eventi generati dalla view
	 */
	private ControllerApplicazione(ModelInterface modelConnector){
		model = modelConnector;
	}
	public void aggiungiGruObserver(Observer observer){
		model.aggiungiGruObserver(observer);
	}
	public void aggiungiRuspaObserver(Observer observer){
		model.aggiungiRuspaObserver(observer);
	}
	public void aggiungiCamionObserver(Observer observer){
		model.aggiungiCamionObserver(observer);
	}	
	public void aggiungiEscavatoreObserver(Observer observer){
		model.aggiungiEscavatoreObserver(observer);
	}
	public void aggiungiCantiereObserver(Observer observer){
		model.aggiungiCantiereObserver(observer);
	}
	
	//MENU FILE LISTENER
	/**
	 * Gestisce l'evento legato alla pressione del menu <em>"Esci"</em>.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public void exitManager(){
		model.storeData();
	}
	
	/**
	 * Gestisce l'evento legato alla pressione del menu <em>"Salva"</em>.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public void salvaDatiListener(){
		model.storeData();
	}
	
	/**
	 * Gestisce l'evento legato alla pressione del menu <em>"Carica"</em>.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public void caricaDatiListener(){
		model.refreshData();
	}
	
	/**
	 * Gestisce la chiusura dell'applicazione
	 *
	 * @return   window adapter
	 */
	public void chiusuraProgramma(){
		model.storeData();
	}
	
	/**
	 * Visualizza la view per modificare i dati di una Ruspa.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	/*public ActionListener visualizzaModificaRuspaView() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v=mainView.getSelectedData();

				if(v==null){
					JOptionPane.showMessageDialog(null,"Selezionare la riga da modificare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					EditRuspa ins = new EditRuspa(mainView, v);
					InsertController ctr = new InsertController(model);
					ins.setInsertButtonListeners(ctr.EditRuspaListener(ins,(Integer)v[0]));
				}
			}
		};
	}*/
	
	/**
	 * Visualizza la view per modificare i dati di un Camion.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	/*public ActionListener visualizzaModificaCamionView() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v=mainView.getSelectedData();

				if(v==null){
					JOptionPane.showMessageDialog(null,"Selezionare la riga da modificare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					EditCamion ins = new EditCamion(mainView, v);
					InsertController ctr = new InsertController(model);
					ins.setInsertButtonListeners(ctr.EditCamionListener(ins,(Integer)v[0]));
				}
			}
		};
	}
	*/
	/**
	 * Visualizza la view per modificare i dati di un Escavatore.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	/*public ActionListener visualizzaModificaEscavatoreView() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v=mainView.getSelectedData();

				if(v==null){
					JOptionPane.showMessageDialog(null,"Selezionare la riga da modificare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					//EditEscavatore ins = new EditEscavatore(mainView, v);
					//InsertController ctr = new InsertController(model);
					//ins.setInsertButtonListeners(ctr.EditEscavatoreListener(ins,(Integer)v[0]));
				}
			}
		};
	}
	public ActionListener VisualizzaModificaCantiereView() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v=mainView.getSelectedData();

				if(v==null){
					JOptionPane.showMessageDialog(null,"Selezionare la riga da modificare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					EditCantiere ins = new EditCantiere(mainView, v);
					CantieriController ctr = new CantieriController(model);
					ins.setInsertButtonListeners(ctr.EditCantiereListener(ins,(Integer)v[0]));
					
					//Se ci sono macchine associate al cantiere, l'utente non deve poter
					//anticipare la'apertura o posticipare la chiusura per evitare inconguenze
					//con le date delle associazioni
					
					if(model.getAssociazioniList((Integer)v[0]).size()>0){
						ins.setMinimaDataFine(ins.getDataFine());
						ins.setMassimaDataInizio(ins.getDataInizio());
					}
				}
			}
		};
	}*/
	//DELETE
	/**
	 * Elimina la macchina selezionata nella tabella. Se la macchina presenta delle associazioni con
	 * dei cantieri appare un messaggio in cui viene chiesta la conferma all'utente dell'eliminazione
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public boolean eliminaMacchina(Integer codiceMacchina){
		//TODO Mostrare a messaggio il codice dei lavori che sono associati a tale macchina
		//int confirm=JOptionPane.showConfirmDialog(mainView,"Eliminando questa macchina verranno eliminate anche le associazioni con i lavori associati. \n Si desidera continuare con l'eliminazione?","Elimina Macchina", JOptionPane.YES_NO_OPTION);		
		//if(confirm==JOptionPane.OK_OPTION){
			return model.eliminaMacchina(codiceMacchina);
		//}
	}
	
	/**
	 * Elimina il cantiere selezionato nella tabella.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public boolean eliminaCantiere(Integer codiceCantiere){
		return model.eliminaCantiere(codiceCantiere);
	}
	
	/**
	 * Visualizza la view per aggiungere Associazioni ad un cantiere.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 *
	public ActionListener addLavoroView(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] data=mainView.getSelectedData();
				if(data!=null){					
					EditLavoro editLavoro = new EditLavoro(mainView, model.getCantiere((int)data[0]));
					CantieriController ctr = new CantieriController(model);
					editLavoro.addAssociaMacchinaListener(ctr.associaMacchinaView(editLavoro));
					editLavoro.addLiberaRichiestaListener(ctr.rimuoviAssociazioneListener(editLavoro));

					editLavoro.setAddLavoroListeners(ctr.AddLavoroListener(editLavoro));
					editLavoro.setAddRichiestaListeners(ctr.AddRichiestaListener(editLavoro));
					editLavoro.setEditLavoroListeners(ctr.EditLavoroListener(editLavoro));
					editLavoro.setAddCantiereListeners(ctr.EditCantiereListener(editLavoro,(Integer)data[0]));
					editLavoro.addDeleteLavoroListener(ctr.DeleteLavoroListener(editLavoro));
					editLavoro.addDeleteRichiestaListener(ctr.DeleteRichiestaListener(editLavoro));
					
				}
				else{
					JOptionPane.showMessageDialog(null,"Selezionare il cantiere cui aggiungere delle associazioni.","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
	}*/

}
