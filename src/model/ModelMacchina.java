package model;

import java.util.Observable;

public abstract class ModelMacchina extends Observable{
	private static int codice;
	protected void aggiornaCodice(int code){
		if(codice<code){
			codice=code;
		}
	}
	protected void incrementaCodice(){
		codice++;
	}
	protected static void initCodice(){
		codice=0;
	}
	protected int getCodice(){
		return codice;
	}
}