package model.organizer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.derby.impl.sql.compile.InsertNode;

import view.lavoro.addNode;
import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaMacchina;
import model.organizer.data.Macchina;
import model.organizer.data.Priority;
import model.organizer.data.RichiestaRuspa;




public class ModelCantiere extends DefaultTreeModel{

	
	private ArrayList<Cantiere> listaCantieri;
	private ArrayList<Observer> lavoroObserver=new ArrayList<Observer>();
	private ArrayList<Observer> richiestaObserver=new ArrayList<Observer>();
	
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

	public void aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita){
		codice++;
		Cantiere c=new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura,priorita);
		this.listaCantieri.add(c);

		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("dd/MM/yyyy");
		Object[] v1={codice,nomeCantiere,indirizzo,df.format(dataApertura.getTime()),df.format(dataChiusura.getTime()),priorita.toString()};
		//setChanged();
		//notifyObservers(v1);
		observer.update(null, v1);//notifyObservers(v1);
		addNode add=new addNode("Aggiungi nuovo Lavoro");
		insertNodeInto(add, c, 0);
		Lavoro lav=(Lavoro) c.getFirstChild();
	}

	public void caricaCantiere(Integer codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita){
		if(this.codice<codice){
			this.codice=codice;
		}
		Cantiere c=new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura,priorita);
		this.listaCantieri.add(c);
		
		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("dd/MM/yyyy");
		Object[] v1={codice,nomeCantiere,indirizzo,df.format(dataApertura.getTime()),df.format(dataChiusura.getTime()),priorita.toString()};
		//setChanged();
		observer.update(null, v1);//notifyObservers(v1);
		addNode add=new addNode("Aggiungi nuovo Lavoro");
		insertNodeInto(add, c, 0);
	}


	public void modificaCantiere(Integer codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita){
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
				observer.update(null, v1);//notifyObservers(v1);
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
	
	public void addLavoroObserver(Observer observer) {
		lavoroObserver.add(observer);
	}
	
	//aggiungo nuovi lavori
	public void aggiungiLavoro(int codiceCantiere, String nome, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		this.codiceLavoro++;
		Cantiere cantiere=getCantiere(codiceCantiere);
		Lavoro lavoro=new Lavoro(codiceLavoro,nome,dataInizio,dataFine,cantiere);
		//Aggiungo il nuovo lavoro all'elenco dei lavoro del cantiere
		cantiere.addLavoro(lavoro);
		insertNodeInto(lavoro, cantiere, 0);

		addNode add=new addNode("Aggiungi nuova Richiesta");
		insertNodeInto(add, lavoro, 0);
		
		
		/*SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("dd/MM/yyyy");
		ArrayList<String> v1=new ArrayList<String>();
		v1.add(Integer.toString(codiceLavoro));v1.add(nome);
		v1.add(df.format(dataInizio.getTime()));
		v1.add(df.format(dataFine.getTime()));//Cantiere,indirizzo,df.format(dataApertura.getTime()),df.format(dataChiusura.getTime())};
		for(Observer ob:lavoroObserver){
			ob.update(this, v1);
		}*/

	}
	
	//carico i lavori presenti a database
	public void caricaLavoro(int codiceCantiere,int codiceLavoro,String nome, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		if(this.codiceLavoro<codiceLavoro){
			this.codiceLavoro=codiceLavoro;
		}
		Cantiere cantiere=getCantiere(codiceCantiere);
		Lavoro lavoro=new Lavoro(codiceLavoro,nome,dataInizio,dataFine,cantiere);
		//Aggiungo il nuovo lavoro all'elenco dei lavoro del cantiere
		cantiere.addLavoro(lavoro);
		insertNodeInto(lavoro, cantiere, 0);
		addNode add=new addNode("Aggiungi nuova Richiesta");
		insertNodeInto(add, lavoro, 0);
	}
	public void rimuoviLavoro(int codiceCantiere,int codiceLavoro){
		Cantiere cantiere=getCantiere(codiceCantiere);
		cantiere.rimuoviLavoro(codiceLavoro);
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
		return totLavori;
	}
	
	public ArrayList<Lavoro> getListaScoperti(int codiceCantiere){
		ArrayList<Lavoro>temp=new ArrayList<Lavoro>();
		Cantiere can=getCantiere(codiceCantiere);
		for(Lavoro l:can.getElencoLavori()){
			if(l.isScoperto()){
				temp.add(l);
			}
		}
		if(temp.size()==0){
			return null;
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
		if(temp.size()==0){
			return null;
		}
		return temp;
	}
	
	public ArrayList<Lavoro> getListaCoperti(int codiceCantiere){
		ArrayList<Lavoro>temp=new ArrayList<Lavoro>();
		Cantiere can=getCantiere(codiceCantiere);
		for(Lavoro l:can.getElencoLavori()){
			if(!l.isScoperto()){
				temp.add(l);
			}
		}
		if(temp.size()==0){
			return null;
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
		if(temp.size()==0){
			return null;
		}
		return temp;
	}
	
//OPERAZIONI SULLE RICHIESTE---------------------------------------------------------------------------------------------------------


	public void addRichiestaObserver(Observer observer) {
		richiestaObserver.add(observer);
	}	
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
	public void aggiungiRichiesta(int codiceCantiere, int codiceLavoro,RichiestaMacchina caratteristiche){
		Cantiere item=getCantiere(codiceCantiere);
		System.out.print("CodiceLavoro: "+codiceLavoro);
		if(item.hasLavoro(codiceLavoro)){
			Lavoro l=item.getLavoro(codiceLavoro);
			l.inserisciRichiesta(caratteristiche);
		}
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
		if(item.hasLavoro(codiceLavoro)){
			Lavoro l=item.getLavoro(codiceLavoro);
			l.caricaRichiesta(caratteristiche,codiceRichiesta,macchina);
		}
	}
	
	public void soddisfaRichiesta(int codiceRichiesta, Macchina m){
		ciclo:for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					lav.soddisfaRichiesta(codiceRichiesta, m);
					break ciclo;
				}
			}
		}
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
		if(can.hasLavoro(codiceLavoro)){
			Lavoro l=getLavoro(codiceLavoro);
			if(l.hasRichiesta(codiceRichiesta)){
				l.soddisfaRichiesta(codiceRichiesta, m);
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
		if(can.hasLavoro(codiceLavoro)){
			Lavoro l=getLavoro(codiceLavoro);
			if(l.hasRichiesta(codiceRichiesta)){
				l.getRichiesta(codiceRichiesta).setCaratteristiche(caratteristiche);
				l.liberaRichiesta(codiceRichiesta);
			}
		}
	}
	
	public void liberaRichiesta(int codiceRichiesta){
		ciclo:for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					lav.liberaRichiesta(codiceRichiesta);
					break ciclo;
				}
			}
		}
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
		if(can.hasLavoro(codiceLavoro)){
			Lavoro l=getLavoro(codiceLavoro);
			if(l.hasRichiesta(codiceRichiesta)){
				l.liberaRichiesta(codiceRichiesta);
			}
		}
	}
	
	public boolean rimuoviRichiesta(int codiceRichiesta){
		for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					lav.eliminaRichiesta(codiceRichiesta);
					return true;
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
		if(can.hasLavoro(codiceLavoro)){
			Lavoro l=getLavoro(codiceLavoro);
			if(l.hasRichiesta(codiceRichiesta)){
				l.eliminaRichiesta(codiceRichiesta);
			}
		}
	}
	
	public ArrayList<Richiesta> getListaSoddisfatte(int codiceCantiere, int codiceLavoro){
		Cantiere can=getCantiere(codiceCantiere);
		if(can.hasLavoro(codiceLavoro)){
			ArrayList<Richiesta>temp=new ArrayList<Richiesta>();
			for(Richiesta r:can.getLavoro(codiceLavoro).getListaRichieste()){
				if(r.isSoddisfatta()){
					temp.add(r);
				}
			}
			if(temp.size()!=0){
				return temp;
			}
		}
		return null;
	}
	
	public ArrayList<Richiesta> getListaSoddisfatte(int codiceCantiere){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		for(Lavoro l:getCantiere(codiceCantiere).getElencoLavori()){
			for(Richiesta r:l.getListaRichieste()){
				if(r.isSoddisfatta()){
					totRichieste.add(r);
				}
			}
		}
		if(totRichieste.size()==0){
			return null;
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
		if(totRichieste.size()==0){
			return null;
		}
		return totRichieste;
	}
	
	public ArrayList<Richiesta> getListaInsoddisfatte(int codiceCantiere, int codiceLavoro){
		Cantiere can=getCantiere(codiceCantiere);
		if(can.hasLavoro(codiceLavoro)){
			ArrayList<Richiesta>temp=new ArrayList<Richiesta>();
			for(Richiesta r:can.getLavoro(codiceLavoro).getListaRichieste()){
				if(!r.isSoddisfatta()){
					temp.add(r);
				}
			}
			if(temp.size()!=0){
				return temp;
			}
		}
		return null;
	}
	
	public ArrayList<Richiesta> getListaInsoddisfatte(int codiceCantiere){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		for(Lavoro l:getCantiere(codiceCantiere).getElencoLavori()){
			for(Richiesta r:l.getListaRichieste()){
				if(!r.isSoddisfatta()){
					totRichieste.add(r);
				}
			}
		}
		if(totRichieste.size()==0){
			return null;
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
		if(totRichieste.size()==0){
			return null;
		}
		return totRichieste;
	}
	
	public ArrayList<Richiesta> getListaRichieste(int codiceCantiere, int codiceLavoro){
		Cantiere can=getCantiere(codiceCantiere);
		if(can.hasLavoro(codiceLavoro)){
			return can.getLavoro(codiceLavoro).getListaRichieste();
		}
		return null;
	}
	
	public ArrayList<Richiesta> getListaRichieste(int codiceCantiere){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		for(Lavoro l:getCantiere(codiceCantiere).getElencoLavori()){
			for(Richiesta r:l.getListaRichieste()){
				totRichieste.add(r);
			}
		}
		if(totRichieste.size()==0){
			return null;
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
		if(totRichieste.size()==0){
			return null;
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

	//Metodi realizzati appositamente per il testing della classe.
	
	
	int getNextCodice(){
		return codice+1;
	}
		
	
	static void resetForTest(){
		if(istanza!=null){
			istanza=null;
		}
	}

	Observer observer;
	public void addObserver(Observer ob) {
		this.observer=ob;
	}



}
