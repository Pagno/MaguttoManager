package view.utils;

import java.util.Date;

import javax.swing.JButton;

import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.core.Robot;
import org.fest.swing.driver.AbstractButtonDriver;
import org.fest.swing.fixture.CommonComponentFixture;
import org.fest.swing.fixture.ComponentFixture;
import org.fest.swing.fixture.FocusableComponentFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fest.swing.fixture.KeyboardInputSimulationFixture;
import org.fest.swing.fixture.MouseInputSimulationFixture;
import org.fest.swing.fixture.StateVerificationFixture;
import org.fest.swing.timing.Timeout;

import com.toedter.calendar.JDateChooser;

public class JDateChooserFixture extends ComponentFixture<JDateChooser>
		implements CommonComponentFixture {

	private AbstractDataChooserDriver driver;//=new AbstractDataChooserDriver(robot);

	public String text() {
		return driver.textOf(target);
	}
	public JDateChooserFixture requireDate(Date expected) {
		driver.requireDate(target,expected);
		return this;
	}
	public JDateChooserFixture setDate(Date text) {
		driver.setDate(target, text);
		return this;
	}

	public JDateChooserFixture(Robot robot, JDateChooser date) {
		super(robot, date);
		driver=new AbstractDataChooserDriver(robot);
	}

	@Override
	public FocusableComponentFixture focus() {
		 
		return null;
	}

	@Override
	public FocusableComponentFixture requireFocused() {
		return null;
	}

	@Override
	public KeyboardInputSimulationFixture pressAndReleaseKeys(int... keyCodes) {
		return null;
	}

	@Override
	public KeyboardInputSimulationFixture pressKey(int keyCode) {
		return null;
	}

	@Override
	public KeyboardInputSimulationFixture pressAndReleaseKey(
			KeyPressInfo keyPressInfo) {
		return null;
	}

	@Override
	public KeyboardInputSimulationFixture releaseKey(int keyCode) {
		return null;
	}

	@Override
	public MouseInputSimulationFixture click() {
		return null;
	}

	@Override
	public MouseInputSimulationFixture click(MouseButton button) {
		return null;
	}

	@Override
	public MouseInputSimulationFixture click(MouseClickInfo mouseClickInfo) {
		 
		return null;
	}

	@Override
	public MouseInputSimulationFixture doubleClick() {
		 
		return null;
	}

	@Override
	public MouseInputSimulationFixture rightClick() {
		 
		return null;
	}

	@Override
	public StateVerificationFixture requireDisabled() {
		 
		return null;
	}

	@Override
	public StateVerificationFixture requireEnabled() {
		 
		return null;
	}

	@Override
	public StateVerificationFixture requireEnabled(Timeout timeout) {
		 
		return null;
	}

	@Override
	public StateVerificationFixture requireNotVisible() {
		 
		return null;
	}

	@Override
	public StateVerificationFixture requireVisible() {
		 
		return null;
	}

}
