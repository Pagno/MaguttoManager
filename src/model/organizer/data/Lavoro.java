package model.organizer.data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *   Questa classe rappresenta un lavoro. Permette di gestire le caratteristiche principali cioè il codice del lavoro, 
 *   il suo nome e le date di inizio e fine del lavoro.
 *   <p>
 *   Ogni istanza contiene l'elenco delle richieste associate al lavoro rappresentato e il cantiere cui si riferisce il lavoro.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class Lavoro extends DefaultMutableTreeNode{
	/** Codice del lavoro. */
	private int codice;
	/** Nome del lavoro. */
	private String nome;
	/** Data di inizio del lavoro. */
	private GregorianCalendar dataInizio;
	/** Data di fine del lavoro. */
	private GregorianCalendar dataFine;
	/** Macchinari richiesti dal lavoro. */
	private ArrayList<Richiesta> macchinariRichiesti;
	/** Cantiere a cui appartiene il lavoro. */
	private Cantiere cantiere;
	
	/**
	 * Istanzia un nuovo lavoro.
	 *
	 * @param codice Codice del lavoro
	 * @param nome Nome del lavoro
	 * @param cantiere Cantiere cui appartiene il lavoro
	 * @param dataInizio Data di inizio del lavoro
	 * @param dataFine Data di fine del lavoro
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
	 * Restituisce il cantiere di appartenenza del lavoro.
	 *
	 * @return Il cantiere cui appartiene il lavoro
	 */
	public Cantiere getCantiere() {
		return cantiere;
	}
	
	/**
	 * Modifica il cantiere cui appartiene il lavoro.
	 *
	 * @param cantiere Il nuovo cantiere
	 */
	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
	}
	
	/**
	 * Restituisce il codice del lavoro.
	 *
	 * @return Il codice del lavoro
	 */
	public int getCodice() {
		return codice;
	}
	
	/**
	 * Modifica il codice del lavoro.
	 *
	 * @param codice Il nuovo codice
	 */
	public void setCodice(int codice) {
		this.codice = codice;
	}
	
	/**
	 * Restituisce il nome del lavoro.
	 *
	 * @return Il nome del lavoro
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Modifica il nome del lavoro.
	 *
	 * @param nome Il nuovo nome del lavoro
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Restituisce la data di inizio del lavoro.
	 *
	 * @return La data di inizio del lavoro
	 */
	public GregorianCalendar getDataInizio() {
		return dataInizio;
	}
	
	/**
	 * Modifica la data di inizio del lavoro.
	 *
	 * @param dataInizio La nuova data di inizio del lavoro
	 */
	public void setDataInizio(GregorianCalendar dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	/**
	 * Restituisce la data di inizio del lavoro sotto forma di stringa.
	 *
	 * @return La stringa rappresentante la data di inizio del lavoro
	 */
	public String getStrDataInizio() {
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");
		return df.format(dataInizio.getTime());
	}
	
	/**
	 * Restituisce la data di fine del lavoro.
	 *
	 * @return La data di fine del lavoro
	 */
	public GregorianCalendar getDataFine() {
		return dataFine;
	}
	
	/**
	 * Restituisce la data di fine del lavoro sotto forma di stringa.
	 *
	 * @return La stringa rappresentante la data di fine del lavoro
	 */
	public String getStrDataFine() {
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");
		return df.format(dataFine.getTime());
	}
	
	/**
	 * Modifica la data di fine del lavoro.
	 *
	 * @param dataFine La nuova data di fine del lavoro
	 */
	public void setDataFine(GregorianCalendar dataFine) {
		this.dataFine = dataFine;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.DefaultMutableTreeNode#toString()
	 */
	@Override
	public String toString() {
		return codice + " " + nome + " "
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
	 * Aggiunge la richiesta di una macchina al lavoro.
	 *
	 * @param caratteristiche Le caratteristiche della macchina richiesta per il lavoro corrente
	 * @return Il codice della richiesta appena inserita
	 */
	public int aggiungiRichiesta(RichiestaMacchina caratteristiche){
		Richiesta r=new Richiesta(caratteristiche,this);
		macchinariRichiesti.add(r);
		add(r);
		return r.getCodice();
	}
	
	/**
	 * Carica la richiesta di una macchina necessaria al lavoro.
	 *
	 * @param caratteristiche Le caratteristiche della macchina richiesta per il lavoro
	 * @param codice Il codice della richiesta caricata
	 * @param m La macchina associata alla richiesta caricata
	 */
	public void caricaRichiesta(RichiestaMacchina caratteristiche,Integer codice, Macchina m){
		Richiesta r=new Richiesta(caratteristiche,this,codice);
		if(r.rispettaRichiesta(m)){
			r.setMacchina(m);
		}
		add(r);
		macchinariRichiesti.add(r);
	}
	
	/*/**
	 * Modifica richiesta.
	 *
	 * @param codice Codice della richiesta da modificare.
	 * @param caratteristiche Caratteristiche della macchina richieste per il lavoro
	 */
	/*public void modificaRichiesta(Integer codice,RichiestaMacchina caratteristiche){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
				item.setCaratteristiche(caratteristiche);
			}
		}
	}*/
	
	/**
	 * Controlla se il lavoro contiene la richiesta che possiede il codice indicato.
	 *
	 * @param codice Il codice della richiesta
	 * @return true se il lavoro contiere la richiesta cui si riferisce il codice
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
	 * Restituisce la richiesta cui si riferisce il codice indicato.
	 *
	 * @param codiceRichiesta Il codice della richiesta
	 * @return La richiesta desiderata
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
	 * Elimina la richiesta cui si riferisce il codice indicato.
	 *
	 * @param codiceRichiesta Il codice della richiesta da eliminare
	 * @return true, se la richiesta &egrave; stata correttamente eliminata
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
	 * Libera tutte le richieste appartenenti al lavoro.
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
	 * @param codice Il codice della richiesta desiderata
	 * @param mac La macchina da associare alla richiesta
	 * @return true se la richiesta viene correttamente associata alla macchina
	 */
	public boolean soddisfaRichiesta(Integer codice,Macchina mac){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
				if(item.rispettaRichiesta(mac)){
					item.setMacchina(mac);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Rimuove l'associazione tra la richiesta indicata dal codice e la sua macchina.
	 *
	 * @param codiceRichiesta Il codice della richiesta da liberare
	 */
	public void liberaRichiesta(Integer codiceRichiesta){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codiceRichiesta){
				item.setMacchina(null);
			}
		}
	}
	
	/*/**
	 *Libera le richieste con associata una data macchina.
	 *
	 * @param codiceMacchina Codice macchina
	 */
	/*public void liberaMacchina(int codiceMacchina){
		for(Richiesta item:macchinariRichiesti){
			if(item.getMacchina()!=null){
				if(item.getMacchina().getCodice()==codiceMacchina){
					item.setMacchina(null);
				}
			}
		}
	}*/

	/**
	 * Verifica se il lavoro ha ancora delle richieste non soddisfatte, e necessita quindi di macchine.
	 *
	 * @return true, se almeno una richiesta del lavoro &egrave; scoperta
	 */
	public boolean isScoperto(){
		for(Richiesta item:macchinariRichiesti){
			if(!item.isSoddisfatta()){
				return true;
			}
		}
		return false;
	}
	
	/*/**
	 * Where scoperto.
	 *
	 * @return the array list
	 */
	/*public ArrayList<Richiesta> whereScoperto(){
		ArrayList<Richiesta>richScoperte=new ArrayList<Richiesta>();
		for(Richiesta item:macchinariRichiesti){
			if(!item.isSoddisfatta()){
				richScoperte.add(item);
			}
		}
		return richScoperte;
	}*/
	
	/**
	 * Restituisce la lista delle richieste del lavoro.
	 *
	 * @return La lista delle richieste
	 */
	public ArrayList<Richiesta> getListaRichieste(){
		return macchinariRichiesti;
	}
	
	/**
	 * Restituisce la priorit&agrave; del cantiere cui appartiene il lavoro.
	 *
	 * @return La priorit&agrave; del cantiere cui appartiene il lavoro
	 */
	public Priorita getPriorita(){
		return cantiere.getPriorita();
	}
	
	/**
	 * Restituisce il codice del cantiere cui appartiene il lavoro.
	 *
	 * @return Il codice del cantiere cui appartiene il lavoro
	 */
	public int getCodiceCantiere(){
		return cantiere.getCodice();
	}
	
	/**
	 * Restituisce la lista dei lavori connessi a quest'ultimo, cioè la lista dei lavori appartenenti al cantiere 
	 * cui appartiene anche il lavoro corrente.<br>
	 * Ogni lavoro è connesso a se stesso, ed è quindi compreso nella lista.
	 *
	 * @return La lista dei lavori connessi
	 */
	public ArrayList<Lavoro> getLavoriConnessi(){
		return cantiere.getElencoLavori();
	}
	
	/**
	 * Restituisce la durata in giorni del lavoro.
	 *
	 * @return La durata del lavoro
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