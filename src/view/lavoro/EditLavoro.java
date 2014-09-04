package view.lavoro;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.hamcrest.core.IsInstanceOf;

import view.lavoro.panel.CantierePanel;
import view.lavoro.panel.LavoroPanel;
import view.lavoro.panel.RichiestaPanel;
import view.lavoro.panel.VisualizzaRichiestaPanel;

import com.toedter.calendar.JDateChooser;

public class EditLavoro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2004768729465641055L;
	private JPanel contentPane,cardPanel;
	private RichiestaPanel pnlAddRichiesta;
	private LavoroPanel pnlLavoro;
	private CantierePanel pnlCantiere;
	private VisualizzaRichiestaPanel pnlVisualizzaPanel;
	
	//MODELLI TABELLA JTREE
	public treeModel treeModel;
	private JTree tree;
	private JScrollPane scrollpane;
	private Object[] datiCantiere;

	//LAVORO PANEL
	/**
	 * Create the dialog.
	 */
	public EditLavoro(JFrame view,Object[] datiCantiere) {
		super(view);
		setTitle("Edit Cantiere");
		this.datiCantiere=datiCantiere;
		setBounds(0, 0, 700, 500);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("Chiudi");
		okButton.setActionCommand("Chiudi");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		

		
		//CARICO I PANNELLI
		cardPanel= new JPanel(new CardLayout());
		pnlCantiere= new CantierePanel();
		pnlCantiere.setDatiCantiere(datiCantiere);
		pnlLavoro= new LavoroPanel(pnlCantiere.getDataInizioCantiere(),pnlCantiere.getDataFineCantiere());
		pnlAddRichiesta= new RichiestaPanel();
		pnlVisualizzaPanel=new VisualizzaRichiestaPanel();
		
		
		cardPanel.add(pnlLavoro,"lavoro");
		cardPanel.add(pnlAddRichiesta,"richiesta");
		cardPanel.add(pnlCantiere,"cantiere");
		cardPanel.add(pnlVisualizzaPanel,"visualizza");

		contentPane.add(cardPanel,BorderLayout.CENTER);
		treeModel = new treeModel(datiCantiere[1].toString()); 
		addNode nuovoLavoro=new addNode("Aggiungi Lavoro");
		
		tree=new JTree(treeModel);
		tree.setSize(200, 500);

		addNodeRenderer renderer = new addNodeRenderer();
	    tree.setCellRenderer(renderer);
		scrollpane = new JScrollPane(tree,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBounds(0,0,200, 500);
		contentPane.add(scrollpane,BorderLayout.WEST);

		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		
		cl.show(cardPanel,"cantiere");
	
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				doMouseClicked(me);
			}
		});
		setVisible(true);
	}
	
	//Aggiornamento dei pannelli
	void doMouseClicked(MouseEvent me) {
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		CardLayout  cl = (CardLayout)(cardPanel.getLayout());
		if (tp!=null && tp.getPath().length==1){
			cl.show(cardPanel,"cantiere");
		}
		else if (tp!=null && tp.getPath().length==2){
			//visualizzo il pannello Lavoro
			cl.show(cardPanel,"lavoro");
			//carico le date del Cantiere nel caso fossere state cambiate
			pnlLavoro.setRangeDate(pnlCantiere.getDataInizioCantiere(),pnlCantiere.getDataFineCantiere());
			//Controllo se il Lavoro e nuovo oppure e gia stato inserito
			if (tp.getPath()[tp.getPath().length-1] instanceof addNode){
				pnlLavoro.clear();pnlLavoro.btnAddActionListener(addLavoroActionListener);
				pnlLavoro.btnLavoro.setText("Inserisci");
			}else{
				ArrayList<String> data=((workNode)tp.getPath()[tp.getPath().length-1]).getData();
				pnlLavoro.fill(data);pnlLavoro.btnAddActionListener(editLavoroActionListener);
				pnlLavoro.btnLavoro.setText("Modifica");
			}
		}else if(tp!=null && tp.getPath().length==3){
			DefaultMutableTreeNode selected=(DefaultMutableTreeNode)tp.getPath()[tp.getPath().length-1];
			if (tp.getPath()[tp.getPath().length-1] instanceof addNode){
				cl.show(cardPanel,"richiesta");
			}else{
				cl.show(cardPanel,"visualizza");
				ArrayList<String> data=((workNode)tp.getPath()[tp.getPath().length-1]).getData();
			}
		}
	}	
	
	
	public void setAddCantiereListeners(ActionListener act) {
		pnlCantiere.setAddCantiereListeners(act);
	}
	ActionListener editLavoroActionListener;
	public void setEditLavoroListeners(ActionListener act) {
		editLavoroActionListener=act;
	}
	ActionListener addLavoroActionListener;
	public void setAddLavoroListeners(ActionListener act) {	  
		addLavoroActionListener=act;
	}
	public void addLavoro(ArrayList<String> work){
		treeModel.addWork(work);
	}
	public void addRichiesta(ArrayList<String> associazione){
		treeModel.addRichiesta(associazione);
	}
	public void addMouseListener(MouseAdapter adp){
		tree.addMouseListener(adp);
	}
	
	
	//ACCESSO AI DATI DEL CANTIERE
	public String getNomeCantiere() {return pnlCantiere.getNomeCantiere();}
	public String getIndirizzoCantiere() {return pnlCantiere.getIndirizzoCantiere();}
	public Date getDataInizioCantiere(){return pnlCantiere.getDataInizioCantiere();}
	public Date getDataFineCantiere(){return pnlCantiere.getDataFineCantiere();}
	public void setDataInizioCantiereChangedListener(PropertyChangeListener list){pnlCantiere.addPropertyChangeListener(list);}
	public void setMinimaDataFineCantiere(Date d){pnlCantiere.setMinimaDataFineCantiere(d);}
	public void setMassimaDataInizioCantiere(Date d){pnlCantiere.setMassimaDataInizioCantiere(d);}
	public void setMinimaDataFine(Date d){pnlCantiere.setMinimaDataFine(d);}
	public void setMassimaDataInizio(Date d){pnlCantiere.setMassimaDataInizio(d);}


	//ACCESSO AI DATI DEL LAVORO
	public String getNomeLavoro() {return pnlLavoro.getNomeLavoro();}
	public Date getDataInizioLavoro(){return pnlLavoro.getDataInizioLavoro();}
	public Date getDataFineLavoro(){return pnlLavoro.getDataFineLavoro();}
	
}


