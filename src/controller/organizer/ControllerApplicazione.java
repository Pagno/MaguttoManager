package controller.organizer;
import java.util.Observer;

import view.MainView;
import model.ModelInterface;
import model.organizer.ModelMacchina;



/**
 * 
 * Questa Classe permette di gestire gli eventi,
 * legati all'interazione con le view,
 * provenienti dalla View Principale.
 * 
 * 
 */
public class ControllerApplicazione{// implements AbstractApplicationController{

	/**   model. */
	private ModelInterface model;
	
	/**   main view. */
	private static ControllerApplicazione istanza;

	/**
	 * Ritorna l'istanza di ControllerApplicazione. Se non era ancora instanziato la crea.
	 *
	 * @param ModelInterface per manipolare i dati gestiti dall'applicazione
	 * @return  Istanza di ControllerApplicazione
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
	 * @param ModelInterface per manipolare i dati gestiti dall'applicazione
	 */
	private ControllerApplicazione(ModelInterface modelConnector){
		model = modelConnector;
	}

	/**
	 * Aggiungi gru observer.
	 *
	 * @param observer the observer
	 */
	public void aggiungiGruObserver(Observer observer){
		model.aggiungiGruObserver(observer);
	}
	
	/**
	 * Aggiungi ruspa observer.
	 *
	 * @param observer the observer
	 */
	public void aggiungiRuspaObserver(Observer observer){
		model.aggiungiRuspaObserver(observer);
	}
	
	/**
	 * Aggiungi camion observer.
	 *
	 * @param observer the observer
	 */
	public void aggiungiCamionObserver(Observer observer){
		model.aggiungiCamionObserver(observer);
	}	
	
	/**
	 * Aggiungi escavatore observer.
	 *
	 * @param observer the observer
	 */
	public void aggiungiEscavatoreObserver(Observer observer){
		model.aggiungiEscavatoreObserver(observer);
	}
	
	/**
	 * Aggiungi cantiere observer.
	 *
	 * @param observer the observer
	 */
	public void aggiungiCantiereObserver(Observer observer){
		model.aggiungiCantiereObserver(observer);
	}
	
	//MENU FILE LISTENER
	/**
	 * Gestisce la chiusura dell'applicazione salvando tutti i dati caricati dall'utente.
	 *
	 */
	public void exitManager(){
		model.storeData();
	}
	
	/**
	 * Salva tutti i dati caricati dall'utente.
	 *
	 */
	public void salvaDatiListener(){
		model.storeData();
	}
	
	/**
	 * Carica i dati dell'applicazione.
	 *
	 *
	 */
	public void caricaDatiListener(){
		model.refreshData();
		model.pubblicaContenuto();
	}
	
	/**
	 * Gestisce la chiusura dell'applicazione salvando tutti i dati caricati dall'utente.
	 *
	 */
	public void chiusuraProgramma(){
		model.storeData();
	}
	
	/*
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
	
	/*
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
	 * @param codiceMacchina the codice macchina
	 * @return istanza classe ActionListener
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
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
	 * Elimina una macchina.Tutte le richieste ad essa associate vengono liberate.
	 *
	 * @param codiceMacchina Codice della macchina da eliminare
	 * 
	 * @return <strong>True</strong> Se la macchina viene correttamente eliminata,
	 * <strong>False</strong> Se non viene eliminata.
	 *
	 */
	public boolean eliminaMacchina(Integer codiceMacchina){
			return model.eliminaMacchina(codiceMacchina);
		//}
	}
	
	/**
	 * Elimina un cantiere.
	 *
	 * @param codiceCantiere Codice del cantiere da eliminare
	 * 
	 * @return <strong>True</strong> Se il cantiere viene correttamente eliminato,
	 * <strong>False</strong> Se non viene eliminato.
	 *
	 */
	public boolean eliminaCantiere(Integer codiceCantiere){
		return model.eliminaCantiere(codiceCantiere);
	}
	
	/*
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
	public void initForTest(){
		if(istanza!=null){
			ModelMacchina.initCodice();
			istanza=null;
		}
	}
}
