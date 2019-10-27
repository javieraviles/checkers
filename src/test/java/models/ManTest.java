package models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ManTest {
	public ManTest() {
	}

	private Man man;

	@Before
	public void before() {
		man = new Man();
	}

	@Test
	public void whenManMovesCorrectly_shouldBeAbleToMove() {
		Coordinate origin = new Coordinate(8, 1);
		Coordinate target = new Coordinate(7, 2);
		assertTrue(man.canMove(origin, target));
	}

	@Test
	public void whenManTriesMovingBackwards_shouldNotBeAbleToMove() {
		Coordinate origin = new Coordinate(7, 2);
		Coordinate target = new Coordinate(8, 1);
		assertFalse(man.canMove(origin, target));
	}

	@Test
	public void whenManTriesMovingStraight_shouldNotBeAbleToMove() {
		Coordinate origin = new Coordinate(8, 1);
		Coordinate target = new Coordinate(7, 1);
		assertFalse(man.canMove(origin, target));
	}
}
