package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.InserimentoGru;
import model.ModelInterface;

public class InsertController {

	InserimentoGru dialog;
	ModelInterface model;
	public InsertController(ModelInterface m,InserimentoGru d){
		dialog=d;
		model=m;
	}
	public ActionListener InsertGruListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(dialog.getProduttore());
				try{
					int ang=Integer.parseInt(dialog.getAngoloRotazione());
					int alt=Integer.parseInt(dialog.getAltezza());
					int lung=Integer.parseInt(dialog.getLunghezza());
					int max=Integer.parseInt(dialog.getPortataMassima());


					System.out.print(dialog.getProduttore());
					model.aggiungiGru(dialog.getProduttore(), dialog.getModello(), Integer.parseInt(dialog.getAngoloRotazione()), Integer.parseInt(dialog.getPortataMassima()), Integer.parseInt(dialog.getLunghezza()), Integer.parseInt(dialog.getAltezza()));
					dialog.dispose();
				}catch(NumberFormatException exc){
					JOptionPane.showMessageDialog(null, "I campi:\n - Lunghezza\n - Altezza\n - Portata Massima\n - Angolo Rotazione\ndevono contenere numeri. ", "Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}
	
}
