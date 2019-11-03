package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

public class CommandView {

	private Console console;
	private static final String[] COLORS = { "blancas", "negras" };
	private final String MOVE = "1) Mover";
	private final String CANCEL = "2) Cancelar";
	private final String CHOOSE_OPTION = "Elige opción: ";
	private final String ERROR = "Error!!! ";
	private final String YOU_LOST = "Derrota!!! No puedes mover tus fichas!!!";

	public CommandView() {
		this.console = new Console();
	}

	public void interact(PlayController playController) {
		Boolean keepAsking;
		this.console.writeln(MOVE);
		this.console.writeln(CANCEL);
		do {
			keepAsking = false;
			String playerOption = this.console.readString(CHOOSE_OPTION);
			if ("1".equals(playerOption)) {
				Error error = null;
				String color = CommandView.COLORS[playController.getColor().ordinal()];
				do {
					String command = this.console.readString("Mueven las " + color + ": ");
					int origin = Integer.parseInt(command.substring(0, 2));
					int target = Integer.parseInt(command.substring(3, 5));
					error = playController.move(new Coordinate(origin / 10, origin % 10),
							new Coordinate(target / 10, target % 10));
					if (error != null) {
						console.writeln(ERROR + error.getText());
					}
				} while (error != null);
				this.console.writeln(playController.printGame());
				if (playController.isWinner()) {
					this.console.writeln(YOU_LOST);
					playController.nextState();
				}
			} else if ("2".equals(playerOption)) {
				playController.nextState();
			} else {
				console.writeln(ERROR + Error.WRONG_OPTION.getText());
				keepAsking = true;
			}
		} while (keepAsking);
	}

}