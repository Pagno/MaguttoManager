package view.lavoro;

import javax.swing.tree.DefaultMutableTreeNode;


	public class addNode extends DefaultMutableTreeNode {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6628712095965266106L;
		String nome;
		
		public addNode(String nome) {
		this.nome=nome;
		}
		public String toString(){
			return  nome;
		}
	}