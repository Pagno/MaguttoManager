package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import controller.VisualizzaCtr;

public class viewPrincipale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6003480805602305099L;
	private JPanel contentPane;
	private static JTable table;
	private JPanel leftMenu;
	private JScrollPane scrollpane;
	private MyTableModel dataModel;
	private VisualizzaCtr controller;

	/**
	 * Create xthe frame.
	 * @throws InterruptedException 
	 */
	public viewPrincipale(VisualizzaCtr ctr) {
		controller=ctr;
		
		
		setTitle("MaguttoManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 365);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		menu();
		leftMenu();
		
		
		dataModel =new MyTableModel();
		table = new JTable();
		table.setModel(dataModel);

		table.setPreferredScrollableViewportSize( 
		table.getPreferredSize()); 
		table.getSelectionModel().addListSelectionListener(controller);
		
		scrollpane = new JScrollPane(table); 
		contentPane.add(scrollpane, BorderLayout.CENTER);
		setVisible(true);
		add();


		
	}
	public void add(){
		Object[] v1={"Matteo", "Pagnoncelli","niente", new Integer(5), new Boolean(false)};   
		dataModel.addData(v1);
		table.repaint();
	}
	
	private void leftMenu(){
		leftMenu =new JPanel();
		leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
		JButton btnViewGru = new JButton("Gru");btnViewGru.addActionListener(controller);
		JButton btnViewRuspa = new JButton("Ruspa");btnViewRuspa.setAlignmentY(Component.CENTER_ALIGNMENT);
		JButton btnViewCamion = new JButton("Camion");btnViewCamion.setAlignmentY(Component.CENTER_ALIGNMENT);
		JButton btnViewEscavatore = new JButton("Escavatore");btnViewEscavatore.setAlignmentY(Component.CENTER_ALIGNMENT);
		leftMenu.add(btnViewGru);
		leftMenu.add(btnViewRuspa);
		leftMenu.add(btnViewCamion);
		leftMenu.add(btnViewEscavatore);
		contentPane.add(leftMenu, BorderLayout.WEST);
	}
	
	private void menu(){
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//MENUBAR
		JMenu menuFile = new JMenu("File");
		JMenu menuAggiungi = new JMenu("Aggiungi");
		
		
		//FILE
		JMenuItem itemFileCarica = new JMenuItem("Carica");
		menuFile.add(itemFileCarica);
		JMenuItem itemFileSalva = new JMenuItem("Salva");
		menuFile.add(itemFileSalva);
		JMenuItem itemFileEsci = new JMenuItem("Esci");
		menuFile.add(itemFileEsci);
		

		
		//AGGIUNGI
		JMenuItem itemAddGru = new JMenuItem("Gru");
		menuAggiungi.add(itemAddGru);
		JMenuItem iteAddRuspa = new JMenuItem("Ruspa");
		menuAggiungi.add(iteAddRuspa);
		JMenuItem itemAddEscavatore = new JMenuItem("Escavatore");
		menuAggiungi.add(itemAddEscavatore);
		JMenuItem iteAddCamion = new JMenuItem("Camion");
		menuAggiungi.add(iteAddCamion);
		
		

		menuBar.add(menuFile);
		menuBar.add(menuAggiungi);
	}
	
	public static int getSingOrdSelected(){
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		int indexRow = table.getSelectedRow();						// posizione della riga selezionata
		int a = (Integer) model.getValueAt(indexRow, 0);					// estraggo il valore I
		System.out.println(a);
		model.removeRow(indexRow);
		table.repaint();
		return a;
	}
	
	public void addData(Object[] obj){
    	dataModel.addData(obj);
    	table.repaint();
    }
}