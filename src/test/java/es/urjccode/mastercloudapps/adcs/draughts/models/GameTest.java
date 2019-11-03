package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

	@InjectMocks
	Game game;

	@Mock
	Board board;

	@Mock
	Turn turn;

	@Test()
	public void whenTryingToMoveOutOfBounds_gameShouldThrowOutCoordinateError() {
		Coordinate validOrigin = new Coordinate(4, 7);
		Coordinate outOfBoundsTarget = new Coordinate(3, 8);
		Error error = game.move(validOrigin, outOfBoundsTarget);
		assertEquals(Error.OUT_COORDINATE, error);
		Mockito.verify(board, never()).move(validOrigin, outOfBoundsTarget);
		Mockito.verify(turn, never()).change();
	}

	@Test
	public void whenTryingToMoveEmptyCoordinate_gameShouldThrowEmptyOriginError() {
		Coordinate emptyOrigin = new Coordinate(4, 3);
		Coordinate target = new Coordinate(3, 4);
		when(board.isEmpty(emptyOrigin)).thenReturn(true);
		assertEquals(Error.EMPTY_ORIGIN, game.move(emptyOrigin, target));
		Mockito.verify(board, never()).move(emptyOrigin, target);
		Mockito.verify(turn, never()).change();
	}

	@Test
	public void whenTryingToMoveOpponentPiece_gameShouldThrowOppositePieceError() {
		Coordinate opponentOrigin = new Coordinate(3, 6);
		Coordinate target = new Coordinate(2, 7);
		Color opponentColor = Color.BLACK;
		when(board.getColor(opponentOrigin)).thenReturn(opponentColor);
		when(turn.isColor(opponentColor)).thenReturn(false);
		Error error = game.move(opponentOrigin, target);
		assertEquals(Error.OPPOSITE_PIECE, error);
		Mockito.verify(board, never()).move(opponentOrigin, target);
		Mockito.verify(turn, never()).change();
	}

	@Test
	public void whenTryingNotToMoveDiagonally_gameShouldThrowNotDiagonalError() {
		Coordinate origin = new Coordinate(5, 0);
		Coordinate notDiagonalTarget = new Coordinate(4, 0);
		Color turnColor = Color.BLACK;
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(turnColor)).thenReturn(true);
		assertEquals(Error.NOT_DIAGONAL, this.game.move(origin, notDiagonalTarget));
		Mockito.verify(board, never()).move(origin, notDiagonalTarget);
		Mockito.verify(turn, never()).change();
	}

	@Test
	public void whenTryingToMoveAndNotGoingForwards_shouldThrowNotAdvancedError() {
		Coordinate origin = new Coordinate(4, 5);
		Coordinate notadvancedTarget = new Coordinate(3, 4);
		Color turnColor = Color.BLACK;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(turnColor)).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		Error error = game.move(origin, notadvancedTarget);
		assertEquals(Error.NOT_ADVANCED, error);
		Mockito.verify(board, never()).move(origin, notadvancedTarget);
		Mockito.verify(turn, never()).change();
	}
	
	@Test
	public void whenTryingToMoveTooFarAway_gameShouldThrowBadDistanceError() {
		Coordinate origin = new Coordinate(5, 0);
		Coordinate tooFarAwayTarget = new Coordinate(2, 3);
		Color turnColor = Color.WHITE;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(turnColor)).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		Error error = game.move(origin, tooFarAwayTarget);
		assertEquals(Error.BAD_DISTANCE, error);
		Mockito.verify(board, never()).move(origin, tooFarAwayTarget);
		Mockito.verify(turn, never()).change();
	}

	@Test
	public void whenTryingtoMoveToNonEmptyTarget_shouldThrowNotEmptyTargetError() {
		Coordinate origin = new Coordinate(1, 2);
		Coordinate notEmptyTarget = new Coordinate(2, 1);
		Color turnColor = Color.BLACK;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(turnColor)).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		when(board.isEmpty(notEmptyTarget)).thenReturn(false);
		Error error = game.move(origin, notEmptyTarget);
		assertEquals(Error.NOT_EMPTY_TARGET, error);
		Mockito.verify(board, never()).move(origin, notEmptyTarget);
		Mockito.verify(turn, never()).change();
	}

	@Test
	public void whenDoingCorrectMove_gameShouldTellBoardToMoveAndTurnToChange() {
		Coordinate origin = new Coordinate(5, 0);
		Coordinate target = new Coordinate(4, 1);
		Color turnColor = Color.WHITE;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(any(Color.class))).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		when(board.isEmpty(target)).thenReturn(true);
		this.game.move(origin, target);
		Mockito.verify(board, times(1)).move(origin, target);
		Mockito.verify(turn, times(1)).change();
	}

	@Test
	public void whenMovingAndTryingToEatButNoPieceBetween_gameShouldThrowEatingEmptyError() {
		Coordinate origin = new Coordinate(3, 0);
		Coordinate target = new Coordinate(5, 2);
		Coordinate between = origin.betweenDiagonal(target);
		Color turnColor = Color.BLACK;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(any(Color.class))).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		when(board.isEmpty(target)).thenReturn(true);
		when(board.getPiece(between)).thenReturn(null);
		assertEquals(Error.EATING_EMPTY, game.move(origin, target));
		Mockito.verify(board, never()).remove(between);
		Mockito.verify(board, never()).move(origin, target);
		Mockito.verify(turn, never()).change();
	}

	@Test
	public void whenMovingAndEating_gameShouldTellBoardToRemoveBetween() {
		Coordinate origin = new Coordinate(3, 0);
		Coordinate target = new Coordinate(5, 2);
		Coordinate between = origin.betweenDiagonal(target);
		Color turnColor = Color.BLACK;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(any(Color.class))).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		when(board.isEmpty(target)).thenReturn(true);
		when(board.getPiece(between)).thenReturn(new Piece(Color.WHITE));
		Error error = game.move(origin, target);
		assertNull(error);
		Mockito.verify(board, times(1)).remove(between);
		Mockito.verify(board, times(1)).move(origin, target);
		Mockito.verify(turn, times(1)).change();
	}

}