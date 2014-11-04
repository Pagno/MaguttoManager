package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import view.cantiere.ViewCantiere;
import view.greedy.ViewGreedyEngine;
import view.lavoro.ViewLavoro;
import view.macchina.ViewCamion;
import view.macchina.ViewEscavatore;
import view.macchina.ViewGru;
import view.macchina.ViewRuspa;
import controller.ControllerConnector;
import controller.ControllerInterface;

import java.awt.event.WindowAdapter;
import java.lang.reflect.Method;

/**
 * Class MainView.
 * 
 * @author Matteo Pagnoncelli
 * @author Mauro Valota
 */
public class MainView extends JFrame {

	/** The main view. */
	MainView mainView;
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 6003480805602305099L;

	/** left menu. */
	private JPanel contentPane, leftMenu;

	/** table. */
	private JTable table;

	/** scrollpane. */
	private JScrollPane scrollpane;

	/** data model cantiere. */
	public MainViewTableModel dataModelGru, dataModelRuspa, dataModelCamion,
			dataModelEscavatore, dataModelCantiere;

	/** btn add associazione. */
	private JButton btnViewGru, btnViewRuspa, btnViewCamion, btnViewEscavatore,
			btnViewCantiere, btnEdit, btnDelete;

	/** item add camion. */
	private JMenuItem itemAddGru, itemAddRuspa, itemAddEscavatore,
			itemAddCamion;

	/** item collega macchina. */
	private JMenuItem itemAddCantiere, itemGreedyEngine;

	/** item file esci. */
	private JMenuItem itemFileSalva, itemFileCarica;

	/** The controller. */
	private ControllerInterface controller;
	
	/** The elimina. */
	private String elimina = "Gru";

	/**
	 * Crea il frame.
	 *
	 * @param aCtr istanza del ControllerInterface per interagire con i datidell'applicazione.
	 */
	public MainView(ControllerInterface aCtr) {
		controller = aCtr;
		setTitle("MaguttoManager");
		mainView = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 365);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		menu();
		leftMenu();

		// SET UP DATA MODEL
		String[] columnNamesGru = { "Produttore", "Modello",
				"Lunghezza Braccio", "Altezza Gancio", "Portata Massima",
				"Angolo Rotazione" };
		dataModelGru = new MainViewTableModel(columnNamesGru);
		String[] columnNamesRuspa = { "Produttore", "Modello",
				"Altezza Scarico", "Capacita", "Portata Massima" };
		dataModelRuspa = new MainViewTableModel(columnNamesRuspa);
		String[] columnNamesCamion = { "Produttore", "Modello", "Lunghezza",
				"Capacita", "Portata Massima" };
		dataModelCamion = new MainViewTableModel(columnNamesCamion);
		String[] columnNamesEscavatore = { "Produttore", "Modello",
				"Altezza Scarico", "Profondita Scavo", "Capacita",
				"Portata Massima" };
		dataModelEscavatore = new MainViewTableModel(columnNamesEscavatore);
		String[] columnNamesCantiere = { "Nome", "Indirizzo", "Data Apertura",
				"Data Chiusura", "Priorita" };
		dataModelCantiere = new MainViewTableModel(columnNamesCantiere);

