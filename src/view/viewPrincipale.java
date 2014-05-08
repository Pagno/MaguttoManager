package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

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

import model.ModelInterface;

public class viewPrincipale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6003480805602305099L;
	private JPanel contentPane,leftMenu;
	private JTable table;
	private JScrollPane scrollpane;
	private MyTableModel dataModel;
	private ModelInterface model;
	private JButton btnViewGru,btnViewRuspa,btnViewCamion,btnViewEscavatore;
	/**
	 * Create xthe frame.
	 * @throws InterruptedException 
	 */
	public viewPrincipale(ModelInterface m) {
		model=m;
		
		
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
		
		scrollpane = new JScrollPane(table); 
		contentPane.add(scrollpane, BorderLayout.CENTER);
		setVisible(true);


		
	}
	
	private void leftMenu(){
		leftMenu =new JPanel();
		leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
		btnViewGru = new JButton("Gru");//btnViewGru.setAlignmentY(Component.CENTER_ALIGNMENT);
		btnViewRuspa = new JButton("Ruspa");//btnViewRuspa.setAlignmentY(Component.CENTER_ALIGNMENT);
		btnViewCamion = new JButton("Camion");//btnViewCamion.setAlignmentY(Component.CENTER_ALIGNMENT);
		btnViewEscavatore = new JButton("Escavatore");//btnViewEscavatore.setAlignmentY(Component.CENTER_ALIGNMENT);
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
	
	public void addButtonGruListener(ActionListener act){		btnViewGru.addActionListener(act);}
	public void addButtonRuspaListener(ActionListener act){		btnViewRuspa.addActionListener(act);}
	public void addButtonCamionListener(ActionListener act){		btnViewCamion.addActionListener(act);}
	public void addButtonEscavatoreListener(ActionListener act){		btnViewEscavatore.addActionListener(act);}
	
	
	public void addData(Object[] obj){
    	if(dataModel.addData(obj)); //table.repaint();
    	
    }
}