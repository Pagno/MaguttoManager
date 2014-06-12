package database;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta le eccezioni che possono essere sollevate dal database.
 * 
 * @author Mauro
 */
public class DBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The error code. */
	int errorCode;
	
	
	/**
	 * Istanzia una nuova eccezione java db.
	 */
	public DBException() {
		super();
	}
	
	/**
	 * Istanzia una nuova eccezione java db.
	 *
	 * @param errorCode il codice errore
	 */
	public DBException(int errorCode){
		exceptionDBManager(errorCode);
	}
	
	
	/**
	 * Manager delle eccezioni del DB.
	 *
	 * @param errorCode il codice errore
	 */
	public void exceptionDBManager(int errorCode){
		switch(errorCode){
		case 1: 
			System.out.println("Errore nel caricamento del driver");
			break;
		case 2:	
			System.out.println("Connessione fallita: database gia connesso");
			break;
		case 3:
			System.out.println("Errore creazione Statement");
			break;
		case 4:
			System.out.println("Errore nella esecuzione della query");
			break;
		case 5: 
			System.out.println("Errore nella esecuzione della modifica");
			break;
		case 6: 
			System.out.println("Errore chiusura ResultSet");
			break;
		case 7: 
			System.out.println("Errore chiusura Statement");
			break;
		case 8: 
			System.out.println("Errore chiusura Connessione");
			break;
		case 9: 
			System.out.println("Errore cancellazione tabella");
			break;	
		case 20: 
			System.out.println("Istanza non ritornata");
			break;	
		}
	}
	

}