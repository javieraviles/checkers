package models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PieceTest {

	public PieceTest() {

	}

	@Test
	public void whenCreatingWhitePiece_colorShouldCorrectlySet() {
		Piece piece = new Piece(Color.WHITE);
		assertEquals(Color.WHITE, piece.getColor());
	}

	@Test
	public void whenCreatingBlackPiece_colorShouldCorrectlySet() {
		Piece piece = new Piece(Color.BLACK);
		assertEquals(Color.BLACK, piece.getColor());
	}

}
