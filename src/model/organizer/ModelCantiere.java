package model.organizer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

import java.util.Observer;

import javax.swing.tree.DefaultTreeModel;


import view.lavoro.addNode;
import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaMacchina;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;




public class ModelCantiere extends DefaultTreeModel{

	//TODO lavoroObserver e RichiestaObserver servono ancora? li ho commentati e non mi risultano errori in altre classi
	//TODO anche per addRichiestaObserver vale la stessa cosa
	
	private ArrayList<Cantiere> listaCantieri;

	private Observer lavoroObserver;
	private Observer richiestaObserver;
	private Observer cantiereObserver;
	
	
	private int codice;

	
	private int codiceLavoro;
	
	
	private static ModelCantiere istanza;


	private ModelCantiere(){
		super(null);
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

	public void aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita){
		codice++;
		Cantiere c=new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura,priorita);
		this.listaCantieri.add(c);

		
		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("dd/MM/yyyy");
		Object[] v1={codice,nomeCantiere,indirizzo,df.format(dataApertura.getTime()),df.format(dataChiusura.getTime()),priorita.toString()};
		//setChanged();
		//notifyObservers(v1);
		cantiereObserver.update(null, v1);//notifyObservers(v1);
		addNode add=new addNode("Aggiungi nuovo Lavoro");
		insertNodeInto(add, c, 0);
	}

	public void caricaCantiere(Integer codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita){
		if(this.codice<codice){
			this.codice=codice;
		}
		Cantiere c=new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura,priorita);
		this.listaCantieri.add(c);
		
		
		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("dd/MM/yyyy");
		Object[] v1={codice,nomeCantiere,indirizzo,df.format(dataApertura.getTime()),df.format(dataChiusura.getTime()),priorita.toString()};
		//setChanged();
		cantiereObserver.update(null, v1);//notifyObservers(v1);
		addNode add=new addNode("Aggiungi nuovo Lavoro");
		insertNodeInto(add, c, 0);
		
	}


	public void modificaCantiere(Integer codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita){
		for(Cantiere item:listaCantieri){
			if(item.getCodice()==codice){
				item.setNomeCantiere(nomeCantiere);
				item.setIndirizzo(indirizzo);
				item.setDataApertura(dataApertura);
				item.setDataChiusura(dataChiusura);
				item.setPriorita(priorita);
				
				SimpleDateFormat df = new SimpleDateFormat();
			    df.applyPattern("dd/MM/yyyy");
				Object[] v1={codice,nomeCantiere,indirizzo,df.format(dataApertura.getTime()),df.format(dataChiusura.getTime()),priorita.toString()};
				//setChanged();
				//notifyObservers(v1);
				cantiereObserver.update(null, v1);//notifyObservers(v1);
			}
		}
	}


	public boolean rimuoviCantiere(String nomeCantiere){
		/*TODO controllare se viene utilizzato:
		 * se non viene utilizzato eliminarlo
		 * se viene utilizzato fare in modo che la stringa sia univoca in inserimenti, caricamenti e modifiche
		 */
		for(Cantiere item:listaCantieri){
			if(item.getNomeCantiere().equals(nomeCantiere)){
				item.svuotaLavori();
				this.listaCantieri.remove(item);
				return true;
			}
		}
		return false;

	}


	public boolean rimuoviCantiere(int codice){
		for(Cantiere cantiere:listaCantieri){
			if(cantiere.getCodice() == codice){
				cantiere.svuotaLavori();
				this.listaCantieri.remove(cantiere);
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
			if(item.hasLavoro(codiceLavoro)){
				return item.getLavoro(codiceLavoro);
			}
		}
		return null;
	}
	
	//aggiungo nuovi lavori
	public void aggiungiLavoro(int codiceCantiere, String nome, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		this.codiceLavoro++;
		Cantiere cantiere=getCantiere(codiceCantiere);
		

		Lavoro lavoro=new Lavoro(codiceLavoro,nome,cantiere,dataInizio,dataFine);

		//Aggiungo il nuovo lavoro all'elenco dei lavoro del cantiere
		cantiere.addLavoro(lavoro);
		insertNodeInto(lavoro, cantiere, 0);

		addNode add=new addNode("Aggiungi nuova Richiesta");
		insertNodeInto(add, lavoro, 0);
		

		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		/*ArrayList<String> lav=new ArrayList<String>(Arrays.asList(
				Integer.toString(codiceLavoro),nome,df.format(dataInizio.getTime()),df.format(dataFine.getTime())
				));
		lavoroObserver.update(null, lav.toArray());//notifyObservers(v1);
*/
	}
	
	//carico i lavori presenti a database
	public void caricaLavoro(int codiceCantiere,int codiceLavoro,String nome, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		if(this.codiceLavoro<codiceLavoro){
			this.codiceLavoro=codiceLavoro;
		}
		Cantiere cantiere=getCantiere(codiceCantiere);
		Lavoro lavoro=new Lavoro(codiceLavoro,nome,cantiere,dataInizio,dataFine);

		//Aggiungo il nuovo lavoro all'elenco dei lavoro del cantiere
		cantiere.addLavoro(lavoro);
		insertNodeInto(lavoro, cantiere, 0);
		addNode add=new addNode("Aggiungi nuova Richiesta");
		insertNodeInto(add, lavoro, 0);
	}
	
	public void rimuoviLavoro(int codiceCantiere,int codiceLavoro){
		Cantiere cantiere=getCantiere(codiceCantiere);
		if(cantiere!=null){
			cantiere.rimuoviLavoro(codiceLavoro);
		}
	}
	
	public boolean rimuoviLavoro(int codiceLavoro){
		for(Cantiere item:listaCantieri){
			if(item.hasLavoro(codiceLavoro)){
				item.rimuoviLavoro(codiceLavoro);
				return true;
			}
		}
		return false;
	}
	
	public void modificaLavoro(int codiceLavoro, String nome, GregorianCalendar dataInizio,GregorianCalendar dataFine){
		for(Cantiere item:listaCantieri){
			if(item.hasLavoro(codiceLavoro)){
				Lavoro lav=item.getLavoro(codiceLavoro);
				lav.setNome(nome);
				lav.setDataInizio(dataInizio);
				lav.setDataFine(dataFine);
				break;
			}
		}
	}
	
	public void modificaLavoro(int codiceCantiere, int codiceLavoro,String nome, GregorianCalendar dataInizio,GregorianCalendar dataFine){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro lav=can.getLavoro(codiceLavoro);
				lav.setNome(nome);
				lav.setDataInizio(dataInizio);
				lav.setDataFine(dataFine);
			}
		}
	}
	
	public ArrayList<Lavoro> getListaLavori(int codiceCantiere){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			return can.getElencoLavori();
		}
		else return new ArrayList<Lavoro>();
	}
	
	public ArrayList<Lavoro> getListaLavori(){
		ArrayList<Lavoro>totLavori=new ArrayList<Lavoro>();
		for(Cantiere item:listaCantieri){
			for(Lavoro l:item.getElencoLavori()){
				totLavori.add(l);
			}
		}
		return totLavori;
	}
	
	public ArrayList<Lavoro> getListaScoperti(int codiceCantiere){
		ArrayList<Lavoro>temp=new ArrayList<Lavoro>();
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			for(Lavoro l:can.getElencoLavori()){
				if(l.isScoperto()){
					temp.add(l);
				}
			}
		}
		return temp;
	}
	
	public ArrayList<Lavoro> getListaScoperti(){
		ArrayList<Lavoro>temp=new ArrayList<Lavoro>();
		for(Cantiere item:listaCantieri){
			for(Lavoro l:item.getElencoLavori()){
				if(l.isScoperto()){
					temp.add(l);
				}
			}
		}
		return temp;
	}
	
	public ArrayList<Lavoro> getListaCoperti(int codiceCantiere){
		ArrayList<Lavoro>temp=new ArrayList<Lavoro>();
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			for(Lavoro l:can.getElencoLavori()){
				if(!l.isScoperto()){
					temp.add(l);
				}
			}
		}
		return temp;
	}
	
	public ArrayList<Lavoro> getListaCoperti(){
		ArrayList<Lavoro>temp=new ArrayList<Lavoro>();
		for(Cantiere item:listaCantieri){
			for(Lavoro l:item.getElencoLavori()){
				if(!l.isScoperto()){
					temp.add(l);
				}
			}
		}
		return temp;
	}
	
	int getNextCodiceLavoro(){
		int code=this.codiceLavoro+1;
		return code;
	}
	
