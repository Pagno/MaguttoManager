package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import model.organizer.data.Camion;
import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Macchina;
import model.organizer.data.Priority;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaMacchina;
import model.organizer.data.RichiestaRuspa;
import model.ModelInterface;
import model.organizer.data.Ruspa;
import view.InsertCantiere;
import view.lavoro.EditLavoro;


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
	public ActionListener InsertNuovoCantiereListener(InsertCantiere editCantiere){
		final InsertCantiere cantieriView=editCantiere;
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
				model.aggiungiCantiere(nome, indirizzo, dataInizio, dataFine,Priority.valueOf(cantieriView.getPriorita()));				
				cantieriView.dispose();
				/*GregorianCalendar inizio=new GregorianCalendar();inizio.setTime(cantieriView.getDataInizio());
				GregorianCalendar fine=new GregorianCalendar();fine.setTime(cantieriView.getDataFine());
				int cod=model.aggiungiCantiere(nome, indirizzo, inizio, fine);
				for(Object[] data:lista){

					model.aggiungiAssociazione((int)(data[0]), cod, (GregorianCalendar)data[1], (GregorianCalendar)data[2]);
				}*/
			}};
	}
	public ActionListener EditCantiereListener(EditLavoro editCantiere,final int codice){
		final EditLavoro  cantieriView=editCantiere;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GregorianCalendar dataInizio=new GregorianCalendar();
				dataInizio.setTime(cantieriView.getDataInizioCantiere());
				GregorianCalendar dataFine=new GregorianCalendar();
				dataFine.setTime(cantieriView.getDataFineCantiere());
				
				
				String nome=cantieriView.getNomeCantiere();
				String indirizzo=cantieriView.getIndirizzoCantiere();
				
				//MEMORIZZO CANTIERE
				model.modificaCantiere(codice,nome, indirizzo, dataInizio, dataFine,Priority.valueOf(cantieriView.getPrioritaCantiere()));
			}
		};
		
	}
	
	
	/**
	 * Adds   macchina listener.
	 *
	 * @param view   view
	 * @return   action listener
	 */
	/*public ActionListener addMacchinaListener(AddAssociazione view){
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
		
	}*/
	
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
	/*public PropertyChangeListener checkAssociazioni(AddAssociazione addAssociazione){
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
	}*/
	
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
	/*public ActionListener cambioTipoMacchina(AddAssociazione addAssociazione){
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
	}*/
	
	public ActionListener EditLavoroListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("editLavoro");
				
			}
		};
	}
	public ActionListener AddLavoroListener(final EditLavoro editLavoro){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(editLavoro.getDataInizioLavoro()==null || editLavoro.getDataFineLavoro()==null
						|| editLavoro.getNomeLavoro()==""){
					JOptionPane.showMessageDialog(null,"Compilare tutti campi.","Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					String nome=editLavoro.getNomeLavoro();
					GregorianCalendar inizio=new GregorianCalendar();
					inizio.setTime(editLavoro.getDataInizioLavoro());
					GregorianCalendar fine=new GregorianCalendar();
					fine.setTime(editLavoro.getDataFineLavoro());
					
					model.insertLavoro(nome, inizio, fine, 1);
				}
			}
		};
	}
	
	
	/**
	 * Inserisci una nuova richiesta.
	 *
	 * @param EditLavoro   editLavoro
	 */
	public ActionListener AddRichiestaListener(final EditLavoro editLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RichiestaMacchina richiesta=null;
				ArrayList<String> v1=null;
				
				System.out.println();
				
				if(editLavoro.getTipoMacchina()=="Gru"){
					richiesta=new RichiestaGru(editLavoro.getMinLunghezza(), editLavoro.getMaxLunghezza(),
							editLavoro.getMinAltezza(), editLavoro.getMaxAltezza(), editLavoro.getMinPortata(),editLavoro.getMaxPortata(),editLavoro.getMinRotazione(), editLavoro.getMaxRotazione());
						//System.out.println("Codice Cantiere: "+editLavoro.getCodiceCantiere());
					//System.out.println("Codice Lavoro: "+editLavoro.getCodiceLavoro());
				}else if(editLavoro.getTipoMacchina()=="Ruspa"){
					richiesta=new RichiestaRuspa(editLavoro.getMinCapacita(), editLavoro.getMaxCapacita(),
							editLavoro.getMinPortata(), editLavoro.getMaxPortata(), editLavoro.getMinAltezza(),editLavoro.getMaxAltezza());
					}else if(editLavoro.getTipoMacchina()=="Camion"){
					richiesta=new RichiestaCamion(editLavoro.getMinCapacita(), editLavoro.getMaxCapacita(),
							editLavoro.getMinPortata(), editLavoro.getMaxPortata(), editLavoro.getMinLunghezza(),editLavoro.getMaxLunghezza());
				}else if(editLavoro.getTipoMacchina()=="Escavatore"){
					richiesta=new RichiestaEscavatore(editLavoro.getMinCapacita(), editLavoro.getMaxCapacita(),
							editLavoro.getMinPortata(), editLavoro.getMaxPortata(), editLavoro.getMinAltezza(),editLavoro.getMaxAltezza(),editLavoro.getMinProfondita(), editLavoro.getMaxProfondita());
				}
				v1=model.addRichiesta(editLavoro.getCodiceCantiere(), editLavoro.getCodiceLavoro(), richiesta);
				
				v1.add(Integer.toString(editLavoro.getMinCapacita()));v1.add(Integer.toString(editLavoro.getMaxCapacita()));
				v1.add(Integer.toString(editLavoro.getMinPortata()));v1.add(Integer.toString(editLavoro.getMaxPortata()));
				v1.add(Integer.toString(editLavoro.getMinLunghezza()));v1.add(Integer.toString(editLavoro.getMaxLunghezza()));
				v1.add(Integer.toString(editLavoro.getMinAltezza()));v1.add(Integer.toString(editLavoro.getMaxAltezza()));
				v1.add(Integer.toString(editLavoro.getMinProfondita()));v1.add(Integer.toString(editLavoro.getMaxProfondita()));
				v1.add(Integer.toString(editLavoro.getMinRotazione()));v1.add(Integer.toString(editLavoro.getMaxRotazione()));
				System.out.println(v1);
				editLavoro.addRichiesta(v1);
			}
		};
	}
	
	public ActionListener DeleteRichiestaListener(final EditLavoro editLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(editLavoro.getCodiceRichiestaSelezionata());
				//model.deleteRichiesta(editLavoro.getCodiceRichiestaSelezionata());
				editLavoro.removeSelectedData();
			}
		};
	}
	
	public ActionListener DeleteLavoroListener(final EditLavoro editLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(editLavoro.getCodiceLavoroSelezionato());
				//model.deleteLavoro(editLavoro.getCodiceLavoroSelezionato());
				editLavoro.removeSelectedData();
			}
		};
	}
	
	/**
	 * Aggiorna elenco macchine.
	 *
	 * @param ass   ass
	 */
	/*private void aggiornaElencoMacchine(AddAssociazione ass){
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
	}*/


}
