package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelCantiere.
 */
class ModelCantiere extends Observable{

	/** The lista cantieri. */
	private ArrayList<Cantiere> listaCantieri;
	
	/** The codice. */
	private Integer codice;
	
	/** The istanza. */
	private static ModelCantiere istanza;

	/**
	 * Instantiates a new model cantiere.
	 */
	private ModelCantiere(){
		listaCantieri=new ArrayList<Cantiere>();
		codice=0;
	}
	
	/**
	 * Gets the model cantiere.
	 *
	 * @return the model cantiere
	 */
	public static synchronized ModelCantiere getModelCantiere(){
		if(istanza==null){
			istanza=new ModelCantiere();
		}
		return istanza;
	}

	/**
	 * Aggiungi cantiere.
	 *
	 * @param nomeCantiere the nome cantiere
	 * @param indirizzo the indirizzo
	 * @param dataApertura the data apertura
	 * @param dataChiusura the data chiusura
	 * @return the int
	 */
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

	/**
	 * Carica cantiere.
	 *
	 * @param codice the codice
	 * @param nomeCantiere the nome cantiere
	 * @param indirizzo the indirizzo
	 * @param dataApertura the data apertura
	 * @param dataChiusura the data chiusura
	 */
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

	/**
	 * Modifica cantiere.
	 *
	 * @param codice the codice
	 * @param nomeCantiere the nome cantiere
	 * @param indirizzo the indirizzo
	 * @param dataApertura the data apertura
	 * @param dataChiusura the data chiusura
	 */
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

	/**
	 * Rimuovi cantiere.
	 *
	 * @param nomeCantiere the nome cantiere
	 * @return true, if successful
	 */
	public boolean rimuoviCantiere(String nomeCantiere){
		for(Cantiere item:listaCantieri){
			if(item.getNomeCantiere().equals(nomeCantiere)){
				this.listaCantieri.remove(item);
				return true;
			}
		}
		return false;

	}

	/**
	 * Rimuovi cantiere.
	 *
	 * @param codice the codice
	 * @return true, if successful
	 */
	public boolean rimuoviCantiere(int codice){
		for(Cantiere item:listaCantieri){
			if(item.getCodice() == codice){
				this.listaCantieri.remove(item);
				return true;
			}
		}
		return false;

	}

	/**
	 * Gets the lista.
	 *
	 * @return the lista
	 */
	public ArrayList<Cantiere> getLista(){
		    return listaCantieri;
	}

	/**
	 * Gets the cantiere.
	 *
	 * @param codice the codice
	 * @return the cantiere
	 */
	public Cantiere getCantiere(Integer codice){
		for(Cantiere item:listaCantieri){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String tmp = "";
		for(Cantiere item:listaCantieri){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}

	//Metodi realizzati appositamente per il testing della classe.
	
	/**
	 * Gets the next codice.
	 *
	 * @return the next codice
	 */
	int getNextCodice(){
		return codice+1;
	}
		
	/**
	 * Reset for test.
	 */
	static void resetForTest(){
		if(istanza!=null){
			istanza=null;
		}
	}
}
