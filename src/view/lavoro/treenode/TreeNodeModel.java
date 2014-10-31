package view.lavoro.treenode;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import model.organizer.data.Cantiere;


public class TreeNodeModel extends DefaultTreeModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141324938219265208L;

	Cantiere c;
	public TreeNodeModel(Cantiere cantiere) {
		super(null);
		setRoot(cantiere);
	}
	
}


