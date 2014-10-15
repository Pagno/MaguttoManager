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

import view.lavoro.EditLavoro;
import controller.ControllerConnector;

import java.awt.event.WindowAdapter;
import java.lang.reflect.Method;

// 
/**
 * Class MainView.
 */
public class MainView extends JFrame {

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
	public MyTableModel dataModelGru, dataModelRuspa, dataModelCamion,
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
	private JMenuItem itemFileSalva, itemFileCarica, itemFileEsci;

	private ControllerConnector controller;
	private String elimina = "Gru";

	/**
	 * Create frame.
	 */
	public MainView() {
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
		dataModelGru = new MyTableModel(columnNamesGru);
		String[] columnNamesRuspa = { "Produttore", "Modello",
				"Altezza Scarico", "Capacita", "Portata Massima" };
		dataModelRuspa = new MyTableModel(columnNamesRuspa);
		String[] columnNamesCamion = { "Produttore", "Modello", "Lunghezza",
				"Capacita", "Portata Massima" };
		dataModelCamion = new MyTableModel(columnNamesCamion);
		String[] columnNamesEscavatore = { "Produttore", "Modello",
				"Altezza Scarico", "Profondita Scavo", "Capacita",
				"Portata Massima" };
		dataModelEscavatore = new MyTableModel(columnNamesEscavatore);
		String[] columnNamesCantiere = { "Nome", "Indirizzo", "Data Apertura",
				"Data Chiusura", "Priorita" };
		dataModelCantiere = new MyTableModel(columnNamesCantiere);

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
	}

	public void setControllerConnector(ControllerConnector aCtr) {
		controller = aCtr;
	}
	/**
	 * Adds window closing listener.
	 *
	 * @param e
	 *            e
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
	 * Left menu.
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
	 * Gets selected data.
	 *
	 * @return selected data
	 */
	public Object[] getSelectedData() {
		return table.getSelectedRow() == -1 ? null : ((MyTableModel) table
				.getModel()).getRowData(table.getSelectedRow());
	}

	/**
	 * Menu.
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
		itemFileEsci = new JMenuItem("Esci");
		menuFile.add(itemFileEsci);

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
	 * Adds button gru listener.
	 *
	 * @param act
	 *            act
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
					// TODO Auto-generated catch block
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
	 * Adds button gru listener.
	 *
	 * @param act
	 *            act
	 */
	public ActionListener greedyEngineListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GreedyEngineView(mainView, controller);
			}
		};
	}

	// MENU ADD LISTENER
	/**
	 * Adds aggiungi gru listener.
	 *
	 * @param act
	 *            act
	 */
	public ActionListener visualizzaInserimentoGru() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditGru(mainView, controller);
			}
		};
	}
	public ActionListener visualizzaInserimentoEscavatore() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditEscavatore(mainView, controller);
			}
		};
	}
	/**
	 * Adds aggiungi ruspa listener.
	 *
	 * @param act
	 *            act
	 */
	public ActionListener visualizzaInserimentoRuspa() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditRuspa(mainView, controller);
			}
		};
	}

	/**
	 * Adds aggiungi camion listener.
	 *
	 * @param act
	 *            act
	 */
	public ActionListener visualizzaInserimentoCamion() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditCamion(mainView, controller);
			}
		};
	}
	public ActionListener visualizzaInserimentoCantiere() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new InsertCantiere(mainView, controller);
			}
		};
	}
	// MENU FILE LISTENER
	/**
	 * Adds btn salva listener.
	 *
	 * @param act
	 *            act
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
	 * Adds btn carica listener.
	 *
	 * @param act
	 *            act
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
	 * Adds btn esci listener.
	 *
	 * @param act
	 *            act
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
	 * Adds elimina listener.
	 *
	 * @param act
	 *            act
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
						Class<AbstractApplicationController> c = AbstractApplicationController.class;
						if (elimina != "Cantiere")
							m = c.getMethod("eliminaMacchina", Integer.class);
						else
							m = c.getMethod("elimina" + elimina, Integer.class);

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
						new EditGru(mainView,v,controller);
					}else if(elimina=="Camion"){
						new EditCamion(mainView,v, controller);
					}else if(elimina=="Escavatore"){
						new EditEscavatore(mainView,v, controller);
					}else if(elimina=="Ruspa"){
						new EditRuspa(mainView,v, controller);
					}else if(elimina=="Cantiere"){
						new EditLavoro(mainView, controller.getCantiere((Integer)v[0]),controller);
					}
				}
			}
		};
	}

	// GESTIONE DEI TABLEMODEL
	/**
	 * Show gru data.
	 */
	public void showGruData() {
		table.setModel(dataModelGru);
		dataModelRuspa.fireTableDataChanged();
	}

	/**
	 * Show ruspa data.
	 */
	public void showRuspaData() {
		table.setModel(dataModelRuspa);
		dataModelRuspa.fireTableDataChanged();
	}

	/**
	 * Show camion data.
	 */
	public void showCamionData() {
		table.setModel(dataModelCamion);
		dataModelCamion.fireTableDataChanged();
	}

	/**
	 * Show escavatore data.
	 */
	public void showEscavatoreData() {
		table.setModel(dataModelEscavatore);
		dataModelEscavatore.fireTableDataChanged();
	}

	/**
	 * Show cantiere data.
	 */
	public void showCantiereData() {
		table.setModel(dataModelCantiere);
		dataModelCantiere.fireTableDataChanged();
	}

	/**
	 * Removes selected.
	 */
	public void removeSelectedRow() {
		((MyTableModel) table.getModel()).removeData(table.getSelectedRow());
	}

}
