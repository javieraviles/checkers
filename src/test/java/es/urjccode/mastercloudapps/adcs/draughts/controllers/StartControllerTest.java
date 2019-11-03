package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;

public class StartControllerTest {

	public StartControllerTest() {
	}

	private StartController startController;
	private Game game;
	private State state;

	@Before
	public void before() {
		game = new Game();
		state = new State();
		startController = new StartController(game, state);
	}

	@Test
	public void whenStartingNewGame_StateShouldChangeFromInitialToOpenedGame() {
		assertEquals(StateValue.INITIAL, state.getStateValue());
		startController.play();
		assertEquals(StateValue.OPENED_GAME, state.getStateValue());
	}

	@Test
	public void whenStartingNewGame_shouldPiecesBePopulatedInTheBoard() {
		startController.play();
		assertEquals(Color.WHITE, game.getPiece(new Coordinate(7, 0)).getColor());
		assertEquals(Color.BLACK, game.getPiece(new Coordinate(1, 2)).getColor());
	}
}