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

	@Test
	public void givenPlayControllerWhenMovementRequiereCorrectThenNotError() {
		PlayController playController = new PlayController(new Game(), new State());
		Coordinate origin = new Coordinate(5, 0);
		Coordinate target = new Coordinate(4, 1);
		assertNull(playController.move(origin, target));
		assertNull(playController.getPiece(origin));
		Piece pieceTarget = playController.getPiece(target);
		assertNotNull(pieceTarget);
		assertEquals(Color.WHITE, pieceTarget.getColor());
	}

}