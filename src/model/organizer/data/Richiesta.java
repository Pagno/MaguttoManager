package model.organizer.data;

public class Richiesta {
	


	private RichiestaMacchina caratteristiche;
	private Macchina macchina;
	
	/**   codice. */
	private static int ultimoCodice;

	private int codice;
	
	
	public Richiesta(RichiestaMacchina caratteristiche) {
		super();
		
		assignCodice();
		this.caratteristiche = caratteristiche;
		this.setMacchina(null);
	}
	
	public Richiesta(RichiestaMacchina caratteristiche, int codiceRichiesta) {
		super();
		
		if(codiceRichiesta>ultimoCodice){
			ultimoCodice=codiceRichiesta;
		}
		this.codice=codiceRichiesta;
		this.caratteristiche = caratteristiche;
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
		if(macchina==null){
			this.macchina=null;
		}
		else{
			if(this.rispettaRichiesta(macchina)){
				this.macchina = macchina;
			}
			else{
				this.macchina=null;
			}
		}
	}
	
	
}

