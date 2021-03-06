package view.lavoro;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.TreePath;

import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Priorita;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaMacchina;
import model.organizer.data.RichiestaRuspa;
import view.lavoro.panel.PanelCantiere;
import view.lavoro.panel.PanelLavoro;
import view.lavoro.panel.PanelRichiesta;
import view.lavoro.panel.PanelVisualizzaRichiesta;
import view.lavoro.treenode.NodeAdder;
import view.lavoro.treenode.NodeRendererAdder;
import view.lavoro.treenode.TreeNodeModel;
import view.macchina.AssociaMacchina;

import javax.swing.JCheckBox;

import controller.ControllerInterface;

/**
 * The Class ViewLavoro.
 * 
 * @author Matteo Pagnoncelli
 * @author Mauro Valota
 */
public class ViewLavoro extends JDialog{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2004768729465641055L;

	/** The card panel. */
	private JPanel contentPane, cardPanel;

	/** The pnl add richiesta. */
	public PanelRichiesta pnlAddRichiesta;

	/** The pnl lavoro. */
	private PanelLavoro pnlLavoro;

	/** The pnl cantiere. */
	private PanelCantiere pnlCantiere;

	/** The pnl visualizza panel. */
	private PanelVisualizzaRichiesta pnlVisualizzaPanel;

	/** The btn delete. */
	private JButton btnDelete;
	// MODELLI TABELLA JTREE
	/** The tree model. */
	public TreeNodeModel treeModel;

	/** The tree. */
	private JTree tree;

	/** The scrollpane. */
	private JScrollPane scrollpane;

	/** The codice cantiere. */
	private int codiceCantiere;

	/** The chckbx new check box. */
	private JCheckBox chckbxNewCheckBox;

	/** The renderer. */
	private NodeRendererAdder renderer;

	/** The edit lavoro. */
	private ViewLavoro editLavoro;
	// LAVORO PANEL
	/**
	 * Create the dialog.
	 */
	private ControllerInterface cCtr;