//OPERAZIONI SULLE RICHIESTE---------------------------------------------------------------------------------------------------------


	public Richiesta getRichiesta(Integer codiceRichiesta){
		for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					return lav.getRichiesta(codiceRichiesta);
				}
			}
		}
		return null;
	}
	

	//Aggiunge una nuova richiesta, che quindi non � soddisfatta
	public boolean aggiungiRichiesta(int codiceCantiere, int codiceLavoro,RichiestaMacchina caratteristiche){

		Cantiere item=getCantiere(codiceCantiere);

		if(item!=null){
			if(item.hasLavoro(codiceLavoro)){			Lavoro l=item.getLavoro(codiceLavoro);
			l.inserisciRichiesta(caratteristiche);
			return true;
			}

		}
		return false;
	}
	
	//Aggiunge una nuova richiesta, che quindi non � soddisfatta
	public void aggiungiRichiesta(int codiceLavoro,RichiestaMacchina caratteristiche){
		for(Cantiere item:listaCantieri){
			if(item.hasLavoro(codiceLavoro)){
				Lavoro l=item.getLavoro(codiceLavoro);
				l.inserisciRichiesta(caratteristiche);
				break;
			}
		}
	}
	
	//Quando voglio caricare una richiesta da DB, devo impostare il codice secondo quanto inserito in precedenza e inserire l'eventuale macchina
	public void caricaRichiesta(int codiceCantiere, int codiceLavoro, int codiceRichiesta, RichiestaMacchina caratteristiche, Macchina macchina){
		Cantiere item=getCantiere(codiceCantiere);
		if(item!=null){
			if(item.hasLavoro(codiceLavoro)){
				Lavoro l=item.getLavoro(codiceLavoro);
				l.caricaRichiesta(caratteristiche,codiceRichiesta,macchina);
			}
		}
	}
	
	public boolean soddisfaRichiesta(int codiceRichiesta, Macchina m){
		ciclo:for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					return lav.soddisfaRichiesta(codiceRichiesta, m);
				}
			}
		}
		return false;
	}
	
	public void soddisfaRichiesta(int codiceLavoro,int codiceRichiesta, Macchina m){
		for(Cantiere can:listaCantieri){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.soddisfaRichiesta(codiceRichiesta, m);
					break;
				}
			}
		}
	}
	
	public void soddisfaRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta, Macchina m){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.soddisfaRichiesta(codiceRichiesta, m);
				}
			}
		}
	}
	
	public void modificaRichiesta(int codiceRichiesta, RichiestaMacchina caratteristiche){
		ciclo:for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					lav.getRichiesta(codiceRichiesta).setCaratteristiche(caratteristiche);
					lav.liberaRichiesta(codiceRichiesta);
					break ciclo;
				}
			}
		}
	}
	
	public void modificaRichiesta(int codiceLavoro,int codiceRichiesta, RichiestaMacchina caratteristiche){
		for(Cantiere can:listaCantieri){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.getRichiesta(codiceRichiesta).setCaratteristiche(caratteristiche);
					l.liberaRichiesta(codiceRichiesta);
					break;
				}
			}
		}
	}
	
	public void modificaRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta,RichiestaMacchina caratteristiche){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.getRichiesta(codiceRichiesta).setCaratteristiche(caratteristiche);
					l.liberaRichiesta(codiceRichiesta);
				}
			}
		}
	}
	
	public boolean liberaRichiesta(int codiceRichiesta){
		ciclo:for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					lav.liberaRichiesta(codiceRichiesta);
					return true;
				}
			}
		}
		return false;
	}
	
	public void liberaRichiesta(int codiceLavoro,int codiceRichiesta){
		for(Cantiere can:listaCantieri){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.liberaRichiesta(codiceRichiesta);
					break;
				}
			}
		}
	}
	
	public void liberaRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.liberaRichiesta(codiceRichiesta);
				}
			}
		}
	}
	
	public boolean rimuoviRichiesta(int codiceRichiesta){
		for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					return lav.eliminaRichiesta(codiceRichiesta);
				}
			}
		}
		return false;
	}
	
	public void rimuoviRichiesta(int codiceLavoro,int codiceRichiesta){
		for(Cantiere can:listaCantieri){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.eliminaRichiesta(codiceRichiesta);
					break;
				}
			}
		}
	}
	
	public void rimuoviRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.eliminaRichiesta(codiceRichiesta);
				}
			}
		}
	}

	public ArrayList<Richiesta> getListaSoddisfatte(int codiceCantiere, int codiceLavoro){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null) {
			if(can.hasLavoro(codiceLavoro)){
				for(Richiesta r:can.getLavoro(codiceLavoro).getListaRichieste()){
					if(r.isSoddisfatta()){
						totRichieste.add(r);
					}
				}
			}
		}
		return totRichieste;
	}
	
	public ArrayList<Richiesta> getListaSoddisfatte(int codiceCantiere){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			for(Lavoro l:can.getElencoLavori()){
				for(Richiesta r:l.getListaRichieste()){
					if(r.isSoddisfatta()){
						totRichieste.add(r);
					}
				}
			}
		}
		return totRichieste;
	}
	
	public ArrayList<Richiesta> getListaSoddisfatte(){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		for(Cantiere c:listaCantieri){
			for(Lavoro l:c.getElencoLavori()){
				for(Richiesta r:l.getListaRichieste()){
					if(r.isSoddisfatta()){
						totRichieste.add(r);
					}
				}
			}
		}
		return totRichieste;
	}

	public ArrayList<Richiesta> getListaInsoddisfatte(int codiceCantiere, int codiceLavoro){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				for(Richiesta r:can.getLavoro(codiceLavoro).getListaRichieste()){
					if(!r.isSoddisfatta()){
						totRichieste.add(r);
					}
				}
			}
		}
		return totRichieste;
	}
	
	public ArrayList<Richiesta> getListaInsoddisfatte(int codiceCantiere){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			for(Lavoro l:can.getElencoLavori()){
				for(Richiesta r:l.getListaRichieste()){
					if(!r.isSoddisfatta()){
						totRichieste.add(r);
					}
				}
			}
		}
		return totRichieste;
	}
	
	public ArrayList<Richiesta> getListaInsoddisfatte(){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		for(Cantiere c:listaCantieri){
			for(Lavoro l:c.getElencoLavori()){
				for(Richiesta r:l.getListaRichieste()){
					if(!r.isSoddisfatta()){
						totRichieste.add(r);
					}
				}
			}
		}
		return totRichieste;
	}
	
	public ArrayList<Richiesta> getListaRichieste(int codiceCantiere, int codiceLavoro){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				return can.getLavoro(codiceLavoro).getListaRichieste();
			}
		}
		return new ArrayList<Richiesta>();
	}

	public ArrayList<Richiesta> getListaRichieste(int codiceCantiere){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			for(Lavoro l:can.getElencoLavori()){
				for(Richiesta r:l.getListaRichieste()){
					totRichieste.add(r);
				}
			}
		}
		return totRichieste;
	}
	
	public ArrayList<Richiesta> getListaRichieste(){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		for(Cantiere c:listaCantieri){
			for(Lavoro l:c.getElencoLavori()){
				for(Richiesta r:l.getListaRichieste()){
					totRichieste.add(r);
				}
			}
		}
		return totRichieste;
	}
//OPERAZIONI GENERICHE---------------------------------------------------------------------------------------------------------------

	public String toString(){
		String tmp = "";
		for(Cantiere item:listaCantieri){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}

	Observer observer;
	public void addObserver(Observer ob) {
		this.observer=ob;
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

	public void svuotaCantieriForTest(){
		codice=0;
		codiceLavoro=0;
		Richiesta.initCodice();
		listaCantieri.clear();
	}


	public void aggiungiLavoroObserver(Observer observer) {
		lavoroObserver=observer;
	}

	public void aggiungiRichiestaObserver(Observer observer) {
		richiestaObserver=observer;
	}	
	

}
