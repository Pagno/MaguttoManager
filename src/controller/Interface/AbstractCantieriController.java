package controller.Interface;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import model.organizer.data.Cantiere;
import model.organizer.data.Priority;
import model.organizer.data.Macchina;

public abstract class AbstractCantieriController {
	public abstract Cantiere getCantiere(int codiceCantiere);
	public abstract boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita );
	public abstract boolean aggiungiLavoro(int codiceCantiere,String nomeLavoro,GregorianCalendar dataInizio,GregorianCalendar dataFine);
	public abstract boolean aggiungiRichiestaGru();
	public abstract boolean soddisfaRichiestaRuspa(Observer observer );
	
	public abstract void aggiungiRichiestaObserver(Observer observer );
	
	public abstract boolean eliminaLavoro(int codiceLavoro);
	public abstract boolean eliminaRichiesta(int codiceRichiesta);
	public abstract  ArrayList<Macchina>  getElencoMacchineDisponibili(int codiceRichiesta);
	
	public abstract boolean liberaRichiesta(int codiceRichiesta);
}

