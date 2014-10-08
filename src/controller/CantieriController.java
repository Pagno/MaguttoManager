package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import controller.data.Associazione;
import model.organizer.data.Cantiere;
import model.organizer.data.Priority;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaMacchina;
import model.organizer.data.RichiestaRuspa;
import model.ModelInterface;
import view.AssociaMacchina;
import view.InsertCantiere;
import view.lavoro.EditLavoro;

/**
 * 
 * Questa classe permette di gestire gli eventi, legati all'isnerimento di nuovi
 * cantieri ed alle associzioni ad essi legate
 * 
 */

public class CantieriController {

	/** model. */
	private ModelInterface model;

	/**
	 * Istanzia un nuovo controllore CantieriController.
	 *
	 * @param modelConnector
	 *            per manipolare i dati inseriti nell'applicazione
	 * 
	 */
	public CantieriController(ModelInterface modelConnector) {
		model = modelConnector;
	}

	/**
	 * Gestisce l'inserimento di un nuovo <em>"Cantiere"</em>.
	 *
	 * @param editCantiere
	 *            view da cui leggere i dati del nuovo Cantiere
	 *
	 * @return istanza classe ActionListener che implementa il metodo
	 *         <strong>actionPerformed</strong> contenente il comportamento
	 *         legato all'evento generato.
	 *
	 */
	public ActionListener InsertNuovoCantiereListener(
			InsertCantiere editCantiere) {
		final InsertCantiere cantieriView = editCantiere;
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar dataInizio = new GregorianCalendar();
				dataInizio.setTime(cantieriView.getDataInizio());
				GregorianCalendar dataFine = new GregorianCalendar();
				dataFine.setTime(cantieriView.getDataFine());

				String nome = cantieriView.getNomeCantiere();
				String indirizzo = cantieriView.getIndirizzo();

				// MEMORIZZO CANTIERE
				model.aggiungiCantiere(nome, indirizzo, dataInizio, dataFine,
						Priority.valueOf(cantieriView.getPriorita()));
				cantieriView.dispose();

			}
		};
	}

	public ActionListener EditCantiereListener(EditLavoro editCantiere,
			final int codice) {
		final EditLavoro cantieriView = editCantiere;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GregorianCalendar dataInizio = new GregorianCalendar();
				dataInizio.setTime(cantieriView.getDataInizioCantiere());
				GregorianCalendar dataFine = new GregorianCalendar();
				dataFine.setTime(cantieriView.getDataFineCantiere());

				String nome = cantieriView.getNomeCantiere();
				String indirizzo = cantieriView.getIndirizzoCantiere();

				// MEMORIZZO CANTIERE
				model.modificaCantiere(codice, nome, indirizzo, dataInizio,
						dataFine,
						Priority.valueOf(cantieriView.getPrioritaCantiere()));
			}
		};

	}

	/**
	 * Adds macchina listener.
	 *
	 * @param view
	 *            view
	 * @return action listener
	 */
	/*
	 * public ActionListener addMacchinaListener(AddAssociazione view){ final
	 * AddAssociazione ass=view; return new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { Macchina
	 * r=(Macchina)ass.getListSelected();
	 * 
	 * 
	 * Object[] list={r.getCodice(),r.getProduttore()+" - "+r.getModello()
	 * ,ass.getDataInizio(),ass.getDataFine()};
	 * 
	 * ass.addData(list); } };
	 * 
	 * }
	 */

	/** validate. */
	// private boolean validate=false;

	/** tipo macchina. */
	// private String tipoMacchina="Ruspa";
	// CONTROLLO CORRETTEZZA DATE

	/**
	 * Controlla le Macchine disponibile per una nuova associzione.
	 *
	 * @param addAssociazione
	 *            view da cui leggere i dati
	 *
	 * @return istanza classe ActionListener che implementa il metodo
	 *         <strong>actionPerformed</strong> contenente il comportamento
	 *         legato all'evento generato.
	 *
	 */
	/*
	 * public PropertyChangeListener checkAssociazioni(AddAssociazione
	 * addAssociazione){ final AddAssociazione ass=addAssociazione; return new
	 * PropertyChangeListener(){
	 * 
	 * @Override public void propertyChange(PropertyChangeEvent arg0) {
	 * JDateChooser event=(JDateChooser)(arg0.getSource());
	 * 
	 * 
	 * if(ass.getDataInizio()!=null && ass.getDataFine()!=null ){
	 * 
	 * if(event.getName().equals("dataInizio")){
	 * if(ass.getDataInizio().compareTo(ass.getDataFine())>0){
	 * ass.setDataInizio(null); validate=false;
	 * JOptionPane.showMessageDialog(null
	 * ,"La data di inizio deve essere minore della data di fine.","Error",
	 * JOptionPane.ERROR_MESSAGE); } else{ validate=true; } }
	 * if(event.getName().equals("dataFine")){
	 * if(ass.getDataFine().compareTo(ass.getDataInizio())<0){
	 * ass.setDataFine(null); validate=false;
	 * JOptionPane.showMessageDialog(null,
	 * "La data di fine deve essere maggiore della data di inizio.","Error",
	 * JOptionPane.ERROR_MESSAGE); } else{ validate=true; } }
	 * if(validate==true){ aggiornaElencoMacchine(ass); } } else{
	 * validate=false; } }
	 * 
	 * }; }
	 */

	/**
	 * Cambiare il tipo di macchina da aggiungere all'associazione.
	 *
	 * @param addAssociazione
	 *            view da cui leggere i dati
	 *
	 * @return istanza classe ActionListener che implementa il metodo
	 *         <strong>actionPerformed</strong> contenente il comportamento
	 *         legato all'evento generato.
	 *
	 */
	/*
	 * public ActionListener cambioTipoMacchina(AddAssociazione
	 * addAssociazione){ final AddAssociazione ass=addAssociazione; return new
	 * ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent arg0) {
	 * tipoMacchina=((JComboBox)arg0.getSource()).getSelectedItem().toString();
	 * System.out.println(tipoMacchina); if(validate==true)
	 * aggiornaElencoMacchine(ass);
	 * 
	 * } }; }
	 */

	public ActionListener EditLavoroListener(final EditLavoro editLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Testare il funzionamento

				GregorianCalendar newStartDate, newEndDate, oldStartDate, oldEndDate;
				// Prendo le nuove date
				(newStartDate = new GregorianCalendar()).setTime(editLavoro
						.getDataInizioLavoro());
				(newEndDate = new GregorianCalendar()).setTime(editLavoro
						.getDataFineLavoro());

				/*
				 * Devo controllare che le date inserite rispettino la
				 * disponibilità delle macchine associatealle varie richieste
				 */
				int codiceLavoro = editLavoro.getCodiceLavoro();

				ArrayList<Richiesta> richieste = model
						.getElencoRichieste(codiceLavoro);

				boolean modifica = true;

				if (richieste.size() != 0) {
					// Prendo le vecchie date
					oldStartDate = ((richieste.get(0)).getDataInizio());
					oldEndDate = ((richieste.get(0)).getDataFine());
					/*
					 * Se la nuova data di inizio e posteriore alla vecchia data
					 * di inizio e la se la nuova data di fine e antecedente
					 * alla vecchia data di fine posso tranquillamente
					 * modificare il lavoro
					 */
					if (newStartDate.before(oldStartDate)
							|| newEndDate.after(oldStartDate)) {
						for (Richiesta richiesta : richieste) {
							if (newStartDate.before(oldStartDate))
								modifica = modifica
										&& richiesta.getMacchina().isFree(
												newStartDate, oldStartDate);
							if (newEndDate.after(oldStartDate))
								modifica = modifica
										&& richiesta.getMacchina().isFree(
												oldEndDate, newEndDate);
						}
					}
				}
				if (modifica) {
					model.modificaLavoro(codiceLavoro,
							editLavoro.getNomeLavoro(), newStartDate,
							newEndDate);
				} else {
					JOptionPane
							.showMessageDialog(
									editLavoro,
									"Non è stato possibile modificare il lavoro. Potrebbero esserci sovrapposizioni con le date di altri lavori.",
									"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}

	public ActionListener AddLavoroListener(final EditLavoro editLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (editLavoro.getDataInizioLavoro() == null
						|| editLavoro.getDataFineLavoro() == null
						|| editLavoro.getNomeLavoro() == "") {
					JOptionPane.showMessageDialog(null,
							"Compilare tutti campi.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String nome = editLavoro.getNomeLavoro();
					GregorianCalendar inizio = new GregorianCalendar();
					inizio.setTime(editLavoro.getDataInizioLavoro());
					GregorianCalendar fine = new GregorianCalendar();
					fine.setTime(editLavoro.getDataFineLavoro());

					model.insertLavoro(nome, inizio, fine, 1);
					editLavoro.reloadModel();
				}
			}
		};
	}

	/**
	 * Inserisci una nuova richiesta.
	 *
	 * @param EditLavoro
	 *            editLavoro
	 */
	public ActionListener AddRichiestaListener(final EditLavoro editLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RichiestaMacchina richiesta = null;

				if (editLavoro.getTipoMacchina() == "Gru") {
					richiesta = new RichiestaGru(editLavoro.getMinLunghezza(),
							editLavoro.getMaxLunghezza(),
							editLavoro.getMinAltezza(),
							editLavoro.getMaxAltezza(),
							editLavoro.getMinPortata(),
							editLavoro.getMaxPortata(),
							editLavoro.getMinRotazione(),
							editLavoro.getMaxRotazione());
				} else if (editLavoro.getTipoMacchina() == "Ruspa") {
					richiesta = new RichiestaRuspa(editLavoro.getMinCapacita(),
							editLavoro.getMaxCapacita(),
							editLavoro.getMinPortata(),
							editLavoro.getMaxPortata(),
							editLavoro.getMinAltezza(),
							editLavoro.getMaxAltezza());
				} else if (editLavoro.getTipoMacchina() == "Camion") {
					richiesta = new RichiestaCamion(
							editLavoro.getMinCapacita(),
							editLavoro.getMaxCapacita(),
							editLavoro.getMinPortata(),
							editLavoro.getMaxPortata(),
							editLavoro.getMinLunghezza(),
							editLavoro.getMaxLunghezza());
				} else if (editLavoro.getTipoMacchina() == "Escavatore") {
					richiesta = new RichiestaEscavatore(
							editLavoro.getMinCapacita(),
							editLavoro.getMaxCapacita(),
							editLavoro.getMinPortata(),
							editLavoro.getMaxPortata(),
							editLavoro.getMinAltezza(),
							editLavoro.getMaxAltezza(),
							editLavoro.getMinProfondita(),
							editLavoro.getMaxProfondita());
				}
				model.addRichiesta(editLavoro.getCodiceCantiere(),
						editLavoro.getCodiceLavoro(), richiesta);

				editLavoro.reloadModel();
			}
		};
	}

	public ActionListener DeleteRichiestaListener(final EditLavoro editLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.print(editLavoro.getCodiceRichiestaSelezionata());
				model.deleteRichiesta(editLavoro
						.getCodiceRichiestaSelezionata());
				editLavoro.reloadModel();
			}
		};
	}

	public ActionListener DeleteLavoroListener(final EditLavoro editLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.print(editLavoro.getCodiceLavoroSelezionato());
				model.deleteLavoro(editLavoro.getCodiceLavoroSelezionato());
				editLavoro.reloadModel();
			}
		};
	}

	/**
	 * Aggiorna elenco macchine.
	 *
	 * @param ass
	 *            ass
	 */
	/*
	 * private void aggiornaElencoMacchine(AddAssociazione ass){
	 * ass.clearList(); if(tipoMacchina.equals("Ruspa")){ ArrayList<Ruspa>
	 * ruspeDisp
	 * =model.elencoRuspeDisponibili(ass.getDataInizio(),ass.getDataFine());
	 * for(Ruspa r:ruspeDisp){ ass.aggiungiMacchinaALista(r); } }
	 * if(tipoMacchina.equals("Gru")){ ArrayList<Gru>
	 * gruDisp=model.elencoGruDisponibili
	 * (ass.getDataInizio(),ass.getDataFine()); for(Gru r:gruDisp){
	 * ass.aggiungiMacchinaALista(r); } } if(tipoMacchina.equals("Escavatore")){
	 * ArrayList<Escavatore>
	 * escavatoreDisp=model.elencoEscavatoreDisponibili(ass
	 * .getDataInizio(),ass.getDataFine()); for(Escavatore r:escavatoreDisp){
	 * ass.aggiungiMacchinaALista(r); } } if(tipoMacchina.equals("Camion")){
	 * ArrayList<Camion>
	 * camionDisp=model.elencoCamionDisponibili(ass.getDataInizio
	 * (),ass.getDataFine()); for(Camion r:camionDisp){
	 * ass.aggiungiMacchinaALista(r); } } }
	 */

	public ActionListener associaMacchinaView(final EditLavoro editLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssociaMacchina am = new AssociaMacchina(editLavoro,
						model.getElencoMacchineDisponibili(editLavoro
								.getCodiceRichiestaSelezionata()));
				int codiceRichiesta = editLavoro
						.getCodiceRichiestaSelezionata();
				am.addBtnAssociaListener(associaMacchina(codiceRichiesta,
						editLavoro, am));
			}
		};
	}

	public ActionListener associaMacchina(final int codiceRichiesta,
			final EditLavoro editLavoro, final AssociaMacchina am) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int codiceMacchina = am.getMacchinaSelezionata();
				am.dispose();
				model.soddisfaRichiesta(codiceRichiesta, codiceMacchina);
				editLavoro.aggiornaRichiesta();
			}
		};
	}

	public ActionListener rimuoviAssociazioneListener(
			final EditLavoro editLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int codiceRichiesta = editLavoro
						.getCodiceRichiestaSelezionata();
				model.liberaRichiesta(codiceRichiesta);
				editLavoro.aggiornaRichiesta();
			}
		};
	}

	/*
	 * ================LISTENER GREEDY ENGINE=================
	 */

	public ActionListener greedyEngineListener(
			final view.GreedyEngine greedyView) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Associazione> assGreedy = controller.greedy.GreedyEngine
						.generateAssociations(model);
				greedyView.setData(assGreedy);
			}
		};
	}

	public ActionListener confermaAssociazioniListener(
			final view.GreedyEngine greedyView) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Associazione> assGreedy = greedyView.getData();
				for (Associazione associazione : assGreedy) {
					associazione.concretizza();
					greedyView.dispose();
				}
			}
		};
	}
}
