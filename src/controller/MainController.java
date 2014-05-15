package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.InserimentoGru;
import view.InserimentoRuspa;
import view.MainView;

import model.ModelConnector;

public class MainController implements ListSelectionListener {

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
				/*Object[] v1 = { "Mauro", "Valota", "Correre", new Integer(5),
						new Boolean(false) };
				mainView.addData(v1);
			*/}

		};
	}
	
	private boolean a=false;
	public ActionListener VisualizzaElencoRuspe() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(a==false){
					mainView.change();a=true;
				}else{
					mainView.change2();a=false;
				}
			}
		};
	}
	public ActionListener VisualizzaModificaGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] v1 = { "Mauro", "Valota", 123, 321, 765, 567 };

				InserimentoGru ins = new InserimentoGru(mainView, v1);
				InsertController ctr = new InsertController(model);
				ins.setInsertButtonListeners(ctr.InsertGruListener(ins));
			}
		};
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

		DefaultListSelectionModel lsm = (DefaultListSelectionModel) e
				.getSource();

		if (lsm.isSelectionEmpty()) {
			System.out.println(" <none>");
		} else {
			// Find out which indexes are selected.
			int minIndex = lsm.getMinSelectionIndex();
			int maxIndex = lsm.getMaxSelectionIndex();
			/*
			 * for (int i = minIndex; i <= maxIndex; i++) { if
			 * (lsm.isSelectedIndex(i)) { System.out.println(" "+i); } }
			 */
			System.out.println(lsm.getLeadSelectionIndex());
		}
	}
}
