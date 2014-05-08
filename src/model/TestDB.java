package model;

import database.Database;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestDB {
	public static void main(String[] args) {
		/*Database db=new Database();
		ModelConnector mc=new ModelConnector(db);
		mc.refreshData();
		mc.aggiungiCamion("Hitachi", "Camioncino", 150, 140, 200);
		GregorianCalendar ap=new GregorianCalendar(2016,Calendar.JANUARY,1);
		GregorianCalendar ch=new GregorianCalendar(2016,Calendar.DECEMBER,17);
		mc.aggiungiCantiere("Pedemontana", "Viale marconi 5", ap, ch);
		ap=new GregorianCalendar(2016,Calendar.FEBRUARY,22);
		ch=new GregorianCalendar(2016,Calendar.NOVEMBER,11);
		mc.aggiungiAssociazione(1, 1, ap, ch);
		mc.pubblicaContenuto();
		mc.storeData();*/
		
		Database db=new Database();
		ModelConnector mc=new ModelConnector(db);
		mc.refreshData();
		mc.pubblicaContenuto();
		mc.storeData();
	}


}
