package view.lavoro;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import view.lavoro.panel.CantierePanel;
import view.lavoro.panel.LavoroPanel;
import view.lavoro.panel.RichiestaPanel;
import view.lavoro.panel.VisualizzaRichiestaPanel;


public class EditLavoro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2004768729465641055L;
	private JPanel contentPane,cardPanel;
	public RichiestaPanel pnlAddRichiesta;
	private LavoroPanel pnlLavoro;
	private CantierePanel pnlCantiere;
	private VisualizzaRichiestaPanel pnlVisualizzaPanel;
	private JButton btnDelete;
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
		
		JPanel pnlWest=new JPanel(new BorderLayout(0,0));
		pnlWest.add(scrollpane,BorderLayout.CENTER);
		btnDelete=new JButton("Delete");
		pnlWest.add(btnDelete,BorderLayout.SOUTH);
		contentPane.add(pnlWest,BorderLayout.WEST);
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		
		cl.show(cardPanel,"cantiere");
	
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				doMouseClicked(me);
			}
		});
		setVisible(true);
	}
	
	private int codiceLavoro;
	//Aggiornamento dei pannelli
	void doMouseClicked(MouseEvent me) {
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		CardLayout  cl = (CardLayout)(cardPanel.getLayout());
		if (tp!=null && tp.getPath().length==1){
			cl.show(cardPanel,"cantiere");
			btnDelete.setEnabled(false);
		}
		else if (tp!=null && tp.getPath().length==2){//VISUALIZZO IL PANNELLO LAVORO
			cl.show(cardPanel,"lavoro");
			//carico le date del Cantiere nel caso fossere state cambiate
			pnlLavoro.setRangeDate(pnlCantiere.getDataInizioCantiere(),pnlCantiere.getDataFineCantiere());
			//Controllo se il Lavoro e nuovo oppure e gia stato inserito
			if (tp.getPath()[tp.getPath().length-1] instanceof addNode){
				btnDelete.setEnabled(false);
				pnlLavoro.clear();pnlLavoro.btnAddActionListener(addLavoroActionListener);
				pnlLavoro.btnLavoro.setText("Inserisci");
			}else{
				btnDelete.setEnabled(true);				
				for( ActionListener al : btnDelete.getActionListeners() ) {
					btnDelete.removeActionListener( al );
				}
				btnDelete.addActionListener(deleteLavoroListener);
				ArrayList<String> data=((workNode)tp.getPath()[tp.getPath().length-1]).getData();
				codiceLavoro=Integer.parseInt(data.get(0));
				pnlLavoro.fill(data);pnlLavoro.btnAddActionListener(editLavoroActionListener);
				pnlLavoro.btnLavoro.setText("Modifica");
			}
		}else if(tp!=null && tp.getPath().length==3){//VISUALIZZO IL PANNELLO RICHIESTA
			if (tp.getPath()[tp.getPath().length-1] instanceof addNode){
				btnDelete.setEnabled(false);
				for( ActionListener al : btnDelete.getActionListeners() ) {
					btnDelete.removeActionListener( al );
				}
				btnDelete.addActionListener(deleteRichiestaListener);
				addNode selected=(addNode)tp.getPath()[tp.getPath().length-1];
				codiceLavoro=((workNode)selected.getParent()).getCodiceLavoro();
				pnlAddRichiesta.clearData();
				cl.show(cardPanel,"richiesta");
			}else{
				btnDelete.setEnabled(true);
				for( ActionListener al : btnDelete.getActionListeners() ) {
					btnDelete.removeActionListener( al );
				}
				btnDelete.addActionListener(deleteRichiestaListener);
				cl.show(cardPanel,"visualizza");
				ArrayList<String> data=((richiestaNode)tp.getPath()[tp.getPath().length-1]).getData();
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
	ActionListener addRichiestaActionListener;
	public void setAddRichiestaListeners(ActionListener act) {
		pnlAddRichiesta.btnAddActionListener(act);
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
	ActionListener deleteLavoroListener;
	public void addDeleteLavoroListener(ActionListener adp){
		deleteLavoroListener=adp;
	}
	ActionListener deleteRichiestaListener;
	public void addDeleteRichiestaListener(ActionListener adp){
		deleteRichiestaListener=adp;
	}
	
	//ACCESSO AI DATI DEL CANTIERE
	public int getCodiceCantiere(){return (int)datiCantiere[0];}
	public String getNomeCantiere() {return pnlCantiere.getNomeCantiere();}
	public String getPrioritaCantiere() {return pnlCantiere.getPrioritaCantiere();}
	public String getIndirizzoCantiere() {return pnlCantiere.getIndirizzoCantiere();}
	public Date getDataInizioCantiere(){return pnlCantiere.getDataInizioCantiere();}
	public Date getDataFineCantiere(){return pnlCantiere.getDataFineCantiere();}
	public void setDataInizioCantiereChangedListener(PropertyChangeListener list){pnlCantiere.addPropertyChangeListener(list);}
	public void setMinimaDataFineCantiere(Date d){pnlCantiere.setMinimaDataFineCantiere(d);}
	public void setMassimaDataInizioCantiere(Date d){pnlCantiere.setMassimaDataInizioCantiere(d);}
	public void setMinimaDataFine(Date d){pnlCantiere.setMinimaDataFine(d);}
	public void setMassimaDataInizio(Date d){pnlCantiere.setMassimaDataInizio(d);}


	//ACCESSO AI DATI DEL LAVORO
	public int getCodiceLavoro() {return codiceLavoro;}
	public String getNomeLavoro() {return pnlLavoro.getNomeLavoro();}
	public Date getDataInizioLavoro(){return pnlLavoro.getDataInizioLavoro();}
	public Date getDataFineLavoro(){return pnlLavoro.getDataFineLavoro();}

	//ACCESSO AI DATI DEL RICHIESTA
	public int getMinCapacita() {return pnlAddRichiesta.getMinCapacita();}
	public int getMinPortata(){return pnlAddRichiesta.getMinPortata();}
	public int getMinLunghezza() {return pnlAddRichiesta.getMinLunghezza();	}
	public int getMinAltezza() {return pnlAddRichiesta.getMinAltezza();}
	public int getMinProfondita() {return pnlAddRichiesta.getMinProfondita();}
	public int getMinRotazione() {return pnlAddRichiesta.getMinRotazione();	}
	
	public int getMaxCapacita() {return pnlAddRichiesta.getMaxCapacita();}
	public int getMaxPortata(){return pnlAddRichiesta.getMaxPortata();}
	public int getMaxLunghezza() {return pnlAddRichiesta.getMaxLunghezza();	}
	public int getMaxAltezza() {return pnlAddRichiesta.getMaxAltezza();}
	public int getMaxProfondita() {return pnlAddRichiesta.getMaxProfondita();}
	public int getMaxRotazione() {return pnlAddRichiesta.getMaxRotazione();	}
	
	public String getTipoMacchina() {return pnlAddRichiesta.getType();}


	public int getCodiceRichiestaSelezionata() {
		TreePath tp=tree.getSelectionPath();
		ArrayList<String> data=((richiestaNode)tp.getPath()[tp.getPath().length-1]).getData();
		return Integer.parseInt(data.get(1));
	}

	public int getCodiceLavoroSelezionato() {
		TreePath tp=tree.getSelectionPath();
		ArrayList<String> data=((workNode)tp.getPath()[tp.getPath().length-1]).getData();
		return Integer.parseInt(data.get(0));
	}


	public void removeSelectedData() {
		System.out.println("rimozione path");
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        TreePath[] paths = tree.getSelectionPaths();
        for (TreePath path : paths) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            if (node.getParent() != null) {
                model.removeNodeFromParent(node);
            }
        }
	}

}


