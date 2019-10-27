package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import models.Game;
import models.State;
import models.StateValue;

public class CancelControllerTest {
	
	public CancelControllerTest() {
	}

	private CancelController cancelController;
	private Game game;
	private State state;

	@Before
	public void before() {
		game = new Game();
		state = new State();
		cancelController = new CancelController(game, state);
	}

	@Test
	public void whenCancelingTheGame_shouldStateGoDirectlyFinal() {
		state.nextState();
		assertEquals(StateValue.OPENED_GAME, state.getStateValue());
		cancelController.stopGame();
		assertEquals(StateValue.FINAL, state.getStateValue());
	}
}
