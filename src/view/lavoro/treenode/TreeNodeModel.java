package view.lavoro.treenode;
import javax.swing.tree.DefaultTreeModel;

import model.organizer.data.Cantiere;


/**
 * The Class TreeNodeModel.
 * 
 * @author Matteo Pagnoncelli
 * @author Mauro Valota
 */
public class TreeNodeModel extends DefaultTreeModel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8141324938219265208L;

	/** Cantiere */
	Cantiere c;
	
	/**
	 * Istanzia il modello del tree node.
	 *
	 * @param cantiere Il Cantiere da visualizzare
	 */
	public TreeNodeModel(Cantiere cantiere) {
		super(null);
		setRoot(cantiere);
	}
	
}


