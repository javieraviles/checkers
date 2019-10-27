package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import models.Color;
import models.Coordinate;
import models.Game;
import models.Piece;

public class PlayControllerTest {

	public PlayControllerTest() {

	}

    /*
 INITIAL BOARD VIEW
     * 
	 12345678
	1 n n n n1
	2n n n n 2
	3 n n n n3
	4        4
	5        5
	6b b b b 6
	7 b b b b7
	8b b b b 8
	 12345678
     * */
	
	@Test
	public void whenMovingCorrectly_shouldNotThrowError() {
		Game game = new Game();
		Coordinate origin = new Coordinate(6, 1);
		Coordinate target = new Coordinate(5, 2);
		PlayController playController = new PlayController(game);
		assertNull(playController.move(origin, target));
		assertNull(playController.getPiece(origin));
		Piece pieceTarget = playController.getPiece(target);
		assertNotNull(pieceTarget);
		assertEquals(Color.WHITE, pieceTarget.getColor());
	}

	@Test
	public void whenMovingToOccupiedPlace_shouldThrowError() {
		Game game = new Game();
		Coordinate origin = new Coordinate(1, 2);
		Coordinate target = new Coordinate(2, 1);
		PlayController playController = new PlayController(game);
		assertEquals("Error! target not empty", playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNotNull(playController.getPiece(target));
	}

	@Test
	public void whenNotMovingDiagonally_shouldThrowError() {
		Game game = new Game();
		Coordinate origin = new Coordinate(1, 2);
		Coordinate target = new Coordinate(2, 2);
		PlayController playController = new PlayController(game);
		assertEquals("Error! Not moving diagonally", playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNull(playController.getPiece(target));
	}

	@Test
	public void whenNotMovingForward_shouldThrowError() {
		Game game = new Game();
		Coordinate origin = new Coordinate(6, 1);
		Coordinate target = new Coordinate(5, 2);
		PlayController playController = new PlayController(game);
		playController.move(origin, target);
		assertEquals("Error! Not moving forward", playController.move(target, origin));
		assertNotNull(playController.getPiece(target));
		assertNull(playController.getPiece(origin));
	}

	@Test
	public void whenThereIsNoPieceInOrigin_shouldThrowError() {
		Game game = new Game();
		Coordinate origin = new Coordinate(1, 1);
		Coordinate target = new Coordinate(2, 2);
		PlayController playController = new PlayController(game);
		assertEquals("Error! No piece to move", playController.move(origin, target));
		assertNull(playController.getPiece(origin));
	}

	@Test
	public void whenMovingTooFarAway_shouldThrowError() {
		Game game = new Game();
		Coordinate origin = new Coordinate(3, 2);
		Coordinate target = new Coordinate(5, 4);
		PlayController playController = new PlayController(game);
		assertEquals("Error! respect the distance", playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNull(playController.getPiece(target));
	}

	@Test
	public void whenGettingMoreThanOneOpponentPieceInOneMovement_shouldThrowError() {
		Game game = new Game();
		Coordinate origin = new Coordinate(5, 4);
		Coordinate target = new Coordinate(2, 1);
		PlayController playController = new PlayController(game);
		playController.move(new Coordinate(3, 2), new Coordinate(4, 3));
		playController.move(new Coordinate(2, 1), new Coordinate(3, 2));
		playController.move(new Coordinate(6, 3), new Coordinate(5, 4));
		assertEquals("Error! can't get that many pieces in one movement", playController.move(origin, target));
		assertNotNull(playController.getPiece(origin));
		assertNull(playController.getPiece(target));
	}
	
	/*
	 * HAY QUE TENER EN CUENTA QUE ALGUNOS ERRORES SON PARA LA DAMA
	 * 
Error!!! No te entiendo: <d><d>{,<d><d>}[0-2]
Error!!! No es una coordenada del tablero
Error!!! No hay ficha que mover
Error!!! No es una de tus fichas
Error!!! No vas en diagonal
Error!!! No está vacío el destino
Error!!! No comes contrarias
Error!!! No se puede comer tantas en un movimiento
Error!!! No avanzas
Error!!! No respetas la distancia
Error!!! No se puede comer tantas en un salto
	 * */

}