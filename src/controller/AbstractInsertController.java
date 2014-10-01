package controller;

import java.util.GregorianCalendar;

import model.organizer.data.Priority;

public abstract class AbstractInsertController {
	public abstract boolean insertNewGruListener(String produttore,String modello,int rotazione, int portata,int lunghezza,int altezza);
	public abstract boolean insertNewRuspaListener(String produttore, String modello,int capacita,int portata,int altezza);
	public abstract boolean insertNewEscavatoreListener(String produttore, String Modello,int capacita,int portata,int altezza,int profondita);
	public abstract boolean insertNewCamionListener(String produttore,String Modello,int capacita,int portata,int lunghezza);
	public abstract boolean insertNewCantiereListener(String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita );

}
