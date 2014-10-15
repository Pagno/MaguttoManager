package view.lavoro.treenode;

import javax.swing.tree.DefaultMutableTreeNode;


	public class NodeAdder extends DefaultMutableTreeNode {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6628712095965266106L;
		String nome;
		
		public NodeAdder(String nome) {
		this.nome=nome;
		}
		public String toString(){
			return  nome;
		}
	}