package models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TurnTest {

	public TurnTest() {

	}
	
	private Turn turn;
	
	@Before
	public void before(){
		turn = new Turn();
	}

	@Test
	public void whenTakingTurn_whiteShouldStart() {
		assertEquals(Color.WHITE, turn.take());
	}

	@Test
	public void whenChangingTurn_blackShouldGo() {
		turn.change();
		assertEquals(Color.BLACK, turn.take());
	}

	@Test
	public void whenChangingTwice_whiteShouldGo() {
		turn.change();
		turn.change();
		assertEquals(Color.WHITE, turn.take());
	}
}
