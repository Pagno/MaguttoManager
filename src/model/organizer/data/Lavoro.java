package model.organizer.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;


// TODO: Auto-generated Javadoc
/**
 * The Class Lavoro.
 */
public class Lavoro extends DefaultMutableTreeNode{

	/** Codice Lavoro. */
	private int codice;
	
	/** Nome Lavoro. */
	private String nome;
	
	/** Dnizio lavoro. */
	private GregorianCalendar dataInizio;
	
	/** Data fine lavoro. */
	private GregorianCalendar dataFine;
	
	/** Macchinari richiesti. */
	private ArrayList<Richiesta> macchinariRichiesti;
	
	/** Cantiere. */
	private Cantiere cantiere;
	

	/**
	 * Crea un nuovo lavoro.
	 *
	 * @param codice Codice Lavoro
	 * @param nome Nome Lavoro
	 * @param cantiere Cantiere Lavoro
	 * @param dataInizio Data inizio del Lavoro
	 * @param dataFine Data fine del Lavoro
	 */
	public Lavoro(int codice, String nome, Cantiere cantiere, GregorianCalendar dataInizio,
			GregorianCalendar dataFine) {
		super();

		this.codice = codice;
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.macchinariRichiesti=new ArrayList<Richiesta>();
		this.cantiere=cantiere;
	}

	/**
	 * Ritorna il cantiere.
	 *
	 * @return il cantiere
	 */
	public Cantiere getCantiere() {
		return cantiere;
	}

