package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import models.Game;
import models.State;
import models.StateValue;

public class ResumeControllerTest {
	
	public ResumeControllerTest() {
	}

	private ResumeController resumeController;
	private Game game;
	private State state;

	@Before
	public void before() {
		game = new Game();
		state = new State();
		resumeController = new ResumeController(game, state);
	}

	@Test
	public void whenPlayingAgain_shouldGetStateBackToInitial() {
		state.nextState();
		state.nextState();
		assertEquals(StateValue.FINAL, state.getStateValue());
		resumeController.resume(true);
		assertEquals(StateValue.INITIAL, state.getStateValue());
	}
}
