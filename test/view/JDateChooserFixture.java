package view;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FocusableComponentFixture requireFocused() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyboardInputSimulationFixture pressAndReleaseKeys(int... keyCodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyboardInputSimulationFixture pressKey(int keyCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyboardInputSimulationFixture pressAndReleaseKey(
			KeyPressInfo keyPressInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyboardInputSimulationFixture releaseKey(int keyCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MouseInputSimulationFixture click() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MouseInputSimulationFixture click(MouseButton button) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MouseInputSimulationFixture click(MouseClickInfo mouseClickInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MouseInputSimulationFixture doubleClick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MouseInputSimulationFixture rightClick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateVerificationFixture requireDisabled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateVerificationFixture requireEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateVerificationFixture requireEnabled(Timeout timeout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateVerificationFixture requireNotVisible() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateVerificationFixture requireVisible() {
		// TODO Auto-generated method stub
		return null;
	}

}
