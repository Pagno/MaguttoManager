package model;

import java.util.ArrayList;
import java.util.Date;

class ModelCantiere{
	
	private ArrayList<Cantiere> listaCantieri;
	private Integer codice;
	
	public ModelCantiere(){
		listaCantieri=new ArrayList<Cantiere>();
		codice=0;
	}
	
	public void aggiungiCantiere(String nomeCantiere,String indirizzo,Date dataApertura,Date dataChiusura){
		codice++;
		this.listaCantieri.add(new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura));
	}
	public void caricaCantiere(Integer codice,String nomeCantiere,String indirizzo,Date dataApertura,Date dataChiusura){
		if(this.codice<codice){
			this.codice=codice;
		}
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
	
	public Cantiere getCantiere(Integer codice){
		for(Cantiere item:listaCantieri){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}
	
}