package controller;

import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;

public class Couple {
	Richiesta ric;
	Macchina mac;
	
	public Couple(Richiesta r, Macchina m){
		ric=r;
		mac=m;
	}

	public Richiesta getRic() {
		return ric;
	}

	public void setRic(Richiesta ric) {
		this.ric = ric;
	}

	public Macchina getMac() {
		return mac;
	}

	public void setMac(Macchina mac) {
		this.mac = mac;
	}
	
	
}
