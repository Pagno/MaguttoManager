package view.lavoro.treenode;

import javax.swing.tree.DefaultMutableTreeNode;


	/**
	 * Classe usate nella JTree.
	 */
	public class NodeAdder extends DefaultMutableTreeNode {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -6628712095965266106L;
		
		/** The nome. */
		String nome;
		
		/**
		 * Istanzia un nuovo NodeAdder.
		 *
		 * @param nome nome del nodo
		 */
		public NodeAdder(String nome) {
		this.nome=nome;
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.tree.DefaultMutableTreeNode#toString()
		 */
		public String toString(){
			return  nome;
		}
	}