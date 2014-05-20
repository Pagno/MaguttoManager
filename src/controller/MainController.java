package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

import view.EditCamion;
import view.EditGru;
import view.EditRuspa;
import view.MainView;
import model.ModelConnector;

public class MainController{

	private ModelConnector model;
	private MainView mainView;

	public MainController(ModelConnector m) {
		model = m;
		mainView = new MainView();
		setObserver();
		model.refreshData();
		mainView.setVisible(true);
		
		//INSERT LISTENER
		mainView.addAggiungiRuspaListener(VisualizzaInserimentoRuspa());
		mainView.addAggiungiGruListener(VisualizzaInserimentoGru());
		
		//TABLE LISTENER
		mainView.addButtonGruListener(VisualizzaElencoGru());
		mainView.addButtonRuspaListener(VisualizzaElencoRuspe());
		
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
		model.addEscavatoreObserver(mainView.dataModelCamion);		
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
