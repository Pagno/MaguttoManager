package model;

import java.util.ArrayList;

public class ParcoMacchine{
	ArrayList<Macchina> gru=new ArrayList<Macchina>();
	public ParcoMacchine(){
		Gru g =new Gru();
		gru.add(g);
		if(gru.get(0) instanceof Escavatore)  System.out.print("OK");
		else System.out.print("KO");
	}
}