	/**
	 * Sets the cantiere.
	 *
	 * @param cantiere the new cantiere
	 */
	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
	}

	/**
	 * Gets the codice.
	 *
	 * @return the codice
	 */
	public int getCodice() {
		return codice;
	}


	/**
	 * Sets the codice.
	 *
	 * @param codice the new codice
	 */
	public void setCodice(int codice) {
		this.codice = codice;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * Sets the nome.
	 *
	 * @param Assegna un nuovo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * Gets the data inizio.
	 *
	 * @return the data inizio
	 */
	public GregorianCalendar getDataInizio() {
		return dataInizio;
	}


	/**
	 * Sets the data inizio.
	 *
	 * @param Assegna un nuovo  data inizio
	 */
	public void setDataInizio(GregorianCalendar dataInizio) {
		this.dataInizio = dataInizio;
	}

	/**
	 * Gets the str data inizio.
	 *
	 * @return the str data inizio
	 */
	public String getStrDataInizio() {

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");

		return df.format(dataInizio.getTime());
		
	}

	/**
	 * Gets the data fine.
	 *
	 * @return the data fine
	 */
	public GregorianCalendar getDataFine() {
		return dataFine;
	}

	/**
	 * Gets the str data fine.
	 *
	 * @return the str data fine
	 */
	public String getStrDataFine() {

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");

		return df.format(dataFine.getTime());
		
	}


	/**
	 * Sets the data fine.
	 *
	 * @param Assegna una nuova data fine
	 */
	public void setDataFine(GregorianCalendar dataFine) {
		this.dataFine = dataFine;
	}


	/* (non-Javadoc)
	 * @see javax.swing.tree.DefaultMutableTreeNode#toString()
	 */
	@Override
	public String toString() {
		return  codice + " " + nome + " "
				+ getStrDataInizio() + " " + getStrDataFine();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Lavoro))
			return false;
		Lavoro other = (Lavoro) obj;
		if (cantiere == null) {
			if (other.cantiere != null)
				return false;
		} else if (!cantiere.equals(other.cantiere))
			return false;
		if (codice != other.codice)
			return false;
		if (dataFine == null) {
			if (other.dataFine != null)
				return false;
		} else if (!dataFine.equals(other.dataFine))
			return false;
		if (dataInizio == null) {
			if (other.dataInizio != null)
				return false;
		} else if (!dataInizio.equals(other.dataInizio))
			return false;
		if (!macchinariRichiesti.equals(other.macchinariRichiesti))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * Aggiungi richiesta macchina al lavoro.
	 *
	 * @param caratteristiche Caratteristiche della macchina richieste per il lavoro
	 * @return codice della appena inserita
	 */
	public int aggiungiRichiesta(RichiestaMacchina caratteristiche){
		Richiesta r=new Richiesta(caratteristiche,this);
		macchinariRichiesti.add(r);
		add(r);
		return r.getCodice();
		
		
	}
	
	/**
	 * Carica richiesta.
	 *
	 * @param caratteristiche Caratteristiche della macchina richieste per il lavoro
	 * @param codice Codice della richeista
	 * @param m macchinada associare
	 */
	public void caricaRichiesta(RichiestaMacchina caratteristiche,Integer codice, Macchina m){
		Richiesta r=new Richiesta(caratteristiche,this,codice);
		if(r.rispettaRichiesta(m)){
			r.setMacchina(m);
		}
		add(r);
		macchinariRichiesti.add(r);
	}
	
	/**
	 * Modifica richiesta.
	 *
	 * @param codice Codice della richiesta da modificare.
	 * @param caratteristiche Caratteristiche della macchina richieste per il lavoro
	 */
	public void modificaRichiesta(Integer codice,RichiestaMacchina caratteristiche){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
				item.setCaratteristiche(caratteristiche);
			}
		}
	}
	
	/**
	 * Controlla se il lavoro contiere una richiesta con quel dato codice.
	 *
	 * @param codice Codice della richiesta
	 * @return true se il lavoro contiere una richiesta con quel dato codice.
	 */
	public boolean hasRichiesta(Integer codice){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the richiesta.
	 *
	 * @param codiceRichiesta Codice richiesta
	 * @return Richiesta
	 */
	public Richiesta getRichiesta(Integer codiceRichiesta){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codiceRichiesta){
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Elimina richiesta.
	 *
	 * @param codiceRichiesta Codice richiesta da elimianre
	 * @return true, se la richiesta è stata correttamente eliminata.
	 */
	public boolean eliminaRichiesta(Integer codiceRichiesta){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codiceRichiesta){
				remove(item);
				item.setMacchina(null);
				macchinariRichiesti.remove(item);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Libera tutte le richiesta legate al lavoro.
	 */
	public void svuotaRichieste(){
		for(Richiesta item:macchinariRichiesti){
			item.setMacchina(null);
		}
		macchinariRichiesti.clear();
	}
	
	/**
	 * Associa un macchina ad una richiesta.
	 *
	 * @param codice Codice della richiesta.
	 * @param mac Macchina da associare alla richiesta.
	 * @return true se la richiesta viene correttamente associata.
	 */
	public boolean soddisfaRichiesta(Integer codice,Macchina mac){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
				if(item.rispettaRichiesta(mac)){
					item.setMacchina(mac);
					//mac.addRichiesta(item);//TODO mac.addRichiesta ?? gia presente in setMacchina()
					return true;
				}
			}
		}
		return false;
	}
	
	//vogliamo cancellare la richiesta, quindi inseriamo null al posto dell'associazione precedente
	/**
	 * Libera richiesta.
	 *
	 * @param codiceRichiesta Codice richiesta
	 */
	public void liberaRichiesta(Integer codiceRichiesta){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codiceRichiesta){
				item.setMacchina(null);
			}
		}
	}
	
	//Libera le richieste con associata una data macchina
	/**
	 * Libera macchina.
	 *
	 * @param codiceMacchina Codice macchina
	 */
	public void liberaMacchina(int codiceMacchina){
		for(Richiesta item:macchinariRichiesti){
			if(item.getMacchina()!=null){
				if(item.getMacchina().getCodice()==codiceMacchina){
					item.setMacchina(null);
				}
			}
		}
	}
	
	//Permette di vedere se il lavoro ha ancora delle richieste non soddisfatte, e necessita quindi di macchine
	/**
	 * Controlla se la richiesta è scoperta.
	 *
	 * @return true, se è scoperta
	 */
	public boolean isScoperto(){
		for(Richiesta item:macchinariRichiesti){
			if(!item.isSoddisfatta()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Where scoperto.
	 *
	 * @return the array list
	 */
	public ArrayList<Richiesta> whereScoperto(){
		ArrayList<Richiesta>richScoperte=new ArrayList<Richiesta>();
		for(Richiesta item:macchinariRichiesti){
			if(!item.isSoddisfatta()){
				richScoperte.add(item);
			}
		}
		return richScoperte;
	}

	/**
	 * Ritorna Lista richieste.
	 *
	 * @return Lista richieste
	 */
	public ArrayList<Richiesta> getListaRichieste(){
		return macchinariRichiesti;
	}

	
	/**
	 * Gets the priorita.
	 *
	 * @return the priorita
	 */
	public Priorita getPriorita(){
		return cantiere.getPriorita();
	}
	
	/**
	 * Gets the codice cantiere.
	 *
	 * @return the codice cantiere
	 */
	public int getCodiceCantiere(){
		return cantiere.getCodice();
	}
	
	/**
	 * Gets the lavori connessi.
	 *
	 * @return the lavori connessi
	 */
	public ArrayList<Lavoro> getLavoriConnessi(){
		return cantiere.getElencoLavori();
	}
	
	/**
	 * Gets the durata.
	 *
	 * @return the durata
	 */
	public int getDurata(){
		int d;
		GregorianCalendar sx,dx;
		d=0;
		sx=(GregorianCalendar)this.getDataInizio().clone();
		dx=(GregorianCalendar)this.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d++;
		}
		return d;
	}
	

}
