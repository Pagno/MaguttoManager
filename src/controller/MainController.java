package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import view.InserimentoGru;
import view.InserimentoRuspa;
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
		mainView.addButtonGruListener(VisualizzaElencoGru());
		mainView.addButtonRuspaListener(VisualizzaElencoRuspe());
		
		
		mainView.addAggiungiGruListener(VisualizzaInserimentoGru());
		mainView.addModificaGruListener(VisualizzaModificaGru());

		mainView.addAggiungiRuspaListener(VisualizzaInserimentoRuspa());
		
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
		model.addRuspaObserver(mainView.dataModelGru);		
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
				InserimentoGru ins = new InserimentoGru(mainView);
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
				InserimentoRuspa ins = new InserimentoRuspa(mainView);
				InsertController ctr = new InsertController(model);
				ins.setInsertButtonListeners(ctr.InsertRuspaListener(ins));
			}
		};
	}
	public ActionListener VisualizzaElencoGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showGru();
			}

		};
	}
	

	public ActionListener VisualizzaElencoRuspe() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainView.showRuspa();
			}
		};
	}
	
	public ActionListener VisualizzaModificaGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v=mainView.getSelectedData();
				if(v==null){
					JOptionPane.showMessageDialog(null,"Selezionare la riga da modificare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					InserimentoGru ins = new InserimentoGru(mainView, v);
					InsertController ctr = new InsertController(model);
					ins.setInsertButtonListeners(ctr.EditGruListener(ins,(Integer)v[0]));
				}
			}
		};
	}
}
