package model.organizer.data;

import java.util.GregorianCalendar;

public final class Richiesta implements Comparable<Richiesta>{
	


	private RichiestaMacchina caratteristiche;
	private Macchina macchina;
	private Lavoro lavoro;
	
	/**   codice. */
	private static int ultimoCodice;

	private int codice;
	
	
	public Richiesta(RichiestaMacchina caratteristiche, Lavoro lavoro) {
		super();
		
		assignCodice();
		this.caratteristiche = caratteristiche;
		this.lavoro=lavoro;
		this.setMacchina(null);
	}
	
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

	public RichiestaMacchina getCaratteristiche() {
		return caratteristiche;
	}


	public void setCaratteristiche(RichiestaMacchina caratteristiche) {
		this.caratteristiche = caratteristiche;
	}


	public boolean isSoddisfatta() {
		if(macchina==null){
			return false;
		}
		else{
			return true;
		}
	}



	
	
	
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Richiesta other = (Richiesta) obj;
	
		if (caratteristiche == null) {
			if (other.caratteristiche != null)
				return false;
		} else if (!caratteristiche.equals(other.caratteristiche))
			return false;
		if (codice != other.codice)
			return false;
		if (macchina == null) {
			if (other.macchina != null)
				return false;
		} else if (!macchina.equals(other.macchina))
			return false;
		if (lavoro == null) {
			if (other.lavoro != null)
				return false;
		} else if (!lavoro.equals(other.lavoro))
			return false;
		return true;
	}

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


	public Macchina getMacchina() {
		return macchina;
	}


	public void setMacchina(Macchina macchina) {
		if(this.macchina!=null){
			this.macchina.removeRichiesta(this);
		}
		if(macchina==null){
			this.macchina=null;
		}
		else{
			if(this.rispettaRichiesta(macchina)){
				this.macchina = macchina;
				this.macchina.addRichiesta(this);
			}
			else{
				this.macchina=null;
			}
		}
	}

	
	@Override
	public int compareTo(Richiesta r) {
		return this.lavoro.getDataInizio().compareTo(r.lavoro.getDataInizio());
	}
	public GregorianCalendar getDataInizio(){
		return lavoro.getDataInizio();
	}
	public GregorianCalendar getDataFine(){
		return lavoro.getDataFine();
	}
	
	public Priority getPriorita(){
		return lavoro.getPriorita();
	}
	
	public int getCodiceLavoro(){
		return lavoro.getCodice();
	}
	
	public int getCodiceCantiere(){
		return lavoro.getCodiceCantiere();
	}
}

