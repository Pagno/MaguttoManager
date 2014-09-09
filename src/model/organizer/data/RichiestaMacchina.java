package model.organizer.data;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class RichiestaMacchina extends DefaultMutableTreeNode{
	
	public RichiestaMacchina() {
		super();
	}
	
	public abstract boolean rispettaRichiesta(Macchina m);
}
