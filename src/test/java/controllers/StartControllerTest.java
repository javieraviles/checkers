package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import models.Color;
import models.Coordinate;
import models.Game;
import models.State;
import models.StateValue;

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
		assertEquals(StateValue.INITIAL, this.state.getStateValue());
		startController.play();
		assertEquals(StateValue.OPENED_GAME, this.state.getStateValue());
	}

	@Test
	public void whenStartingNewGame_shouldPiecesBePopulatedInTheBoard() {
		startController.play();
		assertEquals(Color.WHITE, game.getPiece(new Coordinate(8, 1)).getColor());
		assertEquals(Color.BLACK, game.getPiece(new Coordinate(2, 3)).getColor());
	}

}
