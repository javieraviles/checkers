package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;

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
	public void givenResumeControllerWhenResumeGameMoveToInitialStateRequiereCorrectThenNotError() {
		resumeController.nextState();
		resumeController.nextState();
		assertEquals(StateValue.FINAL, state.getStateValue());
		resumeController.resume(true);
		assertEquals(StateValue.INITIAL, state.getStateValue());
	}

}