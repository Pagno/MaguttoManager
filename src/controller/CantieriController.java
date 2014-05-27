package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import model.Associazione;
import model.ModelConnector;
import model.Ruspa;
import view.AddAssociazione;
import view.EditCantiere;

public class CantieriController {
	private ModelConnector model;
	private EditCantiere cantieriView;
	private ArrayList<String[]> lista;
	
	public CantieriController(ModelConnector m,EditCantiere view) {
		cantieriView=view;
		model = m;	
		lista=new ArrayList<String[]>();
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
				GregorianCalendar inizio=new GregorianCalendar();inizio.setTime(cantieriView.getDataInizio());
				GregorianCalendar fine=new GregorianCalendar();fine.setTime(cantieriView.getDataFine());
				int cod=model.aggiungiCantiere(nome, indirizzo, inizio, fine);
				for(String[] data:lista){

					String[] tokens = data[2].split("/");
					GregorianCalendar i=new GregorianCalendar(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
					tokens = data[3].split("/");
					GregorianCalendar f=new GregorianCalendar(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
					model.aggiungiAssociazione(Integer.parseInt(data[0]), cod, i, f);
				}
			}};
	}
	public ActionListener ViewAddAssociazioniListener(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String nome=cantieriView.getNomeCantiere();
				/*if(cantieriView.getDataInizio()==null || cantieriView.getDataFine()==null ){
					JOptionPane.showMessageDialog(null,"Scelezionare prima Data Inizo e Data Fine Cantiere.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{*/
					AddAssociazione ass =new AddAssociazione(cantieriView,nome, cantieriView.getDataInizio(),cantieriView.getDataFine());
					ass.aggiungiAssoziazioneListener(AddAssociazioniListener(ass));
					ass.addPropertyChangeListener(checkAssociazioni(ass));
				//}
			}
		};
		
	}
	public ActionListener AddAssociazioniListener(AddAssociazione view){
		final AddAssociazione ass=view;
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Ruspa r=(Ruspa)ass.getListSlected();
				SimpleDateFormat df = new SimpleDateFormat();
			    df.applyPattern("dd/MM/yyyy");
			    
				String[] data={Integer.toString(r.getCodice()),r.getProduttore()+" - "+r.getModello()
						,df.format(ass.getDataInizio().getTime())
						,df.format(ass.getDataFine().getTime())};
				lista.add(data);
				ass.addData(data);
			}
		};
		
	}
	
	//CONTROLLO CORRETTEZZA DATE
	public PropertyChangeListener checkAssociazioni(AddAssociazione view){
		final AddAssociazione ass=view;
		return new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				JDateChooser event=(JDateChooser)(arg0.getSource());
				
				boolean validate=true;
				if(ass.getDataInizio()==null || ass.getDataFine()!=null ){
					if(event.getName().equals("dataInizio")){
						if(ass.getDataInizio().compareTo(ass.getDataFine())>0){
							ass.setDataInizio(null);
							validate=false;
							JOptionPane.showMessageDialog(null,"La data di inizio deve essere minore della data di fine.","Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					if(event.getName().equals("dataFine")){
						if(ass.getDataFine().compareTo(ass.getDataInizio())<0){
							ass.setDataFine(null);
							validate=false;
							JOptionPane.showMessageDialog(null,"La data di fine deve essere maggiore della data di inizio.","Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					if(validate==true){
						ArrayList<Ruspa> ruspeDisp=model.elencoRuspeDisponibili(ass.getDataInizio(),ass.getDataFine());
	
						for(Ruspa r:ruspeDisp){
							ass.aggiungiMacchinaALista(r);
						}
					}
				}
			}
			
		};
	}

}
