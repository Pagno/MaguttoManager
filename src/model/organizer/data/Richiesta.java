package model.organizer.data;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * The Class Richiesta.
 */
public final class Richiesta extends DefaultMutableTreeNode implements Comparable<Richiesta>{

	


	/** The caratteristiche. */
	private RichiestaMacchina caratteristiche;
	
	/** The macchina. */
	private Macchina macchina;
	
	/** The lavoro. */
	private Lavoro lavoro;
	
	/**   codice. */
	private static int ultimoCodice;

	/** The codice. */
	private int codice;
	
	
	/**
	 * Instantiates a new richiesta.
	 *
	 * @param caratteristiche caratteristiche richieste
	 * @param lavoro il lavoro cui appartiene la richiesta
	 */
	public Richiesta(RichiestaMacchina caratteristiche, Lavoro lavoro) {
		super();
		
		assignCodice();
		this.caratteristiche = caratteristiche;
		this.lavoro=lavoro;
		this.setMacchina(null);
	}
	
	/**
	 * Instantiates a new richiesta.
	 *
	 * @param caratteristiche caratteristiche richieste
	 * @param lavoro il lavoro cui appartiene la richiesta
	 * @param codiceRichiesta codice della richiesta
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
	 * Inits   codice.
	 */
	public static void initCodice(){
		ultimoCodice=0;
	}
	
	/**
	 * Assign codice.
	 */
	private void assignCodice(){
		ultimoCodice++;
		codice=ultimoCodice;
	}
	
	/**
	 * Gets   codice.
	 *
	 * @return   codice
	 */
	public int getCodice(){
		return codice;
	}

	/**
	 * Gets the caratteristiche.
	 *
	 * @return the caratteristiche
	 */
	public RichiestaMacchina getCaratteristiche() {
		return caratteristiche;
	}


	/**
	 * Sets the caratteristiche.
	 *
	 * @param caratteristiche the new caratteristiche
	 */
	public void setCaratteristiche(RichiestaMacchina caratteristiche) {
		this.caratteristiche = caratteristiche;
		if(!caratteristiche.rispettaRichiesta(macchina)){
			this.setMacchina(null);
		}
	}


	/**
	 * Checks if is soddisfatta.
	 *
	 * @return true, if is soddisfatta
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
	 * Restituisce la richiesta sotto forma di arraylist di stringa utilizzate dalla viey.
	 *
	 * @return arraylist di stringhe rappresentanti la richiesta
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
	 * Verifica se la macchina m soddisfa questa richiesta o meno.
	 *
	 * @param m la macchina
	 * @return true, if successful
	 */
	public boolean rispettaRichiesta(Macchina m){
		return caratteristiche.rispettaRichiesta(m);
	}

	//Metodi realizzati appositamente per il testing della classe.
	/**
	 * Gets   next codice.
	 *
	 * @return   next codice
	 */
	static int getNextCodice(){
		return ultimoCodice+1;
	}


	/**
	 * Gets the macchina.
	 *
	 * @return macchina
	 */
	public Macchina getMacchina() {
		return macchina;
	}


	/**
	 * Sets the macchina.
	 *
	 * @param macchina la macchina che dovrebbe soddisfare la richiesta
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
	 * Restituisce la data inizio del lavoro.
	 *
	 * @return data inizio
	 */
	public GregorianCalendar getDataInizio(){
		return lavoro.getDataInizio();
	}
	
	/**
	 * Restituisce la data fine del lavoro.
	 *
	 * @return data fine
	 */
	public GregorianCalendar getDataFine(){
		return lavoro.getDataFine();

	}
	
	/**
	 * Gets the priorita.
	 *
	 * @return priorita
	 */
	public Priorita getPriorita(){
		return lavoro.getPriorita();
	}
	
	/**
	 * Gets the codice lavoro.
	 *
	 * @return the codice lavoro
	 */
	public int getCodiceLavoro(){
		return lavoro.getCodice();
	}
	
	/**
	 * Gets the codice cantiere.
	 *
	 * @return codice cantiere
	 */
	public int getCodiceCantiere(){
		return lavoro.getCodiceCantiere();
	}
	
	/**
	 * Gets the lavoro.
	 *
	 * @return lavoro
	 */
	public Lavoro getLavoro(){
		return lavoro;
	}
	
