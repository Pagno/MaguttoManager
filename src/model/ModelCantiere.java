package model;

import java.util.ArrayList;
import java.util.Date;

class ModelCantiere{
	
	ArrayList<Cantiere> listaCantieri;
	
	
	public ModelCantiere(){
		listaCantieri=new ArrayList<Cantiere>();
	}
	
	public void aggiungiCantiere(Integer codice,String nomeCantiere,String indirizzo,Date dataApertura,Date dataChiusura){
		this.listaCantieri.add(new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura));
	}
	
	public void rimuoviCantiere(String nomeCantiere){
		for(Cantiere item:listaCantieri){
			if(item.getNomeCantiere().equals(nomeCantiere)){
				this.listaCantieri.remove(item);
				break;
			}
		}
	
	}
	public ArrayList<Cantiere> getLista(){
		    return listaCantieri;
	}
	
}