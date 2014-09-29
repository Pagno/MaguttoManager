package view.lavoro;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;

public class addNodeRenderer implements TreeCellRenderer {
	Boolean showSoddisfatta=true;
	JLabel lbl,lblAdd;
	DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
	
	public void set(boolean bo){
		showSoddisfatta=bo;
	}
	
	public addNodeRenderer(){
		lblAdd=new JLabel();
		lbl=new JLabel();
		lblAdd.setBackground(Color.blue);
		lblAdd.setForeground(Color.RED);
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree arg0, Object arg1,
			boolean arg2, boolean arg3, boolean arg4, int arg5, boolean arg6) {

	    Component returnValue = null;
	    if ((arg1 != null) && (arg1 instanceof DefaultMutableTreeNode)){
	    	if((DefaultMutableTreeNode) arg1 instanceof Lavoro){
				Lavoro ric=(Lavoro)arg1;
				lbl.setText(ric.toString());
				if(showSoddisfatta==false){
					if(ric.hasRichiestaInsoddisfatta()==false)
						lbl.setText("");
				}
				returnValue=lbl;
			}else if((DefaultMutableTreeNode) arg1 instanceof Richiesta){
				Richiesta ric=(Richiesta)arg1;
				lbl.setText(ric.toString());
				if(showSoddisfatta==false){
					if(ric.isSoddisfatta())
						lbl.setText("");
				}
				returnValue=lbl;
			}else  if((DefaultMutableTreeNode) arg1 instanceof addNode){
				lblAdd.setText(showSoddisfatta==false?"":arg1.toString());
				returnValue=lblAdd;
			}
		}
	    if (returnValue == null) 
	        returnValue = defaultRenderer.getTreeCellRendererComponent(arg0,
	            arg1, arg2, arg3, arg4, arg5, arg6);
	    return returnValue;
	    
	}

}
