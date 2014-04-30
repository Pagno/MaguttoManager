package model;

public interface ModelInterface {
	public void storeData();
	public void refreshData();
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);
	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);
	public boolean eliminaMacchina(int codice);
}
