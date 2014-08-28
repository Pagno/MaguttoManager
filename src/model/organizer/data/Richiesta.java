package model.organizer.data;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Richiesta {
	


	private RichiestaMacchina caratteristiche;
	private Associazione associazioneSoddisfacente;
	private Lavoro lavoro;
	
	public Richiesta(RichiestaMacchina caratteristiche, Lavoro lavoro) {
		super();
		
		assignCodice();
		this.caratteristiche = caratteristiche;
		this.lavoro = lavoro;
		this.setAssociazioneSoddisfacente(null);
	}
	
	/**   codice. */
	private static int ultimoCodice;

	private int codice;
	
	/**
	 * Aggiorna codice.
	 *
	 * @param code   code
	 */
	protected void aggiornaCodice(int code){
		if(ultimoCodice<code){
			ultimoCodice=code;
		}
	}
	
	
	/**
	 * Inits   codice.
	 */
	public static void initCodice(){
		ultimoCodice=0;
	}
	
	public void assignCodice(){
		ultimoCodice++;
		codice=ultimoCodice;
	}
	
	/**
	 * Gets   codice.
	 *
	 * @return   codice
	 */
	protected int getCodice(){
		return codice;
	}

	public RichiestaMacchina getCaratteristiche() {
		return caratteristiche;
	}


	public void setCaratteristiche(RichiestaMacchina caratteristiche) {
		this.caratteristiche = caratteristiche;
	}


	public boolean isSoddisfatta() {
		if(associazioneSoddisfacente==null){
			return false;
		}
		else{
			return true;
		}
	}


	public Lavoro getLavoro() {
		return lavoro;
	}


	public void setLavoro(Lavoro lavoro) {
		this.lavoro = lavoro;
	}


	public void setCodice(int codice) {
		this.codice = codice;
	}

	
	
	
	@Override
	public String toString() {
		String s;
		s=getCodice() + " " + caratteristiche + " " + isSoddisfatta();
		if(isSoddisfatta()){
			s=s + " " + associazioneSoddisfacente.getMacchina().getCodice();
		}
		else{
			s=s + " null";
		}
				
		s=s + " " + lavoro.getCodice();
		
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
		if (lavoro == null) {
			if (other.lavoro != null)
				return false;
		} else if (!lavoro.equals(other.lavoro))
			return false;
		if (associazioneSoddisfacente == null) {
			if (other.associazioneSoddisfacente != null)
				return false;
		} else if (!associazioneSoddisfacente.equals(other.associazioneSoddisfacente))
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
	int getNextCodice(){
		return ultimoCodice+1;
	}


	public Associazione getAssociazioneSoddisfacente() {
		return associazioneSoddisfacente;
	}


	public void setAssociazioneSoddisfacente(Associazione associazioneSoddisfacente) {
		this.associazioneSoddisfacente = associazioneSoddisfacente;
	}
	
	
}

