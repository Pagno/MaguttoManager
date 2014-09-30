package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

import view.EditCamion;
import view.InsertCantiere;
import view.EditEscavatore;
import view.EditGru;
import view.EditRuspa;
import view.MainView;
import view.lavoro.EditLavoro;
import model.ModelInterface;

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
public class ApplicationController{

	/**   model. */
	private ModelInterface model;
	
	/**   main view. */
	private MainView mainView;

	/**
	 * Istanzia un nuovo controllore MainController.
	 *
	 * @param modelConnector per manipolare i dati gestiti dall'applicazione
	 * @param view per registrare gli eventi generati dalla view
	 */
	public ApplicationController(ModelInterface modelConnector,MainView view) {
		model = modelConnector;
		mainView = view;
		setObserver();
		model.refreshData();
		mainView.setVisible(true);
		model.pubblicaContenuto();
		//INSERT LISTENER
		mainView.addAggiungiRuspaListener(visualizzaInserimentoRuspa());
		mainView.addAggiungiGruListener(visualizzaInserimentoGru());
		mainView.addAggiungiCamionListener(visualizzaInserimentoCamion());
		mainView.addAggiungiEscavatoreListener(visualizzaInserimentoEscavatore());
		mainView.addAggiungiCantiereListener(visualizzaInserimentoCantiere());
		mainView.addBtnAddLavoroListener(addLavoroView());
		//TABLE LISTENER
		mainView.addButtonGruListener(visualizzaElencoGru());
		mainView.addButtonRuspaListener(visualizzaElencoRuspe());
		mainView.addButtonCamionListener(visualizzaElencoCamion());
		mainView.addButtonEscavatoreListener(visualizzaElencoEscavatore());
		mainView.addButtonCantiereListener(visualizzaElencoCantieri());
		
		//EDIT LISTENER	
		mainView.addModificaListener(visualizzaModificaGruView());
		
		//DELETE LISTENER
		mainView.addEliminaListener(eliminaMacchina());	
		
		//MENU FILE LISTENER
		mainView.addBtnEsciListener(exitManager());
		mainView.addBtnCaricaListener(caricaDatiListener());
		mainView.addBtnSalvaListener(salvaDatiListener());
		mainView.addWindowClosingListener(chiusuraProgramma());
		
		mainView.addGreedyEngineListener(addGreedyEngineView());
		

	}
	