	/**
	 * Gets the lavori connessi.
	 *
	 * @return lavori connessi
	 */
	public ArrayList<Lavoro> getLavoriConnessi(){
		return lavoro.getLavoriConnessi();
	}
	
	/**
	 * Gets the durata.
	 *
	 * @return the durata
	 */
	public int getDurata(){
		return lavoro.getDurata();
	}
	
	/**
	 * verifica se due richieste sono cronologicamente sovrapposte.
	 *
	 * @param other l'altra richiesta
	 * @return true, if successful
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
	 * Verifica se le due richieste hanno la possibilità teorica di prenotare la stessa macchina.
	 *
	 * @param other l'altra richiesta
	 * @return true, if successful
	 */
	public boolean inConflitto(Richiesta other){
		if(this.caratteristiche instanceof RichiestaCamion){
			if(other.getCaratteristiche() instanceof RichiestaCamion){
				RichiestaCamion rt,ro;
				rt=(RichiestaCamion)this.caratteristiche;
				ro=(RichiestaCamion)other.getCaratteristiche();
				if(!(rt.getMinCapacita()>ro.getMaxCapacita()||(ro.getMinCapacita()>rt.getMaxCapacita()))){
					if(!(rt.getMinPortata()>ro.getMaxPortata()||(ro.getMinPortata()>rt.getMaxPortata()))){
						if(!(rt.getMinLunghezza()>ro.getMaxLunghezza()||(ro.getMinLunghezza()>rt.getMaxLunghezza()))){
							return true;
						}
					}
				}
			}
		}
		if(this.caratteristiche instanceof RichiestaRuspa){
			if(other.getCaratteristiche() instanceof RichiestaRuspa){
				RichiestaRuspa rt,ro;
				rt=(RichiestaRuspa)this.caratteristiche;
				ro=(RichiestaRuspa)other.getCaratteristiche();
				if(!(rt.getMinCapacita()>ro.getMaxCapacita()||(ro.getMinCapacita()>rt.getMaxCapacita()))){
					if(!(rt.getMinPortata()>ro.getMaxPortata()||(ro.getMinPortata()>rt.getMaxPortata()))){
						if(!(rt.getMinAltezza()>ro.getMaxAltezza()||(ro.getMinAltezza()>rt.getMaxAltezza()))){
							return true;
						}
					}
				}
			}
		}
		if(this.caratteristiche instanceof RichiestaGru){
			if(other.getCaratteristiche() instanceof RichiestaGru){
				RichiestaGru rt,ro;
				rt=(RichiestaGru)this.caratteristiche;
				ro=(RichiestaGru)other.getCaratteristiche();
				if(!(rt.getMinAltezza()>ro.getMaxAltezza()||(ro.getMinAltezza()>rt.getMaxAltezza()))){
					if(!(rt.getMinPortata()>ro.getMaxPortata()||(ro.getMinPortata()>rt.getMaxPortata()))){
						if(!(rt.getMinLunghezza()>ro.getMaxLunghezza()||(ro.getMinLunghezza()>rt.getMaxLunghezza()))){
							if(!(rt.getMinAngoloRotazione()>ro.getMaxAngoloRotazione()||(ro.getMinAngoloRotazione()>rt.getMaxAngoloRotazione()))){
								return true;
							}
						}
					}
				}
			}
		}
		if(this.caratteristiche instanceof RichiestaEscavatore){
			if(other.getCaratteristiche() instanceof RichiestaEscavatore){
				RichiestaEscavatore rt,ro;
				rt=(RichiestaEscavatore)this.caratteristiche;
				ro=(RichiestaEscavatore)other.getCaratteristiche();
				if(!(rt.getMinCapacita()>ro.getMaxCapacita()||(ro.getMinCapacita()>rt.getMaxCapacita()))){
					if(!(rt.getMinPortata()>ro.getMaxPortata()||(ro.getMinPortata()>rt.getMaxPortata()))){
						if(!(rt.getMinAltezza()>ro.getMaxAltezza()||(ro.getMinAltezza()>rt.getMaxAltezza()))){
							if(!(rt.getMinAltezza()>ro.getMaxAltezza()||(ro.getMinAltezza()>rt.getMaxAltezza()))){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}

