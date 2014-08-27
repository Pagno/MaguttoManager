package view.lavoro;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;


	public class workNode extends DefaultMutableTreeNode  implements Observer {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6628712095965266106L;
		
		ArrayList<String> work;
		ArrayList<ArrayList<String>> listaAssociazioni;

		public workNode(ArrayList<String> w) {
			work=w;
			listaAssociazioni=new ArrayList<ArrayList<String>>();
		}
		public String getId(){
			return work.get(0);
		}
		public void addAssociazione(ArrayList<String> newAss){
			listaAssociazioni.add(newAss);
			workNode a=new workNode(newAss);
			insert(a, 0);
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
		@Override
		public void update(Observable o, Object arg) {
						
		}
	}