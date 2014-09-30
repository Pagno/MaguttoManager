package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import controller.AbstractApplicationController;

import java.awt.event.WindowAdapter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 
/**
 *   Class MainView.
 */
public class MainView extends JFrame {

	/**   Constant serialVersionUID. */
	private static final long serialVersionUID = 6003480805602305099L;
	
	/**   left menu. */
	private JPanel contentPane, leftMenu;
	
	/**   table. */
	private JTable table;
	
	/**   scrollpane. */
	private JScrollPane scrollpane;
	
	/**   data model cantiere. */
	public MyTableModel dataModelGru,dataModelRuspa,dataModelCamion,dataModelEscavatore,dataModelCantiere;
	
	/**   btn add associazione. */
	private JButton btnViewGru, btnViewRuspa, btnViewCamion, btnViewEscavatore,btnViewCantiere,btnEdit,btnDelete,btnAddLavoro;
	
	/**   item add camion. */
	private JMenuItem itemAddGru, itemAddRuspa, itemAddEscavatore, itemAddCamion;
	
	/**   item collega macchina. */
	private JMenuItem itemAddCantiere, itemGreedyEngine;
	
	/**   item file esci. */
	private JMenuItem itemFileSalva,itemFileCarica,itemFileEsci;
	
	private AbstractApplicationController appController;
	/**
	 * Create   frame.
	 */
	public MainView(AbstractApplicationController aCtr) {
		setTitle("MaguttoManager");
		appController=aCtr;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 365);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		menu();
		leftMenu();

		//SET UP   DATA MODEL
		String[] columnNamesGru={"Produttore","Modello","Lunghezza Braccio","Altezza Gancio","Portata Massima","Angolo Rotazione"};
		dataModelGru = new MyTableModel(columnNamesGru);
		String[] columnNamesRuspa={"Produttore","Modello","Altezza Scarico","Capacita","Portata Massima"};
		dataModelRuspa = new MyTableModel(columnNamesRuspa);
		String[] columnNamesCamion={"Produttore","Modello","Lunghezza","Capacita","Portata Massima"};
		dataModelCamion = new MyTableModel(columnNamesCamion);
		String[] columnNamesEscavatore={"Produttore","Modello","Altezza Scarico","Profondita Scavo","Capacita","Portata Massima"};
		dataModelEscavatore = new MyTableModel(columnNamesEscavatore);
		String[] columnNamesCantiere={"Nome","Indirizzo","Data Apertura","Data Chiusura","Priorita"};
		dataModelCantiere = new MyTableModel(columnNamesCantiere);
		
		
		table = new JTable();
		table.setModel(dataModelGru);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		
		scrollpane = new JScrollPane(table);
		JPanel center=new JPanel();
		center.setLayout(new BorderLayout(0, 0));
		btnEdit =new JButton("Modifica");
		btnDelete=new JButton("Elimina");
		btnAddLavoro =new JButton("Lavori");
		btnAddLavoro.setVisible(false);
		
