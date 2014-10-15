package controller.Interface;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observer;

import controller.data.Associazione;
import model.ModelInterface;
import model.organizer.data.Cantiere;
import model.organizer.data.Priority;
import model.organizer.data.Macchina;
import model.organizer.data.RichiestaMacchina;

public abstract class AbstractCantieriController {
	public abstract Cantiere getCantiere(int codiceCantiere);
	public abstract boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita );
	public abstract boolean aggiungiLavoro(int codiceCantiere,String nomeLavoro,GregorianCalendar dataInizio,GregorianCalendar dataFine);
	
	public abstract void aggiungiRichiestaObserver(Observer observer );
	
	public abstract boolean eliminaLavoro(int codiceLavoro);
	public abstract boolean eliminaRichiesta(int codiceRichiesta);
	public abstract  ArrayList<Macchina>  getElencoMacchineDisponibili(int codiceRichiesta);
	
	public abstract boolean liberaRichiesta(int codiceRichiesta);
	public abstract boolean addRichiesta(int codiceCantiere,int codiceLavoro,RichiestaMacchina richiesta);
	public abstract boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina) ;
	public abstract ArrayList<Associazione> generateAssociations();
	public abstract void confermaAssociazioniListener(ArrayList<Associazione> data);
	public abstract void modificaLavoro(int codiceLavoro, String nome,GregorianCalendar inizio, GregorianCalendar fine);
}

