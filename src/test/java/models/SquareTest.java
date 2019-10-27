package models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SquareTest {
	public SquareTest() {

	}

	@Test
	public void whenCreatingNewBlackSquare_shouldAcceptPiece() {
		Square square = new Square(Color.BLACK);
		assertTrue(square.isEmpty());
		square.setPiece(new Piece(Color.WHITE));
		assertFalse(square.isEmpty());
	}

	@Test(expected = AssertionError.class)
	public void whenCreatingNewWhiteSquareAndTryToSetPiece_shouldThrowException() {
		Square square = new Square(Color.WHITE);
		square.setPiece(new Piece(Color.WHITE));
	}
}
