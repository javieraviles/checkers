package models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TurnTest {

	public TurnTest() {

	}

	@Test
	public void whenTakingTurn_whiteShouldStart() {
		Turn turn = new Turn();
		assertEquals(Color.WHITE, turn.take());
	}

	@Test
	public void whenChangingTurn_blackShouldGo() {
		Turn turn = new Turn();
		turn.change();
		assertEquals(Color.BLACK, turn.take());
	}

	@Test
	public void whenChangingTwice_whiteShouldGo() {
		Turn turn = new Turn();
		turn.change();
		turn.change();
		assertEquals(Color.WHITE, turn.take());
	}
}
