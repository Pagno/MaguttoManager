package view.macchina;

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
	 *  View usata per l'associazione di una richiesta ad una macchina.
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAssocia, btnCancel;
	private JComboBox<String> cmbListaMacchine;
	private ArrayList<Macchina> listaMacchine = null;
	private JDialog view;

	/**
	 * Inizializza il frame
	 *
	 * @param v view principale.
	 * @param macchine Elenco delle macchina disponibili per una data richiesta.
	 */
	public AssociaMacchina(JDialog v, ArrayList<Macchina> macchine) {
		super(v);
		setName("associaMacchina");
		this.view = v;
		view.setEnabled(false);
		setTitle("Associa Macchina");

		setBounds(0, 0, 200, 100);
		listaMacchine = macchine;
		btnAssocia = new JButton("Associa");
		btnAssocia.setName("aggiungiAssociazione");
		btnCancel = new JButton("Cancel");
		cmbListaMacchine = new JComboBox<String>();
		cmbListaMacchine.setName("elencoMacchine");

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
	/**
	 * Assegna i listener per il bottone di associazione.
	 *
	 * @param act  ActionListener.
	 */
	public void addBtnAssociaListener(ActionListener act) {
		btnAssocia.addActionListener(act);
	}
	/**
	 * Ritorna il codice della macchina da associare alla richiesta.
	 *
	 * @return codice della macchina da associare alla richiesta.
	 */
	public int getCodiceMacchinaSelezionata() {
		return listaMacchine.get(cmbListaMacchine.getSelectedIndex()).getCodice();
	}

}
