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

public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6003480805602305099L;
	private JPanel contentPane, leftMenu;
	private JTable table;
	private JScrollPane scrollpane;
	public MyTableModel dataModelGru,dataModelRuspa,dataModelCamion,dataModelEscavatore,dataModelCantiere;
	private JButton btnViewGru, btnViewRuspa, btnViewCamion, btnViewEscavatore,btnViewCantiere,btnEdit,btnDelete,btnAddAssociazione;
	private JMenuItem itemAddGru, itemAddRuspa, itemAddEscavatore,
			itemAddCamion;
	private JMenuItem itemAddCantiere, itemCollegaMacchina;
	private JMenuItem itemFileEsci;
	
	/**
	 * Create the frame.
	 * 
	 * @throws InterruptedException
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
		String[] columnNamesRuspa={"Produttore","Modello","Altezza Scarico","Capacit��","Portata Massima"};
		dataModelRuspa = new MyTableModel(columnNamesRuspa);
		String[] columnNamesCamion={"Produttore","Modello","Lunghezza","Capacita","Portata Massima"};
		dataModelCamion = new MyTableModel(columnNamesCamion);
		String[] columnNamesEscavatore={"Produttore","Modello","Altezza Scarico","Profondita Scavo","Capacit��","Portata Massima"};
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
	public void addWindowClosingListener(WindowAdapter e){
		addWindowListener(e);
	}

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

	public Object[] getSelectedData(){
		return table.getSelectedRow()==-1?null:((MyTableModel)table.getModel()).getRowData(table.getSelectedRow());
	}
	
	private void menu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// MENUBAR
		JMenu menuFile = new JMenu("File");
		JMenu menuMacchina = new JMenu("Macchine");
		JMenu menuCantiere = new JMenu("Cantiere");

		// FILE
		JMenuItem itemFileCarica = new JMenuItem("Carica");
		menuFile.add(itemFileCarica);
		JMenuItem itemFileSalva = new JMenuItem("Salva");
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
	public void addExitListener(ActionListener act){
		itemFileEsci.addActionListener(act);
	}
	
	//VIEW LISTENER
	public void addButtonGruListener(ActionListener act) {
		btnViewGru.addActionListener(act);
	}
	public void addButtonRuspaListener(ActionListener act) {
		btnViewRuspa.addActionListener(act);
	}
	public void addButtonCamionListener(ActionListener act) {
		btnViewCamion.addActionListener(act);
	}
	public void addButtonEscavatoreListener(ActionListener act) {
		btnViewEscavatore.addActionListener(act);
	}
	public void addButtonCantiereListener(ActionListener act) {
		btnViewCantiere.addActionListener(act);
	}

	//ADD LISTENER
	public void addAggiungiGruListener(ActionListener act) {
		itemAddGru.addActionListener(act);
	}
	public void addAggiungiRuspaListener(ActionListener act) {
		itemAddRuspa.addActionListener(act);
	}
	public void addAggiungiCamionListener(ActionListener act) {
		itemAddCamion.addActionListener(act);
	}
	public void addAggiungiEscavatoreListener(ActionListener act) {
		itemAddEscavatore.addActionListener(act);
	}
	public void addAggiungiCantiereListener(ActionListener act){
		itemAddCantiere.addActionListener(act);
	}
	
	//BOTTOM BUTTON
	public void addModificaListener(ActionListener act) {
		for( ActionListener al : btnEdit.getActionListeners() ) {
			btnEdit.removeActionListener( al );
	    }
		btnEdit.addActionListener(act);
	}
	public void addEliminaListener(ActionListener act) {
		for( ActionListener al : btnDelete.getActionListeners() ) {
			btnDelete.removeActionListener( al );
	    }
		btnDelete.addActionListener(act);
	}
	public void addBtnAddAssocizioneListener(ActionListener act) {
		for( ActionListener al : btnAddAssociazione.getActionListeners() ) {
			btnAddAssociazione.removeActionListener( al );
	    }
		btnAddAssociazione.addActionListener(act);
	}
	
	
	
	

	
	
	//GESTIONE DEI TABLEMODEL
	public void showGruData(){
		table.setModel(dataModelGru);
		dataModelRuspa.fireTableDataChanged();
	}
	public void showRuspaData(){
		table.setModel(dataModelRuspa);
		dataModelRuspa.fireTableDataChanged();
	}
	public void showCamionData(){
		table.setModel(dataModelCamion);
		dataModelCamion.fireTableDataChanged();
	}
	public void showEscavatoreData(){
		table.setModel(dataModelEscavatore);
		dataModelEscavatore.fireTableDataChanged();
	}
	public void showCantiereData(){
		table.setModel(dataModelCantiere);
		dataModelCantiere.fireTableDataChanged();
	}

	
	public void disableBtnModifica(boolean disable){
		btnEdit.setEnabled(!disable);
	}
	public void setVisibleBtnModifica(boolean visible){
		btnAddAssociazione.setVisible(visible);
	}
	public void removeSelected(){
		((MyTableModel)table.getModel()).removeData(table.getSelectedRow());
	}
	
}