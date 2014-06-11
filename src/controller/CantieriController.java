package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import model.Camion;
import model.Escavatore;
import model.Gru;
import model.Macchina;
import model.ModelConnector;
import model.Ruspa;
import view.AddAssociazione;
import view.EditCantiere;

public class CantieriController {
	private ModelConnector model;
	
	public CantieriController(ModelConnector m) {
		model = m;
	}
	public ActionListener InsertNuovoCantiereListener(EditCantiere view){
		final EditCantiere cantieriView=view;
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
				model.aggiungiCantiere(nome, indirizzo, dataInizio, dataFine);				
				cantieriView.dispose();
				/*GregorianCalendar inizio=new GregorianCalendar();inizio.setTime(cantieriView.getDataInizio());
				GregorianCalendar fine=new GregorianCalendar();fine.setTime(cantieriView.getDataFine());
				int cod=model.aggiungiCantiere(nome, indirizzo, inizio, fine);
				for(Object[] data:lista){

					model.aggiungiAssociazione((int)(data[0]), cod, (GregorianCalendar)data[1], (GregorianCalendar)data[2]);
				}*/
			}};
	}
	/*public ActionListener OpenViewAddAssociazioniListener(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String nome=cantieriView.getNomeCantiere();
				/*if(cantieriView.getDataInizio()==null || cantieriView.getDataFine()==null ){
					JOptionPane.showMessageDialog(null,"Scelezionare prima Data Inizo e Data Fine Cantiere.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					AddAssociazione ass =new AddAssociazione(cantieriView,nome, cantieriView.getDataInizio(),cantieriView.getDataFine());
					ass.aggiungiAssoziazioneListener(AddAssociazioniListener(ass));
					ass.addPropertyChangeListener(checkAssociazioni(ass));
					ass.aggiungiComboBoxListener(cambioTipoMacchina(ass));
					ass.aggiungiRimuoviListener(btnRimuoviListener(ass));
				//}
			}
		};
		
	}*/
	
	public ActionListener addAssociazioneListener(AddAssociazione v,final int codiceCantiere){
		final AddAssociazione view=v;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Object[]> list=view.getAssociazioni();
				
				for(Object[] obj:list){
					model.aggiungiAssociazione((int)obj[0], codiceCantiere,(GregorianCalendar)obj[2],(GregorianCalendar) obj[3]);
				}
				view.dispose();
			}
		};		
	}
	
	public ActionListener btnRimuoviListener(final AddAssociazione view){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				view.rimuoviAssociazioneSelezionata();
			}
		};
		
	}
	
	
	public ActionListener addMacchinaListener(AddAssociazione view){
		final AddAssociazione ass=view;
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Macchina r=(Macchina)ass.getListSelected();
				
			    
				Object[] list={r.getCodice(),r.getProduttore()+" - "+r.getModello()
						,ass.getDataInizio(),ass.getDataFine()};
				
				ass.addData(list);
			}
		};
		
	}
	private boolean validate=false;
	private String tipoMacchina="Ruspa";
	//CONTROLLO CORRETTEZZA DATE
	public PropertyChangeListener checkAssociazioni(AddAssociazione view){
		final AddAssociazione ass=view;
		return new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				JDateChooser event=(JDateChooser)(arg0.getSource());
				

				if(ass.getDataInizio()!=null && ass.getDataFine()!=null ){
					
					if(event.getName().equals("dataInizio")){
						if(ass.getDataInizio().compareTo(ass.getDataFine())>0){
							ass.setDataInizio(null);
							validate=false;
							JOptionPane.showMessageDialog(null,"La data di inizio deve essere minore della data di fine.","Error", JOptionPane.ERROR_MESSAGE);
						}
						else{
							validate=true;
						}
					}
					if(event.getName().equals("dataFine")){
						if(ass.getDataFine().compareTo(ass.getDataInizio())<0){
							ass.setDataFine(null);
							validate=false;
							JOptionPane.showMessageDialog(null,"La data di fine deve essere maggiore della data di inizio.","Error", JOptionPane.ERROR_MESSAGE);
						}
						else{
							validate=true;
						}
					}
					if(validate==true){
						aggiornaElencoMacchine(ass);
					}
				}
				else{
					validate=false;
				}
			}
			
		};
	}
	public ActionListener cambioTipoMacchina(AddAssociazione view){
		final AddAssociazione ass=view;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tipoMacchina=((JComboBox)arg0.getSource()).getSelectedItem().toString();
				if(validate==true)
					aggiornaElencoMacchine(ass);
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	private void aggiornaElencoMacchine(AddAssociazione ass){
		ass.clearList();
		if(tipoMacchina.equals("Ruspa")){
			ArrayList<Ruspa> ruspeDisp=model.elencoRuspeDisponibili(ass.getDataInizio(),ass.getDataFine());
			for(Ruspa r:ruspeDisp){
				ass.aggiungiMacchinaALista(r);
			}
		}
		if(tipoMacchina.equals("Gru")){
			ArrayList<Gru> gruDisp=model.elencoGruDisponibili(ass.getDataInizio(),ass.getDataFine());
			for(Gru r:gruDisp){
				ass.aggiungiMacchinaALista(r);
			}
		}
		if(tipoMacchina.equals("Escavatore")){
			ArrayList<Escavatore> escavatoreDisp=model.elencoEscavatoreDisponibili(ass.getDataInizio(),ass.getDataFine());
			for(Escavatore r:escavatoreDisp){
				ass.aggiungiMacchinaALista(r);
			}
		}
		if(tipoMacchina.equals("Camion")){
			ArrayList<Camion> camionDisp=model.elencoCamionDisponibili(ass.getDataInizio(),ass.getDataFine());
			for(Camion r:camionDisp){
				ass.aggiungiMacchinaALista(r);
			}
		}
	}

}
