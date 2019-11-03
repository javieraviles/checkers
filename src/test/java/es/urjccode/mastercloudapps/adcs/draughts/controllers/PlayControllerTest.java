package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;

public class PlayControllerTest {

	Game game;
	State state;

	public PlayControllerTest() {
		this.state = new State();
		this.game = new Game();
	}

	@Test
	public void whenMovingCorrectly_shouldTellTheGameToMove() {
		Coordinate origin = new Coordinate(5, 2);
		Coordinate target = new Coordinate(4, 3);
		PlayController playController = new PlayController(this.game, this.state);
		assertNull(playController.move(origin, target));
		assertNull(playController.getPiece(origin));
		Piece pieceTarget = playController.getPiece(target);
		assertNotNull(pieceTarget);
		assertEquals(pieceTarget.getColor(), Color.WHITE);
	}

}