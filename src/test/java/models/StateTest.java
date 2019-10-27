package models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StateTest {

	public StateTest() {
	}

	private State state;

	@Before
	public void before() {
		this.state = new State();
	}

	@Test
	public void whenGettingToNextState_shouldChangeCorrectly() {
		assertEquals(StateValue.INITIAL, this.state.getStateValue());
		this.state.nextState();
		assertEquals(StateValue.OPENED_GAME, this.state.getStateValue());
		this.state.nextState();
		assertEquals(StateValue.FINAL, this.state.getStateValue());
	}
}
