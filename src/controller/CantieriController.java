package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.ModelConnector;
import view.EditCantiere;

public class CantieriController {
	private ModelConnector model;

	public CantieriController(ModelConnector m) {
		model = m;
		model.refreshData();
		
	}
	public ActionListener InsertNuovoCantiereListener(EditCantiere dialog){
		final EditCantiere d=dialog;
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(d.getDataInizio());
			}};
		
	}
	public PropertyChangeListener setDataInizioChangedListener(EditCantiere dialog){
		final EditCantiere d=dialog;
		return new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				d.setMinimaDataFine(d.getDataInizio());
				
			}};
		
	}
}
