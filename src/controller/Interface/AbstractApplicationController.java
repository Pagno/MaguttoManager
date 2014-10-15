package controller.Interface;

public interface AbstractApplicationController {
	public boolean eliminaMacchina(Integer codiceMacchina);	
	public boolean eliminaCantiere(Integer codiceCantiere);	
	public void exitManager();
	public void caricaDatiListener();
	public void salvaDatiListener();
	public void chiusuraProgramma();
}
