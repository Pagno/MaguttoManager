package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import controller.Associazione;

public class GreedyEngine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane listScroller;
	private JList list;
	private DefaultListModel<Associazione> listModel;
	private JButton btnGeneraMigliorAssociazione,btnEliminaAssociazione,okButton;
	private ArrayList<Associazione> data=new ArrayList<Associazione>();
	
	public GreedyEngine(JFrame view) {
		super(view);
		setBounds(100, 100, 450, 300);
		setTitle("Greedy Engine");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		listModel=new DefaultListModel<Associazione>();
		list = new JList(listModel);
		listScroller = new JScrollPane(list);
		contentPanel.add(listScroller, BorderLayout.CENTER);

		btnGeneraMigliorAssociazione = new JButton("Genera associazioni");
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
	public void addBtnGeneraMigliorAssociazioneListener(ActionListener act){
		btnGeneraMigliorAssociazione.addActionListener(act);
	}
	public void addBtnConfermaAssociazioniListener(ActionListener act){
		okButton.addActionListener(act);
	}
	public void addBtnEliminaAssociazioneListener(ActionListener act){
		btnGeneraMigliorAssociazione.addActionListener(act);
	}
	public void rimuoviElementoSelezionato(){
		int index=list.getSelectedIndex();
		list.remove(index);
	}
	public void setData(ArrayList<Associazione> listaAssociazioni){
		data=listaAssociazioni;
		for(Associazione associazione:listaAssociazioni){
			listModel.addElement(associazione);
		}
	}
	public ArrayList<Associazione> getData(){
		return data;
	}
}
