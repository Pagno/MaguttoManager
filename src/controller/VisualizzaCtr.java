package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.viewPrincipale;

import model.ModelConnector;

public class VisualizzaCtr implements ActionListener,ListSelectionListener{

	private ModelConnector model;
	private viewPrincipale view;
	
	public VisualizzaCtr(ModelConnector m){
			model=m;
			view =new viewPrincipale(m);
			view.addButtonGruListener(this);
			view.addButtonRuspaListener(this);
			view.addButtonCamionListener(this);
			view.addButtonEscavatoreListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
		if(source instanceof JButton){
			Object[] v1={"Mauro", "Valota","Correre", new Integer(5), new Boolean(false)}; 
            System.out.println("Bottone premuto");
			view.addData(v1);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

		DefaultListSelectionModel lsm = (DefaultListSelectionModel)e.getSource();
        

        if (lsm.isSelectionEmpty()) {
			System.out.println(" <none>");
        } else {
            // Find out which indexes are selected.
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            /*for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                	System.out.println(" "+i);
                }
            }*/
            System.out.println(lsm.getLeadSelectionIndex());
        }
	}

}
