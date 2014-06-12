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

// TODO: Auto-generated Javadoc
/**
 * The Class CantieriController.
 */
public class CantieriController {
	
	/** The model. */
	private ModelConnector model;
	
	/**
	 * Instantiates a new cantieri controller.
	 *
	 * @param m the m
	 */
	public CantieriController(ModelConnector m) {
		model = m;
	}
	
	/**
	 * Insert nuovo cantiere listener.
	 *
	 * @param view the view
	 * @return the action listener
	 */
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
	
	/**
	 * Adds the associazione listener.
	 *
	 * @param v the v
	 * @param codiceCantiere the codice cantiere
	 * @return the action listener
	 */
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
	
	/**
	 * Btn rimuovi listener.
	 *
	 * @param view the view
	 * @return the action listener
	 */
	public ActionListener btnRimuoviListener(final AddAssociazione view){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				view.rimuoviAssociazioneSelezionata();
			}
		};
		
	}
	
	
	/**
	 * Adds the macchina listener.
	 *
	 * @param view the view
	 * @return the action listener
	 */
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
	
	/** The validate. */
	private boolean validate=false;
	
	/** The tipo macchina. */
	private String tipoMacchina="Ruspa";
	//CONTROLLO CORRETTEZZA DATE
	/**
	 * Check associazioni.
	 *
	 * @param view the view
	 * @return the property change listener
	 */
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
	
	/**
	 * Cambio tipo macchina.
	 *
	 * @param view the view
	 * @return the action listener
	 */
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
	
	/**
	 * Aggiorna elenco macchine.
	 *
	 * @param ass the ass
	 */
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
