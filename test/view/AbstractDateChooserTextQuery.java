package view;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

import com.toedter.calendar.JDateChooser;

final class AbstractDateChooserTextQuery {

	@RunsInEDT
	static String textOf(final JDateChooser dateChooser) {
		return execute(new GuiQuery<String>() {
			protected String executeInEDT() {
				Date d = dateChooser.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat(); // creo l'oggetto
				sdf.applyPattern("dd/MM/yyyy");
				return sdf.format(d);
			}
		});
	}

	private AbstractDateChooserTextQuery() {
	}
}
