package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.ModelConnector;

public class VisualizzaCtr implements ActionListener,ListSelectionListener{

	ModelConnector model;
	public VisualizzaCtr(ModelConnector m){
			model=m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
		if(source instanceof JButton){
			
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
