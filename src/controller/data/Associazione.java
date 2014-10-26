package controller.data;

import java.util.GregorianCalendar;

import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;

public class Associazione {
	private Richiesta ric;
	private Macchina mac;
	private boolean confermata;
	
	public Associazione(Richiesta r, Macchina m){
		ric=r;
		mac=m;
		confermata=false;
	}

	public Richiesta getRichiesta() {
		return ric;
	}

	public void setRichiesta(Richiesta ric) {
		this.ric = ric;
	}

	public Macchina getMacchina() {
		return mac;
	}

	public void setMacchina(Macchina mac) {
		this.mac = mac;
	}
	
	public void setConfermata(boolean val){
		confermata=val;
	}
	
	public boolean getConfermata(){
		return confermata;
	}
	
	public void concretizza(){
		if(ric.rispettaRichiesta(mac)){
			ric.setMacchina(mac);
		}
	}
	public String toString(){
		String result="Cantiere:"+ric.getLavoro().getCantiere().getNomeCantiere()
				+" Lavoro:"+ric.getLavoro().getNome()+ " -->  Macchina: "+mac.getProduttore()+" - "+mac.getModello();
		return result;
	}
	
	public GregorianCalendar getDataInizio(){
		return ric.getDataInizio();
	}
	
	public GregorianCalendar getDataFine(){
		return ric.getDataFine();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Associazione other = (Associazione) obj;
		if (confermata != other.confermata)
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (ric == null) {
			if (other.ric != null)
				return false;
		} else if (!ric.equals(other.ric))
			return false;
		return true;
	}
	
	public boolean collide(Richiesta other){
		return this.ric.collide(other);
	}
	
	public boolean collide(Associazione other){
		return this.ric.collide(other.getRichiesta());
	}
	
}
