package controller;

import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;

public class Coppia {
	private Richiesta ric;
	private Macchina mac;
	private boolean confermata;
	
	public Coppia(Richiesta r, Macchina m){
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
	
}
