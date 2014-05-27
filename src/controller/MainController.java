package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

import view.EditCamion;
import view.EditCantiere;
import view.EditEscavatore;
import view.EditGru;
import view.EditRuspa;
import view.MainView;
import model.ModelConnector;

public class MainController{

	private ModelConnector model;
	private MainView mainView;

	public MainController(ModelConnector m,MainView mn) {
		model = m;
		mainView = mn;
		setObserver();
		model.refreshData();
		mainView.setVisible(true);
		//model.pubblicaContenuto();
		//INSERT LISTENER
		mainView.addAggiungiRuspaListener(VisualizzaInserimentoRuspa());
		mainView.addAggiungiGruListener(VisualizzaInserimentoGru());
		mainView.addAggiungiCamionListener(VisualizzaInserimentoCamion());
		mainView.addAggiungiEscavatoreListener(VisualizzaInserimentoEscavatore());
		mainView.addAggiungiCantiereListener(VisualizzaInserimentoCantiere());
		
		//TABLE LISTENER
		mainView.addButtonGruListener(VisualizzaElencoGru());
		mainView.addButtonRuspaListener(VisualizzaElencoRuspe());
		mainView.addButtonCamionListener(VisualizzaElencoCamion());
		mainView.addButtonEscavatoreListener(VisualizzaElencoEscavatore());
		
		//EDIT LISTENER	
		mainView.addModificaListener(VisualizzaModificaGruView());
		
		//DELETE LISTENER
		mainView.addEliminaListener(EliminaMacchina());	
		
		mainView.addExitListener(ExitManager());
		mainView.addWindowClosingListener(chiusuraProgramma());
		/*
		 * view.addButtonRuspaListener(this);
		 * view.addButtonCamionListener(this);
		 * view.addButtonEscavatoreListener(this);
		 */
	}
	private void setObserver(){
		model.addGruObserver(mainView.dataModelGru);
		model.addRuspaObserver(mainView.dataModelRuspa);
		model.addCamionObserver(mainView.dataModelCamion);	
		model.addEscavatoreObserver(mainView.dataModelEscavatore);		
	}
	
	
	public ActionListener ExitManager(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.storeData();
				mainView.dispose();
			}
		};
	}

	public WindowAdapter chiusuraProgramma(){
		return new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				model.storeData();
			}
		};
	}
	public ActionListener VisualizzaInserimentoGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditGru ins = new EditGru(mainView);
				InsertController ctr = new InsertController(model);
				ins.setInsertButtonListeners(ctr.InsertGruListener(ins));
			}
		};
	}
	public ActionListener VisualizzaInserimentoRuspa() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditRuspa ins = new EditRuspa(mainView);
				InsertController ctr = new InsertController(model);
				ins.setInsertButtonListeners(ctr.InsertRuspaListener(ins));
			}
		};
	}
	public ActionListener VisualizzaInserimentoCamion() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditCamion ins = new EditCamion(mainView);
				InsertController ctr = new InsertController(model);
				ins.setInsertButtonListeners(ctr.InsertCamionListener(ins));
			}
		};
	}
	public ActionListener VisualizzaInserimentoEscavatore() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditEscavatore ins = new EditEscavatore(mainView);
				InsertController ctr = new InsertController(model);
				ins.setInsertButtonListeners(ctr.InsertEscavatoreListener(ins));
			}
		};
	}
	public ActionListener VisualizzaInserimentoCantiere() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditCantiere ins = new EditCantiere(mainView);
				CantieriController ctr = new CantieriController(model,ins);
				ins.setInsertAddAssociazioneListeners(ctr.ViewAddAssociazioniListener());
				ins.setInsertButtonListeners(ctr.InsertNuovoCantiereListener());
				//ins.setDataInizioChangedListener(ctr.setDataInizioChangedListener(ins));
			}
		};
	}
	
	//VISUALIZZA ELENCO IN TABELLA
	public ActionListener VisualizzaElencoGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showGruData();
			}

		};
	}
	public ActionListener VisualizzaElencoRuspe() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showRuspaData();
				mainView.addModificaListener(VisualizzaModificaRuspaView());
			}
		};
	}
	public ActionListener VisualizzaElencoCamion() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showCamionData();
				mainView.addModificaListener(VisualizzaModificaCamionView());
			}
		};
	}
	public ActionListener VisualizzaElencoEscavatore() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showEscavatoreData();
				mainView.addModificaListener(VisualizzaModificaEscavatoreView());
			}
		};
	}
	//EDIT
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
}
