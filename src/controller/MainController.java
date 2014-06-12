package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import view.AddAssociazione;
import view.EditCamion;
import view.EditCantiere;
import view.EditEscavatore;
import view.EditGru;
import view.EditRuspa;
import view.MainView;
import model.ModelConnector;

// TODO: Auto-generated Javadoc
/**
 * 
 * Questa Classe permette di gestire gli eventi,
 * legati all'interazione con le view,
 * provenienti dalla View Principale.
 * 
 * @see MainView
 * 
 */
public class MainController{

	/** The model. */
	private ModelConnector model;
	
	/** The main view. */
	private MainView mainView;

	/**
	 * Istanzia un nuovo controllore MainController.
	 *
	 * @param modelConnector per manipolare i dati gestiti dall'applicazione
	 * @param view per registrare gli eventi generati dalla view
	 */
	public MainController(ModelConnector modelConnector,MainView view) {
		model = modelConnector;
		mainView = view;
		setObserver();
		model.refreshData();
		mainView.setVisible(true);
		model.pubblicaContenuto();
		//INSERT LISTENER
		mainView.addAggiungiRuspaListener(VisualizzaInserimentoRuspa());
		mainView.addAggiungiGruListener(VisualizzaInserimentoGru());
		mainView.addAggiungiCamionListener(VisualizzaInserimentoCamion());
		mainView.addAggiungiEscavatoreListener(VisualizzaInserimentoEscavatore());
		mainView.addAggiungiCantiereListener(VisualizzaInserimentoCantiere());
		mainView.addBtnAddAssocizioneListener(AddAssociazioneView());
		//TABLE LISTENER
		mainView.addButtonGruListener(VisualizzaElencoGru());
		mainView.addButtonRuspaListener(VisualizzaElencoRuspe());
		mainView.addButtonCamionListener(VisualizzaElencoCamion());
		mainView.addButtonEscavatoreListener(VisualizzaElencoEscavatore());
		mainView.addButtonCantiereListener(VisualizzaElencoCantieri());
		
		//EDIT LISTENER	
		mainView.addModificaListener(VisualizzaModificaGruView());
		
		//DELETE LISTENER
		mainView.addEliminaListener(EliminaMacchina());	
		
		//MENU FILE LISTENER
		mainView.addBtnEsciListener(ExitManager());
		mainView.addBtnCaricaListener(caricaDatiListener());
		mainView.addBtnSalvaListener(salvaDatiListener());
		mainView.addWindowClosingListener(chiusuraProgramma());
		
		
		//MENU FILE LISTENER
		mainView.addBtnSalvaListener(salvaDatiListener());
		

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
	public ActionListener ExitManager(){
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
	 * @return the window adapter
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
	public ActionListener VisualizzaInserimentoGru() {
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
	public ActionListener VisualizzaInserimentoRuspa() {
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
	public ActionListener VisualizzaInserimentoCamion() {
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
	public ActionListener VisualizzaInserimentoEscavatore() {
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
	public ActionListener VisualizzaInserimentoCantiere() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditCantiere ins = new EditCantiere(mainView);
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
	public ActionListener VisualizzaElencoGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showGruData();
				mainView.disableBtnModifica(false);
				mainView.addModificaListener(VisualizzaModificaGruView());
				mainView.addEliminaListener(EliminaMacchina());
				mainView.setVisibleBtnModifica(false);
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
	public ActionListener VisualizzaElencoRuspe() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showRuspaData();
				mainView.disableBtnModifica(false);
				mainView.addModificaListener(VisualizzaModificaRuspaView());
				mainView.addEliminaListener(EliminaMacchina());
				mainView.setVisibleBtnModifica(false);
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
	public ActionListener VisualizzaElencoCamion() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showCamionData();
				mainView.disableBtnModifica(false);
				mainView.addModificaListener(VisualizzaModificaCamionView());
				mainView.addEliminaListener(EliminaMacchina());
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
	public ActionListener VisualizzaElencoEscavatore() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showEscavatoreData();
				mainView.disableBtnModifica(false);
				mainView.addModificaListener(VisualizzaModificaEscavatoreView());
				mainView.addEliminaListener(EliminaMacchina());
				mainView.setVisibleBtnModifica(false);
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
	public ActionListener VisualizzaElencoCantieri() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showCantiereData();
				mainView.disableBtnModifica(true);
				mainView.addEliminaListener(EliminaCantiere());
				mainView.setVisibleBtnModifica(true);
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
	public ActionListener VisualizzaModificaGruView() {
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
	public ActionListener VisualizzaModificaRuspaView() {
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
	public ActionListener VisualizzaModificaCamionView() {
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
	public ActionListener VisualizzaModificaEscavatoreView() {
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
	//DELETE
	/**
	 * Elimina la macchina selezionata nella tabella.
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener EliminaMacchina(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v=mainView.getSelectedData();

				if(v==null){
					JOptionPane.showMessageDialog(null,"Selezionare la riga da modificare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					model.eliminaMacchina(Integer.parseInt(v[0].toString()));
					mainView.removeSelected();
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
	public ActionListener EliminaCantiere(){
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
	public ActionListener AddAssociazioneView(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] data=mainView.getSelectedData();
				String[] tokens = ((String)data[3]).split("/");
				GregorianCalendar d=new GregorianCalendar(Integer.parseInt(tokens[2]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[0]));
				tokens = ((String)data[4]).split("/");
				GregorianCalendar d2=new GregorianCalendar(Integer.parseInt(tokens[2]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[0]));
				
				
				AddAssociazione ass = new AddAssociazione(mainView, data[1].toString(),d,d2);
				CantieriController ctr = new CantieriController(model);
				
				ass.addMacchinaListener(ctr.addMacchinaListener(ass));
				ass.addPropertyChangeListener(ctr.checkAssociazioni(ass));
				ass.addComboBoxListener(ctr.cambioTipoMacchina(ass));
				ass.addRimuoviListener(ctr.btnRimuoviListener(ass));
				ass.setOkBtnListeners(ctr.addAssociazioneListener(ass,(int) mainView.getSelectedData()[0]));
			}
			
		};
	}
}
