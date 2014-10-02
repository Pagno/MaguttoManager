package view;

import static view.JDateChooserSetTextTask.setDateIn;
import static view.DateAssert.verifyThat;

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

	@Override
	public void requireText(JDateChooser dateChooser, String expected) {
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

}
