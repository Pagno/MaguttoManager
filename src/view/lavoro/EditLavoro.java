package view.lavoro;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.TreePath;

import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaMacchina;
import model.organizer.data.RichiestaRuspa;
import view.AssociaMacchina;
import view.lavoro.panel.CantierePanel;
import view.lavoro.panel.LavoroPanel;
import view.lavoro.panel.RichiestaPanel;
import view.lavoro.panel.VisualizzaRichiestaPanel;

import javax.swing.JCheckBox;

import controller.ControllerConnector;
import controller.Interface.AbstractCantieriController;


public class EditLavoro extends JDialog implements Observer{

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
	private int codiceCantiere;
	private JCheckBox chckbxNewCheckBox;
	private addNodeRenderer renderer;
	private EditLavoro editLavoro;
	//LAVORO PANEL
	/**
	 * Create the dialog.
	 */
	private ControllerConnector cCtr;
	public EditLavoro(JFrame view,Cantiere cantiere,ControllerConnector aCtr) {
		super(view);
		editLavoro=this;
		cCtr=aCtr;
		setTitle("Edit Cantiere");
		codiceCantiere=cantiere.getCodice();
		//this.datiCantiere=datiCantiere;
		setBounds(0, 0, 900, 500);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("Chiudi");
		okButton.setActionCommand("Chiudi");
		okButton.setName("chiudi");
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
		pnlCantiere.setNomeCantiere(cantiere.getNomeCantiere());
		pnlCantiere.setIndirizzoCantiere(cantiere.getIndirizzo());
		pnlCantiere.setPrioritaCantiere(cantiere.getPrioritaString());
		pnlCantiere.setDataInizioCantiere(cantiere.getDataApertura());
		pnlCantiere.setDataFineCantiere(cantiere.getDataChiusura());
		pnlCantiere.setName("pnlCantiere");
		
		//pnlCantiere.setDatiCantiere(datiCantiere);
		pnlLavoro= new LavoroPanel(pnlCantiere.getDataInizioCantiere(),pnlCantiere.getDataFineCantiere());
		pnlLavoro.setName("pnlLavoro");
		pnlAddRichiesta= new RichiestaPanel();
		pnlAddRichiesta.setName("pnlAddRichiesta");
		pnlAddRichiesta.btnAdd.addActionListener(aggiungiRichiestaListener());
		pnlVisualizzaPanel=new VisualizzaRichiestaPanel();
		pnlVisualizzaPanel.setName("pnlVisualizzaPanel");
		
		cardPanel.add(pnlLavoro,"lavoro");
		cardPanel.add(pnlAddRichiesta,"richiesta");
		cardPanel.add(pnlCantiere,"cantiere");
		cardPanel.add(pnlVisualizzaPanel,"visualizza");

		contentPane.add(cardPanel,BorderLayout.CENTER);
		
		treeModel = new treeModel(cantiere); 
		treeModel.reload(cantiere);
		tree=new JTree(treeModel);
		tree.setSize(200, 500);

		renderer = new addNodeRenderer();
	    tree.setCellRenderer(renderer);
	    tree.setName("tree");
	    
	    chckbxNewCheckBox = new JCheckBox("Nascondi Richieste Soddisfatte");
	    chckbxNewCheckBox.addItemListener(check());
		scrollpane = new JScrollPane(tree,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBounds(0,0,200, 500);
		
		JPanel pnlWest=new JPanel(new BorderLayout(0,0));
		pnlWest.add(chckbxNewCheckBox,BorderLayout.NORTH);
		pnlWest.add(scrollpane,BorderLayout.CENTER);
		
		
		scrollpane.setColumnHeaderView(chckbxNewCheckBox);
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
		try{
			if(tp.getPathCount()==1){ //Seleziono il cantiere e mostro il pannello del cantiere
				cl.show(cardPanel,"cantiere");
				btnDelete.setEnabled(false);
			}else if(tp.getPathCount()==2){
				cl.show(cardPanel,"lavoro");
				pnlLavoro.setRangeDate(pnlCantiere.getDataInizioCantiere(),pnlCantiere.getDataFineCantiere());
				if(tp.getPathComponent(tp.getPathCount()-1).toString().equals("Aggiungi nuovo Lavoro")){
					btnDelete.setEnabled(false);
					pnlLavoro.clear();
					pnlLavoro.btnAddActionListener(aggiungiLavoroListener());
					pnlLavoro.btnLavoro.setText("Inserisci");
				}else{
					Lavoro lavoro=((Lavoro)tp.getPath()[tp.getPathCount()-1]);
					ArrayList<String> l=new ArrayList<String>();
					l.add(Integer.toString(lavoro.getCodice()));
					l.add(lavoro.getNome());
					SimpleDateFormat df = new SimpleDateFormat();
					df.applyPattern("dd/MM/yyyy");
					l.add(df.format(lavoro.getDataInizio().getTime()));
					l.add(df.format(lavoro.getDataFine().getTime()));
					pnlLavoro.fill(l);
					

					pnlLavoro.btnAddActionListener(modificaLavoroListener());
					btnDelete.setEnabled(true);				
					for( ActionListener al : btnDelete.getActionListeners() ) {
						btnDelete.removeActionListener( al );
					}

					btnDelete.addActionListener(deleteLavoroListener(lavoro.getCodice()));

					codiceLavoro=lavoro.getCodice();
					pnlLavoro.btnLavoro.setText("Modifica");
				}
			}else if(tp.getPathCount()==3){
				if(tp.getPathComponent(tp.getPathCount()-1).toString().equals("Aggiungi nuova Richiesta")){
					cl.show(cardPanel,"richiesta");
					addNode addNode=(addNode)tp.getPath()[tp.getPath().length-1];
					Lavoro lavoro=(Lavoro)addNode.getParent();
					codiceLavoro=lavoro.getCodice();
				}else{
					btnDelete.setEnabled(true);
					for( ActionListener al : btnDelete.getActionListeners() ) {
						btnDelete.removeActionListener( al );
					}
					
					
					Richiesta richiesta=(Richiesta)tp.getPath()[tp.getPath().length-1];

					btnDelete.addActionListener(deleteRichiestaListener(richiesta.getCodice()));
					codiceLavoro=richiesta.getCodiceLavoro();
					
					if(richiesta.isSoddisfatta()){
						pnlVisualizzaPanel.btnAssociaMacchina.setText("Rimuovi Associazione");
						pnlVisualizzaPanel.addSoddisfaRichiestaListener(liberaRichiestaListener(richiesta.getCodice()));
					}else{
						pnlVisualizzaPanel.addLiberaRichiestaListener(associaMacchinaListener(richiesta.getCodice()));
						pnlVisualizzaPanel.btnAssociaMacchina.setText("Aggiungi Associazione");
					}
					ArrayList<String> data=richiesta.getData();
					pnlVisualizzaPanel.loadData(data);

					cl.show(cardPanel,"visualizza");
				}	
			}
			
		}catch(java.lang.NullPointerException ex){
			
		}
	}	
	
	private ItemListener check(){
		return new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				renderer.set(!chckbxNewCheckBox.isSelected());
				treeModel.reload();
			}
		};
	}	
	
	private void addLavoro(ArrayList<String> work){
		treeModel.addWork(work);
	}
	private void addRichiesta(ArrayList<String> associazione){
		treeModel.addRichiesta(associazione);
	}
	
	public ActionListener liberaRichiestaListener(final int codiceRichiesta){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cCtr.liberaRichiesta(codiceRichiesta);
				aggiornaRichiesta();
			}
		};
	}
	public ActionListener associaMacchinaListener(final int codiceRichiesta){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssociaMacchina am=new AssociaMacchina(editLavoro, cCtr.getElencoMacchineDisponibili(codiceRichiesta));
				am.addBtnAssociaListener(aggiungiAssociazioneMacchinaListener( am,codiceRichiesta));
			}
		};
	}
	public ActionListener aggiungiAssociazioneMacchinaListener(final AssociaMacchina am,final int codiceRichiesta){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int codiceMacchina=am.getCodiceMacchinaSelezionata();
				cCtr.soddisfaRichiesta(codiceRichiesta, codiceMacchina);
				aggiornaRichiesta();
				am.dispose();
			}
		};
	}
	public ActionListener aggiungiLavoroListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pnlLavoro.getDataInizioLavoro()==null || pnlLavoro.getDataFineLavoro()==null
						|| pnlLavoro.getNomeLavoro()==""){
					JOptionPane.showMessageDialog(null,"Compilare tutti campi.","Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					String nome=pnlLavoro.getNomeLavoro();
					GregorianCalendar inizio=new GregorianCalendar();
					inizio.setTime(pnlLavoro.getDataInizioLavoro());
					GregorianCalendar fine=new GregorianCalendar();
					fine.setTime(pnlLavoro.getDataFineLavoro());
					
					//Aggiungi il lavoro
					cCtr.aggiungiLavoro(codiceCantiere,nome, inizio, fine);
					
					//ricarico il modello
					reloadModel();
				}
			}
		};
	}
	public ActionListener modificaLavoroListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pnlLavoro.getDataInizioLavoro()==null || pnlLavoro.getDataFineLavoro()==null
						|| pnlLavoro.getNomeLavoro()==""){
					JOptionPane.showMessageDialog(null,"Compilare tutti campi.","Error", JOptionPane.ERROR_MESSAGE);
				}else{
					String nome=pnlLavoro.getNomeLavoro();
					GregorianCalendar inizio=new GregorianCalendar();
					inizio.setTime(pnlLavoro.getDataInizioLavoro());
					GregorianCalendar fine=new GregorianCalendar();
					fine.setTime(pnlLavoro.getDataFineLavoro());
					
					//Aggiungi il lavoro
					cCtr.modificaLavoro(codiceCantiere,nome, inizio, fine);
					
					//ricarico il modello
					reloadModel();
				}
			}
		};
	}
	public ActionListener aggiungiRichiestaListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RichiestaMacchina richiesta = null;

				if (pnlAddRichiesta.getTipoMacchina() == "Gru") {
					richiesta = new RichiestaGru(pnlAddRichiesta.getMinLunghezza(),
							pnlAddRichiesta.getMaxLunghezza(),
							pnlAddRichiesta.getMinAltezza(),
							pnlAddRichiesta.getMaxAltezza(),
							pnlAddRichiesta.getMinPortata(),
							pnlAddRichiesta.getMaxPortata(),
							pnlAddRichiesta.getMinRotazione(),
							pnlAddRichiesta.getMaxRotazione());
				} else if (pnlAddRichiesta.getTipoMacchina() == "Ruspa") {
					richiesta = new RichiestaRuspa(pnlAddRichiesta.getMinCapacita(),
							pnlAddRichiesta.getMaxCapacita(),
							pnlAddRichiesta.getMinPortata(),
							pnlAddRichiesta.getMaxPortata(),
							pnlAddRichiesta.getMinAltezza(),
							pnlAddRichiesta.getMaxAltezza());
				} else if (pnlAddRichiesta.getTipoMacchina() == "Camion") {
					richiesta = new RichiestaCamion(
							pnlAddRichiesta.getMinCapacita(),
							pnlAddRichiesta.getMaxCapacita(),
							pnlAddRichiesta.getMinPortata(),
							pnlAddRichiesta.getMaxPortata(),
							pnlAddRichiesta.getMinLunghezza(),
							pnlAddRichiesta.getMaxLunghezza());
				} else if (pnlAddRichiesta.getTipoMacchina() == "Escavatore") {
					richiesta = new RichiestaEscavatore(
							pnlAddRichiesta.getMinCapacita(),
							pnlAddRichiesta.getMaxCapacita(),
							pnlAddRichiesta.getMinPortata(),
							pnlAddRichiesta.getMaxPortata(),
							pnlAddRichiesta.getMinAltezza(),
							pnlAddRichiesta.getMaxAltezza(),
							pnlAddRichiesta.getMinProfondita(),
							pnlAddRichiesta.getMaxProfondita());
				}
				cCtr.addRichiesta(codiceCantiere,codiceLavoro, richiesta);

				reloadModel();
			}
		};
	}
	
	private ActionListener deleteRichiestaListener(final int codiceRichiesta) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCtr.eliminaRichiesta(codiceRichiesta);
				reloadModel();
			}
		};
	}
	
	public ActionListener deleteLavoroListener(final int codiceLavoro) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCtr.eliminaLavoro(codiceLavoro);
				
			}
		};
	}
	
	
	

	private void reloadModel(){
		treeModel.reload();
	}
	private void aggiornaRichiesta(){
		TreePath tp=tree.getSelectionPath();
		btnDelete.setEnabled(true);
		for( ActionListener al : btnDelete.getActionListeners() ) {
			btnDelete.removeActionListener( al );
		}
		
		
		Richiesta richiesta=(Richiesta)tp.getPath()[tp.getPath().length-1];
		btnDelete.addActionListener(deleteRichiestaListener(richiesta.getCodice()));
		
		if(richiesta.isSoddisfatta()){
			pnlVisualizzaPanel.btnAssociaMacchina.setText("Rimuovi Associazione");
			pnlVisualizzaPanel.addSoddisfaRichiestaListener(liberaRichiestaListener(richiesta.getCodice()));
		}else{
			pnlVisualizzaPanel.addLiberaRichiestaListener(associaMacchinaListener(richiesta.getCodice()));
			pnlVisualizzaPanel.btnAssociaMacchina.setText("Aggiungi Associazione");
		}
		
		ArrayList<String> data=richiesta.getData();
		pnlVisualizzaPanel.loadData(data);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ArrayList<String> arg=new ArrayList<String>(Arrays.asList((String[])arg1));
		if(arg.size()==4){
			addLavoro(arg);
		}else{
			addRichiesta(arg);
		}
	}
}
