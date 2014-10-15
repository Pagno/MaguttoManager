package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.organizer.data.Macchina;

public class AssociaMacchina extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAssocia, btnCancel;
	private JComboBox<String> cmbListaMacchine;
	private ArrayList<Macchina> listaMacchine = null;
	private JDialog view;

	public AssociaMacchina(JDialog v, ArrayList<Macchina> macchine) {
		super(v);
		this.view = v;
		view.setEnabled(false);
		setTitle("Associa Macchina");

		setBounds(0, 0, 200, 100);
		listaMacchine = macchine;
		btnAssocia = new JButton("Associa");
		btnCancel = new JButton("Cancel");
		cmbListaMacchine = new JComboBox<String>();

		for (Macchina macchina : listaMacchine) {
			cmbListaMacchine.addItem(macchina.getProduttore() + " - " + macchina.getModello());
		}

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnAssocia);
		pnlSouth.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JPanel pnlCenter = new JPanel(new FlowLayout());
		pnlCenter.add(cmbListaMacchine);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				view.setEnabled(true);
			}

		});

	}

	public void addBtnAssociaListener(ActionListener act) {
		btnAssocia.addActionListener(act);
	}

	public int getCodiceMacchinaSelezionata() {
		return listaMacchine.get(cmbListaMacchine.getSelectedIndex()).getCodice();
	}

}
