package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void whenAssertingWhetherCoordinateIsBetweenDiagonal_shouldBeEqualForSomeExamples() {
		assertEquals(new Coordinate(1, 1), new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 0)));
		assertEquals(new Coordinate(3, 1), new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 0)));
		assertEquals(new Coordinate(3, 3), new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 4)));
		assertEquals(new Coordinate(1, 3), new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 4)));
	}

	@Test
	public void whenCalculatingDiagonalDistance_shouldBeEqualForSomeExamples() {
		int diagonalDistance = 3;
		assertEquals(diagonalDistance, new Coordinate(3, 4).diagonalDistance(new Coordinate(0, 7)));
	}

	@Test
	public void whenCreatingCoordinate_shouldBeValidIfWithinLimits() {
		assertTrue(new Coordinate(4, 7).isValid());
	}

	@Test
	public void whenCheckingWhetherCoordinateIsDiagonal_shouldBeTrueForSomeExamples() {
		assertTrue(new Coordinate(4, 5).isDiagonal(new Coordinate(5, 6)));
		assertTrue(new Coordinate(3, 2).isDiagonal(new Coordinate(2, 1)));
	}

}