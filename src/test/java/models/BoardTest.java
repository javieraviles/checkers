package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	public BoardTest() {

	}

	private Board board;
	
	@Before
	public void before(){
		board = new Board();
	}

	@Test
	public void whenCreatingNewBoard_shouldBeCompletelyEmpty() {
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size() - 1; j++) {
				assertNull(board.getPiece(new Coordinate(i, j)));
			}
		}
	}

	@Test
	public void whenBoardIsPopulated_everyPieceShouldBeCorrectlyPositioned() {
		board.populateBoard();
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(8, 1)).getColor());
		board.removePiece(new Coordinate(8, 1));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(8, 3)).getColor());
		board.removePiece(new Coordinate(8, 3));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(8, 5)).getColor());
		board.removePiece(new Coordinate(8, 5));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(8, 7)).getColor());
		board.removePiece(new Coordinate(8, 7));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(7, 1)).getColor());
		board.removePiece(new Coordinate(7, 1));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(7, 4)).getColor());
		board.removePiece(new Coordinate(7, 4));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(7, 6)).getColor());
		board.removePiece(new Coordinate(7, 6));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(7, 8)).getColor());
		board.removePiece(new Coordinate(7, 8));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(6, 1)).getColor());
		board.removePiece(new Coordinate(6, 1));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(6, 3)).getColor());
		board.removePiece(new Coordinate(6, 3));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(6, 5)).getColor());
		board.removePiece(new Coordinate(6, 5));
		assertEquals(Color.WHITE, board.getPiece(new Coordinate(6, 7)).getColor());
		board.removePiece(new Coordinate(6, 7));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(1, 2)).getColor());
		board.removePiece(new Coordinate(1, 2));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(1, 4)).getColor());
		board.removePiece(new Coordinate(1, 4));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(1, 6)).getColor());
		board.removePiece(new Coordinate(1, 6));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(1, 8)).getColor());
		board.removePiece(new Coordinate(1, 8));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(2, 1)).getColor());
		board.removePiece(new Coordinate(2, 1));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(2, 3)).getColor());
		board.removePiece(new Coordinate(2, 3));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(2, 5)).getColor());
		board.removePiece(new Coordinate(2, 5));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(2, 7)).getColor());
		board.removePiece(new Coordinate(2, 7));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(3, 2)).getColor());
		board.removePiece(new Coordinate(3, 2));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(3, 4)).getColor());
		board.removePiece(new Coordinate(3, 4));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(3, 6)).getColor());
		board.removePiece(new Coordinate(3, 6));
		assertEquals(Color.BLACK, board.getPiece(new Coordinate(3, 8)).getColor());
		board.removePiece(new Coordinate(3, 8));
		for (int i = 0; i < board.size() - 1; i++) {
			for (int j = 0; j < board.size() - 1; j++) {
				assertNull(board.getPiece(new Coordinate(i, j)));
			}
		}
	}

	@Test
	public void whenPlayerWhiteHasNoPieces_blackIsWinner() {
		board.populateBoard();
		board.removePiece(new Coordinate(8, 1));
		board.removePiece(new Coordinate(8, 3));
		board.removePiece(new Coordinate(8, 5));
		board.removePiece(new Coordinate(8, 7));
		board.removePiece(new Coordinate(7, 1));
		board.removePiece(new Coordinate(7, 4));
		board.removePiece(new Coordinate(7, 6));
		board.removePiece(new Coordinate(7, 8));
		board.removePiece(new Coordinate(6, 1));
		board.removePiece(new Coordinate(6, 3));
		board.removePiece(new Coordinate(6, 5));
		board.removePiece(new Coordinate(6, 7));
		assertEquals(Color.BLACK, board.isThereWinner());
	}

	@Test
	public void whenPlayerWhiteCantMove_blackIsWinner() {
		board.populateBoard();
		board.removePiece(new Coordinate(8, 1));
		board.removePiece(new Coordinate(8, 3));
		board.removePiece(new Coordinate(8, 5));
		board.removePiece(new Coordinate(8, 7));
		board.removePiece(new Coordinate(7, 1));
		board.removePiece(new Coordinate(7, 4));
		board.removePiece(new Coordinate(7, 6));
		board.removePiece(new Coordinate(7, 8));
		board.removePiece(new Coordinate(6, 1));
		board.removePiece(new Coordinate(6, 3));
		board.removePiece(new Coordinate(6, 5));
		board.movePiece(new Coordinate(6, 7), new Coordinate(5, 8));
		board.movePiece(new Coordinate(5, 8), new Coordinate(4, 7));
		assertEquals(Color.BLACK, board.isThereWinner());
	}

	@Test
	public void whenClearingBoard_shouldHaveNoPieces() {
		board.clear();
		for (int i = 0; i < board.size() - 1; i++) {
			for (int j = 0; j < board.size() - 1; j++) {
				assertNull(board.getPiece(new Coordinate(i, j)));
			}
		}
	}

}