		table = new JTable();
		table.setName("table");
		table.setModel(dataModelGru);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		scrollpane = new JScrollPane(table);
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout(0, 0));

		btnEdit =new JButton("Modifica");
		btnEdit.setName("modifica");
		btnDelete=new JButton("Elimina");
		btnDelete.setName("elimina");
		
		center.add(scrollpane, BorderLayout.CENTER);
		JPanel southPanel = new JPanel();
		southPanel.add(btnEdit);
		southPanel.add(btnDelete);
		center.add(southPanel, BorderLayout.SOUTH);
		contentPane.add(center, BorderLayout.CENTER);
		listener();
		setObserver();
		controller.caricaDatiListener();
		setVisible(true);
	}
	
	/**
	 * Assegna gli observer del model.
	 */
	private void setObserver(){
		controller.aggiungiGruObserver(dataModelGru);
		controller.aggiungiRuspaObserver(dataModelRuspa);
		controller.aggiungiCamionObserver(dataModelCamion);	
		controller.aggiungiEscavatoreObserver(dataModelEscavatore);	
		controller.aggiungiCantiereObserver(dataModelCantiere);		
	}

	/**
	 * Adds window closing listener.
	 *
	 * @return WindowAdapter
	 */
	private WindowAdapter addWindowClosingListener() {
		return new WindowAdapter() {
			public void windowClosing(WindowEvent w) {
				controller.exitManager();
				dispose();
			}
		};
	}

	/**
	 * Inizializza il menu laterale sinitro.
	 */
	private void leftMenu() {
		leftMenu = new JPanel();
		leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
		btnViewGru = new JButton("Gru");
		btnViewGru.setName("Gru");
		btnViewRuspa = new JButton("Ruspa");
		btnViewRuspa.setName("Ruspa");
		btnViewCamion = new JButton("Camion");
		btnViewCamion.setName("Camion");
		btnViewEscavatore = new JButton("Escavatore");
		btnViewEscavatore.setName("Escavatore");
		btnViewCantiere = new JButton("Cantiere");
		btnViewCantiere.setName("Cantiere");
		leftMenu.add(btnViewGru);
		leftMenu.add(btnViewRuspa);
		leftMenu.add(btnViewCamion);
		leftMenu.add(btnViewEscavatore);
		leftMenu.add(btnViewCantiere);
		contentPane.add(leftMenu, BorderLayout.WEST);
	}

	/**
	 * Ritorna i dati relativi alla riga selezionata nella tabella principale
	 *
	 * @return Dati relativi alla riga selezionata nella tabella principale
	 */
	public Object[] getSelectedData() {
		return table.getSelectedRow() == -1 ? null : ((MainViewTableModel) table
				.getModel()).getRowData(table.getSelectedRow());
	}

	/**
	 * Crea il menu della view Principale.
	 */
	private void menu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// MENUBAR
		JMenu menuFile = new JMenu("File");
		JMenu menuMacchina = new JMenu("Macchine");
		JMenu menuCantiere = new JMenu("Cantiere");

		// FILE
		itemFileCarica = new JMenuItem("Carica");
		menuFile.add(itemFileCarica);
		itemFileSalva = new JMenuItem("Salva");
		menuFile.add(itemFileSalva);

		// AGGIUNGI
		itemAddGru = new JMenuItem("Aggiungi Gru");
		itemAddGru.setName("aggiungiGru");
		menuMacchina.add(itemAddGru);
		menuMacchina.addSeparator();
		itemAddRuspa = new JMenuItem("Aggiungi Ruspa");
		itemAddRuspa.setName("aggiungiRuspa");
		menuMacchina.add(itemAddRuspa);
		menuMacchina.addSeparator();
		itemAddCamion = new JMenuItem("Aggiungi Camion");
		itemAddCamion.setName("aggiungiCamion");
		menuMacchina.add(itemAddCamion);
		menuMacchina.addSeparator();
		itemAddEscavatore = new JMenuItem("Aggiungi Escavatore");
		itemAddEscavatore.setName("aggiungiEscavatore");
		menuMacchina.add(itemAddEscavatore);

		// CANTIERE
		itemAddCantiere = new JMenuItem("Aggiungi Cantiere");
		itemAddCantiere.setName("aggiungiCantiere");
		menuCantiere.add(itemAddCantiere);
		menuCantiere.addSeparator();
		itemGreedyEngine = new JMenuItem("Associa Macchina in modo automatico");

		itemGreedyEngine.setName("itemAssocia");

		itemGreedyEngine.addActionListener(greedyEngineListener());
		menuCantiere.add(itemGreedyEngine);

		menuBar.add(menuFile);
		menuBar.add(menuMacchina);
		menuBar.add(menuCantiere);
	}

	// VIEW LISTENER
	/**
	 * Assegna i diversi listener ai bottone della view.
	 */
	public void listener() {
		btnViewGru.addActionListener(visualizzaElenco(this));
		btnViewRuspa.addActionListener(visualizzaElenco(this));
		btnViewEscavatore.addActionListener(visualizzaElenco(this));
		btnViewCamion.addActionListener(visualizzaElenco(this));
		btnViewCantiere.addActionListener(visualizzaElenco(this));

		// zZ
		itemFileSalva.addActionListener(addBtnSalvaListener());
		addWindowListener(addWindowClosingListener());
		itemFileCarica.addActionListener(addBtnCaricaListener());
		itemAddGru.addActionListener(visualizzaInserimentoGru());
		itemAddRuspa.addActionListener(visualizzaInserimentoRuspa());
		itemAddCamion.addActionListener(visualizzaInserimentoCamion());
		itemAddEscavatore.addActionListener(visualizzaInserimentoEscavatore());
		itemAddCantiere.addActionListener(visualizzaInserimentoCantiere());
		btnDelete.addActionListener(addEliminaListener());
		btnEdit.addActionListener(addBtnModificaListener());
	}

	/**
	 * Restituisce L'ActionLisener per gestire il click la visualizzazione dei dati caricati nell'
	 * applicazione.
	 *
	 * @param mainView Visualizza i dati caricati nell'applicazione.
	 * @return ActionListener
	 */
	public ActionListener visualizzaElenco(final MainView mainView) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Method m;
				try {
					Class<MainView> c = MainView.class;
					m = c.getMethod("show"
							+ ((JButton) arg0.getSource()).getText() + "Data");
					m.invoke(mainView);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// disableBtnModifica(false);
				elimina = ((JButton) arg0.getSource()).getName();
				btnEdit.setText("Modifica");
				if (((JButton) arg0.getSource()).equals(btnViewCantiere)) {
					elimina = "Cantiere";
					btnEdit.setText("Gestisci Cantiere");
				}
			}
		};
	}

	/**
	 * Evento legato alla pressione al menu greedyEngine.
	 *
	 * @return ActionListener
	 */
	public ActionListener greedyEngineListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewGreedyEngine(mainView, controller);
			}
		};
	}

	// MENU ADD LISTENER
	/**
	 *Evento legato alla pressione al menu Aggiungi Gru.
	 *
	 * @return ActionListener
	 */
	public ActionListener visualizzaInserimentoGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewGru(mainView, controller);
			}
		};
	}
	
	/**
	 * Evento legato alla pressione al menu Aggiungi Escavatore.
	 *
	 * @return ActionListener
	 */
	public ActionListener visualizzaInserimentoEscavatore() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewEscavatore(mainView, controller);
			}
		};
	}
	
	/**
	 * Evento legato alla pressione al menu Aggiungi Ruspa.
	 *
	 * @return ActionListener
	 */
	public ActionListener visualizzaInserimentoRuspa() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewRuspa(mainView, controller);
			}
		};
	}

	/**
	 *  Evento legato alla pressione al menu Aggiungi Camion.
	 *
	 * @return ActionListener
	 */
	public ActionListener visualizzaInserimentoCamion() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewCamion(mainView, controller);
			}
		};
	}
	
	/**
	 * Evento legato alla pressione al menu Aggiungi Cantiere.
	 *
	 * @return ActionListener
	 */
	public ActionListener visualizzaInserimentoCantiere() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewCantiere(mainView, controller);
			}
		};
	}
	// MENU FILE LISTENER
	/**
	 * Evento legato alla pressione al menu Salva.
	 *
	 * @return ActionListener
	 */
	public ActionListener addBtnSalvaListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.salvaDatiListener();
			}
		};
	}

	/**
	 * Evento legato alla pressione al menu Carica.
	 *
	 * @return ActionListener
	 */
	public ActionListener addBtnCaricaListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dataModelCamion.resetData();
				dataModelRuspa.resetData();
				dataModelEscavatore.resetData();
				dataModelGru.resetData();
				dataModelCantiere.resetData();
				controller.caricaDatiListener();
			}
		};
	}

	/**
	 * Evento legato alla pressione al menu Esci.
	 *
	 * @return ActionListener
	 */
	public ActionListener addBtnEsciListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.exitManager();
			}
		};
	}

	/**
	 * Evento legato alla pressione del bottone Elimina.
	 *
	 * @return ActionListener
	 */
	private ActionListener addEliminaListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] v = getSelectedData();
				if (v == null) {
					JOptionPane.showMessageDialog(null,
							"Selezionare la riga da eliminare.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Method m;
					try {
						System.out.println(elimina);
						Class<ControllerConnector> c = ControllerConnector.class;
						if (elimina != "Cantiere"){
							m = c.getMethod("eliminaMacchina", Integer.class);
						}else{
							m = c.getMethod("elimina" + elimina, Integer.class);
						}
						Boolean deleted = (Boolean) m.invoke(controller,
								(Integer) v[0]);
						if (deleted) {
							removeSelectedRow();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

	/**
	 * Evento legato alla pressione del bottone Modifica.
	 *
	 * @return ActionListener 
	 */
	private ActionListener addBtnModificaListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] v = getSelectedData();
				if (v == null) {
					JOptionPane.showMessageDialog(null,
							"Selezionare la riga da modificare.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if(elimina=="Gru"){
						new ViewGru(mainView,v,controller);
					}else if(elimina=="Camion"){
						new ViewCamion(mainView,v, controller);
					}else if(elimina=="Escavatore"){
						new ViewEscavatore(mainView,v, controller);
					}else if(elimina=="Ruspa"){
						new ViewRuspa(mainView,v, controller);
					}else if(elimina=="Cantiere"){
						new ViewLavoro(mainView, controller.getCantiere((Integer)v[0]),controller);
					}
				}
			}
		};
	}

	// GESTIONE DEI TABLEMODEL
	/**
	 * Mostra nella tabella i dati delle gru caricate.
	 */
	public void showGruData() {
		table.setModel(dataModelGru);
		dataModelRuspa.fireTableDataChanged();
	}

	/**
	 * Mostra nella tabella i dati delle ruspe caricate.
	 */
	public void showRuspaData() {
		table.setModel(dataModelRuspa);
		dataModelRuspa.fireTableDataChanged();
	}

	/**
	 * Mostra nella tabella i dati dei camion caricati.
	 */
	public void showCamionData() {
		table.setModel(dataModelCamion);
		dataModelCamion.fireTableDataChanged();
	}

	/**
	 * Mostra nella tabella i dati degli escavatori caricati
	 */
	public void showEscavatoreData() {
		table.setModel(dataModelEscavatore);
		dataModelEscavatore.fireTableDataChanged();
	}

	/**
	 * Mostra nella tabella i dati dei cantieri caricati.
	 */
	public void showCantiereData() {
		table.setModel(dataModelCantiere);
		dataModelCantiere.fireTableDataChanged();
	}

	/**
	 * Rimuovi l'elemento selezionato nella tabella.
	 */
	public void removeSelectedRow() {
		((MainViewTableModel) table.getModel()).removeData(table.getSelectedRow());
	}

}
