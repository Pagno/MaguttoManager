package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import model.ModelConnector;
import view.AddAssociazione;
import view.EditCantiere;

public class CantieriController {
	private ModelConnector model;
	private EditCantiere cantieriView;
	private AddAssociazione ass;

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
					ass =new AddAssociazione(cantieriView,nome, cantieriView.getDataInizio(),cantieriView.getDataFine());
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
	public PropertyChangeListener checkAssociazioni(){
		return new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				JDateChooser event=(JDateChooser)(arg0.getSource());
				
				if(ass.getDataInizio()!=null && ass.getDataFine()!=null && event.getName().equals("dataInizio")){
					if(ass.getDataInizio().compareTo(ass.getDataFine())>0){
						ass.setDataInizio(null);
						JOptionPane.showMessageDialog(null,"La data di inizio deve essere minore della data di fine.","Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(ass.getDataInizio()!=null && ass.getDataFine()!=null && event.getName().equals("dataFine")){
					if(ass.getDataFine().compareTo(ass.getDataInizio())<0){
						ass.setDataFine(null);
						JOptionPane.showMessageDialog(null,"La data di fine deve essere maggiore della data di inizio.","Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
			
		};
	}
}
