package model.organizer;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Ruspa;


/**
 *   La classe ModelRuspa permette di gestire tutte le istanze della classe Ruspa.
 *   <p>
 *   Permette quindi di inserire, modificare o eliminare ruspe.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class ModelRuspa extends ModelMacchina{
	
	/**   Lispa delle ruspe. */
	private ArrayList<Ruspa> listaRuspe;
	
	/**   Istanza della classe ModelRuspa. */
	private static ModelRuspa istanza;

	/**
	 * Istanzia un nuovo ModelRuspa.
	 */
	private ModelRuspa(){
		listaRuspe=new ArrayList<Ruspa>();
	}
	
	/**
	 * Restituisce una nuova istanza di ModelRuspa se non ne &egrave; stata istanziata una in precedenza, altrimenti restituisce
	 * l'istanza realizzata in precedenza.<p>
	 * Questo metodo permette di adottare il pattern Singleton.
	 *
	 * @return L'istanza di ModelRuspa
	 */
	public static synchronized ModelRuspa getModelRuspa(){
		if(istanza==null){
			istanza=new ModelRuspa();
		}
		return istanza;
	}


	/**
	 * Aggiunge una ruspa alla lista.
	 *
	 * @param produttore Il produttore della ruspa
	 * @param modello Il modello della ruspa
	 * @param capacita La capacit&agrave; della ruspa
	 * @param portata La portata della ruspa
	 * @param altezza L'altezza della ruspa
	 */
	public void aggiungiRuspa(String produttore, String modello,int capacita,int portata,int altezza){
		incrementaCodice();
		Ruspa ruspa= new Ruspa(getCodice(), produttore, modello,capacita,portata,altezza);
		listaRuspe.add(ruspa);

		Object[] v1={ruspa.getCodice(),produttore,modello,altezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Carica una ruspa nella lista.<p>
	 * Da usare esclusivamente quando si caricano le ruspe da database.
	 *
	 * @param codice Il codice della ruspa
	 * @param produttore Il produttore della ruspa
	 * @param modello Il modello della ruspa
	 * @param capacita La capacit&agrave; della ruspa
	 * @param portata La portata della ruspa
	 * @param altezza L'altezza della ruspa
	 */
	public void caricaRuspa(int codice,String produttore, String modello,int capacita,int portata,int altezza){
		aggiornaCodice(codice);
		Ruspa ruspa= new Ruspa(codice, produttore, modello,capacita,portata,altezza);
		listaRuspe.add(ruspa);
		
		Object[] v1={ruspa.getCodice(),produttore,modello,altezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Modifica la ruspa identificato dal codice inserito.
	 *
	 * @param codice Il codice della ruspa
	 * @param produttore Il produttore della ruspa
	 * @param modello Il modello della ruspa
	 * @param capacita La capacit&agrave; della ruspa
	 * @param portata La portata della ruspa
	 * @param altezza L'altezza della ruspa
	 */
	public void modificaRuspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
		    	item.setModello(Modello);
		    	item.setCapacitaMassima(capacita);
		    	item.setPortataMassima(portata);
		    	item.setAltezzaMassima(altezza);
			}
		}

		Object[] v1={codice,produttore,Modello,altezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Elimina la ruspa identificata dal codice inserito.
	 *
	 * @param codice Il codice della ruspa
	 * @return true, se la ruspa viene eliminata correttamente
	 */
	public boolean eliminaRuspa(int codice){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				item.liberaRichieste();
				return listaRuspe.remove(item);
			}	
		}
		return false;		
	}

	/**
	 * Restituisce la lista delle ruspe.
	 *
	 * @return La lista delle ruspe
	 */
	public ArrayList<Ruspa> getLista(){
		return listaRuspe;
	}

	/**
	 * Verifica se la macchina che possiede il codice indicato è effettivamente una ruspa.
	 *
	 * @param codice Il codice della macchina desiderata
	 * @return true, se il codice indicato appartiene a una ruspa
	 */
	public boolean isRuspa(Integer codice){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				return true;
			}	
		}
		return false;	
	}

	/**
	 * Restituisce la ruspa indentificata dal codice inserito.
	 *
	 * @param codice Il codice della ruspa desiderata
	 * @return La ruspa desiderata
	 */
	public Ruspa getRuspa(Integer codice){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Restituisce la lista contenente le ruspe disponibili nel periodo indicato dalle due date.
	 *
	 * @param dataInizio La data d'inizio del periodo
	 * @param dataFine La data di fine del periodo
	 * @return La lista delle ruspe disponibili nel periodo indicato
	 */
	public ArrayList<Ruspa> getDisponibili(GregorianCalendar dataInizio,GregorianCalendar dataFine){
		ArrayList<Ruspa>listaLibera=new ArrayList<Ruspa>();
		for(Ruspa ruspa:listaRuspe){
			if(ruspa.isLibera(dataInizio, dataFine)){
				listaLibera.add(ruspa);
			}
		}
		return listaLibera;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String tmp = "";
		for(Ruspa item:listaRuspe){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}
	
	//Metodi realizzati appositamente per il testing della classe.
	

	/**
	 * Resetta la lista e il codice per effettuare i test.
	 */
	public void resetForTest(){
		/*if(istanza!=null){
			ModelMacchina.initCodice();
			istanza=null;
		}*/
		ModelMacchina.initCodice();
		listaRuspe.clear();
	}
	
	/**
	 * Aggiunge una Ruspa per effettuare i test.
	 * 
	 * @param ruspa La ruspa da inserire
	 */
	public void aggiungiRuspaForTest(Ruspa ruspa){
		aggiornaCodice(ruspa.getCodice());
		listaRuspe.add(ruspa);
	}

	/**
	 * Distrugge l'istanza per eseguire i test.
	 */
	public void initForTest(){
		if(istanza!=null){
			ModelMacchina.initCodice();
			istanza=null;
		}
	}
}
