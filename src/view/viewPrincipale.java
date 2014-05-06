package view;

import java.awt.BorderLayout;
import java.awt.Component;

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

public class viewPrincipale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6003480805602305099L;
	private JPanel contentPane;
	private JTable table;
	private JPanel leftMenu;
	private JScrollPane scrollpane;

	/**
	 * Create xthe frame.
	 */
	public viewPrincipale() {
		setTitle("MaguttoManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 365);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		menu();
		leftMenu();
		
		
		MyTableModel modelTable =new MyTableModel();
		table = new JTable(modelTable);
		table.setPreferredScrollableViewportSize( 
		table.getPreferredSize()); 
		// aggiunge la tabella ad uno ScollPane 
		scrollpane = new JScrollPane(table); 
		// aggiunge lo ScrollPane al pannello 

		contentPane.add(scrollpane, BorderLayout.CENTER);
		setVisible(true);
	}
	
	private void leftMenu(){
		leftMenu =new JPanel();
		leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
		JButton btnViewGru = new JButton("Gru");btnViewGru.setAlignmentY(Component.BOTTOM_ALIGNMENT);
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
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnAggiungi = new JMenu("Aggiungi");
		menuBar.add(mnAggiungi);
		
		JMenuItem mntmGru = new JMenuItem("Gru");
		mnAggiungi.add(mntmGru);
		
		
	}
}
