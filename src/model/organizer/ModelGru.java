package model.organizer;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Gru;


/**
 *   La classe ModelGru permette di gestire tutte le istanze della classe Gru.
 *   <p>
 *   Permette quindi di inserire, modificare o eliminare gru.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class ModelGru extends ModelMacchina{
	
	/**   Lista delle gru. */
	private ArrayList<Gru> listaGru;
	
	/**   Istanza di ModelGru. */
	private static ModelGru istanza;

	/**
	 * Istanzia un nuovo ModelGru.
	 */
	private ModelGru(){
		listaGru=new ArrayList<Gru>();
	}

	/**
	 * Restituisce una nuova istanza di ModelGru se non ne &egrave; stata istanziata una in precedenza, altrimenti restituisce
	 * l'istanza realizzata in precedenza.<p>
	 * Questo metodo permette di adottare il pattern Singleton.
	 *
	 * @return L'istanza di ModelCamion
	 */
	public static synchronized ModelGru getModelGru(){
		if(istanza==null){
			istanza=new ModelGru();
		}
		return istanza;
	}

	/**
	 * Aggiunge una gru alla lista.
	 *
	 * @param produttore Il produttore della gru
	 * @param modello Il modello della gru
	 * @param rotazione L'angolo di rotazione della gru
	 * @param portata La portata della gru
	 * @param lunghezza La lunghezza della gru
	 * @param altezza L'altezza della gru
	 */
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		incrementaCodice();
		Gru gru=new Gru(getCodice(),produttore,modello, rotazione, portata,lunghezza,altezza);
		listaGru.add(gru);
		

		Object[] v1={gru.getCodice(),produttore,modello,lunghezza,altezza,portata,rotazione};
		setChanged();
		notifyObservers(v1);
	}
	
	/**
	 * Carica una gru nella lista.<p>
	 * Da usare esclusivamente quando si caricano le gru da database.
	 *
	 * @param codice Il codice della gru
	 * @param produttore Il produttore della gru
	 * @param modello Il modello della gru
	 * @param rotazione L'angolo di rotazione della gru
	 * @param portata La portata della gru
	 * @param lunghezza La lunghezza della gru
	 * @param altezza L'altezza della gru
	 */
	public void caricaGru(int codice, String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		aggiornaCodice(codice);
		Gru gru=new Gru(codice,produttore,modello, rotazione, portata,lunghezza,altezza);
		listaGru.add(gru);
		

		Object[] v1={gru.getCodice(),produttore,modello,lunghezza,altezza,portata,rotazione};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Modifica la gru identificata dal codice inserito.
	 *
	 * @param codice Il codice della gru
	 * @param produttore Il produttore della gru
	 * @param modello Il modello della gru
	 * @param rotazione L'angolo di rotazione della gru
	 * @param portata La portata della gru
	 * @param lunghezza La lunghezza della gru
	 * @param altezza L'altezza della gru
	 */
	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
				item.setModello(modello);
				item.setLunghezzaMassima(lunghezza);
				item.setPortataMassima( portata);
				item.setAltezza( altezza);
				item.setAngoloRotazione(rotazione);
			}
		}
		Object[] v1={codice,produttore,modello,lunghezza,altezza,portata,rotazione};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Elimina la gru identificata dal codice inserito.
	 *
	 * @param codice Il codice della gru
	 * @return true, se la gru viene eliminata correttamente
	 */
	public boolean eliminaGru(int codice){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				item.liberaRichieste();
				return listaGru.remove(item);
			}
		}
		return false;		
	}
	
	/**
	 * Restituisce la lista delle ruspe.
	 *
	 * @return La lista delle ruspe
	 */
	public ArrayList<Gru> getLista(){
		return listaGru;
	}

	/**
	 * Verifica se la macchina che possiede il codice indicato è effettivamente una gru.
	 *
	 * @param codice Il codice della macchina desiderata
	 * @return true, se il codice indicato appartiene a una gru
	 */
	public boolean isGru(Integer codice){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				return true;
			}
		}
		return false;	
	}

	/**
	 * Restituisce la gru indentificata dal codice inserito.
	 *
	 * @param codice Il codice della gru desiderata
	 * @return La gru desiderata
	 */
	public Gru getGru(Integer codice){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Restituisce la lista contenente le gru disponibili nel periodo indicato dalle due date.
	 *
	 * @param dataInizio La data d'inizio del periodo
	 * @param dataFine La data di fine del periodo
	 * @return La lista delle gru disponibili nel periodo indicato
	 */
	public ArrayList<Gru> getDisponibili(GregorianCalendar dataInizio,GregorianCalendar dataFine){
		ArrayList<Gru> listaLibera=new ArrayList<Gru>();
		
		for(Gru gru:listaGru){
			if(gru.isLibera(dataInizio, dataFine)){
				listaLibera.add(gru);
			}
		}
		return listaLibera;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String tmp = "";
		for(Gru item:listaGru){
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
		listaGru.clear();
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
	
	/**
	 * Aggiunge una Gru per effettuare i test.
	 * 
	 * @param gru La gru da inserire
	 */
	public void aggiungiGruForTest(Gru gru){
		aggiornaCodice(gru.getCodice());
		listaGru.add(gru);
	}
}
