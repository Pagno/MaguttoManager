package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

class ModelCantiere{
	
	private ArrayList<Cantiere> listaCantieri;
	private Integer codice;
	
	public ModelCantiere(){
		listaCantieri=new ArrayList<Cantiere>();
		codice=0;
	}
	
	public void aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura){
		codice++;
		this.listaCantieri.add(new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura));
	}
	
	void caricaCantiere(Integer codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura){
		if(this.codice<codice){
			this.codice=codice;
		}
		this.listaCantieri.add(new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura));
	}
	
	public void modificaCantiere(Integer codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura){
		Iterator<Cantiere> itr = listaCantieri.iterator();
		while (itr.hasNext()) {
			if(itr.next().getCodice()==codice){
				itr.next().setNomeCantiere(nomeCantiere);
				itr.next().setIndirizzo(indirizzo);
				itr.next().setDataApertura(dataApertura);
				itr.next().setDataChiusura(dataChiusura);
			}
		}
	}
	
	public boolean rimuoviCantiere(String nomeCantiere){
		for(Cantiere item:listaCantieri){
			if(item.getNomeCantiere().equals(nomeCantiere)){
				this.listaCantieri.remove(item);
				return true;
			}
		}
		return false;
	
	}
	
	public boolean rimuoviCantiere(int codice){
		for(Cantiere item:listaCantieri){
			if(item.getCodice() == codice){
				this.listaCantieri.remove(item);
				return true;
			}
		}
		return false;
	
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
	
	public String toString(){
		String tmp = "";
		for(Cantiere item:listaCantieri){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}
	
}