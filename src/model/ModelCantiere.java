package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;

class ModelCantiere extends Observable{

	private ArrayList<Cantiere> listaCantieri;
	private Integer codice;
	private static ModelCantiere istanza;

	private ModelCantiere(){
		listaCantieri=new ArrayList<Cantiere>();
		codice=0;
	}
	
	public static synchronized ModelCantiere getModelCantiere(){
		if(istanza==null){
			istanza=new ModelCantiere();
		}
		return istanza;
	}

	public int aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura){
		codice++;
		this.listaCantieri.add(new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura));
		

		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("dd/MM/yyyy");
		Object[] v1={codice,nomeCantiere,indirizzo,df.format(dataApertura.getTime()),df.format(dataChiusura.getTime())};
		setChanged();
		notifyObservers(v1);
		
		return codice;
		
	}

	void caricaCantiere(Integer codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura){
		if(this.codice<codice){
			this.codice=codice;
		}
		this.listaCantieri.add(new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura));
	
		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("dd/MM/yyyy");
		Object[] v1={codice,nomeCantiere,indirizzo,df.format(dataApertura.getTime()),df.format(dataChiusura.getTime())};
		setChanged();
		notifyObservers(v1);
	
	}

	public void modificaCantiere(Integer codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura){
		for(Cantiere item:listaCantieri){
			if(item.getCodice()==codice){
				item.setNomeCantiere(nomeCantiere);
				item.setIndirizzo(indirizzo);
				item.setDataApertura(dataApertura);
				item.setDataChiusura(dataChiusura);
				
				SimpleDateFormat df = new SimpleDateFormat();
			    df.applyPattern("dd/MM/yyyy");
				Object[] v1={codice,nomeCantiere,indirizzo,df.format(dataApertura.getTime()),df.format(dataChiusura.getTime())};
				setChanged();
				notifyObservers(v1);
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

	//Metodi realizzati appositamente per il testing della classe.
	
		int getNextCodice(){
			return codice+1;
		}
		
		static void resetForTest(){
			if(istanza!=null){
				istanza=null;
			}
		}
}
