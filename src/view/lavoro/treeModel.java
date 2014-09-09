package view.lavoro;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import model.organizer.data.Cantiere;


public class treeModel extends DefaultTreeModel  implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141324938219265208L;

	ArrayList<workNode> listaLavori=new ArrayList<workNode>();  
	Cantiere c;
	public treeModel(Cantiere cantiere) {
		super(null);
		

		setRoot(cantiere);

		addNode add=new addNode("Aggiungi nuovo Lavoro");
		insertNodeInto(add, cantiere, 0);
	}
	public void addWork(ArrayList<String> work){
		workNode a=new workNode(work);
		addNode add=new addNode("Aggiungi nuova Richiesta");
		insertNodeInto(add, a, 0);
		//insertNodeInto(a, getRoot(), 0);
		listaLavori.add(a);//aggiungo il nodo
	}
	public void addRichiesta(ArrayList<String> richiesta){
		for(workNode wn:listaLavori){
			if(wn.getCodiceLavoro()==Integer.parseInt(richiesta.get(0))){
				wn.addRichiesta(richiesta);
				richiestaNode a=new richiestaNode(richiesta);
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


