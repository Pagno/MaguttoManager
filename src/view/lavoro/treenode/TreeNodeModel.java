package view.lavoro.treenode;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import model.organizer.data.Cantiere;


public class TreeNodeModel extends DefaultTreeModel  implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141324938219265208L;

	ArrayList<NodeWork> listaLavori=new ArrayList<NodeWork>();  
	Cantiere c;
	public TreeNodeModel(Cantiere cantiere) {
		super(null);
		setRoot(cantiere);
	}
	public void addWork(ArrayList<String> work){
		NodeWork a=new NodeWork(work);
		//insertNodeInto(a, getRoot(), 0);
		
		listaLavori.add(a);//aggiungo il nodo
	}
	public void addRichiesta(ArrayList<String> richiesta){
		for(NodeWork wn:listaLavori){
			if(wn.getCodiceLavoro()==Integer.parseInt(richiesta.get(0))){
				wn.addRichiesta(richiesta);
				NodeRichiesta a=new NodeRichiesta(richiesta);
				insertNodeInto(a, wn, 0);
			}
				
		}//aggiungo il nodo
	}


	@Override
	public void update(Observable o, Object arg) {
		if(((ArrayList<String>)arg).size()==4){
			addWork((ArrayList<String>)arg);
		}else{
			addRichiesta((ArrayList<String>)arg);
		}
	}	
}


