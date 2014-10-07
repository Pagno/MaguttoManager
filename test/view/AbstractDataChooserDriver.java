package view;

import static view.JDateChooserSetTextTask.setDateIn;
import static view.DateAssert.verifyThat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.fest.swing.core.Robot;
import org.fest.swing.driver.JComponentDriver;
import org.fest.swing.driver.TextDisplayDriver;

import com.toedter.calendar.JDateChooser;

public class AbstractDataChooserDriver extends JComponentDriver implements
		TextDisplayDriver<JDateChooser> {

	private static final String TEXT_PROPERTY = "text";

	public AbstractDataChooserDriver(Robot robot) {
		super(robot);
	}

	public void requireDate(JDateChooser dateChooser, Date data) {
		Date d = dateChooser.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat(); // creo l'oggetto
		sdf.applyPattern("dd/MM/yyyy");
		String expected=sdf.format(d);
		 verifyThat(textOf(dateChooser)).as(propertyName(dateChooser, TEXT_PROPERTY)).isEqualOrMatches(expected);
	}

	@Override
	public void requireText(JDateChooser component, Pattern pattern) {

	}

	@Override
	public String textOf(JDateChooser component) {
		return AbstractDateChooserTextQuery.textOf(component);
	}

	public void setDate(JDateChooser target, Date text) {
		robot.focus(target);
	    setDateIn(target, text);
	    robot.waitForIdle();
	}

	@Override
	public void requireText(JDateChooser component, String expected) {
		// TODO Auto-generated method stub
		
	}
}
