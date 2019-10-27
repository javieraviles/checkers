package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
	public GameTest() {
	}

	private Game game;

	@Before
	public void before() {
		game = new Game();
	}

	@Test
	public void whenGameStarts_shouldTurnBeWhiteAndNoWinnerYet() {
		assertEquals(Color.WHITE, game.getTurn().take());
		assertNull(game.isWinner());
	}

	@Test
	public void whenGameStartsAndMakingOneMove_shouldTurnBeBlackAndNoWinnerYet() {
		Coordinate origin = new Coordinate(6, 1);
		Coordinate target = new Coordinate(5, 2);
		game.move(origin, target);
		assertEquals(Color.BLACK, game.getTurn().take());
		assertNull(game.isWinner());
	}

	@Test
	public void whenWhiteWinsGame_shouldBeWinner() {
		playEntireGameWhereWhiteIsWinner();
		assertEquals(Color.WHITE, game.isWinner());
	}

	private void playEntireGameWhereWhiteIsWinner() {
		// TODO - to be implemented
	}
}
