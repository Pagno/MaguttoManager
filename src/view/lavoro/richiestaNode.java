package view.lavoro;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

class richiestaNode extends DefaultMutableTreeNode{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6286210896311354098L;
	ArrayList<String> richiesta;

	public richiestaNode(ArrayList<String> newRichiesta) {
		richiesta = newRichiesta;
	}
	public String getCodiceRichiesta(){
		return richiesta.get(0);
	}
	public String toString(){
		return richiesta.get(0)+" - "+richiesta.get(1);
	}
	public String getName(){
		return richiesta.get(1);
	}
	public ArrayList<String> getData(){
		return richiesta;
	}
}
