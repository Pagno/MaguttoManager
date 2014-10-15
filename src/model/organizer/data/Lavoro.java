package model.organizer.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;


public class Lavoro extends DefaultMutableTreeNode{

	private int codice;
	private String nome;
	private GregorianCalendar dataInizio;
	private GregorianCalendar dataFine;
	private ArrayList<Richiesta> macchinariRichiesti;
	private Cantiere cantiere;
	

	public Lavoro(int codice, String nome, Cantiere cantiere, GregorianCalendar dataInizio,
			GregorianCalendar dataFine) {
		super();

		this.codice = codice;
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.macchinariRichiesti=new ArrayList<Richiesta>();
		this.cantiere=cantiere;
	}

	public Cantiere getCantiere() {
		return cantiere;
	}

	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
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
		if (!(obj instanceof Lavoro))
			return false;
		Lavoro other = (Lavoro) obj;
		if (cantiere == null) {
			if (other.cantiere != null)
				return false;
		} else if (!cantiere.equals(other.cantiere))
			return false;
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
		if (!macchinariRichiesti.equals(other.macchinariRichiesti))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public int inserisciRichiesta(RichiestaMacchina caratteristiche){
		Richiesta r=new Richiesta(caratteristiche,this);
		macchinariRichiesti.add(r);
		add(r);
		return r.getCodice();
		
		
	}
	
	public void caricaRichiesta(RichiestaMacchina caratteristiche,Integer codice, Macchina m){
		Richiesta r=new Richiesta(caratteristiche,this,codice);
		if(r.rispettaRichiesta(m)){
			r.setMacchina(m);
		}
		add(r);
		macchinariRichiesti.add(r);
	}
	
	public void modificaRichiesta(Integer codice,RichiestaMacchina caratteristiche){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
				item.setCaratteristiche(caratteristiche);
			}
		}
	}
	
	public boolean hasRichiesta(Integer codice){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codice){
				return true;
			}
		}
		return false;
	}

	public Richiesta getRichiesta(Integer codiceRichiesta){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codiceRichiesta){
				return item;
			}
		}
		return null;
	}
	
	public boolean eliminaRichiesta(Integer codiceRichiesta){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codiceRichiesta){
				item.setMacchina(null);
				macchinariRichiesti.remove(item);
				return true;
			}
		}
		return false;
	}
	
	public void svuotaRichieste(){
		for(Richiesta item:macchinariRichiesti){
			item.setMacchina(null);
		}
		macchinariRichiesti.clear();
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
	
	//vogliamo cancellare la richiesta, quindi inseriamo null al posto dell'associazione precedente
	public void liberaRichiesta(Integer codiceRichiesta){
		for(Richiesta item:macchinariRichiesti){
			if(item.getCodice()==codiceRichiesta){
				item.setMacchina(null);
			}
		}
	}
	
	//Libera le richieste con associata una data macchina
	public void liberaMacchina(int codiceMacchina){
		for(Richiesta item:macchinariRichiesti){
			if(item.getMacchina()!=null){
				if(item.getMacchina().getCodice()==codiceMacchina){
					item.setMacchina(null);
				}
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

	
	public Priority getPriorita(){
		return cantiere.getPriorita();
	}
	
	public int getCodiceCantiere(){
		return cantiere.getCodice();
	}
	
	public ArrayList<Lavoro> getRelatedWorks(){
		return cantiere.getElencoLavori();
	}
	
	public int getDurata(){
		int d;
		GregorianCalendar sx,dx;
		d=0;
		sx=(GregorianCalendar)this.getDataInizio().clone();
		dx=(GregorianCalendar)this.getDataFine().clone();
		while(sx.before(dx)){
			sx.add(Calendar.DAY_OF_MONTH, 1);
			d++;
		}
		return d;
	}
	

}
