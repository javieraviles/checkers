package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import models.Color;
import models.Coordinate;
import models.Game;
import models.Piece;

public class PlayControllerTest {

	public PlayControllerTest() {

	}

	/*
	 * INITIAL BOARD VIEW
	 * 
	 * 12345678 1 n n n n1 2n n n n 2 3 n n n n3 4 4 5 5 6b b b b 6 7 b b b b7 8b b
	 * b b 8 12345678
	 */

	private Game game;
	private PlayController playController;

	@Before
	public void before() {
		game = new Game();
		playController = new PlayController(game);
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
		assertEquals("Error! target not empty", playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNotNull(playController.getPiece(target));
	}

	@Test
	public void whenNotMovingDiagonally_shouldThrowError() {
		Coordinate origin = new Coordinate(1, 2);
		Coordinate target = new Coordinate(2, 2);
		assertEquals("Error! Not moving diagonally", playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNull(playController.getPiece(target));
	}

	@Test
	public void whenNotMovingForward_shouldThrowError() {
		Coordinate origin = new Coordinate(6, 1);
		Coordinate target = new Coordinate(5, 2);
		playController.move(origin, target);
		assertEquals("Error! Not moving forward", playController.move(target, origin));
		assertNotNull(playController.getPiece(target));
		assertNull(playController.getPiece(origin));
	}

	@Test
	public void whenThereIsNoPieceInOrigin_shouldThrowError() {
		Coordinate origin = new Coordinate(1, 1);
		Coordinate target = new Coordinate(2, 2);
		assertEquals("Error! No piece to move", playController.move(origin, target));
		assertNull(playController.getPiece(origin));
	}

	@Test
	public void whenMovingTooFarAway_shouldThrowError() {
		Coordinate origin = new Coordinate(3, 2);
		Coordinate target = new Coordinate(5, 4);
		assertEquals("Error! respect the distance", playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNull(playController.getPiece(target));
	}

	@Test
	public void whenKingGettingMoreThanOnePieceInOneMovement_shouldThrowError() {
		Coordinate origin = new Coordinate(5, 4);
		Coordinate target = new Coordinate(2, 1);
		playController.move(new Coordinate(3, 2), new Coordinate(4, 3));
		playController.move(new Coordinate(2, 1), new Coordinate(3, 2));
		playController.move(new Coordinate(6, 3), new Coordinate(5, 4));
		playController.getPiece(origin).makeKing();
		assertEquals("Error! can't get that many pieces in one jump", playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNull(playController.getPiece(target));
	}

	@Test
	public void whenMovingLikeGettingOpponentPieceWithNoPiece_shouldThrowError() {
		Coordinate origin = new Coordinate(5, 4);
		Coordinate target = new Coordinate(3, 2);
		playController.move(new Coordinate(3, 2), new Coordinate(4, 3));
		playController.move(new Coordinate(6, 3), new Coordinate(5, 4));
		assertEquals("Error! not getting any opponent pieces", playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNull(playController.getPiece(target));
	}

	@Test
	public void whenPieceGettingEndOfBoard_shouldBeConvertedIntoKingAndWillBeAbleToMoveBackwardsAndLongDistances() {
		playController.removePiece(new Coordinate(1, 2));
		playController.move(new Coordinate(3, 4), new Coordinate(4, 3));
		playController.move(new Coordinate(6, 1), new Coordinate(5, 2));
		playController.move(new Coordinate(5, 2), new Coordinate(1, 2));
		assertTrue(playController.getPiece(new Coordinate(1, 2)).isKing());
		playController.move(new Coordinate(3, 6), new Coordinate(4, 5));
		playController.move(new Coordinate(1, 2), new Coordinate(5, 6));
		assertTrue(playController.getPiece(new Coordinate(5, 6)).isKing());
	}

}