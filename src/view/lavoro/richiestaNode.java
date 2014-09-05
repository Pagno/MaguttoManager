package view.lavoro;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;

class richiestaNode extends DefaultMutableTreeNode implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6286210896311354098L;
	ArrayList<String> ass;

	public richiestaNode(ArrayList<String> newAss) {
		ass = newAss;
	}
	public String getCodiceRichiesta(){
		return ass.get(0);
	}
	public String toString(){
		return ass.get(0)+" - "+ass.get(1);
	}
	public String getName(){
		return ass.get(1);
	}
	public ArrayList<String> getData(){
		return ass;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		//addRichiesta((ArrayList<String>)arg1);
	}
}
