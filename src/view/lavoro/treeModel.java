package view.lavoro;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public class treeModel extends DefaultTreeModel  implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141324938219265208L;

	ArrayList<workNode> listaLavori=new ArrayList<workNode>();  
	DefaultMutableTreeNode root;
	
	
	public treeModel(String cantiere) {
		super(null);
		

		root =  new DefaultMutableTreeNode(cantiere);
		setRoot(root);

		addNode add=new addNode("Aggiungi nuovo Lavoro");
		insertNodeInto(add, root, 0);
	}
	public void addWork(ArrayList<String> work){
		workNode a=new workNode(work);
		addNode add=new addNode("Aggiungi nuova Richiesta");
		insertNodeInto(add, a, 0);
		insertNodeInto(a, root, 0);
		listaLavori.add(a);//aggiungo il nodo
	}
	public void addRichiesta(ArrayList<String> ass){
		for(workNode wn:listaLavori){
			if(wn.getId().compareTo(ass.get(0))==0){
				wn.addAssociazione(ass);
				break;
			}
				
		}//aggiungo il nodo
	}


	@Override
	public void update(Observable o, Object arg) {
		addWork((ArrayList<String>)arg);
	}
	
	class richiestaNode extends DefaultMutableTreeNode implements Observer{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6286210896311354098L;
		ArrayList<String> ass;

		public richiestaNode(ArrayList<String> newAss) {
			ass = newAss;
		}
		public String getId(){
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
			addRichiesta((ArrayList<String>)arg1);
		}
	}
}