		center.add(scrollpane, BorderLayout.CENTER);
		JPanel southPanel =new JPanel();
		southPanel.add(btnEdit);
		southPanel.add(btnDelete);
		southPanel.add(btnAddLavoro);
		center.add(southPanel,BorderLayout.SOUTH);
		contentPane.add(center, BorderLayout.CENTER);
		listener();
	}
	
	/**
	 * Adds   window closing listener.
	 *
	 * @param e   e
	 */
	private WindowListener addWindowClosingListener(WindowAdapter e){
		return new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				appController.chiusuraProgramma();
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
		btnViewRuspa = new JButton("Ruspa");
		btnViewCamion = new JButton("Camion");
		btnViewEscavatore = new JButton("Escavatore");
		btnViewCantiere = new JButton("Cantiere");
		leftMenu.add(btnViewGru);
		leftMenu.add(btnViewRuspa);
		leftMenu.add(btnViewCamion);
		leftMenu.add(btnViewEscavatore);
		leftMenu.add(btnViewCantiere);
		contentPane.add(leftMenu, BorderLayout.WEST);
	}

	/**
	 * Gets   selected data.
	 *
	 * @return   selected data
	 */
	public Object[] getSelectedData(){
		return table.getSelectedRow()==-1?null:((MyTableModel)table.getModel()).getRowData(table.getSelectedRow());
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
		menuMacchina.add(itemAddGru);
		menuMacchina.addSeparator();
		itemAddRuspa = new JMenuItem("Aggiungi Ruspa");
		menuMacchina.add(itemAddRuspa);
		menuMacchina.addSeparator();
		itemAddCamion = new JMenuItem("Aggiungi Camion");
		menuMacchina.add(itemAddCamion);
		menuMacchina.addSeparator();
		itemAddEscavatore = new JMenuItem("Aggiungi Escavatore");
		menuMacchina.add(itemAddEscavatore);

		// CANTIERE
		itemAddCantiere = new JMenuItem("Aggiungi Cantiere");
		menuCantiere.add(itemAddCantiere);
		menuCantiere.addSeparator();
		itemGreedyEngine = new JMenuItem("Associa Macchina in modo automatico");
		menuCantiere.add(itemGreedyEngine);
		
		
		menuBar.add(menuFile);
		menuBar.add(menuMacchina);
		menuBar.add(menuCantiere);
	}
	
	
	//VIEW LISTENER
	public void listener(){
		btnViewGru.addActionListener(visualizzaElencoGru(this));
		btnViewRuspa.addActionListener(visualizzaElencoGru(this));
		btnViewEscavatore.addActionListener(visualizzaElencoGru(this));
		btnViewCamion.addActionListener(visualizzaElencoGru(this));
		btnViewCantiere.addActionListener(visualizzaElencoGru(this));
		
		//zZ
		itemFileSalva.addActionListener(addBtnSalvaListener());
		addWindowListener(addWindowClosingListener());
		itemFileCarica.addActionListener(addBtnCaricaListener());
	}
	/**
	 * Adds   button gru listener.
	 *
	 * @param act   act
	 */
	public ActionListener visualizzaElencoGru(final MainView mainView) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showGruData();
				Method m;
				try {
					Class c=MainView.class;
					m = c.getMethod("show"+((JButton)arg0.getSource()).getText()+"Data");
					m.invoke(mainView);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				//disableBtnModifica(false);
				btnAddLavoro.setVisible(false);
				if(((JButton)arg0.getSource()).equals(btnViewCantiere)){
					btnAddLavoro.setVisible(true);
				}
			}
		};
	}
	/**
	 * Adds   button gru listener.
	 *
	 * @param act   act
	 */
	public void addGreedyEngineListener(ActionListener act) {
		itemGreedyEngine.addActionListener(act);
	}
	//MENU ADD LISTENER
	/**
	 * Adds   aggiungi gru listener.
	 *
	 * @param act   act
	 */
	public void addAggiungiGruListener(ActionListener act) {
		itemAddGru.addActionListener(act);
	}
	
	/**
	 * Adds   aggiungi ruspa listener.
	 *
	 * @param act   act
	 */
	public void addAggiungiRuspaListener(ActionListener act) {
		itemAddRuspa.addActionListener(act);
	}
	
	/**
	 * Adds   aggiungi camion listener.
	 *
	 * @param act   act
	 */
	public void addAggiungiCamionListener(ActionListener act) {
		itemAddCamion.addActionListener(act);
	}
	
	/**
	 * Adds   aggiungi escavatore listener.
	 *
	 * @param act   act
	 */
	public void addAggiungiEscavatoreListener(ActionListener act) {
		itemAddEscavatore.addActionListener(act);
	}
	
	/**
	 * Adds   aggiungi cantiere listener.
	 *
	 * @param act   act
	 */
	public void addAggiungiCantiereListener(ActionListener act){
		itemAddCantiere.addActionListener(act);
	}
	
	//MENU FILE LISTENER
	/**
	 * Adds   btn salva listener.
	 *
	 * @param act   act
	 */
	public ActionListener addBtnSalvaListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appController.salvaDatiListener();
			}
		};
	}
	
	/**
	 * Adds   btn carica listener.
	 *
	 * @param act   act
	 */
	public ActionListener addBtnCaricaListener(){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dataModelCamion.resetData();
				dataModelRuspa.resetData();
				dataModelEscavatore.resetData();
				dataModelGru.resetData();
				dataModelCantiere.resetData();
				appController.caricaDatiListener();
			}
		};
	}
	
	/**
	 * Adds   btn esci listener.
	 *
	 * @param act   act
	 */
	public ActionListener addBtnEsciListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appController.exitManager();
			}
		};
	}
	//BOTTOM BUTTON
	/**
	 * Adds   modifica listener.
	 *
	 * @param act   act
	 */
	public void addModificaListener(ActionListener act) {
		for( ActionListener al : btnEdit.getActionListeners() ) {
			btnEdit.removeActionListener( al );
	    }
		btnEdit.addActionListener(act);
	}
	
	/**
	 * Adds   elimina listener.
	 *
	 * @param act   act
	 */
	public void addEliminaListener(ActionListener act) {
		for( ActionListener al : btnDelete.getActionListeners() ) {
			btnDelete.removeActionListener( al );
	    }
		btnDelete.addActionListener(act);
	}
	
	/**
	 * Adds   btn add associzione listener.
	 *
	 * @param act   act
	 */
	public void addBtnAddLavoroListener(ActionListener act) {
		for( ActionListener al : btnAddLavoro.getActionListeners() ) {
			btnAddLavoro.removeActionListener( al );
	    }
		btnAddLavoro.addActionListener(act);
	}
	
	
	
	

	
	
	//GESTIONE DEI TABLEMODEL
	/**
	 * Show gru data.
	 */
	public void showGruData(){
		table.setModel(dataModelGru);
		dataModelRuspa.fireTableDataChanged();
	}
	
	/**
	 * Show ruspa data.
	 */
	public void showRuspaData(){
		table.setModel(dataModelRuspa);
		dataModelRuspa.fireTableDataChanged();
	}
	
	/**
	 * Show camion data.
	 */
	public void showCamionData(){
		table.setModel(dataModelCamion);
		dataModelCamion.fireTableDataChanged();
	}
	
	/**
	 * Show escavatore data.
	 */
	public void showEscavatoreData(){
		table.setModel(dataModelEscavatore);
		dataModelEscavatore.fireTableDataChanged();
	}
	
	/**
	 * Show cantiere data.
	 */
	public void showCantiereData(){
		table.setModel(dataModelCantiere);
		dataModelCantiere.fireTableDataChanged();
	}

	
	/**
	 * Disable btn modifica.
	 *
	 * @param disable   disable
	 */
	public void disableBtnModifica(boolean disable){
		btnEdit.setEnabled(!disable);
	}
	/**
	 * Removes   selected.
	 */
	public void removeSelected(){
		((MyTableModel)table.getModel()).removeData(table.getSelectedRow());
	}
	
}