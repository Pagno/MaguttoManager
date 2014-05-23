package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import model.ModelConnector;
import view.AddAssociazione;
import view.EditCantiere;

public class CantieriController {
	private ModelConnector model;
	private EditCantiere cantieriView;

	public CantieriController(ModelConnector m,EditCantiere view) {
		cantieriView=view;
		model = m;		
	}
	public ActionListener InsertNuovoCantiereListener(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar dataInizio=new GregorianCalendar();
				dataInizio.setTime(cantieriView.getDataInizio());
				GregorianCalendar dataFine=new GregorianCalendar();
				dataFine.setTime(cantieriView.getDataFine());
				
				
				String nome=cantieriView.getNomeCantiere();
				String indirizzo=cantieriView.getIndirizzo();
				
				//MEMORIZZO CANTIERE
				//model.aggiungiCantiere(nome, indirizzo, dataInizio, dataFine);
				// TODO Auto-generated method stub
				
				//MEMORIZZO LE ASSOCIAZIONI DI QUEL CANTIERE
				//cantieriView.getAssociazioniList();
				
				
				System.out.println(nome+" - "+indirizzo+" - "+cantieriView.getDataInizio()+" - "+cantieriView.getDataFine());
				cantieriView.dispose();
			}};
	}
	public ActionListener ViewAddAssocizioniListener(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String nome=cantieriView.getNomeCantiere();
				/*if(cantieriView.getDataInizio()==null || cantieriView.getDataFine()==null ){
					JOptionPane.showMessageDialog(null,"Scelezionare prima Data Inizo e Data Fine Cantiere.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{*/
					AddAssociazione ass =new AddAssociazione(cantieriView,nome, cantieriView.getDataInizio(),cantieriView.getDataFine());
					ass.aggiungiAssoziazioneListenet(AddAssociazioniListener(ass));
				//}
			}
		};
		
	}
	public ActionListener AddAssociazioniListener(AddAssociazione view){
		final AddAssociazione viewAss=view;
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] data={"Ruspa","a","b"};
				viewAss.addData(data);
			}
		};
		
	}
	
}
