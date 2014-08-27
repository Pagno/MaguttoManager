package model.organizer.data;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Richiesta {
	

	enum tipoMacchina{RUSPA,CAMION,ESCAVATORE,GRU};
	private tipoMacchina tipo;
	private GregorianCalendar dataInizio;
	private GregorianCalendar dataFine;
	private RichiestaMacchina caratteristiche;
	private boolean soddisfatta;
	private Macchina macchina;
	private Lavoro lavoro;
	
	public Richiesta(tipoMacchina tipo,
			GregorianCalendar dataInizio, GregorianCalendar dataFine,
			RichiestaMacchina caratteristiche, Lavoro lavoro) {
		super();
		
		this.tipo = tipo;
		assignCodice();
		dataInizio = dataInizio;
		dataFine = dataFine;
		//TODO inserire le caratteristiche o passare il riferimento a RichiestaMacchina??
		this.caratteristiche = caratteristiche;
		this.lavoro = lavoro;
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

	public tipoMacchina getTipo() {
		return tipo;
	}


	public void setTipo(tipoMacchina tipo) {
		this.tipo = tipo;
	}


	public GregorianCalendar getDataInizio() {
		return dataInizio;
	}


	public void setDataInizio(GregorianCalendar dataInizio) {
		dataInizio = dataInizio;
	}


	public GregorianCalendar getDataFine() {
		return dataFine;
	}

	public String getStrDataInizio() {

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");

		return df.format(dataInizio.getTime());
		
	}
	
	public String getStrDataFine() {

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");

		return df.format(dataFine.getTime());
		
	}

	public void setDataFine(GregorianCalendar dataFine) {
		dataFine = dataFine;
	}


	public RichiestaMacchina getCaratteristiche() {
		return caratteristiche;
	}


	public void setCaratteristiche(RichiestaMacchina caratteristiche) {
		this.caratteristiche = caratteristiche;
	}


	public boolean isSoddisfatta() {
		return soddisfatta;
	}


	public void setSoddisfatta(boolean soddisfatta) {
		this.soddisfatta = soddisfatta;
	}


	public Macchina getMacchina() {
		return macchina;
	}


	public void setMacchina(Macchina macchina) {
		this.macchina = macchina;
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
		s=getCodice() + " " + tipo + " " + dataInizio
				+ " " + dataFine + " "
				+ caratteristiche + " " + soddisfatta;
		if(soddisfatta){
			s=s + " " + macchina.getCodice();
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
		if (soddisfatta != other.soddisfatta)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	public boolean rispettaRichiesta(Macchina m){
		//TODO controllare se la macchina è del tipo dichiarato nell'enum, poi confrontare con la macchina inserita.
		return false;
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
	
	
}

