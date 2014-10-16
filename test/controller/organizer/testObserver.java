package controller.organizer;

import java.util.Observable;
import java.util.Observer;

public class testObserver implements Observer {
	
	public Object[] s;

	@Override
	public void update(Observable o, Object arg) {
		s = (Object[]) arg;
	}
	
}
