package model.organizer.data;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class Lavoro {
	private int codice;
	private String nome;
	private GregorianCalendar dataInizio;
	private GregorianCalendar dataFine;
	private ArrayList<Richiesta> macchinariRichiesti;
	
	
	public Lavoro(int codice, String nome, GregorianCalendar dataInizio,
			GregorianCalendar dataFine) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.macchinariRichiesti=new ArrayList<Richiesta>();
	}


	public int getCodice() {
		return codice;
	}


	public void setCodice(int codice) {
		this.codice = codice;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public GregorianCalendar getDataInizio() {
		return dataInizio;
	}


	public void setDataInizio(GregorianCalendar dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getStrDataInizio() {

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");

		return df.format(dataInizio.getTime());
		
	}

	public GregorianCalendar getDataFine() {
		return dataFine;
	}

	public String getStrDataFine() {

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");

		return df.format(dataFine.getTime());
		
	}


	public void setDataFine(GregorianCalendar dataFine) {
		this.dataFine = dataFine;
	}


	@Override
	public String toString() {
		return  codice + " " + nome + " "
				+ getStrDataInizio() + " " + getStrDataFine();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lavoro other = (Lavoro) obj;
		if (codice != other.codice)
			return false;
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
		if (macchinariRichiesti == null) {
			if (other.macchinariRichiesti != null)
				return false;
		} else if (!macchinariRichiesti.equals(other.macchinariRichiesti))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	public void inserisciRichiesta(RichiestaMacchina caratteristiche){
		Richiesta r=new Richiesta(caratteristiche);
		macchinariRichiesti.add(r);
	}
	
	public void caricaRichiesta(RichiestaMacchina caratteristiche,Integer codice, Macchina m){
		Richiesta r=new Richiesta(caratteristiche,codice);
		if(r.rispettaRichiesta(m)){
			r.setMacchina(m);
		}
		macchinariRichiesti.add(r);
	}
	
	public void modificaRichiesta(Integer codice,RichiestaMacchina caratteristiche){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
				item.setCaratteristiche(caratteristiche);
			}
		}
	}
	
	public boolean eliminaRichiesta(Integer codice){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
				macchinariRichiesti.remove(item);
				return true;
			}
		}
		return false;
	}
	
	public void soddisfaRichiesta(Integer codice,Macchina mac){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
				if(item.rispettaRichiesta(mac)){
					item.setMacchina(mac);
				}
			}
		}
	}
	
	//vogliamo cancellare l'associazione, quindi inseriamo null al posto dell'associazione precedente
	public void liberaRichiesta(Integer codice){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
					item.setMacchina(null);
			}
		}
	}
	
	//Permette di vedere se il lavoro ha ancora delle richieste non soddisfatte, e necessita quindi di macchine
	public boolean isScoperto(){
		for(Richiesta item:macchinariRichiesti){
			if(!item.isSoddisfatta()){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Richiesta> whereScoperto(){
		if(!isScoperto()){
			return null;
		}
		ArrayList<Richiesta>richScoperte=new ArrayList<Richiesta>();
		for(Richiesta item:macchinariRichiesti){
			if(!item.isSoddisfatta()){
				richScoperte.add(item);
			}
		}
		return richScoperte;
	}

	public ArrayList<Richiesta> getListaRichieste(){
		return macchinariRichiesti;
	}
	
}
