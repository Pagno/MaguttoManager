package model.organizer.data;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.GregorianCalendar;

/**
 *   Questa classe rappresenta la richiesta di una particolare macchina. Permette di definire le caratteristiche che devono
 *   essere soddisfatte da una macchina per poterla soddisfare; quando è soddisfatta inoltre memorizza la macchina che permette 
 *   di considerarla coperta.
 *   <p>
 *   Ogni istanza contiene il lavoro cui appartiene la richiesta, la macchina ad essa eventualmente associata e un codice 
 *   identificativo numerico.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public final class Richiesta extends DefaultMutableTreeNode implements Comparable<Richiesta>{
	
	/** Caratteristiche che devono esser rispettate dalla macchina. */
	private RichiestaMacchina caratteristiche;
	
	/** Macchina associata. */
	private Macchina macchina;
	
	/** Lavoro cui appartiene la richiesta. */
	private Lavoro lavoro;
	/** Ultimo codice associato a una richiesta in precedenza. */
	private static int ultimoCodice;
	
	/** Codice della richiesta. */
	private int codice;
	
	/**
	 * Istanzia una nuova richiesta.
	 *
	 * @param caratteristiche Le caratteristiche della richiesta
	 * @param lavoro Il lavoro cui appartiene la richiesta
	 */
	public Richiesta(RichiestaMacchina caratteristiche, Lavoro lavoro) {
		super();
		assignCodice();
		this.caratteristiche = caratteristiche;
		this.lavoro=lavoro;
		this.setMacchina(null);
	}
	
	/**
	 * Istanzia una nuova richiesta, permettendo di deciderne il codice.<BR>
	 * Per rispettare l'univocit&agrave; dei codici, il costruttore deve essere utilizzato solamente in fase di caricamento 
	 * dei dati dal database.
	 *
	 * @param caratteristiche Le caratteristiche della richiesta
	 * @param lavoro Il lavoro cui appartiene la richiesta
	 * @param codiceRichiesta Il codice identificativo della richiesta
	 */
	public Richiesta(RichiestaMacchina caratteristiche,Lavoro lavoro, int codiceRichiesta) {
		super();
		if(codiceRichiesta>ultimoCodice){
			ultimoCodice=codiceRichiesta;
		}
		this.codice=codiceRichiesta;
		this.caratteristiche = caratteristiche;
		this.lavoro=lavoro;
		this.setMacchina(null);
	}
	/**
	 * Inizializza i codici delle richieste.
	 */
	public static void initCodice(){
		ultimoCodice=0;
	}
	
	/**
	 * Assegna automaticamente il codice alla richiesta inserita.<BR>
	 * Il codice assegnato &egrave; quello successivo all'ultimo utilizzato in precedenza.
	 */
	private void assignCodice(){
		ultimoCodice++;
		codice=ultimoCodice;
	}
	/**
	 * Restituisce il codice della richiesta.
	 *
	 * @return Il codice della richiesta
	 */
	public int getCodice(){
		return codice;
	}
	
	/**
	 * Restituisce le caratteristiche che devono esser soddisfatte dalla macchina per rispettare la richiesta.
	 *
	 * @return Le caratteristiche desiderate
	 */
	public RichiestaMacchina getCaratteristiche() {
		return caratteristiche;
	}
	
	/*/**
	 * Sets the caratteristiche.
	 *
	 * @param caratteristiche the new caratteristiche
	 */
	/*public void setCaratteristiche(RichiestaMacchina caratteristiche) {
		this.caratteristiche = caratteristiche;
		if(!caratteristiche.rispettaRichiesta(macchina)){
			this.setMacchina(null);
		}
	}*/
	
	/**
	 * Controlla se alla richiesta &egrave; stata associata una macchina.
	 *
	 * @return true, se la richiesta &egrave; stata associata a una macchina
	 */
	public boolean isSoddisfatta() {
		if(macchina==null){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Restituisce una lista di stringhe rappresentante la richiesta corrente.
	 *
	 * @return La lista di stringhe rappresentante la richiesta corrente
	 */
	public ArrayList<String> getData() {
		ArrayList<String> l=new ArrayList<String>();
		l.add(Integer.toString(getCodice()));
		if(getCaratteristiche() instanceof RichiestaCamion){
			l.add("Camion");
			RichiestaCamion rc=(RichiestaCamion)getCaratteristiche();
			l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add(Integer.toString(rc.getMinLunghezza()));l.add(Integer.toString(rc.getMaxLunghezza()));
			l.add("0");l.add("0");
			l.add("0");l.add("0");
			l.add("0");l.add("0");
		}else if(getCaratteristiche() instanceof RichiestaEscavatore){
			l.add("Escavatore");
			RichiestaEscavatore rc=(RichiestaEscavatore)getCaratteristiche();
			l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
			l.add(Integer.toString(rc.getMinProfondita()));l.add(Integer.toString(rc.getMaxProfondita()));
			l.add("0");l.add("0");
		}else if(getCaratteristiche() instanceof RichiestaGru){
			l.add("Gru");
			RichiestaGru rc=(RichiestaGru)getCaratteristiche();
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add(Integer.toString(rc.getMinLunghezza()));l.add(Integer.toString(rc.getMaxLunghezza()));
			l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinAngoloRotazione()));l.add(Integer.toString(rc.getMaxAngoloRotazione()));
		}else {
			//Resta solo il caso ruspa
			l.add("Ruspa");
			RichiestaRuspa rc=(RichiestaRuspa)getCaratteristiche();
			l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
			l.add("0");l.add("0");
			l.add("0");l.add("0");
		}
		return l;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.DefaultMutableTreeNode#toString()
	 */
	@Override
	public String toString() {
		String s;
		s=getCodice() + " " + caratteristiche + " " + isSoddisfatta();
		if(isSoddisfatta()){
			s=s + " " + macchina.getCodice();
		}
		else{
			s=s + " null";
		}
		return s;
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
		if (!(obj instanceof Richiesta))
			return false;
		Richiesta other = (Richiesta) obj;
		if (caratteristiche == null) {
			if (other.caratteristiche != null)
				return false;
		} else if (!caratteristiche.equals(other.caratteristiche))
			return false;
		if (codice != other.codice)
			return false;
		if (lavoro == null) {
			if (other.lavoro != null)
				return false;
		} else if (!lavoro.equals(other.lavoro))
			return false;
		if (macchina == null) {
			if (other.macchina != null)
				return false;
		} else if (!macchina.equals(other.macchina))
			return false;
		return true;
	}
	
	/**
	 * Verifica se la macchina proposta ha le caratteristiche necessarie per soddisfare la richiesta.
	 *
	 * @param m La macchina proposta
	 * @return true, se la macchina proposta &egrave; adatta a soddisfare la richiesta
	 */
	public boolean rispettaRichiesta(Macchina m){
		return caratteristiche.rispettaRichiesta(m);
	}
	
	/**
	 * Restituisce la macchina associata a questa richiesta.
	 *
	 * @return La macchina associata a questa richiesta, oppure null se la richiesta non &egrave; soddisfatta
	 */
	public Macchina getMacchina() {
		return macchina;
	}
	
	/**
	 * Associa una macchina alla richiesta corrente.<BR>
	 * Se la macchina proposta non &egrave; adatta a soddisfare la richiesta, la richiesta resta scoperta; nel caso in cui in precedenza
	 * fosse gi&agrave; stata associata essa viene liberata.<BR>
	 * Il metodo si occupa anche dell'aggiornamento della lista delle richieste per la macchina proposta e per l'eventuale 
	 * macchina precedentemente associata.<BR>
	 * Se il parametro in input ha valore null l'eventuale macchina associata viene liberata.
	 *
	 * @param macchina La macchina da associare alla richiesta
	 */
	public void setMacchina(Macchina macchina) {
		if(this.macchina!=null){
			this.macchina.rimuoviRichiesta(this);
		}
		if(macchina==null){
			this.macchina=null;
		}
		else{
			if(this.rispettaRichiesta(macchina)){
				this.macchina = macchina;
				this.macchina.aggiungiRichiesta(this);
			}
			else{
				this.macchina=null;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Richiesta r) {
		if(this.equals(r)) {return 0;}
		if(this.lavoro.getDataInizio().compareTo(r.lavoro.getDataInizio())!=0){
			return this.lavoro.getDataInizio().compareTo(r.lavoro.getDataInizio());
		}
		else return this.codice-r.getCodice();
	}
	
	/**
	 * Restituisce la data d'inizio del lavoro cui appartiene la richiesta.
	 *
	 * @return La data d'inizio del lavoro cui appartiene la richiesta
	 */
	public GregorianCalendar getDataInizio(){
		return lavoro.getDataInizio();
	}
	
	/**
	 * Restituisce la data di fine del lavoro cui appartiene la richiesta.
	 *
	 * @return La data di fine del lavoro cui appartiene la richiesta
	 */
	public GregorianCalendar getDataFine(){
		return lavoro.getDataFine();
	}
	
	/**
	 * Restituisce la priorit&agrave; del cantiere cui appartiene il lavoro proprietario della richiesta.
	 *
	 * @return La priorit&agrave; del cantiere
	 */
	public Priorita getPriorita(){
		return lavoro.getPriorita();
	}
	
	/**
	 * Restituisce il codice del lavoro proprietario della richiesta.
	 *
	 * @return Il codice del lavoro proprietario della richiesta
	 */
	public int getCodiceLavoro(){
		return lavoro.getCodice();
	}
	
	/**
	 * Restituisce il codice del cantiere cui appartiene il lavoro proprietario della richiesta.
	 *
	 * @return Il codice del cantiere cui appartiene il lavoro proprietario della richiesta
	 */
	public int getCodiceCantiere(){
		return lavoro.getCodiceCantiere();
	}
	
	/**
	 * Restituisce il lavoro proprietario della richiesta.
	 * @return Il lavoro proprietario della richiesta
	 */
	public Lavoro getLavoro(){
		return lavoro;
	}
	
	/**
	 * Restituisce la lista dei lavori connessi a quello cui appartiene la richiesta.
	 *
	 * @return La lista dei lavori connessi a quello cui appartiene la richiesta
	 */
	public ArrayList<Lavoro> getLavoriConnessi(){
		return lavoro.getLavoriConnessi();
	}
	
	/**
	 * Restituisce la durata del lavoro proprietario della richiesta.
	 *
	 * @return La durata del lavoro proprietario della richiesta
	 */
	public int getDurata(){
		return lavoro.getDurata();
	}
	
	/**
	 * Verifica se la richiesta inserita è cronologicamente sovrapposta alla richiesta corrente.
	 *
	 * @param other L'altra richiesta
	 * @return true, se le due richieste si riferiscono a periodi cronologicamente sovrapposti
	 */
	public boolean collide(Richiesta other){
		if(this.getDataInizio().after(other.getDataFine())||this.getDataFine().before(other.getDataInizio())){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Verifica se la richiesta inserita potrebbe potenzialmente occupare la una macchina in grado di soddisfare 
	 * la richiesta corrente.<BR>
	 * Ci&ograve; significa che le due richieste esigono lo stesso tipo di macchina, e che tutti gli intervalli delle 
	 * caratteristiche tipiche di tali macchine sono almeno parzialmente sovrapposti.
	 *
	 * @param other L'altra richiesta
	 * @return true, se le due richieste potrebbero potenzialmente contendersi una particolare macchina
	 */
	public boolean inConflitto(Richiesta other){
		return this.caratteristiche.inConflitto(other.getCaratteristiche());
	}
	
	

	//Metodi realizzati appositamente per il testing della classe.

	/**
	 * Restituisce il prossimo codice che verrà assegnato a una richiesta.
	 *
	 * @return Il prossimo codice da assegnare
	 */
	static int getNextCodice(){
		return ultimoCodice+1;
	}
}