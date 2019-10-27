package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import models.Color;
import models.Coordinate;
import models.Error;
import models.Game;
import models.Piece;
import models.State;

public class PlayControllerTest {

	public PlayControllerTest() {

	}

	/*
	 * INITIAL BOARD VIEW
	 * 
	 * 12345678 1 n n n n1 2n n n n 2 3 n n n n3 4 4 5 5 6b b b b 6 7 b b b b7 8b b
	 * b b 8 12345678
	 */

	private State state;
	private Game game;
	private PlayController playController;

	@Before
	public void before() {
		game = new Game();
		state = new State();
		playController = new PlayController(game, state);
	}

	@Test
	public void whenMovingCorrectly_shouldNotThrowError() {
		Coordinate origin = new Coordinate(6, 1);
		Coordinate target = new Coordinate(5, 2);
		assertNull(playController.move(origin, target));
		assertNull(playController.getPiece(origin));
		Piece pieceTarget = playController.getPiece(target);
		assertNotNull(pieceTarget);
		assertEquals(Color.WHITE, pieceTarget.getColor());
	}

	@Test
	public void whenMovingToOccupiedPlace_shouldThrowError() {
		Coordinate origin = new Coordinate(1, 2);
		Coordinate target = new Coordinate(2, 1);
		assertEquals(Error.TARGET_NOT_EMPTY, playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNotNull(playController.getPiece(target));
	}

	@Test
	public void whenNotMovingDiagonally_shouldThrowError() {
		Coordinate origin = new Coordinate(1, 2);
		Coordinate target = new Coordinate(2, 2);
		assertEquals(Error.NOT_MOVING_DIAGONALLY, playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNull(playController.getPiece(target));
	}

	@Test
	public void whenNotMovingForward_shouldThrowError() {
		Coordinate origin = new Coordinate(6, 1);
		Coordinate target = new Coordinate(5, 2);
		playController.move(origin, target);
		assertEquals(Error.NOT_MOVING_FORWARD, playController.move(target, origin));
		assertNotNull(playController.getPiece(target));
		assertNull(playController.getPiece(origin));
	}

	@Test
	public void whenThereIsNoPieceInOrigin_shouldThrowError() {
		Coordinate origin = new Coordinate(1, 1);
		Coordinate target = new Coordinate(2, 2);
		assertEquals(Error.NO_PIECE_TO_MOVE, playController.move(origin, target));
		assertNull(playController.getPiece(origin));
	}

	@Test
	public void whenMovingTooFarAway_shouldThrowError() {
		Coordinate origin = new Coordinate(3, 2);
		Coordinate target = new Coordinate(5, 4);
		assertEquals(Error.RESPECT_DISTANCE, playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNull(playController.getPiece(target));
	}

	@Test
	public void whenMovingLikeGettingOpponentPieceWithNoPiece_shouldThrowError() {
		Coordinate origin = new Coordinate(5, 4);
		Coordinate target = new Coordinate(3, 2);
		playController.move(new Coordinate(3, 2), new Coordinate(4, 3));
		playController.move(new Coordinate(6, 3), new Coordinate(5, 4));
		assertEquals(Error.NOT_GETTING_PIECES, playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNull(playController.getPiece(target));
	}

	@Test
	public void whenPieceGettingEndOfBoard_shouldBeConvertedIntoKingAndWillBeAbleToMoveBackwardsAndLongDistancesButNeverGetMultiplePiecesInOneMovement() {
		playController.move(new Coordinate(3, 2), new Coordinate(4, 2));
		playController.move(new Coordinate(2, 1), new Coordinate(3, 2));
		playController.move(new Coordinate(1, 2), new Coordinate(2, 1));
		playController.move(new Coordinate(3, 4), new Coordinate(4, 3));
		playController.move(new Coordinate(6, 1), new Coordinate(5, 2));
		playController.move(new Coordinate(5, 2), new Coordinate(1, 2));
		assertTrue(playController.getPiece(new Coordinate(1, 2)).isKing());
		playController.move(new Coordinate(3, 6), new Coordinate(4, 5));
		playController.move(new Coordinate(1, 2), new Coordinate(5, 6));
		assertTrue(playController.getPiece(new Coordinate(5, 6)).isKing());
		playController.move(new Coordinate(1, 4), new Coordinate(2, 3));
		playController.move(new Coordinate(2, 7), new Coordinate(3, 6));
		playController.move(new Coordinate(5, 6), new Coordinate(4, 7));
		assertEquals(Error.CANT_GET_MANY_PIECES_IN_ONE_JUMP,
				playController.move(new Coordinate(4, 7), new Coordinate(1, 4)));
	}

}