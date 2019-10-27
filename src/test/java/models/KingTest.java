package models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class KingTest {

	public KingTest() {
	}

	private King king;

	@Before
	public void before() {
		king = new King();
	}

	@Test
	public void whenKingMovesCorrectly_shouldBeAbleToMove() {
		Coordinate origin = new Coordinate(8, 1);
		Coordinate target = new Coordinate(1, 8);
		assertTrue(king.canMove(origin, target));
	}
	
	@Test
	public void whenKingTriesMovingBackwards_shouldBeAbleToMove() {
		Coordinate origin = new Coordinate(1, 8);
		Coordinate target = new Coordinate(8, 1);
		assertTrue(king.canMove(origin, target));
	}

	@Test
	public void whenKingTriesMovingStraight_shouldNotBeAbleToMove() {
		Coordinate origin = new Coordinate(8, 1);
		Coordinate target = new Coordinate(1, 1);
		assertFalse(king.canMove(origin, target));
	}
}
