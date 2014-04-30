package model;

public interface ModelInterface {
	public void storeData();
	public void refreshData();
	
	//GRU
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);
	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza);

	//CAMION
	public void aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza);
	public void modificaCamion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza);

	
	public boolean eliminaMacchina(int codice);
}
