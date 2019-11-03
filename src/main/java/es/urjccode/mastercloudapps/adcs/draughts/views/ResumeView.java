package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.ResumeController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

public class ResumeView {

	private Console console;

	public ResumeView() {
		this.console = new Console();
	}

	private static final char AFIRMATIVE = 's';
	private static final char NEGATIVE = 'n';
	private static final String QUESTION = "¿Queréis jugar otra? (" + ResumeView.AFIRMATIVE + "/" + ResumeView.NEGATIVE
			+ "): ";
	private static final String MESSAGE = "El valor tiene que ser '" + ResumeView.AFIRMATIVE + "' o '"
			+ ResumeView.NEGATIVE + "'";

	public void interact(ResumeController resumeController) {
		char answer;
		boolean ok;
		do {
			answer = this.console.readChar(ResumeView.QUESTION);
			ok = ResumeView.isAfirmative(answer) || ResumeView.isNegative(answer);
			if (!ok) {
				this.console.writeln(ResumeView.MESSAGE);
			}
		} while (!ok);
		resumeController.resume(ResumeView.isAfirmative(answer));
	}

	private static boolean isAfirmative(char answer) {
		return Character.toLowerCase(answer) == ResumeView.AFIRMATIVE;
	}

	private static boolean isNegative(char answer) {
		return Character.toLowerCase(answer) == ResumeView.NEGATIVE;
	}

}