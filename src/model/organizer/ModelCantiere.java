package model.organizer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;

import model.organizer.data.Cantiere;

// 
/**
 *   Class ModelCantiere.
 */
public class ModelCantiere extends Observable{

	/**   lista cantieri. */
	private ArrayList<Cantiere> listaCantieri;
	
	/**   codice. */
	private Integer codice;
	
	/**   istanza. */
	private static ModelCantiere istanza;

	/**
	 * Instantiates a new model cantiere.
	 */
	private ModelCantiere(){
		listaCantieri=new ArrayList<Cantiere>();
		codice=0;
	}
	
	/**
	 * Gets   model cantiere.
	 *
	 * @return   model cantiere
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
	 * @param nomeCantiere   nome cantiere
	 * @param indirizzo   indirizzo
	 * @param dataApertura   data apertura
	 * @param dataChiusura   data chiusura
	 * @return   int
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
	 * @param codice   codice
	 * @param nomeCantiere   nome cantiere
	 * @param indirizzo   indirizzo
	 * @param dataApertura   data apertura
	 * @param dataChiusura   data chiusura
	 */
	public void caricaCantiere(Integer codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura){
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
	 * @param codice   codice
	 * @param nomeCantiere   nome cantiere
	 * @param indirizzo   indirizzo
	 * @param dataApertura   data apertura
	 * @param dataChiusura   data chiusura
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
	 * @param nomeCantiere   nome cantiere
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
	 * @param codice   codice
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
	 * Gets   lista.
	 *
	 * @return   lista
	 */
	public ArrayList<Cantiere> getLista(){
		    return listaCantieri;
	}

	/**
	 * Gets   cantiere.
	 *
	 * @param codice   codice
	 * @return   cantiere
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
	 * Gets   next codice.
	 *
	 * @return   next codice
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
