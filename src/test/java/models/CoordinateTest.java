package models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordinateTest {

	private Coordinate coordinate;

	@Test
	public void whenCreatingCoordinate_getsCreatedCorrectly() {
		coordinate = new Coordinate(1, 2);
		assertEquals(1, coordinate.getRow());
		assertEquals(2, coordinate.getColumn());
		coordinate = new Coordinate(0, 2);
		assertEquals(0, coordinate.getRow());
		assertEquals(2, coordinate.getColumn());
	}

	@Test(expected = AssertionError.class)
	public void whenCreatingCoordinateOutOfSquare_shouldThrowException() {
		coordinate = new Coordinate(-1, 3);
	}

	@Test
	public void whenCreatingCoordinateFromCoordinate_shouldBeCreatedFine() {
		coordinate = new Coordinate(new Coordinate(1, 2));
		assertEquals(1, coordinate.getRow());
		assertEquals(2, coordinate.getColumn());
		coordinate = new Coordinate(new Coordinate(0, 0));
		assertEquals(0, coordinate.getRow());
		assertEquals(0, coordinate.getColumn());
	}

	@Test
	public void whenSettingRow_shouldChangeCorrectly() {
		coordinate = new Coordinate(1, 2);
		coordinate.setRow(2);
		assertEquals(2, coordinate.getRow());
		assertEquals(2, coordinate.getColumn());
		coordinate = new Coordinate(2, 2);
		coordinate.setRow(0);
		assertEquals(0, coordinate.getRow());
		assertEquals(2, coordinate.getColumn());
	}

	@Test(expected = AssertionError.class)
	public void whenSettingRowTooFarAway_shouldThrowException() {
		coordinate = new Coordinate(1, 2);
		coordinate.setRow(3);
	}

	@Test
	public void whenSettingColumn_shouldChangeCorrectly() {
		coordinate = new Coordinate(1, 2);
		coordinate.setColumn(0);
		assertEquals(1, coordinate.getRow());
		assertEquals(0, coordinate.getColumn());
		coordinate = new Coordinate(2, 2);
		coordinate.setColumn(1);
		assertEquals(2, coordinate.getRow());
		assertEquals(1, coordinate.getColumn());
	}

	@Test(expected = AssertionError.class)
	public void whenSettingColumnTooFarAway_shouldThrowException() {
		coordinate = new Coordinate(1, 2);
		coordinate.setColumn(3);
	}

}