	/**
	 * Imposta le classi observer della view.
	 */
	private void setObserver(){
		model.addGruObserver(mainView.dataModelGru);
		model.addRuspaObserver(mainView.dataModelRuspa);
		model.addCamionObserver(mainView.dataModelCamion);	
		model.addEscavatoreObserver(mainView.dataModelEscavatore);	
		model.addCantiereObserver(mainView.dataModelCantiere);		
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
	public ActionListener exitManager(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.storeData();
				mainView.dispose();
			}
		};
	}
	
	/**
	 * Gestisce l'evento legato alla pressione del menu <em>"Salva"</em>.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener salvaDatiListener(){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				model.storeData();
			}
			
		};
	}
	
	/**
	 * Gestisce l'evento legato alla pressione del menu <em>"Carica"</em>.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener caricaDatiListener(){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.dataModelCamion.resetData();
				mainView.dataModelRuspa.resetData();
				mainView.dataModelEscavatore.resetData();
				mainView.dataModelGru.resetData();
				mainView.dataModelCantiere.resetData();
				model.refreshData();
			}
			
		};
	}
	
	/**
	 * Gestisce la chiusura dell'applicazione
	 *
	 * @return   window adapter
	 */
	public WindowAdapter chiusuraProgramma(){
		return new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				model.storeData();
			}
		};
	}
	
	//BTN VIEW LISTENER
	/**
	 * Visualizza la view per iniserire una nuova Gru.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaInserimentoGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditGru ins = new EditGru(mainView);
				InsertController ctr = new InsertController(model);
				ins.setInsertButtonListeners(ctr.insertNewGruListener(ins));
			}
		};
	}
	
	/**
	 * Visualizza la view per iniserire una nuova Ruspa.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaInserimentoRuspa() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditRuspa ins = new EditRuspa(mainView);
				InsertController ctr = new InsertController(model);
				ins.setInsertButtonListeners(ctr.insertNewRuspaListener(ins));
			}
		};
	}
	
	/**
	 * Visualizza la view per iniserire un nuovo Camion.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaInserimentoCamion() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditCamion ins = new EditCamion(mainView);
				InsertController ctr = new InsertController(model);
				ins.setInsertButtonListeners(ctr.insertNewCamionListener(ins));
			}
		};
	}
	
	/**
	 * Visualizza la view per iniserire un nuovo Escavatore.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaInserimentoEscavatore() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditEscavatore ins = new EditEscavatore(mainView);
				InsertController ctr = new InsertController(model);
				ins.setInsertButtonListeners(ctr.insertNewEscavatoreListener(ins));
			}
		};
	}
	
	/**
	 * Visualizza la view per iniserire un nuovo Cantiere.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaInserimentoCantiere() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InsertCantiere ins = new InsertCantiere(mainView);
				CantieriController ctr = new CantieriController(model);
				//ins.setInsertAddAssociazioneListeners(ctr.OpenViewAddAssociazioniListener());
				ins.setInsertButtonListeners(ctr.InsertNuovoCantiereListener(ins));
				//ins.setDataInizioChangedListener(ctr.setDataInizioChangedListener(ins));
			}
		};
	}
	
	//VISUALIZZA ELENCO IN TABELLA
	/**
	 * Visualizza l'elenco delle Gru inserite.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaElencoGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showGruData();
				mainView.disableBtnModifica(false);
				mainView.addModificaListener(visualizzaModificaGruView());
				mainView.addEliminaListener(eliminaMacchina());
				mainView.setVisibleBtnAssociazioni(false);
			}

		};
	}
	
	/**
	 * Visualizza l'elenco delle Ruspe inserite.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaElencoRuspe() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showRuspaData();
				mainView.disableBtnModifica(false);
				mainView.addModificaListener(visualizzaModificaRuspaView());
				mainView.addEliminaListener(eliminaMacchina());
				mainView.setVisibleBtnAssociazioni(false);
			}
		};
	}
	
	/**
	 * Visualizza l'elenco dei Csmion inseriti.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaElencoCamion() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showCamionData();
				mainView.disableBtnModifica(false);
				mainView.addModificaListener(visualizzaModificaCamionView());
				mainView.addEliminaListener(eliminaMacchina());
				mainView.setVisibleBtnAssociazioni(false);
			}
		};
	}
	
	/**
	 * Visualizza l'elenco degli Escavatori inseriti.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaElencoEscavatore() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showEscavatoreData();
				mainView.disableBtnModifica(false);
				mainView.addModificaListener(visualizzaModificaEscavatoreView());
				mainView.addEliminaListener(eliminaMacchina());
				mainView.setVisibleBtnAssociazioni(false);
			}
		};
	}
	
	/**
	 * Visualizza l'elenco dei Cantieri inseriti.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaElencoCantieri() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showCantiereData();
				//mainView.disableBtnModifica(true);
				mainView.addEliminaListener(eliminaCantiere());
				mainView.addModificaListener(addLavoroView());
				mainView.setVisibleBtnAssociazioni(true);
			}
		};
	}
	
	//EDIT
	/**
	 * Visualizza la view per modificare i dati di una Gru.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaModificaGruView() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v=mainView.getSelectedData();
				if(v==null){
					JOptionPane.showMessageDialog(null,"Selezionare la riga da modificare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					EditGru ins = new EditGru(mainView, v);
					InsertController ctr = new InsertController(model);
					ins.setInsertButtonListeners(ctr.EditGruListener(ins,(Integer)v[0]));
				}
			}
		};
	}
	/**
	 * Visualizza la view per modificare i dati di una Ruspa.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaModificaRuspaView() {
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
	}
	
	/**
	 * Visualizza la view per modificare i dati di un Camion.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaModificaCamionView() {
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
	
	/**
	 * Visualizza la view per modificare i dati di un Escavatore.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener visualizzaModificaEscavatoreView() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v=mainView.getSelectedData();

				if(v==null){
					JOptionPane.showMessageDialog(null,"Selezionare la riga da modificare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					EditEscavatore ins = new EditEscavatore(mainView, v);
					InsertController ctr = new InsertController(model);
					ins.setInsertButtonListeners(ctr.EditEscavatoreListener(ins,(Integer)v[0]));
				}
			}
		};
	}
	/*public ActionListener VisualizzaModificaCantiereView() {
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
	public ActionListener eliminaMacchina(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v=mainView.getSelectedData();

				if(v==null){
					JOptionPane.showMessageDialog(mainView,"Selezionare la riga da modificare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					//TODO Mostrare a messaggio il codice dei lavori che sono associati a tale macchina
					//int confirm=JOptionPane.showConfirmDialog(mainView,"Eliminando questa macchina verranno eliminate anche le associazioni con i lavori associati. \n Si desidera continuare con l'eliminazione?","Elimina Macchina", JOptionPane.YES_NO_OPTION);		
					//if(confirm==JOptionPane.OK_OPTION){
						model.eliminaMacchina(Integer.parseInt(v[0].toString()));
					//}
				}
			}
		};
	}
	
	/**
	 * Elimina il cantiere selezionato nella tabella.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener eliminaCantiere(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v=mainView.getSelectedData();

				if(v==null){
					JOptionPane.showMessageDialog(null,"Selezionare la riga da modificare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					model.eliminaCantiere(Integer.parseInt(v[0].toString()));
					mainView.removeSelected();
				}
			}
		};
	}
	
	/**
	 * Visualizza la view per aggiungere Associazioni ad un cantiere.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
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
	}
	public ActionListener addGreedyEngineView(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				view.GreedyEngine greedyEngine=new view.GreedyEngine(mainView);
				CantieriController ctr = new CantieriController(model);
				greedyEngine.addBtnGeneraMigliorAssociazioneListener(ctr.greedyEngineListener(greedyEngine));
				greedyEngine.addBtnConfermaAssociazioniListener(ctr.confermaAssociazioniListener(greedyEngine));
			}
		};
	}
}
