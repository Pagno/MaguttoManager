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

public interface AbstractCantieriController {
	public  Cantiere getCantiere(int codiceCantiere);
	public  boolean modificaCantiere(int codiceCantiere,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priority priorita );
	public  boolean aggiungiLavoro(int codiceCantiere,String nomeLavoro,GregorianCalendar dataInizio,GregorianCalendar dataFine);
	
	public  void aggiungiRichiestaObserver(Observer observer );
	
	public  boolean eliminaLavoro(int codiceLavoro);
	public  boolean eliminaRichiesta(int codiceRichiesta);
	public   ArrayList<Macchina>  getElencoMacchineDisponibili(int codiceRichiesta);
	
	public  boolean liberaRichiesta(int codiceRichiesta);
	public  boolean addRichiesta(int codiceCantiere,int codiceLavoro,RichiestaMacchina richiesta);
	public  boolean soddisfaRichiesta(int codiceRichiesta, int codiceMacchina) ;
	public  ArrayList<Associazione> generateAssociations();
	public  void confermaAssociazioniListener(ArrayList<Associazione> data);
	public  void modificaLavoro(int codiceLavoro, String nome,GregorianCalendar inizio, GregorianCalendar fine);
}

