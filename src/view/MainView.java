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
	private JMenuItem itemAddCantiere, itemCollegaMacchina;
	
	/**   item file esci. */
	private JMenuItem itemFileSalva,itemFileCarica,itemFileEsci;
	
	/**
	 * Create   frame.
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
		
	}
	
	/**
	 * Adds   window closing listener.
	 *
	 * @param e   e
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
		itemCollegaMacchina = new JMenuItem("Nuova Associazione");
		menuCantiere.add(itemCollegaMacchina);
		
		
		menuBar.add(menuFile);
		menuBar.add(menuMacchina);
		menuBar.add(menuCantiere);
	}
	
	/**
	 * Adds   exit listener.
	 *
	 * @param act   act
	 */
	public void addExitListener(ActionListener act){
		itemFileEsci.addActionListener(act);
	}
	
	//VIEW LISTENER
	/**
	 * Adds   button gru listener.
	 *
	 * @param act   act
	 */
	public void addButtonGruListener(ActionListener act) {
		btnViewGru.addActionListener(act);
	}
	
	/**
	 * Adds   button ruspa listener.
	 *
	 * @param act   act
	 */
	public void addButtonRuspaListener(ActionListener act) {
		btnViewRuspa.addActionListener(act);
	}
	
	/**
	 * Adds   button camion listener.
	 *
	 * @param act   act
	 */
	public void addButtonCamionListener(ActionListener act) {
		btnViewCamion.addActionListener(act);
	}
	
	/**
	 * Adds   button escavatore listener.
	 *
	 * @param act   act
	 */
	public void addButtonEscavatoreListener(ActionListener act) {
		btnViewEscavatore.addActionListener(act);
	}
	
	/**
	 * Adds   button cantiere listener.
	 *
	 * @param act   act
	 */
	public void addButtonCantiereListener(ActionListener act) {
		btnViewCantiere.addActionListener(act);
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
	public void addBtnSalvaListener(ActionListener act){
		itemFileSalva.addActionListener(act);
	}
	
	/**
	 * Adds   btn carica listener.
	 *
	 * @param act   act
	 */
	public void addBtnCaricaListener(ActionListener act){
		itemFileCarica.addActionListener(act);
	}
	
	/**
	 * Adds   btn esci listener.
	 *
	 * @param act   act
	 */
	public void addBtnEsciListener(ActionListener act){
		itemFileEsci.addActionListener(act);
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
	 * Sets   visible btn modifica.
	 *
	 * @param visible   new visible btn modifica
	 */
	public void setVisibleBtnAssociazioni(boolean visible){
		btnAddLavoro.setVisible(visible);
	}
	
	/**
	 * Removes   selected.
	 */
	public void removeSelected(){
		((MyTableModel)table.getModel()).removeData(table.getSelectedRow());
	}
	
}