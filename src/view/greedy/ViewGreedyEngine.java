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
import controller.data.Associazione;

public class ViewGreedyEngine extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766346581320901687L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane listScroller;
	private JList<Associazione> list;
	private DefaultListModel<Associazione> listModel;
	private JButton btnGeneraMigliorAssociazione,btnEliminaAssociazione,okButton;
	private ArrayList<Associazione> data=new ArrayList<Associazione>();
	private ControllerConnector cCtr;
	public ViewGreedyEngine(JFrame view,ControllerConnector aCtr) {
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
	private ActionListener generaMigliorAssociazioneListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Associazione> assGreedy=cCtr.generaAssociazioni();
				setData(assGreedy);
			}
		};
		
	}
	private ActionListener confermaAssociazioniListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCtr.confermaAssociazioniListener(data);
				dispose();
			}
		};
	}
	
	private void setData(ArrayList<Associazione> listaAssociazioni){
		data=listaAssociazioni;
		for(Associazione associazione:listaAssociazioni){
			listModel.addElement(associazione);
		}
	}
}
