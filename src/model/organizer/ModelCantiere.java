package model.organizer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;

import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaMacchina;
import model.organizer.data.Macchina;

//TODO aggiungere Observer aggiunta lavori


public class ModelCantiere extends Observable{

	
	private ArrayList<Cantiere> listaCantieri;
	
	
	private int codice;

	
	private int codiceLavoro;
	
	
	private static ModelCantiere istanza;


	private ModelCantiere(){
		listaCantieri=new ArrayList<Cantiere>();
		codice=0;
		codiceLavoro=0;
	}
	

	public static synchronized ModelCantiere getModelCantiere(){
		if(istanza==null){
			istanza=new ModelCantiere();
		}
		return istanza;
	}
//OPERAZIONI SUI CANTIERI-------------------------------------------------------------------------------------------------------------

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


	public ArrayList<Cantiere> getListaCantieri(){
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

//OPERAZIONI SUI LAVORI----------------------------------------------------------------------------------------------------------------
	
	public Lavoro getLavoro(Integer codiceLavoro){
		for(Cantiere item:listaCantieri){
			for(Lavoro lavoro:item.getElencoLavori()){
				if(lavoro.getCodice()==codiceLavoro)
					return lavoro;
				break;
			}
		}
		return null;
	}
	
	//aggiungo nuovi lavori
	public void aggiungiLavoro(int codiceCantiere, String nome, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		this.codiceLavoro++;
		Cantiere cantiere=getCantiere(codiceCantiere);
		Lavoro lavoro=new Lavoro(codiceLavoro,nome,dataInizio,dataFine);
		//Aggiungo il nuovo lavoro all'elenco dei lavoro del cantiere
		cantiere.addLavoro(lavoro);
	}
	
	//carico i lavori presenti a database
	public void caricaLavoro(int codiceCantiere,int codiceLavoro,String nome, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		if(this.codiceLavoro<codiceLavoro){
			this.codiceLavoro=codiceLavoro;
		}
		Cantiere cantiere=getCantiere(codiceCantiere);
		Lavoro lavoro=new Lavoro(codiceLavoro,nome,dataInizio,dataFine);
		//Aggiungo il nuovo lavoro all'elenco dei lavoro del cantiere
		cantiere.addLavoro(lavoro);
	}
	public void rimuoviLavoro(int codiceCantiere,int codiceLavoro){
		Cantiere cantiere=getCantiere(codiceCantiere);
		cantiere.rimuoviLavoro(codiceLavoro);
	}
	
	public void rimuoviLavoro(int codiceLavoro){
		for(Cantiere item:listaCantieri){
			if(item.hasLavoro(codiceLavoro)){
				item.rimuoviLavoro(codiceLavoro);
			}
		}
	}
	
	public void modificaLavoro(int codiceLavoro, String nome, GregorianCalendar dataInizio,GregorianCalendar dataFine){
		for(Cantiere item:listaCantieri){
			if(item.hasLavoro(codiceLavoro)){
				Lavoro lav=item.getLavoro(codiceLavoro);
				lav.setNome(nome);
				lav.setDataInizio(dataInizio);
				lav.setDataFine(dataFine);
			}
		}
	}
	
	public void modificaLavoro(int codiceCantiere, int codiceLavoro,String nome, GregorianCalendar dataInizio,GregorianCalendar dataFine){
		Cantiere can=getCantiere(codiceCantiere);
		if(can.hasLavoro(codiceLavoro)){
			Lavoro lav=can.getLavoro(codiceLavoro);
			lav.setNome(nome);
			lav.setDataInizio(dataInizio);
			lav.setDataFine(dataFine);
		}
	}
	
	public ArrayList<Lavoro> getListaLavori(int codiceCantiere){
		Cantiere can=getCantiere(codiceCantiere);
		return can.getElencoLavori();
	}
	
	public ArrayList<Lavoro> getListaLavori(){
		ArrayList<Lavoro>totLavori=new ArrayList<Lavoro>();
		for(Cantiere item:listaCantieri){
			for(Lavoro l:item.getElencoLavori()){
				totLavori.add(l);
			}
		}
		if(totLavori.size()==0){
			return null;
		}
		else{
			return totLavori;
		}
	}
	
//OPERAZIONI SULLE RICHIESTE---------------------------------------------------------------------------------------------------------
	
	public Richiesta getRichiesta(Integer codiceRichiesta){
		for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				for(Richiesta item:lav.getListaRichieste()){
					if(item.getCodice()==codiceRichiesta)
						return item;
					break;
				}
			}
		}
		return null;
	}
	
	//Aggiunge una nuova richiesta, che quindi non è soddisfatta
	public void aggiungiRichiesta(int codiceCantiere, int codiceLavoro,RichiestaMacchina caratteristiche){
		Cantiere item=getCantiere(codiceCantiere);
		if(item.hasLavoro(codiceLavoro)){
			Lavoro l=item.getLavoro(codiceLavoro);
			l.inserisciRichiesta(caratteristiche);
		}
	}
	
	//Aggiunge una nuova richiesta, che quindi non è soddisfatta
	public void aggiungiRichiesta(int codiceLavoro,RichiestaMacchina caratteristiche){
		for(Cantiere item:listaCantieri){
			if(item.hasLavoro(codiceLavoro)){
				Lavoro l=item.getLavoro(codiceLavoro);
				l.inserisciRichiesta(caratteristiche);
			}
		}
	}
	
	//Quando voglio caricare una richiesta da DB, devo impostare il codice secondo quanto inserito in precedenza e inserire l'eventuale macchina
	public void caricaRichiesta(int codiceCantiere, int codiceLavoro, int codiceRichiesta, RichiestaMacchina caratteristiche, Macchina macchina){
		Cantiere item=getCantiere(codiceCantiere);
		if(item.hasLavoro(codiceLavoro)){
			Lavoro l=item.getLavoro(codiceLavoro);
			l.caricaRichiesta(caratteristiche,codiceRichiesta,macchina);
		}
	}
	
	public void soddisfaRichiesta(int codiceRichiesta, Macchina m){
		//TODO
	}
	
	public void soddisfaRichiesta(int codiceLavoro,int codiceRichiesta, Macchina m){
		//TODO
	}
	
	public void soddisfaRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta, Macchina m){
		//TODO
	}
	
	public void modificaRichiesta(int codiceRichiesta, RichiestaMacchina caratteristiche){
		//TODO
		//Se è già soddisfatta, modificando le caratteristiche libero la macchina!!!
	}
	
	public void modificaRichiesta(int codiceLavoro,int codiceRichiesta, RichiestaMacchina caratteristiche){
		//TODO
		//Se è già soddisfatta, modificando le caratteristiche libero la macchina!!!
	}
	
	public void modificaRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta,RichiestaMacchina caratteristiche){
		//TODO
		//Se è già soddisfatta, modificando le caratteristiche libero la macchina!!!
	}
	
	public void liberaRichiesta(int codiceRichiesta){
		//TODO
	}
	
	public void liberaRichiesta(int codiceLavoro,int codiceRichiesta){
		//TODO
	}
	
	public void liberaRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta){
		//TODO
	}
	
	public void rimuoviRichiesta(int codiceRichiesta){
		//TODO
	}
	
	public void rimuoviRichiesta(int codiceLavoro,int codiceRichiesta){
		//TODO
	}
	
	public void rimuoviRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta){
		//TODO
	}
	
	public void getListaRichieste(int codiceCantiere, int codiceLavoro){
		//TODO
	}
	
	public void getListaRichieste(int codiceCantiere){
		//TODO
	}
	
	public void getListaRichieste(){
		// TODO
	}
//OPERAZIONI GENERICHE---------------------------------------------------------------------------------------------------------------

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
