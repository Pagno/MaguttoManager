package model.organizer.data;

/**
 *   La classe Gru rappresenta le caratteristiche di una gru.
 *   
 *   Permette di gestire le quattro caratteristiche principali di tale tipo di macchina, cioè la lunghezza, l'altezza,la portata e l'angolo di rotazione.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class Gru extends Macchina{
	
	/**   Lunghezza. */
	private int lunghezza;
	
	/**   Altezza. */
	private int altezza;
	
	/**   Portata massima. */
	private int portataMax;
	
	/**   Angolo di rotazione. */
	private int angoloRotazione;
	
	/**
	 * Istanzia una nuova gru.
	 *
	 * @param codice Il codice della Gru
	 * @param produttore Il produttore della Gru
	 * @param modello Il modello della Gru
	 * @param rotazione L'angolo di rotazione della Gru
	 * @param portata La portata della Gru
	 * @param lunghezza La lunghezza della Gru
	 * @param altezza L'altezza della Gru
	 */
	public Gru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		super(codice,produttore,modello);

		this.altezza=altezza;
		this.angoloRotazione=rotazione;
		this.portataMax=portata;
		this.lunghezza=lunghezza;
	}
	
	//GET
	/**
	 * Restituisce la lunghezza della gru.
	 *
	 * @return La lunghezza della gru
	 */
	public int getLunghezza(){		return this.lunghezza;	}
	
	/**
	 * Restituisce la portata massima della gru.
	 *
	 * @return La portata massima della gru
	 */
	public int getPortataMassima(){		return this.portataMax;	}
	
	/**
	 * Restituisce l'altezza della gru.
	 *
	 * @return L'altezza della gru
	 */
	public int getAltezza(){		return this.altezza;	}
	
	/**
	 * Restituisce l'angolo di rotazione della gru.
	 *
	 * @return L'angolo di rotazione della gru
	 */
	public int getAngoloRotazione(){		return this.angoloRotazione;	}
	
	
	//SET
	/**
	 * Modifica la lunghezza massima della gru.
	 *
	 * @param lunghezza La nuova lunghezza massima della gru
	 */
	public void setLunghezzaMassima(int lunghezza){	this.lunghezza=lunghezza;	}
	
	/**
	 * Modifica la portata massima della gru.
	 *
	 * @param portata La nuova portata massima della gru
	 */
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	
	/**
	 * Modifica l'altezza della gru.
	 *
	 * @param altezza La nuova altezza della gru
	 */
	public void setAltezza(int altezza){		this.altezza=altezza;	}
	
	/**
	 * Modifica l'angolo di rotazione della gru.
	 *
	 * @param rotazione Il nuovo angolo di rotazione della gru
	 */
	public void setAngoloRotazione(int rotazione){	this.angoloRotazione=rotazione;	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getLunghezza() + " " + this.getPortataMassima() + " " + this.getAltezza() + " " + this.getAngoloRotazione();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + altezza;
		result = prime * result + angoloRotazione;
		result = prime * result + lunghezza;
		result = prime * result + portataMax;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gru other = (Gru) obj;
		if (altezza != other.altezza)
			return false;
		if (angoloRotazione != other.angoloRotazione)
			return false;
		if (lunghezza != other.lunghezza)
			return false;
		if (portataMax != other.portataMax)
			return false;
		return true;
	}
	
}