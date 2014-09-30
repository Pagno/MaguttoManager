package controller.data;

import java.util.GregorianCalendar;

import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;

public class Prenotazione {
	private Associazione associazione;
	private Integer durataLavoro;
	
	public Prenotazione(Associazione associazione,Integer durataLavoro){
		this.associazione=associazione;
		this.durataLavoro=durataLavoro;
	}

	public Associazione getAssociazione() {
		return associazione;
	}

	public void setAssociazione(Associazione associazione) {
		this.associazione = associazione;
	}

	public Integer getDurataLavoro() {
		return durataLavoro;
	}

	public void setDurataLavoro(Integer durataLavoro) {
		this.durataLavoro = durataLavoro;
	}
	
	public Associazione select() {
		associazione.setConfermata(true);
		return associazione;
	}
	
	public Richiesta getRichiesta() {
		return associazione.getRichiesta();
	}
	
	public Macchina getMacchina(){
		return associazione.getMacchina();
	}
	
	public GregorianCalendar getDataInizio(){
		return associazione.getDataInizio();
	}
	
	public GregorianCalendar getDataFine(){
		return associazione.getDataFine();
	}
}
