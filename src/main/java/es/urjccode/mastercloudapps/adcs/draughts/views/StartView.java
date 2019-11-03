package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.StartController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

class StartView {

	Console console;

	private String INITIAL_MESSAGE = "Las Damas!!!";

	public StartView() {
		this.console = new Console();
	}

	void interact(StartController startController) {
		startController.play();
		this.console.writeln(INITIAL_MESSAGE);
		this.console.writeln();
		this.console.writeln(startController.getStringBoardGame());
	}

}
