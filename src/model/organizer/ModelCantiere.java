package model.organizer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;
import javax.swing.tree.DefaultTreeModel;
import view.lavoro.treenode.NodeAdder;
import model.organizer.data.Cantiere;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaMacchina;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;

/**
 * La classe ModelCantiere permette di gestire i cantieri, i lavori e le richieste.<p>
 * Questa classe crea in pratica un albero di istanze di Cantiere, Lavoro e Richiesta, e definisce quindi i metodi necessari a 
 * gestire tali istanze.
 * 
 * @author Matteo Pagnoncelli
 * @author Mauro Valota
 */
public class ModelCantiere extends DefaultTreeModel{
	/** Lista dei cantieri. */
	private ArrayList<Cantiere> listaCantieri;
	
	/** Observer del lavoro. */
	private Observer lavoroObserver;
	/** Observer della richiesta. */
	private Observer richiestaObserver;
	/** Observer del cantiere. */
	private Observer cantiereObserver;
	/** Ultimo codice usato dai cantieri. */
	private int codice;
	/** Ultimo codice usato dai lavori. */
	private int codiceLavoro;
	/** Istanza della classe ModelCantiere. */
	private static ModelCantiere istanza;
	
	/**
	 * Istanzia un nuovo ModelCantiere.
	 */
	private ModelCantiere(){
		super(null);
		listaCantieri=new ArrayList<Cantiere>();
		codice=0;
		codiceLavoro=0;
	}
	
	/**
	 * Restituisce una nuova istanza di ModelCantiere se non ne &egrave; stata istanziata una in precedenza, altrimenti restituisce
	 * l'istanza realizzata in precedenza.<p>
	 * Questo metodo permette di adottare il pattern Singleton.
	 *
	 * @return L'istanza di ModelCantiere
	 */
	public static synchronized ModelCantiere getModelCantiere(){
		if(istanza==null){
			istanza=new ModelCantiere();
		}
		return istanza;
	}
	
	//OPERAZIONI SUI CANTIERI-------------------------------------------------------------------------------------------------------------
	
	/**
	 * Aggiunge un cantiere alla lista dei cantieri.
	 *
	 * @param nomeCantiere Il nome del cantiere
	 * @param indirizzo L'indirizzo del cantiere
	 * @param dataApertura La data di apertura del cantiere
	 * @param dataChiusura La data di chiusura del cantiere
	 * @param priorita La priorit&agrave; del cantiere
	 */
	public void aggiungiCantiere(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita){
		codice++;
		Cantiere c=new Cantiere(codice,nomeCantiere, indirizzo, dataApertura, dataChiusura,priorita);
		this.listaCantieri.add(c);
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("dd/MM/yyyy");
		Object[] v1={codice,nomeCantiere,indirizzo,df.format(dataApertura.getTime()),df.format(dataChiusura.getTime()),priorita.toString()};
		//setChanged();
		//notifyObservers(v1);
		if(cantiereObserver!=null){
			cantiereObserver.update(null, v1);//notifyObservers(v1);
			NodeAdder add=new NodeAdder("Aggiungi nuovo Lavoro");
			insertNodeInto(add, c, 0);
		}
	}
	
