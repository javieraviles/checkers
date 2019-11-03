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

import es.urjccode.mastercloudapps.adcs.draughts.controllers.ResumeController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class ResumeViewTest {

	@InjectMocks
	ResumeView resumeView;

	@Mock
	ResumeController resumeController;

	@Mock
	Console console;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenAskingToPlayAgainAnsweringInvalidCharacterAndYes_shouldRestartGameCorrectlyAndPrintConsoleMessage() {
		when(console.readChar("¿Queréis jugar otra? (s/n): ")).thenReturn('p').thenReturn('s');
		resumeView.interact(resumeController);
		verify(console, times(1)).writeln("El valor tiene que ser 's' o 'n'");
		verify(resumeController, times(1)).resume(true);
	}

	@Test
	public void whenAskingToPlayAgainAndAnsweringNo_shouldGoNextStateCorrectly() {
		when(console.readChar("¿Queréis jugar otra? (s/n): ")).thenReturn('n');
		resumeView.interact(resumeController);
		verify(resumeController, times(1)).resume(false);
	}

}