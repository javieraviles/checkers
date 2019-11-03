package es.urjccode.mastercloudapps.adcs.draughts.views;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class CommandViewTest {

	@InjectMocks
	CommandView commandView;

	@Mock
	PlayController playController;

	@Mock
	Console console;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenUserEnteringCoordinatesCorrectly_shouldTellPlayControllerToMove() {
		Coordinate origin = new Coordinate(2, 3);
		Coordinate target = new Coordinate(3, 2);
		when(console.readString("Mueven las blancas: ")).thenReturn("23.32\n");
		when(console.readString("Elige opción: ")).thenReturn("1");
		when(playController.getColor()).thenReturn(Color.WHITE);
		commandView.interact(playController);
		verify(playController, times(1)).move(origin, target);
	}

	@Test
	public void whenThereIsWinner_shouldTellPlayControllerToGoNextState() {
		when(playController.getColor()).thenReturn(Color.WHITE);
		when(console.readString("Mueven las blancas: ")).thenReturn("23.32\n");
		when(console.readString("Elige opción: ")).thenReturn("1");
		when(playController.isWinner()).thenReturn(true);
		commandView.interact(playController);
		verify(playController, times(1)).nextState();
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenUserEnteringWrongCharactersAsCoordinates_shouldThrowException() {
		when(playController.getColor()).thenReturn(Color.WHITE);
		when(console.readString("Mueven las blancas: ")).thenReturn("asdas\n");
		when(console.readString("Elige opción: ")).thenReturn("1");
		commandView.interact(playController);
	}

	@Test
	public void whenUserChoosesCancel_shouldTellPlayCoontrollerToGoNextState() {
		when(console.readString("Elige opción: ")).thenReturn("2");
		commandView.interact(playController);
		verify(playController, times(1)).nextState();
	}

}