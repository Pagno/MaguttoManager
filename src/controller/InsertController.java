package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.EditCamion;
import view.EditGru;
import view.EditRuspa;
import model.ModelInterface;

public class InsertController {

	ModelInterface model;

	public InsertController(ModelInterface m) {
		model = m;
	}
	
	//INSERT LISTENER
	public ActionListener InsertGruListener( EditGru d) {
		final EditGru dialog=d;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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

	public ActionListener InsertRuspaListener(EditRuspa d) {
		final EditRuspa dialog=d;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
									"I campi:\n - Capacità\n - Altezza\n - Portata Massima\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}
	public ActionListener InsertCamionListener(EditCamion d) {
		final EditCamion dialog=d;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
									"I campi:\n - Capacità\n - Altezza\n - Portata Massima\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}

	
	
	//EDIT LISTENER
	public ActionListener EditGruListener( EditGru d,final int codice) {
		final EditGru dialog=d;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
	
	public ActionListener EditRuspaListener( EditRuspa d,final int codice) {
		final EditRuspa dialog=d;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
	
	public ActionListener EditCamionListener( EditCamion d,final int codice) {
		final EditCamion dialog=d;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					int cap= Integer.parseInt(dialog.getCapacita());
					int lung = Integer.parseInt(dialog.getLunghezza());
					int max = Integer.parseInt(dialog.getPortataMassima());

					model.modificaGru(codice,dialog.getProduttore(),dialog.getModello(),cap,max,lung);
					
					
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
