package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.InserimentoGru;
import view.MainView;

import model.ModelConnector;

public class MainController implements ListSelectionListener {

	private ModelConnector model;
	private MainView view;

	public MainController(ModelConnector m) {
		model = m;
		view = new MainView(m);
		view.addButtonGruListener(VisualizzaElencoGru());
		view.addAggiungiGruListener(VisualizzaInserimentoGru());
		view.addModificaGruListener(VisualizzaModificaGru());
		/*
		 * view.addButtonRuspaListener(this);
		 * view.addButtonCamionListener(this);
		 * view.addButtonEscavatoreListener(this);
		 */
	}

	public ActionListener VisualizzaInserimentoGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InserimentoGru ins = new InserimentoGru(view);
				InsertController ctr = new InsertController(model, ins);
				ins.setInsertButtonListeners(ctr.InsertGruListener());
			}
		};

	}

	public ActionListener VisualizzaElencoGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] v1 = { "Mauro", "Valota", "Correre", new Integer(5),
						new Boolean(false) };
				System.out.println("Bottone premuto");
				view.addData(v1);
			}

		};
	}

	public ActionListener VisualizzaModificaGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] v1 = { "Mauro", "Valota", 123, 321, 765, 567 };

				InserimentoGru ins = new InserimentoGru(view, v1);
				InsertController ctr = new InsertController(model, ins);
				ins.setInsertButtonListeners(ctr.InsertGruListener());
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