	/**
	 * Carica un cantiere nella lista.<p>
	 * Da usare esclusivamente quando si caricano i cantieri da database.
	 *
	 * @param codice Il codice del cantiere
	 * @param nomeCantiere Il nome del cantiere
	 * @param indirizzo L'indirizzo del cantiere
	 * @param dataApertura La data di apertura del cantiere
	 * @param dataChiusura La data di chiusura del cantiere
	 * @param priorita La priorit&agrave; del cantiere
	 */
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
		if(cantiereObserver!=null){
			cantiereObserver.update(null, v1);//notifyObservers(v1);
			NodeAdder add=new NodeAdder("Aggiungi nuovo Lavoro");
			insertNodeInto(add, c, 0);
		}
	}
	
	/**
	 * Modifica il cantiere identificato dal codice inserito.
	 *
	 * @param codice Il codice del cantiere
	 * @param nomeCantiere Il nome del cantiere
	 * @param indirizzo L'indirizzo del cantiere
	 * @param dataApertura La data di apertura del cantiere
	 * @param dataChiusura La data di chiusura del cantiere
	 * @param priorita La priorit&agrave; del cantiere
	 */
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
				if(cantiereObserver!=null){
					cantiereObserver.update(null, v1);//notifyObservers(v1);
				}
			}
		}
	}
	
	/*/**
	 * Rimuovi cantiere.
	 *
	 * @param nomeCantiere nome del cantiere
	 * @return true, if successful
	 */
	/*public boolean rimuoviCantiere(String nomeCantiere){
		
		for(Cantiere item:listaCantieri){
			if(item.getNomeCantiere().equals(nomeCantiere)){
				item.svuotaLavori();
				this.listaCantieri.remove(item);
				return true;
			}
		}
		return false;
	}*/
	
	/**
	 * Elimina il cantiere identificato dal codice inserito.
	 *
	 * @param codice Il codice del cantiere
	 * @return true, se il cantiere viene eliminato correttamente
	 */
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
	
	/**
	 * Restituisce la lista dei cantieri.
	 *
	 * @return La lista dei cantieri
	 */
	public ArrayList<Cantiere> getListaCantieri(){
		return listaCantieri;
	}
	
	/**
	 * Restituisce il cantiere indentificato dal codice inserito.
	 *
	 * @param codice Il codice del cantiere desiderato
	 * @return Il cantiere desiderato
	 */
	public Cantiere getCantiere(Integer codice){
		for(Cantiere item:listaCantieri){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}
	
	//OPERAZIONI SUI LAVORI----------------------------------------------------------------------------------------------------------------
	
	/**
	 * Restituisce il lavoro indentificato dal codice inserito.
	 *
	 * @param codiceLavoro Il codice del lavoro desiderato
	 * @return Il lavoro desiderato
	 */
	public Lavoro getLavoro(Integer codiceLavoro){
		for(Cantiere item:listaCantieri){
			if(item.hasLavoro(codiceLavoro)){
				return item.getLavoro(codiceLavoro);
			}
		}
		return null;
	}
	
	/**
	 * Aggiunge un lavoro alla lista.
	 *
	 * @param codiceCantiere Il codice del cantiere cui appartiene il lavoro
	 * @param nome Il nome del lavoro
	 * @param dataInizio La data di inizio del lavoro
	 * @param dataFine La data di fine del lavoro
	 */
	public void aggiungiLavoro(int codiceCantiere, String nome, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		this.codiceLavoro++;
		Cantiere cantiere=getCantiere(codiceCantiere);
		Lavoro lavoro=new Lavoro(codiceLavoro,nome,cantiere,dataInizio,dataFine);
		//Aggiungo il nuovo lavoro all'elenco dei lavoro del cantiere
		cantiere.aggiungiLavoro(lavoro);
		insertNodeInto(lavoro, cantiere, 0);
		NodeAdder add=new NodeAdder("Aggiungi nuova Richiesta");
		insertNodeInto(add, lavoro, 0);
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		/*ArrayList<String> lav=new ArrayList<String>(Arrays.asList(
		Integer.toString(codiceLavoro),nome,df.format(dataInizio.getTime()),df.format(dataFine.getTime())
		));
		lavoroObserver.update(null, lav.toArray());//notifyObservers(v1);
		 */
	}
	
	/**
	 * Carica un lavoro nella lista.<p>
	 * Da usare esclusivamente quando si caricano i lavori da database.
	 *
	 * @param codiceCantiere Il codice del cantiere cui appartiene il lavoro
	 * @param codiceLavoro codice del lavoro
	 * @param nome Il nome del lavoro
	 * @param dataInizio La data di inizio del lavoro
	 * @param dataFine La data di fine del lavoro
	 */
	public void caricaLavoro(int codiceCantiere,int codiceLavoro,String nome, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		if(this.codiceLavoro<codiceLavoro){
			this.codiceLavoro=codiceLavoro;
		}
		Cantiere cantiere=getCantiere(codiceCantiere);
		Lavoro lavoro=new Lavoro(codiceLavoro,nome,cantiere,dataInizio,dataFine);
		//Aggiungo il nuovo lavoro all'elenco dei lavoro del cantiere
		cantiere.aggiungiLavoro(lavoro);
		insertNodeInto(lavoro, cantiere, 0);
		NodeAdder add=new NodeAdder("Aggiungi nuova Richiesta");
		insertNodeInto(add, lavoro, 0);
	}
	
	/*/**
	 * Elimina lavoro.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 */
	/*public void eliminaLavoro(int codiceCantiere,int codiceLavoro){
		Cantiere cantiere=getCantiere(codiceCantiere);
		if(cantiere!=null){
			cantiere.rimuoviLavoro(codiceLavoro);
		}
	}*/
	
	/**
	 * Elimina il lavoro identificato dal codice inserito.
	 *
	 * @param codiceLavoro Il codice del lavoro
	 * @return true, se il lavoro viene eliminato correttamente
	 */
	public boolean eliminaLavoro(int codiceLavoro){
		for(Cantiere item:listaCantieri){
			if(item.hasLavoro(codiceLavoro)){
				item.remove(item.getLavoro(codiceLavoro));
				item.rimuoviLavoro(codiceLavoro);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Modifica il lavoro identificato dal codice inserito.
	 *
	 * @param codiceLavoro Il codice del lavoro da modificare
	 * @param nome Il nome del lavoro
	 * @param dataInizio La data di inizio del lavoro
	 * @param dataFine La data di fine del lavoro
	 */
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
	
	/*/**
	 * Modifica lavoro.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 * @param nome nome del lavoro
	 * @param dataInizio data inizio del lavoro
	 * @param dataFine data fine del lavoro
	 */
	/*public void modificaLavoro(int codiceCantiere, int codiceLavoro,String nome, GregorianCalendar dataInizio,GregorianCalendar dataFine){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro lav=can.getLavoro(codiceLavoro);
				lav.setNome(nome);
				lav.setDataInizio(dataInizio);
				lav.setDataFine(dataFine);
			}
		}
	}*/
	
	/*/**
	 * Gets the lista lavori.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @return lista dei lavori
	 */
	/*public ArrayList<Lavoro> getListaLavori(int codiceCantiere){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			return can.getElencoLavori();
		}
		else return new ArrayList<Lavoro>();
	}*/
	
	/*/**
	 * Gets lista dei lavori.
	 *
	 * @return lista dei lavori
	 */
	/*public ArrayList<Lavoro> getListaLavori(){
		ArrayList<Lavoro>totLavori=new ArrayList<Lavoro>();
		for(Cantiere item:listaCantieri){
			for(Lavoro l:item.getElencoLavori()){
				totLavori.add(l);
			}
		}
		return totLavori;
	}*/
	
	/*/**
	 * Gets lista dei lavori scoperti.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @return lista dei lavori scoperti del cantiere
	 */
	/*public ArrayList<Lavoro> getListaScoperti(int codiceCantiere){
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
	}*/
	
	/*/**
	 * Gets the lista scoperti.
	 *
	 * @return lista dei lavori scoperti
	 */
	/*public ArrayList<Lavoro> getListaScoperti(){
		ArrayList<Lavoro>temp=new ArrayList<Lavoro>();
		for(Cantiere item:listaCantieri){
			for(Lavoro l:item.getElencoLavori()){
				if(l.isScoperto()){
					temp.add(l);
				}
			}
		}
		return temp;
	}*/
	
	/*/**
	 * Gets the lista coperti.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @return lista dei lavori coperti del cantiere
	 */
	/*public ArrayList<Lavoro> getListaCoperti(int codiceCantiere){
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
	}*/
	
	/*/**
	 * Gets the lista coperti.
	 *
	 * @return lista dei lavori coperti
	 */
	/*public ArrayList<Lavoro> getListaCoperti(){
		ArrayList<Lavoro>temp=new ArrayList<Lavoro>();
		for(Cantiere item:listaCantieri){
			for(Lavoro l:item.getElencoLavori()){
				if(!l.isScoperto()){
					temp.add(l);
				}
			}
		}
		return temp;
	}*/
	
	/**
	 * Restituisce il prossimo codice da assegnare a un lavoro.
	 *
	 * @return il prossimo codice da assegnare a un lavoro
	 */
	int getProssimoCodiceLavoro(){
		int code=this.codiceLavoro+1;
		return code;
	}
	
	//OPERAZIONI SULLE RICHIESTE---------------------------------------------------------------------------------------------------------
	
	/**
	 * Restituisce una richiesta.
	 *
	 * @param codiceRichiesta Il codice della richiesta desiderata
	 * @return La richiesta desiderata
	 */
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
	
	/**
	 * Aggiunge una nuova richiesta.<p>
	 * Inizialmente la richiesta non &egrave; soddisfatta, la macchina quindi &egrave; impostata a null.
	 *
	 * @param codiceCantiere Il codice del cantiere cui appartiene il lavoro
	 * @param codiceLavoro Il codice del lavoro cui appartiene la richiesta
	 * @param caratteristiche Le caratteristiche che devono esser rispettate dalla macchina
	 * @return true, se la richiesta è stata inserita correttamente
	 */
	public boolean aggiungiRichiesta(int codiceCantiere, int codiceLavoro,RichiestaMacchina caratteristiche){
		Cantiere item=getCantiere(codiceCantiere);
		if(item!=null){
			if(item.hasLavoro(codiceLavoro)){	Lavoro l=item.getLavoro(codiceLavoro);
			l.aggiungiRichiesta(caratteristiche);
			return true;
			}
		}
		
		return false;
	}
	
	/*/**
	 * Aggiunge una nuova richiesta, che quindi non ï¿½ soddisfatta
	 *
	 * @param codiceLavoro codice del lavoro
	 * @param caratteristiche caratteristiche
	 */
	/*public void aggiungiRichiesta(int codiceLavoro,RichiestaMacchina caratteristiche){
		for(Cantiere item:listaCantieri){
			if(item.hasLavoro(codiceLavoro)){
				Lavoro l=item.getLavoro(codiceLavoro);
				l.aggiungiRichiesta(caratteristiche);
				break;
			}
		}
	}*/
	
	/*
	 * Quando voglio caricare una richiesta da DB, devo impostare il codice secondo quanto inserito in precedenza e inserire l'eventuale macchina
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 * @param codiceRichiesta codice della richiesta
	 * @param caratteristiche caratteristiche richieste
	 * @param macchina la macchina che soddisfa la richiesta
	 *
	public void caricaRichiesta(int codiceCantiere, int codiceLavoro, int codiceRichiesta, RichiestaMacchina caratteristiche, Macchina macchina){
		Cantiere item=getCantiere(codiceCantiere);
		if(item!=null){
			if(item.hasLavoro(codiceLavoro)){
				Lavoro l=item.getLavoro(codiceLavoro);
				l.caricaRichiesta(caratteristiche,codiceRichiesta,macchina);
			}
		}
	}*/
	
	/**
	 * Associa la macchina a una richiesta.
	 *
	 * @param codiceRichiesta Il codice della richiesta
	 * @param m La macchina da associare alla richiesta
	 * @return true, se la macchina viene associata correttamente alla richiesta
	 */
	public boolean soddisfaRichiesta(int codiceRichiesta, Macchina m){
		for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					return lav.soddisfaRichiesta(codiceRichiesta, m);
				}
			}
		}
		return false;
	}
	
	/*/**
	 * Soddisfa richiesta.
	 *
	 * @param codiceLavoro codice del lavoro
	 * @param codiceRichiesta codice della richiesta
	 * @param m la macchina
	 */
	/*public void soddisfaRichiesta(int codiceLavoro,int codiceRichiesta, Macchina m){
		for(Cantiere can:listaCantieri){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.soddisfaRichiesta(codiceRichiesta, m);
					break;
				}
			}
		}
	}*/
	
	/*/**
	 * Soddisfa richiesta.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 * @param codiceRichiesta codice della richiesta
	 * @param m la macchina
	 */
	/*public void soddisfaRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta, Macchina m){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.soddisfaRichiesta(codiceRichiesta, m);
				}
			}
		}
	}*/
	
	/*/**
	 * Modifica richiesta.
	 *
	 * @param codiceRichiesta codice della richiesta
	 * @param caratteristiche caratteristiche richieste
	 */
	/*public void modificaRichiesta(int codiceRichiesta, RichiestaMacchina caratteristiche){
		ciclo:for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					lav.getRichiesta(codiceRichiesta).setCaratteristiche(caratteristiche);
					lav.liberaRichiesta(codiceRichiesta);
					break ciclo;
				}
			}
		}
	}*/
	
	/*/**
	 * Modifica richiesta.
	 *
	 * @param codiceLavoro codice del lavoro
	 * @param codiceRichiesta codice della richiesta
	 * @param caratteristiche caratteristiche richieste
	 */
	/*public void modificaRichiesta(int codiceLavoro,int codiceRichiesta, RichiestaMacchina caratteristiche){
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
	}*/
	
	/*/**
	 * Modifica richiesta.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 * @param codiceRichiesta codice della richiesta
	 * @param caratteristiche caratteristiche richieste
	 */
	/*public void modificaRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta,RichiestaMacchina caratteristiche){
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
	}*/
	
	/**
	 * Libera la richiesta dall'associazione alla macchina.
	 *
	 * @param codiceRichiesta Il codice della richiesta
	 * @return true, se la richiesta viene liberata correttamente
	 */
	public boolean liberaRichiesta(int codiceRichiesta){
		for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					lav.liberaRichiesta(codiceRichiesta);
					return true;
				}
			}
		}
		return false;
	}
	
	/*/**
	 * Libera richiesta.
	 *
	 * @param codiceLavoro codice del lavoro
	 * @param codiceRichiesta codice della richiesta
	 */
	/*public void liberaRichiesta(int codiceLavoro,int codiceRichiesta){
		for(Cantiere can:listaCantieri){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.liberaRichiesta(codiceRichiesta);
					break;
				}
			}
		}
	}*/
	
	/*/**
	 * Libera richiesta.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 * @param codiceRichiesta codice della richiesta
	 */
	/*public void liberaRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.liberaRichiesta(codiceRichiesta);
				}
			}
		}
	}*/
	
	/**
	 * Elimina la richiesta indicata dal codice.
	 *
	 * @param codiceRichiesta Il codice della richiesta da eliminare
	 * @return true, se la richiesta viene eliminata correttamente
	 */
	public boolean eliminaRichiesta(int codiceRichiesta){
		for(Cantiere can:listaCantieri){
			for(Lavoro lav:can.getElencoLavori()){
				if(lav.hasRichiesta(codiceRichiesta)){
					return lav.eliminaRichiesta(codiceRichiesta);
				}
			}
		}
		return false;
	}
	
	/*/**
	 * Elimina richiesta.
	 *
	 * @param codiceLavoro codice del lavoro
	 * @param codiceRichiesta codice della richiesta
	 */
	/*public void eliminaRichiesta(int codiceLavoro,int codiceRichiesta){
		for(Cantiere can:listaCantieri){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.eliminaRichiesta(codiceRichiesta);
					break;
				}
			}
		}
	}*/
	
	/*/**
	 * Elimina richiesta.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 * @param codiceRichiesta codice della richiesta
	 */
	/*public void eliminaRichiesta(int codiceCantiere, int codiceLavoro,int codiceRichiesta){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				Lavoro l=getLavoro(codiceLavoro);
				if(l.hasRichiesta(codiceRichiesta)){
					l.eliminaRichiesta(codiceRichiesta);
				}
			}
		}
	}*/
	
	/*/**
	 * Gets the lista soddisfatte.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 * @return lista delle richieste soddisfatte
	 */
	/*public ArrayList<Richiesta> getListaSoddisfatte(int codiceCantiere, int codiceLavoro){
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
	}*/
	
	/*/**
	 * Gets the lista soddisfatte.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @return lista delle richieste soddisfatte
	 */
	/*public ArrayList<Richiesta> getListaSoddisfatte(int codiceCantiere){
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
	}*/
	
	/*/**
	 * Gets the lista soddisfatte.
	 *
	 * @return the lista delle richieste soddisfatte
	 */
	/*public ArrayList<Richiesta> getListaSoddisfatte(){
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
	}*/
	
	/*/**
	 * Gets the lista insoddisfatte.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 * @return lista delle richieste insoddisfatte
	 */
	/*public ArrayList<Richiesta> getListaInsoddisfatte(int codiceCantiere, int codiceLavoro){
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
	}*/
	
	/*/**
	 * Gets the lista insoddisfatte.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @return lista delle richieste insoddisfatte
	 */
	/*public ArrayList<Richiesta> getListaInsoddisfatte(int codiceCantiere){
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
	}*/
	
	/**
	 * Restituisce la lista delle richieste insoddisfatte.
	 *
	 * @return La lista delle richieste insoddisfatte
	 */
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
	
	/*/**
	 * Gets the lista richieste.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @param codiceLavoro codice del lavoro
	 * @return lista delle richieste soddisfatte
	 */
	/*public ArrayList<Richiesta> getListaRichieste(int codiceCantiere, int codiceLavoro){
		Cantiere can=getCantiere(codiceCantiere);
		if(can!=null){
			if(can.hasLavoro(codiceLavoro)){
				return can.getLavoro(codiceLavoro).getListaRichieste();
			}
		}
		return new ArrayList<Richiesta>();
	}*/
	
	/*/**
	 * Gets the lista richieste.
	 *
	 * @param codiceCantiere codice del cantiere
	 * @return lista delle richieste
	 */
	/*public ArrayList<Richiesta> getListaRichieste(int codiceCantiere){
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
	}*/
	
	/*/**
	 * Gets the lista richieste.
	 *
	 * @return lista delle richieste
	 */
	/*public ArrayList<Richiesta> getListaRichieste(){
		ArrayList<Richiesta>totRichieste=new ArrayList<Richiesta>();
		for(Cantiere c:listaCantieri){
			for(Lavoro l:c.getElencoLavori()){
				for(Richiesta r:l.getListaRichieste()){
					totRichieste.add(r);
				}
			}
		}
		return totRichieste;
	}*/
	
	//OPERAZIONI GENERICHE---------------------------------------------------------------------------------------------------------------
	
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
	
	/**
	 * Aggiunge un observer.
	 *
	 * @param ob L'observer da aggiungere
	 */
	public void aggiungiObserver(Observer ob) {
		this.cantiereObserver=ob;
	}
	
	//Metodi realizzati appositamente per il testing della classe.
	
	/**
	 * Restituisce il prossimo codice da assegnare a un cantiere.
	 *
	 * @return Il prossimo codice da assegnare a un cantiere
	 */
	int getProssimoCodice(){
		return codice+1;
	}
	
	/**
	 * Distrugge l'istanza per effettuare i test.
	 */
	static void resetForTest(){
		if(istanza!=null){
			istanza=null;
		}
	}
	
	/**
	 * Elimina tutti i cantieri per effettuare i test.
	 */
	public void svuotaCantieriForTest(){
		codice=0;
		codiceLavoro=0;
		Richiesta.initCodice();
		for(Cantiere c:listaCantieri){
			c.svuotaLavori();
		}
		listaCantieri.clear();
	}
}
