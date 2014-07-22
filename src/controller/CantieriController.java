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
import model.ModelInterface;
import model.Ruspa;
import view.AddAssociazione;
import view.EditCantiere;


/**
 * 
 * Questa classe permette di gestire gli eventi,
 * legati all'isnerimento di nuovi cantieri 
 * ed alle associzioni ad essi legate
 * 
 */

public class CantieriController {
	
	/**   model. */
	private ModelInterface model;
	
	/**
	 * Istanzia un nuovo controllore CantieriController.
	 *
	 * @param modelConnector per manipolare i dati inseriti nell'applicazione
	 * 
	 */
	public CantieriController(ModelInterface modelConnector) {
		model = modelConnector;
	}
	
	/**
	 * Gestisce l'inserimento di un nuovo <em>"Cantiere"</em>.
	 *
	 * @param editCantiere view da cui leggere i dati del nuovo Cantiere
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener InsertNuovoCantiereListener(EditCantiere editCantiere){
		final EditCantiere cantieriView=editCantiere;
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
	public ActionListener EditCantiereListener(EditCantiere editCantiere,final int codice){
		final EditCantiere cantieriView=editCantiere;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GregorianCalendar dataInizio=new GregorianCalendar();
				dataInizio.setTime(cantieriView.getDataInizio());
				GregorianCalendar dataFine=new GregorianCalendar();
				dataFine.setTime(cantieriView.getDataFine());
				
				
				String nome=cantieriView.getNomeCantiere();
				String indirizzo=cantieriView.getIndirizzo();
				
				//MEMORIZZO CANTIERE
				model.modificaCantiere(codice,nome, indirizzo, dataInizio, dataFine);				
				cantieriView.dispose();
			}
		};
		
	}

	/**
	 * Gestisce l'aggiunta di una <em>"Associazione"</em>.
	 *
	 *@param addAssociazione view da cui leggere i dati dell'Associzione da inserire
	 *@param codiceCantiere codice  del Cantiere 
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener addAssociazioneListener(AddAssociazione addAssociazione,final int codiceCantiere){
		final AddAssociazione view=addAssociazione;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Macchina r=(Macchina)view.getListSelected();
				
				model.aggiungiAssociazione(r.getCodice(), codiceCantiere,view.getDataInizio(),view.getDataFine());
				
				aggiornaElencoMacchine(view);
			}
		};		
	}
	
	/**
	 * Gestisce l'eliminaziozne delle Associzioni selezionate.
	 *
	 *@param addAssociazione view da cui leggere i dati dell'Associazione da cancellare
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener btnRimuoviListener(final AddAssociazione addAssociazione){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(addAssociazione.getCodiceAssociazioneSelezionata()==-1){
					JOptionPane.showMessageDialog(null,"Selezionare l'associazione da cancellare.","Error", JOptionPane.ERROR_MESSAGE);		
				}else{
					model.eliminaAssociazione(addAssociazione.getCodiceAssociazioneSelezionata());
					addAssociazione.rimuoviAssociazioneSelezionata();
					aggiornaElencoMacchine(addAssociazione);
				}
			}
		};
		
	}
	
	
	/**
	 * Adds   macchina listener.
	 *
	 * @param view   view
	 * @return   action listener
	 */
	public ActionListener addMacchinaListener(AddAssociazione view){
		final AddAssociazione ass=view;
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Macchina r=(Macchina)ass.getListSelected();
				
			    
				Object[] list={r.getCodice(),r.getProduttore()+" - "+r.getModello()
						,ass.getDataInizio(),ass.getDataFine()};
				
				ass.addData(list);
			}
		};
		
	}
	
	/**   validate. */
	private boolean validate=false;
	
	/**   tipo macchina. */
	private String tipoMacchina="Ruspa";
	//CONTROLLO CORRETTEZZA DATE
	
	/**
	 * Controlla le Macchine disponibile per una nuova associzione.
	 *
	 * @param addAssociazione view da cui leggere i dati
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public PropertyChangeListener checkAssociazioni(AddAssociazione addAssociazione){
		final AddAssociazione ass=addAssociazione;
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
	 * Cambiare il tipo di macchina da aggiungere all'associazione.
	 *
	 * @param addAssociazione view da cui leggere i dati
	 *
	 * @return istanza classe ActionListener 
	 * che implementa il metodo <strong>actionPerformed</strong>
	 * contenente il comportamento legato all'evento generato.
	 *
	 */
	public ActionListener cambioTipoMacchina(AddAssociazione addAssociazione){
		final AddAssociazione ass=addAssociazione;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tipoMacchina=((JComboBox)arg0.getSource()).getSelectedItem().toString();
				System.out.println(tipoMacchina);
				if(validate==true)
					aggiornaElencoMacchine(ass);
				
			}
		};
	}
	
	/**
	 * Aggiorna elenco macchine.
	 *
	 * @param ass   ass
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
