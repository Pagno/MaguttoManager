package view.lavoro;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class addNodeRenderer implements TreeCellRenderer {

	JLabel lbl;
	DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
	
	
	public addNodeRenderer(){
		lbl=new JLabel();
		lbl.setBackground(Color.blue);
		lbl.setForeground(Color.RED);
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree arg0, Object arg1,
			boolean arg2, boolean arg3, boolean arg4, int arg5, boolean arg6) {

	    Component returnValue = null;
	    if ((arg1 != null) && (arg1 instanceof DefaultMutableTreeNode)){
			
			if((DefaultMutableTreeNode) arg1 instanceof addNode){
				lbl.setText(arg1.toString());
				returnValue=lbl;
			}
		}
	    if (returnValue == null) 
	        returnValue = defaultRenderer.getTreeCellRendererComponent(arg0,
	            arg1, arg2, arg3, arg4, arg5, arg6);
	    return returnValue;
	    
	}

}
