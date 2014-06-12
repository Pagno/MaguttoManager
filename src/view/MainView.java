package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
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

import java.awt.event.WindowAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class MainView.
 */
public class MainView extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6003480805602305099L;
	
	/** The left menu. */
	private JPanel contentPane, leftMenu;
	
	/** The table. */
	private JTable table;
	
	/** The scrollpane. */
	private JScrollPane scrollpane;
	
	/** The data model cantiere. */
	public MyTableModel dataModelGru,dataModelRuspa,dataModelCamion,dataModelEscavatore,dataModelCantiere;
	
	/** The btn add associazione. */
	private JButton btnViewGru, btnViewRuspa, btnViewCamion, btnViewEscavatore,btnViewCantiere,btnEdit,btnDelete,btnAddAssociazione;
	
	/** The item add camion. */
	private JMenuItem itemAddGru, itemAddRuspa, itemAddEscavatore,
			itemAddCamion;
	
	/** The item collega macchina. */
	private JMenuItem itemAddCantiere, itemCollegaMacchina;
	
	/** The item file esci. */
	private JMenuItem itemFileSalva,itemFileCarica,itemFileEsci;
	
	/**
	 * Create the frame.
	 */
	public MainView() {

		setTitle("MaguttoManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 365);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		menu();
		leftMenu();

		//SET UP THE DATA MODEL
		String[] columnNamesGru={"Produttore","Modello","Lunghezza Braccio","Altezza Gancio","Portata Massima","Angolo Rotazione"};
		dataModelGru = new MyTableModel(columnNamesGru);
		String[] columnNamesRuspa={"Produttore","Modello","Altezza Scarico","Capacita","Portata Massima"};
		dataModelRuspa = new MyTableModel(columnNamesRuspa);
		String[] columnNamesCamion={"Produttore","Modello","Lunghezza","Capacita","Portata Massima"};
		dataModelCamion = new MyTableModel(columnNamesCamion);
		String[] columnNamesEscavatore={"Produttore","Modello","Altezza Scarico","Profondita Scavo","Capacita","Portata Massima"};
		dataModelEscavatore = new MyTableModel(columnNamesEscavatore);
		String[] columnNamesCantiere={"Nome","Indirizzo","Data Apertura","Data Chiusura"};
		dataModelCantiere = new MyTableModel(columnNamesCantiere);
		
		
		table = new JTable();
		table.setModel(dataModelGru);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		
		
		
		
		
		scrollpane = new JScrollPane(table);
		JPanel center=new JPanel();
		center.setLayout(new BorderLayout(0, 0));
		btnEdit =new JButton("Modifica");
		btnDelete=new JButton("Elimina");
		btnAddAssociazione =new JButton("Aggiungi Associazione");
		btnAddAssociazione.setVisible(false);
		
		center.add(scrollpane, BorderLayout.CENTER);
		JPanel southPanel =new JPanel();
		southPanel.add(btnEdit);
		southPanel.add(btnDelete);
		southPanel.add(btnAddAssociazione);
		center.add(southPanel,BorderLayout.SOUTH);
		contentPane.add(center, BorderLayout.CENTER);
		
	}
	
	/**
	 * Adds the window closing listener.
	 *
	 * @param e the e
	 */
	public void addWindowClosingListener(WindowAdapter e){
		addWindowListener(e);
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
		leftMenu.add(btnViewCantiere);
		contentPane.add(leftMenu, BorderLayout.WEST);
	}

	/**
	 * Gets the selected data.
	 *
	 * @return the selected data
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
		itemCollegaMacchina = new JMenuItem("Nuova Associazione");
		menuCantiere.add(itemCollegaMacchina);
		
		
		menuBar.add(menuFile);
		menuBar.add(menuMacchina);
		menuBar.add(menuCantiere);
	}
	
	/**
	 * Adds the exit listener.
	 *
	 * @param act the act
	 */
	public void addExitListener(ActionListener act){
		itemFileEsci.addActionListener(act);
	}
	
	//VIEW LISTENER
	/**
	 * Adds the button gru listener.
	 *
	 * @param act the act
	 */
	public void addButtonGruListener(ActionListener act) {
		btnViewGru.addActionListener(act);
	}
	
	/**
	 * Adds the button ruspa listener.
	 *
	 * @param act the act
	 */
	public void addButtonRuspaListener(ActionListener act) {
		btnViewRuspa.addActionListener(act);
	}
	
	/**
	 * Adds the button camion listener.
	 *
	 * @param act the act
	 */
	public void addButtonCamionListener(ActionListener act) {
		btnViewCamion.addActionListener(act);
	}
	
	/**
	 * Adds the button escavatore listener.
	 *
	 * @param act the act
	 */
	public void addButtonEscavatoreListener(ActionListener act) {
		btnViewEscavatore.addActionListener(act);
	}
	
	/**
	 * Adds the button cantiere listener.
	 *
	 * @param act the act
	 */
	public void addButtonCantiereListener(ActionListener act) {
		btnViewCantiere.addActionListener(act);
	}

	//MENU ADD LISTENER
	/**
	 * Adds the aggiungi gru listener.
	 *
	 * @param act the act
	 */
	public void addAggiungiGruListener(ActionListener act) {
		itemAddGru.addActionListener(act);
	}
	
	/**
	 * Adds the aggiungi ruspa listener.
	 *
	 * @param act the act
	 */
	public void addAggiungiRuspaListener(ActionListener act) {
		itemAddRuspa.addActionListener(act);
	}
	
	/**
	 * Adds the aggiungi camion listener.
	 *
	 * @param act the act
	 */
	public void addAggiungiCamionListener(ActionListener act) {
		itemAddCamion.addActionListener(act);
	}
	
	/**
	 * Adds the aggiungi escavatore listener.
	 *
	 * @param act the act
	 */
	public void addAggiungiEscavatoreListener(ActionListener act) {
		itemAddEscavatore.addActionListener(act);
	}
	
	/**
	 * Adds the aggiungi cantiere listener.
	 *
	 * @param act the act
	 */
	public void addAggiungiCantiereListener(ActionListener act){
		itemAddCantiere.addActionListener(act);
	}
	
	//MENU FILE LISTENER
	/**
	 * Adds the btn salva listener.
	 *
	 * @param act the act
	 */
	public void addBtnSalvaListener(ActionListener act){
		itemFileSalva.addActionListener(act);
	}
	
	/**
	 * Adds the btn carica listener.
	 *
	 * @param act the act
	 */
	public void addBtnCaricaListener(ActionListener act){
		itemFileCarica.addActionListener(act);
	}
	
	/**
	 * Adds the btn esci listener.
	 *
	 * @param act the act
	 */
	public void addBtnEsciListener(ActionListener act){
		itemFileEsci.addActionListener(act);
	}
	//BOTTOM BUTTON
	/**
	 * Adds the modifica listener.
	 *
	 * @param act the act
	 */
	public void addModificaListener(ActionListener act) {
		for( ActionListener al : btnEdit.getActionListeners() ) {
			btnEdit.removeActionListener( al );
	    }
		btnEdit.addActionListener(act);
	}
	
	/**
	 * Adds the elimina listener.
	 *
	 * @param act the act
	 */
	public void addEliminaListener(ActionListener act) {
		for( ActionListener al : btnDelete.getActionListeners() ) {
			btnDelete.removeActionListener( al );
	    }
		btnDelete.addActionListener(act);
	}
	
	/**
	 * Adds the btn add associzione listener.
	 *
	 * @param act the act
	 */
	public void addBtnAddAssocizioneListener(ActionListener act) {
		for( ActionListener al : btnAddAssociazione.getActionListeners() ) {
			btnAddAssociazione.removeActionListener( al );
	    }
		btnAddAssociazione.addActionListener(act);
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
	 * @param disable the disable
	 */
	public void disableBtnModifica(boolean disable){
		btnEdit.setEnabled(!disable);
	}
	
	/**
	 * Sets the visible btn modifica.
	 *
	 * @param visible the new visible btn modifica
	 */
	public void setVisibleBtnModifica(boolean visible){
		btnAddAssociazione.setVisible(visible);
	}
	
	/**
	 * Removes the selected.
	 */
	public void removeSelected(){
		((MyTableModel)table.getModel()).removeData(table.getSelectedRow());
	}
	
}