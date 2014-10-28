package view.lavoro.treenode;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

class NodeRichiesta extends DefaultMutableTreeNode{
	private static final long serialVersionUID = 6286210896311354098L;
	ArrayList<String> richiesta;

	/**
	 * Inserisce una nuova richiesta.
	 * 
	 * @param newRichiesta dati della nuova richiesta da inserire.
	 */
	public NodeRichiesta(ArrayList<String> newRichiesta) {
		richiesta = newRichiesta;
	}
	/**
	 * Ritorna il codice della richiesta.
	 * 
	 * @return Codice della richiesta.
	 */
	public String getCodiceRichiesta(){
		return richiesta.get(0);
	}
	public String toString(){
		return richiesta.get(0)+" - "+richiesta.get(1);
	}
	/**
	 * Ritorna il nome della richiesta.
	 * 
	 * @return Nome della richiesta.
	 */
	public String getName(){
		return richiesta.get(1);
	}
	/**
	 * Ritorna i dati della richiesta.
	 * 
	 * @return Dati della richiesta.
	 */
	public ArrayList<String> getData(){
		return richiesta;
	}
}
