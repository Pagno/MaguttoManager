package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import model.ModelConnector;
import view.EditCantiere;

public class CantieriController {
	private ModelConnector model;

	public CantieriController(ModelConnector m) {
		model = m;		
	}
	public ActionListener InsertNuovoCantiereListener(EditCantiere dialog){
		final EditCantiere d=dialog;
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar dataInizio=new GregorianCalendar();
				dataInizio.setTime(d.getDataInizio());
				GregorianCalendar dataFine=new GregorianCalendar();
				dataFine.setTime(d.getDataFine());
				
				
				String nome=d.getNomeCantiere();
				String indirizzo=d.getIndirizzo();
				model.aggiungiCantiere(nome, indirizzo, dataInizio, dataFine);
				// TODO Auto-generated method stub
				
				
				d.dispose();
			}};
		
	}
}
