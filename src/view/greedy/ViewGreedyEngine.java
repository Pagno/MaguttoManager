package view.greedy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import controller.ControllerConnector;
import controller.ControllerInterface;
import controller.greedy.data.Associazione;


/**
 * The Class ViewGreedyEngine.
 * 
 * @author Matteo Pagnoncelli
 * @author Mauro Valota
 */
public class ViewGreedyEngine extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7766346581320901687L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The list scroller. */
	private JScrollPane listScroller;
	
	/** The list. */
	private JList<Associazione> list;
	
	/** The list model. */
	private DefaultListModel<Associazione> listModel;
	
	/** The ok button. */
	private JButton btnGeneraMigliorAssociazione,btnEliminaAssociazione,okButton;
	
	/** The data. */
	private ArrayList<Associazione> data=new ArrayList<Associazione>();
	
	/** The c ctr. */
	private ControllerInterface cCtr;
	
	/**
	 * Crea un nuovo frame per la generazione automatica delle associazioni.
	 *
	 * @param view view padre
	 * @param insCtr Interfaccia del componente controller necessario per comunicare con i dati dell'applicazione
	*/
	public ViewGreedyEngine(JFrame view,ControllerInterface aCtr) {
		super(view);
		cCtr=aCtr;
		setBounds(100, 100, 450, 300);
		setTitle("Greedy Engine");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		listModel=new DefaultListModel<Associazione>();
		list = new JList<Associazione>(listModel);
		listScroller = new JScrollPane(list);
		contentPanel.add(listScroller, BorderLayout.CENTER);

		btnGeneraMigliorAssociazione = new JButton("Genera associazioni");
		btnGeneraMigliorAssociazione.addActionListener(generaMigliorAssociazioneListener());
		contentPanel.add(btnGeneraMigliorAssociazione, BorderLayout.NORTH);

		btnEliminaAssociazione = new JButton("Elimina associazione");
		contentPanel.add(btnEliminaAssociazione, BorderLayout.SOUTH);
		btnEliminaAssociazione.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=list.getSelectedIndex();
				data.remove(listModel.get(index));
				listModel.remove(index);			
			}
		});
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Conferma Associazioni");
		okButton.addActionListener(confermaAssociazioniListener());
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setVisible(true);
	}
	
	/**
	 * Evento legato alla pressione del bottone "Genera Associazioni".
	 *
	 * @return ActionListener
	 */
	private ActionListener generaMigliorAssociazioneListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Associazione> assGreedy=cCtr.generaAssociazioni();
				setData(assGreedy);
			}
		};
		
	}
	
	/**
	 * Evento legato alla pressione del bottone "Conferma Associazioni".
	 *
	 * @return ActionListener
	 */
	private ActionListener confermaAssociazioniListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCtr.confermaAssociazioniListener(data);
				dispose();
			}
		};
	}
	
	/**
	 * Modifica l'elenco delle associazioni visualizzate nella JList.
	 *
	 * @param listaAssociazioni elenco delle associazioni generate dall'algoritmo.
	 */
	private void setData(ArrayList<Associazione> listaAssociazioni){
		data=listaAssociazioni;
		for(Associazione associazione:listaAssociazioni){
			listModel.addElement(associazione);
		}
	}
}
