package view;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.util.Date;

import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;

import com.toedter.calendar.JDateChooser;

public class JDateChooserSetTextTask {

	@RunsInEDT
	static void setDateIn(final JDateChooser dateChooser, final Date date) {
		execute(new GuiTask() {
			protected void executeInEDT() {
				dateChooser.setDate(date);
			}
		});
	}

	private JDateChooserSetTextTask() {
	}
}
