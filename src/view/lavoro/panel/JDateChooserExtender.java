package view.lavoro.panel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import com.toedter.calendar.JDateChooser;

public class JDateChooserExtender extends com.toedter.calendar.JDateChooser implements PropertyChangeListener{
	
	private static final long serialVersionUID = 59871197754032250L;
	
	
	//TENGI IN MEMORIA I MINIMI DATA CHOOSER
	ArrayList<JDateChooser> min;
	ArrayList<JDateChooser> max;
	String nome;
	
	public JDateChooserExtender(String nome){
		this.nome=nome;
		min=new ArrayList<JDateChooser>();
		max=new ArrayList<JDateChooser>();
	}
	
	public void setMinDataJDateChooser(JDateChooser dataChooser){
		min.add(dataChooser);
		dataChooser.addPropertyChangeListener(this.minPropertyChange());
	}
	public void setMaxDataJDateChooser(JDateChooser dataChooser){
		max.add(dataChooser);
		dataChooser.addPropertyChangeListener(this.maxPropertyChange());
	}
	
	
	private PropertyChangeListener minPropertyChange() {
		return new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				for(JDateChooser jd:min){
					if(jd.getDate()!=null){
						if(getDate().before(jd.getDate())){
							alertExceedMinDate();
						}
							
					}
				}
			}
		};
	}
	
	private void alertExceedMinDate(){
		System.out.println("");
	}
	
	private PropertyChangeListener maxPropertyChange() {
		return new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				//Cerco il minimo
				
			}
		};
	}
}