	/**
	 * Crea un nuovo frame per la gestione di un cantiere.
	 *
	 * @param view view padre
	 * @param cantiere Cantiere da modificare
	 * @param aCtr the a ctr
	 */
	public ViewLavoro(JFrame view, Cantiere cantiere, ControllerInterface aCtr) {
		super(view);
		editLavoro = this;
		cCtr = aCtr;
		setTitle("Edit Cantiere");
		codiceCantiere = cantiere.getCodice();
		// this.datiCantiere=datiCantiere;
		setBounds(0, 0, 900, 500);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("Chiudi");
		okButton.setActionCommand("Chiudi");
		okButton.setName("chiudi");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// CARICO I PANNELLI
		cardPanel = new JPanel(new CardLayout());
		pnlCantiere = new PanelCantiere();
		pnlCantiere.setNomeCantiere(cantiere.getNomeCantiere());
		pnlCantiere.setIndirizzoCantiere(cantiere.getIndirizzo());
		pnlCantiere.setPrioritaCantiere(cantiere.getPrioritaString());
		pnlCantiere.setDataInizioCantiere(cantiere.getDataApertura());
		pnlCantiere.setDataFineCantiere(cantiere.getDataChiusura());
		// pnlCantiere.setRangeDate(cantiere.getDataApertura(),
		// cantiere.getDataChiusura(),cantiere.getElencoLavori().size()!=0);
		pnlCantiere.setName("pnlCantiere");
		pnlCantiere.addModificaCantiereListener(modificaCantiere());

		// pnlCantiere.setDatiCantiere(datiCantiere);
		pnlLavoro = new PanelLavoro(pnlCantiere.getDataInizioCantiere(),
				pnlCantiere.getDataFineCantiere());
		pnlLavoro.setName("pnlLavoro");
		pnlAddRichiesta = new PanelRichiesta();
		pnlAddRichiesta.setName("pnlAddRichiesta");
		pnlAddRichiesta.btnAdd.addActionListener(aggiungiRichiestaListener());
		pnlVisualizzaPanel = new PanelVisualizzaRichiesta();
		pnlVisualizzaPanel.setName("pnlVisualizzaPanel");

		cardPanel.add(pnlLavoro, "lavoro");
		cardPanel.add(pnlAddRichiesta, "richiesta");
		cardPanel.add(pnlCantiere, "cantiere");
		cardPanel.add(pnlVisualizzaPanel, "visualizza");

		contentPane.add(cardPanel, BorderLayout.CENTER);

		treeModel = new TreeNodeModel(cantiere);
		treeModel.reload(cantiere);
		tree = new JTree(treeModel);
		tree.setSize(200, 500);

		renderer = new NodeRendererAdder();
		tree.setCellRenderer(renderer);
		tree.setName("tree");

		chckbxNewCheckBox = new JCheckBox("Nascondi Richieste Soddisfatte");
		chckbxNewCheckBox.addItemListener(check());
		scrollpane = new JScrollPane(tree,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBounds(0, 0, 200, 500);

		JPanel pnlWest = new JPanel(new BorderLayout(0, 0));
		pnlWest.add(chckbxNewCheckBox, BorderLayout.NORTH);
		pnlWest.add(scrollpane, BorderLayout.CENTER);

		scrollpane.setColumnHeaderView(chckbxNewCheckBox);
		btnDelete = new JButton("Delete");
		pnlWest.add(btnDelete, BorderLayout.SOUTH);
		contentPane.add(pnlWest, BorderLayout.WEST);
		CardLayout cl = (CardLayout) (cardPanel.getLayout());

		cl.show(cardPanel, "cantiere");

		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				doMouseClicked(me);
			}
		});
		setVisible(true);
	}

	/**
	 * Evento legato alla modifica del cantiere.
	 *
	 * @return ActionListener
	 */
	private ActionListener modificaCantiere() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar dataInizio = new GregorianCalendar();
				dataInizio.setTime(pnlCantiere.getDataInizioCantiere());
				GregorianCalendar dataFine = new GregorianCalendar();
				dataFine.setTime(pnlCantiere.getDataFineCantiere());
				if (cCtr.modificaCantiere(codiceCantiere,
						pnlCantiere.getNomeCantiere(),
						pnlCantiere.getIndirizzoCantiere(), dataInizio,
						dataFine,
						Priorita.valueOf(pnlCantiere.getPrioritaCantiere()))) {
					JOptionPane.showMessageDialog(null,
							"Cantiere Modificato correttamente.", "OK",
							JOptionPane.INFORMATION_MESSAGE);
					reloadModel();
				}
			}
		};
	}

	/** The codice lavoro. */
	private int codiceLavoro;

	// Aggiornamento dei pannelli
	/**
	 * Evento legto al click del mouse sull'elenco dei lavoro.
	 *
	 * @param me Gestore del click del mouse
	 */
	void doMouseClicked(MouseEvent me) {
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		CardLayout cl = (CardLayout) (cardPanel.getLayout());
		try {
			if (tp.getPathCount() == 1) { // Seleziono il cantiere e mostro il
											// pannello del cantiere
				cl.show(cardPanel, "cantiere");
				btnDelete.setEnabled(false);
			} else if (tp.getPathCount() == 2) {
				cl.show(cardPanel, "lavoro");
				pnlLavoro.setRangeDate(pnlCantiere.getDataInizioCantiere(),
						pnlCantiere.getDataFineCantiere());
				if (tp.getPathComponent(tp.getPathCount() - 1).toString()
						.equals("Aggiungi nuovo Lavoro")) {
					btnDelete.setEnabled(false);
					pnlLavoro.clear();
					pnlLavoro.btnAddActionListener(aggiungiLavoroListener());
					pnlLavoro.btnLavoro.setText("Inserisci");
				} else {
					Lavoro lavoro = ((Lavoro) tp.getPath()[tp.getPathCount() - 1]);
					codiceLavoro = lavoro.getCodice();

					ArrayList<String> l = new ArrayList<String>();
					l.add(Integer.toString(lavoro.getCodice()));
					l.add(lavoro.getNome());
					SimpleDateFormat df = new SimpleDateFormat();
					df.applyPattern("dd/MM/yyyy");
					l.add(df.format(lavoro.getDataInizio().getTime()));
					l.add(df.format(lavoro.getDataFine().getTime()));
					pnlLavoro.fill(l);

					pnlLavoro.btnAddActionListener(modificaLavoroListener());
					btnDelete.setEnabled(true);
					for (ActionListener al : btnDelete.getActionListeners()) {
						btnDelete.removeActionListener(al);
					}

					btnDelete.addActionListener(deleteLavoroListener(lavoro
							.getCodice()));

					pnlLavoro.btnLavoro.setText("Modifica");
				}
			} else if (tp.getPathCount() == 3) {
				if (tp.getPathComponent(tp.getPathCount() - 1).toString()
						.equals("Aggiungi nuova Richiesta")) {
					pnlAddRichiesta.clearData();
					cl.show(cardPanel, "richiesta");
					NodeAdder addNode = (NodeAdder) tp.getPath()[tp.getPath().length - 1];
					Lavoro lavoro = (Lavoro) addNode.getParent();
					codiceLavoro = lavoro.getCodice();
				} else {
					btnDelete.setEnabled(true);
					for (ActionListener al : btnDelete.getActionListeners()) {
						btnDelete.removeActionListener(al);
					}

					Richiesta richiesta = (Richiesta) tp.getPath()[tp.getPath().length - 1];

					btnDelete
							.addActionListener(deleteRichiestaListener(richiesta
									.getCodice()));
					codiceLavoro = richiesta.getCodiceLavoro();

					ArrayList<String> data = richiesta.getData();
					pnlVisualizzaPanel.loadData(data);

					if (richiesta.isSoddisfatta()) {
						pnlVisualizzaPanel.btnAssociaMacchina
								.setText("Rimuovi Associazione");
						pnlVisualizzaPanel
								.addSoddisfaRichiestaListener(liberaRichiestaListener(richiesta
										.getCodice()));
						pnlVisualizzaPanel.setMacchina(richiesta.getMacchina()
								.getCodice()
								+ " "
								+ richiesta.getMacchina().getProduttore()
								+ " "
								+ richiesta.getMacchina().getModello());
					} else {
						pnlVisualizzaPanel
								.addLiberaRichiestaListener(associaMacchinaListener(richiesta
										.getCodice()));
						pnlVisualizzaPanel.btnAssociaMacchina
								.setText("Aggiungi Associazione");
					}
					cl.show(cardPanel, "visualizza");
				}
			}

		} catch (java.lang.NullPointerException ex) {

		}
	}

	/**
	 * Check.
	 *
	 * @return the item listener
	 */
	private ItemListener check() {
		return new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				renderer.set(!chckbxNewCheckBox.isSelected());
				treeModel.reload();
			}
		};
	}

	/**
	 *  Evento legato alla pressione del bottone "Libera Richiesta".
	 *
	 * @return ActionListener
	 *
	 * @param codiceRichiesta Codice della richiesta
	 */
	public ActionListener liberaRichiestaListener(final int codiceRichiesta) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cCtr.liberaRichiesta(codiceRichiesta);

				aggiornaRichiesta();
				reloadModel();
			}
		};
	}

	/**
	 * Evento legato alla pressione del bottone "Associa Macchina".
	 *
	 * 
	 * @param codiceRichiesta Codice della richiesta
	 * @return ActionLister
	 */
	public ActionListener associaMacchinaListener(final int codiceRichiesta) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssociaMacchina am = new AssociaMacchina(editLavoro,
						cCtr.getElencoMacchineDisponibili(codiceRichiesta));
				am.addBtnAssociaListener(aggiungiAssociazioneMacchinaListener(
						am, codiceRichiesta));
			}
		};
	}

	/**
	 * Aggiungi associazione macchina..
	 *
	 * @param am
	 *            the am
	 * @param codiceRichiesta
	 *            the codice richiesta
	 * @return ActionListener
	 */
	public ActionListener aggiungiAssociazioneMacchinaListener(
			final AssociaMacchina am, final int codiceRichiesta) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int codiceMacchina = am.getCodiceMacchinaSelezionata();
				cCtr.soddisfaRichiesta(codiceRichiesta, codiceMacchina);

				aggiornaRichiesta();
				reloadModel();
				am.dispose();
			}
		};
	}

	/**
	 * Aggiungi lavoro listener.
	 *
	 * @return ActionListener
	 */
	public ActionListener aggiungiLavoroListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (pnlLavoro.getDataInizioLavoro() == null
						|| pnlLavoro.getDataFineLavoro() == null
						|| pnlLavoro.getNomeLavoro() == "") {
					JOptionPane.showMessageDialog(null,
							"Compilare tutti campi.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String nome = pnlLavoro.getNomeLavoro();
					GregorianCalendar inizio = new GregorianCalendar();
					inizio.setTime(pnlLavoro.getDataInizioLavoro());
					GregorianCalendar fine = new GregorianCalendar();
					fine.setTime(pnlLavoro.getDataFineLavoro());

					// Aggiungi il lavoro
					cCtr.aggiungiLavoro(codiceCantiere, nome, inizio, fine);

					// ricarico il modello
					reloadModel();
				}
			}
		};
	}

	/**
	 * Modifica il lavoro .
	 *
	 * @return ActionListener
	 */
	public ActionListener modificaLavoroListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (pnlLavoro.getDataInizioLavoro() == null
						|| pnlLavoro.getDataFineLavoro() == null
						|| pnlLavoro.getNomeLavoro() == "") {
					JOptionPane.showMessageDialog(null,
							"Compilare tutti campi.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String nome = pnlLavoro.getNomeLavoro();
					GregorianCalendar inizio = new GregorianCalendar();
					inizio.setTime(pnlLavoro.getDataInizioLavoro());
					GregorianCalendar fine = new GregorianCalendar();
					fine.setTime(pnlLavoro.getDataFineLavoro());

					// Aggiungi il lavoro
					if (cCtr.modificaLavoro(codiceCantiere, codiceLavoro, nome,
							inizio, fine)) {
						JOptionPane.showMessageDialog(null,
								"Lavoro Modificato correttamente.", "OK",
								JOptionPane.INFORMATION_MESSAGE);
					}
					// ricarico il modello
					reloadModel();
				}
			}
		};
	}

	/**
	 * Aggiungi una nuova richiesta.
	 *
	 * @return ActionListener
	 */
	public ActionListener aggiungiRichiestaListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RichiestaMacchina richiesta = null;
				if (controlloCorrettezzaDati()) {
					if (pnlAddRichiesta.getTipoMacchina() == "Gru") {
						richiesta = new RichiestaGru(
								pnlAddRichiesta.getMinLunghezza() == 0 ? -1
										: pnlAddRichiesta.getMinLunghezza(),
								pnlAddRichiesta.getMaxLunghezza() == 0 ? -1
										: pnlAddRichiesta.getMaxLunghezza(),
								pnlAddRichiesta.getMinAltezza() == 0 ? -1
										: pnlAddRichiesta.getMinAltezza(),
								pnlAddRichiesta.getMaxAltezza() == 0 ? -1
										: pnlAddRichiesta.getMaxAltezza(),
								pnlAddRichiesta.getMinPortata() == 0 ? -1
										: pnlAddRichiesta.getMinPortata(),
								pnlAddRichiesta.getMaxPortata() == 0 ? -1
										: pnlAddRichiesta.getMaxPortata(),
								pnlAddRichiesta.getMinRotazione() == 0 ? -1
										: pnlAddRichiesta.getMinRotazione(),
								pnlAddRichiesta.getMaxRotazione() == 0 ? -1
										: pnlAddRichiesta.getMaxRotazione());
					} else if (pnlAddRichiesta.getTipoMacchina() == "Ruspa") {
						richiesta = new RichiestaRuspa(
								pnlAddRichiesta.getMinCapacita() == 0 ? -1
										: pnlAddRichiesta.getMinCapacita(),
								pnlAddRichiesta.getMaxCapacita() == 0 ? -1
										: pnlAddRichiesta.getMaxCapacita(),
								pnlAddRichiesta.getMinPortata() == 0 ? -1
										: pnlAddRichiesta.getMinPortata(),
								pnlAddRichiesta.getMaxPortata() == 0 ? -1
										: pnlAddRichiesta.getMaxPortata(),
								pnlAddRichiesta.getMinAltezza() == 0 ? -1
										: pnlAddRichiesta.getMinAltezza(),
								pnlAddRichiesta.getMaxAltezza() == 0 ? -1
										: pnlAddRichiesta.getMaxAltezza());
					} else if (pnlAddRichiesta.getTipoMacchina() == "Camion") {
						richiesta = new RichiestaCamion(
								pnlAddRichiesta.getMinCapacita() == 0 ? -1
										: pnlAddRichiesta.getMinCapacita(),
								pnlAddRichiesta.getMaxCapacita() == 0 ? -1
										: pnlAddRichiesta.getMaxCapacita(),
								pnlAddRichiesta.getMinPortata() == 0 ? -1
										: pnlAddRichiesta.getMinPortata(),
								pnlAddRichiesta.getMaxPortata() == 0 ? -1
										: pnlAddRichiesta.getMaxPortata(),
								pnlAddRichiesta.getMinLunghezza() == 0 ? -1
										: pnlAddRichiesta.getMinLunghezza(),
								pnlAddRichiesta.getMaxLunghezza() == 0 ? -1
										: pnlAddRichiesta.getMaxLunghezza());
					} else if (pnlAddRichiesta.getTipoMacchina() == "Escavatore") {
						richiesta = new RichiestaEscavatore(
								pnlAddRichiesta.getMinCapacita() == 0 ? -1
										: pnlAddRichiesta.getMinCapacita(),
								pnlAddRichiesta.getMaxCapacita() == 0 ? -1
										: pnlAddRichiesta.getMaxCapacita(),
								pnlAddRichiesta.getMinPortata() == 0 ? -1
										: pnlAddRichiesta.getMinPortata(),
								pnlAddRichiesta.getMaxPortata() == 0 ? -1
										: pnlAddRichiesta.getMaxPortata(),
								pnlAddRichiesta.getMinAltezza() == 0 ? -1
										: pnlAddRichiesta.getMinAltezza(),
								pnlAddRichiesta.getMaxAltezza() == 0 ? -1
										: pnlAddRichiesta.getMaxAltezza(),
								pnlAddRichiesta.getMinProfondita() == 0 ? -1
										: pnlAddRichiesta.getMinProfondita(),
								pnlAddRichiesta.getMaxProfondita() == 0 ? -1
										: pnlAddRichiesta.getMaxProfondita());
					}
					cCtr.aggiungiRichiesta(codiceCantiere, codiceLavoro,
							richiesta);

					reloadModel();
				}
			}
		};
	}

	/**
	 * Controllo se i dati della nuova richeista sono corretti.
	 *
	 * @return true, Se i dati della nuova richiesta sono corretti
	 */
	private boolean controlloCorrettezzaDati() {
		boolean correttezza = true;
		if (pnlAddRichiesta.getMaxCapacita() != 0) {
			if (pnlAddRichiesta.getMinCapacita() > pnlAddRichiesta
					.getMaxCapacita())
				correttezza = false;
		}
		if (pnlAddRichiesta.getMaxLunghezza() != 0) {
			if (pnlAddRichiesta.getMinLunghezza() > pnlAddRichiesta
					.getMaxLunghezza())
				correttezza = false;
		}
		if (pnlAddRichiesta.getMaxAltezza() != 0) {
			if (pnlAddRichiesta.getMinAltezza() > pnlAddRichiesta
					.getMaxAltezza())
				correttezza = false;
		}
		if (pnlAddRichiesta.getMaxRotazione() != 0) {
			if (pnlAddRichiesta.getMinRotazione() > pnlAddRichiesta
					.getMaxRotazione())
				correttezza = false;
		}
		if (pnlAddRichiesta.getMaxPortata() != 0) {
			if (pnlAddRichiesta.getMinPortata() > pnlAddRichiesta
					.getMaxPortata())
				correttezza = false;
		}
		if (pnlAddRichiesta.getMaxProfondita() != 0) {
			if (pnlAddRichiesta.getMinProfondita() > pnlAddRichiesta
					.getMaxProfondita())
				correttezza = false;
		}
		if (!correttezza)
			JOptionPane.showMessageDialog(this,
					"Verificare la correttezza dei dati inseriti.",
					"Attenzione", JOptionPane.ERROR_MESSAGE);
		if (correttezza) {
			if (pnlAddRichiesta.getMinCapacita() < 0
					|| pnlAddRichiesta.getMaxCapacita() < 0)
				correttezza = false;
			if (pnlAddRichiesta.getMinLunghezza() < 0
					|| pnlAddRichiesta.getMaxLunghezza() < 0)
				correttezza = false;
			if (pnlAddRichiesta.getMinAltezza() < 0
					|| pnlAddRichiesta.getMaxAltezza() < 0)
				correttezza = false;
			if (pnlAddRichiesta.getMinRotazione() < 0
					|| pnlAddRichiesta.getMaxRotazione() < 0)
				correttezza = false;
			if (pnlAddRichiesta.getMinPortata() < 0
					|| pnlAddRichiesta.getMaxPortata() < 0)
				correttezza = false;
			if (pnlAddRichiesta.getMinProfondita() < 0
					|| pnlAddRichiesta.getMaxProfondita() < 0)
				correttezza = false;
			if (!correttezza)
				JOptionPane.showMessageDialog(this,
						"Sono ammessi solamento valori positivi.",
						"Attenzione", JOptionPane.ERROR_MESSAGE);
		}

		return correttezza;
	}

	/**
	 * Delete richiesta listener.
	 *
	 * @param codiceRichiesta
	 *            the codice richiesta
	 * @return the action listener
	 */
	private ActionListener deleteRichiestaListener(final int codiceRichiesta) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCtr.eliminaRichiesta(codiceRichiesta);
				reloadModel();
			}
		};
	}

	/**
	 * Delete lavoro listener.
	 *
	 * @param codiceLavoro
	 *            the codice lavoro
	 * @return the action listener
	 */
	public ActionListener deleteLavoroListener(final int codiceLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCtr.eliminaLavoro(codiceLavoro);
				reloadModel();
			}
		};
	}

	/**
	 * Reload model.
	 */
	private void reloadModel() {
		try {
			TreePath tp = tree.getSelectionPath();
			treeModel.reload();
			tree.expandPath(tp.getParentPath());
		} catch (NullPointerException ex) {

		}
	}

	/**
	 * Aggiorna richiesta.
	 */
	private void aggiornaRichiesta() {
		TreePath tp = tree.getSelectionPath();
		btnDelete.setEnabled(true);
		for (ActionListener al : btnDelete.getActionListeners()) {
			btnDelete.removeActionListener(al);
		}

		Richiesta richiesta = (Richiesta) tp.getPath()[tp.getPath().length - 1];
		btnDelete.addActionListener(deleteRichiestaListener(richiesta
				.getCodice()));

		ArrayList<String> data = richiesta.getData();
		pnlVisualizzaPanel.loadData(data);

		if (richiesta.isSoddisfatta()) {
			pnlVisualizzaPanel.btnAssociaMacchina
					.setText("Rimuovi Associazione");
			pnlVisualizzaPanel
					.addSoddisfaRichiestaListener(liberaRichiestaListener(richiesta
							.getCodice()));
			pnlVisualizzaPanel.setMacchina(richiesta.getMacchina().getCodice()
					+ " " + richiesta.getMacchina().getProduttore() + " "
					+ richiesta.getMacchina().getModello());
		} else {
			pnlVisualizzaPanel
					.addLiberaRichiestaListener(associaMacchinaListener(richiesta
							.getCodice()));
			pnlVisualizzaPanel.btnAssociaMacchina
					.setText("Aggiungi Associazione");
		}

	}

}
