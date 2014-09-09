package view.lavoro;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;


	public class workNode extends DefaultMutableTreeNode{
		/**
		 * 
		 */
		private static final long serialVersionUID = -6628712095965266106L;
		
		ArrayList<String> work;
		ArrayList<ArrayList<String>> listaRichieste;

		public workNode(ArrayList<String> w) {
			work=w;
			listaRichieste=new ArrayList<ArrayList<String>>();
		}
		public int getCodiceLavoro(){
			return Integer.parseInt(work.get(0));
		}
		public void addRichiesta(ArrayList<String> newAss){
			listaRichieste.add(newAss);
			//richiestaNode a=new richiestaNode(newAss);
			//add(a);
		}

		public String toString(){
			return  work.get(0)+" - "+work.get(1);
		}
		public ArrayList<String> getData(){
			return work;
		}
		public String getName(){
			return work.get(1);
		}
	}