package database;

// TODO: Auto-generated Javadoc
/**
 * Classe che crea le tabelle e inserisce dei dati nel caso di primo utilizzo dell'applicazione
 * 
 * @author Mauro
 */
public class InitDB {
	
	/**
	 * Istanzia un nuovo InitDB.
	 */
	public InitDB(){
		
	}
	
	
	/**
	 * Crea le tabelle.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public void createTables() throws JavaDBException{
		System.out.println("Query1");
		
		String qry = "create table APP.Cantiere ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"Codice integer not null unique, " +
				"Name varchar(30) not null, " +
				"DataApertura date not null, " +
				"DataChiusura date not null, " +
				"Indirizzo varchar(50) not null, " +
				"primary key (I))";
		Database.update(qry);
		System.out.println("Query2");
		
		qry = "create table APP.Macchina ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"Codice integer not null unique, " +
				"Produttore  varchar(20) notnull, " +
				"Modello varchar(20) not null, " +
				"Tipo ENUM('Gru','Camion','Escavatore','Ruspa'), " +
				"CapacitaMax integer, " +
				"PortataMax integer, " +
				"AltezzaMax integer, " +
				"LunghezzaMax integer, " +
				"ProfonditaMax integer, " +
				"RotazioneMax integer, " +
				"primary key (I))";
		Database.update(qry);

		
		qry = "create table APP.Prenotazione ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"CodiceMacchina integer not null references APP.Macchina(Codice), " +
				"CodiceCantiere integer not null references APP.Cantiere(Codice), " +
				"DataInizio date not null, " +
				"DataFine date not null, " +
				"primary key (I))";
		Database.update(qry);
	}
	
	
	public void popola()throws JavaDBException{
		String qry = "Insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,AltezzaMax)"+
				"Values(111,Kramer Allrad,580,Ruspa,2900,50,250)";
		Database.update(qry);
		
		
	}

	
	/**
	 * Inserisce nelle tabelle Users e Settings.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public void insertIntoTables() throws JavaDBException{
		String qry = "insert into APP.Users (ID, Name, Password, Type, DBupdate) values " +
				"('Amm1', 'Username', 'Password', 'Admin', true)";
		Database.update(qry);
		qry = "insert into APP.Settings (NameParam, Value) values " +
				"('Sconto'	  , '30'  ), " +
				"('Contatore' , '5'   ), " +
				"('FirstLogin', 'true'), " +
				"('Update'	  , 'true'), " +
				"('Coperto'	  , '1'	  )";
		Database.update(qry);
		
	}
	
}