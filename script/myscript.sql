create table APP.Macchina (
	Codice integer  primary key,
	Produttore  varchar(20) not null,
	Modello varchar(20) not null,
	Tipo varchar(10) not null check (Tipo like 'Gru' or Tipo like 'Camion' or Tipo like 'Ruspa' or Tipo like 'Escavatore'),
	CapacitaMax integer,
	PortataMax integer, 
	AltezzaMax integer,
	LunghezzaMax integer,
	ProfonditaMax integer,
	RotazioneMax integer);

create table APP.Cantiere (
	Codice integer  primary key, 
	Nome varchar(30) not null, 
	DataApertura date not null,
	DataChiusura date not null, 
	Indirizzo varchar(50) not null);
	
create table APP.Lavoro(
	Codice integer  primary key, 
	Nome varchar(30) not null,
	CodiceCantiere integer references APP.Cantiere(Codice), 
	DataInizio date not null,
	DataFine date not null);
	
create table APP.Associazione (
	Id integer primary key,
	CodiceMacchina integer references APP.Macchina(Codice), 
	CodiceLavoro integer references APP.Lavoro(Codice), 
	DataInizio date not null,
	DataFine date not null);
	
create table APP.Richiesta(
	Codice integer  primary key,
	CodiceLavoro integer references APP.Lavoro(Codice), 
	CodiceAssociazione integer references APP.Associazione(Id),
	DataInizio date not null,
	DataFine date not null,
	Tipo varchar(10) not null check (Tipo like 'Gru' or Tipo like 'Camion' or Tipo like 'Ruspa' or Tipo like 'Escavatore'),
	CapacitaMax integer,
	PortataMax integer, 
	AltezzaMax integer,
	LunghezzaMax integer,
	ProfonditaMax integer,
	RotazioneMax integer);